/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.altriaa.buku.Model;

/**
 *
 * @author User
 */
public class Product {
    private int productId;
    private String genre;
    private String judul;
    private String pengarang;
    private String penerbit;
    private double harga;
    private int jilid;

    public int getProductId() {
        return productId;
    }
 
    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getGenre() {
        return genre;
    }
 
    public void setGenre(String Genre) {
        this.genre = Genre;
    }

    public String getJudul() {
        return judul;
    }
 
    public void setJudul(String Judul) {
        this.judul = Judul;
    }
    
     public String getPengarang() {
        return pengarang;
    }
 
    public void setPengarang(String Pengarang) {
        this.pengarang = Pengarang;
    }
    
     public String getPenerbit() {
        return penerbit;
    }
 
    public void setPenerbit(String Penerbit) {
        this.penerbit = Penerbit;
    }
        
    public double getHarga() {
        return harga;
    }
 
    public void setHarga(double Harga) {
        this.harga = Harga;
    }

    public int getJilid() {
        return jilid;
    }
 
    public void setJilid(int Jilid) {
        this.jilid = Jilid;
    }

}