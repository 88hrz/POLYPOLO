/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

/**
 *
 * @author X1
 */
public class SanPham {
    private Integer del;

    public Integer getDel() {
        return del;
    }

    public void setDel(Integer del) {
        this.del = del;
    }

    public SanPham(Integer del) {
        this.del = del;
    }
    private Integer maSP;
    private Integer maSPCT;
    private String tenSP;
    private Integer maDM;
    private String trangThai;
    private Double giaNhap, giaBan;
    private Integer maSz;
    private Integer maMau;
    private Integer soLuong;

    public SanPham() {
    }

    public Integer getMaSPCT() {
        return maSPCT;
    }

    public void setMaSPCT(Integer maSPCT) {
        this.maSPCT = maSPCT;
    }

    public SanPham(Integer maSP, Integer maSPCT, String tenSP, Integer maDM, String trangThai, Double giaNhap, Double giaBan, Integer maSz, Integer maMau, Integer soLuong) {
        this.maSP = maSP;
        this.maSPCT = maSPCT;
        this.tenSP = tenSP;
        this.maDM = maDM;
        this.trangThai = trangThai;
        this.giaNhap = giaNhap;
        this.giaBan = giaBan;
        this.maSz = maSz;
        this.maMau = maMau;
        this.soLuong = soLuong;
    }


    

    
    public Integer getMaSP() {
        return maSP;
    }

    public void setMaSP(Integer maSP) {
        this.maSP = maSP;
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public Integer getMaDM() {
        return maDM;
    }

    public void setMaDM(Integer maDM) {
        this.maDM = maDM;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public Double getGiaNhap() {
        return giaNhap;
    }

    public void setGiaNhap(Double giaNhap) {
        this.giaNhap = giaNhap;
    }

    public Double getGiaBan() {
        return giaBan;
    }

    public void setGiaBan(Double giaBan) {
        this.giaBan = giaBan;
    }

    public Integer getMaSz() {
        return maSz;
    }

    public void setMaSz(Integer maSz) {
        this.maSz = maSz;
    }

    public Integer getMaMau() {
        return maMau;
    }

    public void setMaMau(Integer maMau) {
        this.maMau = maMau;
    }

    public Integer getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(Integer soLuong) {
        this.soLuong = soLuong;
    }
    
    
}
