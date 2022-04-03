package controller;

import entity.Shippers;
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
import model.DAOShippers;

/**
 *
 * @author MT Bac Ninh
 */
@WebServlet(name = "ControllerShipper", urlPatterns = {"/ControllerShipper"})
public class ControllerShipper extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            DAOShippers dao = new DAOShippers();
            String service = request.getParameter("do");
            if (service == null) {
                service = "listAllShipper";
            }
            if (service.equals("insertShipper")) {
                //Get Data
                String cpname = request.getParameter("cpName");
                String phone = request.getParameter("phone");
                //Check
                if (cpname == null || cpname.isEmpty() || cpname.length() > 40) {
                    out.print("<h3 style='color:red'> CompanyName is NOT NULL && size <= 40 </h3>");
                    return;
                }
                if (phone.length() > 24) {
                    out.print("<h3 style='color:red'> Phone size <= 24 </h3>");
                    return;
                }
                //Convert

                //Execute
                Shippers ship = new Shippers(cpname, phone);
                int n = dao.addShippers(ship);
                if (n > 0) {
                    out.print("<h3 style='color:red'> Inserted Shippers </h3>");
                }
            }
            if (service.equals("listAllShipper")) {
                Vector<Shippers> vector1 = dao.listAllShipper("select * from Shippers");
                String titlePage="Shippers Manager";
                String titleTable="List of Shippers";
                //set data for request
                    request.setAttribute("listshipp", vector1);
                    request.setAttribute("titlepage", titlePage);
                    request.setAttribute("titletable", titleTable);
                  //select jsp
                  RequestDispatcher dispath =
                          request.getRequestDispatcher("/JSP/listShipper.jsp");
                          dispath.forward(request, response);
//                out.print("<table border=\"1\">\n"
//                        + "            <thead>\n"
//                        + "                <tr>\n"
//                        + "                    <th>ShipperID</th>\n"
//                        + "                    <th>CompanyName</th>\n"
//                        + "                    <th>Phone</th>\n"
//                        + "                    <th></th>\n"
//                        + "                    <th></th>\n"
//                        + "                    \n"
//                        + "                </tr>\n"
//                        + "            </thead>\n"
//                        + "            <tbody>");
//                for (Shippers shippers : vector) {
//                    out.print("<tr>\n"
//                            + "                    <td>" + shippers.getShipperID() + "</td>\n"
//                            + "                    <td>" + shippers.getCompanyName() + "</td>\n"
//                            + "                    <td>" + shippers.getPhone() + "</td>\n"
//                            + "                  <td><a href=\"ControllerShipper?do=updateShipper&pid=" + shippers.getShipperID() + "\">udpate</a></td>\n"
//                            + "                  <td><a href=\"ControllerShipper?do=deleteShipper&pid=" + shippers.getShipperID() + "\">delete</a></td>\n"
//                            + "                    \n"
//                            + "                </tr>");
//                }
//
//                out.print("</tbody>\n"
//                        + "        </table>");

            }
            if (service.equals("updateShipper")) {
                String submit= request.getParameter("submit");
                if(submit==null){
                    int id= Integer.parseInt(request.getParameter("pid"));
                    ResultSet rs = dao.getData("select * from Shippers where ShipperID=" + id);
                     request.setAttribute("rs", rs);
                RequestDispatcher dispath =
                          request.getRequestDispatcher("/JSP/updateShipper.jsp");
                          dispath.forward(request, response);
                }else{
                    String cpname = request.getParameter("cpName");
                    String phone = request.getParameter("phone");
                    int id=Integer.parseInt(request.getParameter("cpid"));
                    Shippers ship = new Shippers(id, cpname, phone);
                    dao.updateShippers(ship);
                    response.sendRedirect("ControllerShipper");
            }
            if (service.equals("deleteShipper")) {
                 int id= Integer.parseInt(request.getParameter("pid"));
                dao.deleShipper(id);
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
