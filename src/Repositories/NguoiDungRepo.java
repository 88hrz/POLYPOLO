/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repositories;

import Models.NguoiDung;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author X1
 */
public class NguoiDungRepo {
    
    
    public ArrayList<NguoiDung> getListGV() {
        String sql = "select * from NguoiDung";
        ArrayList<NguoiDung> list = new ArrayList<>();
        try (Connection conn = DbConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                NguoiDung km = new NguoiDung(rs.getInt(1), rs.getString(2),
                        rs.getString(3), rs.getString(4));
                list.add(km);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public NguoiDung findID(String tenND) {
        String sql = "select MaNguoiDung, TenDangNhap, MatKhau, VaiTro from NguoiDung "
                + "where VaiTro = ?";
        NguoiDung Km = new NguoiDung();
        try (Connection conn = DbConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, tenND);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                NguoiDung km = new NguoiDung(rs.getInt(1), rs.getString(2),
                        rs.getString(3), rs.getString(4));
                Km = km;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Km;
    }
}
