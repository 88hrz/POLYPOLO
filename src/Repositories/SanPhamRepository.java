/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repositories;

import Models.SanPham;
import Models.SanPhamChiTiet;
import ViewModels.SPCTViewModel;
import ViewModels.HoaDon_SPViewModel;
import java.util.ArrayList;
import java.sql.*;

/**
 *
 * @author X1
 */
public class SanPhamRepository {
    DbConnection dbConnection;
    
    //GETLIST VIEW MODEL
    public ArrayList<SPCTViewModel> getListSanPham(){
        String sql = "SELECT spct.MaSanPham, spct.TenSanPhamChiTiet ,dm.TenDanhMuc\n" +
                                ", ms.TenMau, s.TenSize, sp.GiaNhap, sp.GiaBan, spct.TrangThai, spct.SoLuongTon FROM DanhMuc dm\n" +
                                "INNER JOIN SanPham sp ON dm.MaDanhMuc = sp.MaDanhMuc\n" +
                                "INNER JOIN SanPhamChiTiet spct ON spct.MaSanPham = sp.MaSanPham\n" +
                                "INNER JOIN MauSac ms ON ms.MaMau = spct.MaMau\n" +
                                "INNER JOIN Size s ON s.MaSize = spct.MaSize";
        ArrayList<SPCTViewModel> ls = new ArrayList<>();
        
        try (Connection conn = dbConnection.getConnection();
                PreparedStatement ps = conn.prepareCall(sql)){
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {                
                Integer maSP = rs.getInt("MaSanPham");
                String tenSP = rs.getString("TenSanPhamChiTiet");
                String tenDM = rs.getString("TenDanhMuc");
                String tenMau = rs.getString("TenMau");
                String tenSize = rs.getString("TenSize");
                Double giaNhap = rs.getDouble("GiaNhap");
                Double giaBan = rs.getDouble("GiaBan");
                String trangThai = rs.getString("TrangThai");
                Integer soLuong = rs.getInt("SoLuongTon");
                
                SPCTViewModel spct = new SPCTViewModel(maSP, tenSP, tenDM, tenMau, tenSize, giaNhap, giaBan, trangThai, soLuong);
                ls.add(spct);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ls;
    }
    //GETLIST BY SEARCH_ID
    public ArrayList<SPCTViewModel> getListBySearch(Integer id){
        String sql = "SELECT spct.MaSanPham, spct.TenSanPhamChiTiet ,dm.TenDanhMuc\n" +
                                ", ms.TenMau, s.TenSize, sp.GiaNhap, sp.GiaBan, spct.TrangThai, spct.SoLuongTon FROM DanhMuc dm\n" +
                                "INNER JOIN SanPham sp ON dm.MaDanhMuc = sp.MaDanhMuc\n" +
                                "INNER JOIN SanPhamChiTiet spct ON spct.MaSanPham = sp.MaSanPham\n" +
                                "INNER JOIN MauSac ms ON ms.MaMau = spct.MaMau\n" +
                                "INNER JOIN Size s ON s.MaSize = spct.MaSize WHERE spct.MaSanPham = ?";
        ArrayList<SPCTViewModel> ls = new ArrayList<>();
        
        try (Connection conn = dbConnection.getConnection();
                PreparedStatement ps = conn.prepareCall(sql)){
            ps.setObject(1, id);
            
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {                
                Integer maSP = rs.getInt("MaSanPham");
                String tenSP = rs.getString("TenSanPhamChiTiet");
                String tenDM = rs.getString("TenDanhMuc");
                String tenMau = rs.getString("TenMau");
                String tenSize = rs.getString("TenSize");
                Double giaNhap = rs.getDouble("GiaNhap");
                Double giaBan = rs.getDouble("GiaBan");
                String trangThai = rs.getString("TrangThai");
                Integer soLuong = rs.getInt("SoLuongTon");

                SPCTViewModel spct = new SPCTViewModel(maSP, tenSP, tenDM, tenMau, tenSize, giaNhap, giaBan, trangThai, soLuong);
                ls.add(spct);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ls;
    }

    //GET_PRICE BY ID
    public SanPham getPriceByID(String id){
        String sql = "SELECT * FROM SanPham WHERE MaSanPham = "+id+"";
        SanPham sp = new SanPham();
        
        try (Connection conn = dbConnection.getConnection();
                PreparedStatement ps = conn.prepareCall(sql)){
            ps.setObject(1, id);
            
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {                
                Integer maSP = rs.getInt("MaSanPham");
                Integer maDM = rs.getInt("MaDanhMuc");
                String trangThai = rs.getString("TrangThai");
                Double giaNhap = rs.getDouble("GiaNhap");
                Double giaBan = rs.getDouble("GiaBan");
                
                sp = new SanPham(maSP, maDM, trangThai, giaNhap, giaBan);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sp;
    }
    
    
    
    
    
    
    
    
    
    
    //ADD SANPHAM
    public Boolean addSP(SanPham sp){
        String sql = "INSERT INTO SanPham(MaDanhMuc, TrangThai, GiaNhap, GiaBan) \n" +
                            "VALUES(?,?, ?, ?)";
        
        try (Connection conn = dbConnection.getConnection();
                PreparedStatement ps = conn.prepareCall(sql)){
            ps.setObject(1, sp.getMaDM());
            ps.setObject(2, sp.getTrangThai());
            ps.setObject(3, sp.getGiaNhap());
            ps.setObject(4, sp.getGiaBan());
            
            int check = ps.executeUpdate();
            if (check>0) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    //ADD SPCT
    public Boolean addSPCT(SanPhamChiTiet spct){
        String sql = "INSERT INTO SanPhamChiTiet (MaSanPham, TenSanPhamChiTiet, MaSize, MaMau, TrangThai, SoLuongTon) \n" +
                        "VALUES  (?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = dbConnection.getConnection();
                PreparedStatement ps = conn.prepareCall(sql)){
            ps.setObject(1, spct.getMaSP());
            ps.setObject(2, spct.getTenSP());
            ps.setObject(3, spct.getMaSize());
            ps.setObject(4, spct.getMaMau());
            ps.setObject(5, spct.getTrangThai());
            ps.setObject(6, spct.getSoLuong());
            
            int check = ps.executeUpdate();
            if (check > 0) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
