/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repositories;

import Models.User;
import ViewModels.UserViewModel;
import Views.QuanLyTaiKhoan;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author X1
 */
public class UserRepository {
    DbConnection dbConnection;

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
    //LAY I4
    public UserViewModel checkI4(String userID, String passCode) {
    String sql = "SELECT nd.MaNguoiDung, nd.TenDangNhap, nd.MatKhau, nv.TenNhanVien, nv.GioiTinh, nd.VaiTro, nv.SoDienThoai, nv.DiaChi FROM NguoiDung nd\n" +
                    "LEFT JOIN NhanVien nv ON nd.MaNguoiDung = nv.MaNguoiDung\n" +
                    "WHERE nd.TenDangNhap = ? AND nd.MatKhau = ?";
    UserViewModel u = new UserViewModel();
    
    try (Connection conn = dbConnection.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) { 
        ps.setString(1, userID);
        ps.setString(2, passCode);
        
        ResultSet rs = ps.executeQuery();
        if (rs.next()) { 
            Integer maND = rs.getInt("MaNguoiDung");
            String tenDN = rs.getString("TenDangNhap");
            String mk = rs.getString("MatKhau");
            String hoTen = rs.getString("TenNhanVien");
            String gioiTinh = rs.getString("GioiTinh");
            String vaiTro = rs.getString("VaiTro");
            Integer soDT = rs.getInt("SoDienThoai");
            String diaChi = rs.getString("DiaChi");
            
            u = new UserViewModel(maND, soDT, tenDN, hoTen, gioiTinh, vaiTro, diaChi, mk);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return u;
}

    //GETLIST
    public ArrayList<UserViewModel> getList(){
        String sql = "SELECT nd.MaNguoiDung, nd.TenDangNhap, nd.MatKhau, nv.TenNhanVien, nv.GioiTinh, nd.VaiTro, nv.SoDienThoai, nv.DiaChi FROM NguoiDung nd\n" +
                    "LEFT JOIN NhanVien nv ON nd.MaNguoiDung = nv.MaNguoiDung\n" +
                    "WHERE nd.Deleted != 1";
        ArrayList<UserViewModel> ls = new ArrayList<>();
        
        try (Connection conn = dbConnection.getConnection();
                PreparedStatement ps = conn.prepareCall(sql)){
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {                
                Integer maND = rs.getInt("MaNguoiDung");
                String  tenDN = rs.getString("TenDangNhap");
                String mk = rs.getString("MatKhau");
                String hoTen = rs.getString("TenNhanVien");
                String gioiTinh = rs.getString("GioiTinh");
                String vaiTro = rs.getString("VaiTro");
                Integer soDT = rs.getInt("SoDienThoai");
                String diaChi = rs.getString("DiaChi");
                
                UserViewModel u = new UserViewModel(maND, soDT, tenDN, hoTen, gioiTinh, vaiTro, diaChi, mk);
                ls.add(u);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ls;
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
    //SEARCH
    public ArrayList<UserViewModel> getListBySearch(String name){
        String sql = "SELECT nd.MaNguoiDung, nd.TenDangNhap, nd.MatKhau, nv.TenNhanVien, nv.GioiTinh, nd.VaiTro, nv.SoDienThoai, nv.DiaChi FROM NguoiDung nd \n" +
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
                String gioiTinh = rs.getString("GioiTinh");
                String vaiTro = rs.getString("VaiTro");
                Integer soDT = rs.getInt("SoDienThoai");
                String diaChi = rs.getString("DiaChi");
                
                UserViewModel u = new UserViewModel(maND, soDT, tenDN, hoTen, gioiTinh, vaiTro, diaChi, mk);
                ls.add(u);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ls;
    }
    //CHECK ID
    public Boolean checkId(String id){
        String sql = "SELECT COUNT(*) FROM NguoiDung WHERE MaNguoiDung = ?";
        
        try (Connection conn = dbConnection.getConnection();
                PreparedStatement ps = conn.prepareCall(sql)){
            ps.setObject(1, id);
            
            int check = ps.executeUpdate();
            if (check>0) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    //ADD ACCOUNT
    public Boolean addAccount(User u){
        String sql = "INSERT INTO NguoiDung(TenDangNhap,MatKhau,VaiTro, Deleted) VALUES(?,?,?,0);";
        
        try (Connection conn = dbConnection.getConnection();
                PreparedStatement ps = conn.prepareCall(sql)){
            ps.setObject(1, u.getUserName());
            ps.setObject(2, u.getPassCode());
            ps.setObject(3, u.getRole());
            
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
    public Boolean updateAccount(User u){
        String sql = "UPDATE NguoiDung SET TenDangNhap = ? , MatKhau = ?, VaiTro = ? WHERE MaNguoiDung = ?";
        
        try (Connection conn = dbConnection.getConnection();
                PreparedStatement ps = conn.prepareCall(sql)){
            ps.setObject(1, u.getUserName());
            ps.setObject(2, u.getPassCode());
            ps.setObject(3, u.getRole());
            ps.setObject(4, u.getUserID());
            
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
