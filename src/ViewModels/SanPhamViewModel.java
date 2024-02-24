/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ViewModels;

/**
 *
 * @author X1
 */
public class SanPhamViewModel {
    private Integer maSP, soLuong;
    private String tenSP, tenDM, mauSac, kichCo, trangThai;
    private Double donGia;

    public SanPhamViewModel() {
    }

    public SanPhamViewModel(Integer maSP, Integer soLuong, String tenSP, String tenDM, String mauSac, String kichCo, String trangThai, Double donGia) {
        this.maSP = maSP;
        this.soLuong = soLuong;
        this.tenSP = tenSP;
        this.tenDM = tenDM;
        this.mauSac = mauSac;
        this.kichCo = kichCo;
        this.trangThai = trangThai;
        this.donGia = donGia;
    }

    public Integer getMaSP() {
        return maSP;
    }

    public void setMaSP(Integer maSP) {
        this.maSP = maSP;
    }

    public Integer getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(Integer soLuong) {
        this.soLuong = soLuong;
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public String getTenDM() {
        return tenDM;
    }

    public void setTenDM(String tenDM) {
        this.tenDM = tenDM;
    }

    public String getMauSac() {
        return mauSac;
    }

    public void setMauSac(String mauSac) {
        this.mauSac = mauSac;
    }

    public String getKichCo() {
        return kichCo;
    }

    public void setKichCo(String kichCo) {
        this.kichCo = kichCo;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public Double getDonGia() {
        return donGia;
    }

    public void setDonGia(Double donGia) {
        this.donGia = donGia;
    }
    
    
    
    
}
