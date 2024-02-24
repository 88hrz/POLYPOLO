/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repositories;

import Models.HoaDon;
import ViewModels.GioHangViewModel;
import ViewModels.HoaDonChiTietViewModel;
import ViewModels.HoaDonViewModel;
import ViewModels.SanPhamViewModel;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author X1
 */
public class HoaDonRepository {
    DbConnection dbConnection;
    
    //GET LIST HOADON
    public ArrayList<HoaDonChiTietViewModel> getListHoaDon(){
        String sql = "SELECT MaHoaDon, TenNhanVien, TenKhachHang, PhuongThucThanhToan, NgayLap, TongTien FROM HoaDon";
        ArrayList<HoaDonChiTietViewModel> lsHDView = new ArrayList<>();
        
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
                
                HoaDonChiTietViewModel hdView = new HoaDonChiTietViewModel(maHD, tenNV, tenKH, phuongThuc, ngayLap, tongTien);
                lsHDView.add(hdView);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lsHDView;
    }   
    //GET LIST GIOHANG
    public ArrayList<GioHangViewModel> getListGioHang(){
        String sql = "SELECT SanPhamChiTiet.MaSanPham, SanPhamChiTiet.TenSanPhamChiTiet, MauSac.TenMau, Size.TenSize\n" +
                            ", HoaDonChiTiet.SoLuong, HoaDonChiTiet.DonGia\n" +
                            ", HoaDon.TongTien FROM SanPhamChiTiet \n" +
                            "INNER JOIN MauSac ON SanPhamChiTiet.MaMau = MauSac.MaMau\n" +
                            "INNER JOIN Size ON SanPhamChiTiet.MaSize = Size.MaSize\n" +
                            "INNER JOIN HoaDonChiTiet ON HoaDonChiTiet.MaSanPhamChiTiet = SanPhamChiTiet.MaSanPhamChiTiet\n" +
                            "INNER JOIN HoaDon ON  HoaDonChiTiet.MaHoaDon = HoaDon.MaHoaDon";
        ArrayList<GioHangViewModel> lsGioHang = new ArrayList<>();
        
        try (Connection conn = dbConnection.getConnection();
                PreparedStatement ps = conn.prepareCall(sql)){
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {                
                Integer maSP = rs.getInt("MaSanPham");
                String tenSP = rs.getString("TenSanPhamChiTiet");
                String tenMau = rs.getString("TenMau");
                String kichCo = rs.getString("TenSize");
                Integer soLuong = rs.getInt("SoLuong");
                Double donGia = rs.getDouble("DonGia");
                Double tongTien = rs.getDouble("TongTien");
                
                GioHangViewModel gioHang = new GioHangViewModel(maSP, soLuong, tenSP, tenMau, kichCo, donGia, tongTien);
                lsGioHang.add(gioHang); 
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lsGioHang;
    }
    //GET LIST SANPHAM
    public ArrayList<SanPhamViewModel> getListSanPham(){
        String sql = "SELECT SanPhamChiTiet.MaSanPhamChiTiet, SanPhamChiTiet.TenSanPhamChiTiet, DanhMuc.TenDanhMuc\n" +
                        ", MauSac.TenMau, Size.TenSize\n" +
                        ", HoaDonChiTiet.DonGia, HoaDonChiTiet.SoLuong\n" +
                        ", SanPhamChiTiet.TrangThai FROM SanPhamChiTiet \n" +
                        "INNER JOIN SanPham ON SanPham.MaSanPham = SanPhamChiTiet.MaSanPham\n" +
                        "INNER JOIN DanhMuc ON DanhMuc.MaDanhMuc = SanPham.MaDanhMuc\n" +
                        "INNER JOIN MauSac ON MauSac.MaMau = SanPhamChiTiet.MaMau\n" +
                        "INNER JOIN Size ON Size.MaSize = SanPhamChiTiet.MaSize\n" +
                        "INNER JOIN HoaDonChiTiet ON HoaDonChiTiet.MaSanPhamChiTiet = SanPhamChiTiet.MaSanPhamChiTiet";
        ArrayList<SanPhamViewModel> lsSanPham = new ArrayList<>();
        
        try (Connection conn = dbConnection.getConnection();
                PreparedStatement ps = conn.prepareCall(sql)){
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {                
                Integer maSPCT = rs.getInt("MaSanPhamChiTiet");
                String tenSP = rs.getString("TenSanPhamChiTiet");
                String tenDM = rs.getString("TenDanhMuc");
                String tenMau = rs.getString("TenMau");
                String kichCo = rs.getString("TenSize");
                Double donGia = rs.getDouble("DonGia");
                Integer soLuong = rs.getInt("SoLuong");
                String trangThai = rs.getString("TrangThai");
                
                SanPhamViewModel sanPham = new SanPhamViewModel(maSPCT, soLuong, tenSP, tenDM, tenMau, kichCo, trangThai, donGia);
                lsSanPham.add(sanPham);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lsSanPham;
    }
    
    //GET LIST BY TRANGTHAI
    public ArrayList<HoaDonViewModel> getListByTrangThai(String trangThai){
        String sql = "SELECT * FROM HoaDon WHERE TrangThai LIKE N'%" + trangThai + "%'";
        ArrayList<HoaDonViewModel> lsTrangThai = new ArrayList<>();
        
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
                
                HoaDonViewModel hoaDon = new HoaDonViewModel(maHD, maHD, tenNV, tenKH, phuongThuc, tongTien, ngayLap);
                lsTrangThai.add(hoaDon);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lsTrangThai;
    }
    
    //DEL GIOHANG
    public Boolean deleteSingleGioHang(Integer maSP){
        String sql = "DELETE FROM HoaDonChiTiet WHERE HoaDonChiTiet.MaSanPhamChiTiet =" + maSP +"";
        
        try (Connection conn = dbConnection.getConnection();
                PreparedStatement ps = conn.prepareCall(sql)){
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
