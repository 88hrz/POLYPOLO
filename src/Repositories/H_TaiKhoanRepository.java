/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repositories;

import ViewModels.TaiKhoanViewModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Admin
 */
public class H_TaiKhoanRepository {
    
    public String getName(String tenDN) {
        String sql = "Select NhanVien.TenNhanVien from NhanVien inner join NguoiDung on NhanVien.MaNguoiDung = NguoiDung.MaNguoiDung where TenDangNhap = '" + tenDN + "'";
        TaiKhoanViewModel tkvm = new TaiKhoanViewModel();
        try (Connection conn = DbConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {                
                tkvm.setHoTen(rs.getString("TenNhanVien"));
                return tkvm.getHoTen();
            }

        } catch (Exception e) {
            e.printStackTrace();
        } 
        return "Loi";
    }
    public String getTenDN(String tenDN) {
        String sql = "Select NguoiDung.TenDangNhap from NhanVien inner join NguoiDung on NhanVien.MaNguoiDung = NguoiDung.MaNguoiDung where TenDangNhap = '" + tenDN + "'";
        TaiKhoanViewModel tkvm = new TaiKhoanViewModel();
        try (Connection conn = DbConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {                
                tkvm.setTenDN(rs.getString("TenDangNhap"));
                return tkvm.getTenDN();
            }

        } catch (Exception e) {
            e.printStackTrace();
        } 
        return "Loi";
    }
    public String getMK(String tenDN) {
        String sql = "Select NguoiDung.MatKhau from NhanVien inner join NguoiDung on NhanVien.MaNguoiDung = NguoiDung.MaNguoiDung where TenDangNhap = '" + tenDN + "'";
        TaiKhoanViewModel tkvm = new TaiKhoanViewModel();
        try (Connection conn = DbConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {                
                tkvm.setmK(rs.getString("MatKhau"));
                return tkvm.getmK();
            }

        } catch (Exception e) {
            e.printStackTrace();
        } 
        return "Loi";
    }
    public String getIdPerson(String tenDN) {
        String sql = "Select NhanVien.MaNhanVien from NhanVien inner join NguoiDung on NhanVien.MaNguoiDung = NguoiDung.MaNguoiDung where TenDangNhap = '" + tenDN + "'";
        TaiKhoanViewModel tkvm = new TaiKhoanViewModel();
        try (Connection conn = DbConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {                
                tkvm.setMaNV(rs.getInt("MaNhanVien"));
                return String.valueOf(tkvm.getMaNV());
            }

        } catch (Exception e) {
            e.printStackTrace();
        } 
        return "Loi";
    }
    
    public String getGioiTinh(String tenDN) {
        String sql = "Select NhanVien.GioiTinh from NhanVien inner join NguoiDung on NhanVien.MaNguoiDung = NguoiDung.MaNguoiDung where TenDangNhap = '" + tenDN + "'";
        TaiKhoanViewModel tkvm = new TaiKhoanViewModel();
        try (Connection conn = DbConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {                
                tkvm.setGioiTinh(rs.getString("GioiTinh"));
                return tkvm.getGioiTinh();
            }

        } catch (Exception e) {
            e.printStackTrace();
        } 
        return "Loi";
    }
    public String getVaiTro(String tenDN) {
        String sql = "Select NguoiDung.VaiTro from NhanVien inner join NguoiDung on NhanVien.MaNguoiDung = NguoiDung.MaNguoiDung where TenDangNhap = '" + tenDN + "'";
        TaiKhoanViewModel tkvm = new TaiKhoanViewModel();
        try (Connection conn = DbConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {                
                tkvm.setVaiTro(rs.getString("VaiTro"));
                if (tkvm.getVaiTro() == "staff") {
                    return "Nhân viên";
                } else {
                    return "Quản trị viên";
                }
                
            }

        } catch (Exception e) {
            e.printStackTrace();
        } 
        return "Loi";
    }
    public String getSDT(String tenDN) {
        String sql = "Select NhanVien.SoDienThoai from NhanVien inner join NguoiDung on NhanVien.MaNguoiDung = NguoiDung.MaNguoiDung where TenDangNhap = '" + tenDN + "'";
        TaiKhoanViewModel tkvm = new TaiKhoanViewModel();
        try (Connection conn = DbConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {                
                tkvm.setsDT(rs.getString("SoDienThoai"));
                return tkvm.getsDT();
            }

        } catch (Exception e) {
            e.printStackTrace();
        } 
        return "Loi";
    }
    public String getDiaChi(String tenDN) {
        String sql = "Select NhanVien.DiaChi from NhanVien inner join NguoiDung on NhanVien.MaNguoiDung = NguoiDung.MaNguoiDung where TenDangNhap = '" + tenDN + "'";
        TaiKhoanViewModel tkvm = new TaiKhoanViewModel();
        try (Connection conn = DbConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {                
                tkvm.setDiaChi(rs.getString("DiaChi"));
                return tkvm.getDiaChi();
            }

        } catch (Exception e) {
            e.printStackTrace();
        } 
        return "Loi";
    }
}
