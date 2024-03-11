/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repositories;

import Models.NhanSu;
import Models.TaiKhoan;
import Models.User;
import ViewModels.UserViewModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author X1
 */
public class UserRepository {
    DbConnection dbConnection;

    public User getListByUserId(String userID){
        String sql = "SELECT * FROM NguoiDung nd WHERE nd.TenDangNhap = ? ";
        User u = new User();
        
        try (Connection conn = dbConnection.getConnection();
                PreparedStatement ps = conn.prepareCall(sql)){
            ps.setObject(1, userID);
            
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {                
                String username = rs.getString("TenDangNhap");
                String password = rs.getString("MatKhau");
                String role = rs.getString("VaiTro");
                
                u = new User(null, username, password, role);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return u;
    }
    //CHECKLOGIN
    public Boolean checkLogin(String userID, String passCode){
        String sql = "SELECT * FROM NguoiDung WHERE TenDangNhap = ? AND MatKhau = ?";
        User u = null;
        
        try (Connection conn = dbConnection.getConnection();
                PreparedStatement ps = conn.prepareCall(sql)){
            ps.setObject(1, userID);
            ps.setObject(2, passCode);
            
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {                
                String username = rs.getString("TenDangNhap");
                String password = rs.getString("MatKhau");
                
                u = new User(userID, passCode);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (u == null) {
            return false;
        } else {
            return true;
        }
    }
    
    //GETLIST
    public ArrayList<UserViewModel> getList(){
        String sql = "SELECT nv.MaNhanVien, nd.TenDangNhap, nd.MatKhau\n" +
"            , nv.TenNhanVien, nv.SoDienThoai, nd.VaiTro FROM NguoiDung nd INNER JOIN NhanVien nv ON nd.MaNguoiDung = nv.MaNguoiDung\n" +
"            WHERE nv.Deleted!=1 AND nd.Deleted!=1";
        ArrayList<UserViewModel> ls = new ArrayList<>();
        
        try (Connection conn = dbConnection.getConnection();
                PreparedStatement ps = conn.prepareCall(sql)){
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {                
                Integer maND = rs.getInt("MaNhanVien");
                String  tenDN = rs.getString("TenDangNhap");
                String mk = rs.getString("MatKhau");
                String hoTen = rs.getString("TenNhanVien");
                String vaiTro = rs.getString("VaiTro");
                String soDT = rs.getString("SoDienThoai");
                
                UserViewModel u = new UserViewModel(maND, tenDN, hoTen, mk, soDT, vaiTro);
                ls.add(u);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ls;
    }

    public UserViewModel getListById(Integer id){
        String sql = "SELECT nd.MaNguoiDung, nd.TenDangNhap, nd.MatKhau\n" +
"                   , nv.TenNhanVien, nv.SoDienThoai, nv.GioiTinh, nv.DiaChi, nv.NgaySinh, nd.VaiTro FROM NguoiDung nd INNER JOIN NhanVien nv ON nd.MaNguoiDung = nv.MaNguoiDung\n" +
"                   WHERE nv.Deleted !=1 AND nd.Deleted!=1 AND nd.MaNguoiDung = ?";
        UserViewModel u = new UserViewModel();
        
        try (Connection conn = dbConnection.getConnection();
                PreparedStatement ps = conn.prepareCall(sql)){
            ps.setObject(1, id);
            
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {                
                Integer maND = rs.getInt("MaNguoiDung");
                String  tenDN = rs.getString("TenDangNhap");
                String mk = rs.getString("MatKhau");
                String hoTen = rs.getString("TenNhanVien");
                String vaiTro = rs.getString("VaiTro");
                String soDT = rs.getString("SoDienThoai");
                String diaC = rs.getString("DiaChi");
                Date ngayS = rs.getDate("NgaySinh");
                String gioiT = rs.getString("GioiTinh");
   
                u = new UserViewModel(maND, tenDN, hoTen, mk, soDT, vaiTro, diaC, gioiT, ngayS);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return u;
    }
    
    //GETLIST USER
    public ArrayList<User> getListUser(){
        String sql = "SELECT * FROM NguoiDung WHERE NguoiDung.Deleted !=1";
        ArrayList<User> ls = new  ArrayList<>();
        
        try (Connection conn = dbConnection.getConnection();
                PreparedStatement ps = conn.prepareCall(sql)){
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {                
                Integer id = rs.getInt("MaNguoiDung");
                String userId = rs.getString("TenDangNhap");
                String passCode = rs.getString("MatKhau");
                String role = rs.getString("VaiTro");
                
                User u = new User(id, userId, passCode, role);
                ls.add(u);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ls;
    }
    public ArrayList<NhanSu> getAll() {
        String sql = "SELECT * FROM NhanVien WHERE Deleted !=1";
        ArrayList<NhanSu> list = new ArrayList<>();
//        ArrayList<User> ls = new  ArrayList<>(); 

        try (Connection conn = dbConnection.getConnection(); PreparedStatement ps = conn.prepareCall(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
//                Integer id = rs.getInt("MaNguoiDung");
//                String userId = rs.getString("TenDangNhap");
//                String passCode = rs.getString("MatKhau");
//                String role = rs.getString("VaiTro");
//                
//                User u = new User(id, userId, passCode, role);
//ls.add(u);
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

    //SEARCH
    public ArrayList<UserViewModel> getListBySearch(String name){
        String sql = "SELECT nd.MaNguoiDung, nd.TenDangNhap, nd.MatKhau, nv.TenNhanVien, nd.VaiTro, nv.SoDienThoai FROM NguoiDung nd \n" +
                        "INNER JOIN NhanVien nv ON nd.MaNguoiDung = nv.MaNguoiDung\n" +
                        "WHERE nv.Deleted!=1 AND nv.TenNhanVien LIKE '%"+ name + "%'";
        ArrayList<UserViewModel> ls = new ArrayList<>();
        
        try (Connection conn = dbConnection.getConnection();
                PreparedStatement ps = conn.prepareCall(sql)){
            
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {                
                Integer maND = rs.getInt("MaNguoiDung");
                String  tenDN = rs.getString("TenDangNhap");
                String mk = rs.getString("MatKhau");
                String hoTen = rs.getString("TenNhanVien");
                String vaiTro = rs.getString("VaiTro");
                String soDT = rs.getString("SoDienThoai");;
                
                UserViewModel u = new UserViewModel(maND, tenDN, hoTen, vaiTro, mk, soDT);
                ls.add(u);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ls;
    }
    //CHECK ID
    public Boolean checkName(String name){
        String sql = "SELECT COUNT(*) FROM NguoiDung WHERE TenDangNhap = ?";
        
        try (Connection conn = dbConnection.getConnection();
                PreparedStatement ps = conn.prepareCall(sql)){
            ps.setObject(1, name);
            
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
    
    
    //ADD ACCOUNT
    public Boolean addAccount(TaiKhoan tk){
        String sql = "INSERT INTO NguoiDung(TenDangNhap,MatKhau,VaiTro,Deleted) \n" +
                                        "VALUES(?,?,?,0);\n"
                + " INSERT INTO NhanVien(MaNguoiDung,TenNhanVien,GioiTinh,NgaySinh,SoDienThoai,DiaChi,Deleted)\n" +
"                                       VALUES(?,?,?,?,?,?,0)";
        
        try (Connection conn = dbConnection.getConnection();
                PreparedStatement ps = conn.prepareCall(sql)){
            ps.setObject(1, tk.getTenDN());
            ps.setObject(2, tk.getMatK());
            ps.setObject(3, tk.getVaiT());
            
            ps.setObject(4, tk.getMaND());
            ps.setObject(5, tk.getTenNV());
            ps.setObject(6, tk.getGioiT());
            ps.setObject(7, tk.getNgayS());
            ps.setObject(8, tk.getSoDT());
            ps.setObject(9, tk.getDiaC());
            
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
    public Boolean updateAccount(TaiKhoan tk){
        String sql = "UPDATE NhanVien\n" +
                        "SET TenNhanVien = ?, SoDienThoai = ?,DiaChi = ?,GioiTinh = ?,NgaySinh = ?\n" +
                        "WHERE Deleted!=1 AND  MaNguoiDung = ?; "
                + "UPDATE NguoiDung SET TenDangNhap = ? , MatKhau = ?, VaiTro = ? WHERE MaNguoiDung = ?;";
        
        try (Connection conn = dbConnection.getConnection();
                PreparedStatement ps = conn.prepareCall(sql)){
            ps.setObject(1, tk.getTenNV());
            ps.setObject(2, tk.getSoDT());
            ps.setObject(3, tk.getDiaC());
            ps.setObject(4, tk.getGioiT());
            ps.setObject(5, tk.getMaND());
            
            ps.setObject(6, tk.getTenDN());
            ps.setObject(7, tk.getMatK());
            ps.setObject(8, tk.getVaiT());
            ps.setObject(9, tk.getMaND());
            
            
            int check = ps.executeUpdate();
            if (check>0) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    //HIDE
    public Boolean hideAccount(User u){
        String sql = "UPDATE NguoiDung SET Deleted = 1 WHERE MaNguoiDung = ?";
        
        try (Connection conn = dbConnection.getConnection();
                PreparedStatement ps = conn.prepareCall(sql)){
            ps.setObject(1, u.getUserID());
            
            int check = ps.executeUpdate();
            if (check>0) {
                return true;
            }        
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
   
}
