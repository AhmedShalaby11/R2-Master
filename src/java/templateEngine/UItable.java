/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package templateEngine;

import businessLogic.syncMSAN;
import com.mysql.fabric.Response;
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
public class UItable extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    syncMSAN s = new syncMSAN();
    List<String> _listColName = new ArrayList<String>();
    List<String> _listTableDesign = new ArrayList<String>();
     ArrayList<String> _listTableRow = new ArrayList<String>();

    String _valueTableHead;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            clearList();
  _listTableRow=(ArrayList<String>) request.getAttribute("list"); 

            _listColName.add("Process");
            _listColName.add("Category");
            _listColName.add("Status");

            addTable();

            for (int i = 0; i < _listTableDesign.size(); i++) {
                out.print(_listTableDesign.get(i));
            }

            out.flush();

        }
    }

    public void addTable() {
//        _listTableRow =rowData._ListRowData;
        _listTableDesign.add("<table class='w3-table-all'><thead ><tr class='w3-red'>");
        for (int i = 0; i < _listColName.size(); i++) {
            _listTableDesign.add("<th>" + _listColName.get(i) + "</th>");

        }
        _listTableDesign.add("</tr></thead><tbody>");
        for (int i = 0; i < _listTableRow.size(); i++) {
            _listTableDesign.add("<tr>" + _listTableRow.get(i) + "</tr>");
        }
        _listTableDesign.add("</tbody></table>");
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

    public void clearList() {
        _listColName.clear();
        _listTableDesign.clear();
//        _listTableRow.clear();
        
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
