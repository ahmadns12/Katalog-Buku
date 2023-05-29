/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.altriaa.buku.Controller;

import com.altriaa.buku.View.ProductForm;
import com.altriaa.buku.View.ProductFormUser;
import com.altriaa.buku.Model.Product;
import com.altriaa.buku.DAO.ProductDAOImp;
import javax.swing.JOptionPane;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Johannes Alexander Putra, CSCU
 */
public class ProductController {
    
    
    public void simpan(ProductForm form){
        try {
            Product product = new Product();
            String genre = form.txtGenre.getText();
            String judul = form.txtJudul.getText();
            String pengarang = form.txtPengarang.getText();
            String penerbit = form.txtPenerbit.getText();
            double harga = Double.parseDouble(form.txtHarga.getText());
            int jilid = Integer.parseInt(form.txtJilid.getText());

            product.setGenre(genre);
            product.setJudul(judul);
            product.setPengarang(pengarang);
            product.setPenerbit(penerbit);
            product.setHarga(harga);
            product.setJilid(jilid);

            ProductDAOImp dao = new ProductDAOImp();
            dao.insert(product);
            loadDB(form);
            clearTextField(form);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
     public void loadDB(ProductForm form)
    {
        try {
            ProductDAOImp productDAO = new ProductDAOImp();
            List<Product> listProduct = productDAO.list();
            DefaultTableModel DFT = (DefaultTableModel) form.tableProduct.getModel();
            DFT.setRowCount(0);

            for(Product product: listProduct)
            {
                int productId = product.getProductId();
                String genre = product.getGenre();
                String judul = product.getJudul();
                String pengarang = product.getPengarang();
                String penerbit = product.getPenerbit();
                double harga = product.getHarga();
                int jilid = product.getJilid();
                DFT.addRow(new Object[]{productId,genre,judul,pengarang,penerbit,harga,jilid});
            } 

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
     public void loadDBByName(ProductForm form,String title){
         
          try {
            ProductDAOImp productDAO = new ProductDAOImp();
            List<Product> listProduct = productDAO.listByName(title);
            DefaultTableModel DFT = (DefaultTableModel) form.tableProduct.getModel();
            DFT.setRowCount(0);

            for(Product product: listProduct)
            {
                int IDproduct = product.getProductId();
                String genre = product.getGenre();
                String judul = product.getJudul();
                String pengarang = product.getPengarang();
                String penerbit = product.getPenerbit();
                double harga = product.getHarga();
                int jilid = product.getJilid();
                DFT.addRow(new Object[]{IDproduct,genre,judul,pengarang,penerbit,harga,jilid});
            } 

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
     }
     public void delete(ProductForm form){
          // TODO add your handling code here:
        try {

            int productId = Integer.parseInt(form.txtIDProduct.getText());
            String judul = form.txtJudul.getText();

            int pesan = JOptionPane.showOptionDialog(form, "Anda yakin akan menghapus buku " +judul+ "?", "Konfirmasi Hapus Produk", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
            if(pesan == JOptionPane.YES_OPTION){
                Product product = new Product();
                product.setProductId(productId);
                ProductDAOImp dao = new ProductDAOImp();
                dao.delete(product);
                loadDB(form);
                clearTextField(form);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
     }
     public void clearTextField(ProductForm form){
        
        form.txtIDProduct.setText("");
        form.txtGenre.setText("");
        form.txtJudul.setText("");
        form.txtPengarang.setText("");
        form.txtPenerbit.setText("");
        form.txtHarga.setText("");
        form.txtJilid.setText("");
         
     }
     public void edit(ProductForm form){
          // TODO add your handling code here:
        try {
            Product product = new Product();
            int productId = Integer.parseInt(form.txtIDProduct.getText());
            String genre = form.txtGenre.getText();
            String judul = form.txtJudul.getText();
            String pengarang = form.txtPengarang.getText();
            String penerbit = form.txtPenerbit.getText();
            double harga = Double.parseDouble(form.txtHarga.getText());
            int jilid = Integer.parseInt(form.txtJilid.getText());
            product.setGenre(genre);
            product.setJudul(judul);
            product.setPengarang(pengarang);
            product.setPenerbit(penerbit);
            product.setHarga(harga);
            product.setJilid(jilid);
            product.setProductId(productId);
        
            ProductDAOImp dao = new ProductDAOImp();
            dao.update(product);
            loadDB(form);
            clearTextField(form);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
     }
     public void cari(ProductForm form){
          // TODO add your handling code here:
        try {
            int productId = Integer.parseInt(JOptionPane.showInputDialog("Masukan ID Produk"));
            ProductDAOImp dao = new ProductDAOImp();
            Product productDAO = dao.getProduct(productId);
            loadDBByID(form,productId);
            form.txtIDProduct.setText(String.valueOf(productDAO.getProductId()));            
            form.txtGenre.setText(productDAO.getGenre());
            form.txtJudul.setText(productDAO.getJudul());
            form.txtPengarang.setText(productDAO.getPengarang());
            form.txtPenerbit.setText(productDAO.getPenerbit());
            form.txtHarga.setText(String.format("%.0f", productDAO.getHarga()));
            form.txtJilid.setText(String.valueOf(productDAO.getJilid()));

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
     }
     public void exit(ProductForm form){
         try {
             if (JOptionPane.showConfirmDialog(null, "Konfigurasi jika anda ingin keluar","Katalog Produk",
                     JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION)
             {
                 System.exit(0);
             }
         } catch (Exception x) {
             JOptionPane.showMessageDialog(null, x);
         }
     }
     public void click(ProductForm form){
         try {

            int row =form.tableProduct.getSelectedRow();
            String dataKlik=(form.tableProduct.getModel().getValueAt(row, 0).toString());
            int productId = Integer.parseInt(dataKlik);

            ProductDAOImp dao = new ProductDAOImp();
            Product productDAO = dao.getProduct(productId);

            form.txtIDProduct.setText(String.valueOf(productDAO.getProductId()));            
            form.txtGenre.setText(productDAO.getGenre());
            form.txtJudul.setText(productDAO.getJudul());
            form.txtPengarang.setText(productDAO.getPengarang());
            form.txtPenerbit.setText(productDAO.getPenerbit());
            form.txtHarga.setText(String.format("%.0f", productDAO.getHarga()));
            form.txtJilid.setText(String.valueOf(productDAO.getJilid()));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
     }
     public void loadDBByID(ProductForm form,int productId){
         
          try {
            ProductDAOImp productDAO = new ProductDAOImp();
            List<Product> listProduct = productDAO.listByID(productId);
            DefaultTableModel DFT = (DefaultTableModel) form.tableProduct.getModel();
            DFT.setRowCount(0);

            for(Product product: listProduct)
            {
                int IDproduct = product.getProductId();
                String genre = product.getGenre();
                String judul = product.getJudul();
                String pengarang = product.getPengarang();
                String penerbit = product.getPenerbit();
                double harga = product.getHarga();
                int jilid = product.getJilid();
                DFT.addRow(new Object[]{IDproduct,genre,judul,pengarang,penerbit,harga,jilid});
            } 

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
     }
     
     //     View per genre
    public void loadKuliner(ProductForm form)
    {
        try {
            ProductDAOImp productDAO = new ProductDAOImp();
            List<Product> listProduct = productDAO.kuliner();
            DefaultTableModel DFT = (DefaultTableModel) form.tableProduct.getModel();
            DFT.setRowCount(0);

            for(Product product: listProduct)
            {
                int productId = product.getProductId();
                String genre = product.getGenre();
                String judul = product.getJudul();
                String pengarang = product.getPengarang();
                String penerbit = product.getPenerbit();
                double harga = product.getHarga();
                int jilid = product.getJilid();
                DFT.addRow(new Object[]{productId,genre,judul,pengarang,penerbit,harga,jilid});
            } 

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }


public void loadSelfImprovement(ProductForm form)
    {
        try {
            ProductDAOImp productDAO = new ProductDAOImp();
            List<Product> listProduct = productDAO.selfImprovement();
            DefaultTableModel DFT = (DefaultTableModel) form.tableProduct.getModel();
            DFT.setRowCount(0);

            for(Product product: listProduct)
            {
                int productId = product.getProductId();
                String genre = product.getGenre();
                String judul = product.getJudul();
                String pengarang = product.getPengarang();
                String penerbit = product.getPenerbit();
                double harga = product.getHarga();
                int jilid = product.getJilid();
                DFT.addRow(new Object[]{productId,genre,judul,pengarang,penerbit,harga,jilid});
            } 

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

public void loadFilosofi(ProductForm form)
    {
        try {
            ProductDAOImp productDAO = new ProductDAOImp();
            List<Product> listProduct = productDAO.filosofi();
            DefaultTableModel DFT = (DefaultTableModel) form.tableProduct.getModel();
            DFT.setRowCount(0);

            for(Product product: listProduct)
            {
                int productId = product.getProductId();
                String genre = product.getGenre();
                String judul = product.getJudul();
                String pengarang = product.getPengarang();
                String penerbit = product.getPenerbit();
                double harga = product.getHarga();
                int jilid = product.getJilid();
                DFT.addRow(new Object[]{productId,genre,judul,pengarang,penerbit,harga,jilid});
            } 

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

public void loadKriminal(ProductForm form)
    {
        try {
            ProductDAOImp productDAO = new ProductDAOImp();
            List<Product> listProduct = productDAO.kriminal();
            DefaultTableModel DFT = (DefaultTableModel) form.tableProduct.getModel();
            DFT.setRowCount(0);

            for(Product product: listProduct)
            {
                int productId = product.getProductId();
                String genre = product.getGenre();
                String judul = product.getJudul();
                String pengarang = product.getPengarang();
                String penerbit = product.getPenerbit();
                double harga = product.getHarga();
                int jilid = product.getJilid();
                DFT.addRow(new Object[]{productId,genre,judul,pengarang,penerbit,harga,jilid});
            } 

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

public void loadMisteri(ProductForm form)
    {
        try {
            ProductDAOImp productDAO = new ProductDAOImp();
            List<Product> listProduct = productDAO.misteri();
            DefaultTableModel DFT = (DefaultTableModel) form.tableProduct.getModel();
            DFT.setRowCount(0);

            for(Product product: listProduct)
            {
                int productId = product.getProductId();
                String genre = product.getGenre();
                String judul = product.getJudul();
                String pengarang = product.getPengarang();
                String penerbit = product.getPenerbit();
                double harga = product.getHarga();
                int jilid = product.getJilid();
                DFT.addRow(new Object[]{productId,genre,judul,pengarang,penerbit,harga,jilid});
            } 

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

public void loadFantasi(ProductForm form)
    {
        try {
            ProductDAOImp productDAO = new ProductDAOImp();
            List<Product> listProduct = productDAO.fantasi();
            DefaultTableModel DFT = (DefaultTableModel) form.tableProduct.getModel();
            DFT.setRowCount(0);

            for(Product product: listProduct)
            {
                int productId = product.getProductId();
                String genre = product.getGenre();
                String judul = product.getJudul();
                String pengarang = product.getPengarang();
                String penerbit = product.getPenerbit();
                double harga = product.getHarga();
                int jilid = product.getJilid();
                DFT.addRow(new Object[]{productId,genre,judul,pengarang,penerbit,harga,jilid});
            } 

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

public void loadHoror(ProductForm form)
    {
        try {
            ProductDAOImp productDAO = new ProductDAOImp();
            List<Product> listProduct = productDAO.horor();
            DefaultTableModel DFT = (DefaultTableModel) form.tableProduct.getModel();
            DFT.setRowCount(0);

            for(Product product: listProduct)
            {
                int productId = product.getProductId();
                String genre = product.getGenre();
                String judul = product.getJudul();
                String pengarang = product.getPengarang();
                String penerbit = product.getPenerbit();
                double harga = product.getHarga();
                int jilid = product.getJilid();
                DFT.addRow(new Object[]{productId,genre,judul,pengarang,penerbit,harga,jilid});
            } 

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

public void loadManga(ProductForm form)
    {
        try {
            ProductDAOImp productDAO = new ProductDAOImp();
            List<Product> listProduct = productDAO.manga();
            DefaultTableModel DFT = (DefaultTableModel) form.tableProduct.getModel();
            DFT.setRowCount(0);

            for(Product product: listProduct)
            {
                int productId = product.getProductId();
                String genre = product.getGenre();
                String judul = product.getJudul();
                String pengarang = product.getPengarang();
                String penerbit = product.getPenerbit();
                double harga = product.getHarga();
                int jilid = product.getJilid();
                DFT.addRow(new Object[]{productId,genre,judul,pengarang,penerbit,harga,jilid});
            } 

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

public void loadPuisi(ProductForm form)
    {
        try {
            ProductDAOImp productDAO = new ProductDAOImp();
            List<Product> listProduct = productDAO.puisi();
            DefaultTableModel DFT = (DefaultTableModel) form.tableProduct.getModel();
            DFT.setRowCount(0);

            for(Product product: listProduct)
            {
                int productId = product.getProductId();
                String genre = product.getGenre();
                String judul = product.getJudul();
                String pengarang = product.getPengarang();
                String penerbit = product.getPenerbit();
                double harga = product.getHarga();
                int jilid = product.getJilid();
                DFT.addRow(new Object[]{productId,genre,judul,pengarang,penerbit,harga,jilid});
            } 

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

public void loadDrama(ProductForm form)
    {
        try {
            ProductDAOImp productDAO = new ProductDAOImp();
            List<Product> listProduct = productDAO.drama();
            DefaultTableModel DFT = (DefaultTableModel) form.tableProduct.getModel();
            DFT.setRowCount(0);

            for(Product product: listProduct)
            {
                int productId = product.getProductId();
                String genre = product.getGenre();
                String judul = product.getJudul();
                String pengarang = product.getPengarang();
                String penerbit = product.getPenerbit();
                double harga = product.getHarga();
                int jilid = product.getJilid();
                DFT.addRow(new Object[]{productId,genre,judul,pengarang,penerbit,harga,jilid});
            } 

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

public void loadKomedi(ProductForm form)
    {
        try {
            ProductDAOImp productDAO = new ProductDAOImp();
            List<Product> listProduct = productDAO.komedi();
            DefaultTableModel DFT = (DefaultTableModel) form.tableProduct.getModel();
            DFT.setRowCount(0);

            for(Product product: listProduct)
            {
                int productId = product.getProductId();
                String genre = product.getGenre();
                String judul = product.getJudul();
                String pengarang = product.getPengarang();
                String penerbit = product.getPenerbit();
                double harga = product.getHarga();
                int jilid = product.getJilid();
                DFT.addRow(new Object[]{productId,genre,judul,pengarang,penerbit,harga,jilid});
            } 

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }


public void opsiGeneral(ProductForm form){
    try{
        loadDB(form);
        clearTextField(form);
    } catch (Exception e){
        JOptionPane.showMessageDialog(null, e);
    }
}

public void opsiKuliner(ProductForm form){
    try{
        loadKuliner(form);
        clearTextField(form);
    } catch (Exception e){
        JOptionPane.showMessageDialog(null, e);
    }
}

public void opsiSelfImprovement(ProductForm form){
    try{
        loadSelfImprovement(form);
        clearTextField(form);
    } catch (Exception e){
        JOptionPane.showMessageDialog(null, e);
    }
}

public void opsiFilosofi(ProductForm form){
    try{
        loadFilosofi(form);
        clearTextField(form);
    } catch (Exception e){
        JOptionPane.showMessageDialog(null, e);
    }
}

public void opsiKriminal(ProductForm form){
    try{
        loadKriminal(form);
        clearTextField(form);
    } catch (Exception e){
        JOptionPane.showMessageDialog(null, e);
    }
}

public void opsiMisteri(ProductForm form){
    try{
        loadMisteri(form);
        clearTextField(form);
    } catch (Exception e){
        JOptionPane.showMessageDialog(null, e);
    }
}

public void opsiFantasi(ProductForm form){
    try{
        loadFantasi(form);
        clearTextField(form);
    } catch (Exception e){
        JOptionPane.showMessageDialog(null, e);
    }
}

public void opsiHoror(ProductForm form){
    try{
        loadHoror(form);
        clearTextField(form);
    } catch (Exception e){
        JOptionPane.showMessageDialog(null, e);
    }
}

public void opsiManga(ProductForm form){
    try{
        loadManga(form);
        clearTextField(form);
    } catch (Exception e){
        JOptionPane.showMessageDialog(null, e);
    }
}

public void opsiPuisi(ProductForm form){
    try{
        loadPuisi(form);
        clearTextField(form);
    } catch (Exception e){
        JOptionPane.showMessageDialog(null, e);
    }
}

public void opsiDrama(ProductForm form){
    try{
        loadDrama(form);
        clearTextField(form);
    } catch (Exception e){
        JOptionPane.showMessageDialog(null, e);
    }
}

public void opsiKomedi(ProductForm form){
    try{
        loadKomedi(form);
        clearTextField(form);
    } catch (Exception e){
        JOptionPane.showMessageDialog(null, e);
    }
}

    public void cariNama(ProductForm form){
              // TODO add your handling code here:
            try {
                String judul = String.valueOf(JOptionPane.showInputDialog("Masukan Nama Produk"));
                ProductDAOImp dao = new ProductDAOImp();
                Product productDAO = dao.getProductWithName(judul);
                loadDBByName(form,judul);
                form.txtIDProduct.setText(String.valueOf(productDAO.getProductId()));            
                form.txtGenre.setText(productDAO.getGenre());
                form.txtJudul.setText(productDAO.getJudul());
                form.txtPengarang.setText(productDAO.getPengarang());
                form.txtPenerbit.setText(productDAO.getPenerbit());
                form.txtHarga.setText(String.format("%.0f", productDAO.getHarga()));
                form.txtJilid.setText(String.valueOf(productDAO.getJilid()));

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
         }
     
    //User FORM
     public void loadDBUser(ProductFormUser form)
    {
        try {
            ProductDAOImp productDAO = new ProductDAOImp();
            List<Product> listProduct = productDAO.list();
            DefaultTableModel DFT = (DefaultTableModel) form.tableProduct5.getModel();
            DFT.setRowCount(0);

            for(Product product: listProduct)
            {
                int productId = product.getProductId();
                String genre = product.getGenre();
                String judul = product.getJudul();
                String pengarang = product.getPengarang();
                String penerbit = product.getPenerbit();
                double harga = product.getHarga();
                int jilid = product.getJilid();
                DFT.addRow(new Object[]{productId,genre,judul,pengarang,penerbit,harga,jilid});
            } 

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
     public void clickUser(ProductFormUser form){
         try {

            int row =form.tableProduct5.getSelectedRow();
            String dataKlik=(form.tableProduct5.getModel().getValueAt(row, 0).toString());
            int productId = Integer.parseInt(dataKlik);

            ProductDAOImp dao = new ProductDAOImp();
            Product productDAO = dao.getProduct(productId);

            form.txtIDProductUser.setText(String.valueOf(productDAO.getProductId()));            
            form.txtGenreUser.setText(productDAO.getGenre());
            form.txtJudulUser.setText(productDAO.getJudul());
            form.txtPengarangUser.setText(productDAO.getPengarang());
            form.txtPenerbitUser.setText(productDAO.getPenerbit());
            form.txtHargaUser.setText(String.format("%.0f", productDAO.getHarga()));
            form.txtJilidUser.setText(String.valueOf(productDAO.getJilid()));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
     }
     public void cariUser(ProductFormUser form){
          // TODO add your handling code here:
        try {
            int productId = Integer.parseInt(JOptionPane.showInputDialog("Masukan ID Produk"));
            ProductDAOImp dao = new ProductDAOImp();
            Product productDAO = dao.getProduct(productId);
            loadDBByIDUser(form,productId);
            form.txtIDProductUser.setText(String.valueOf(productDAO.getProductId()));            
            form.txtGenreUser.setText(productDAO.getGenre());
            form.txtJudulUser.setText(productDAO.getJudul());
            form.txtPengarangUser.setText(productDAO.getPengarang());
            form.txtPenerbitUser.setText(productDAO.getPenerbit());
            form.txtHargaUser.setText(String.format("%.0f", productDAO.getHarga()));
            form.txtJilidUser.setText(String.valueOf(productDAO.getJilid()));

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
     }
     public void loadDBByIDUser(ProductFormUser form,int productId){
         
          try {
            ProductDAOImp productDAO = new ProductDAOImp();
            List<Product> listProduct = productDAO.listByID(productId);
            DefaultTableModel DFT = (DefaultTableModel) form.tableProduct5.getModel();
            DFT.setRowCount(0);

            for(Product product: listProduct)
            {
                int IDproduct = product.getProductId();
                String genre = product.getGenre();
                String judul = product.getJudul();
                String pengarang = product.getPengarang();
                String penerbit = product.getPenerbit();
                double harga = product.getHarga();
                int jilid = product.getJilid();
                DFT.addRow(new Object[]{IDproduct,genre,judul,pengarang,penerbit,harga,jilid});
            } 

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
     }
     public void loadDBByNameUser(ProductFormUser form,String title){
         
          try {
            ProductDAOImp productDAO = new ProductDAOImp();
            List<Product> listProduct = productDAO.listByName(title);
            DefaultTableModel DFT = (DefaultTableModel) form.tableProduct5.getModel();
            DFT.setRowCount(0);

            for(Product product: listProduct)
            {
                int IDproduct = product.getProductId();
                String genre = product.getGenre();
                String judul = product.getJudul();
                String pengarang = product.getPengarang();
                String penerbit = product.getPenerbit();
                double harga = product.getHarga();
                int jilid = product.getJilid();
                DFT.addRow(new Object[]{IDproduct,genre,judul,pengarang,penerbit,harga,jilid});
            } 

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
     }
     //     View per genre
    public void loadKulinerUser(ProductFormUser form)
        {
            try {
                ProductDAOImp productDAO = new ProductDAOImp();
                List<Product> listProduct = productDAO.kuliner();
                DefaultTableModel DFT = (DefaultTableModel) form.tableProduct5.getModel();
                DFT.setRowCount(0);

                for(Product product: listProduct)
                {
                    int productId = product.getProductId();
                    String genre = product.getGenre();
                    String judul = product.getJudul();
                    String pengarang = product.getPengarang();
                    String penerbit = product.getPenerbit();
                    double harga = product.getHarga();
                    int jilid = product.getJilid();
                    DFT.addRow(new Object[]{productId,genre,judul,pengarang,penerbit,harga,jilid});
                } 

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }


    public void loadSelfImprovementUser(ProductFormUser form)
        {
            try {
                ProductDAOImp productDAO = new ProductDAOImp();
                List<Product> listProduct = productDAO.selfImprovement();
                DefaultTableModel DFT = (DefaultTableModel) form.tableProduct5.getModel();
                DFT.setRowCount(0);

                for(Product product: listProduct)
                {
                    int productId = product.getProductId();
                    String genre = product.getGenre();
                    String judul = product.getJudul();
                    String pengarang = product.getPengarang();
                    String penerbit = product.getPenerbit();
                    double harga = product.getHarga();
                    int jilid = product.getJilid();
                    DFT.addRow(new Object[]{productId,genre,judul,pengarang,penerbit,harga,jilid});
                } 

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }

    public void loadFilosofiUser(ProductFormUser form)
        {
            try {
                ProductDAOImp productDAO = new ProductDAOImp();
                List<Product> listProduct = productDAO.filosofi();
                DefaultTableModel DFT = (DefaultTableModel) form.tableProduct5.getModel();
                DFT.setRowCount(0);

                for(Product product: listProduct)
                {
                    int productId = product.getProductId();
                    String genre = product.getGenre();
                    String judul = product.getJudul();
                    String pengarang = product.getPengarang();
                    String penerbit = product.getPenerbit();
                    double harga = product.getHarga();
                    int jilid = product.getJilid();
                    DFT.addRow(new Object[]{productId,genre,judul,pengarang,penerbit,harga,jilid});
                } 

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }

    public void loadKriminalUser(ProductFormUser form)
        {
            try {
                ProductDAOImp productDAO = new ProductDAOImp();
                List<Product> listProduct = productDAO.kriminal();
                DefaultTableModel DFT = (DefaultTableModel) form.tableProduct5.getModel();
                DFT.setRowCount(0);

                for(Product product: listProduct)
                {
                    int productId = product.getProductId();
                    String genre = product.getGenre();
                    String judul = product.getJudul();
                    String pengarang = product.getPengarang();
                    String penerbit = product.getPenerbit();
                    double harga = product.getHarga();
                    int jilid = product.getJilid();
                    DFT.addRow(new Object[]{productId,genre,judul,pengarang,penerbit,harga,jilid});
                } 

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }

    public void loadMisteriUser(ProductFormUser form)
        {
            try {
                ProductDAOImp productDAO = new ProductDAOImp();
                List<Product> listProduct = productDAO.misteri();
                DefaultTableModel DFT = (DefaultTableModel) form.tableProduct5.getModel();
                DFT.setRowCount(0);

                for(Product product: listProduct)
                {
                    int productId = product.getProductId();
                    String genre = product.getGenre();
                    String judul = product.getJudul();
                    String pengarang = product.getPengarang();
                    String penerbit = product.getPenerbit();
                    double harga = product.getHarga();
                    int jilid = product.getJilid();
                    DFT.addRow(new Object[]{productId,genre,judul,pengarang,penerbit,harga,jilid});
                } 

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }

    public void loadFantasiUser(ProductFormUser form)
        {
            try {
                ProductDAOImp productDAO = new ProductDAOImp();
                List<Product> listProduct = productDAO.fantasi();
                DefaultTableModel DFT = (DefaultTableModel) form.tableProduct5.getModel();
                DFT.setRowCount(0);

                for(Product product: listProduct)
                {
                    int productId = product.getProductId();
                    String genre = product.getGenre();
                    String judul = product.getJudul();
                    String pengarang = product.getPengarang();
                    String penerbit = product.getPenerbit();
                    double harga = product.getHarga();
                    int jilid = product.getJilid();
                    DFT.addRow(new Object[]{productId,genre,judul,pengarang,penerbit,harga,jilid});
                } 

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }

    public void loadHororUser(ProductFormUser form)
        {
            try {
                ProductDAOImp productDAO = new ProductDAOImp();
                List<Product> listProduct = productDAO.horor();
                DefaultTableModel DFT = (DefaultTableModel) form.tableProduct5.getModel();
                DFT.setRowCount(0);

                for(Product product: listProduct)
                {
                    int productId = product.getProductId();
                    String genre = product.getGenre();
                    String judul = product.getJudul();
                    String pengarang = product.getPengarang();
                    String penerbit = product.getPenerbit();
                    double harga = product.getHarga();
                    int jilid = product.getJilid();
                    DFT.addRow(new Object[]{productId,genre,judul,pengarang,penerbit,harga,jilid});
                } 

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }

    public void loadMangaUser(ProductFormUser form)
        {
            try {
                ProductDAOImp productDAO = new ProductDAOImp();
                List<Product> listProduct = productDAO.manga();
                DefaultTableModel DFT = (DefaultTableModel) form.tableProduct5.getModel();
                DFT.setRowCount(0);

                for(Product product: listProduct)
                {
                    int productId = product.getProductId();
                    String genre = product.getGenre();
                    String judul = product.getJudul();
                    String pengarang = product.getPengarang();
                    String penerbit = product.getPenerbit();
                    double harga = product.getHarga();
                    int jilid = product.getJilid();
                    DFT.addRow(new Object[]{productId,genre,judul,pengarang,penerbit,harga,jilid});
                } 

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }

    public void loadPuisiUser(ProductFormUser form)
        {
            try {
                ProductDAOImp productDAO = new ProductDAOImp();
                List<Product> listProduct = productDAO.puisi();
                DefaultTableModel DFT = (DefaultTableModel) form.tableProduct5.getModel();
                DFT.setRowCount(0);

                for(Product product: listProduct)
                {
                    int productId = product.getProductId();
                    String genre = product.getGenre();
                    String judul = product.getJudul();
                    String pengarang = product.getPengarang();
                    String penerbit = product.getPenerbit();
                    double harga = product.getHarga();
                    int jilid = product.getJilid();
                    DFT.addRow(new Object[]{productId,genre,judul,pengarang,penerbit,harga,jilid});
                } 

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }

    public void loadDramaUser(ProductFormUser form)
        {
            try {
                ProductDAOImp productDAO = new ProductDAOImp();
                List<Product> listProduct = productDAO.drama();
                DefaultTableModel DFT = (DefaultTableModel) form.tableProduct5.getModel();
                DFT.setRowCount(0);

                for(Product product: listProduct)
                {
                    int productId = product.getProductId();
                    String genre = product.getGenre();
                    String judul = product.getJudul();
                    String pengarang = product.getPengarang();
                    String penerbit = product.getPenerbit();
                    double harga = product.getHarga();
                    int jilid = product.getJilid();
                    DFT.addRow(new Object[]{productId,genre,judul,pengarang,penerbit,harga,jilid});
                } 

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }

    public void loadKomediUser(ProductFormUser form)
        {
            try {
                ProductDAOImp productDAO = new ProductDAOImp();
                List<Product> listProduct = productDAO.komedi();
                DefaultTableModel DFT = (DefaultTableModel) form.tableProduct5.getModel();
                DFT.setRowCount(0);

                for(Product product: listProduct)
                {
                    int productId = product.getProductId();
                    String genre = product.getGenre();
                    String judul = product.getJudul();
                    String pengarang = product.getPengarang();
                    String penerbit = product.getPenerbit();
                    double harga = product.getHarga();
                    int jilid = product.getJilid();
                    DFT.addRow(new Object[]{productId,genre,judul,pengarang,penerbit,harga,jilid});
                } 

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }


    public void opsiGeneralUser(ProductFormUser form){
        try{
            loadDBUser(form);
            clearTextFieldUser(form);
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void opsiKulinerUser(ProductFormUser form){
        try{
            loadKulinerUser(form);
            clearTextFieldUser(form);
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void opsiSelfImprovementUser(ProductFormUser form){
        try{
            loadSelfImprovementUser(form);
            clearTextFieldUser(form);
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void opsiFilosofiUser(ProductFormUser form){
        try{
            loadFilosofiUser(form);
            clearTextFieldUser(form);
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void opsiKriminalUser(ProductFormUser form){
        try{
            loadKriminalUser(form);
            clearTextFieldUser(form);
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void opsiMisteriUser(ProductFormUser form){
        try{
            loadMisteriUser(form);
            clearTextFieldUser(form);
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void opsiFantasiUser(ProductFormUser form){
        try{
            loadFantasiUser(form);
            clearTextFieldUser(form);
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void opsiHororUser(ProductFormUser form){
        try{
            loadHororUser(form);
            clearTextFieldUser(form);
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void opsiMangaUser(ProductFormUser form){
        try{
            loadMangaUser(form);
            clearTextFieldUser(form);
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void opsiPuisiUser(ProductFormUser form){
        try{
            loadPuisiUser(form);
            clearTextFieldUser(form);
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void opsiDramaUser(ProductFormUser form){
        try{
            loadDramaUser(form);
            clearTextFieldUser(form);
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void opsiKomediUser(ProductFormUser form){
        try{
            loadKomediUser(form);
            clearTextFieldUser(form);
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }

        public void cariNamaUser(ProductFormUser form){
                  // TODO add your handling code here:
                try {
                    String judul = String.valueOf(JOptionPane.showInputDialog("Masukan Nama Produk"));
                    ProductDAOImp dao = new ProductDAOImp();
                    Product productDAO = dao.getProductWithName(judul);
                    loadDBByNameUser(form,judul);
                    form.txtIDProductUser.setText(String.valueOf(productDAO.getProductId()));            
                    form.txtGenreUser.setText(productDAO.getGenre());
                    form.txtJudulUser.setText(productDAO.getJudul());
                    form.txtPengarangUser.setText(productDAO.getPengarang());
                    form.txtPenerbitUser.setText(productDAO.getPenerbit());
                    form.txtHargaUser.setText(String.format("%.0f", productDAO.getHarga()));
                    form.txtJilidUser.setText(String.valueOf(productDAO.getJilid()));

                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e);
                }
             }
     public void clearTextFieldUser(ProductFormUser form){
        
        form.txtIDProductUser.setText("");
        form.txtGenreUser.setText("");
        form.txtJudulUser.setText("");
        form.txtPengarangUser.setText("");
        form.txtPenerbitUser.setText("");
        form.txtHargaUser.setText("");
        form.txtJilidUser.setText("");
         
     }
}
