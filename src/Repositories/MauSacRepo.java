/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repositories;

import Models.MauSac;
import java.util.ArrayList;
import java.sql.*;

/**
 *
 * @author X1
 */
public class MauSacRepo {
    DbConnection dbConnection;
    
    //GETLIST MAUSAC
    public ArrayList<MauSac> getList(){
        String sql = "SELECT * FROM MauSac";
        ArrayList<MauSac> ls = new ArrayList<>();
        
        try (Connection conn = dbConnection.getConnection();
                PreparedStatement ps = conn.prepareCall(sql)){
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                Integer maMau = rs.getInt("MaMau");
                String tenMau = rs.getString("TenMau");
                
                MauSac ms = new MauSac(maMau, tenMau);
                ls.add(ms);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ls;
    }
    
    //GET_NAME BY ID COLOR
    public MauSac getNameByID(String id){
        String sql = "SELECT * FROM MauSac WHERE MaMau = "+ id +"";
        MauSac ms = new MauSac();
        
        try (Connection conn = dbConnection.getConnection();
                PreparedStatement ps = conn.prepareCall(sql)){
            ps.setObject(1, id);
            
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {                
                Integer maMau = rs.getInt("MaMau");
                String tenMau = rs.getString("TenMau");
                ms = new MauSac(maMau, tenMau);
            }  
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ms;
    }
}
