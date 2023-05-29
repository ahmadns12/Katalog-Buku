/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.altriaa.buku.DAO;

import com.altriaa.buku.DB.ConnectionDataBase;
import com.altriaa.buku.Model.Product;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
/**
 *
 * @author User
 */
public class ProductDAOImp {
    public void insert(Product product) {
        try {
            Connection conDB =  ConnectionDataBase.getConnection();
            String sql = "INSERT INTO tbl_buku (genre,judul,pengarang,penerbit,harga,jilid) VALUES (?,?,?,?,?,?)";
            PreparedStatement ps = conDB.prepareStatement(sql);
            ps.setString(1, product.getGenre());
            ps.setString(2, product.getJudul());
            ps.setString(3, product.getPengarang());
            ps.setString(4, product.getPenerbit());
            ps.setDouble(5, product.getHarga());
            ps.setInt(6, product.getJilid());
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data has been saved");
        }  
        catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Cannot insert into table");
        }
    }   

    public void update(Product product) {
        try {
            Connection conDB =  ConnectionDataBase.getConnection();
            String sql = "UPDATE tbl_buku SET genre=?, judul=?, pengarang=?, penerbit=?, harga=?, jilid=? WHERE productId=?";
            PreparedStatement ps = conDB.prepareStatement(sql);
            ps.setString(1, product.getGenre());
            ps.setString(2, product.getJudul());
            ps.setString(3, product.getPengarang());
            ps.setString(4, product.getPenerbit());
            ps.setDouble(5, product.getHarga());
            ps.setInt(6, product.getJilid());
            ps.setInt(7, product.getProductId());
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data has been update");
        }  
        catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Cannot update into table");
        }
    }   

    public void delete(Product product) {
        try {
            Connection conDB =  ConnectionDataBase.getConnection();
            String sql = "DELETE FROM tbl_buku WHERE productId=?";
            PreparedStatement ps = conDB.prepareStatement(sql);
            ps.setInt(1, product.getProductId());
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data has been delete");
        }  
        catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Cannot update into table");
        }
    }   

    public Product getProduct(int productId) {
        Product product = new Product();
        try {
            Connection conDB = ConnectionDataBase.getConnection();
            String sql = "SELECT * FROM tbl_buku WHERE productId=?";
            PreparedStatement ps = conDB.prepareStatement(sql);
            ps.setInt(1, productId);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                product.setProductId(rs.getInt("productId"));
                product.setGenre(rs.getString("genre"));
                product.setJudul(rs.getString("judul"));
                product.setPengarang(rs.getString("pengarang"));
                product.setPenerbit(rs.getString("penerbit"));
                product.setHarga(rs.getInt("harga"));
                product.setJilid(rs.getInt("jilid"));
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Cannot get data from table by using productId");
        }
        return product;
    }
    
    public Product getProductWithID(int productId) {
        Product product = new Product();
        try {
            Connection conDB = ConnectionDataBase.getConnection();
            String sql = "SELECT * FROM tbl_buku WHERE productId=?";
            PreparedStatement ps = conDB.prepareStatement(sql);
            ps.setInt(1, productId);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                product.setProductId(rs.getInt("productId"));
                product.setGenre(rs.getString("genre"));
                product.setJudul(rs.getString("judul"));
                product.setPengarang(rs.getString("pengarang"));
                product.setPenerbit(rs.getString("penerbit"));
                product.setHarga(rs.getInt("harga"));
                product.setJilid(rs.getInt("jilid"));
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Cannot get data from table by using productId");
        }
        return product;
    }
    
    public Product getProductWithName(String judul) {
    Product product = new Product();
    try {
        Connection conDB = ConnectionDataBase.getConnection();
        String sql = "SELECT * FROM tbl_buku WHERE judul LIKE ?";
        PreparedStatement ps = conDB.prepareStatement(sql);
        ps.setString(1, "%" + judul + "%");
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            product.setProductId(rs.getInt("productId"));
            product.setGenre(rs.getString("genre"));
            product.setJudul(rs.getString("judul"));
            product.setPengarang(rs.getString("pengarang"));
            product.setPenerbit(rs.getString("penerbit"));
            product.setHarga(rs.getInt("harga"));
            product.setJilid(rs.getInt("jilid"));
        }
    } catch (Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Cannot get data from table by using name");
    }
    return product;
}
    
    //    View per genre
    public List<Product> kuliner() {
      
        List<Product> listProduct = new ArrayList<Product>();

        try{
            Connection conDB = ConnectionDataBase.getConnection();
            String sql = "SELECT * FROM view_genre_kuliner";
            PreparedStatement ps = conDB.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                Product product = new Product();
                product.setProductId(rs.getInt("productId"));
                product.setGenre(rs.getString("genre"));
                product.setJudul(rs.getString("judul"));
                product.setPengarang(rs.getString("pengarang"));
                product.setPenerbit(rs.getString("penerbit"));
                product.setHarga(rs.getInt("harga"));
                product.setJilid(rs.getInt("jilid"));
 
                listProduct.add(product);
            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Cannot get data from table");
        }
        return listProduct;
    }
    
    public List<Product> selfImprovement() {
      
        List<Product> listProduct = new ArrayList<Product>();

        try{
            Connection conDB = ConnectionDataBase.getConnection();
            String sql = "SELECT * FROM view_genre_self_improvement";
            PreparedStatement ps = conDB.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                Product product = new Product();
                product.setProductId(rs.getInt("productId"));
                product.setGenre(rs.getString("genre"));
                product.setJudul(rs.getString("judul"));
                product.setPengarang(rs.getString("pengarang"));
                product.setPenerbit(rs.getString("penerbit"));
                product.setHarga(rs.getInt("harga"));
                product.setJilid(rs.getInt("jilid"));
 
                listProduct.add(product);
            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Cannot get data from table");
        }
        return listProduct;
    }
    
    public List<Product> filosofi() {
      
        List<Product> listProduct = new ArrayList<Product>();

        try{
            Connection conDB = ConnectionDataBase.getConnection();
            String sql = "SELECT * FROM view_genre_filosofi";
            PreparedStatement ps = conDB.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                Product product = new Product();
                product.setProductId(rs.getInt("productId"));
                product.setGenre(rs.getString("genre"));
                product.setJudul(rs.getString("judul"));
                product.setPengarang(rs.getString("pengarang"));
                product.setPenerbit(rs.getString("penerbit"));
                product.setHarga(rs.getInt("harga"));
                product.setJilid(rs.getInt("jilid"));
 
                listProduct.add(product);
            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Cannot get data from table");
        }
        return listProduct;
    }
    
    public List<Product> kriminal() {
      
        List<Product> listProduct = new ArrayList<Product>();

        try{
            Connection conDB = ConnectionDataBase.getConnection();
            String sql = "SELECT * FROM view_genre_kriminal";
            PreparedStatement ps = conDB.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                Product product = new Product();
                product.setProductId(rs.getInt("productId"));
                product.setGenre(rs.getString("genre"));
                product.setJudul(rs.getString("judul"));
                product.setPengarang(rs.getString("pengarang"));
                product.setPenerbit(rs.getString("penerbit"));
                product.setHarga(rs.getInt("harga"));
                product.setJilid(rs.getInt("jilid"));
 
                listProduct.add(product);
            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Cannot get data from table");
        }
        return listProduct;
    }
    
    public List<Product> misteri() {
      
        List<Product> listProduct = new ArrayList<Product>();

        try{
            Connection conDB = ConnectionDataBase.getConnection();
            String sql = "SELECT * FROM view_genre_misteri";
            PreparedStatement ps = conDB.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                Product product = new Product();
                product.setProductId(rs.getInt("productId"));
                product.setGenre(rs.getString("genre"));
                product.setJudul(rs.getString("judul"));
                product.setPengarang(rs.getString("pengarang"));
                product.setPenerbit(rs.getString("penerbit"));
                product.setHarga(rs.getInt("harga"));
                product.setJilid(rs.getInt("jilid"));
 
                listProduct.add(product);
            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Cannot get data from table");
        }
        return listProduct;
    }
    
    public List<Product> fantasi() {
      
        List<Product> listProduct = new ArrayList<Product>();

        try{
            Connection conDB = ConnectionDataBase.getConnection();
            String sql = "SELECT * FROM view_genre_fantasi";
            PreparedStatement ps = conDB.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                Product product = new Product();
                product.setProductId(rs.getInt("productId"));
                product.setGenre(rs.getString("genre"));
                product.setJudul(rs.getString("judul"));
                product.setPengarang(rs.getString("pengarang"));
                product.setPenerbit(rs.getString("penerbit"));
                product.setHarga(rs.getInt("harga"));
                product.setJilid(rs.getInt("jilid"));
 
                listProduct.add(product);
            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Cannot get data from table");
        }
        return listProduct;
    }
    
    public List<Product> horor() {
      
        List<Product> listProduct = new ArrayList<Product>();

        try{
            Connection conDB = ConnectionDataBase.getConnection();
            String sql = "SELECT * FROM view_genre_horor";
            PreparedStatement ps = conDB.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                Product product = new Product();
                product.setProductId(rs.getInt("productId"));
                product.setGenre(rs.getString("genre"));
                product.setJudul(rs.getString("judul"));
                product.setPengarang(rs.getString("pengarang"));
                product.setPenerbit(rs.getString("penerbit"));
                product.setHarga(rs.getInt("harga"));
                product.setJilid(rs.getInt("jilid"));
 
                listProduct.add(product);
            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Cannot get data from table");
        }
        return listProduct;
    }
    
    public List<Product> manga() {
      
        List<Product> listProduct = new ArrayList<Product>();

        try{
            Connection conDB = ConnectionDataBase.getConnection();
            String sql = "SELECT * FROM view_genre_manga";
            PreparedStatement ps = conDB.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                Product product = new Product();
                product.setProductId(rs.getInt("productId"));
                product.setGenre(rs.getString("genre"));
                product.setJudul(rs.getString("judul"));
                product.setPengarang(rs.getString("pengarang"));
                product.setPenerbit(rs.getString("penerbit"));
                product.setHarga(rs.getInt("harga"));
                product.setJilid(rs.getInt("jilid"));
 
                listProduct.add(product);
            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Cannot get data from table");
        }
        return listProduct;
    }
    
    public List<Product> puisi() {
      
        List<Product> listProduct = new ArrayList<Product>();

        try{
            Connection conDB = ConnectionDataBase.getConnection();
            String sql = "SELECT * FROM view_genre_puisi";
            PreparedStatement ps = conDB.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                Product product = new Product();
                product.setProductId(rs.getInt("productId"));
                product.setGenre(rs.getString("genre"));
                product.setJudul(rs.getString("judul"));
                product.setPengarang(rs.getString("pengarang"));
                product.setPenerbit(rs.getString("penerbit"));
                product.setHarga(rs.getInt("harga"));
                product.setJilid(rs.getInt("jilid"));
 
                listProduct.add(product);
            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Cannot get data from table");
        }
        return listProduct;
    }
    
    public List<Product> drama() {
      
        List<Product> listProduct = new ArrayList<Product>();

        try{
            Connection conDB = ConnectionDataBase.getConnection();
            String sql = "SELECT * FROM view_genre_drama";
            PreparedStatement ps = conDB.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                Product product = new Product();
                product.setProductId(rs.getInt("productId"));
                product.setGenre(rs.getString("genre"));
                product.setJudul(rs.getString("judul"));
                product.setPengarang(rs.getString("pengarang"));
                product.setPenerbit(rs.getString("penerbit"));
                product.setHarga(rs.getInt("harga"));
                product.setJilid(rs.getInt("jilid"));
 
                listProduct.add(product);
            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Cannot get data from table");
        }
        return listProduct;
    }
    
    public List<Product> komedi() {
      
        List<Product> listProduct = new ArrayList<Product>();

        try{
            Connection conDB = ConnectionDataBase.getConnection();
            String sql = "SELECT * FROM view_genre_komedi";
            PreparedStatement ps = conDB.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                Product product = new Product();
                product.setProductId(rs.getInt("productId"));
                product.setGenre(rs.getString("genre"));
                product.setJudul(rs.getString("judul"));
                product.setPengarang(rs.getString("pengarang"));
                product.setPenerbit(rs.getString("penerbit"));
                product.setHarga(rs.getInt("harga"));
                product.setJilid(rs.getInt("jilid"));
 
                listProduct.add(product);
            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Cannot get data from table");
        }
        return listProduct;
    }

    public List<Product> list() {
      
        List<Product> listProduct = new ArrayList<Product>();

        try{
            Connection conDB = ConnectionDataBase.getConnection();
            String sql = "SELECT * FROM tbl_buku";
            PreparedStatement ps = conDB.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                Product product = new Product();
                product.setProductId(rs.getInt("productId"));
                product.setGenre(rs.getString("genre"));
                product.setJudul(rs.getString("judul"));
                product.setPengarang(rs.getString("pengarang"));
                product.setPenerbit(rs.getString("penerbit"));
                product.setHarga(rs.getInt("harga"));
                product.setJilid(rs.getInt("jilid"));
 
                listProduct.add(product);
            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Cannot get data from table");
        }
        return listProduct;
    }


    public List<Product> listByID(int productId) {

        List<Product> listProduct = new ArrayList<Product>();

        try{
            Connection conDB = ConnectionDataBase.getConnection();
            String sql = "SELECT * FROM tbl_buku WHERE productId=?";
            PreparedStatement ps = conDB.prepareStatement(sql);
            ps.setInt(1, productId);
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                Product product = new Product();
                product.setProductId(rs.getInt("productId"));
                product.setGenre(rs.getString("genre"));
                product.setJudul(rs.getString("judul"));
                product.setPengarang(rs.getString("pengarang"));
                product.setPenerbit(rs.getString("penerbit"));
                product.setHarga(rs.getInt("harga"));
                product.setJilid(rs.getInt("jilid"));

                listProduct.add(product);
            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Cannot get data from table");
        }
        return listProduct;
    }
    
    public List<Product> listByName(String judul) {

        List<Product> listProduct = new ArrayList<Product>();

        try{
            Connection conDB = ConnectionDataBase.getConnection();
            String sql = "SELECT * FROM tbl_buku WHERE judul like ?";
            PreparedStatement ps = conDB.prepareStatement(sql);
            ps.setString(1,  "%" + judul + "%");
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                Product product = new Product();
                product.setProductId(rs.getInt("productId"));
                product.setGenre(rs.getString("genre"));
                product.setJudul(rs.getString("judul"));
                product.setPengarang(rs.getString("pengarang"));
                product.setPenerbit(rs.getString("penerbit"));
                product.setHarga(rs.getInt("harga"));
                product.setJilid(rs.getInt("jilid"));

                listProduct.add(product);
            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Cannot get data from table");
        }
        return listProduct;
    }

}
