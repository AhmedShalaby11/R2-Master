package templateEngine;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author AhmedShalaby
 */
public class chartOpenedClosed extends HttpServlet {
 Connection conn =null;
   List<String> _valuesList = new ArrayList<String>();
        List<String> _categoryList = new ArrayList<String>();
        List<String> _messageList = new ArrayList<String>();
         String query,chart,title;
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
      
       
      
        query = request.getParameter("query");
        chart = request.getParameter("chart");
        title = request.getParameter("title");
        
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
                    Class.forName("com.mysql.jdbc.Driver").newInstance();   
           request.getParameter("_queryChart");
  Class.forName("com.mysql.jdbc.Driver").newInstance();   
           
    java.sql.Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/integration?" +
                                   "user=root&password=ahmedtato1&useSSL=false");

//    info("connected to mySQL");
    Statement stmt = null ;    
    
  
       
         Statement st = conn.createStatement();
        ResultSet rs;
            rs = st.executeQuery(query);
              while(rs.next())
        {
            _valuesList.add(rs.getString(3));
            _messageList.add(rs.getString(2));
            _categoryList.add(rs.getString(1));
        }
            
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head><script src='http://canvasjs.com/assets/script/canvasjs.min.js'></script>");
            out.println("<title>Servlet chartTransactions</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<script type='text/javascript'>\n" +
" function getChart() {\n" +
"var chart = new CanvasJS.Chart('chartContainer',\n" +
"{\n" +
"animationEnabled: true,\n" +
"theme: 'theme1',\n" +
"//exportEnabled: true,\n" +
"title:{\n" +
"text: '"+title.trim()+"'\n" +
"},\n" +
"data: [\n" +
"{\n" +
"type: '"+chart.trim()+"', //change type to bar, line, area, pie, etc\n" +
"dataPoints: [\n" );
            for (int i = 0; i < _categoryList.size(); i++) {
                out.println("{   y: "+_valuesList.get(i)+" ,label: '"+_messageList.get(i)+"' },");
            }
          
out.print("]\n" +
"}\n" +
"]\n" +
"});\n" +
"\n" +
"    if($(\"#chartContainer\").length ==1 )\n" +
"                                 {\n" +
"                                     if (chart.length ==1 )\n" +
"{\n" +
"    chart.clear();"
        + "chart.destroy()\n" +
"    chart.render();\n" +
"}\n" +
"else\n" +
"{\n" +
"	chart.render();\n" +
"    }    " +
"                                 }" +
"}\n" +
"</script>");
           
            out.println(" <div id='chartContainer' class='w3-animate-zoom container' style='height: 350px;margin-left:30%;'></div>");
            out.println("</body>");
            out.println("</html>");
            conn.close();
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
            Logger.getLogger(chartTransactions.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(chartTransactions.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(chartTransactions.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(chartTransactions.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(chartTransactions.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(chartTransactions.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(chartTransactions.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(chartTransactions.class.getName()).log(Level.SEVERE, null, ex);
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
