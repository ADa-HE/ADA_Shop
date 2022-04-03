/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Oderlist;
import entity.OrderDetails;
import entity.Products;
import entity.Status;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.util.Vector;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.DAOOderlist;
import model.DAOOrderDetails;
import model.DAOProducts;

/**
 *
 * @author ADA
 */
@WebServlet(name = "ControllAdmin", urlPatterns = {"/ControllAdmin"})
public class ControllAdmin extends HttpServlet {

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
            String service = request.getParameter("do");
            DAOOrderDetails dapodi = new DAOOrderDetails();
            DAOOderlist daoode = new DAOOderlist();
            DAOProducts daopro = new DAOProducts();
            
            if (service.equals("Order")) {
                String submit = request.getParameter("submit");
                String filter = request.getParameter("filter");
                Vector<Status> vectostatus = dapodi.listStatus();
                Vector<Status> vectostatuswait = dapodi.listStatusWait();
                Vector<Oderlist> vectordet = daoode.joinOrderDetail();
                Vector<OrderDetails> vectortal = dapodi.listAllOrderDetail();
                if (submit != null) {
                    String searchName = request.getParameter("searchName");
                    vectordet = daoode.joinOrderDetailName(searchName);
                }
                if (filter != null&&!filter.equals("all")) {
                    int id = Integer.parseInt(filter);
                    vectordet = daoode.joinOrderDetailID(id);
                    request.setAttribute("tempidTus", id);
                }   
                
                request.setAttribute("vectordet", vectordet);
                request.setAttribute("vectostatus", vectostatus);
                request.setAttribute("vectostatuswait", vectostatuswait);
                request.setAttribute("vectortal", vectortal);
                request.getRequestDispatcher("/view/AdminOrder.jsp").forward(request, response);
            }
            if (service.equals("OrderDetail")) {
                //AdminOrdTail
                int odeID = Integer.parseInt(request.getParameter("id"));
                ResultSet rs1 = dapodi.getData("select o.OrderID, cus.ContactName, o.OrderDate, emp.LastName +' '+emp.FirstName, o.ShippedDate,o.status from [Order Details] od\n"
                        + "                                               join Orders o on o.OrderID = od.OrderID\n"
                        + "                                              join Products pro on od.ProductID = pro.ProductID\n"
                        + "                                                join Suppliers sup on pro.SupplierID = sup.SupplierID\n"
                        + "                                                join Categories cate on pro.CategoryID = cate.CategoryID\n"
                        + "                                               join Customers cus on o.CustomerID = cus.CustomerID\n"
                        + "                                               join Employees emp on o.EmployeeID = emp.EmployeeID\n"
                        + "                                             join Shippers ship on o.ShipVia = ship.ShipperID where o.OrderID=" + odeID);
                ResultSet rs2 = dapodi.getData("select pro.ProductID,pro.ProductName,od.Quantity ,pro.UnitPrice, pro.UnitPrice*od.Quantity as tong from [Order Details] od\n"
                        + "                                                                          join Orders o on o.OrderID = od.OrderID\n"
                        + "                                                                        join Products pro on od.ProductID = pro.ProductID\n"
                        + "                                                                           join Suppliers sup on pro.SupplierID = sup.SupplierID\n"
                        + "                                                                          join Categories cate on pro.CategoryID = cate.CategoryID\n"
                        + "                                                                        join Customers cus on o.CustomerID = cus.CustomerID\n"
                        + "                                                                      join Employees emp on o.EmployeeID = emp.EmployeeID\n"
                        + "                                                                      join Shippers ship on o.ShipVia = ship.ShipperID where o.OrderID=" + odeID);

                Vector<Status> vector1 = dapodi.listStatus();
                request.setAttribute("vectorstatus", vector1);
                request.setAttribute("rssear", rs1);
                request.setAttribute("rs2product", rs2);
                request.getRequestDispatcher("/view/AdminOrdTail.jsp").forward(request, response);

            }
            if (service.equals("updateOrdst")) {
                //AdminOrdTail
                int odeID = Integer.parseInt(request.getParameter("ordeID"));
                int stID = Integer.parseInt(request.getParameter("statussID"));
                Vector<OrderDetails> vectortal = dapodi.listAllOrderDetail();
                Vector<Products> vectorpro = daopro.listAllProducts("select * from Products");
                Vector<Oderlist> vectordet = daoode.joinOrderDetail();
                boolean check = false;//check xem đã done chưa!
                for (Oderlist o1 : vectordet) {// 
                    if (o1.getOrderID() == odeID) {
                        if (o1.getStatus() == 3) {
                            check = true;
                        }
                    }
                }
                if (check == false) {
                    int n = daoode.updateStatus(stID, odeID);
                }
                if (stID == 3 && check == false) {//khi done và trước đó ko done -> trừ sl
                    for (OrderDetails o : vectortal) {// 
                        if (o.getOrderID() == odeID) {//lấy 
                            for (Products products : vectorpro) {
                                if (products.getProductID() == o.getProductID()) {
                                    products.setUnitsInStock(products.getUnitsInStock() - o.getQuantity());
                                }
                            }
                        }
                    }
                }
                String sent = request.getParameter("filterdeatil");//senRedirect trong orderdetail
                if (sent != null) {
                    response.sendRedirect("ControllAdmin?do=OrderDetail&id=" + odeID);
                } else {
                    response.sendRedirect("ControllAdmin?do=Order");
                }
            }
            if(service.equals("listProduct")){
                Vector<Products> vector = daopro.listAllProducts("select * from Products");
                //set data for request
                //select jsp
                request.setAttribute("list", vector);
                RequestDispatcher dispath
                        = request.getRequestDispatcher("/view/AdminProduct.jsp");
                dispath.forward(request, response);
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
