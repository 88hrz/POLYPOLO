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
    private String tenNv, tenKH, ngayLap, pTTT;
    private Integer maHD, soDT;

    public HoaDonView(String tenNv, String tenKH, String ngayLap, String pTTT, Integer maHD, Integer soDT) {
        this.tenNv = tenNv;
        this.tenKH = tenKH;
        this.ngayLap = ngayLap;
        this.pTTT = pTTT;
        this.maHD = maHD;
        this.soDT = soDT;
    }

    public Integer getSoDT() {
        return soDT;
    }

    public void setSoDT(Integer soDT) {
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
