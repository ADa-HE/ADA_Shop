package model;

import entity.Shippers;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

//insert into Shippers(CompanyName, Phone)
//values ('Cong Ty Nam Tien', '0353890333')
public class DAOShippers extends ConnectDB{
    //Insert
    public int addShippers(Shippers ship){
        int n = 0;
        String sql = "insert into Shippers(CompanyName,Phone)" 
                +"values (?,?)";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, ship.getCompanyName());
            pre.setString(2, ship.getPhone());
            n =pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n;
    }
    //Update
    public int updateShippers(Shippers ship){
        int n = 0;
        String sql = "update Shippers set CompanyName=? ,Phone=? where ShipperID=?" ;
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, ship.getCompanyName());
            pre.setString(2, ship.getPhone());
            pre.setInt(3, ship.getShipperID());
            n =pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n;
    }
    //Delete
    public int deleShipper(int id){
       int n = 0;
        String sql = "delete from Shippers where ShipperID=" +id;
        
        try {
            Statement state = conn.createStatement();
            n = state.executeUpdate(sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n; 
    }
    //Select list All
    public void listAllShipper(){
        String sql = "select * from Shippers";
        
        ResultSet rs = getData(sql);
        try {
            while(rs.next()){
                int shipperId = rs.getInt("ShipperID");
                String companyName = rs.getString(2);
                String Phone = rs.getString(3);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    public Vector<Shippers> listAllShipper(String sql){
        Vector<Shippers> vector = new Vector<>();
        
        ResultSet rs = getData(sql);
        try {
            while(rs.next()){
                int shipperId = rs.getInt("ShipperID");
                String companyName = rs.getString(2);
                String Phone = rs.getString(3);
                Shippers ship = new Shippers(shipperId, companyName, Phone);
                vector.add(ship);
                
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return vector;
    }
    //Main
    public static void main(String[] args) {
        DAOShippers daoShip = new DAOShippers();
//        int n = daoShip.addShippers(
//                new Shippers("Cong Ty Nam Tien", "0353890333")
//        );
//        if(n > 0){
//            System.out.println("Inserted Data Shippers");
//        }
        //Update
//        int up = daoShip.updateShippers(
//                new Shippers(4, "Cong Ty Bao Hao", "0353890333")
//        );
//        if(up > 0){
//            System.out.println("Update Data Shippers");
//        }
        //Delete
//        int dele = daoShip.deleShipper(7);
    }
}
