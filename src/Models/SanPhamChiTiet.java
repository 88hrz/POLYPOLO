/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

/**
 *
 * @author X1
 */
public class SanPhamChiTiet {
    private Integer id_SPCT, maSP, maMau, maSize;
    private Integer soLuongTon;
    private String ten_SPCT, mauSac, size, trangThai;

    public SanPhamChiTiet() {
    }

    public SanPhamChiTiet(Integer id_SPCT, Integer maSP, Integer maMau, Integer maSize, Integer soLuongTon, String ten_SPCT, String mauSac, String size, String trangThai) {
        this.id_SPCT = id_SPCT;
        this.maSP = maSP;
        this.maMau = maMau;
        this.maSize = maSize;
        this.soLuongTon = soLuongTon;
        this.ten_SPCT = ten_SPCT;
        this.mauSac = mauSac;
        this.size = size;
        this.trangThai = trangThai;
    }

    public Integer getId_SPCT() {
        return id_SPCT;
    }

    public void setId_SPCT(Integer id_SPCT) {
        this.id_SPCT = id_SPCT;
    }

    public Integer getMaSP() {
        return maSP;
    }

    public void setMaSP(Integer maSP) {
        this.maSP = maSP;
    }

    public Integer getMaMau() {
        return maMau;
    }

    public void setMaMau(Integer maMau) {
        this.maMau = maMau;
    }

    public Integer getMaSize() {
        return maSize;
    }

    public void setMaSize(Integer maSize) {
        this.maSize = maSize;
    }

    public Integer getSoLuongTon() {
        return soLuongTon;
    }

    public void setSoLuongTon(Integer soLuongTon) {
        this.soLuongTon = soLuongTon;
    }

    public String getTen_SPCT() {
        return ten_SPCT;
    }

    public void setTen_SPCT(String ten_SPCT) {
        this.ten_SPCT = ten_SPCT;
    }

    public String getMauSac() {
        return mauSac;
    }

    public void setMauSac(String mauSac) {
        this.mauSac = mauSac;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    
    
    
}
