/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services;

import Repositories.HoaDonRepository;
import ViewModels.HD_GioHangViewModel;
import Models.HoaDon;
import Models.HoaDonChiTiet;
import Models.HoaDonView;
import Models.NhanSu;
import Models.SanPhamChiTiet;
import Repositories.NhanSuRepo;
import Repositories.SanPhamRepository;
import ViewModels.HD_HoaDonViewModel;
import ViewModels.HD_SanPhamViewModel;
import java.util.ArrayList;

/**
 *
 * @author X1
 */
public class HoaDonService {
    HoaDonRepository hdRepo = new HoaDonRepository();
    NhanSuRepo nsRepo = new NhanSuRepo();
    SanPhamRepository spRepo = new SanPhamRepository();
    
    //GET TOTAL
    public HoaDon getTotal(Integer id) {
        return hdRepo.getTotal(id);
    }
    //SEARCH
    public ArrayList<HD_HoaDonViewModel> searchTel(String tel){
        return hdRepo.searchCustomer(tel);
    }
    
    //DELETE
    public String deleteProduct(int mahd){
        Boolean check = hdRepo.deleteProduct(mahd);
        if (check) {
            return "Xoá sản phẩm thành công!";
        }else{
            return "Xóa sản phẩm thất bại :(";
        }
    }
    public Boolean emptyBasket(int mahd){
        return hdRepo.emptyBasket(mahd);
//        if (check) {
//            return "Xóa giỏ hàng thành công!";
//        }else{
//            return "Giỏ hàng đã trống!";
//        }
    }
    
    //GET LIST HOADON
    public ArrayList<HD_HoaDonViewModel> getListHoaDon(){
        return hdRepo.getListHoaDon();
    }
    
    
//    //GET POS HD
//    public HD_HoaDonViewModel getPosHD(Integer id){
//        return hdRepo.getPosHD(id);
//    }
    //GET LIST
    public ArrayList<HoaDon> getList(){
        return hdRepo.getList();
    }
    //SP BY ID
    public SanPhamChiTiet getIdSP(String name){
        return spRepo.getName(name);
    }
    public SanPhamChiTiet getById(Integer id){
        return spRepo.getListById(id);
    }
//    public ArrayList<HoaDonViewModel> getListByID(Integer maHD){
//        return hdRepo.getListByID(maHD);
//    }
    //GET TENNV
    public ArrayList<NhanSu> getListTenNV(){
        return nsRepo.getListTenNV();
    }
    
    //GETLIST VIEW MODEL
    public ArrayList<HD_HoaDonViewModel> getListByTrangThai(String trangThai){
        return hdRepo.getListByTrangThai(trangThai);
    }
    
    
    //GETLIST VIEW MODEL
    public HD_HoaDonViewModel getListHDById(Integer id){
        return hdRepo.getListHDById(id);
    }
    //GETLIST SANPHAMVIEW
    public ArrayList<HD_SanPhamViewModel> getListSanPham(){
        return hdRepo.getListSanPham();
    }
    //GETLIST SP_SEARCHBYNAME
    public ArrayList<HD_SanPhamViewModel> getListBySearchName(String name){
        return hdRepo.getListSearchByName(name);
    }
    public ArrayList<HD_SanPhamViewModel> getListById(Integer id){
        return hdRepo.getListById(id);
    }
    
    //GETNAME
    public NhanSu getIdByName(String name){
        return nsRepo.getIdByName(name);
    }

    
   //GETLIST BY ID
    public HoaDon getListByID(Integer id){
        return hdRepo.getListByID(id);
    }
    
    //ADD
    public String addHDCT(HoaDonChiTiet hdct){
        Boolean check = hdRepo.addHDCT(hdct);
        if (check) {
            return "Thêm sản phẩm vào giỏ hàng thành công!";
        }else{
            return "Thêm sản phẩm thất bại :(";
        }
    }
    
    //ADD SP -> UPDATE SL
    public Boolean  updateSPTon(int soL, int id){
        return hdRepo.updateSP(soL, id);
    
    }
    public String updateSP(int soL, int id){
        Boolean check = hdRepo.updateSP(soL, id);
        if (check) {
            return "Thêm sản phẩm vào giỏ hàng thành công!";
        }else{
            return "Thêm sản phẩm thất bại :(";
        }
    }
    
    //GETLIST GH
    public ArrayList<HD_GioHangViewModel> getListGioHangById(Integer id){
        return hdRepo.getListGioHangById(id);
    }

   public String add(HoaDon hd){
        Boolean check = hdRepo.addHoaDon(hd);
        if (check) {
            return "Thêm hóa đơn thành công!";
        }else{
            return "Thêm hóa đơn thất bại :(";
        }
    }
    public String thanhToan(HoaDon hd) {
        boolean check = hdRepo.thanhToan(hd);
        if (check == true) {
            return "Thanh toán thành công!";
        } else {
            return "Thanh toán thất bại!";
        }
    }
}

