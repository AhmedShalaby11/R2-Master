/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package businessLogic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author AShalaby11
 */
public class _ConnectDerby {

    private static Connection con = null;
    private static Statement stmt = null;
    ResultSet resultSet = null;
    String _textOutput;
  static  ArrayList ar = new ArrayList();

    public void openConnection(String _databaseName, String _databaseUN, String _databasePW) throws SQLException, ClassNotFoundException {

        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();
            con = DriverManager.getConnection("jdbc:derby://localhost:1527/" + _databaseName + ";create=true;user=" + _databaseUN + ";password=" + _databaseUN + "");

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    static void _query(String sql) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
 
        stmt = con.createStatement();

        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {
            ar.add(rs.getString("USERNAEM"));
        }

    }

}
