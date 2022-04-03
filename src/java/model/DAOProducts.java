package model;

import entity.Products;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DAOProducts extends ConnectDB {

    //Insert
    public int addProducts(Products pro) {
        int n = 0;
        String sql = "insert into Products(ProductName,SupplierID,CategoryID,"
                + "QuantityPerUnit,UnitPrice,UnitsInStock,UnitsOnOrder,"
                + "ReorderLevel,Discontinued)"
                + " values(?,?,?,?,?,?,?,?,?)";

        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, pro.getProductName());
            pre.setInt(2, pro.getSupplierID());
            pre.setInt(3, pro.getCategoryID());
            pre.setString(4, pro.getQuantityPerUnit());
            pre.setDouble(5, pro.getUnitPrice());
            pre.setInt(6, pro.getUnitsInStock());
            pre.setInt(7, pro.getUnitsOnOrder());
            pre.setInt(8, pro.getReorderLevel());
            pre.setInt(9, pro.getDiscontinued());

            n = pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n;
    }

    //ChaneQuantity
    public int changeUnitsInStock(int id, int unitInStock) {
        int n = 0;
        String sql = "update Products set UnitsInStock=? where ProductID=?";

        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, unitInStock);
            pre.setInt(2, id);

            n = pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n;
    }

    //Update
    public int updateProducts(Products pro) {
        int n = 0;
        String sql = "update Products set ProductName = ?,SupplierID=?,CategoryID=?,"
                + "QuantityPerUnit=?,UnitPrice=?,UnitsInStock=?,UnitsOnOrder=?,"
                + "ReorderLevel=?,Discontinued=? where ProductID = ?";

        try {
            PreparedStatement pre = conn.prepareStatement(sql);

            pre.setString(1, pro.getProductName());
            pre.setInt(2, pro.getSupplierID());
            pre.setInt(3, pro.getCategoryID());
            pre.setString(4, pro.getQuantityPerUnit());
            pre.setDouble(5, pro.getUnitPrice());
            pre.setInt(6, pro.getUnitsInStock());
            pre.setInt(7, pro.getUnitsOnOrder());
            pre.setInt(8, pro.getReorderLevel());
            pre.setInt(9, pro.getDiscontinued());
            pre.setInt(10, pro.getProductID());

            n = pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n;
    }

    //Delete
    public int removeProduct(int id) {
        int n = 0;
        String sql = "delete from Products where ProductID=" + id;
        //Check foreign key constrain (if table is 1)

        try {
            Statement state = conn.createStatement();
            n = state.executeUpdate(sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n;
    }

    public Vector<Products> listCateID(int careID, int index, int spPerPag) {
        Vector<Products> vector = new Vector<Products>();
        String sql = "SELECT * FROM( select * from(select *,ROW_NUMBER() over (Order by ProductID ASC) as row_index  from (select * from Products where CategoryID ="+ careID +") as duct) pro)as a where row_index>=(?-1)*?+1 and row_index<=?*?";

        try {
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setInt(1, index);
            stm.setInt(2, spPerPag);
            stm.setInt(3, index);
            stm.setInt(4, spPerPag);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                int pId = rs.getInt("ProductID"); //rs.getInt(1);sq
                String pname = rs.getString(2);//rs.getString("ProductName");
                int subId = rs.getInt(3);
                int cateId = rs.getInt(4);
                String QuantityPerUnit = rs.getString(5);
                double UnitPrice = rs.getDouble(6);
                int UnitsInStock = rs.getInt(7);
                int UnitsOnOrder = rs.getInt(8);
                int ReorderLevel = rs.getInt(9);
                int Discontinued = rs.getInt(10);
                String img= rs.getString(11);
                Products pro = new Products(pId, pname, subId, cateId,
                        QuantityPerUnit, UnitPrice, UnitsInStock, UnitsOnOrder,
                        ReorderLevel, Discontinued,img);

                vector.add(pro);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return vector;
    }

    //Vector: Có threads safe
    public Vector<Products> searchByPrice(double from, double to) {
        Vector<Products> vector = new Vector<Products>();
        String sql = "select * from Products where UnitPrice between " + from
                + " and " + to;
        ResultSet rs = getData(sql);

        try {
            while (rs.next()) {
                int pId = rs.getInt("ProductID"); //rs.getInt(1);
                String pname = rs.getString(2);//rs.getString("ProductName");
                int subId = rs.getInt(3);
                int cateId = rs.getInt(4);
                String QuantityPerUnit = rs.getString(5);
                double UnitPrice = rs.getDouble(6);
                int UnitsInStock = rs.getInt(7);
                int UnitsOnOrder = rs.getInt(8);
                int ReorderLevel = rs.getInt(9);
                int Discontinued = rs.getInt(10);
                String img= rs.getString(11);
                Products pro = new Products(pId, pname, subId, cateId,
                        QuantityPerUnit, UnitPrice, UnitsInStock, UnitsOnOrder,
                        ReorderLevel, Discontinued,img);

                vector.add(pro);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return vector;
    }

    //Select = ResultSet use
    public Vector<Products> listAllProducts(String sql) {
        Vector<Products> vector = new Vector<Products>();
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                //rs.getDataType(fieldName,index=1)//Lấy dữ liệu cột
                int pId = rs.getInt("ProductID"); //rs.getInt(1);
                String pname = rs.getString(2);//rs.getString("ProductName");
                int subId = rs.getInt(3);
                int cateId = rs.getInt(4);
                String QuantityPerUnit = rs.getString(5);
                double UnitPrice = rs.getDouble(6);
                int UnitsInStock = rs.getInt(7);
                int UnitsOnOrder = rs.getInt(8);
                int ReorderLevel = rs.getInt(9);
                int Discontinued = rs.getInt(10);
                String img= rs.getString(11);
                Products pro = new Products(pId, pname, subId, cateId,
                        QuantityPerUnit, UnitPrice, UnitsInStock, UnitsOnOrder,
                        ReorderLevel, Discontinued,img);
                vector.add(pro);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return vector;
    }

    public ArrayList<Products> litAllProducts(String sql) {
        ArrayList<Products> vector = new ArrayList<>();
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                //rs.getDataType(fieldName,index=1)//Lấy dữ liệu cột
                int pId = rs.getInt("ProductID"); //rs.getInt(1);
                String pname = rs.getString(2);//rs.getString("ProductName");
                int subId = rs.getInt(3);
                int cateId = rs.getInt(4);
                String QuantityPerUnit = rs.getString(5);
                double UnitPrice = rs.getDouble(6);
                int UnitsInStock = rs.getInt(7);
                int UnitsOnOrder = rs.getInt(8);
                int ReorderLevel = rs.getInt(9);
                int Discontinued = rs.getInt(10);
                String img= rs.getString(11);
                Products pro = new Products(pId, pname, subId, cateId,
                        QuantityPerUnit, UnitPrice, UnitsInStock, UnitsOnOrder,
                        ReorderLevel, Discontinued,img);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return vector;
    }

    public Vector<Products> listAllProductsPage(int index, int spPerPag) {
        Vector<Products> vector = new Vector<Products>();
        String sql = "select * from\n"
                + "(select *,ROW_NUMBER() over (Order by ProductID ASC) as row_index  from Products) pro\n"
                + "where \n"
                + "row_index>=(?-1)*?+1 and row_index<=?*?";

        try {
            PreparedStatement stm = conn.prepareStatement(sql);

            stm.setInt(1, index);
            stm.setInt(2, spPerPag);
            stm.setInt(3, index);
            stm.setInt(4, spPerPag);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                //rs.getDataType(fieldName,index=1)//Lấy dữ liệu cột
                int pId = rs.getInt("ProductID"); //rs.getInt(1);
                String pname = rs.getString(2);//rs.getString("ProductName");
                int subId = rs.getInt(3);
                int cateId = rs.getInt(4);
                String QuantityPerUnit = rs.getString(5);
                double UnitPrice = rs.getDouble(6);
                int UnitsInStock = rs.getInt(7);
                int UnitsOnOrder = rs.getInt(8);
                int ReorderLevel = rs.getInt(9);
                int Discontinued = rs.getInt(10);
                String img= rs.getString(11);
                Products pro = new Products(pId, pname, subId, cateId,
                        QuantityPerUnit, UnitPrice, UnitsInStock, UnitsOnOrder,
                        ReorderLevel, Discontinued,img);
                vector.add(pro);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return vector;
    }

    public Vector<Products> listAllSeName(int index, int page, String productName) {
        Vector<Products> vector = new Vector<Products>();
        String sql = "select * from\n"
                + "(select *,ROW_NUMBER() over (Order by ProductID ASC) as row_index  from (select * from Products where ProductName LIKE '%" + productName + "%') as duct) pro\n"
                + "where \n"
                + "row_index>=(?-1)*?+1 and row_index<=?*?";

        try {
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setInt(1, index);
            stm.setInt(2, page);
            stm.setInt(3, index);
            stm.setInt(4, page);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                //rs.getDataType(fieldName,index=1)//Lấy dữ liệu cột
                int pId = rs.getInt("ProductID"); //rs.getInt(1);
                String pname = rs.getString(2);//rs.getString("ProductName");
                int subId = rs.getInt(3);
                int cateId = rs.getInt(4);
                String QuantityPerUnit = rs.getString(5);
                double UnitPrice = rs.getDouble(6);
                int UnitsInStock = rs.getInt(7);
                int UnitsOnOrder = rs.getInt(8);
                int ReorderLevel = rs.getInt(9);
                int Discontinued = rs.getInt(10);
                String img= rs.getString(11);
                Products pro = new Products(pId, pname, subId, cateId,
                        QuantityPerUnit, UnitPrice, UnitsInStock, UnitsOnOrder,
                        ReorderLevel, Discontinued,img);
                vector.add(pro);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return vector;
    }

    public ArrayList<Products> liAllProductsPageCateID(int index, int page) {
        ArrayList<Products> vector = new ArrayList<Products>();
        String sql = "select * from\n"
                + "(select *,ROW_NUMBER() over (Order by ProductID ASC) as row_index  from Products) pro\n"
                + "where \n"
                + "row_index>=(?-1)*?+1 and row_index<=?*?";

        try {
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setInt(1, index);
            stm.setInt(2, page);
            stm.setInt(3, index);
            stm.setInt(4, page);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                //rs.getDataType(fieldName,index=1)//Lấy dữ liệu cột
                int pId = rs.getInt("ProductID"); //rs.getInt(1);
                String pname = rs.getString(2);//rs.getString("ProductName");
                int subId = rs.getInt(3);
                int cateId = rs.getInt(4);
                String QuantityPerUnit = rs.getString(5);
                double UnitPrice = rs.getDouble(6);
                int UnitsInStock = rs.getInt(7);
                int UnitsOnOrder = rs.getInt(8);
                int ReorderLevel = rs.getInt(9);
                int Discontinued = rs.getInt(10);
                String img= rs.getString(11);
                Products pro = new Products(pId, pname, subId, cateId,
                        QuantityPerUnit, UnitPrice, UnitsInStock, UnitsOnOrder,
                        ReorderLevel, Discontinued,img);
                vector.add(pro);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return vector;
    }

    //SELECT COUNT(*) as Total FROM Products
    public int countTotalPro(String sql) {
        try {

            ResultSet rs = getData(sql);
            if (rs.next()) {
                return rs.getInt("Total");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return -1;
    }

    public int countTotalCateID(String productName) {
        try {
            String sql = "select * from\n"
                    + "(select *,ROW_NUMBER() over (Order by ProductID ASC) as row_index  from (select * from Products where ProductName LIKE '%" + productName + "%') as duct) pro";
            ResultSet rs = getData(sql);
            if (rs.next()) {
                return rs.getInt("Total");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return -1;
    }
    public int countTotalSearch(String productName) {
        try {
            String sql = "select COUNT(*) as Total from\n"
                    + "(select *,ROW_NUMBER() over (Order by ProductID ASC) as row_index  from (select * from Products where ProductName LIKE '%" + productName + "%') as duct) pro";
            ResultSet rs = getData(sql);
            if (rs.next()) {
                return rs.getInt("Total");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return -1;
    }

    public Products getProductById(int id) {
        String sql = "select * from Products where ProductID = " + id;
        ResultSet rs = getData(sql);
        try {
            if (rs.next()) {
                int pId = rs.getInt("ProductID"); //rs.getInt(1);
                String pname = rs.getString(2);//rs.getString("ProductName");
                int subId = rs.getInt(3);
                int cateId = rs.getInt(4);
                String QuantityPerUnit = rs.getString(5);
                double UnitPrice = rs.getDouble(6);
                int UnitsInStock = rs.getInt(7);
                int UnitsOnOrder = rs.getInt(8);
                int ReorderLevel = rs.getInt(9);
                int Discontinued = rs.getInt(10);
                String img= rs.getString(11);
                Products pro = new Products(pId, pname, subId, cateId,
                        QuantityPerUnit, UnitPrice, UnitsInStock, UnitsOnOrder,
                        ReorderLevel, Discontinued,img);
                return pro;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
     public int deleProduct(int id){
        int n = 0;
        String sql = "DELETE FROM Products  WHERE ProductID=" +id;
        try {
            Statement state = conn.createStatement();
            n = state.executeUpdate(sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n;
    }

   

    public void listAllProduct() {
        String sql = "select * from Products";
        try {
            //Cách gọi trực tiếp ResultSet
//            Statement state = conn.createStatement(ResultSet.TYPE_FORWARD_ONLY,
//                    ResultSet.CONCUR_READ_ONLY);
//            ResultSet rs = state.executeQuery(sql);

            //ResultSet.TYPE_FORWARD_ONLY: default
            //            TYPE_SCROLL_INSENSITIVE: No thread safe
            //            TYPE_SCROLL_SENSITIVE: Thread safe:Lập trình đa luồng (Không được dùng)
//            ResultSet.CONCUR_READ_ONLY: default(Không được sửa)
//            ResultSet.CONCUR_UPDATABLE
            //Cách gọi qua Hàm ResultSet getData(sql) bên trong ConnectDB
            ResultSet rs = getData(sql);
            while (rs.next()) {
                //rs.getDataType(fieldName,index=1)//Lấy dữ liệu cột
                int pId = rs.getInt("ProductID"); //rs.getInt(1);
                String pname = rs.getString(2);//rs.getString("ProductName");
                int subId = rs.getInt(3);
                int cateId = rs.getInt(4);
                String QuantityPerUnit = rs.getString(5);
                double UnitPrice = rs.getDouble(6);
                int UnitsInStock = rs.getInt(7);
                int UnitsOnOrder = rs.getInt(8);
                int ReorderLevel = rs.getInt(9);
                int Discontinued = rs.getInt(10);
                String img= rs.getString(11);
                Products pro = new Products(pId, pname, subId, cateId,
                        QuantityPerUnit, UnitPrice, UnitsInStock, UnitsOnOrder,
                        ReorderLevel, Discontinued,img);
                System.out.println(pro);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    //Main
    public static void main(String[] args) {
        DAOProducts dao = new DAOProducts();
//        System.out.println(dao.countTotalPro("SELECT COUNT(*) as Total FROM Products"));
        System.out.println(dao.countTotalSearch("sa"));
        
//        Vector<Products> vector = dao.listAllSeName(2, 9, "a");
//
//        for (Products products : vector) {
//            System.out.println(products);
//        }
////        
    }

}
