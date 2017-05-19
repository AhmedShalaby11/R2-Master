/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package businessLogic;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author AhmedShalaby
 */
public class getDataTable extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    List<String> _listMSAN = new ArrayList<String>();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            
            
_listMSAN = ( ArrayList<String>) request.getAttribute("_ListcsvMSAN");
       
//            datatable libs
            out.print("<link rel=\"stylesheet\" href=\"https://www.w3schools.com/w3css/4/w3.css\"><script src='https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js'></script><link rel='stylesheet' type='text/css' href='https://cdn.datatables.net/v/dt/dt-1.10.13/af-2.1.3/b-1.2.4/b-colvis-1.2.4/b-html5-1.2.4/cr-1.3.2/r-2.1.0/sc-1.4.2/se-1.2.0/datatables.min.css'>"
                    + "<script type='text/javascript' src='https://cdn.datatables.net/v/dt/dt-1.10.13/af-2.1.3/b-1.2.4/b-colvis-1.2.4/b-html5-1.2.4/cr-1.3.2/r-2.1.0/sc-1.4.2/se-1.2.0/datatables.min.js'></script>");

//table header;
            out.print("<table id='_msanTables' class='w3-table w3-striped w3-border'> ");
            //table head;
            out.print("<thead><tr   class='w3-red'>"
                    + "<th style='text-align:center;'>MSAN ID</th>"
                    + "</tr></thead><tbody>");
            for (int i = 0; i < _listMSAN.size(); i++) {

                out.print("<tr><td style='text-align:center;'>" + _listMSAN.get(i) + "</td></tr>");

            }
//            table closing;
            out.print("</tbody></table>");
//            datatable Scriptor;
            out.print("<script type='text/javascript'>  $(document).ready(function(){    $('#_msanTables').dataTable();  });</script>");

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
        processRequest(request, response);
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
        processRequest(request, response);
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
