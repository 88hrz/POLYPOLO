/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ViewModels;

import java.util.Date;

/**
 *
 * @author X1
 */
public class HoaDonViewModel {
    private Integer maHD, maNV;
    private String tenNV, tenKH, phuongThuc;
    private Double tongTien;
    private Date ngayLap;

    public HoaDonViewModel() {
    }

    public HoaDonViewModel(Integer maHD, Integer maNV, String tenNV, String tenKH, String phuongThuc, Double tongTien, Date ngayLap) {
        this.maHD = maHD;
        this.maNV = maNV;
        this.tenNV = tenNV;
        this.tenKH = tenKH;
        this.phuongThuc = phuongThuc;
        this.tongTien = tongTien;
        this.ngayLap = ngayLap;
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

    public Date getNgayLap() {
        return ngayLap;
    }

    public void setNgayLap(Date ngayLap) {
        this.ngayLap = ngayLap;
    }
    
    
}
