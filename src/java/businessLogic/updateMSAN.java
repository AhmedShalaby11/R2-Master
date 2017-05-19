/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package businessLogic;

import com.mysql.jdbc.PreparedStatement;
import java.awt.Button;
import java.io.BufferedReader;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import oracle.jdbc.pool.OracleDataSource;
import java.sql.DriverManager;
import java.util.List;
import oracle.jdbc.pool.OracleConnectionPoolDataSource;
import java.io.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author AhmedShalaby
 */
public class updateMSAN  {

        loggerComponent log = new loggerComponent();
        BufferedReader br = null;
        List<String> _ListCSVcabinet = new ArrayList<String>();
        List<String> _ListcabinetService_ID = new ArrayList<String>();
        List<String> _ListcabinetName = new ArrayList<String>();
        List<String> _ListcabinetID = new ArrayList<String>();
        List<String> _ListGetCsv = new ArrayList<String>();
        String line = "";
        String cabinetSVLAN;
        String cabinetSLOT;
        String cabinetID;
        String msanName;
        String msandata;
        String splitter2 = "", line2 = "";
        
        
          public void getServices(List<String> array)
               {
                  
                   for (int i = 0; i < _ListcabinetID.size(); i++) {
                       log.print(array.get(i));
                   }
               }
        
        
    public void checkCabinetExistance( String ds, String splitter,String checkServices) throws IOException, SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {

            
           
            
            log.info("Started");
            log.info("Check cabinets existance");
            OracleDataSource ods = new OracleDataSource();
            ods.setURL("jdbc:oracle:thin:ADSLPRO_APP/ADSLPRO_APP_123@10.230.91.213:1527:DPROPRD1");
            Connection conn = ods.getConnection();

            log.info("Reading CSV file " + ds);
            br = new BufferedReader(new FileReader(ds));
            splitter2 = ",";
            line2 = "";
            while ((line2 = br.readLine()) != null) {
                if (line2.contains("-") == false) {
                    log.error("Incorrect MSAN format given");
                }

                if (conn.isValid(10)) {
                    log.database("connected to Oracle database :ADSL PRO PRD");
                    log.info("Table.ADSL_CABINETS");
                }
                else
                {
                    log.error("Cannot connect to Database ");
                }

                String checkQuery = "select cabinet_id,cabinet_name from adsl_cabinets where cabinet_name in (" + line2 + ")";
                Statement stm = conn.createStatement();
                try {
                    ResultSet emps = stm.executeQuery(checkQuery.trim());

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
                        }
                    }
                    log.info("Closing connection");
                    if (_ListcabinetID.isEmpty()) {
                        log.warning("CSV data not found in database");
                    }
                    if (emps.isClosed() == false) {
                        //closing connections and readers if opened.
                        emps.close();
                        conn.close();
                    }
                } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException ex) {
                    log.error("there's an exception" + ex.toString());
                }
            }

            //compare the CSV with the database entries to get the delta.
            if ("".equals(ds) || splitter == "") {
                log.error("No file path defined or a splitter");
                log.info("Stopped");
                return;
            } 
            
            else if("1".equals(checkServices))
            {
                log.function("Check Cabinet Services");
                String _queryCheckMSAN_service;
               //todo:list object
                //get all services for msan ID 
                
                getServices(_ListcabinetID);
                //check MSANs services in database
                log.info("Getting MSANs ID");
                if(_ListcabinetID.size() >0 )
                {
                for (int i = 0; i < _ListcabinetID.size(); i++) {
                    //query
                   _queryCheckMSAN_service ="select * from adsl_cabinet_service where cabinet_id = '"+_ListcabinetID.get(i)+"' ";                    
                    
                    
                }
                }
             
            }
            
            
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
                _ListCSVcabinet.removeAll(_ListcabinetName);
if (            _ListCSVcabinet.isEmpty()) {
                    log.info("No changes on MSANs table");
                }
else {
    log.exec("Updating Table.ADSL_CABINETS");
                for (int indexOfMSAN = 0; indexOfMSAN < _ListCSVcabinet.size(); indexOfMSAN++) {
//            5.insert MSANs into adsl_cabinets table.

                    insertCabinets(_ListCSVcabinet.get(indexOfMSAN));

                }
                
                

            }
log.info("Process completed");
log.info("Shutting down and closing connections");
            }
        
    }

    public void insertCabinets(String msanName) throws SQLException, ClassNotFoundException {
    try {
        log.function("Inserting MSANs");
        OracleDataSource ods = new OracleDataSource();
        log.database("connected to Oracle database :ADSL PRO PRD");
        try {
            log.info("Table.ADSL_CABINETS");
        } catch (IllegalAccessException ex) {
            Logger.getLogger(updateMSAN.class.getName()).log(Level.SEVERE, null, ex);
        }
        ods.setURL("jdbc:oracle:thin:ADSLPRO_APP/ADSLPRO_APP_123@10.230.91.213:1527:DPROPRD1");
        try (Connection conn = ods.getConnection()) {
            Statement stat = conn.createStatement();
            String _queryAddMSAN ="insert into adsl_cabinets (cabinet_id,cabinet_name,arabic_name,total_number_of_ports,site_id,is_deleted,BRAS_dynamic,protocol_id,msan_id) values ((select (max(cabinet_id)+1) from adsl_cabinets),'" + msanName.trim() + "','" + msanName.trim() + "',400,246062,'N','Y',6,'" + msanName.trim() + "')";
            stat.executeQuery(_queryAddMSAN);
            log.exec("MSAN Inserted");
            log.database("Connection Closed");
        }
    } catch (InstantiationException ex) {
        Logger.getLogger(updateMSAN.class.getName()).log(Level.SEVERE, null, ex);
    }
            
    
       
    }

    public void insertCabinetsServices(String cabinetName) {

    }
    public void readServices(String ds, String splitter) throws FileNotFoundException, IOException, SQLException, ClassNotFoundException, InstantiationException
    {
    try {
        log.function("Inserting MSANs");
    } catch (ClassNotFoundException | InstantiationException ex) {
        Logger.getLogger(updateMSAN.class.getName()).log(Level.SEVERE, null, ex);
    }
 OracleDataSource ods = new OracleDataSource();
            ods.setURL("jdbc:oracle:thin:ADSLPRO_APP/ADSLPRO_APP_123@10.230.91.213:1527:DPROPRD1");
            Connection conn = ods.getConnection();
    try {
        log.database("connected to Oracle database :ADSL PRO PRD");
          log.info("Table.ADSL_CABINET_SERVICE");
    } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
        Logger.getLogger(updateMSAN.class.getName()).log(Level.SEVERE, null, ex);
    }
                   
          
          
 br = new BufferedReader(new FileReader(ds));
                while ((line = br.readLine()) != null) {
                    // 2. split and list data.                
                    String data[] = line.split(splitter);
//             3.List the MSANs
        for (String data1 : data) {
//                     log.print(data[i].replace("'", ""));
String[] ser = data1.split(",");
try {
    log.function("Updating Table.ADSL_CABINET_SERVICE");
} catch (ClassNotFoundException | InstantiationException ex) {
    Logger.getLogger(updateMSAN.class.getName()).log(Level.SEVERE, null, ex);
}
Statement stat = conn.createStatement();
String _queryCheckMSAN_Service= "select * from adsl_cabinet_service where cabinet_id = ((select cabinet_id from adsl_cabinets where cabinet_name="+ser[0]+"))";
ResultSet rs = stat.executeQuery(_queryCheckMSAN_Service);
if(rs.next() ==false)
{
    try {
        
        //
        log.exec("MSAN Inserted");
        String _queryAddMSAN_Service = "insert into adsl_cabinet_service (cabinet_id,service_id,svlan,cabinet_slot,bras_id) values ((select cabinet_id from adsl_cabinets where cabinet_name="+ser[0]+"),'1',"+ser[1]+","+ser[2]+",(select id from adsl_bras where bras_name ="+ser[3].replace(";","")+"))";
        stat.executeQuery(_queryAddMSAN_Service);
        
        //
    } catch (InstantiationException ex) {
        Logger.getLogger(updateMSAN.class.getName()).log(Level.SEVERE, null, ex);
    }
    
    try {
        log.database("Connection Closed");
    } catch (ClassNotFoundException ex) {
        Logger.getLogger(updateMSAN.class.getName()).log(Level.SEVERE, null, ex);
    } catch (InstantiationException ex) {
        Logger.getLogger(updateMSAN.class.getName()).log(Level.SEVERE, null, ex);
    }
    
    
}
else
{
    try {
        log.warning("MSAN Already exists");
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
    }

}
