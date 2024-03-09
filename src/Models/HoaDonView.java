/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;


/**
 *
 * @author X1
 */
public class HoaDonView {
    private String tenNv, tenKH, ngayLap, pTTT, soDT,trangThai;
    private Integer maHD ;

    public HoaDonView(String tenNv, String tenKH, String ngayLap, String pTTT, String soDT, String trangThai, Integer maHD) {
        this.tenNv = tenNv;
        this.tenKH = tenKH;
        this.ngayLap = ngayLap;
        this.pTTT = pTTT;
        this.soDT = soDT;
        this.trangThai = trangThai;
        this.maHD = maHD;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    

    public HoaDonView() {
    }

    public String getSoDT() {
        return soDT;
    }

    public void setSoDT(String soDT) {
        this.soDT = soDT;
    }

    public String getTenNv() {
        return tenNv;
    }

    public void setTenNv(String tenNv) {
        this.tenNv = tenNv;
    }

    public String getTenKH() {
        return tenKH;
    }

    public void setTenKH(String tenKH) {
        this.tenKH = tenKH;
    }

    public String getNgayLap() {
        return ngayLap;
    }

    public void setNgayLap(String ngayLap) {
        this.ngayLap = ngayLap;
    }

    public String getpTTT() {
        return pTTT;
    }

    public void setpTTT(String pTTT) {
        this.pTTT = pTTT;
    }

    public Integer getMaHD() {
        return maHD;
    }

    public void setMaHD(Integer maHD) {
        this.maHD = maHD;
    }
    
}
