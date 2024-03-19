/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ViewModels;

import java.util.Date;

/**
 *
 * @author hmail
 */
public class ThongKeViewDoanhThu {
    private Integer thang;
    private Integer soLuong;
    private Integer tongTien;
    private Date ngay; 
    
    
    public ThongKeViewDoanhThu() {
    }

    public ThongKeViewDoanhThu(Integer thang, Integer soLuong, Integer tongTien) {
        this.thang = thang;
        this.soLuong = soLuong;
        this.tongTien = tongTien;
    }

    public ThongKeViewDoanhThu(Integer soLuong, Integer tongTien, Date ngay) {
        this.soLuong = soLuong;
        this.tongTien = tongTien;
        this.ngay = ngay;
    }

    
    
    public Integer getThang() {
        return thang;
    }

    public void setThang(Integer thang) {
        this.thang = thang;
    }

    public Integer getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(Integer soLuong) {
        this.soLuong = soLuong;
    }

    public Integer getTongTien() {
        return tongTien;
    }

    public void setTongTien(Integer tongTien) {
        this.tongTien = tongTien;
    }

    public Date getNgay() {
        return ngay;
    }

    public void setNgay(Date ngay) {
        this.ngay = ngay;
    }
    
    
}
