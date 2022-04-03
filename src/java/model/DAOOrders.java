package model;

import entity.Orders;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Stack;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DAOOrders extends ConnectDB {

    public int addOrders(Orders ord) {
        int n = 0;
        String sql = "insert into Orders(CustomerID,EmployeeID, OrderDate,"
                + "RequiredDate,ShippedDate, ShipVia, Freight, ShipName, "
                + "ShipAddress, ShipCity, ShipRegion, ShipPostalCode, ShipCountry)"
                + " values(?,?,?,?,?,?,?,?,?,?,?,?,?)";

        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            // set value for ?
            // ? index start 1
            // pre.setDataType(index, value);
            //dataType of dataType of field
            pre.setString(1, ord.getCustomerID());
            pre.setInt(2, ord.getEmployeeID());
            pre.setString(3, ord.getOrderDate());
            pre.setString(4, ord.getRequiredDate());
            pre.setString(5, ord.getShippedDate());
            pre.setInt(6, ord.getShipVia());
            pre.setDouble(7, ord.getFreight());
            pre.setString(8, ord.getShipName());
            pre.setString(9, ord.getShipAddress());
            pre.setString(10, ord.getShipCity());
            pre.setString(11, ord.getShipRegion());
            pre.setString(12, ord.getShipPostalCode());
            pre.setString(13, ord.getShipCountry());
            //Gán lại n
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n;
    }

    public int addOrderGetID(Orders ord) {
        int n = 0;
        String sql = "insert into Orders(CustomerID,EmployeeID,"
                + " ShipName,"
                + "ShipAddress, ShipCity, ShipPostalCode, ShipCountry)"
                + " values(?,?,?,?,?,?,?)";

        try {
            PreparedStatement pre = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            // set value for ?
            // ? index start 1
            // pre.setDataType(index, value);
            //dataType of dataType of field
            pre.setString(1, ord.getCustomerID());
            pre.setInt(2, ord.getEmployeeID());
            pre.setString(3, ord.getShipName());
            pre.setString(4, ord.getShipAddress());
            pre.setString(5, ord.getShipCity());
            pre.setString(6, ord.getShipPostalCode());
            pre.setString(7, ord.getShipCountry());
            //Gán lại n
            n = pre.executeUpdate();
            ResultSet rs = pre.getGeneratedKeys();
            while (rs.next()) {
                n = rs.getInt(1);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n;
    }

    //Update
    public int updateOrders(Orders ord) {
        int n = 0;
        String sql = "update Orders set CustomerID=?,EmployeeID=?, OrderDate=?,"
                + "RequiredDate=?,ShippedDate=?, ShipVia=?, Freight=?, ShipName=?, "
                + "ShipAddress=?, ShipCity=?, ShipRegion=?, ShipPostalCode=?, ShipCountry=? where OrderID=?";

        try {
            PreparedStatement pre = conn.prepareStatement(sql);

            pre.setString(1, ord.getCustomerID());
            pre.setInt(2, ord.getEmployeeID());
            pre.setString(3, ord.getOrderDate());
            pre.setString(4, ord.getRequiredDate());
            pre.setString(5, ord.getShippedDate());
            pre.setInt(6, ord.getShipVia());
            pre.setDouble(7, ord.getFreight());
            pre.setString(8, ord.getShipName());
            pre.setString(9, ord.getShipAddress());
            pre.setString(10, ord.getShipCity());
            pre.setString(11, ord.getShipRegion());
            pre.setString(12, ord.getShipPostalCode());
            pre.setString(13, ord.getShipCountry());
            pre.setInt(14, ord.getOrderID());
            //Gán lại n
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n;
    }

    //Delete
    public int deleOrder(int id) {
        int n = 0;
        String sql = "delete from Orders where OrderID=" + id;

        try {
            Statement state = conn.createStatement();
            n = state.executeUpdate(sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n;
    }

    //Search Order by CustomerID
    public Vector<Orders> searchByCustomerId(String CustomerID) {
        Vector<Orders> vector = new Vector<Orders>();
        String sql = "select * from Orders where CustomerID LIKE '%" + CustomerID + "%'";

        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                int odId = rs.getInt(1);
                String cusId = rs.getString(2);
                int empId = rs.getInt(3);
                String odDate = rs.getString(4);
                String RequiredDate = rs.getString(5);
                String ShippedDate = rs.getString(6);
                int ShipVia = rs.getInt(7);
                double Freight = rs.getDouble(8);
                String ShipName = rs.getString(9);
                String ShipAddress = rs.getString(10);
                String ShipCity = rs.getString(11);
                String ShipRegion = rs.getString(12);
                String ShipPostalCode = rs.getString(13);
                String ShipCountry = rs.getString(14);

                Orders od = new Orders(odId, cusId, empId, odDate, RequiredDate,
                        ShippedDate, ShipVia, Freight, ShipName, ShipAddress,
                        ShipCity, ShipRegion, ShipPostalCode, ShipCountry);

                vector.add(od);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return vector;
    }

    //Select
    public void listAllOrder() {
        String sql = "select * from Orders";

        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                int odId = rs.getInt(1);
                String cusId = rs.getString(2);
                int empId = rs.getInt(3);
                String odDate = rs.getString(4);
                String RequiredDate = rs.getString(5);
                String ShippedDate = rs.getString(6);
                int ShipVia = rs.getInt(7);
                double Freight = rs.getDouble(8);
                String ShipName = rs.getString(9);
                String ShipAddress = rs.getString(10);
                String ShipCity = rs.getString(11);
                String ShipRegion = rs.getString(12);
                String ShipPostalCode = rs.getString(13);
                String ShipCountry = rs.getString(14);

                Orders od = new Orders(odId, cusId, empId, odDate, RequiredDate,
                        ShippedDate, ShipVia, Freight, ShipName, ShipAddress,
                        ShipCity, ShipRegion, ShipPostalCode, ShipCountry);
                System.out.println(od);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    public int getcountorder(String cusID) {
        
        String sql = "select count(*) from Orders where CustomerID='"+ cusID+"'";
        int i =0;
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                i=rs.getInt(1);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return i;
    }

    public int[] getIdOrder(String idCus) {
        DAOOrders daoOrder = new DAOOrders();
        int leng=daoOrder.getcountorder(idCus);
        String sql = "select OrderID from Orders where CustomerID='"+idCus+"'";
        int[] arr = new int[leng];
        try {
            ResultSet rs = getData(sql);
            while(rs.next()) {
                    int i=0;
                    arr[i] = rs.getInt(1);
                    //thiet lap phan tu tai vi tri i la i + 100 
                    i++;
                
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return arr;
    }
      public Vector gettemp(String idCus) {
        DAOOrders daoOrder = new DAOOrders();
        int leng=daoOrder.getcountorder(idCus);
        Vector vec = new Vector();
        String sql = "select OrderID from Orders where CustomerID='"+idCus+"'";
        
        try {
            ResultSet rs = getData(sql);
            while(rs.next()) {
                   
                    vec.add(rs.getInt(1));
                    
                
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return vec;
    }

    //Main
    public static void main(String[] args) {
        DAOOrders daoOrder = new DAOOrders();
//        int n= daoOrder.IdbyUser("")
//        int n = daoOrder.addOrders(
//                new Orders("VINET", 1, "1996-07-04", "1996-08-01", "1996-07-16", 
//                        1, 50.5, "Vins et alcools Chevalier", 
//                        "59 rue de l'Abbaye", "Reims", "RJ", "1234", "France")
//        );
//        if(n > 0){
//            System.out.println("Inserted Data Orders");
//        }
        //Update
//        int up = daoOrder.updateOrders(
//                new Orders(11078,"PICCO", 1, "1996-07-04", "1996-08-01", "1996-07-16", 
//                        1, 100, "Vins et alcools Chevalier", 
//                        "59 rue de l'Abbaye", "Reims", "RJ", "1234", "France")
//        );
//        if(up > 0){
//            System.out.println("Updated Data Orders");
//        }
        //Delete
//        int dele = daoOrder.deleOrder(11078);
//        if(dele > 0){
//            System.out.println("Delete successful");
//        }
        //Select
        //int n=daoOrder.addOrderGetID( new Orders("34321", 0, "34321", "34321", "34321", "34321", "34321"));
//        System.out.println(daoOrder.getIdOrder("ALFKI"));
//    int[] n=daoOrder.getIdOrder("ALFKI");
//        for (int i = 0; i < n.length; i++) {
//            System.out.println(n[i]);
//            
//        }
//Search By Customer ID
        Vector search = daoOrder.gettemp("ALFKI");
        for (Object od : search) {
            System.out.println(od);
        }
    }
}
