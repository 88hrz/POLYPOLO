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
public class DanhMucRepository {
    DbConnection dbConnection;
    
    //GETLIST DANH MUC
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
}
