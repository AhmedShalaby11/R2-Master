/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package businessLogic;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import oracle.jdbc.pool.OracleDataSource;

/**
 *
 * @author AhmedShalaby
 */
public class syncDatabase extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    loggerComponent log = new loggerComponent();

    BufferedReader br = null;

    List<String> _ListCSVcabinet = new ArrayList<String>();
    List<String> _ListcabinetService_ID = new ArrayList<String>();
    List<String> _ListcabinetName = new ArrayList<String>();
    List<String> _ListcabinetID = new ArrayList<String>();
    List<String> _ListGetCsv = new ArrayList<String>();
    List<String> _ListRowData = new ArrayList<String>();
    List<String> _ListQuery = new ArrayList<String>();
    String line = "";
    String _queryCheckMSAN;
    String cabinetSVLAN;
    String cabinetSLOT;
    String cabinetID;
    String msanName;
    String msanID;
    String msandata;
    String splitter2 = "", line2 = "", splitter = ",";
    ResultSet emps = null;
    String Cabinets = "C:\\Users\\AShalaby11\\Downloads\\R1.23\\web\\uploads\\cabinets.csv";
    String Services = "C:\\Users\\AShalaby11\\Downloads\\R1.23\\web\\uploads\\cabinetServices.csv";
    Statement stmt;
    ResultSet rs;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter pr = response.getWriter()) {
            try {
                OracleDataSource ods = new OracleDataSource();
                ods.setURL("jdbc:oracle:thin:ADSLPRO_APP/ADSLPRO_APP_123@10.230.91.213:1527:DPROPRD1");

                try {
                    Class.forName("com.mysql.jdbc.Driver").newInstance();
                } catch (InstantiationException ex) {
                    Logger.getLogger(syncDatabase.class.getName()).log(Level.SEVERE, null, ex);
                }
            } catch (IllegalAccessException ex) {
                Logger.getLogger(syncDatabase.class.getName()).log(Level.SEVERE, null, ex);
            }

            clearLists();
//            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/integration?"
//                    + "user=root&password=ahmedtato1&useSSL=false");

            Statement stmt;
            ResultSet rs;

            //read cabinets.csv
            br = new BufferedReader(new FileReader(Cabinets));

            splitter2 = ",";
            line2 = "";
            while ((line2 = br.readLine()) != null) {
                //check if line is not a normal text
                if (line2.contains("-") == false) {
                    //error("incorrect format")

                }
                String data[] = line2.split(splitter2);
                //adding csv msans to list in a clean format.
                for (String recordValue : data) {
                    _ListCSVcabinet.add(recordValue.replace("'", ""));
                }
                //done

            }
            br.close();
            //end of while loop

            //2.check if these csv list exist in database.
            //2.a.check if the list is not an empty list then query.
//             2.b.loop through every cabinets to check existance according to 
//                list count..
            for (int i = 0; i < _ListCSVcabinet.size(); i++) {
                _queryCheckMSAN = "select cabinet_name from adsl_cabinets where cabinet_name ='" + _ListCSVcabinet.get(i) + "'";
//               2.c.Generates a list query.
                _ListQuery.add(_queryCheckMSAN);
//end of for loop

            }
            //end of while loop
            if (_ListQuery.isEmpty() == false) {
                //connect and get the existing cabs..

                OracleDataSource ods = new OracleDataSource();
                ods.setURL("jdbc:oracle:thin:ADSLPRO_APP/ADSLPRO_APP_123@10.230.91.213:1527:DPROPRD1");

                Class.forName("com.mysql.jdbc.Driver").newInstance();

                Connection conn = ods.getConnection();
                stmt = conn.createStatement();
                //loopt according to listquery size
                for (int i = 0; i < _ListQuery.size(); i++) {
                    rs = stmt.executeQuery(_ListQuery.get(i));
                    //add to list if it's not exist.
                    if (rs.next()) {
                        //get record data.
                        msanName = rs.getString("cabinet_name");

                        //add record data to list.
                        _ListcabinetName.add(msanName);
                        //end

                    } else {
                        //error.("msan already exists");
                    }
                    //end of if statement.
                }
            }
            //important to close database connection.

//compare and get the delta.
            _ListCSVcabinet.removeAll(_ListcabinetName);
            //---------------------------------------------
            //end of check
//         ---------------------------------------------

            if (_ListCSVcabinet.isEmpty() == false) {   //starting to insert msans
                for (int i = 0; i < _ListCSVcabinet.size(); i++) {
                    OracleDataSource ods = new OracleDataSource();
                    ods.setURL("jdbc:oracle:thin:ADSLPRO_APP/ADSLPRO_APP_123@10.230.91.213:1527:DPROPRD1");

                    Class.forName("com.mysql.jdbc.Driver").newInstance();
                    Connection conn = ods.getConnection();
                    stmt = conn.createStatement();
                    String _query = "insert into adsl_cabinets (cabinet_id,cabinet_name,arabic_name,total_number_of_ports,site_id,is_deleted,BRAS_dynamic,protocol_id,msan_id) values ((select (max(cabinet_id)+1) from adsl_cabinets),'" + _ListCSVcabinet.get(i) + "','" + _ListCSVcabinet.get(i) + "',400,246062,'N','Y',6,'" + _ListCSVcabinet.get(i) + "')";
                    stmt.execute(_query);
                }
                //end of loop .
                //msans insreted.

            } else {
                pr.append("<p>MSANS already updated.</p>");

            }

//         add MSAN services (MSAN ID ,SVLAN ,SLOT,BRAS ID );
            addCabinetServices();

        }
    }

    public void addCabinetServices() throws ClassNotFoundException {

        try {
            clearLists();
            //define connection string and targted database
//            Class.forName("com.mysql.jdbc.Driver").newInstance();
//           
//            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/integration?"
//                    + "user=root&password=ahmedtato1&useSSL=false");

            OracleDataSource ods = new OracleDataSource();
            ods.setURL("jdbc:oracle:thin:ADSLPRO_APP/ADSLPRO_APP_123@10.230.91.213:1527:DPROPRD1");
            Connection conn = ods.getConnection();

            //read services CSV
            splitter = ";";
            br = new BufferedReader(new FileReader(Services));
            while ((line = br.readLine()) != null) {
                // 2. split and list data.
                String data[] = line.split(splitter);
//             3.List the MSANs
                for (String data1 : data) {
//                     log.print(data[i].replace("'", ""));
                    String[] ser = data1.split(",");
                    String Cabinet_Name = ser[0];
                    String Cabinet_Slot = ser[2]; //lag
                    String Cabinet_SVLAN = ser[1];
                    String Cabinet_Bras = ser[3].replace(";", "");
                    //check if exist.
                    stmt = conn.createStatement();
                    String _queryCheckMSANID_existance = "select cabinet_id from adsl_cabinets where cabinet_name=" + Cabinet_Name + "";
                    rs = stmt.executeQuery(_queryCheckMSANID_existance);
                    if (rs.next()) {
                        String cabinetID = rs.getString("cabinet_id");
                        stmt = conn.createStatement();
                        String _queryCheckMSAN_existance = "select cabinet_id from adsl_cabinet_service where cabinet_id='" + cabinetID + "'";
                        rs = stmt.executeQuery(_queryCheckMSAN_existance);
                        if (rs.next() == false) {
                            try{
                            stmt = conn.createStatement();
                            String _queryAddMSAN_Service = "insert into adsl_cabinet_service (cabinet_id,service_id,svlan,cabinet_slot,bras_id) values ((select cabinet_id from adsl_cabinets where cabinet_name=" + Cabinet_Name + "),'1'," + Cabinet_SVLAN + "," + Cabinet_Slot + ",(select id from adsl_bras where bras_name =" + Cabinet_Bras + "))";
                            stmt.executeQuery(_queryAddMSAN_Service);
                            }
                            catch(Exception ex)
                            {
                              
                            }
                        } else {
                            //not inserted in adsl_cabinet_service > cabinet found
                        }

                        //get the id from table and check the id in services and define to insert or not
                        //if record found
                    } else {

                        //cabinet not found in adsl_cabinets
                        //else not found > will insert the service.
                    }
                }
            }

        } catch (Exception ex) {

        }

    }

    public void clearLists() {
        _ListQuery.clear();
        _ListRowData.clear();
        _ListGetCsv.clear();
        _ListcabinetID.clear();
        _ListcabinetName.clear();
        _ListcabinetService_ID.clear();
        _ListCSVcabinet.clear();
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            try {
                processRequest(request, response);
            } catch (IllegalAccessException ex) {
                Logger.getLogger(syncDatabase.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (SQLException | ClassNotFoundException | InstantiationException ex) {
            Logger.getLogger(syncDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            try {
                try {
                    processRequest(request, response);
                } catch (IllegalAccessException ex) {
                    Logger.getLogger(syncDatabase.class.getName()).log(Level.SEVERE, null, ex);
                }
            } catch (ClassNotFoundException | InstantiationException ex) {
                Logger.getLogger(syncDatabase.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (SQLException ex) {
            Logger.getLogger(syncDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
