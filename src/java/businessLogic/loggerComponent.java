/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package businessLogic;

import java.io.*;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author AhmedShalaby
 */
public class loggerComponent {

    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
    LocalDateTime now = LocalDateTime.now();
    String systime = dtf.format(now);
    Connection conn = null;
    String logCategory = null;
    String logMessage = null;

    public void test() throws ClassNotFoundException, InstantiationException, IllegalAccessException {

        try {

            Class.forName("com.mysql.jdbc.Driver").newInstance();

            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/integration?"
                    + "user=root&password=ahmedtato1&useSSL=false");

//    info("connected to mySQL");
            Statement stmt = null;

            String qurey = "insert into integration.i_log (log_category,log_message,log_time) values ('" + logCategory.trim() + "','" + logMessage.trim() + "','" + systime + "');";
            Statement st = conn.createStatement();
            st.execute(qurey);

        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }

    }

    public void logEvent(String cabinetName, String cabinetID) {
        print("cabinet name :" + cabinetName + " cabinet ID: " + cabinetID + "");
    }

    public void print(String text) {
        System.out.println(text);
    }

    public void info(String text) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        try {
            System.out.println("Info " + systime + ": " + text + ".");
            this.logCategory = "info";
            this.logMessage = text;
            test();
        } catch (Exception ex) {

        }
    }

    public void error(String text) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        try {
            System.out.println("Error " + systime + ": " + text + ".");
            this.logCategory = "Error";
            this.logMessage = text;

            test();
        } catch (Exception ex) {

        }

    }

    public void warning(String text) throws ClassNotFoundException, InstantiationException {
        try {
            System.out.println("Warning " + systime + ": " + text + ".");
            this.logCategory = "Warning";
            this.logMessage = text;

            test();
        } catch (Exception ex) {

        }
    }

    public void success(String text) throws ClassNotFoundException, InstantiationException {
        try {
            System.out.println("Success " + systime + ": " + text + ".");
            this.logCategory = "Success";
            this.logMessage = text;

            test();
        } catch (Exception ex) {

        }
    }

    public void exec(String text) throws ClassNotFoundException, InstantiationException {
        try {
            System.out.println("Execute " + systime + ": " + text + ".");
            this.logCategory = "Execute";
            this.logMessage = text;

            test();
        } catch (Exception ex) {

        }
    }

    public void function(String text) throws ClassNotFoundException, InstantiationException {
        try {
            System.out.println("Function " + systime + ": " + text + ".");
            this.logCategory = "Function";
            this.logMessage = text;

            test();
        } catch (Exception ex) {

        }
    }

    public void database(String text) throws ClassNotFoundException, InstantiationException {
        System.out.println("Database " + systime + ": " + text + ".");
        this.logCategory = "Database";
        this.logMessage = text;
        try {
            test();
        } catch (IllegalAccessException ex) {
            Logger.getLogger(loggerComponent.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void logCabinet(List<String> _listCabinet, List<String> _listCabinetID) throws ClassNotFoundException, SQLException, InstantiationException {
        String user = "test";
        _listCabinet.add("1-1-1-1");
        _listCabinetID.add("1234");

        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();

            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/integration?"
                    + "user=root&password=ahmedtato1&useSSL=false");
            Statement stmt;
            String _querylogCabinet;
            ResultSet rs;

            stmt = conn.createStatement();
            for (int i = 0; i < _listCabinet.size(); i++) {
                stmt.executeUpdate("INSERT INTO `integration`.`cabinet_log`"
                        + "("
                        + "`cabinet_name`,"
                        + "`cabinet_id`,"
                        + "`transaction_id`,"
                        + "`username`)"
                        + "VALUES"
                        + "('"+_listCabinet.get(i)+"','"+_listCabinetID.get(i)+"','testTransactionID', + '"+user+"')");
            }
         if (conn.isValid(0))
            conn.close();

        } catch (Exception ex) {

        }
    }

}
