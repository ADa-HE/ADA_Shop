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
import java.util.Vector;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.DAOCustomers;
import model.DAOOrderDetails;
import model.DAOOrders;
import model.DAOProducts;
import model.DAOlogin;

/**
 *
 * @author ADA
 */
@WebServlet(name = "LoginControl", urlPatterns = {"/LoginControl"})
public class LoginControl extends HttpServlet {

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
            HttpSession session = request.getSession();
            Enumeration em = session.getAttributeNames();
            String user = request.getParameter("username");
            String pass = request.getParameter("userpassword");
            DAOlogin dao = new DAOlogin();
            DAOOrderDetails daodetai = new DAOOrderDetails();
            DAOCustomers daocus = new DAOCustomers();
            DAOProducts daoPro = new DAOProducts();
            Account acc = (Account) session.getAttribute("Username");
            String temp = "";
            if (service == null) {
                // lấy account nếu có trong session thì chuyển DAO
                //                 không có chuyển vào login
                // Login: kiểm tra user pass có trong database?
                //roll:0 1
                if (acc == null) {// chưa có acc đã login chưa
                    out.print("acc null");
                    request.getRequestDispatcher("/view/login.jsp").forward(request, response);
                } else { // có acc 
                    String notice = "Giỏ hàng còn trống<> thêm hàng vào giỏ nào!";
                    Vector<ItemProductCart> listProductCarts = new Vector<>();
                    ItemProductCart pro = new ItemProductCart();
                    while (em.hasMoreElements()) {
                        String key = em.nextElement().toString();
                        if (!key.equals("Username") && !key.equals("sizeProCart")) {
                            pro = (ItemProductCart) session.getAttribute(key);
                            listProductCarts.addElement(pro);
                        }
                    }
                    if (pro.getProduct() == null) {
                        request.setAttribute("notice", notice);
                    }
                    Customers custom = daocus.userPassCustomers(acc.getUser(), acc.getPass());
                    out.print(custom);
                    request.setAttribute("cust", custom);
                    request.setAttribute("listProCart", listProductCarts);
                    request.getRequestDispatcher("/view/checkout.jsp").forward(request, response);
                }
            }
            if (service.equals("checkaccount")) {
                boolean test = false;
                Vector<Account> vector = dao.listAllAccount();
                //check tài khoản có trong db k?roll gì?
                for (Account account : vector) {
                    if (account.getUser().equals(user) && account.getPass().equals(pass)) {
                        acc = new Account(user, pass, account.getRoll());
                        test = true;
                    }
                }

                if (test == false) {//error login
                    String error = "Account not exsist or wrong password or password";
                    request.setAttribute("error", error);
                    out.print(error);
                    RequestDispatcher dispath
                            = request.getRequestDispatcher("/view/login.jsp");
                    dispath.forward(request, response);
                } else {
                    String re = request.getParameter("rem");
                    Cookie cu = new Cookie("us", acc.getUser());
                    Cookie pa = new Cookie("pa", acc.getPass());
                    Cookie rem = new Cookie("rem", re);
                    System.out.println(re);
                    System.out.println(acc);
                    if (re == null) {
                        cu.setMaxAge(0);
                        pa.setMaxAge(0);
                        rem.setMaxAge(0);
                    } else {
                        cu.setMaxAge(60 * 60 * 24);
                        pa.setMaxAge(60 * 60 * 24);
                        rem.setMaxAge(60 * 60 * 24);
                    }
                    response.addCookie(cu);
                    response.addCookie(pa);
                    response.addCookie(rem);
                    if (acc.getRoll() == 0) {

                        session.setAttribute("Username", new Account(acc.getUser(), acc.getPass(), acc.getRoll()));

                        response.sendRedirect("SessionCart?do=showcart");

                    } else {
//                            out.print(acc);
                        session.setAttribute("UsernameAdmin", new Account(acc.getUser(), acc.getPass(), acc.getRoll()));

                        request.getRequestDispatcher("/view/Admin.jsp").forward(request, response);
                    }
                }
            }
            if (service.equals("logout")) {
                session.invalidate();
                response.sendRedirect("LoginControl");
            }
            if (service.equals("register")) {
                boolean check = true;
                String noti = "Lag quá sigup lại nào!";
                String noti1 = "Account already exists";
                String noti2 = "The password is not the same";

                Vector<Account> vector = dao.listAllAccount();
                String user1 = request.getParameter("user");
                String pass1 = request.getParameter("password");
                String repass1 = request.getParameter("repassword");
                for (Account account : vector) {
                    if (account.getUser().equals(user1)) {
                        check = false;
                        out.print(check);
                    }

                }
                if (user1.equals("") || pass1.equals("") || repass1.equals("")) {
                    request.setAttribute("noti", noti);
                    request.getRequestDispatcher("/view/login.jsp").forward(request, response);
                } else if (check == false) {
                    request.setAttribute("notiUser", noti1);
                    request.getRequestDispatcher("/view/login.jsp").forward(request, response);
                } else if (!pass1.equals(repass1)) {
                    request.setAttribute("notiPass", noti2);
                    request.getRequestDispatcher("/view/login.jsp").forward(request, response);
                } else {//dk thành công
                    System.out.println("OK");

                    temp = daocus.generateCaptcha();
                    daocus.addUserPass(new Customers(temp, user1, pass1));
                    acc = new Account(user1, pass1, 0);
                    System.out.println(acc);
                    session.setAttribute("Username", acc);
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
