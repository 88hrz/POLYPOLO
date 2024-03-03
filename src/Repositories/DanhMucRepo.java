/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repositories;

import Models.DanhMuc;
import java.util.ArrayList;
import java.sql.*;

/**
 *
 * @author X1
 */
public class DanhMucRepo {
    DbConnection dbConnection;
    
    //GET LIST
    public ArrayList<DanhMuc> getList(){
        String sql = "SELECT * FROM DanhMuc";
        ArrayList<DanhMuc> ls = new ArrayList<>();
        
        try (Connection conn = dbConnection.getConnection();
                PreparedStatement ps = conn.prepareCall(sql)){
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {                
                Integer maDM = rs.getInt("MaDanhMuc");
                String tenDM = rs.getString("TenDanhMuc");
                String trangThai = rs.getString("TrangThai");
                
                DanhMuc dm = new DanhMuc(maDM, tenDM, trangThai);
                ls.add(dm);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ls;
    }
    
    //GET_ID
    public DanhMuc getIdByName(String name){
        String sql = "SELECT MaDanhMuc, TenDanhMuc FROM DanhMuc WHERE TenDanhMuc = ?";
        DanhMuc dm = null;
        
        try (Connection conn = dbConnection.getConnection();
                PreparedStatement ps = conn.prepareCall(sql)){
            ps.setObject(1, name);
            
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {                
                Integer maDM = rs.getInt("MaDanhMuc");
                String tenDM = rs.getString("TenDanhMuc");
                
                dm = new DanhMuc(maDM, tenDM);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dm;
    }
}
