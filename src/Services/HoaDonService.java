/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services;

import Repositories.HoaDonRepository;
import ViewModels.GioHangViewModel;
import Models.HoaDon;
import ViewModels.HoaDonViewModel;
import ViewModels.HoaDon_SPViewModel;
import java.util.ArrayList;

/**
 *
 * @author X1
 */
public class HoaDonService {
    HoaDonRepository hdRepo = new HoaDonRepository();
    
    //GET LIST
//    public ArrayList<HoaDonViewModel> getListByID(Integer maHD){
//        return hdRepo.getListByID(maHD);
//    }
    
    //GETLIST VIEW MODEL
    public ArrayList<HoaDonViewModel> getListHoaDonView(){
        return hdRepo.getListHoaDonView();
    }
    //GETLIST BY TRANGTHAI
    public ArrayList<HoaDon> getListByTrangThai(String trangThai){
        return hdRepo.getListByTrangThai(trangThai);
    }
    //GETLIST SANPHAMVIEW
    public ArrayList<HoaDon_SPViewModel> getListSanPham(){
        return hdRepo.getListSanPham();
    }
    
    //SEARCH
    public ArrayList<HoaDon_SPViewModel> getListBySearch(Integer id){
        return hdRepo.getListBySearch(id);
    }
    
   //GETLIST BY ID
    public HoaDon getListByID(Integer id){
        return hdRepo.getListByID(id);
    }
    
    //GETLISTBYID HOADON
//    public String addHoaDon(HoaDon hoaDon){
//        if (!hdRepo.getListByID(hoaDon.getMaHD()).isEmpty()) {
//            return "Đã tồn tại HĐ với mã này!";
//        }else{
//            Boolean check = hdRepo.addHoaDon(hoaDon);
//            if (check) {
//                return "Thêm hóa đơn thành công :)";
//            }else{
//                return "Thêm hóa đơn thất bại :(";
//            }
//        }
//    }
   
    

    

    
    //<editor-fold defaultstate="collapsed" desc="DELETE GIOHANG">
    //DEL SINGLE
//    public String delSingleGioHang(Integer maSP){
//        Boolean check = hdRepo.deleteSingleGioHang(maSP);
//        if (check) {
//            return "Xóa SP thành công!";
//        }else{
//            return "Xóa SP thất bại :(";
//        }
//    }
//    //DEL ALL
//    public String delAll(){
//        Boolean check = hdRepo.deleteAll();
//        if (check) {
//            return "Xóa giỏ hàng thành công!";
//        }else{
//            return "Xóa giỏ hàng thất bại!";
//        }
//    }
    //</editor-fold>
}

