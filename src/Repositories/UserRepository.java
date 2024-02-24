/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repositories;

import Models.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author X1
 */
public class UserRepository {
    DbConnection dbConnection;

    
    public Boolean checkLogin(String userID, String passCode){
        String sql = "SELECT * FROM NhanVien WHERE TenDangNhap = ? AND MatKhau = ?";
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
}
