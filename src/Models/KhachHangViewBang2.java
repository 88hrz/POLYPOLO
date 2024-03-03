/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

/**
 *
 * @author hmail
 */
public class KhachHangViewBang2 {
    private Integer maHD;
    private String tenSP;
    private String tenKH;
    private Integer soLuong;
    private Double donGia;
    private Double thanhTien;

    public KhachHangViewBang2() {
    }

    public KhachHangViewBang2(Integer maHD, String tenSP, String tenKH, Integer soLuong, Double donGia) {
        this.maHD = maHD;
        this.tenSP = tenSP;
        this.tenKH = tenKH;
        this.soLuong = soLuong;
        this.donGia = donGia;
    }

    public Integer getMaHD() {
        return maHD;
    }

    public void setMaHD(Integer maHD) {
        this.maHD = maHD;
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public String getTenKH() {
        return tenKH;
    }

    public void setTenKH(String tenKH) {
        this.tenKH = tenKH;
    }

    public Integer getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(Integer soLuong) {
        this.soLuong = soLuong;
    }

    public Double getDonGia() {
        return donGia;
    }

    public void setDonGia(Double donGia) {
        this.donGia = donGia;
    }

    public Double getThanhTien() {
        return donGia*soLuong;
    }

    public void setThanhTien(Double thanhTien) {
        this.thanhTien = thanhTien;
    }
    
    
}
