package controller;

import entity.Categories;
import entity.Employees;
import entity.OrderDetails;
import entity.Products;
import entity.Suppliers;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
import model.DAOEmployees;
import model.DAOOrderDetails;
import model.DAOProducts;
import model.DAOSuppliers;

/**
 *
 * @author MT Bac Ninh
 */
@WebServlet(name = "ControllerProduct", urlPatterns = {"/ControllerProduct"})
public class ControllerProduct extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            //Get Data
            DAOProducts dao = new DAOProducts();
            DAOCategories daocate = new DAOCategories();
            DAOEmployees daoem = new DAOEmployees();
            DAOSuppliers daosup = new DAOSuppliers();
            //Lấy service
            String service = request.getParameter("do");
            if (service == null) {//Khi gọi trực tiếp thì hiển thị listall
                service = "listAllProduct";
            }
            if (service.equals("insertProduct")) {
                String submit = request.getParameter("submit");
                if (submit == null) {//Chưa nhấn submit
                    Vector<Suppliers> vectorsup = daosup.listAllSupplier("select * from Suppliers");
                    Vector<Categories> vectorCate = daocate.listAllCategories("select * from Categories");
                    request.setAttribute("vectorsup", vectorsup);
                    request.setAttribute("vectorCate", vectorCate);
                    request.getRequestDispatcher("/view/AdminInsertProduct.jsp").forward(request, response);
                } else {

                    String ProductName = request.getParameter("pName");
                    String SupplierID = request.getParameter("suppId");
                    String CategoryID = request.getParameter("cateId");
                    String QuantityPerUnit = request.getParameter("qPerUnit");
                    String UnitPrice = request.getParameter("uPrice");
                    String UnitsInStock = request.getParameter("uInStock");
                    String UnitsOnOrder = request.getParameter("uOnOrder");
                    String ReorderLevel = request.getParameter("reOLevel");
                    String Discontinued = request.getParameter("discontinute");
                    String picture = request.getParameter("picture");

                    //Check/ Validate data
                    //Convert
//            private double UnitPrice;
//            private int UnitsInStock, UnitsOnOrder, ReorderLevel, Discontinued;
                    int suppId = Integer.parseInt(SupplierID);
                    int cateId = Integer.parseInt(CategoryID);
                    double uPrice = Double.parseDouble(UnitPrice);
                    int uInStock = Integer.parseInt(UnitsInStock);
                    int uOnOrder = Integer.parseInt(UnitsOnOrder);
                    int reOrLevel = Integer.parseInt(ReorderLevel);
                    int discon = Integer.parseInt(Discontinued);
                    //Display
                    Products pro = new Products(suppId, ProductName, suppId, cateId, QuantityPerUnit, uPrice, uInStock, uOnOrder, reOrLevel, discon, picture);
                    int n = dao.addProducts(pro);
                    if(n>0){                      
                        response.sendRedirect("ControllAdmin?do=listProduct");
                    }else{
                        out.print("óid");
                    }
                    
                }
            }
            if (service.equals("listAllProduct")) {
                String submit = request.getParameter("submit");
                String cateid = request.getParameter("cateid");
                String searchProName = request.getParameter("searchProName");
                String page = request.getParameter("page");
                if (page == null || page.trim().length() == 0) {
                    page = "1";
                }
                int pageIndex = Integer.parseInt(page);
                int pageSize = 9;
                int totalRecord = dao.countTotalPro("SELECT COUNT(*) as Total FROM Products");
                Vector<Products> vector = dao.listAllProductsPage(pageIndex, pageSize);
                Vector<Categories> vectorCate = daocate.listAllCategories("select * from Categories");
                if (submit != null) {// khi ấn search
                    vector = dao.listAllSeName(pageIndex, pageSize, searchProName);
                    totalRecord = dao.countTotalSearch(searchProName);
                    request.setAttribute("temppgsearch", searchProName);
                } else {// chưa ấn search
                    if (cateid != null) { //khi chọn category!
                        int careID = Integer.parseInt(cateid);
                        vector = dao.listCateID(careID, pageIndex, pageSize);
                        totalRecord = dao.countTotalPro("select COUNT(*) as Total from Products where CategoryID =" + careID + "");
                        request.setAttribute("temppg", careID);
                    }

                }
                //chia tính số trang
                int totalpage = (totalRecord % pageSize == 0) ? (totalRecord / pageSize) : ((totalRecord / pageSize) + 1);
                request.setAttribute("vector", vector);
                request.setAttribute("vectorCate", vectorCate);
                request.setAttribute("totalRecord", totalpage);
                request.setAttribute("pageIndex", pageIndex);
                request.getRequestDispatcher("/view/index.jsp").forward(request, response);
            }
            if (service.equals("updateProduct")) {
                String submit = request.getParameter("submit");
                if (submit == null) {//Chưa nhấn submit
//                    //Update
//                    //Step 1:get bản ghi --> Display form 
                    int pid = Integer.parseInt(request.getParameter("pid"));
                    ResultSet rs = dao.getData("select * from Products where ProductID=" + pid);
                    ResultSet rs1 = dao.getData("select * from Suppliers");
                    ResultSet rs2 = dao.getData("select * from Categories");
                    //set data for request
                    request.setAttribute("rsProduct", rs);
                    request.setAttribute("rsSupplier", rs1);
                    request.setAttribute("rscategories", rs2);
                    //select jsp
                    RequestDispatcher dispath
                            = request.getRequestDispatcher("view/AdminUpProduct.jsp");
                    dispath.forward(request, response);
//                    
                } else {//Update giống Insert nhưng k cần check vì update server
                    //Step 2 update
                    String spid = request.getParameter("pid");
                    String ProductName = request.getParameter("pName");
                    String SupplierID = request.getParameter("suppId");
                    String CategoryID = request.getParameter("cateId");
                    String QuantityPerUnit = request.getParameter("qPerUnit");
                    String UnitPrice = request.getParameter("uPrice");
                    String UnitsInStock = request.getParameter("uInStock");
                    String UnitsOnOrder = request.getParameter("uOnOrder");
                    String ReorderLevel = request.getParameter("reOLevel");
                    String Discontinued = request.getParameter("discontinute");
                    String img = request.getParameter("img");
                    //Convert
                    int pid = Integer.parseInt(spid);
                    int suppId = Integer.parseInt(SupplierID);
                    int cateId = Integer.parseInt(CategoryID);
                    double uPrice = Double.parseDouble(UnitPrice);
                    int uInStock = Integer.parseInt(UnitsInStock);
                    int uOnOrder = Integer.parseInt(UnitsOnOrder);
                    int reOrLevel = Integer.parseInt(ReorderLevel);
                    int discon = Integer.parseInt(Discontinued);
                    //Display
                    Products pro = new Products(pid, ProductName, suppId, cateId, QuantityPerUnit, uPrice, uInStock, uOnOrder, reOrLevel, discon, img);
                    int n = dao.updateProducts(pro);
                    response.sendRedirect("ControllAdmin?do=listProduct");//Chuyển trang 1 cách trực tiếp đến 1 trang nào đó
                }
            }
            if (service.equals("deleteProduct")) {
                //Delete
                boolean check =false;
                int pid = Integer.parseInt(request.getParameter("pid"));
                DAOOrderDetails daooder = new DAOOrderDetails();
                Vector<OrderDetails> vector = daooder.listAllOrderDetail();
                for (OrderDetails orderDetails : vector) {
                    if(orderDetails.getProductID()==pid){
                        check=true;
                    }
                }
                if(check==false){
                    
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
