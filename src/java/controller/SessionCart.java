/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Account;
import entity.Customers;
import entity.ItemProductCart;
import entity.OrderDetails;
import entity.Orders;
import entity.Products;
import entity.Status;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.util.Enumeration;
import java.util.Map;
import java.util.Vector;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.smartcardio.Card;
import model.DAOCustomers;
import model.DAOOrderDetails;
import model.DAOOrders;
import model.DAOProducts;

/**
 *
 * @author ADA
 */
@WebServlet(name = "SessionCart", urlPatterns = {"/SessionCart"})
public class SessionCart extends HttpServlet {

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
            DAOProducts dao = new DAOProducts();
            HttpSession session = request.getSession();
            Enumeration em = session.getAttributeNames();
            Vector<ItemProductCart> listProductCarts = new Vector<>();
            int temp = 0;
            DAOCustomers daoCus = new DAOCustomers();
            DAOOrderDetails daodetai = new DAOOrderDetails();
            DAOOrders dao1 = new DAOOrders();
            Vector<Customers> vectorCus = daoCus.listAllCustommer("select *from Customers");

            //Lấy service
            String getcusID = "";
            if (service.equals("showcart")) {
                while (em.hasMoreElements()) {
                    String key = em.nextElement().toString();
                    if (!key.equals("Username") && !key.equals("sizeProCart")) {
                        ItemProductCart pro = (ItemProductCart) session.getAttribute(key);
                        listProductCarts.addElement(pro);
                    }
                }
//                    for (ItemProductCart listProductCart : listProductCarts) {
//                        out.print(listProductCart);
//                }
                request.setAttribute("listProCart", listProductCarts);
                request.getRequestDispatcher("/view/Cart.jsp").forward(request, response);

            }

            if (service.equals("addtoCart")) {
                String page = request.getParameter("page");
                if (page == null || page.trim().length() == 0) {
                    page = "1";
                }
                int pageIndex = Integer.parseInt(page);
                String cateid = request.getParameter("cateid");
                if (cateid != null) { //khi chọn category!
                    int careID = Integer.parseInt(cateid);

                }
                String id = request.getParameter("proid");
                int idp = Integer.parseInt(id);
                int idpage = Integer.parseInt(page);
                ItemProductCart IproCart = (ItemProductCart) session.getAttribute(id);
                Products product = dao.getProductById(idp);
//                String size =(String)request.getAttribute("sizeProCart");           
//                        //-------------
                String sizeStr;
                try {
                    sizeStr = session.getAttribute("sizeProCart").toString();
                } catch (Exception e) {
                    sizeStr = null;
                }
                int size;
                if (sizeStr == null) {
                    size = 0;
                } else {
                    size = Integer.parseInt(sizeStr);
                }
                //-------------
                //not found
                if (IproCart == null) {
                    if (product.getUnitsInStock() > 0) {
                        IproCart = new ItemProductCart(idp, product);
                        IproCart.setQuantity(1);
                        session.setAttribute(id, IproCart);
                        size++;
                        session.setAttribute("sizeProCart", size);
//                    if (size == null) {
//                        session.setAttribute("sizeProCart","1");
//                    } else {
//                        int temp1 = Integer.parseInt(size)+1;
//                        String te=""+temp1;
//                        session.setAttribute("sizeProCart", te);
//
//                    }
                    }
                } else {
                    IproCart.setQuantity(IproCart.getQuantity() + 1);
                    session.setAttribute(id, IproCart);
                }
                out.print(cateid);

                if (cateid != null) {
                    response.sendRedirect("ControllerProduct?page=" + idpage + "&cateid=" + cateid);

                } else {
                    response.sendRedirect("ControllerProduct?page=" + idpage);
                }
            }
            if (service.equals("removeItem")) {
                String id = request.getParameter("id");
                String sizeStr;
                try {
                    sizeStr = session.getAttribute("sizeProCart").toString();
                } catch (Exception e) {
                    sizeStr = null;
                }
                int size;
                if (sizeStr == null) {
                    size = 0;
                } else {
                    size = Integer.parseInt(sizeStr);
                }
                if (id != null) {
                    size--;
                    if (size < 0) {
                        size = 0;
                    }
                    session.setAttribute("sizeProCart", size);
                    session.removeAttribute(id);
                } else {
                    while (em.hasMoreElements()) {
                        String key = em.nextElement().toString();
                        if (!key.equals("Username")) {
                            session.removeAttribute(key);
                        }
                    }
                }
                response.sendRedirect("SessionCart?do=showcart");
            }
            if (service.equals("plusItem")) {
                String id = request.getParameter("id");
                ItemProductCart IproCart = (ItemProductCart) session.getAttribute(id);
                String getmethod = request.getParameter("method");

                if (getmethod.equals("minus")) {
                    while (em.hasMoreElements()) {
                        String key = em.nextElement().toString();
                        if (key.equals(id)) {
                            IproCart.setQuantity(IproCart.getQuantity() - 1);
                            if (IproCart.getQuantity() < 1) {
                                IproCart.setQuantity(1);
                            }
                            session.setAttribute(id, IproCart);
                        }
                    }
                }
                if (getmethod.equals("pluss")) {
                    while (em.hasMoreElements()) {
                        String key = em.nextElement().toString();
                        if (key.equals(id)) {
                            IproCart.setQuantity(IproCart.getQuantity() + 1);
                            if (IproCart.getQuantity() > IproCart.getProduct().getUnitsInStock()) {
                                IproCart.setQuantity(IproCart.getProduct().getUnitsInStock());
                            }
                            session.setAttribute(id, IproCart);
                        }
                    }
                }
                response.sendRedirect("SessionCart?do=showcart");
            }
            if (service.equals("checkout")) {
                //update vào datebase.....
                //.........
                Vector<OrderDetails> vecto = new Vector<>();
                String PostalCode = request.getParameter("PostalCode");
                String ShipAddress = request.getParameter("Shipaddress");
                String ShipName = request.getParameter("ShipName");
                String ShipCity = request.getParameter("Shipcity");
                String ShipCountry = request.getParameter("ShipCountry");
                Account acc = (Account) session.getAttribute("Username");
                Customers cus = daoCus.userPassCustomers(acc.getUser(), acc.getPass());
                int n = dao1.addOrderGetID(new Orders(cus.getCustomerID(), 1, ShipName, ShipAddress, ShipCity, PostalCode, ShipCountry));
                temp = n;
                System.out.println(n);
                System.out.println(new Orders(cus.getCustomerID(), 1, ShipName, ShipAddress, ShipCity, PostalCode, ShipCountry));
                if (n > 0) {
                    while (em.hasMoreElements()) {
                        String key = em.nextElement().toString();
                        if (!key.equals("Username") && !key.equals("sizeProCart")) {
                            ItemProductCart pro = (ItemProductCart) session.getAttribute(key);
                            vecto.add(new OrderDetails(n, pro.getProduct().getProductID(), pro.getProduct().getUnitPrice(), pro.getQuantity(), 0));
                        }
                    }
                    for (OrderDetails o : vecto) {
                        int k=daodetai.addOrderDetails( new OrderDetails(o.getOrderID(), o.getProductID(), o.getUnitPrice(), o.getQuantity(), 0));
                    }
                    //daodetai.addOrderDetails(new OrderDetails(n, pro.getProduct().getProductID(), pro.getProduct().getUnitPrice(), pro.getQuantity(), 0));

//                    while (em.hasMoreElements()) {
//                        String key = em.nextElement().toString();
//                        if (!key.equals("Username")) {
//                            session.removeAttribute(key);
//                        }
//                    }
                    response.sendRedirect("SessionCart?do=removeItem");
                } else {
                    response.sendRedirect("checkout.jsp");
                }
            }
            if (service.equals("listOderDetail")) {
                Account acc = (Account) session.getAttribute("Username");
                String cusID = daoCus.getId(acc.getUser());
                out.println(cusID);
//                Orders oder = new Orders();
//                DAOOrders daoord = new DAOOrders();
//                    Vector<Products> vectorPro = daoPro.listAllProducts("select* from Products");
//                    Vector<OrderDetails> vectordt = daodetai.listByOrderID(temp);
                Vector<Status> vectorsta = daodetai.listStatus();
//                    request.setAttribute("listOrdDtail", vectordt);
                request.setAttribute("vectorsta", vectorsta);
//                    request.setAttribute("vectorPro", vectorPro);
                ResultSet rs1 = dao.getData("select od.OrderID, pro.ProductName,pro.image ,od.UnitPrice,od.Quantity,od.UnitPrice*od.Quantity, o.status from [Order Details] od\n"
                        + "join Orders o on o.OrderID = od.OrderID \n"
                        + "join Products pro on od.ProductID = pro.ProductID \n"
                        + "join Suppliers sup on pro.SupplierID = sup.SupplierID \n"
                        + "join Categories cate on pro.CategoryID = cate.CategoryID \n"
                        + "join Customers cus on o.CustomerID = cus.CustomerID \n"
                        + "join Employees emp on o.EmployeeID = emp.EmployeeID\n"
                        + " where cus.CustomerID='" + cusID + "'");
                request.setAttribute("rs1", rs1);
                request.getRequestDispatcher("/view/OrderDetail.jsp").forward(request, response);
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
