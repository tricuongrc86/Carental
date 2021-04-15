/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tricuong.lab231.carrental.database.orderdetailtbl;

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
public class OrderTblDetailDAO {

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

    public boolean createBillDetail(OrderTblDetailDTO dto) throws SQLException, NamingException, ParseException {
        conn = null;
        stm = null;
        boolean check = false;
        try {
            conn = DBHelper.makeConnection();
            if (conn != null) {
                String sql = "insert into OrderDetailTbl(IdOderDetail,IdCar,quantity,price,IdOrder,RentDate,ReturnDate)\n"
                        + "values(?,?,?,?,?,?,?)";
                stm = conn.prepareStatement(sql);
                stm.setString(1, dto.getIdOrderDetail());
                stm.setString(2, dto.getIdCar());
                stm.setInt(3, dto.getQuantity());
                stm.setFloat(4, dto.getPrice());
                stm.setString(5, dto.getIdOrder());
                stm.setTimestamp(6, Ultilities.convertStringToTimestamp1(dto.getRentDate()));
                stm.setTimestamp(7, Ultilities.convertStringToTimestamp1(dto.getReturnDate()));
                check = stm.executeUpdate() > 0;
            }
        } finally {
            closeConnection();
        }
        return check;
    }

    public List<OrderTblDetailDTO> getAllHistory(String idOrder) throws SQLException, NamingException {
        conn = null;
        stm = null;
        rs = null;
        List<OrderTblDetailDTO> list = new ArrayList<>();
        try {
            conn = DBHelper.makeConnection();
            if (conn != null) {
                String sql = "select b.[image],b.price,a.quantity,a.price,Convert(varchar,a.RentDate,105) as rentDate,Convert(varchar,a.ReturnDate,105) as returnDate\n"
                        + "from OrderDetailTbl a,CarTbl b\n"
                        + "where IdOrder=? and a.IdCar=b.CarID";
                stm = conn.prepareStatement(sql);
                stm.setString(1, idOrder);
                rs = stm.executeQuery();
                OrderTblDetailDTO dto = null;
                while (rs.next()) {
                    String img = rs.getString(1);
                    float unitPrice = rs.getFloat(2);
                    int quantity = rs.getInt(3);
                    float price = rs.getFloat(4);
                    String dateRent = rs.getString(5);
                    String dateReturn = rs.getString(6);
                    dto = new OrderTblDetailDTO(quantity, price, dateRent, dateReturn, img, unitPrice);
                    list.add(dto);
                }
            }
        } finally {
            closeConnection();
        }
        return list;
    }
}
