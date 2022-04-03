/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Categories;
import entity.Oderlist;
import entity.Status;
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
import model.DAOOderlist;

/**
 *
 * @author ADA
 */
@WebServlet(name = "OderlistControl", urlPatterns = {"/OderlistControl"})
public class OderlistControl extends HttpServlet {

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
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            DAOOderlist dao = new DAOOderlist();
            //Lấy service
            String service = request.getParameter("do");
            if (service == null) {
                service = "OrderList";
            }
            if (service.equals("OrderList")) {
                //List
                Vector<Oderlist> vector = dao.joinOrderDetail();
                Vector<Status> vector1 = dao.listStatus("select * from Status");
                //set data for request
                request.setAttribute("list", vector);
                request.setAttribute("vectorstatus", vector1);

                RequestDispatcher dispath
                        = request.getRequestDispatcher("/JSP/listOrders1.jsp");
                dispath.forward(request, response);
            }
            if (service.equals("orderDetail")) {
                //Test
                int pid = Integer.parseInt(request.getParameter("orID"));
                ResultSet rs1 = dao.getData("select o.OrderID, cus.ContactName, o.OrderDate, emp.LastName +' '+emp.FirstName, o.ShippedDate,o.status from [Order Details] od\n"
                        + "                                               join Orders o on o.OrderID = od.OrderID\n"
                        + "                                              join Products pro on od.ProductID = pro.ProductID\n"
                        + "                                                join Suppliers sup on pro.SupplierID = sup.SupplierID\n"
                        + "                                                join Categories cate on pro.CategoryID = cate.CategoryID\n"
                        + "                                               join Customers cus on o.CustomerID = cus.CustomerID\n"
                        + "                                               join Employees emp on o.EmployeeID = emp.EmployeeID\n"
                        + "                                             join Shippers ship on o.ShipVia = ship.ShipperID where o.OrderID=" + pid);
                ResultSet rs2 = dao.getData("select pro.ProductID,pro.ProductName,od.Quantity ,pro.UnitPrice, pro.UnitPrice*od.Quantity as tong from [Order Details] od\n"
                        + "                                                                          join Orders o on o.OrderID = od.OrderID\n"
                        + "                                                                        join Products pro on od.ProductID = pro.ProductID\n"
                        + "                                                                           join Suppliers sup on pro.SupplierID = sup.SupplierID\n"
                        + "                                                                          join Categories cate on pro.CategoryID = cate.CategoryID\n"
                        + "                                                                        join Customers cus on o.CustomerID = cus.CustomerID\n"
                        + "                                                                      join Employees emp on o.EmployeeID = emp.EmployeeID\n"
                        + "                                                                      join Shippers ship on o.ShipVia = ship.ShipperID where o.OrderID="+ pid);

                Vector<Status> vector1 = dao.listStatus("select * from Status");
                request.setAttribute("vectorstatus", vector1);
                request.setAttribute("rssear", rs1);
                request.setAttribute("rs2product", rs2);
                //ResultSet rs1 = dao.getData("");
                RequestDispatcher dispath
                        = request.getRequestDispatcher("/JSP/OderlistControl.jsp");
                dispath.forward(request, response);

            }
            if (service.equals("updateStatus")) {
                int oder = Integer.parseInt(request.getParameter("OderIDupdate"));
                int status = Integer.parseInt(request.getParameter("StatusId"));
                int n = dao.updateStatus(status, oder);

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
