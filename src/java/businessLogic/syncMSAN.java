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
public class syncMSAN extends HttpServlet {

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
    String cabinetSVLAN;
    String cabinetSLOT;
    String cabinetID;
    String msanName;
    String msandata;
    String splitter2 = "", line2 = "", splitter = ",";
    ResultSet emps = null;
    String ds = "/Users/AhmedShalaby/NetBeansProjects/Robust/web/uploads/cabinets.csv";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException, InstantiationException, SQLException, IllegalAccessException {

        try (PrintWriter out = response.getWriter()) {

            try {

                _ListRowData.clear();
                log.info("Started");
                _ListRowData.add("<tr><td>Started</td><td>Info</td><td id='label-success'>Success</td></tr>");
                log.info("Check cabinets existance");
                _ListRowData.add("<tr><td>Check cabinets existance</td><td>Info</td><td id='label-success'>Success</td></tr>");
                log.info("Reading CSV file " + ds);
                _ListRowData.add("<tr><td>Reading CSV file</td><td>Info</td><td id='label-success'>Success</td></tr>");

            } catch (Exception ex) {

            }
            OracleDataSource ods = new OracleDataSource();
            ods.setURL("jdbc:oracle:thin:ADSLPRO_APP/ADSLPRO_APP_123@10.230.91.213:1527:DPROPRD1");
            Connection conn = ods.getConnection();

            br = new BufferedReader(new FileReader(ds));
            splitter2 = ",";
            line2 = "";

            while ((line2 = br.readLine()) != null) {
                if (line2.contains("-") == false) {

                    log.error("Incorrect MSAN format given");

                    _ListRowData.add("<tr><td>Incorrect MSAN format given</td><td>Error</td><td id='label-error'>Error</td></tr>");

                } //correction 
                else {
                    if (conn.isValid(10)) {

                        log.database("connected to Oracle database :ADSL PRO PRD");
                        _ListRowData.add("<tr><td>Connected to Oracle database: ADSL PRO PRD</td><td>Info</td><td id='label-success'>Success</td></tr>");
                        log.info("Table.ADSL_CABINETS");
                        _ListRowData.add("<tr><td><b>Table.ADSL_CABINETS</b></td><td>Info</td><td id='label-success'>Success</td></tr>");

                    } else {
                        log.error("Cannot Establish a Database Connection");
                        _ListRowData.add("<tr><td>Cannot connect to Database</td><td>Error</td><td id='label-error'>Error</td></tr>");

                        break;
                    }
                }

            }
            //todo

            String checkQuery = "select cabinet_id,cabinet_name from adsl_cabinets where cabinet_name in (" + line2 + ")";
            Statement stm = conn.createStatement();
            try {
                emps = stm.executeQuery(checkQuery.trim());

                while (emps.next()) {

                    try {
                        cabinetID = emps.getString(1);
                        msanName = emps.getString(2);

                        //get a list of msan mapped to it's id.
                        _ListcabinetID.add(cabinetID);

                        //list for the database cabinets
                        _ListcabinetName.add(msanName);
                        //getting added msans.

                    } catch (SQLException ex) {

                        log.error("Exception: " + ex.toString());

                        _ListRowData.add("<tr><td>" + ex.toString() + "</td><td>Error</td><td id='label-error'>Exception</td></tr>");

                    }
                }

                log.info("Closing connection");
                _ListRowData.add("<tr><td>Closing connection</td><td>Info</td><td id='label-success'>Success</td></tr>");

            } catch (Exception ex) {

            }

            if (_ListcabinetID.isEmpty()) {
                log.warning("CSV data not found in database");

                _ListRowData.add("<tr><td>No data found in database matches with CSV file's data.</td><td>Info</td><td id='label-success'>Success</td></tr>");
            }
            if (emps.isClosed() == false) {
                //closing connections and readers if opened.
                emps.close();
                conn.close();
            }
            //compare the CSV with the database entries to get the delta.
//            if ("".equals(ds) || splitter == "") {
//                log.error("No file path defined or a splitter");
//                
//                log.info("Stopped");
//                return;
//            } 

//            else if("1".equals(checkServices))
//            {
            log.function("Check Cabinet Services");
            _ListRowData.add("<tr><td>Check cabinet Services</td><td>Info</td><td id='label-success'>Success</td></tr>");
            String _queryCheckMSAN_service;
            //todo:list object
            //get all services for msan ID 

            getServices(_ListcabinetID);
            //check MSANs services in database
            log.info("Getting MSANs ID");
            _ListRowData.add("<tr><td>Getting MSAN IDs</td><td>Info</td><td id='label-success'>Success</td></tr>");
            if (_ListcabinetID.size() > 0) {
                for (int i = 0; i < _ListcabinetID.size(); i++) {
                    //query
                    _queryCheckMSAN_service = "select * from adsl_cabinet_service where cabinet_id = '" + _ListcabinetID.get(i) + "' ";

//                    check this line
                }
            } //             
            //            }
            else {
// 1.Read CSV file
//                log.info("Reading CSV file " + ds.toString());
                br = new BufferedReader(new FileReader(ds));
                while ((line = br.readLine()) != null) {
                    // 2. split and list data.                
                    String data[] = line.split(splitter);
//             3.List the MSANs
                    for (String data1 : data) {
                        _ListCSVcabinet.add(data1.replace("'", ""));
                    }

                }

//         4. Compare lists and get delta
                log.function("Comparing database MSANs with CSV data");
                _ListRowData.add("<tr><td>Comparing database MSANs with CSV data</td><td>Info</td><td id='label-success'>Success</td></tr>");
                _ListCSVcabinet.removeAll(_ListcabinetName);
                _ListRowData.add("<tr><td>Adding new " + _ListCSVcabinet.size() + " MSAN</td><td>Info</td><td id='label-success'>Success</td></tr></tbody></table></div>");

                if (_ListCSVcabinet.size() == 0) {
                    log.info("No changes on MSANs table");
                    _ListRowData.add("<tr><td>No changes on MSANs table</td><td>Info</td><td id='label-success'>Success</td></tr>");
                } else {
                    log.exec("Updating Table.ADSL_CABINETS");
                    _ListRowData.add("<tr><td>Updating Table.ADSL_CABINETS</td><td>Info</td><td id='label-success'>Success</td></tr>");

                    try {
                        //open connection here

                        for (int indexOfMSAN = 0; indexOfMSAN < _ListCSVcabinet.size(); indexOfMSAN++) {
//            5.insert MSANs into adsl_cabinets table.
                            String _queryAddMSAN = "insert into adsl_cabinets (cabinet_id,cabinet_name,arabic_name,total_number_of_ports,site_id,is_deleted,BRAS_dynamic,protocol_id,msan_id) values ((select (max(cabinet_id)+1) from adsl_cabinets),'" + _ListCSVcabinet.get(indexOfMSAN) + "','" + _ListCSVcabinet.get(indexOfMSAN) + "',400,246062,'N','Y',6,'" + _ListCSVcabinet.get(indexOfMSAN) + "')";

                            _ListQuery.add(_queryAddMSAN);

                        }
                        insertCabinets(_ListQuery);

                        _ListRowData.add("<tr><td><b>" + _ListCSVcabinet.size() + " MSANs were added</b></td><td>Info</td><td id='label-success'>Success</td></tr>");
                        conn.close();
                        readServices();
                        _ListRowData.add("<tr><td><b>Services Updated</b></td><td>Info</td><td id='label-success'>Success</td></tr>");
                    } catch (Exception ex) {
                        

                    }

                }
                log.info("Process completed");
                _ListRowData.add("<tr><td>Process completed</td><td>Info</td><td id='label-success'>Success</td></tr>");
                log.info("Shutting down and closing connections");
                _ListRowData.add("<tr><td>Closing connections</td><td>Info</td><td id='label-success'>Success</td></tr></tbody></table></div>");

        RequestDispatcher rd = request.getRequestDispatcher("UItable");
                request.setAttribute("list", _ListRowData);
                rd.forward(request, response);
            }
  
        } //catch main try 
        catch (ClassNotFoundException | InstantiationException | SQLException ex) {
            log.error("Process Exception: " + ex.toString());
        }
              
    }

    public void readServices() throws FileNotFoundException, IOException, SQLException, ClassNotFoundException, InstantiationException {
        try {
            ds = "/Users/AhmedShalaby/NetBeansProjects/Robust/web/uploads/cabinetServices.csv";
            splitter = "';'";

            log.function("Inserting MSANs");
            _ListRowData.add("<tr><td> Inserting MSANs services</td><td>Info</td><td id='label-success'>Success</td></tr></tbody></table></div>");
            OracleDataSource ods = new OracleDataSource();
            ods.setURL("jdbc:oracle:thin:ADSLPRO_APP/ADSLPRO_APP_123@10.230.91.213:1527:DPROPRD1");
            Connection conn = ods.getConnection();

            log.database("connected to Oracle database :ADSL PRO PRD");
            _ListRowData.add("<tr><td>Connected to database</td><td>Info</td><td id='label-success'>Success</td></tr></tbody></table></div>");

            log.info("Table.ADSL_CABINET_SERVICE");

            _ListRowData.add("<tr><td>TABLE.ADSL_CABINETS_SERVICES</td><td>Info</td><td id='label-success'>Success</td></tr></tbody></table></div>");

            br = new BufferedReader(new FileReader(ds));
            while ((line = br.readLine()) != null) {
                // 2. split and list data.
                String data[] = line.split(splitter);
//             3.List the MSANs
                for (String data1 : data) {
//                     log.print(data[i].replace("'", ""));
                    String[] ser = data1.split(",");

                    log.function("Updating Table.ADSL_CABINET_SERVICE");
                    _ListRowData.add("<tr><td>Updating services</td><td>Info</td><td id='label-success'>Success</td></tr></tbody></table></div>");

                    Statement stat = conn.createStatement();
                    String _queryCheckMSAN_Service = "select * from adsl_cabinet_service where cabinet_id = ((select cabinet_id from adsl_cabinets where cabinet_name=" + ser[0] + "))";
                    ResultSet rs = stat.executeQuery(_queryCheckMSAN_Service);
                    if (rs.next() == false) {
                        try {

                            //
                            log.exec("MSAN Service Inserted");

                            String _queryAddMSAN_Service = "insert into adsl_cabinet_service (cabinet_id,service_id,svlan,cabinet_slot,bras_id) values ((select cabinet_id from adsl_cabinets where cabinet_name=" + ser[0] + "),'1'," + ser[1] + "," + ser[2] + ",(select id from adsl_bras where bras_name =" + ser[3].replace(";", "") + "))";
                            stat.executeQuery(_queryAddMSAN_Service);

                            //
                        } catch (InstantiationException ex) {
                            Logger.getLogger(updateMSAN.class.getName()).log(Level.SEVERE, null, ex);
                        }

                        try {
                            log.database("Connection Closed");
                            _ListRowData.add("<tr><td>Connection Closed</td><td>Info</td><td id='label-success'>Success</td></tr></tbody></table></div>");
                        } catch (ClassNotFoundException ex) {
                            Logger.getLogger(updateMSAN.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (InstantiationException ex) {
                            Logger.getLogger(updateMSAN.class.getName()).log(Level.SEVERE, null, ex);
                        }

                    } else {
                        try {
                            log.warning("MSAN Already exists");
                            _ListRowData.add("<tr><td>Services already exists.</td><td>Info</td><td id='label-success'>Success</td></tr></tbody></table></div>");
                        } catch (ClassNotFoundException ex) {
                            Logger.getLogger(updateMSAN.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (InstantiationException ex) {
                            Logger.getLogger(updateMSAN.class.getName()).log(Level.SEVERE, null, ex);
                        }

                    }
                }

            }
            log.database("Connection Closed");
            conn.close();
        } catch (IllegalAccessException ex) {
            Logger.getLogger(syncMSAN.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void insertCabinets(List<String> _ListQuery) throws SQLException, ClassNotFoundException, IllegalAccessException {
        try {
            log.function("Inserting MSANs");
            _ListRowData.add("<tr><td>Inserting MSANs</td><td>Info</td><td id='label-success'>Success</td></tr></tbody></table></div>");
            OracleDataSource ods = new OracleDataSource();
            log.database("connected to Oracle database :ADSL PRO PRD");

            log.info("Table.ADSL_CABINETS");

            ods.setURL("jdbc:oracle:thin:ADSLPRO_APP/ADSLPRO_APP_123@10.230.91.213:1527:DPROPRD1");
            try (Connection conn = ods.getConnection()) {
                Statement stat = conn.createStatement();
                for (int i = 0; i < _ListQuery.size(); i++) {
                    stat.executeQuery(_ListQuery.get(i));
                }

                log.exec("MSAN Inserted");
                conn.close();
                log.database("Connection Closed");
            }
        } catch (InstantiationException ex) {
            Logger.getLogger(updateMSAN.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void getServices(List<String> array) {

        for (int i = 0; i < _ListcabinetID.size(); i++) {
            log.print(array.get(i));
        }
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
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(syncMSAN.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(syncMSAN.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(syncMSAN.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(syncMSAN.class.getName()).log(Level.SEVERE, null, ex);
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
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(syncMSAN.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(syncMSAN.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(syncMSAN.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(syncMSAN.class.getName()).log(Level.SEVERE, null, ex);
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
