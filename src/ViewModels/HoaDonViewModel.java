/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ViewModels;


/**
 *
 * @author X1
 */
public class HoaDonViewModel {
    private Integer maHD;
    private String tenKH;
    private Integer soDT;
    private String phuongThuc, trangThai;
    private Double tongTien;
    private String ngayLap;
    private String tenNV;

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public HoaDonViewModel() {
    }

    public HoaDonViewModel(Double tongTien) {
        this.tongTien = tongTien;
    }

    public HoaDonViewModel(Integer maHD, String tenKH, Integer soDT, String phuongThuc, String trangThai, Double tongTien, String ngayLap, String tenNV) {
        this.maHD = maHD;
        this.tenKH = tenKH;
        this.soDT = soDT;
        this.phuongThuc = phuongThuc;
        this.trangThai = trangThai;
        this.tongTien = tongTien;
        this.ngayLap = ngayLap;
        this.tenNV = tenNV;
    }
    public HoaDonViewModel(Integer maHD, String tenKH, Integer soDT, String phuongThuc, String ngayLap, String tenNV) {
        this.maHD = maHD;
        this.tenKH = tenKH;
        this.soDT = soDT;
        this.phuongThuc = phuongThuc;
        this.ngayLap = ngayLap;
        this.tenNV = tenNV;
    }

    public HoaDonViewModel(Integer maHD, String tenKH, Integer soDT, String phuongThuc, Double tongTien, String ngayLap, String tenNV) {
        this.maHD = maHD;
        this.tenKH = tenKH;
        this.soDT = soDT;
        this.phuongThuc = phuongThuc;
        this.tongTien = tongTien;
        this.ngayLap = ngayLap;
        this.tenNV = tenNV;
    }

    public Integer getMaHD() {
        return maHD;
    }

    public void setMaHD(Integer maHD) {
        this.maHD = maHD;
    }

    public String getTenKH() {
        return tenKH;
    }

    public void setTenKH(String tenKH) {
        this.tenKH = tenKH;
    }

    public Integer getSoDT() {
        return soDT;
    }

    public void setSoDT(Integer soDT) {
        this.soDT = soDT;
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

    public String getTenNV() {
        return tenNV;
    }

    public void setTenNV(String tenNV) {
        this.tenNV = tenNV;
    }

}
