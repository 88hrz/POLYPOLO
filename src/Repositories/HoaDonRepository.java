/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repositories;

import ViewModels.GioHangViewModel;
import Models.HoaDon;
import ViewModels.HoaDonViewModel;
import ViewModels.HoaDon_SPViewModel;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author X1
 */
public class HoaDonRepository {
    DbConnection dbConnection;
    
    //GET LIST HOADONVIEW
    public ArrayList<HoaDonViewModel> getListHoaDonView(){
        String sql = "SELECT hdct.MaHoaDonChiTiet, kh.TenKhachHang, kh.SoDienThoai\n" +
                            ", hd.PhuongThucThanhToan, hd.TongTien , hd.NgayLap, hd.TenNhanVien FROM HoaDonChiTiet hdct\n" +
                            "INNER JOIN KhachHang kh ON kh.MaHoaDon = hdct.MaHoaDon\n" +
                            "INNER JOIN HoaDon hd ON hd.MaHoaDon = hdct.MaHoaDon";
        ArrayList<HoaDonViewModel> lsHoaDon = new ArrayList<>();
        
        try (Connection conn = dbConnection.getConnection();
                PreparedStatement ps = conn.prepareCall(sql)){
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {                
                Integer maHD = rs.getInt("MaHoaDonChiTiet");
                String tenKH = rs.getString("TenKhachHang");
                Integer soDT = rs.getInt("SoDienThoai");
                String phuongThuc = rs.getString("PhuongThucThanhToan");
                Double tongTien = rs.getDouble("TongTien");
                Date ngayLap = rs.getDate("NgayLap");
                String tenNV = rs.getString("TenNhanVien");
                
                HoaDonViewModel hoaDon = new HoaDonViewModel(maHD, tenKH, soDT, phuongThuc, tongTien, ngayLap, tenNV);
                lsHoaDon.add(hoaDon);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lsHoaDon;
    }
    //GET LIST BY TRANGTHAI
    public ArrayList<HoaDon> getListByTrangThai(String trangThai){
        String sql = "SELECT * FROM HoaDon WHERE TrangThai LIKE N'%" + trangThai + "%'";
        ArrayList<HoaDon> lsTrangThai = new ArrayList<>();
        
        try (Connection conn = dbConnection.getConnection();
                PreparedStatement ps = conn.prepareCall(sql)){
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {                
                Integer maHD = rs.getInt("MaHoaDon");
                String tenNV = rs.getString("TenNhanVien");
                String tenKH = rs.getString("TenKhachHang");
                String phuongThuc = rs.getString("PhuongThucThanhToan");
                Date ngayLap = rs.getDate("NgayLap");
                Double tongTien = rs.getDouble("TongTien");
                
                HoaDon hoaDon = new HoaDon(maHD, null, tenNV, tenKH, phuongThuc, tongTien, ngayLap, trangThai);
                lsTrangThai.add(hoaDon);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lsTrangThai;
    }
    //GET LIST SANPHAM
    public ArrayList<HoaDon_SPViewModel> getListSanPham(){
            String sql = "SELECT spct.MaSanPham, spct.TenSanPhamChiTiet, dm.TenDanhMuc\n" +
                                ", ms.TenMau, s.TenSize\n" +
                                ", sp.GiaBan, spct.SoLuongTon, spct.TrangThai FROM SanPham sp\n" +
                                "INNER JOIN DanhMuc dm ON dm.MaDanhMuc = sp.MaDanhMuc\n" +
                                "INNER JOIN SanPhamChiTiet spct ON spct.MaSanPham = sp.MaSanPham\n" +
                                "INNER JOIN MauSac ms ON ms.MaMau = spct.MaMau\n" +
                                "INNER JOIN Size s ON s.MaSize = spct.MaSize";
        ArrayList<HoaDon_SPViewModel> lsSanPham = new ArrayList<>();
        
        try (Connection conn = dbConnection.getConnection();
                PreparedStatement ps = conn.prepareCall(sql)){
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {                
                Integer maSPCT = rs.getInt("MaSanPham");
                String tenSP = rs.getString("TenSanPhamChiTiet");
                String tenDM = rs.getString("TenDanhMuc");
                String tenMau = rs.getString("TenMau");
                String kichCo = rs.getString("TenSize");
                Double donGia = rs.getDouble("GiaBan");
                Integer soLuong = rs.getInt("SoLuongTon");
                String trangThai = rs.getString("TrangThai");
                
                HoaDon_SPViewModel sanPhamView = new HoaDon_SPViewModel(maSPCT, tenSP, tenDM, tenMau, kichCo, donGia, soLuong, trangThai);
                lsSanPham.add(sanPhamView);     
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lsSanPham;
    }
    //SEARCH
    public ArrayList<HoaDon_SPViewModel> getListBySearch(Integer id){
        String sql = "SELECT spct.MaSanPham, spct.TenSanPhamChiTiet, dm.TenDanhMuc\n" +
                            ", ms.TenMau, s.TenSize\n" +
                            ", sp.GiaBan, spct.SoLuongTon, spct.TrangThai FROM SanPham sp\n" +
                            "INNER JOIN DanhMuc dm ON dm.MaDanhMuc = sp.MaDanhMuc\n" +
                            "INNER JOIN SanPhamChiTiet spct ON spct.MaSanPham = sp.MaSanPham\n" +
                            "INNER JOIN MauSac ms ON ms.MaMau = spct.MaMau\n" +
                            "INNER JOIN Size s ON s.MaSize = spct.MaSize\n" +
                            "--SEARCH SANPHAM\n" +
                            "WHERE spct.MaSanPham = ?";
        ArrayList<HoaDon_SPViewModel> ls = new ArrayList<>();
        
        try (Connection conn = dbConnection.getConnection();
                PreparedStatement ps = conn.prepareCall(sql)){
            ps.setObject(1, id);
            
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {                
                Integer maSPCT = rs.getInt("MaSanPham");
                String tenSP = rs.getString("TenSanPhamChiTiet");
                String tenDM = rs.getString("TenDanhMuc");
                String tenMau = rs.getString("TenMau");
                String kichCo = rs.getString("TenSize");
                Double donGia = rs.getDouble("GiaBan");
                Integer soLuong = rs.getInt("SoLuongTon");
                String trangThai = rs.getString("TrangThai");
                
                HoaDon_SPViewModel sanPham = new HoaDon_SPViewModel(maSPCT, tenSP, tenDM, tenMau, kichCo, donGia, soLuong, trangThai);
                ls.add(sanPham);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ls;
    }
    
    
    
    
    
    
    
    
    
    
    
    ///////XÓA TỪ ĐÂYY
    //ADD GIOHANG
    public Boolean addGioHang(Integer idHD, Integer idSP, Integer buyQT, Double price){
        String sql = "SELECT * FROM HoaDonChiTiet hdct\n" +
                        "INNER JOIN SanPhamChiTiet spct\n" +
                        "ON spct.MaSanPhamChiTiet = hdct.MaSanPhamChiTiet\n" +
                        "WHERE hdct.MaHoaDon = ? AND spct.MaSanPhamChiTiet = ?";
        
        try (Connection conn = dbConnection.getConnection();
                PreparedStatement ps = conn.prepareCall(sql)){
            ps.setInt(1, idHD);
            ps.setInt(2, idSP);
            
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Integer currentQt = rs.getInt("SoLuong");
                Integer newQt = currentQt + buyQT;
                
                String sqlUpdate = "UPDATE HoaDonChiTiet SET SoLuong = ?, DonGia = ?\n" +
                                        "WHERE MaHoaDon = ? AND MaSanPhamChiTiet = ?";
                PreparedStatement ps1 = conn.prepareStatement(sqlUpdate);
                ps1.setInt(1, newQt);
                ps1.setDouble(2, price);
                ps1.setInt(3, idHD);
                ps1.setInt(4, idSP);
                
                ps1.executeUpdate();
            }else{
                String sqlInsert = "INSERT INTO HoaDonChiTiet (MaHoaDon, MaSanPhamChiTiet, SoLuong, DonGia) VALUES (?, ?, ?, ?)";
                PreparedStatement ps2 = conn.prepareStatement(sqlInsert);
                ps.setInt(1, idHD);
                ps.setInt(2, idSP);
                ps.setInt(3, buyQT);
                ps.setDouble(4, price);
                
                ps2.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    //GETLIST GIOHANG
    public ArrayList<GioHangViewModel> getListGioHang(Integer id){
        String sql = "SELECT * FROM HoaDon hd INNER JOIN \n" +
                            "(HoaDonChiTiet hdct INNER JOIN SanPhamChiTiet spct \n" +
                            "ON hdct.MaSanPhamChiTiet = spct.MaSanPhamChiTiet)\n" +
                            "ON hd.MaHoaDon = hdct.MaHoaDon WHERE hdct.MaHoaDon = ?";
        ArrayList<GioHangViewModel> ls = new ArrayList<>();
        
        try (Connection conn = dbConnection.getConnection();
                PreparedStatement ps = conn.prepareCall(sql)){
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {                
                GioHangViewModel gioHang = new GioHangViewModel();
                gioHang.setMaSP(rs.getInt("MaHoaDon"));
                gioHang.setTenSP(rs.getString("TenSanPhamChiTiet"));
                gioHang.setMauSac(rs.getString("MaMau"));
                gioHang.setKichCo(rs.getString("MaSize"));
                gioHang.setSoLuong(rs.getInt("SoLuong"));
                gioHang.setDonGia(rs.getDouble("DonGia"));
                gioHang.setThanhTien(rs.getDouble("TongTien"));
                
                ls.add(gioHang);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ls;
    }

    //GETLIST BY ID HOADON_ent
    public HoaDon getListByID(Integer id){
        String sql = "SELECT * FROM HoaDon WHERE MaHoaDon = ?";
        HoaDon hoaDon = new HoaDon();
        
        try (Connection conn = dbConnection.getConnection();
                PreparedStatement ps = conn.prepareCall(sql)){
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {                
                Integer maHD = rs.getInt("MaHoaDon");
                Integer maNV = rs.getInt("MaNhanVien");
                String tenKH = rs.getString("TenKhachHang");
                String tenNV = rs.getString("TenNhanVien");
                Double tongTien = rs.getDouble("TongTien");
                Date ngayLap = rs.getDate("NgayLap");
                String trangThai = rs.getString("TrangThai");
                String phuongThuc = rs.getString("PhuongThucThanhToan");
                
                hoaDon = new HoaDon(maHD, maNV, tenNV, tenKH, phuongThuc, tongTien, ngayLap, trangThai);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return hoaDon;
    }

    //ADD HOADON
    public Boolean addHoaDon(HoaDon hoaDon){
        String sql = "INSERT INTO HoaDon (MaNhanVien, TenKhachHang, TenNhanVien, TongTien, NgayLap, TrangThai, PhuongThucThanhToan)\n" +
                                    "VALUES (?, ?,?, ?, ?, ?, ?)";
        
        try (Connection conn = dbConnection.getConnection();
                PreparedStatement ps = conn.prepareCall(sql)){
            ps.setObject(1, hoaDon.getMaNV());
            ps.setObject(2, hoaDon.getTenKH());
            ps.setObject(3, hoaDon.getTenNV());
            ps.setObject(4, hoaDon.getTongTien());
            ps.setObject(5, hoaDon.getNgayLap().toString());
            ps.setObject(6, hoaDon.getTrangThai());
            ps.setObject(7, hoaDon.getPhuongThuc());
            
            int check = ps.executeUpdate();
            if (check > 0) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    //THANH TOAN
    public Boolean thanhToanHD(Integer maHD){
        String sql = "UPDATE HoaDon SET TrangThai = N'Đã thanh toán' WHERE MaHoaDon = ?";
        
        try (Connection conn = dbConnection.getConnection();
                PreparedStatement ps = conn.prepareCall(sql)){
            ps.setObject(1, maHD);
            
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
