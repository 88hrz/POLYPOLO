/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ViewModels;

/**
 *
 * @author X1
 */
public class GioHangViewModel {
    private Integer maSP, soLuong;
    private String tenSP, mauSac, kichCo;
    private Double donGia, thanhTien;

    public GioHangViewModel() {
    }

    public GioHangViewModel(Integer maSP, Integer soLuong, String tenSP, String mauSac, String kichCo, Double donGia, Double thanhTien) {
        this.maSP = maSP;
        this.soLuong = soLuong;
        this.tenSP = tenSP;
        this.mauSac = mauSac;
        this.kichCo = kichCo;
        this.donGia = donGia;
        this.thanhTien = thanhTien;
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

    public Double getDonGia() {
        return donGia;
    }

    public void setDonGia(Double donGia) {
        this.donGia = donGia;
    }

    public Double getThanhTien() {
        return thanhTien;
    }

    public void setThanhTien(Double thanhTien) {
        this.thanhTien = thanhTien;
    }
    
    
}
