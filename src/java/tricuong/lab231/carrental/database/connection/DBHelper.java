/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tricuong.lab231.carrental.database.connection;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author TriCuong
 */
public class DBHelper implements Serializable {

    public static Connection makeConnection() throws SQLException, NamingException {
        Connection conn;
        Context context = new InitialContext();
        Context tomCatContext = (Context) context.lookup("java:comp/env");
        DataSource dataSource = (DataSource) tomCatContext.lookup("DB_CarRental");
        conn = dataSource.getConnection();
        return conn;
    }
}
