/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repositories;

import Models.SanPham;
import Models.SanPhamChiTiet;
import ViewModels.SanPhamViewModel;
import java.util.ArrayList;
import java.sql.*;

/**
 *
 * @author X1
 */
public class SanPhamRepository {
    DbConnection dbConnection;
    
    //HIDE_UNHIDE
    public Boolean hideSP(SanPham sp){
        String sql = "UPDATE SanPham SET Deleted = 1 WHERE MaSanPham = ?";
        try (Connection conn = dbConnection.getConnection();
                PreparedStatement ps = conn.prepareCall(sql)){
            ps.setObject(1, sp.getMaSP());
            
            int check = ps.executeUpdate();
            if (check>0) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    public Boolean unhideSP(SanPham sp){
        String sql = "UPDATE SanPham SET Deleted = 0 WHERE MaSanPham = ?";
        try (Connection conn = dbConnection.getConnection();
                PreparedStatement ps = conn.prepareCall(sql)){
            ps.setObject(1, sp.getMaSP());
            
            int check = ps.executeUpdate();
            if (check>0) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    //GETLIST
    public ArrayList<SanPhamViewModel> getListSanPham(){
        String sql = "SELECT spct.MaSanPham, spct.TenSanPhamChiTiet, dm.TenDanhMuc, ms.TenMau, s.TenSize, sp.GiaNhap, sp.GiaBan, spct.TrangThai, spct.SoLuongTon FROM DanhMuc dm\n" +
                        "INNER JOIN SanPham sp ON dm.MaDanhMuc = sp.MaDanhMuc\n" +
                        "INNER JOIN SanPhamChiTiet spct ON spct.MaSanPham = sp.MaSanPham\n" +
                        "INNER JOIN MauSac ms ON ms.MaMau = spct.MaMau\n" +
                        "INNER JOIN Size s ON s.MaSize = spct.MaSize\n" +
                        "WHERE sp.Deleted !=1";
        ArrayList<SanPhamViewModel> ls = new ArrayList<>();
        
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
                
                SanPhamViewModel spct = new SanPhamViewModel(maSP, tenSP, tenDM, tenMau, tenSize, giaNhap, giaBan, trangThai, soLuong);
                ls.add(spct);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ls;
    }
    //GETLIST HIDE
    public ArrayList<SanPhamViewModel> getListHideSP(){
        String sql ="SELECT spct.MaSanPham, spct.TenSanPhamChiTiet, dm.TenDanhMuc, ms.TenMau, s.TenSize, sp.GiaNhap, sp.GiaBan, spct.TrangThai, spct.SoLuongTon FROM DanhMuc dm\n" +
                        "INNER JOIN SanPham sp ON dm.MaDanhMuc = sp.MaDanhMuc\n" +
                        "INNER JOIN SanPhamChiTiet spct ON spct.MaSanPham = sp.MaSanPham\n" +
                        "INNER JOIN MauSac ms ON ms.MaMau = spct.MaMau\n" +
                        "INNER JOIN Size s ON s.MaSize = spct.MaSize\n" +
                        "WHERE sp.Deleted !=0";
        
        ArrayList<SanPhamViewModel> ls = new ArrayList<>();
        
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
                
                SanPhamViewModel spct = new SanPhamViewModel(maSP, tenSP, tenDM, tenMau, tenSize, giaNhap, giaBan, trangThai, soLuong);
                ls.add(spct);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ls;
    }
    
    //GETLIST BY SEARCH_NAME SP
    public ArrayList<SanPhamViewModel> getListBySearch(String name){
        String sql = "SELECT spct.MaSanPham, spct.TenSanPhamChiTiet, dm.TenDanhMuc, ms.TenMau, s.TenSize, sp.GiaNhap, sp.GiaBan, spct.TrangThai, spct.SoLuongTon FROM DanhMuc dm\n" +
                        "INNER JOIN SanPham sp ON dm.MaDanhMuc = sp.MaDanhMuc\n" +
                        "INNER JOIN SanPhamChiTiet spct ON spct.MaSanPham = sp.MaSanPham\n" +
                        "INNER JOIN MauSac ms ON ms.MaMau = spct.MaMau\n" +
                        "INNER JOIN Size s ON s.MaSize = spct.MaSize\n" +
                        "WHERE sp.Deleted !=1 AND spct.TenSanPhamChiTiet LIKE N'%"+name+"%'";
        ArrayList<SanPhamViewModel> ls = new ArrayList<>();
        
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

                SanPhamViewModel spct = new SanPhamViewModel(maSP, tenSP, tenDM, tenMau, tenSize, giaNhap, giaBan, trangThai, soLuong);
                ls.add(spct);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ls;
    }
   
    //ADD SANPHAM
    public Boolean addSP(SanPham sp){
        String sql = "INSERT INTO SanPham(MaDanhMuc, TrangThai, GiaNhap, GiaBan, Deleted) VALUES (?, ?, ?, ?,0);"
                + "INSERT INTO SanPhamChiTiet (MaSanPham, TenSanPhamChiTiet, MaSize, MaMau, TrangThai, SoLuongTon,Deleted) \n" +
                        "VALUES(?, ?, ?, ?, ?, ?,0);";

        try (Connection conn = dbConnection.getConnection();
                PreparedStatement ps =conn.prepareCall(sql)) {
            ps.setObject(1, sp.getMaDM());
            ps.setObject(2, sp.getTrangThai());
            ps.setObject(3, sp.getGiaNhap());
            ps.setObject(4, sp.getGiaBan());
            
            ps.setObject(5, sp.getMaSP());
            ps.setObject(6, sp.getTenSP());
            ps.setObject(7, sp.getMaSz());
            ps.setObject(8, sp.getMaMau());
            ps.setObject(9, sp.getTrangThai());
            ps.setObject(10, sp.getSoLuong());
            
            int check = ps.executeUpdate();
            if (check>0) {
                return true;
            }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return false;
}
    //UPDATE
    public Boolean updateSP(SanPham sp){
        String sql = "UPDATE SanPhamChiTiet SET TenSanPhamChiTiet = ?, TrangThai = ?, MaMau = ?, MaSize = ?,SoLuongTon= ? "
                + "WHERE MaSanPham = ?;" +
                 "UPDATE SanPham SET MaDanhMuc = ?, TrangThai = ?, GiaBan = ?,GiaNhap = ? "
                + "WHERE MaSanPham = ?;";
        
        try (Connection conn = dbConnection.getConnection();
                PreparedStatement ps = conn.prepareCall(sql)){
            ps.setObject(1, sp.getTenSP());
            ps.setObject(2, sp.getTrangThai());
            ps.setObject(3, sp.getMaMau());
            ps.setObject(4, sp.getMaSz());
            ps.setObject(5, sp.getSoLuong());
            ps.setObject(6, sp.getMaSP());
            
            ps.setObject(7, sp.getMaDM());
            ps.setObject(8, sp.getTrangThai());
            ps.setObject(9, sp.getGiaBan());
            ps.setObject(10, sp.getGiaNhap());
            ps.setObject(11, sp.getMaSP());
            
            int check = ps.executeUpdate();
            if (check>0) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    //CHECK ID
    public boolean checkId(String maSP) {
        String sql ="SELECT COUNT(*) FROM SanPham WHERE MaSanPham =?";
    try (Connection conn = dbConnection.getConnection();
         PreparedStatement ps = conn.prepareCall(sql)) {
        ps.setString(1, maSP);
        try (ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return false;
}

//    //DELETE
//    public Boolean deleteSP(SanPham sp){
//        String sql = "DELETE FROM HoaDonChiTiet WHERE MaSanPhamChiTiet IN (SELECT MaSanPhamChiTiet FROM SanPhamChiTiet WHERE MaSanPham = ?);\n" +
//                            "DELETE FROM SanPhamChiTiet WHERE MaSanPham = ?;\n" +
//                            "DELETE FROM SanPham WHERE MaSanPham = ?;";
//        
//        try (Connection conn = dbConnection.getConnection();
//                PreparedStatement ps = conn.prepareCall(sql)){
//            ps.setObject(1, sp.getMaSP());
//            ps.setObject(2, sp.getMaSP());
//            ps.setObject(3, sp.getMaSP());
//            
//            int check = ps.executeUpdate();
//            if (check>0) {
//                return true;
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return false;
//    }
    
    //GETNAME
    public SanPhamChiTiet getName(String name){
        String sql = "SELECT MaSanPham,TenSanPhamChiTiet FROM SanPhamChiTiet WHERE Deleted !=1 AND TenSanPhamChiTiet = ?";
        SanPhamChiTiet spct = null;
        
        try (Connection conn = dbConnection.getConnection();
                PreparedStatement ps = conn.prepareCall(sql)){
            ps.setObject(1, name);
            
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {                
                Integer maSP = rs.getInt("MaSanPham");
                String tenSP = rs.getString("TenSanPhamChiTiet");
                
                spct = new SanPhamChiTiet(maSP, tenSP);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return spct;
    }
   
    
    



}
