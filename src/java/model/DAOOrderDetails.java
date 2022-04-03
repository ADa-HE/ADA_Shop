package model;

import entity.OrderDetails;
import entity.Status;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Stack;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;


public class DAOOrderDetails extends ConnectDB{
    public int addOrderDetails(OrderDetails orD){
        int n = 0;
        String sql = "INSERT INTO [Order Details](OrderID,ProductID,"
                + "UnitPrice,Quantity,Discount)"
                + "VALUES(?,?,?,?,?)";
        try {
            PreparedStatement pre =conn.prepareStatement(sql);
            pre.setInt(1, orD.getOrderID());
            pre.setInt(2, orD.getProductID());
            pre.setDouble(3, orD.getUnitPrice());
            pre.setInt(4, orD.getQuantity());
            pre.setDouble(5, orD.getDiscount());
            
            n =pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n;
    }
    //Update
    public int updateOrderDetails(OrderDetails orD){
        int n = 0;
        String sql = "update [Order Details] set UnitPrice=?,Quantity=?,Discount=? where OrderID=? and ProductID=?";
        try {
            PreparedStatement pre =conn.prepareStatement(sql);
            
            pre.setDouble(1, orD.getUnitPrice());
            pre.setInt(2, orD.getQuantity());
            pre.setDouble(3, orD.getDiscount());
            pre.setInt(4, orD.getOrderID());
            pre.setInt(5, orD.getProductID());
            
            n =pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n;
    }
    //Delete
    public int deleOrdDetail(int OrderID, int ProductID){
        int n = 0;
        String sql = "delete from [Order Details] where OrderID="+OrderID
                +" and ProductID=" + ProductID;
        
        try {
            Statement state = conn.createStatement();
            n = state.executeUpdate(sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n;
    }
    //Select
    public Vector<OrderDetails> listAllOrderDetail(){
        String sql = "select * from [Order Details]";
        Vector<OrderDetails> vectortal = new Vector<>();
        ResultSet rs = getData(sql);
        try {
            while(rs.next()){
                int ordId = rs.getInt(1);
                int proId = rs.getInt(2);
                double unitPrice = rs.getDouble(3);
                int quantity = rs.getInt(4);
                double discount = rs.getInt(5);
                
                OrderDetails ordDetail = new OrderDetails(ordId, proId, unitPrice, quantity, discount);
                vectortal.add(ordDetail);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return vectortal;
    }
    public Vector listAllOrderDetail1(){
        Vector<OrderDetails> vector = new Vector<>();
        String sql = "select * from [Order Details]";
        ResultSet rs = getData(sql);
        try {
            while(rs.next()){
                int ordId = rs.getInt(1);
                int proId = rs.getInt(2);
                double unitPrice = rs.getDouble(3);
                int quantity = rs.getInt(4);
                double discount = rs.getInt(5);
                
                OrderDetails ordDetail = new OrderDetails(ordId, proId, unitPrice, quantity, discount);
                vector.add(ordDetail);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return vector;
    }
    public Vector listStatus(){
        Vector<Status> vector = new Vector<>();
        String sql = "select * from [status]";
        ResultSet rs = getData(sql);
        try {
            while(rs.next()){
                int sId = rs.getInt(1);
                String name = rs.getString(2);
                vector.add(new Status(sId, name));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return vector;
    }
    public Vector listStatusWait(){
        Vector<Status> vector = new Vector<>();
        String sql = "select * from Orders where status=1";
        ResultSet rs = getData(sql);
        try {
            while(rs.next()){
                int sId = rs.getInt(1);
                String name = rs.getString(2);
                vector.add(new Status(sId, name));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return vector;
    }
     public Vector listAllOder(String cusID){
        Vector<Status> vector = new Vector<>();
        String sql = "select * from [status]";
        ResultSet rs = getData(sql);
        try {
            while(rs.next()){
                int sId = rs.getInt(1);
                String name = rs.getString(2);
                vector.add(new Status(sId, name));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return vector;
    }
    public Vector listByOrderID(String CusID){
        Vector<OrderDetails> vector = new Vector<>();
        String sql = "select od.OrderID, od.ProductID, od.UnitPrice, od.Quantity, od.Discount,o.status from [Order Details] od\n" +
"join Orders o on o.OrderID = od.OrderID where o.CustomerID='"+CusID +"'order by o.status asc";
        ResultSet rs = getData(sql);
        try {
            while(rs.next()){
                int ordId = rs.getInt(1);
                int proId = rs.getInt(2);
                double unitPrice = rs.getDouble(3);
                int quantity = rs.getInt(4);
                double discount = rs.getInt(5);
                int status = rs.getInt(6);
                OrderDetails ordDetail = new OrderDetails(ordId, proId, unitPrice, quantity, discount,status);
                vector.add(ordDetail);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return vector;
    }
    
    //Join All Table
    public void joinOrderDetail(){
        
        String sql = "select od.OrderID, od.ProductID, od.UnitPrice, od.Quantity, od.Discount from [Order Details] od" +
                    " join Orders o on o.OrderID = od.OrderID" +
                    " join Products pro on od.ProductID = pro.ProductID" +
                    " join Suppliers sup on pro.SupplierID = sup.SupplierID" +
                    " join Categories cate on pro.CategoryID = cate.CategoryID" +
                    " join Customers cus on o.CustomerID = cus.CustomerID" +
                    " join Employees emp on o.EmployeeID = emp.EmployeeID" +
                    " join Shippers ship on o.ShipVia = ship.ShipperID";

        
//        System.out.println(sql);
        ResultSet rs = getData(sql);
        try {
            while(rs.next()){
                int ordId = rs.getInt(1);
                int proId = rs.getInt(2);
                double unitPrice = rs.getDouble(3);
                int quantity = rs.getInt(4);
                double discount = rs.getInt(5);
                
                OrderDetails ordDetail = new OrderDetails(ordId, proId, unitPrice, quantity, discount);
                
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    //Main
    public static void main(String[] args) {
        DAOOrderDetails daoDetail = new DAOOrderDetails();
        OrderDetails oaa= new OrderDetails
        int n = daoDetail.addOrderDetails(new OrderDetails(10248, 1, 13.00, 2, 0));
                System.out.println(n);
//        );
//        if(n > 0){
//            System.out.println("Inserted Data OrderDetails");
//        }
//        //Update

//        int up = daoDetail.updateOrderDetails(
//                new OrderDetails(10248, 77, 1, 10, 0)
//        );
//        if(up > 0){
//            System.out.println("Updated Data OrderDetails");
//        }
          //Delete
//          int n = daoDetail.deleOrdDetail(10248, 77);
//          if(n > 0){System.out.println("Delete Successfull");}
//          else{System.out.println("Not Found");}
          //Select
//          daoDetail.listAllOrderDetail();
          //Join All Table
//          Vector<Status> dao = daoDetail.listStatus();
//          for (Status orderDetails : dao) {
//              System.out.println(orderDetails);
        }
    }

