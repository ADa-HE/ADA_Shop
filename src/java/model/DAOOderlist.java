/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import entity.Oderlist;
import entity.OrderDetails;
import entity.Status;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

/**
 *
 * @author ADA
 */
public class DAOOderlist extends ConnectDB {
    public Vector<Oderlist> joinOrderDetail(){
        Vector<Oderlist> vector = new Vector<>();
        String sql ="select DISTINCT o.OrderID, cus.ContactName, o.OrderDate, o.OrderDate,o.status from [Order Details] od\n" +
"                                               join Orders o on o.OrderID = od.OrderID\n" +
"                                              join Products pro on od.ProductID = pro.ProductID\n" +
"                                                join Suppliers sup on pro.SupplierID = sup.SupplierID\n" +
"                                                join Categories cate on pro.CategoryID = cate.CategoryID\n" +
"                                               join Customers cus on o.CustomerID = cus.CustomerID\n" +
"                                               join Employees emp on o.EmployeeID = emp.EmployeeID\n" +
"                                             join Shippers ship on o.ShipVia = ship.ShipperID ";
//        System.out.println(sql);
        ResultSet rs = getData(sql);
        try {
            while(rs.next()){
                int ordId = rs.getInt(1);
                String contactName = rs.getString(2);
                Date OrderDate = rs.getDate(3);
                Date ShipDate =rs.getDate(4);
                int status = rs.getInt(5);
                Oderlist list = new Oderlist(ordId, contactName, OrderDate,  ShipDate,status);
                vector.add(list);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return vector;
    }
    public Vector<Oderlist> joinOrderDetailName( String name){
        Vector<Oderlist> vector = new Vector<>();
        String sql ="select  DISTINCT o.OrderID, cus.ContactName, o.OrderDate, o.ShippedDate,o.status from [Order Details] od\n" +
"                                              join Orders o on o.OrderID = od.OrderID\n" +
"                                             join Products pro on od.ProductID = pro.ProductID\n" +
"                                               join Suppliers sup on pro.SupplierID = sup.SupplierID\n" +
"                                               join Categories cate on pro.CategoryID = cate.CategoryID\n" +
"                                              join Customers cus on o.CustomerID = cus.CustomerID\n" +
"                                             join Employees emp on o.EmployeeID = emp.EmployeeID\n" +
"                                           join Shippers ship on o.ShipVia = ship.ShipperID where cus.ContactName like '%" + name + "%'";
//        System.out.println(sql);
        ResultSet rs = getData(sql);
        try {
            while(rs.next()){
                int ordId = rs.getInt(1);
                String contactName = rs.getString(2);
                Date OrderDate = rs.getDate(3);
                Date ShipDate =rs.getDate(4);
                int status = rs.getInt(5);
                Oderlist list = new Oderlist(ordId, contactName, OrderDate,  ShipDate,status);
                vector.add(list);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return vector;
    }
    public Vector<Oderlist> joinOrderDetailID( int idSta){
        Vector<Oderlist> vector = new Vector<>();
        String sql ="select  DISTINCT o.OrderID, cus.ContactName, o.OrderDate, o.ShippedDate,o.status from [Order Details] od\n" +
"                                              join Orders o on o.OrderID = od.OrderID\n" +
"                                             join Products pro on od.ProductID = pro.ProductID\n" +
"                                               join Suppliers sup on pro.SupplierID = sup.SupplierID\n" +
"                                               join Categories cate on pro.CategoryID = cate.CategoryID\n" +
"                                              join Customers cus on o.CustomerID = cus.CustomerID\n" +
"                                             join Employees emp on o.EmployeeID = emp.EmployeeID\n" +
"                                           join Shippers ship on o.ShipVia = ship.ShipperID where o.status="+idSta;
//        System.out.println(sql);
        ResultSet rs = getData(sql);
        try {
            while(rs.next()){
                int ordId = rs.getInt(1);
                String contactName = rs.getString(2);
                Date OrderDate = rs.getDate(3);
                Date ShipDate =rs.getDate(4);
                int status = rs.getInt(5);
                Oderlist list = new Oderlist(ordId, contactName, OrderDate,  ShipDate,status);
                vector.add(list);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return vector;
    }
    public Vector<Status> listStatus(String sql){
    Vector<Status> vector = new Vector<>();
//        System.out.println(sql);
        ResultSet rs = getData(sql);
        try {
            while(rs.next()){
                int StatusId = rs.getInt(1);
                String NameStatus = rs.getString(2);
                Status sta = new Status(StatusId, NameStatus);
                    vector.add(sta);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return vector;
    }
    
    public int updateStatus( int status,int id){
        int n=0;
            String sqlPre = "update Orders set status =?\n" +
"               where OrderID = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sqlPre);
            //pre.setDataType(index, value);
            pre.setInt(1, status);
            pre.setInt(2, id);
           
            //run
            n = pre.executeUpdate();
                //pre.execute(); create, drop, Alter
                //pre.executeQuery(); select
                //pre.executeUpdate(); insert, delete, update
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n;
    }
   
    public static void main(String[] args) {
        DAOOderlist dao = new DAOOderlist();
//        Vector vector = dao.listStatus("select * from Status");
        Vector<Oderlist> vector = dao.joinOrderDetailID(3);
        for (Oderlist t : vector) {
            System.out.println(t);
        }
//           int n= dao.updateStatus(3, 10263);
//           if(n>0) System.out.println("ok");
    }
}
