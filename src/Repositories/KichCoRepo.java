/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repositories;

import Models.KichCo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author X1
 */
public class KichCoRepo {
    DbConnection dbConnection;
    
    //GETLIST
    public ArrayList<KichCo> getList(){
        String sql = "SELECT * FROM Size";
        ArrayList<KichCo> ls = new ArrayList<>();
        
        try (Connection conn = dbConnection.getConnection();
                PreparedStatement ps = conn.prepareCall(sql)){
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                Integer maSz = rs.getInt("MaSize");
                String tenSz = rs.getString("TenSize");
                
                KichCo size = new KichCo(maSz, tenSz);
                ls.add(size);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ls;
    }
    
    //GET_NAME BY ID SZ
    public KichCo getNameByID(String id){
        String sql = "SELECT * FROM Size WHERE MaSize = " + id +"" ;
        KichCo sz = new KichCo();
        
        try (Connection conn = dbConnection.getConnection();
                PreparedStatement ps = conn.prepareCall(sql)){
            ps.setObject(1, id);
            
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {                
                Integer maSz = rs.getInt("MaSize");
                String tenSz = rs.getString("TenSize");
                
                sz = new KichCo(maSz, tenSz);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sz;
    }
}
