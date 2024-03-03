/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repositories;

import Models.NhanSu;
import ViewModels.NhanSuViewModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class NhanSuRepo {

    public ArrayList<NhanSuViewModel> getList() {
        String sql = "select MaNhanVien,TenNhanVien,TenDangNhap,GioiTinh,SoDienThoai,DiaChi,VaiTro from NhanVien "
                         + "inner join NguoiDung on NhanVien.MaNguoiDung = NguoiDung.MaNguoiDung";
        ArrayList<NhanSuViewModel> list = new ArrayList<>();
        try (Connection conn = DbConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql);) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                NhanSuViewModel spvm = new NhanSuViewModel(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7));
                list.add(spvm);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    
    public ArrayList<NhanSu> getListTenNV() {
        String sql = "SELECT * FROM NhanVien WHERE Deleted !=1;";
        ArrayList<NhanSu> list = new ArrayList<>();
        
        try (Connection conn = DbConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql);) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
               String tenNV = rs.getString("TenNhanVien");
               String gioiTinh = rs.getString("GioiTinh");
               String soDT = rs.getString("SoDienThoai");
               String ngaySinh = rs.getString("NgaySinh");
               String diaChi = rs.getString("DiaChi");
               Integer maNguoiDung = rs.getInt("MaNguoiDung");
               
                NhanSu ns = new NhanSu(tenNV, gioiTinh, ngaySinh, soDT, diaChi, maNguoiDung);
                list.add(ns);
               
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public Boolean AddNew(NhanSu sp) {
        String sql = " INSERT INTO NhanVien(MaNguoiDung,TenNhanVien,GioiTinh,SoDienThoai,DiaChi) VALUES(?,?,?,?,?) ";
        
        try (Connection conn = DbConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setObject(1, sp.getMaNguoiDung());
            ps.setObject(2, sp.getTenNhanVien());
            ps.setObject(3, sp.getGioiTinh());
            ps.setObject(4, sp.getSoDienThoai());
            ps.setObject(5, sp.getDiaChi());
            int kq = ps.executeUpdate();
            if (kq > 0) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public Boolean delete(String maNV) {
        String sql = "DELETE FROM [dbo].[NhanVien]\n"
                + "      WHERE [MaNhanVien] = '" + maNV + "'";
        
        try (Connection conn = DbConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            int ketQua = ps.executeUpdate();
            if (ketQua > 0) {
                return true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public Boolean updateNew(NhanSu ns){
        String sql = "update NhanVien set TenNhanVien = ?, GioiTinh = ?,SoDienThoai = ?,DiaChi = ? where MaNhanVien = ? ";
        try (Connection conn = DbConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setObject(1, ns.getTenNhanVien());
            ps.setObject(2, ns.getGioiTinh());
            ps.setObject(3, ns.getSoDienThoai());
            ps.setObject(4, ns.getDiaChi());
            ps.setObject(5, ns.getMaNguoiDung());
            int kq = ps.executeUpdate();
                return kq > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public ArrayList<NhanSuViewModel> searchByName(String TenNhanVien) {
        String sql = "select MaNhanVien,TenNhanVien,TenDangNhap,GioiTinh,SoDienThoai,DiaChi,VaiTro from NhanVien "
                + "inner join NguoiDung on NhanVien.MaNguoiDung = NguoiDung.MaNguoiDung Where TenNhanVien like '%" + TenNhanVien + "%'";
        ArrayList<NhanSuViewModel> list = new ArrayList<>();
        try (Connection conn = DbConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql);) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                NhanSuViewModel spvm = new NhanSuViewModel(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7));
                list.add(spvm);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

}
