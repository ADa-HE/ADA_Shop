package model;

import entity.Employee1;
import entity.Employees;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

//insert into Employees(LastName, FirstName, Title, TitleOfCourtesy, 
//BirthDate, HireDate, Address, City, Region, PostalCode, Country, 
//HomePhone, Extension, Photo, Notes, ReportsTo, PhotoPath)
//values('David', 'DemoA', 'Sales Manager', 'Mr', '1999-01-01', '2020-01-01', 'Thuan Thanh', 'Bac Ninh'
//,'WA','99201', 'VN', '0353890333', '1234', 'Photo', 'Notes demo', 2, 'demophoto.jpg')
public class DAOEmployees extends ConnectDB{
    public int addEmployees(Employees emp){
        int n = 0;
        String sql = "insert into Employees(LastName, FirstName, Title, TitleOfCourtesy"
                + ", BirthDate, HireDate, Address, City, Region, "
                + "PostalCode, Country,HomePhone, Extension, Notes, "
                + "ReportsTo, PhotoPath,username,[password],[roll])" +
                " values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,1)";
        
        try {
            //Create Prepare Statement
            PreparedStatement pre = conn.prepareStatement(sql);
            
            pre.setString(1, emp.getLastName());
            pre.setString(2, emp.getFirstName());
            pre.setString(3, emp.getTitle());
            pre.setString(4, emp.getTitleOfCourtesy());
            pre.setString(5, emp.getBirthDate());
            pre.setString(6, emp.getHireDate());
            pre.setString(7, emp.getAddress());
            pre.setString(8, emp.getCity());
            pre.setString(9, emp.getRegion());
            pre.setString(10, emp.getPostalCode());
            pre.setString(11, emp.getCountry());
            pre.setString(12, emp.getHomePhone());
            pre.setString(13, emp.getExtension());
            pre.setString(14, emp.getNotes());
            pre.setInt(15, emp.getReportsTo());
            pre.setString(16, emp.getPhotoPath());
            pre.setString(17, emp.getPhotoPath());
            pre.setString(18, emp.getPhotoPath());
            
            //run
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n;
    }
    //Update
    public int updateEmployees(Employees emp){
        int n = 0;
        String sql = "update Employees set LastName=?, FirstName=?, Title=?, TitleOfCourtesy=?"
                + ", BirthDate=?, HireDate=?, Address=?, City=?, Region=?, "
                + "PostalCode=?, Country=?,HomePhone=?, Extension=?, Notes=?, "
                + "ReportsTo=?, PhotoPath=? where EmployeeID=?";
        
        try {
            //Create Prepare Statement
            PreparedStatement pre = conn.prepareStatement(sql);
            
            pre.setString(1, emp.getLastName());
            pre.setString(2, emp.getFirstName());
            pre.setString(3, emp.getTitle());
            pre.setString(4, emp.getTitleOfCourtesy());
            pre.setString(5, emp.getBirthDate());
            pre.setString(6, emp.getHireDate());
            pre.setString(7, emp.getAddress());
            pre.setString(8, emp.getCity());
            pre.setString(9, emp.getRegion());
            pre.setString(10, emp.getPostalCode());
            pre.setString(11, emp.getCountry());
            pre.setString(12, emp.getHomePhone());
            pre.setString(13, emp.getExtension());
            pre.setString(14, emp.getNotes());
            pre.setInt(15, emp.getReportsTo());
            pre.setString(16, emp.getPhotoPath());
            pre.setInt(17, emp.getEmployeeID());
            //run
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n;
    }
    //Delete
    public int deleEmployee(int id){
        int n = 0;
        String sql = "delete from Employees where EmployeeID=" +id;
        
        try {
            Statement state = conn.createStatement();
            n = state.executeUpdate(sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n;
    }
    //Select list all
    public void listAllEmployee(){
        String sql = "select * from Employees";
        
        ResultSet rs = getData(sql);
        try {
            while(rs.next()){
                int empID = rs.getInt("EmployeeID");
                String lName = rs.getString(2);
                String fName = rs.getString(3);
                String title = rs.getString(4);
                String TOC = rs.getString(5);
                String birthDate = rs.getString(6);
                String hireDate = rs.getString(7);
                String address = rs.getString(8);
                String city = rs.getString(9);
                String region = rs.getString(10);
                String postalCode = rs.getString(11);
                String country = rs.getString(12);
                String homePhone = rs.getString(13);
                String extension = rs.getString(14);
                String photo = rs.getString(15);
                String notes = rs.getString(16);
                int reportsTo = rs.getInt(17);
                String photoPath = rs.getString(18);
                
                Employees emp = new Employees(empID, lName, fName, title, TOC, birthDate,
                        hireDate, address, city, region, postalCode, country, homePhone, 
                        extension, photo, notes, reportsTo, photoPath);
                
                System.out.println(emp);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    public Vector<Employees> listAllEmployees(String sql){
        Vector<Employees> vector = new Vector<Employees>();
        ResultSet rs = getData(sql);
        try {
            while(rs.next()){
                int empID = rs.getInt("EmployeeID");
                String lName = rs.getString(2);
                String fName = rs.getString(3);
                String title = rs.getString(4);
                String TOC = rs.getString(5);
                String birthDate = rs.getString(6);
                String hireDate = rs.getString(7);
                String address = rs.getString(8);
                String city = rs.getString(9);
                String region = rs.getString(10);
                String postalCode = rs.getString(11);
                String country = rs.getString(12);
                String homePhone = rs.getString(13);
                String extension = rs.getString(14);
                String photo = rs.getString(15);
                String notes = rs.getString(16);
                int reportsTo = rs.getInt(17);
                String photoPath = rs.getString(18);
                String user= rs.getString(19);
                String pass = rs.getString(20);
                Employees emp = new Employees(empID, lName, fName, title, TOC, birthDate,
                        hireDate, address, city, region, postalCode, country, homePhone, 
                        extension, photo, notes, reportsTo, photoPath,user,pass);
                
                vector.add(emp);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return vector;
    }
    public Employee1 userPassEmployee(String user, String pass) {
        String sql = "select * from Employees where username=? and password=?";
        try {
            //Create Statement
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, user);
            pre.setString(2, pass);
            //run
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                int empID = rs.getInt("EmployeeID");
                String lName = rs.getString(2);
                String fName = rs.getString(3);
                String title = rs.getString(4);
                String TOC = rs.getString(5);
                String birthDate = rs.getString(6);
                String hireDate = rs.getString(7);
                String address = rs.getString(8);
                String city = rs.getString(9);
                String region = rs.getString(10);
                String postalCode = rs.getString(11);
                String country = rs.getString(12);
                String homePhone = rs.getString(13);
                String extension = rs.getString(14);
                String photo = rs.getString(15);
                String notes = rs.getString(16);
                int reportsTo = rs.getInt(17);
                String photoPath = rs.getString(18);
                String userr = rs.getString(19);
                String passs = rs.getString(20);
                return new Employee1(empID, lName, fName, title, country, birthDate, hireDate, address, city, region, postalCode, country, homePhone, extension, photo, notes, reportsTo, photoPath, user, pass);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    //Main
    public static void main(String[] args) {
        DAOEmployees daoEmp = new DAOEmployees();
//        int n = daoEmp.addEmployees(
//                new Employees("David", "DemoA", "Sales Manager", "Mr", 
//            "1999-01-01", "2020-01-01", "Thuan Thanh", "Bac Ninh", "WA", 
//            "99201", "VN", "0353890333", "1234", "Photo", "Notes demo", 2, "demophoto.jpg")
//        );
//        if(n > 0){
//            System.out.println("Inserted Data Employees");
//        }
        //Update
//        int up = daoEmp.updateEmployees(
//                new Employees(10, "Update Emp", "UpdateA", "Sales Manager", "Mr", 
//            "1999-01-01", "2020-01-01", "Thuan Thanh", "Bac Ninh", "WA", 
//            "99201", "VN", "0353890333", "1234", "Photo", "Notes demo", 2, "demophoto.jpg")
//        );
//        if(up > 0){
//            System.out.println("Update Data Employees");
//        }
        //Delete
//        int dele = daoEmp.deleEmployee(12);
//        if(dele > 0){System.out.println("Delete Employee success");}
        //Select all list
        daoEmp.listAllEmployee();
    }
}
