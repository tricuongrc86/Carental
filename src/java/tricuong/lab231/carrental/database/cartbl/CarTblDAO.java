/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tricuong.lab231.carrental.database.cartbl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import tricuong.lab231.carrental.database.connection.DBHelper;

/**
 *
 * @author TriCuong
 */
public class CarTblDAO {

    static Connection conn = null;
    static PreparedStatement stm = null;
    static ResultSet rs = null;
    static int limit = 5;

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

    public List<CarTblDTO> getListCarHaveDayRent(String dateRent, String dateReturn, String nameSearch, String typeSearch, int index, int amount) throws SQLException, NamingException {
        conn = null;
        stm = null;
        rs = null;
        CarTblDTO dto = null;
        List<CarTblDTO> list = new ArrayList<>();
        int start = (index - 1) * limit;
        try {
            conn = DBHelper.makeConnection();
            if (conn != null) {
//                String sql = "select main.CarID,main.Name,main.[image],main.[type],main.color,main.price,main.availble\n"
//                        + "from\n"
//                        + "((select c.CarID,c.Name,c.image,c.type,c.color,c.price,c.availble\n"
//                        + "from\n"
//                        + "	(select CarID,Name,type,image,color,price,(quantity-BusyCar) as availble\n"
//                        + "	 from carTbl a,(select sum(quantity) as BusyCar,IdCar\n"
//                        + "					from OrderDetailTbl\n"
//                        + "					 where  (@dateRent between RentDate and ReturnDate) or (@dateReturn between rentDate and returndate)\n"
//                        + "					 group by IdCar) b\n"
//                        + "	 where a.CarID=b.IdCar) c\n"
//                        + "where c.availble>0)\n"
//                        + "union\n"
//                        + "(select c.CarID,c.Name,c.image,c.type,c.color,c.price,c.quantity\n"
//                        + " from CarTbl c\n"
//                        + " where  not EXISTS (select *\n"
//                        + "                    from (select sum(quantity) as BusyCar,IdCar\n"
//                        + "						  from OrderDetailTbl\n"
//                        + "                          where(@dateRent between RentDate and ReturnDate) or (@dateReturn between rentDate and returndate)\n"
//                        + "                          group by IdCar) b\n"
//                        + "                          where c.CarID=b.IdCar )\n"
//                        + "                  )) main\n"
//                        + "where main.Name like '%'+@nameSearch+'%' and main.availble>=@amount and main.[type] like '%'+@typeSearch+'%'";
                String sql = "exec searchCar @dateRent=?,@dateReturn=?,@nameSearch=?,@typeSearch=?,@amount=?,@indexStart=?,@limit=?";
                stm = conn.prepareStatement(sql);
                stm.setString(1, dateRent);
                stm.setString(2, dateReturn);
                stm.setString(3, nameSearch);
                stm.setString(4, typeSearch);
                stm.setInt(5, amount);
                stm.setInt(6, start);
                stm.setInt(7, limit);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String id = rs.getString(1);
                    String name = rs.getString(2);
                    String image = rs.getString(3);
                    String type = rs.getString(4);
                    String color = rs.getString(5);
                    float price = rs.getFloat(6);
                    int quantity = rs.getInt(7);
                    dto = new CarTblDTO(id, name, image, type, color, quantity, price);
                    list.add(dto);
                }
            }
        } finally {
            closeConnection();
        }
        return list;
    }

    public int countPage(String dateRent, String dateReturn, String nameSearch, String typeSearch, int amount) throws SQLException, NamingException {
        conn = null;
        stm = null;
        rs = null;
        int countPage = 0;
        try {
            conn = DBHelper.makeConnection();
            if (conn != null) {
                String sql = "exec countPage @dateRent=?,@dateReturn=?,@typeSearch=?,@nameSearch=?,@amount=?";
                stm = conn.prepareStatement(sql);
                stm.setString(1, dateRent);
                stm.setString(2, dateReturn);
                stm.setString(3, nameSearch);
                stm.setString(4, typeSearch);
                stm.setInt(5, amount);
                rs = stm.executeQuery();
                if (rs.next()) {
                    int total = rs.getInt(1);
                    countPage = total / limit;
                    if (total % limit != 0) {
                        countPage++;
                    }
                }
            }
        } finally {
            closeConnection();
        }
        return countPage;
    }

    public CarTblDTO getInfoCar(String id, String dateRent, String dateReturn) throws SQLException, NamingException {
        conn = null;
        stm = null;
        rs = null;
        CarTblDTO dto = null;
        try {
            conn = DBHelper.makeConnection();
            if (conn != null) {
                String sql = "exec getInfoCar @idCar=?,@dateRent=?,@dateReturn=?";
                stm = conn.prepareStatement(sql);
                stm.setString(1, id);
                stm.setString(2, dateRent);
                stm.setString(3, dateReturn);
                rs = stm.executeQuery();
                if (rs.next()) {
                    String name = rs.getString(1);
                    String image = rs.getString(2);
                    String type = rs.getString(3);
                    String color = rs.getString(4);
                    float price = rs.getFloat(5);
                    int availble = rs.getInt(6);
                    dto = new CarTblDTO(id, name, image, type, color, availble, price);
                }
            }
        } finally {
            closeConnection();
        }
        return dto;
    }
    public int getAvailbleCar(String id, String dateRent, String dateReturn) throws SQLException, NamingException {
        conn = null;
        stm = null;
        rs = null;
        int  availble = 0;
        try {
            conn = DBHelper.makeConnection();
            if (conn != null) {
                String sql = "exec getAvailbleCar @idCar=?,@dateRent=?,@dateReturn=?";
                    stm = conn.prepareStatement(sql);
                stm.setString(1, id);
                stm.setString(2, dateRent);
                stm.setString(3, dateReturn);
                rs = stm.executeQuery();
                if (rs.next()) {
                   availble=rs.getInt(1);
                }
            }
        } finally {
            closeConnection();
        }
        return availble+1;
    }
   

    public List<String> getAllTypeCar() throws SQLException, NamingException {
        conn = null;
        stm = null;
        rs = null;
        List<String> list = new ArrayList<>();
        try {
            conn = DBHelper.makeConnection();
            if (conn != null) {
                String sql = "select Type\n"
                        + "from TypeCarTbl";
                stm = conn.prepareStatement(sql);
                rs = stm.executeQuery();
                while (rs.next()) {
                    list.add(rs.getString(1));
                }
            }
        } finally {
            closeConnection();
        }
        return list;
    }

}
