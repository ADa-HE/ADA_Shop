package controller;

import entity.Suppliers;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.DAOSuppliers;

/**
 *
 * @author MT Bac Ninh
 */
@WebServlet(name = "ControllerSupplier", urlPatterns = {"/ControllerSupplier"})
public class ControllerSupplier extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            DAOSuppliers dao = new DAOSuppliers();
            String service = request.getParameter("do");
            if (service == null) {
                service = "listAllSupplier";
            }
            if (service.equals("insertSupplier")) {
                //Get Data
                String cpName = request.getParameter("cpName");
                String ctName = request.getParameter("ctName");
                String ctTitle = request.getParameter("ctTitle");
                String address = request.getParameter("address");
                String city = request.getParameter("city");
                String region = request.getParameter("region");
                String pCode = request.getParameter("psCode");
                String country = request.getParameter("country");
                String phone = request.getParameter("phone");
                String fax = request.getParameter("fax");
                String homePage = request.getParameter("hPage");
                //Check
                if (cpName == null || cpName.equals("")) {
                    out.print("<h3 style='color:red'>CompanyName is not null </h3>");
                    return;
                }
                if (cpName.length() > 40) {
                    out.print("<h3 style='color:red'>CompanyName must <= 40 char </h3>");
                    return;
                }
                if (ctName.length() > 30) {
                    out.print("<h3 style='color:red'>ContactName must <= 30 char </h3>");
                    return;
                }
                if (ctTitle.length() > 30) {
                    out.print("<h3 style='color:red'>ContactTitle must <= 30 char </h3>");
                    return;
                }
                if (address.length() > 60) {
                    out.print("<h3 style='color:red'>Address must <= 60 char </h3>");
                    return;
                }
                if (city.length() > 15) {
                    out.print("<h3 style='color:red'>City must <= 15 char </h3>");
                    return;
                }
                if (region.length() > 15) {
                    out.print("<h3 style='color:red'>Region must <= 15 char </h3>");
                    return;
                }
                if (pCode.length() > 10) {
                    out.print("<h3 style='color:red'>PostalCode must <= 10 char </h3>");
                    return;
                }
                if (country.length() > 15) {
                    out.print("<h3 style='color:red'>Country must <= 15 char </h3>");
                    return;
                }
                if (phone.length() > 24) {
                    out.print("<h3 style='color:red'>Phone must <= 24 char </h3>");
                    return;
                }
                if (fax.length() > 24) {
                    out.print("<h3 style='color:red'>Fax must <= 24 char </h3>");
                    return;
                }
                //Convert

                //Execute
                Suppliers sup = new Suppliers(cpName, ctName, ctTitle, address, city,
                        region, pCode, country, phone, fax, homePage);
                int n = dao.addSupplier(sup);
                if (n > 0) {
                    out.print("<h3 style='color:red'> Inserted Shupplier </h3>");
                }
            }
            if (service.equals("listAllSupplier")) {
                Vector<Suppliers> vector = dao.listAllSupplier("select * from Suppliers");
                String titlePage="Suppliers Manager";
                String titleTable="List of Suppliers";
                //set data for request
                request.setAttribute("list", vector);
                 request.setAttribute("titlepage", titlePage);
                
                  request.setAttribute("titletable", titleTable);
                  //select jsp
                  RequestDispatcher dispath =
                          request.getRequestDispatcher("/JSP/listSuppliers.jsp");
                          dispath.forward(request, response);
//                out.print("<table border=\"1\">\n"
//                        + "            <thead>\n"
//                        + "                <tr>\n"
//                        + "                    <th>SupplierID</th>\n"
//                        + "                    <th>CompanyName</th>\n"
//                        + "                    <th>ContactName</th>\n"
//                        + "                    <th>ContactTitle</th>\n"
//                        + "                    <th>Address</th>\n"
//                        + "                    <th>City</th>\n"
//                        + "                    <th>Region</th>\n"
//                        + "                    <th>PostalCode</th>\n"
//                        + "                    <th>Country</th>\n"
//                        + "                    <th>Phone</th>\n"
//                        + "                    <th>Fax</th>\n"
//                        + "                    <th>HomePage</th>\n"
//                        + "                    <th></th>\n"
//                        + "                    <th></th>\n"
//                        + "                </tr>\n"
//                        + "            </thead>\n"
//                        + "            <tbody>");
//                for (Suppliers sup : vector) {
//                    out.print("<tr>\n"
//                            + "                    <td>" + sup.getSupplierID() + "</td>\n"
//                            + "                    <td>" + sup.getCompanyName() + "</td>\n"
//                            + "                    <td>" + sup.getContactName() + "</td>\n"
//                            + "                    <td>" + sup.getContactTitle() + "</td>\n"
//                            + "                    <td>" + sup.getAddress() + "</td>\n"
//                            + "                    <td>" + sup.getCity() + "</td>\n"
//                            + "                    <td>" + sup.getRegion() + "</td>\n"
//                            + "                    <td>" + sup.getPostalCode() + "</td>\n"
//                            + "                    <td>" + sup.getCountry() + "</td>\n"
//                            + "                    <td>" + sup.getPhone() + "</td>\n"
//                            + "                    <td>" + sup.getFax() + "</td>\n"
//                            + "                    <td>" + sup.getHomePage() + "</td>\n"
//                            + "                    <td><a href=\"ControllerSupplier?do=updateSupplier&pid=" + sup.getSupplierID() + "\">update</a></td>\n"
//                            + "                    <td><a href=\"ControllerSupplier?do=deleteSupplier&pid=" + sup.getSupplierID() + "\">delete</a></td>\n"
//                            + "                </tr>");
//                }
//
//                out.print("</tbody>\n"
//                        + "        </table>");
            }
            if (service.equals("updateSupplier")) {
                String submit = request.getParameter("submit");
                if (submit == null) {
                     int id = Integer.parseInt(request.getParameter("pid"));
                    ResultSet rs = dao.getData("select * from Suppliers where SupplierID=" + id);
                     request.setAttribute("rs", rs);
                    RequestDispatcher dispath =
                          request.getRequestDispatcher("/JSP/updateSuppier.jsp");
                          dispath.forward(request, response);
                } else {
                    String SupplierID = request.getParameter("SupplierID");
                    String cpName = request.getParameter("cpName");
                    String ctName = request.getParameter("ctName");
                    String ctTitle = request.getParameter("ctTitle");
                    String address = request.getParameter("address");
                    String city = request.getParameter("city");
                    String region = request.getParameter("region");
                    String pCode = request.getParameter("psCode");
                    String country = request.getParameter("country");
                    String phone = request.getParameter("phone");
                    String fax = request.getParameter("fax");
                    String homePage = request.getParameter("hPage");
                    int ids = Integer.parseInt(SupplierID);
                    Suppliers sup = new Suppliers(ids, cpName, ctName, ctTitle, address, city, region, pCode, country, phone, fax, homePage);
                    int n = dao.updateSupplier(sup);
                    response.sendRedirect("ControllerSupplier");
                }
            }
            
            
            if (service.equals("deleteSupplier")) {
                int id= Integer.parseInt(request.getParameter("pid"));
                dao.deleSupplier(id);
            }
            
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
