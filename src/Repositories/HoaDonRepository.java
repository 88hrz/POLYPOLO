/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repositories;

import ViewModels.GioHangViewModel;
import Models.HoaDon;
import Models.HoaDonChiTiet;
import Models.HoaDonView;
import ViewModels.HoaDonViewModel;
import ViewModels.HoaDon_SPViewModel;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author X1
 */
public class HoaDonRepository {
    DbConnection dbConnection;
    
    //DEL
    public Boolean delete(int maHD) {
        String sql = " Delete HoaDonChiTiet where MaHoaDon = ?";

        try (Connection conn = dbConnection.getConnection(); PreparedStatement ps = conn.prepareCall(sql)) {
            ps.setObject(1, maHD);

            int check = ps.executeUpdate();
            if (check > 0) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    //GET POS DSHD
    public HoaDonViewModel getPosHD(Integer maHD) {
        for (HoaDonViewModel hdvm : getListHoaDonView()) {
            if (hdvm.getMaHD().equals(maHD)) { 
                return hdvm;
            }
        }
        return null;
    }
    //GETLIST
    public ArrayList<HoaDon> getList() {
        String sql = "SELECT * FROM HoaDon WHERE Deleted !=1";
        ArrayList<HoaDon> lsTrangThai = new ArrayList<>();

        try (Connection conn = dbConnection.getConnection(); PreparedStatement ps = conn.prepareCall(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Integer maHD = rs.getInt("MaHoaDon");
                String tenNV = rs.getString("TenNhanVien");
                String tenKH = rs.getString("TenKhachHang");
                String phuongThuc = rs.getString("PhuongThucThanhToan");
                String ngayLap = rs.getString("NgayLap");
                Double tongTien = rs.getDouble("TongTien");
                String trangThai = rs.getString("TrangThai");
                HoaDon hoaDon = new HoaDon(maHD, null, tenNV, tenKH, phuongThuc, tongTien, ngayLap, trangThai);
                lsTrangThai.add(hoaDon);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lsTrangThai;
    }
    public ArrayList<HoaDonView> getListHD(){
        String sql = "SELECT hd.MaHoaDon, hd.TenNhanVien, hd.TenKhachHang, kh.SoDienThoai, hd.PhuongThucThanhToan, hd.NgayLap FROM HoaDon hd\n" +
                    "INNER JOIN KhachHang kh ON kh.MaHoaDon = hd.MaHoaDon\n" +
                    "WHERE hd.Deleted !=1";
        ArrayList<HoaDonView> ls = new ArrayList<>();
        
        try (Connection conn = dbConnection.getConnection();
                PreparedStatement ps = conn.prepareCall(sql)){
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {                
                Integer maHD = rs.getInt("MaHoaDon");
                String tenNV = rs.getString("TenNhanVien");
                String tenKH = rs.getString("TenKhachHang");
                Integer soDT = rs.getInt("SoDienThoai");
                String pTTT = rs.getString("PhuongThucThanhToan");
                String ngayLap = rs.getString("NgayLap");
                
                HoaDonView hd = new HoaDonView(tenNV, tenKH, ngayLap, pTTT, maHD, soDT);
                ls.add(hd); 
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ls;
    }
    public ArrayList<HoaDonView> getListHDBySearch(String name){
        String sql = "SELECT hd.MaHoaDon, hd.TenNhanVien, hd.TenKhachHang, kh.SoDienThoai, hd.PhuongThucThanhToan, hd.NgayLap FROM HoaDon hd\n" +
                    "INNER JOIN HoaDonChiTiet hdct ON hdct.MaHoaDon = hd.MaHoaDon\n" +
                    "INNER JOIN KhachHang kh ON kh.MaHoaDon = hd.MaHoaDon\n" +
                    "WHERE kh.Deleted !=1 AND  kh.TenKhachHang LIKE '%"+name+"%'";
        ArrayList<HoaDonView> ls = new ArrayList<>();
        
        try (Connection conn = dbConnection.getConnection();
                PreparedStatement ps = conn.prepareCall(sql)){
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {                
                Integer maHD = rs.getInt("MaHoaDon");
                String tenNV = rs.getString("TenNhanVien");
                String tenKH = rs.getString("TenKhachHang");
                Integer soDT = rs.getInt("SoDienThoai");
                String pTTT = rs.getString("PhuongThucThanhToan");
                String ngayLap = rs.getString("NgayLap");
                
                HoaDonView hd = new HoaDonView(tenNV, tenKH, ngayLap, pTTT, maHD, soDT);
                ls.add(hd); 
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ls;
    }
    //GET LIST HOADONVIEW
    public ArrayList<HoaDonViewModel> getListHoaDonView() {
        String sql = "SELECT hd.MaHoaDon, hd.TenNhanVien, hd.TenKhachHang, kh.SoDienThoai, hd.PhuongThucThanhToan, hd.TongTien, hd.NgayLap FROM HoaDon hd\n" +
                    "INNER JOIN KhachHang kh ON kh.MaHoaDon = hd.MaHoaDon\n" +
                    "INNER JOIN NhanVien nv ON nv.MaNhanVien = hd.MaNhanVien\n" +
                    "WHERE kh.Deleted !=1";
        ArrayList<HoaDonViewModel> lsHoaDon = new ArrayList<>();

        try (Connection conn = dbConnection.getConnection(); PreparedStatement ps = conn.prepareCall(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Integer maHD = rs.getInt("MaHoaDon");
                String tenNV = rs.getString("TenNhanVien");
                String tenKH = rs.getString("TenKhachHang");
                Integer soDT = rs.getInt("SoDienThoai");
                String phuongThuc = rs.getString("PhuongThucThanhToan");
                Double tongTien = rs.getDouble("TongTien");
                String ngayLap = rs.getString("NgayLap");
                
                
                HoaDonViewModel hd = new HoaDonViewModel(maHD, tenKH, soDT, phuongThuc, tongTien, ngayLap, tenNV);
                lsHoaDon.add(hd);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lsHoaDon;
    }
    //FILTER
    public HoaDonViewModel getListHDById(Integer id) {
        String sql = "SELECT hd.MaHoaDon, hd.TenNhanVien, hd.TenKhachHang, kh.SoDienThoai, hd.PhuongThucThanhToan, hd.NgayLap FROM HoaDon hd\n"
                + "INNER JOIN KhachHang kh ON kh.MaHoaDon = hd.MaHoaDon\n"
                + "INNER JOIN NhanVien nv ON nv.MaNhanVien = hd.MaNhanVien\n"
                + "WHERE kh.Deleted !=1 AND hd.MaHoaDon = ?";
        //    ArrayList<HoaDonViewModel> lsHoaDon = new ArrayList<>();
        HoaDonViewModel hd = new HoaDonViewModel();

        try (Connection conn = dbConnection.getConnection(); PreparedStatement ps = conn.prepareCall(sql)) {
            ps.setObject(1, id);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Integer maHD = rs.getInt("MaHoaDon");
                String tenNV = rs.getString("TenNhanVien");
                String tenKH = rs.getString("TenKhachHang");
                Integer soDT = rs.getInt("SoDienThoai");
                String phuongThuc = rs.getString("PhuongThucThanhToan");
                String ngayLap = rs.getString("NgayLap");

                hd = new HoaDonViewModel(maHD, tenKH, soDT, phuongThuc, ngayLap, tenNV);
                //            lsHoaDon.add(hd);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //    return lsHoaDon;
        return hd;
    }
    //GETLIST BY TRANGTHAI
    public ArrayList<HoaDon> getListByTrangThai(String trangThai) {
        String sql = "SELECT hd.MaHoaDon, hd.TenNhanVien, hd.TenKhachHang, kh.SoDienThoai, hd.PhuongThucThanhToan, hd.TongTien, hd.NgayLap FROM HoaDon hd\n" +
                    "INNER JOIN KhachHang kh ON kh.MaHoaDon = hd.MaHoaDon\n" +
                    "INNER JOIN NhanVien nv ON nv.MaNhanVien = hd.MaNhanVien\n" +
                    "WHERE kh.Deleted !=1  AND hd.TrangThai LIKE N'"+trangThai+"%'";
        ArrayList<HoaDon> lsTrangThai = new ArrayList<>();

        try (Connection conn = dbConnection.getConnection(); PreparedStatement ps = conn.prepareCall(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Integer maHD = rs.getInt("MaHoaDon");
                String tenNV = rs.getString("TenNhanVien");
                String tenKH = rs.getString("TenKhachHang");
                String phuongThuc = rs.getString("PhuongThucThanhToan");
                String ngayLap = rs.getString("NgayLap");
                Double tongTien = rs.getDouble("TongTien");

                HoaDon hd = new HoaDon(maHD, null, tenNV, tenKH, phuongThuc, tongTien, ngayLap, trangThai);
                lsTrangThai.add(hd);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lsTrangThai;
    }
    //GET TỔNG
    public HoaDon getTongTien(Integer id){
        String sql = "SELECT SUM(hdct.DonGia * hdct.SoLuong) as 'Tổng' FROM HoaDonChiTiet hdct \n" +
                            "INNER JOIN HoaDon hd ON hd.MaHoaDon = hdct.MaHoaDon\n" +
                            "WHERE hd.Deleted !=1 AND hd.MaHoaDon = ?;";
        HoaDon hd = null;
        
        try (Connection conn = dbConnection.getConnection();
                PreparedStatement ps = conn.prepareCall(sql)){
            ps.setObject(1, id);
            
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {                
                hd = new HoaDon(rs.getDouble(1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return hd;
    }
    //GETLIST SANPHAM
    public ArrayList<HoaDon_SPViewModel> getListSanPham() {
        String sql = "SELECT spct.MaSanPham, spct.TenSanPhamChiTiet, dm.TenDanhMuc\n"
                + ", ms.TenMau, s.TenSize\n"
                + ", sp.GiaBan, spct.SoLuongTon, spct.TrangThai FROM SanPham sp\n"
                + "INNER JOIN DanhMuc dm ON dm.MaDanhMuc = sp.MaDanhMuc\n"
                + "INNER JOIN SanPhamChiTiet spct ON spct.MaSanPham = sp.MaSanPham\n"
                + "INNER JOIN MauSac ms ON ms.MaMau = spct.MaMau\n"
                + "INNER JOIN Size s ON s.MaSize = spct.MaSize";
        ArrayList<HoaDon_SPViewModel> lsSanPham = new ArrayList<>();

        try (Connection conn = dbConnection.getConnection(); PreparedStatement ps = conn.prepareCall(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Integer maSPCT = rs.getInt("MaSanPham");
                String tenSP = rs.getString("TenSanPhamChiTiet");
                String tenDM = rs.getString("TenDanhMuc");
                String tenMau = rs.getString("TenMau");
                String kichCo = rs.getString("TenSize");
                Double donGia = rs.getDouble("GiaBan");
                Integer soLuong = rs.getInt("SoLuongTon");
                String trangThai = rs.getString("TrangThai");

                HoaDon_SPViewModel sanPhamView = new HoaDon_SPViewModel(maSPCT, tenSP, tenDM, tenMau, kichCo, donGia, soLuong, trangThai);
                lsSanPham.add(sanPhamView);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lsSanPham;
    }
    //GETLIST SP BY SEARCH
    public ArrayList<HoaDon_SPViewModel> getListSearchByName(String name) {
        String sql = "SELECT spct.MaSanPham, spct.TenSanPhamChiTiet, dm.TenDanhMuc\n"
                + ", ms.TenMau, s.TenSize\n"
                + ", sp.GiaBan, spct.SoLuongTon, spct.TrangThai FROM SanPham sp\n"
                + "INNER JOIN DanhMuc dm ON dm.MaDanhMuc = sp.MaDanhMuc\n"
                + "INNER JOIN SanPhamChiTiet spct ON spct.MaSanPham = sp.MaSanPham\n"
                + "INNER JOIN MauSac ms ON ms.MaMau = spct.MaMau\n"
                + "INNER JOIN Size s ON s.MaSize = spct.MaSize "
                + "WHERE spct.TenSanPhamChiTiet LIKE N'%"+name+"%'";
        ArrayList<HoaDon_SPViewModel> lsSanPham = new ArrayList<>();

        try (Connection conn = dbConnection.getConnection(); PreparedStatement ps = conn.prepareCall(sql)) {
            
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Integer maSPCT = rs.getInt("MaSanPham");
                String tenSP = rs.getString("TenSanPhamChiTiet");
                String tenDM = rs.getString("TenDanhMuc");
                String tenMau = rs.getString("TenMau");
                String kichCo = rs.getString("TenSize");
                Double donGia = rs.getDouble("GiaBan");
                Integer soLuong = rs.getInt("SoLuongTon");
                String trangThai = rs.getString("TrangThai");

                HoaDon_SPViewModel sanPhamView = new HoaDon_SPViewModel(maSPCT, tenSP, tenDM, tenMau, kichCo, donGia, soLuong, trangThai);
                lsSanPham.add(sanPhamView);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lsSanPham;
    }

    //ADD HDCT
    public Boolean addHDCT(HoaDonChiTiet hdct) {
        String sql = "INSERT INTO HoaDonChiTiet (MaHoaDon, MaSanPhamChiTiet, SoLuong, DonGia)\n"
                + "					VALUES (?, ?, ?, ?)";

        try (Connection conn = dbConnection.getConnection(); PreparedStatement ps = conn.prepareCall(sql)) {
            ps.setObject(1, hdct.getMaHD());
            ps.setObject(2, hdct.getMaSPCT());
            ps.setObject(3, hdct.getSoLuong());
            ps.setObject(4, hdct.getDonGia());

            int check = ps.executeUpdate();
            if (check > 0) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public Boolean ThanhToa(HoaDon hd) {
        String sql = "Update HoaDon set TrangThai = N'Đã thanh toán' where MaHoaDon = ?";

        try (Connection conn = dbConnection.getConnection(); PreparedStatement ps = conn.prepareCall(sql)) {
            ps.setObject(1, hd.getMaHD());
            int check = ps.executeUpdate();
            if (check > 0) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    //UPDATE
    public Boolean updateHDCT(int soL, double gia, int id) {
        String sql = "UPDATE HoaDonChiTiet\n"
                + "SET SoLuong = ?, DonGia = ? WHERE MaHoaDon = ?";

        try (Connection conn = dbConnection.getConnection(); PreparedStatement ps = conn.prepareCall(sql)) {
            ps.setObject(1, soL);
            ps.setObject(2, gia);
            ps.setObject(3, id);

            int check = ps.executeUpdate();
            if (check > 0) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    //UPDATE SP
    public Boolean updateSP(int soL, int id) {
        String sql = " UPDATE SanPhamChiTiet SET SoLuongTon = ?"
                + "WHERE MaSanPham =?";

        try (Connection conn = dbConnection.getConnection(); PreparedStatement ps = conn.prepareCall(sql)) {
            ps.setObject(1, soL);
            ps.setObject(2, id);

            int check = ps.executeUpdate();
            if (check > 0) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    //GETLIST HDCT
    public ArrayList<GioHangViewModel> getListGioHang(Integer id) {
        String sql = "SELECT spct.MaSanPham, spct.TenSanPhamChiTiet\n"
                + ", ms.TenMau, s.TenSize, hdct.SoLuong, hdct.DonGia, hd.TongTien FROM DanhMuc dm\n"
                + "INNER JOIN SanPham sp ON dm.MaDanhMuc = sp.MaDanhMuc\n"
                + "INNER JOIN SanPhamChiTiet spct ON spct.MaSanPham = sp.MaSanPham\n"
                + "INNER JOIN MauSac ms ON ms.MaMau = spct.MaMau\n"
                + "INNER JOIN Size s ON s.MaSize = spct.MaSize\n"
                + "INNER JOIN HoaDonChiTiet hdct ON hdct.MaSanPhamChiTiet = spct.MaSanPhamChiTiet\n"
                + "INNER JOIN HoaDon hd ON hd.MaHoaDon = hdct.MaHoaDon\n"
                + "WHERE hd.MaHoaDon = ?";
        ArrayList<GioHangViewModel> ls = new ArrayList<>();

        try (Connection conn = dbConnection.getConnection(); PreparedStatement ps = conn.prepareCall(sql)) {
            ps.setObject(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Integer maSP = rs.getInt("MaSanPham");
                String tenSP = rs.getString("TenSanPhamChiTiet");
                String tenMau = rs.getString("TenMau");
                String tenSz = rs.getString("TenSize");
                Integer soL = rs.getInt("SoLuong");
                Double donG = rs.getDouble("DonGia");
                Double tengT = rs.getDouble("TongTien");

                GioHangViewModel gH = new GioHangViewModel(maSP, tenSP, tenMau, tenSz, soL, donG, tengT);
                ls.add(gH);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ls;
    }
    //LINH
    public ArrayList<HoaDonViewModel> getListHoaDonView2(Integer ID) {
        String sql = "SELECT hdct.MaHoaDonChiTiet, kh.TenKhachHang, kh.SoDienThoai\n"
                + ", hd.PhuongThucThanhToan, hd.TongTien , hd.NgayLap, hd.TenNhanVien FROM HoaDonChiTiet hdct\n"
                + "INNER JOIN KhachHang kh ON kh.MaHoaDon = hdct.MaHoaDon\n"
                + "INNER JOIN HoaDon hd ON hd.MaHoaDon = hdct.MaHoaDon where  hdct.MaHoaDonChiTiet =" + ID;
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

    //DELETE
    public Boolean deleteSingle(Integer id, Integer CT) {
        String sql = "DELETE FROM HoaDonChiTiet WHERE MaHoaDonChiTiet = ? and MaHoaDon = ?";

        try (Connection conn = dbConnection.getConnection(); PreparedStatement ps = conn.prepareCall(sql)) {
            ps.setObject(1, id);
            ps.setObject(2, CT);
            int check = ps.executeUpdate();
            if (check > 0) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    //GETLIST BY ID HOADON_ent
    public HoaDon getListByID(Integer id) {
        String sql = "SELECT * FROM HoaDon WHERE MaHoaDon = ?";
        HoaDon hoaDon = new HoaDon();

        try (Connection conn = dbConnection.getConnection(); PreparedStatement ps = conn.prepareCall(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Integer maHD = rs.getInt("MaHoaDon");
                Integer maNV = rs.getInt("MaNhanVien");
                String tenKH = rs.getString("TenKhachHang");
                String tenNV = rs.getString("TenNhanVien");
                Double tongTien = rs.getDouble("TongTien");
                String ngayLap = rs.getString("NgayLap");
                String trangThai = rs.getString("TrangThai");
                String phuongThuc = rs.getString("PhuongThucThanhToan");

                hoaDon = new HoaDon(maHD, maNV, tenNV, tenKH, phuongThuc, tongTien, ngayLap, trangThai);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return hoaDon;
    }
    //ADD HOADON
    public Boolean addHoaDon(HoaDon hoaDon) {
        String sql = "INSERT INTO HoaDon (MaNhanVien, TenKhachHang, TenNhanVien, TongTien, NgayLap, PhuongThucThanhToan, TrangThai,Deleted)\n"
                + "VALUES (?, ?, ?, ?, ?, ?, N'Chưa thanh toán',0);";

        try (Connection conn = dbConnection.getConnection(); 
                PreparedStatement ps = conn.prepareCall(sql)) {
            ps.setObject(1, hoaDon.getMaNV());
            ps.setObject(2, hoaDon.getTenKH());
            ps.setObject(3, hoaDon.getTenNV());
            ps.setObject(4, hoaDon.getTongTien());
            ps.setObject(5, hoaDon.getNgayLap().toString());
            ps.setObject(6, hoaDon.getPhuongThuc());

            int check = ps.executeUpdate();
            if (check > 0) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}
