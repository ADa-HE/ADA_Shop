package model;

import entity.Categories;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

//insert into Categories(CategoryName, Description, Picture)
//values('Demo Categories', 'Demo Desciption Categories', 'Demo Picture Categories')

public class DAOCategories extends ConnectDB{
    //Insert
    public int addCategories(Categories cate){
        int n = 0;
        String sql = "insert into Categories(CategoryName, Description)" +
        " values(?,?)";
        try {
            //Create Statement
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, cate.getCategoryName());
            pre.setString(2, cate.getDescription());
            //run
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n;
    }
    
    //Update
    public int updateCategory(Categories cate){
        int n = 0;
        String sqlPre = "update Categories set CategoryName =?, "
                + "Description = ? where CategoryID = ?";
        
        try {
            PreparedStatement pre = conn.prepareStatement(sqlPre);
            //pre.setDataType(index, value);
            pre.setString(1, cate.getCategoryName());
            pre.setString(2, cate.getDescription());
            pre.setInt(3, cate.getCategoryID());
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
    //Delete
    public int deleCategory(int id){
        int n = 0;
        String sql = "delete from Categories where CategoryID=" +id;
        
        try {
            Statement state = conn.createStatement();
            n = state.executeUpdate(sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n;
    }
    //Display List All
    public void listAllCategory(){
        String sql = "select * from Categories";
        
        ResultSet rs = getData(sql);
        try {
            while(rs.next()){
                int cateId = rs.getInt("CategoryID");//rs.getInt(1)
                String cateName = rs.getString(2);
                String cateDes = rs.getString(3);
                String picture = rs.getString(4);
                
                Categories cate = new Categories(cateId, cateName, picture, picture);
                
                System.out.println(cate);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    public Vector<Categories> listAllCategories(String sql){
        Vector<Categories> vector = new Vector<Categories>();
        ResultSet rs = getData(sql);
        try {
            while(rs.next()){
                int cateId = rs.getInt("CategoryID");//rs.getInt(1)
                String cateName = rs.getString(2);
                String cateDes = rs.getString(3);
                String picture = rs.getString(4);
                
                Categories cate = new Categories(cateId, cateName, cateDes);
                vector.add(cate);
                
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return vector;
    }
    //Main
    public static void main(String[] args) {
        DAOCategories daoCate = new DAOCategories();
        //Insert 
//        int n = daoCate.addCategories(
//                new Categories("Demo Name", "Demo Des")
//        );
//        if(n > 0){
//            System.out.println("Inserted Data Categories");
//        }
        //Update
//        int up = daoCate.updateCategory(
//                new Categories(10, "Update Name", "Update Des"));
//        if(up > 0){
//            System.out.println("Update Data Categories");
//        }
        //Select List All
//        daoCate.listAllCategory();
        //Delete Category
        int dele = daoCate.updateCategory( new Categories(1, "23", "2"));
        if(dele > 0){System.out.println("Delete Successfull");}
    }
}
