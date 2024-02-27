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
    private Integer maSP;
    private String tenSP;
    private String maSize;
    private String maMau;
    private String trangThai;
    private Integer soLuong;

    public SanPhamChiTiet() {
    }

    public SanPhamChiTiet(Integer maSP, String tenSP, String maSize, String maMau, String trangThai, Integer soLuong) {
        this.maSP = maSP;
        this.tenSP = tenSP;
        this.maSize = maSize;
        this.maMau = maMau;
        this.trangThai = trangThai;
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

    public String getMaSize() {
        return maSize;
    }

    public void setMaSize(String maSize) {
        this.maSize = maSize;
    }

    public String getMaMau() {
        return maMau;
    }

    public void setMaMau(String maMau) {
        this.maMau = maMau;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public Integer getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(Integer soLuong) {
        this.soLuong = soLuong;
    }
    
    

    
    
    
}
