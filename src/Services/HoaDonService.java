/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services;

import Repositories.HoaDonRepository;
import ViewModels.GioHangViewModel;
import ViewModels.HoaDonChiTietViewModel;
import ViewModels.HoaDonViewModel;
import ViewModels.SanPhamViewModel;
import java.util.ArrayList;

/**
 *
 * @author X1
 */
public class HoaDonService {
    HoaDonRepository hdRepo = new HoaDonRepository();
    
    //GET LIST HOADON
    public ArrayList<HoaDonChiTietViewModel> getListHoaDon(){
        return hdRepo.getListHoaDon();
    }   
    //GET LIST GIOHANG
    public ArrayList<GioHangViewModel> getListGioHang(){
        return hdRepo.getListGioHang();
    }   
    //GET LIST SANPHAM
    public ArrayList<SanPhamViewModel> getListSanPham(){
        return hdRepo.getListSanPham();
    }
    
    //GET LIST BY TRANGTHAI
    public ArrayList<HoaDonViewModel> getListByTrangThai(String trangThai){
        return hdRepo.getListByTrangThai(trangThai);
    }
    
    //DEL GIOHANG
    public String delSingleGioHang(Integer maSP){
        Boolean check = hdRepo.deleteSingleGioHang(maSP);
        if (check) {
            return "Xóa SP thành công!";
        }else{
            return "Xóa SP thất bại :(";
        }
    }
    
}
 