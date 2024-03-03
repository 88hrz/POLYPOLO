/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repositories;

import Models.HoaDon;
import ViewModels.HoaDonViewModel;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author hmail
 */
public class ThongKeRespository {

    DbConnection dbConnection;

    public ArrayList<HoaDonViewModel> getListHoaDonView(Date ngay) {
        String sql = "SELECT hdct.MaHoaDonChiTiet, kh.TenKhachHang, kh.SoDienThoai ,\n"
                + "					 hd.PhuongThucThanhToan, hd.TongTien , hd.NgayLap, hd.TenNhanVien FROM HoaDonChiTiet hdct\n"
                + "                INNER JOIN KhachHang kh ON kh.MaHoaDon = hdct.MaHoaDon\n"
                + "                INNER JOIN HoaDon hd ON hd.MaHoaDon = hdct.MaHoaDon where NgayLap = ?";
        ArrayList<HoaDonViewModel> lsHoaDon = new ArrayList<>();

        try (Connection conn = dbConnection.getConnection(); PreparedStatement ps = conn.prepareCall(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Integer maHD = rs.getInt("MaHoaDonChiTiet");
                String tenKH = rs.getString("TenKhachHang");
                Integer soDT = rs.getInt("SoDienThoai");
                String phuongThuc = rs.getString("PhuongThucThanhToan");
                Double tongTien = rs.getDouble("TongTien");
                String ngayLap = rs.getString("NgayLap");
                String tenNV = rs.getString("TenNhanVien");

                HoaDonViewModel hoaDon = new HoaDonViewModel(maHD, tenKH, soDT, phuongThuc, tongTien, ngayLap, tenNV);
                lsHoaDon.add(hoaDon);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lsHoaDon;
    }

    public HoaDonViewModel tongDoanhThuNgay() {
        String sql = "SELECT CAST(COALESCE(SUM(TongTien), 0) AS VARCHAR(MAX)) AS TongDoanhThu\n" +
                        "FROM HoaDon\n" +
                        "WHERE NgayLap = CONVERT(date, GETDATE());";

        HoaDonViewModel hd = new HoaDonViewModel();
        try (Connection conn = dbConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                hd = new HoaDonViewModel(rs.getDouble(1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return hd;
    }

    public HoaDonViewModel tongDoanhThuThang() {
        String sql = "SELECT CAST(COALESCE(SUM(TongTien), 0) AS VARCHAR(MAX)) AS TongDoanhThu\n" +
                        "FROM HoaDon\n" +
                        "WHERE YEAR(NgayLap) = YEAR(GETDATE())\n" +
                        "AND MONTH(NgayLap) = MONTH(GETDATE());";

        HoaDonViewModel hd = new HoaDonViewModel();
        try (Connection conn = dbConnection.getConnection(); PreparedStatement ps = conn.prepareCall(sql)) {
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                hd = new HoaDonViewModel(rs.getDouble(1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return hd;
    }

    public HoaDonViewModel tongDoanhThuNam() {
        String sql = "SELECT CAST(COALESCE(SUM(TongTien), 0) AS VARCHAR(MAX)) AS TongDoanhThu\n"
                + "FROM HoaDon\n"
                + "WHERE YEAR(NgayLap) = YEAR(GETDATE());";

        HoaDonViewModel hd = new HoaDonViewModel();
        try (Connection conn = dbConnection.getConnection(); PreparedStatement ps = conn.prepareCall(sql)) {
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                hd = new HoaDonViewModel(rs.getDouble(1));
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

    public ArrayList<HoaDonViewModel> getListHoaDonView(Integer maHDD) {
        String sql = "SELECT hdct.MaHoaDonChiTiet, kh.TenKhachHang, kh.SoDienThoai\n"
                + ", hd.PhuongThucThanhToan, hd.TongTien , hd.NgayLap, hd.TenNhanVien FROM HoaDonChiTiet hdct\n"
                + "INNER JOIN KhachHang kh ON kh.MaHoaDon = hdct.MaHoaDon\n"
                + "INNER JOIN HoaDon hd ON hd.MaHoaDon = hdct.MaHoaDon where hdct.MaHoaDon = " + maHDD;
        ArrayList<HoaDonViewModel> lsHoaDon = new ArrayList<>();

        try (Connection conn = dbConnection.getConnection(); PreparedStatement ps = conn.prepareCall(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Integer maHD = rs.getInt("MaHoaDonChiTiet");
                String tenKH = rs.getString("TenKhachHang");
                Integer soDT = rs.getInt("SoDienThoai");
                String phuongThuc = rs.getString("PhuongThucThanhToan");
                Double tongTien = rs.getDouble("TongTien");
                String ngayLap = rs.getString("NgayLap");
                String tenNV = rs.getString("TenNhanVien");

                HoaDonViewModel hoaDon = new HoaDonViewModel(maHD, tenKH, soDT, phuongThuc, tongTien, ngayLap, tenNV);
                lsHoaDon.add(hoaDon);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lsHoaDon;
    }

    public ArrayList<HoaDonViewModel> getListHoaDonView(String bd, String kt) {
        String sql = "SELECT hdct.MaHoaDonChiTiet, kh.TenKhachHang, kh.SoDienThoai\n"
                + ", hd.PhuongThucThanhToan, hd.TongTien , hd.NgayLap, hd.TenNhanVien FROM HoaDonChiTiet hdct\n"
                + "INNER JOIN KhachHang kh ON kh.MaHoaDon = hdct.MaHoaDon\n"
                + "INNER JOIN HoaDon hd ON hd.MaHoaDon = hdct.MaHoaDon where hd.NgayLap BETWEEN '" + bd + "' and '" + kt + "'";
        ArrayList<HoaDonViewModel> lsHoaDon = new ArrayList<>();

        try (Connection conn = dbConnection.getConnection(); PreparedStatement ps = conn.prepareCall(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Integer maHD = rs.getInt("MaHoaDonChiTiet");
                String tenKH = rs.getString("TenKhachHang");
                Integer soDT = rs.getInt("SoDienThoai");
                String phuongThuc = rs.getString("PhuongThucThanhToan");
                Double tongTien = rs.getDouble("TongTien");
                String ngayLap = rs.getString("NgayLap");
                String tenNV = rs.getString("TenNhanVien");

                HoaDonViewModel hoaDon = new HoaDonViewModel(maHD, tenKH, soDT, phuongThuc, tongTien, ngayLap, tenNV);
                lsHoaDon.add(hoaDon);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lsHoaDon;
    }

    public ArrayList<HoaDonViewModel> Tim(String Ngay) {
        String sql = "SELECT hdct.MaHoaDonChiTiet, kh.TenKhachHang, kh.SoDienThoai\n"
                + ", hd.PhuongThucThanhToan, hd.TongTien , hd.NgayLap, hd.TenNhanVien FROM HoaDonChiTiet hdct\n"
                + "INNER JOIN KhachHang kh ON kh.MaHoaDon = hdct.MaHoaDon\n"
                + "INNER JOIN HoaDon hd ON hd.MaHoaDon = hdct.MaHoaDon where hd.NgayLap = ?";
        ArrayList<HoaDonViewModel> lsHoaDon = new ArrayList<>();

        try (Connection conn = dbConnection.getConnection(); PreparedStatement ps = conn.prepareCall(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Integer maHD = rs.getInt("MaHoaDonChiTiet");
                String tenKH = rs.getString("TenKhachHang");
                Integer soDT = rs.getInt("SoDienThoai");
                String phuongThuc = rs.getString("PhuongThucThanhToan");
                Double tongTien = rs.getDouble("TongTien");
                String ngayLap = rs.getString("NgayLap");
                String tenNV = rs.getString("TenNhanVien");

                HoaDonViewModel hoaDon = new HoaDonViewModel(maHD, tenKH, soDT, phuongThuc, tongTien, ngayLap, tenNV);
                lsHoaDon.add(hoaDon);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lsHoaDon;
    }

    public ArrayList<HoaDonViewModel> Loc(int ngay) {
        String sql = "SELECT hdct.MaHoaDonChiTiet, kh.TenKhachHang, kh.SoDienThoai\n"
                + "                , hd.PhuongThucThanhToan, hd.TongTien , hd.NgayLap, hd.TenNhanVien FROM HoaDonChiTiet hdct\n"
                + "               INNER JOIN KhachHang kh ON kh.MaHoaDon = hdct.MaHoaDon\n"
                + "                 INNER JOIN HoaDon hd ON hd.MaHoaDon = hdct.MaHoaDon where NgayLap = " + ngay;
        ArrayList<HoaDonViewModel> lsHoaDon = new ArrayList<>();

        try (Connection conn = dbConnection.getConnection(); PreparedStatement ps = conn.prepareCall(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Integer maHD = rs.getInt("MaHoaDonChiTiet");
                String tenKH = rs.getString("TenKhachHang");
                Integer soDT = rs.getInt("SoDienThoai");
                String phuongThuc = rs.getString("PhuongThucThanhToan");
                Double tongTien = rs.getDouble("TongTien");
                String ngayLap = rs.getString("NgayLap");
                String tenNV = rs.getString("TenNhanVien");

                HoaDonViewModel hoaDon = new HoaDonViewModel(maHD, tenKH, soDT, phuongThuc, tongTien, ngayLap, tenNV);
                lsHoaDon.add(hoaDon);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lsHoaDon;
    }
}
