/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ViewModels;

/**
 *
 * @author X1
 */
public class UserViewModel {
    private Integer id, soDT;
    private String tenDN, tenNV, gioiTinh, vaiTro, diaChi, matKhau;

    public UserViewModel() {
    }

    public UserViewModel(Integer id, Integer soDT, String tenDN, String tenNV, String gioiTinh, String vaiTro, String diaChi, String matKhau) {
        this.id = id;
        this.soDT = soDT;
        this.tenDN = tenDN;
        this.tenNV = tenNV;
        this.gioiTinh = gioiTinh;
        this.vaiTro = vaiTro;
        this.diaChi = diaChi;
        this.matKhau = matKhau;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSoDT() {
        return soDT;
    }

    public void setSoDT(Integer soDT) {
        this.soDT = soDT;
    }

    public String getTenDN() {
        return tenDN;
    }

    public void setTenDN(String tenDN) {
        this.tenDN = tenDN;
    }

    public String getTenNV() {
        return tenNV;
    }

    public void setTenNV(String tenNV) {
        this.tenNV = tenNV;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getVaiTro() {
        return vaiTro;
    }

    public void setVaiTro(String vaiTro) {
        this.vaiTro = vaiTro;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }
    
    
}
