/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services;

import Repositories.HoaDonRepository;
import ViewModels.GioHangViewModel;
import Models.HoaDon;
import Models.HoaDonChiTiet;
import Models.HoaDonView;
import Models.NhanSu;
import Models.SanPhamChiTiet;
import Repositories.NhanSuRepo;
import Repositories.SanPhamRepository;
import ViewModels.HoaDonViewModel;
import ViewModels.HoaDon_SPViewModel;
import ViewModels.NhanSuViewModel;
import java.util.ArrayList;

/**
 *
 * @author X1
 */
public class HoaDonService {
    HoaDonRepository hdRepo = new HoaDonRepository();
    NhanSuRepo nsRepo = new NhanSuRepo();
    SanPhamRepository spRepo = new SanPhamRepository();
    
    //DEL_ALL
    public String  Xoa1SPDCT(Integer id){
        Boolean check = hdRepo.delete(id);
        if (check) {
            return "Xoá giỏ hàng thành công!";
        }else{
            return "Xóa giỏ hàng thất bại :(";
        }
    }
    //GETLIST HD VIEW
    public ArrayList<HoaDonView> getListHD(){
        return hdRepo.getListHD();
    }
    //GETLIST HD VIEW BY SEARCH
    public ArrayList<HoaDonView> getListHDBysEarch(String name){
        return hdRepo.getListHDBySearch(name);
    }
    //GET POS HD
    public HoaDonViewModel getPosHD(Integer id){
        return hdRepo.getPosHD(id);
    }
    //GET LIST
    public ArrayList<HoaDon> getList(){
        return hdRepo.getList();
    }
    //GET ID SP
    public SanPhamChiTiet getIdSP(String name){
        return spRepo.getName(name);
    }
//    public ArrayList<HoaDonViewModel> getListByID(Integer maHD){
//        return hdRepo.getListByID(maHD);
//    }
    //GETTENNV
    public ArrayList<NhanSu> getListTenNV(){
        return nsRepo.getListTenNV();
    }
    //GET TỔNG
    public HoaDon getTongTien(Integer id){
        return hdRepo.getTongTien(id);
    }
    //GETLIST VIEW MODEL
    public ArrayList<HoaDonViewModel> getListHoaDonView(){
        return hdRepo.getListHoaDonView();
    }
    public ArrayList<HoaDonViewModel> getListHoaDonView2(Integer ID){
        return hdRepo.getListHoaDonView2(ID);
    }
    //GETLIST VIEW MODEL
    public HoaDonViewModel getListHDById(Integer id){
        return hdRepo.getListHDById(id);
    }
    //GETLIST BY TRANGTHAI
    public ArrayList<HoaDon> getListByTrangThai(String trangThai){
        return hdRepo.getListByTrangThai(trangThai);
    }
    //GETLIST SANPHAMVIEW
    public ArrayList<HoaDon_SPViewModel> getListSanPham(){
        return hdRepo.getListSanPham();
    }
    //GETLIST SP_SEARCHBYNAME
    public ArrayList<HoaDon_SPViewModel> getListBySearchName(String name){
        return hdRepo.getListSearchByName(name);
    }
    
    //GETNAME
    public ArrayList<NhanSuViewModel> getId(String name){
        return nsRepo.searchByName(name);
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
    
    //UPDATE
    public String updateSP(int soL, int id){
        Boolean check = hdRepo.updateSP(soL, id);
        if (check) {
            return "Thêm sản phẩm vào giỏ hàng thành công!";
        }else{
            return "Thêm sản phẩm thất bại :(";
        }
    }
    //GETLIST GH
    public ArrayList<GioHangViewModel> getListGioHang(Integer id){
        return hdRepo.getListGioHang(id);
    }
    
    //DELETE SINGLE
    public String  deleteSingle(Integer id, Integer CT){
        Boolean check = hdRepo.deleteSingle(id, CT);
        if (check) {
            return "Xoá sản phẩm thành công!";
        }else{
            return "Xóa sản phẩm thất bại :(";
        }
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
   public String Add(HoaDon hd){
        Boolean check = hdRepo.addHoaDon(hd);
        if (check) {
            return "Thêm hóa đơn thành công!";
        }else{
            return "Thêm hóa đơn thất bại :(";
        }
    }
    public String thanhToan(HoaDon hd) {
        boolean check = hdRepo.ThanhToa(hd);
        if (check == true) {
            return "Thanh toán thành công!";
        } else {
            return "Thanh toán thất bại!";
        }
    }

    

    
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

