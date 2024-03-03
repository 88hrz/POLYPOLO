/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;


/**
 *
 * @author X1
 */
public class HoaDon {
    private Integer maHD, maNV;
    private String tenNV, tenKH, phuongThuc;
    private Double tongTien;
    private String ngayLap;

    public HoaDon(Double tongTien) {
        this.tongTien = tongTien;
    }
    private String trangThai;

    public HoaDon() {
    }

    public HoaDon(String trangThai) {
        this.trangThai = trangThai;
    }

    public HoaDon(Integer maHD, Integer maNV, String tenNV, String tenKH, String phuongThuc, Double tongTien, String ngayLap, String trangThai) {
        this.maHD = maHD;
        this.maNV = maNV;
        this.tenNV = tenNV;
        this.tenKH = tenKH;
        this.phuongThuc = phuongThuc;
        this.tongTien = tongTien;
        this.ngayLap = ngayLap;
        this.trangThai = trangThai;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    

    public Integer getMaHD() {
        return maHD;
    }

    public void setMaHD(Integer maHD) {
        this.maHD = maHD;
    }

    public Integer getMaNV() {
        return maNV;
    }

    public void setMaNV(Integer maNV) {
        this.maNV = maNV;
    }

    public String getTenNV() {
        return tenNV;
    }

    public void setTenNV(String tenNV) {
        this.tenNV = tenNV;
    }

    public String getTenKH() {
        return tenKH;
    }

    public void setTenKH(String tenKH) {
        this.tenKH = tenKH;
    }

    public String getPhuongThuc() {
        return phuongThuc;
    }

    public void setPhuongThuc(String phuongThuc) {
        this.phuongThuc = phuongThuc;
    }

    public Double getTongTien() {
        return tongTien;
    }

    public void setTongTien(Double tongTien) {
        this.tongTien = tongTien;
    }

    public String getNgayLap() {
        return ngayLap;
    }

    public void setNgayLap(String ngayLap) {
        this.ngayLap = ngayLap;
    }
    
    
}
