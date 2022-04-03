package controller;

import entity.Employee1;
import entity.Employees;
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
import javax.servlet.http.HttpSession;
import model.DAOEmployees;

/**
 *
 * @author MT Bac Ninh
 */
@WebServlet(name = "ControllerEmployee", urlPatterns = {"/ControllerEmployee"})
public class ControllerEmployee extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            DAOEmployees dao = new DAOEmployees();
            //GetData
            String service = request.getParameter("do");
            if (service == null) {
                service="listAllEmployee";
            }
            if (service.equals("insertEmloyee")) {
                String submit = request.getParameter("submit");
                if (submit == null) {
                    request.getRequestDispatcher("/view/AdminInsertEmployee.jsp").forward(request, response);
                }else{
                String lName = request.getParameter("lname");
                String fName = request.getParameter("fname");
                String title = request.getParameter("title");
                String titleOC = request.getParameter("titleOC");
                String bDate = request.getParameter("bDate");
                String hDate = request.getParameter("hDate");
                String address = request.getParameter("address");
                String city = request.getParameter("city");
                String region = request.getParameter("region");
                String psCode = request.getParameter("psCode");
                String country = request.getParameter("country");
                String hPhone = request.getParameter("hPhone");
                String extention = request.getParameter("extenstion");
                String photo = request.getParameter("photo");
                String note = request.getParameter("note");
                String rTo = request.getParameter("rTo");
                String pPath = request.getParameter("pPath");
                String user = request.getParameter("user");
                String password = request.getParameter("password");
                //Check
                //Convert
                int ReportsTo = Integer.parseInt(rTo);
                //Display
                Employees emp = new Employees(1, lName, fName, title, titleOC, hDate, hDate, address, city, region, psCode, country, hPhone, extention, photo, note, ReportsTo, pPath, user, pPath);
                out.print(emp);
                int n = dao.addEmployees(emp);
                if (n > 0) {
                    out.print("<h3 style='color:red'> Inserted Employee </h3>");
                    response.sendRedirect("ControllerEmployee");
                }else  out.print("<h3 style='color:red'> Not </h3>");
                }
            }
            if (service.equals("listAllEmployee")) {
                Vector<Employees> vector = dao.listAllEmployees("select * from Employees");
                String titlePage = "Employees Manager";
                String titleTable = "List of Employees";
                //set data for request
                request.setAttribute("list", vector);
                //select jsp
                RequestDispatcher dispath
                        = request.getRequestDispatcher("/view/AdminEmployee.jsp");
                dispath.forward(request, response);
            }
            if (service.equals("updateEmployee")) {
                String submit = request.getParameter("submit");
                if (submit == null) {
                    int eid = Integer.parseInt(request.getParameter("pid"));
                    ResultSet rs = dao.getData("select * from Employees where EmployeeID=" + eid);
                    request.setAttribute("rs", rs);
                    RequestDispatcher dispath
                            = request.getRequestDispatcher("/view/AdminUpEmploee.jsp");
                    dispath.forward(request, response);
                } else {
                    String id = request.getParameter("eid");
                    String lName = request.getParameter("lname");
                    String fName = request.getParameter("fname");
                    String title = request.getParameter("title");
                    String titleOC = request.getParameter("titleOC");
                    String bDate = request.getParameter("bDate");
                    String hDate = request.getParameter("hDate");
                    String address = request.getParameter("address");
                    String city = request.getParameter("city");
                    String region = request.getParameter("region");
                    String psCode = request.getParameter("psCode");
                    String country = request.getParameter("country");
                    String hPhone = request.getParameter("hPhone");
                    String extention = request.getParameter("extenstion");
                    String photo = request.getParameter("photo");
                    String note = request.getParameter("note");
                    String rTo = request.getParameter("rTo");
                    String pPath = request.getParameter("pPath");
                    String User = request.getParameter("User");
                    String Password = request.getParameter("Password");
                    int ReportsTo = Integer.parseInt(rTo);
                    int id1 = Integer.parseInt(id);
                    //Display
                    Employees emp = new Employees(id1, lName, fName, title, titleOC,
                            bDate, hDate, address, city, region, psCode, country,
                            hPhone, extention, photo, note, ReportsTo, pPath,User,Password);
                    int n = dao.updateEmployees(emp);
                    response.sendRedirect("ControllerEmployee");
                }
            }
            if (service.equals("deleteEmloyee")) {
                int id = Integer.parseInt(request.getParameter("pid"));
                dao.deleEmployee(id);
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
