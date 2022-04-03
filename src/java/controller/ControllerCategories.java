package controller;

import entity.Categories;
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
import model.DAOCategories;

/**
 *
 * @author MT Bac Ninh
 */
@WebServlet(name = "ControllerCategories", urlPatterns = {"/ControllerCategories"})
public class ControllerCategories extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            DAOCategories dao = new DAOCategories();
            String services = request.getParameter("do");
            //Get Date
            if (services == null) {
                services = "listAllCategories";
            }
            if (services.equals("InsertCategories")) {
                String cateName = request.getParameter("ctName");
                String des = request.getParameter("description");
                String picture = request.getParameter("picture");
                //Check/Validate
                if (cateName == null || cateName.equals("")) {
                    out.print("CategoryName is not empty");
                }
                if (cateName.length() > 15) {
                    out.print("<h3 style='color:red'>CategoryName must <= 15 char </h3>");
                    return;
                }
                //Convert

                //Execute
                Categories cate = new Categories(cateName, des, picture);
                int n = dao.addCategories(cate);
                if (n > 0) {
                    out.print("<h3 style='color:red'> Inserted Category </h3>");
                }
            }
            if (services.equals("DeleteCategories")) {

            }
            if (services.equals("listAllCategories")) {
                Vector<Categories> vector = dao.listAllCategories("select * from Categories");
                
                String titlePage="Product Manager";
                String titleTable="List of11 Products";
                //set data for request
                request.setAttribute("list", vector);
                 request.setAttribute("titlepage", titlePage);
                
                  request.setAttribute("titletable", titleTable);
                  //select jsp
                  RequestDispatcher dispath =
                          request.getRequestDispatcher("/JSP/listCategories.jsp");
                          dispath.forward(request, response);
//                out.print("<table border=\"1\">\n"
//                        + "            <thead>\n"
//                        + "                <tr>\n"
//                        + "                    <th>CategoryID</th>\n"
//                        + "                    <th>CategoryName</th>\n"
//                        + "                    <th>Description</th>\n"
////                        + "                    <th>Picture</th>\n"
//                        + "                    <th></th>\n"
//                        + "                    <th></th>\n"
//                        + "                </tr>\n"
//                        + "            </thead>\n"
//                        + "            <tbody>");
//                for (Categories cate : vector) {
//                    out.print("<tr>\n"
//                            + "                    <td>" + cate.getCategoryID() + "</td>\n"
//                            + "                    <td>" + cate.getCategoryName() + "</td>\n"
//                            + "                    <td>" + cate.getDescription() + "</td>\n"
////                            + "                    <td>" + cate.getPicture() + "</td>\n"
//                            + "                    <td><a href=\"ControllerCategories?do=UpdateCategories&pid="+cate.getCategoryID()+"\" >Update</a></td>\n"
//                            + "                    <td><a href=\"ControllerCategories?do=DeleteCategories&pid="+cate.getCategoryID()+"\" >Delete</a></td>\n"
//                            + "                </tr>");
//                }
//                out.print(" </tbody>\n"
//                        + "      </table>");

            }
            if (services.equals("UpdateCategories")) {
                String submit= request.getParameter("submit");
                if(submit==null){
                int cateid=Integer.parseInt(request.getParameter("pid"));
                ResultSet rs=dao.getData("select * from Categories where CategoryID="+cateid);
                request.setAttribute("rs", rs);
                RequestDispatcher dispath =
                          request.getRequestDispatcher("/JSP/updateCategories.jsp");
                          dispath.forward(request, response);
                }else{
                    String cateid = request.getParameter("cid");
                    String cateName = request.getParameter("cpName");
                    String des = request.getParameter("description");
//                    String picture = request.getParameter("picture");
                // có thể sai tên
                int id= Integer.parseInt(request.getParameter("cid"));
                Categories cate = new Categories(id, cateName, des);
                int n = dao.updateCategory(cate);
                response.sendRedirect("ControllerCategories");
                }
            }
            if (services.equals("DeleteCategories")) {
                int id= Integer.parseInt(request.getParameter("pid"));
                dao.deleCategory(id);
                response.sendRedirect("ControllerCategories");
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
