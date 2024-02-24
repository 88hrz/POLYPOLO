/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

/**
 *
 * @author X1
 */
public class HoaDonChiTiet {
    private Integer  maHDCT, maSPCT;
    private Integer maHD, soLuong;
    private Double donGia;

    public HoaDonChiTiet() {
    }

    public HoaDonChiTiet(Integer maHDCT, Integer maSPCT, Integer maHD, Integer soLuong, Double donGia) {
        this.maHDCT = maHDCT;
        this.maSPCT = maSPCT;
        this.maHD = maHD;
        this.soLuong = soLuong;
        this.donGia = donGia;
    }

    public Integer getMaHDCT() {
        return maHDCT;
    }

    public void setMaHDCT(Integer maHDCT) {
        this.maHDCT = maHDCT;
    }

    public Integer getMaSPCT() {
        return maSPCT;
    }

    public void setMaSPCT(Integer maSPCT) {
        this.maSPCT = maSPCT;
    }

    public Integer getMaHD() {
        return maHD;
    }

    public void setMaHD(Integer maHD) {
        this.maHD = maHD;
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

    
    
    
    
}
