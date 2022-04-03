package model;

import entity.Suppliers;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

//insert into Suppliers(CompanyName,ContactName,ContactTitle,Address,City,Region,PostalCode,Country,Phone, Fax, HomePage)
//values ('Cong Ty Nam Tien', 'Nam Tien', 'Sales Agent', 'Thuan Thanh', 'Bac ninh', 'LA', '1234', 'VN', '0353890333', '0353890333', 'Demo Home Page')

public class DAOSuppliers extends ConnectDB{
    //Insert
    public int addSupplier(Suppliers sup){
        int n = 0;
        String sql = "insert into Suppliers(CompanyName,ContactName,ContactTitle,Address,City,Region,PostalCode,Country,Phone, Fax, HomePage)"
                +"values (?,?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, sup.getCompanyName()); 
            pre.setString(2, sup.getContactName()); 
            pre.setString(3, sup.getContactTitle()); 
            pre.setString(4,sup.getAddress()); 
            pre.setString(5, sup.getCity()); 
            pre.setString(6, sup.getRegion()); 
            pre.setString(7, sup.getPostalCode()); 
            pre.setString(8, sup.getCountry()); 
            pre.setString(9, sup.getPhone()); 
            pre.setString(10, sup.getFax()); 
            pre.setString(11, sup.getHomePage()); 
            
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return n;
    }
    //Update
    public int updateSupplier(Suppliers sup){
        int n = 0;
        String sql = "update Suppliers set CompanyName=?,ContactName=?,ContactTitle=?,Address=?,City=?"
                + ",Region=?,PostalCode=?,Country=?,Phone=?, Fax=?, HomePage=? where SupplierID=?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, sup.getCompanyName()); 
            pre.setString(2, sup.getContactName()); 
            pre.setString(3, sup.getContactTitle()); 
            pre.setString(4,sup.getAddress()); 
            pre.setString(5, sup.getCity()); 
            pre.setString(6, sup.getRegion()); 
            pre.setString(7, sup.getPostalCode()); 
            pre.setString(8, sup.getCountry()); 
            pre.setString(9, sup.getPhone()); 
            pre.setString(10, sup.getFax()); 
            pre.setString(11, sup.getHomePage()); 
            pre.setInt(12, sup.getSupplierID());
            
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return n;
    }
    //Delete
    public int deleSupplier(int id){
        int n = 0;
        String sql = "delete from Suppliers where SupplierID=" +id;
        
        try {
            Statement state = conn.createStatement();
            n = state.executeUpdate(sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n;
    }
    //Select List All
    public void listAllSupplier(){
        String sql = "select * from Suppliers";
        
        ResultSet rs = getData(sql);
        try {
            while(rs.next()){
                int supplierId = rs.getInt("SupplierID");
                String CompanyName = rs.getString(2);
                String ContactName = rs.getString(3);
                String ContactTitle = rs.getString(4);
                String Address = rs.getString(5);
                String City = rs.getString(6);
                String Region = rs.getString(7);
                String PostalCode = rs.getString(8);
                String Country = rs.getString(9);
                String Phone = rs.getString(10);
                String Fax = rs.getString(11);
                String HomePage = rs.getString(12);
                
                Suppliers sup = new Suppliers(supplierId, CompanyName, ContactName, 
                        ContactTitle, Address, City, Region, PostalCode, Country, 
                        Phone, Fax, HomePage);
                
                System.out.println(sup);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    public Vector<Suppliers> listAllSupplier(String sql){
      
        Vector<Suppliers> vector = new Vector<>();
        ResultSet rs = getData(sql);
        try {
            while(rs.next()){
                int supplierId = rs.getInt("SupplierID");
                String CompanyName = rs.getString(2);
                String ContactName = rs.getString(3);
                String ContactTitle = rs.getString(4);
                String Address = rs.getString(5);
                String City = rs.getString(6);
                String Region = rs.getString(7);
                String PostalCode = rs.getString(8);
                String Country = rs.getString(9);
                String Phone = rs.getString(10);
                String Fax = rs.getString(11);
                String HomePage = rs.getString(12);
                
                Suppliers sup = new Suppliers(supplierId, CompanyName, ContactName, 
                        ContactTitle, Address, City, Region, PostalCode, Country, 
                        Phone, Fax, HomePage);
                
                vector.add(sup);
                        
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return vector;
    }
    
    //Main
    public static void main(String[] args) {
        DAOSuppliers daoSup = new DAOSuppliers();
//        int n = daoSup.addSupplier(
//                new Suppliers("Cong Ty Nam Tien", "Nam Tien", "Sales Agent", 
//               "Thuan Thanh", "Bac ninh", "LA", "1234", "VN", "0353890333", 
//               "0353890333", "Demo HomePage")
//        );
//        if(n > 0){
//            System.out.println("Inserted Data Suppliers");
//        }
        //Update
//        int up = daoSup.updateSupplier(
//                new Suppliers(30, "Cong Ty FUHO", "FUHO", "Sales Agent", 
//               "Thuan Thanh", "Bac ninh", "LA", "1234", "VN", "0353890333", 
//               "0353890333", "Demo HomePage")
//        );
//        if(up > 0){
//            System.out.println("Update Data Suppliers");
//        }
        //Delete
//        int dele = daoSup.deleCategory(30);
//        if(dele > 0){
//            System.out.println("Delete SuccessFul");
//        }
        //Select
        daoSup.listAllSupplier();
    }
}
