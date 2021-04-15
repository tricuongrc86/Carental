/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tricuong.lab231.carrental.database.registrationtbl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import javax.naming.NamingException;
import tricuong.lab231.carrental.common.Ultilities;
import tricuong.lab231.carrental.database.connection.DBHelper;

/**
 *
 * @author richardnguyen3599
 */
public class RegistrationTblDAO {

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

    public boolean checkLoginByID(String userID, String password) throws SQLException, NamingException {
        conn = null;
        stm = null;
        rs = null;
        boolean check = false;
        try {
            conn = DBHelper.makeConnection();
            if (conn != null) {
                String sql = "select userID\n"
                        + "from RegistrationTbl\n"
                        + "where userID=? and [PASSWORD]=? and [status]=1";
                stm = conn.prepareStatement(sql);
                stm.setString(1, userID);
                stm.setString(2, password);
                rs = stm.executeQuery();
                if (rs.next()) {
                    check = true;
                }
            }
        } finally {
            closeConnection();
        }
        return check;
    }

    public String checkRole(String userID) throws SQLException, NamingException {
        conn = null;
        stm = null;
        rs = null;
        String role = null;
        try {
            conn = DBHelper.makeConnection();
            if (conn != null) {
                String sql = "select role\n"
                        + "from RegistrationTbl\n"
                        + "where userID=?";
                stm = conn.prepareStatement(sql);
                stm.setString(1, userID);
                rs = stm.executeQuery();
                if (rs.next()) {
                    role = rs.getString(1);
                }
            }
        } finally {
            closeConnection();
        }
        return role;
    }

    public String getName(String userID) throws SQLException, NamingException {
        conn = null;
        stm = null;
        rs = null;
        String name = null;
        try {
            conn = DBHelper.makeConnection();
            if (conn != null) {
                String sql = "select name\n"
                        + "from RegistrationTbl\n"
                        + "where userID=?";
                stm = conn.prepareStatement(sql);
                stm.setString(1, userID);
                rs = stm.executeQuery();
                if (rs.next()) {
                    name = rs.getString(1);
                }
            }
        } finally {
            closeConnection();
        }
        return name;
    }

    public String getCodeVerify(String email) throws SQLException, NamingException {
        conn = null;
        stm = null;
        rs = null;
        String name = null;
        try {
            conn = DBHelper.makeConnection();
            if (conn != null) {
                String sql = "select Verification\n"
                        + "from RegistrationTbl\n"
                        + "where userID=?";
                stm = conn.prepareStatement(sql);
                stm.setString(1, email);
                rs = stm.executeQuery();
                if (rs.next()) {
                    name = rs.getString(1);
                }
            }
        } finally {
            closeConnection();
        }
        return name;
    }

    public boolean checkExistedEmail(String email) throws SQLException, NamingException {
        conn = null;
        stm = null;
        rs = null;
        boolean check = false;
        try {
            conn = DBHelper.makeConnection();
            if (conn != null) {
                String sql = "select userID\n"
                        + "from RegistrationTbl\n"
                        + "where userID=?";
                stm = conn.prepareStatement(sql);
                stm.setString(1, email);
                rs = stm.executeQuery();
                if (rs.next()) {
                    check = true;
                }
            }
        } finally {
            closeConnection();
        }
        return check;
    }

    public boolean createAcc(RegistrationTblDTO dto) throws SQLException, NamingException, ParseException {
        conn = null;
        stm = null;
        boolean check = false;
        try {
            conn = DBHelper.makeConnection();
            if (conn != null) {
                String sql = "insert into RegistrationTbl(userID,PASSWORD,name,Phone,Address,role,status,dateCreate,Verification)\n"
                        + "values(?,?,?,?,?,?,?,?,?)\n";
                stm = conn.prepareStatement(sql);
                stm.setString(1, dto.getEmail());
                stm.setString(2, dto.getPassword());
                stm.setString(3, dto.getName());
                stm.setString(4, dto.getPhone());
                stm.setString(5, dto.getAddress());
                stm.setString(6, dto.getRole());
                stm.setBoolean(7, dto.isStatus());
                stm.setTimestamp(8, Ultilities.convertStringToTimestamp(dto.getDateCreate()));
                stm.setString(9, dto.getVerification());
                check = stm.executeUpdate() > 0;
            }
        } finally {
            closeConnection();
        }
        return check;
    }

    public boolean verifyAccount(String email) throws SQLException, NamingException, ParseException {
        conn = null;
        stm = null;
        boolean check = false;
        try {
            conn = DBHelper.makeConnection();
            if (conn != null) {
                String sql = "update RegistrationTbl\n"
                        + "set role='Active'\n"
                        + "where userID=?";
                stm = conn.prepareStatement(sql);
                stm.setString(1, email);
                check = stm.executeUpdate() > 0;
            }
        } finally {
            closeConnection();
        }
        return check;
    }

    public boolean updateVerifyCode(String email, String code) throws SQLException, NamingException, ParseException {
        conn = null;
        stm = null;
        boolean check = false;
        try {
            conn = DBHelper.makeConnection();
            if (conn != null) {
                String sql = "update RegistrationTbl\n"
                        + "set Verification=?\n"
                        + "where userID=?";
                stm = conn.prepareStatement(sql);
                stm.setString(1, code);
                stm.setString(2, email);
                check = stm.executeUpdate() > 0;
            }
        } finally {
            closeConnection();
        }
        return check;
    }
}
