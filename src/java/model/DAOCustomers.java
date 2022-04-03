package model;

import entity.Account;
import entity.Customers;
import entity.Products;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

//insert into Customers(CustomerID,CompanyName,ContactName,ContactTitle,
//Address,City,Region,PostalCode,Country, Phone, Fax)
//values ('DemoID CUS', 'Cong Ty Nam Tien', 'Nam Tien', 'Owner', '123 Bac Ninh', 'Bac Ninh', 'CA', '1234', 'VN', '0353890333', '0353890333')
public class DAOCustomers extends ConnectDB {

    //Insert
    public int addCustomers(Customers cus) {
        int n = 0;
        String sql = "insert into Customers(CustomerID,CompanyName,ContactName,ContactTitle,Address,City,Region,PostalCode,Country, Phone, Fax)"
                + " values (?,?,?,?,?,?,?,?,?,?,?)";

        try {
            //Create Statement
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, cus.getCustomerID());
            pre.setString(2, cus.getCompanyName());
            pre.setString(3, cus.getContactName());
            pre.setString(4, cus.getContactTitle());
            pre.setString(5, cus.getAddress());
            pre.setString(6, cus.getCity());
            pre.setString(7, cus.getRegion());
            pre.setString(8, cus.getPostalCode());
            pre.setString(9, cus.getCountry());
            pre.setString(10, cus.getPhone());
            pre.setString(11, cus.getFax());

            //run
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n;
    }
    public int addUserPass(Customers cus) {
        int n = 0;
        String sql = "insert into Customers(CustomerID,userName,password,roll)"
                + " values (?,?,?,?)";

        try {
            //Create Statement
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, cus.getCustomerID());
            pre.setString(2, cus.getUser());
            pre.setString(3, cus.getPass());
            pre.setInt(4, 0);
            //run
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n;
    }

    //Update
    public int updateCustomers(Customers cus) {
        int n = 0;
        String sql = "update Customers set CompanyName=?,ContactName=?,"
                + "ContactTitle=?,Address=?,City=?,Region=?,PostalCode=?,Country=?, "
                + "Phone=?, Fax=?,userName=?,password=? where CustomerID=?";

        try {
            //Create Statement
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, cus.getCompanyName());
            pre.setString(2, cus.getContactName());
            pre.setString(3, cus.getContactTitle());
            pre.setString(4, cus.getAddress());
            pre.setString(5, cus.getCity());
            pre.setString(6, cus.getRegion());
            pre.setString(7, cus.getPostalCode());
            pre.setString(8, cus.getCountry());
            pre.setString(9, cus.getPhone());
            pre.setString(10, cus.getFax());
            pre.setString(11, cus.getUser());
            pre.setString(12, cus.getPass());
            pre.setString(13, cus.getCustomerID());
            
            //run
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n;
    }

    //Delete
    public int deleCustomer(String id) {
        int n = 0;
        String sql = "delete from Customers where CustomerID='" + id + "'";
        //Check foreign key constrain
        try {
            Statement state = conn.createStatement();
            n = state.executeUpdate(sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return n;
    }

    //Select All
    public void listAllCustommer() {
        String sql = "select * from Customers";

        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                String cusId = rs.getString("CustomerID");
                String companyName = rs.getString(2);
                String contactName = rs.getString(3);
                String contactTitle = rs.getString(4);
                String address = rs.getString(5);
                String city = rs.getString(6);
                String region = rs.getString(7);
                String postalCode = rs.getString(8);
                String country = rs.getString(9);
                String phone = rs.getString(10);
                String fax = rs.getString(11);

                Customers cus = new Customers(cusId, companyName, contactName,
                        contactTitle, address, city, region, postalCode,
                        country, phone, fax);

                System.out.println(cus);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public Vector<Customers> listAllCustommer(String sql) {
        Vector<Customers> vector = new Vector<>();
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                String cusId = rs.getString("CustomerID");
                String companyName = rs.getString(2);
                String contactName = rs.getString(3);
                String contactTitle = rs.getString(4);
                String address = rs.getString(5);
                String city = rs.getString(6);
                String region = rs.getString(7);
                String postalCode = rs.getString(8);
                String country = rs.getString(9);
                String phone = rs.getString(10);
                String fax = rs.getString(11);
                String user = rs.getString(12);
                String pass = rs.getString(13);
                Customers cus = new Customers(cusId, companyName, contactName,
                        contactTitle, address, city, region, postalCode,
                        country, phone, fax, user, pass);
                vector.add(cus);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return vector;
    }
    public String getId(String user) {
        String sql = "select CustomerID from Customers where userName='"+ user+"'";
        String n="";
        try {
           
            ResultSet rs = getData(sql);
            if (rs.next()) {
                n=rs.getString(1);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n;
    }

    public Customers userPassCustomers(String user, String pass) {
        String sql = "select * from Customers where userName=? and password=?";
        try {
            //Create Statement
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, user);
            pre.setString(2, pass);
            //run
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                return new Customers(rs.getString("CustomerID"),
                        rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
                        rs.getString(6), rs.getString(7), rs.getString(8),
                        rs.getString(9), rs.getString(10), rs.getString(11), rs.getString(12), rs.getString(13));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    Vector<Account> vector = new Vector<>();

    public void userPass() {
        String sql = "select userName,password from Customers";
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                if (rs.getString(1) != null) {
                    vector.add(new Account(rs.getString(1), rs.getString(2), 0));
                }
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    public void userPass1() {

        String sql = "select userName,password from Employees";
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                if (rs.getString(1) != null) {
                    vector.add(new Account(rs.getString(1), rs.getString(2), 1));
                }
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public String generateCaptcha() {
        StringBuilder captcha = new StringBuilder("");
        Random random = new Random();
        String str = "QWERTYUIOPASDFGHJKLZXCVBNM";
        DAOCustomers dao = new DAOCustomers();
        Vector<Customers> vector = dao.listAllCustommer("select *from Customers");
        String temp = "";
        while (true) {
            for (int i = 0; i < 5; i++) {//6 ki tu
                int idxChar = random.nextInt(str.length());//random vị trí
                captcha.append(str.charAt(idxChar));// lấy giá trị 
            }
            temp = captcha.toString();

            for (Customers customers : vector) {
                if (customers.getCustomerID().equals(temp)) {
                } else {
                    return temp;
                }
            }

        }

    }

    public static void main(String[] args) {
        DAOCustomers dao = new DAOCustomers();
//        int n =dao.addUserPass(new Customers("aaaa", "hfdkafa", "sgsfdsf"));
       
        System.out.println(dao.getId("dat"));
//        dao.userPass();
//        dao.userPass1();
//        Vector<Customers> vector = dao.listAllCustommer("select *from Customers");
//        for (Customers vf : vector) {
//            System.out.println(vf);
//        }
    }
}
   
//Main
