package controller;

import entity.Account;
import entity.Customers;
import entity.OrderDetails;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.util.Enumeration;
import java.util.Vector;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.DAOCustomers;
import model.DAOOderlist;
import model.DAOOrderDetails;

/**
 *
 * @author MT Bac Ninh
 */
@WebServlet(name = "ControllerCustomer", urlPatterns = {"/ControllerCustomer"})
public class ControllerCustomer extends HttpServlet {

//    private String CustomerID, CompanyName, ContactName, 
//            ContactTitle, Address, City, Region, 
//            PostalCode, Country, Phone, Fax;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            DAOCustomers dao = new DAOCustomers();
            //Get Data
            String service = request.getParameter("do");
            if (service == null) {
                service = "listAllCustomer";
            }
            if (service.equals("InsertCustomer")) {
                String cID = request.getParameter("cId");
                String cpName = request.getParameter("cpName");
                String ctName = request.getParameter("ctName");
                String ctTitle = request.getParameter("contacTitle");
                String address = request.getParameter("address");
                String city = request.getParameter("city");
                String region = request.getParameter("region");
                String pscode = request.getParameter("pscode");
                String country = request.getParameter("country");
                String phone = request.getParameter("phone");
                String fax = request.getParameter("fax");
                //Check//Validate
                if (cID == null || cID.isEmpty()) {
                    out.print("<h3 style='color:red'>CustomerID cannot Null </h3>");
                    return;
                }
                if (cpName == null || cpName.equals("")) {
                    out.print("<h3 style='color:red'>CompanyName cannot Null </h3>");
                    return;
                }
                if (cID.length() > 5) {
                    out.print("<h3 style='color:red'>CustomerID must <= 5 char </h3>");
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
                if (pscode.length() > 10) {
                    out.print("<h3 style='color:red'>PostalCode must <= 10 char </h3>");
                    return;
                }
                if (country.length() > 1) {
                    out.print("<h3 style='color:red'>Country must <= 15 char </h3>");
                    return;
                }
                if (phone.length() > 2) {
                    out.print("<h3 style='color:red'>Phone must <= 24 char </h3>");
                    return;
                }
                if (fax.length() > 2) {
                    out.print("<h3 style='color:red'>Fax must <= 24 char </h3>");
                    return;
                }
                String useName = request.getParameter("useName");
                String password = request.getParameter("password");
                //Convert

                //Execute
                Customers cus = new Customers(cID, cpName, ctName, ctTitle, address, city, region, pscode, country, phone, fax, useName, password);
                int n = dao.addCustomers(cus);
                if (n > 0) {
                    out.print("<h3 style='color:red'> Inserted Customer </h3>");
                }
            }
            if (service.equals("listAllCustomer")) {
                Vector<Customers> vector = dao.listAllCustommer("select * from Customers");
                
                String titlePage = "Customer Manager";
                String titleTable = "List of11 Customer";
                //set data for request
                request.setAttribute("list", vector);
                request.setAttribute("titlepage", titlePage);
                
                request.setAttribute("titletable", titleTable);
                //select jsp
                RequestDispatcher dispath
                        = request.getRequestDispatcher("/view/AdminCustom.jsp");
                dispath.forward(request, response);
            }
            if (service.equals("DeleteCustomer")) {
                String id = request.getParameter("pid");
                dao.deleCustomer(id);
            }
            if (service.equals("UpdateCustomer")) {
                String submit = request.getParameter("submit");
                if (submit == null) {
                    String cusID = request.getParameter("pid");
                    ResultSet rs = dao.getData("select * from Customers  where CustomerID='" + cusID + "'");
                    request.setAttribute("rs", rs);
                    RequestDispatcher dispath
                            = request.getRequestDispatcher("/view/AdminUpCustom.jsp");
                    dispath.forward(request, response);
                } else {
                    String cID = request.getParameter("cId");
                    String cpName = request.getParameter("cpName");
                    String ctName = request.getParameter("ctName");
                    String ctTitle = request.getParameter("contacTitle");
                    String address = request.getParameter("address");
                    String city = request.getParameter("city");
                    String region = request.getParameter("region");
                    String pscode = request.getParameter("pscode");
                    String country = request.getParameter("country");
                    String phone = request.getParameter("phone");
                    String fax = request.getParameter("fax");
                    String useName = request.getParameter("User");
                    String password = request.getParameter("PassWord");
                    Customers cus = new Customers(cID, cpName, ctName, ctTitle, address, city, region, pscode, country, phone, fax, useName, password);
                    System.out.println(cus);
                    int n = dao.updateCustomers(cus);
                    response.sendRedirect("ControllerCustomer?do=listAllCustomer");
                }
            }
            if (service.equals("pdateustomer")) {
                String submit = request.getParameter("submit");
                if (submit == null) {
                   RequestDispatcher dispath
                            = request.getRequestDispatcher("/view/updateProfile.jsp");
                    dispath.forward(request, response);
                } else {
                    DAOCustomers daoCus = new DAOCustomers();
                    HttpSession session = request.getSession();
                    Enumeration em = session.getAttributeNames();
                    Account acc = (Account) session.getAttribute("Username");
                    String cusID = daoCus.getId(acc.getUser());
                    String cpName = request.getParameter("cpName");
                    String ctName = request.getParameter("ctName");
                    String ctTitle = request.getParameter("contacTitle");
                    String address = request.getParameter("address");
                    String city = request.getParameter("city");
                    String region = request.getParameter("region");
                    String pscode = request.getParameter("pscode");
                    String country = request.getParameter("country");
                    String phone = request.getParameter("phone");
                    String fax = request.getParameter("fax");
                    Customers cus = new Customers(cusID, cpName, ctName, ctTitle, address, city, region, pscode, country, phone, fax, acc.getUser(), acc.getPass());
                    System.out.println(cus);
                    int n = dao.updateCustomers(cus);
                    response.sendRedirect("ControllerProduct");
                }
                
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
