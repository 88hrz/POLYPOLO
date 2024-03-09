/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repositories;

import Models.HoaDon;
import ViewModels.HD_HoaDonViewModel;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author hmail
 */
public class ThongKeRespository {

    DbConnection dbConnection;

    //LOAD PIE_CHART
    public ArrayList<Integer> showYear() {
        String sql = "SELECT YEAR(NgayLap) AS NamLap FROM HoaDon WHERE HoaDon.Deleted!=1 GROUP BY YEAR(NgayLap);";
        ArrayList<Integer> years = new ArrayList<>();
                
        try (Connection conn = dbConnection.getConnection();
                PreparedStatement ps = conn.prepareCall(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {                
                Integer namLap = rs.getInt("NamLap");
                years.add(namLap);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return years;
    }
    public ArrayList<Integer> showMonth() {
        String sql = "SELECT MONTH(NgayLap) AS ThangLap FROM HoaDon WHERE HoaDon.Deleted!=1 GROUP BY MONTH(NgayLap);";
        ArrayList<Integer> months = new ArrayList<>();
                
        try (Connection conn = dbConnection.getConnection();
                PreparedStatement ps = conn.prepareCall(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {                
                Integer namLap = rs.getInt("ThangLap");
                months.add(namLap);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return months;
    }
    
    
    
    //
    public ArrayList<HD_HoaDonViewModel> getListHoaDonView(Date ngay) {
        String sql = "SELECT hdct.MaHoaDonChiTiet, kh.TenKhachHang, kh.SoDienThoai ,\n"
                + "					 hd.PhuongThucThanhToan, hd.TongTien , hd.NgayLap, hd.TenNhanVien FROM HoaDonChiTiet hdct\n"
                + "                INNER JOIN KhachHang kh ON kh.MaHoaDon = hdct.MaHoaDon\n"
                + "                INNER JOIN HoaDon hd ON hd.MaHoaDon = hdct.MaHoaDon where NgayLap = ?";
        ArrayList<HD_HoaDonViewModel> lsHoaDon = new ArrayList<>();

        try (Connection conn = dbConnection.getConnection(); PreparedStatement ps = conn.prepareCall(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Integer maHD = rs.getInt("MaHoaDonChiTiet");
                String tenKH = rs.getString("TenKhachHang");
                String soDT = rs.getString("SoDienThoai");
                String phuongThuc = rs.getString("PhuongThucThanhToan");
                Double tongTien = rs.getDouble("TongTien");
                String ngayLap = rs.getString("NgayLap");
                String tenNV = rs.getString("TenNhanVien");

                HD_HoaDonViewModel hoaDon = new HD_HoaDonViewModel(maHD, tenKH, soDT, phuongThuc, tongTien, ngayLap, tenNV);
                lsHoaDon.add(hoaDon);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lsHoaDon;
    }

    public HD_HoaDonViewModel tongDoanhThuNgay() {
        String sql = "SELECT CAST(COALESCE(SUM(TongTien), 0) AS VARCHAR(MAX)) AS TongDoanhThu\n" +
                        "FROM HoaDon\n" +
                        "WHERE NgayLap = CONVERT(date, GETDATE());";

        HD_HoaDonViewModel hd = new HD_HoaDonViewModel();
        try (Connection conn = dbConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                hd = new HD_HoaDonViewModel(rs.getDouble(1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return hd;
    }

    public HD_HoaDonViewModel tongDoanhThuThang() {
        String sql = "SELECT CAST(COALESCE(SUM(TongTien), 0) AS VARCHAR(MAX)) AS TongDoanhThu\n" +
                        "FROM HoaDon\n" +
                        "WHERE YEAR(NgayLap) = YEAR(GETDATE())\n" +
                        "AND MONTH(NgayLap) = MONTH(GETDATE());";

        HD_HoaDonViewModel hd = new HD_HoaDonViewModel();
        try (Connection conn = dbConnection.getConnection(); PreparedStatement ps = conn.prepareCall(sql)) {
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                hd = new HD_HoaDonViewModel(rs.getDouble(1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return hd;
    }

    public HD_HoaDonViewModel tongDoanhThuNam() {
        String sql = "SELECT CAST(COALESCE(SUM(TongTien), 0) AS VARCHAR(MAX)) AS TongDoanhThu\n"
                + "FROM HoaDon\n"
                + "WHERE YEAR(NgayLap) = YEAR(GETDATE());";

        HD_HoaDonViewModel hd = new HD_HoaDonViewModel();
        try (Connection conn = dbConnection.getConnection(); PreparedStatement ps = conn.prepareCall(sql)) {
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                hd = new HD_HoaDonViewModel(rs.getDouble(1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return hd;
    }

    public HoaDon tongDaTT() {
        String sql = "SELECT COUNT(*) AS TongSoDonHangDaThanhToan\n"
                + "FROM HoaDon\n"
                + "WHERE TrangThai = N'Đã thanh toán';";

        HoaDon hd = new HoaDon();
        try (Connection conn = dbConnection.getConnection(); PreparedStatement ps = conn.prepareCall(sql)) {
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                hd = new HoaDon("4");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return hd;
    }

    public ArrayList<HD_HoaDonViewModel> getListHoaDonView(Integer maHDD) {
        String sql = "SELECT hdct.MaHoaDonChiTiet, kh.TenKhachHang, kh.SoDienThoai\n"
                + ", hd.PhuongThucThanhToan, hd.TongTien , hd.NgayLap, hd.TenNhanVien FROM HoaDonChiTiet hdct\n"
                + "INNER JOIN KhachHang kh ON kh.MaHoaDon = hdct.MaHoaDon\n"
                + "INNER JOIN HoaDon hd ON hd.MaHoaDon = hdct.MaHoaDon where hdct.MaHoaDon = " + maHDD;
        ArrayList<HD_HoaDonViewModel> lsHoaDon = new ArrayList<>();

        try (Connection conn = dbConnection.getConnection(); PreparedStatement ps = conn.prepareCall(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Integer maHD = rs.getInt("MaHoaDonChiTiet");
                String tenKH = rs.getString("TenKhachHang");
                String soDT = rs.getString("SoDienThoai");
                String phuongThuc = rs.getString("PhuongThucThanhToan");
                Double tongTien = rs.getDouble("TongTien");
                String ngayLap = rs.getString("NgayLap");
                String tenNV = rs.getString("TenNhanVien");

                HD_HoaDonViewModel hoaDon = new HD_HoaDonViewModel(maHD, tenKH, soDT, phuongThuc, tongTien, ngayLap, tenNV);
                lsHoaDon.add(hoaDon);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lsHoaDon;
    }

    public ArrayList<HD_HoaDonViewModel> getListHoaDonView(String bd, String kt) {
        String sql = "SELECT hdct.MaHoaDonChiTiet, kh.TenKhachHang, kh.SoDienThoai\n"
                + ", hd.PhuongThucThanhToan, hd.TongTien , hd.NgayLap, hd.TenNhanVien FROM HoaDonChiTiet hdct\n"
                + "INNER JOIN KhachHang kh ON kh.MaHoaDon = hdct.MaHoaDon\n"
                + "INNER JOIN HoaDon hd ON hd.MaHoaDon = hdct.MaHoaDon where hd.NgayLap BETWEEN '" + bd + "' and '" + kt + "'";
        ArrayList<HD_HoaDonViewModel> lsHoaDon = new ArrayList<>();

        try (Connection conn = dbConnection.getConnection(); PreparedStatement ps = conn.prepareCall(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Integer maHD = rs.getInt("MaHoaDonChiTiet");
                String tenKH = rs.getString("TenKhachHang");
                String soDT = rs.getString("SoDienThoai");
                String phuongThuc = rs.getString("PhuongThucThanhToan");
                Double tongTien = rs.getDouble("TongTien");
                String ngayLap = rs.getString("NgayLap");
                String tenNV = rs.getString("TenNhanVien");

                HD_HoaDonViewModel hoaDon = new HD_HoaDonViewModel(maHD, tenKH, soDT, phuongThuc, tongTien, ngayLap, tenNV);
                lsHoaDon.add(hoaDon);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lsHoaDon;
    }

    public ArrayList<HD_HoaDonViewModel> Tim(String Ngay) {
        String sql = "SELECT hdct.MaHoaDonChiTiet, kh.TenKhachHang, kh.SoDienThoai\n"
                + ", hd.PhuongThucThanhToan, hd.TongTien , hd.NgayLap, hd.TenNhanVien FROM HoaDonChiTiet hdct\n"
                + "INNER JOIN KhachHang kh ON kh.MaHoaDon = hdct.MaHoaDon\n"
                + "INNER JOIN HoaDon hd ON hd.MaHoaDon = hdct.MaHoaDon where hd.NgayLap = ?";
        ArrayList<HD_HoaDonViewModel> lsHoaDon = new ArrayList<>();

        try (Connection conn = dbConnection.getConnection(); PreparedStatement ps = conn.prepareCall(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Integer maHD = rs.getInt("MaHoaDonChiTiet");
                String tenKH = rs.getString("TenKhachHang");
                String soDT = rs.getString("SoDienThoai");
                String phuongThuc = rs.getString("PhuongThucThanhToan");
                Double tongTien = rs.getDouble("TongTien");
                String ngayLap = rs.getString("NgayLap");
                String tenNV = rs.getString("TenNhanVien");

                HD_HoaDonViewModel hoaDon = new HD_HoaDonViewModel(maHD, tenKH, soDT, phuongThuc, tongTien, ngayLap, tenNV);
                lsHoaDon.add(hoaDon);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lsHoaDon;
    }

    public ArrayList<HD_HoaDonViewModel> Loc(int ngay) {
        String sql = "SELECT hdct.MaHoaDonChiTiet, kh.TenKhachHang, kh.SoDienThoai\n"
                + "                , hd.PhuongThucThanhToan, hd.TongTien , hd.NgayLap, hd.TenNhanVien FROM HoaDonChiTiet hdct\n"
                + "               INNER JOIN KhachHang kh ON kh.MaHoaDon = hdct.MaHoaDon\n"
                + "                 INNER JOIN HoaDon hd ON hd.MaHoaDon = hdct.MaHoaDon where NgayLap = " + ngay;
        ArrayList<HD_HoaDonViewModel> lsHoaDon = new ArrayList<>();

        try (Connection conn = dbConnection.getConnection(); PreparedStatement ps = conn.prepareCall(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Integer maHD = rs.getInt("MaHoaDonChiTiet");
                String tenKH = rs.getString("TenKhachHang");
                String soDT = rs.getString("SoDienThoai");
                String phuongThuc = rs.getString("PhuongThucThanhToan");
                Double tongTien = rs.getDouble("TongTien");
                String ngayLap = rs.getString("NgayLap");
                String tenNV = rs.getString("TenNhanVien");

                HD_HoaDonViewModel hoaDon = new HD_HoaDonViewModel(maHD, tenKH, soDT, phuongThuc, tongTien, ngayLap, tenNV);
                lsHoaDon.add(hoaDon);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lsHoaDon;
    }
}
