/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tricuong.lab231.carrental.database.ordertbl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import tricuong.lab231.carrental.common.Ultilities;
import tricuong.lab231.carrental.database.connection.DBHelper;

/**
 *
 * @author TriCuong
 */
public class OrderTblDAO {

    static Connection conn = null;
    static PreparedStatement stm = null;
    static ResultSet rs = null;

    public static void closeConnection() throws SQLException, NamingException {
        if (rs != null) {
            rs.close();
        }
        if (stm != null) {
            stm.close();
        }
        if (conn != null) {
            conn.close();
        }
    }

    public int getMaxId() throws SQLException, NamingException {
        conn = null;
        stm = null;
        rs = null;
        int maxId = 0;
        try {
            conn = DBHelper.makeConnection();
            if (conn != null) {
                String sql = "SELECT a.IdOrder\n"
                        + "from(\n"
                        + "select Max(dateOrder) as dateMax\n"
                        + "from OrderTbl) main,OrderTbl a\n"
                        + "where main.dateMax=a.dateOrder";
                stm = conn.prepareStatement(sql);
                rs = stm.executeQuery();
                if (rs.next()) {
                    String id = rs.getString(1);
                    String numberStr = id.split("-")[1];
                    maxId = Integer.parseInt(numberStr);
                }
            }
        } finally {
            closeConnection();
        }
        return maxId + 1;
    }

    public boolean createBill(OderTblDTO dto) throws SQLException, NamingException, ParseException {
        conn = null;
        stm = null;
        boolean check = false;
        try {
            conn = DBHelper.makeConnection();
            if (conn != null) {
                String sql = "insert into OrderTbl(IdOrder,userID,totalPrice,dateOrder)\n"
                        + "values(?,?,?,?)";
                stm = conn.prepareStatement(sql);
                stm.setString(1, dto.getIdOrder());
                stm.setString(2, dto.getUserId());
                stm.setFloat(3, dto.getTotalPrice());
                stm.setTimestamp(4, Ultilities.convertStringToTimestamp(dto.getDateOrder()));
                check = stm.executeUpdate() > 0;
            }
        } finally {
            closeConnection();
        }
        return check;
    }

    public List<OderTblDTO> getAllHistory(String email) throws SQLException, NamingException {
        conn = null;
        stm = null;
        rs = null;
        List<OderTblDTO> list = new ArrayList<>();
        try {
            conn = DBHelper.makeConnection();
            if (conn != null) {
                String sql = "select IdOrder,totalPrice,Convert(varchar,dateOrder,105) as Date\n"
                        + "from OrderTbl\n"
                        + "where userID=?\n"
                        + "ORDER by dateOrder desc";
                stm = conn.prepareStatement(sql);
                stm.setString(1, email);
                rs = stm.executeQuery();
                OderTblDTO dto = null;
                while (rs.next()) {
                    String id = rs.getString(1);
                    float total = rs.getFloat(2);
                    String dateOrder = rs.getString(3);
                    dto = new OderTblDTO(id, total, dateOrder);
                    list.add(dto);
                }
            }
        } finally {
            closeConnection();
        }
        return list;
    }

    public OderTblDTO getBill(String idOrder) throws SQLException, NamingException {
        conn = null;
        stm = null;
        rs = null;
        OderTblDTO dto = null;
        try {
            conn = DBHelper.makeConnection();
            if (conn != null) {
                String sql = "select totalPrice,Convert(varchar,dateOrder,105) as Date\n"
                        + "from OrderTbl\n"
                        + "where IdOrder=?\n";
                stm = conn.prepareStatement(sql);
                stm.setString(1, idOrder);
                rs = stm.executeQuery();

                if (rs.next()) {

                    float total = rs.getFloat(1);
                    String dateOrder = rs.getString(2);
                    dto = new OderTblDTO(idOrder, total, dateOrder);
                }
            }
        } finally {
            closeConnection();
        }
        return dto;
    }

}
