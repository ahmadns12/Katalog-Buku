/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.altriaa.buku.DAO;

import com.altriaa.buku.Model.Product;
import java.util.List;
/**
 *
 * @author User
 */
public interface ProductDAO {
    
    public void insert(Product product);
    public void update(Product product);
    public void delete(Product product);
    public Product getProduct(int productId);
    public Product getProductWithID(int productId);
    public Product getProductWithName(String judul);
    public List<Product> list();
    public List<Product> kuliner();
    public List<Product> selfImprovement();
    public List<Product> filosofi();
    public List<Product> kriminal();
    public List<Product> misteri();
    public List<Product> fantasi();
    public List<Product> horor();
    public List<Product> manga();
    public List<Product> puisi();
    public List<Product> drama();
    public List<Product> komedi();
    public List<Product> listByID(int productId);
    public List<Product> listByName(int productId);
}
