/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services;

import Models.DanhMuc;
import Models.KichCo;
import Models.MauSac;
import Models.SanPham;
import Models.SanPhamChiTiet;
import Repositories.DanhMucRepo;
import Repositories.KichCoRepo;
import Repositories.MauSacRepo;
import Repositories.SanPhamRepository;
import ViewModels.SanPhamViewModel;
import java.util.ArrayList;

/**
 *
 * @author X1
 */
public class SanPhamService {
    SanPhamRepository spRepo = new SanPhamRepository();
    DanhMucRepo dmRepo = new DanhMucRepo();
    MauSacRepo msRepo = new MauSacRepo();
    KichCoRepo szRepo = new KichCoRepo();
    
    //GETLIST SP
    public ArrayList<SanPham> getListSP(){
        return spRepo.getListSP();
    }
    //HIDE
    public void hidetTTMS(MauSac ms){
        msRepo.hideTTMS(ms);
    }
    public void hideTTSz(KichCo sz){
        szRepo.hideTTSz(sz);
    }
//    public Boolean unhideSP(MauSac ms){
//        return msRepo.unhideTTMS(ms);
//    }
    //GETLIST HIDE
    public ArrayList<MauSac> getListHideTTMS(){
        return msRepo.getListHide();
    }
    public ArrayList<KichCo> getListHideTTSz(){
        return szRepo.getList();
    }
    
    public void hideSP(SanPham sp){
        spRepo.hideSP(sp);
    }
    public Boolean unhideSP(SanPham sp){
        return spRepo.unhideSP(sp);
    }
    //GETLIST UNHIDE
    public ArrayList<SanPhamViewModel> getListHide(){
        return spRepo.getListHideSP();
    }
    //GETLIST
    public ArrayList<SanPhamViewModel> getListSanPham(){
        return spRepo.getListSanPham();
    }
    //GET_CBO DM
    public ArrayList<DanhMuc> getCboDM(){
        return dmRepo.getList();
    }
    //GET_CBO MS
    public ArrayList<MauSac> getCboMau(){
        return msRepo.getList();
    }
    //GET_CBO SZ
    public ArrayList<KichCo> getCboSz(){
        return szRepo.getList();
    }
    //GETLIST BY SEARCH
    public ArrayList<SanPhamViewModel> getListBySearch(String name){
        return spRepo.getListBySearch(name);
    }
    
    //GET_ID COLOR
    public MauSac getIdByName(String name){
        return msRepo.getIdByName(name);
    }
    //SZ
    public KichCo getIdByNamee(String name){
        return szRepo.getIdByName(name);
    }
    //TENDM
    public DanhMuc getIdByNameee(String name){
        return dmRepo.getIdByName(name);
    }
    
    //GETNAME
    public SanPhamChiTiet getName(String name){
        return spRepo.getName(name);
    }

    //ADD
    public String addSP(SanPham sp){
        Boolean check = spRepo.addSP(sp);
        if (check) {
            return "Thêm mới sản phẩm thành công!";
        }else{
            return "Thêm mới sản phẩm thất bại :(";
        }
    }
    //GET ID
    public SanPham getId(String tenSP){
        return spRepo.getId(tenSP);
    }
    //CHECK ID
    public Boolean checkID(String maSP){
        return spRepo.checkId(maSP);
    }
    //UPDATE
    public String updateSP(SanPham sp){
        Boolean check = spRepo.updateSP(sp);
        if (check) {
            return "Update thành công gòii~";
        }else{
            return "Update thất bại!!";
        }
    }
//    //DEL
//    public String deleteSP(SanPham sp){
//        Boolean check = spRepo.deleteSP(sp);
//        if (check) {
//            return "Xóa SP thành công!!~";
//        }else{
//            return "Xóa SP thất bại :((";
//        }
//    }

    //ADD TT
    public String addColor(MauSac ms){
        Boolean check = msRepo.addColor(ms);
        if (check) {
            return "Thêm thuộc tính màu sắc mới thành công!";
        }else{
            return "Thêm thuộc tính màu sắc mới thất bại :(";
        }
    }
    public String addSz(KichCo sz){
        Boolean check = szRepo.addSz(sz);
        if (check) {
            return "Thêm thuộc tính kích cỡ mới thành công!";
        }else{
            return "Thêm thuộc tính kích cỡ mới thất bại :(";
        }
    }
    //UPDATE
    public String updateCl(MauSac ms){
        Boolean check = msRepo.updateColor(ms);
        if (check) {
            return "Cập nhật thuộc tính màu sắc mới thành công!";
        }else{
            return "Cập nhật thuộc tính màu sắc mới thất bại :(";
        }
    }
    public String updateSz(KichCo sz){
        Boolean check = szRepo.updateSz(sz);
        if (check) {
            return "Cập nhật thuộc tính size mới thành công!";
        }else{
            return "Cập nhật thuộc tính size mới thất bại :(";
        }
    }
    //LOAD_TT
    public ArrayList<MauSac> loadDataColor(){
        return msRepo.getList();
    }
    public ArrayList<KichCo> loadDataSz(){
        return szRepo.getList();
    }
    
}
