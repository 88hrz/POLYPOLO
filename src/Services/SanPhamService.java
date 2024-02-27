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
import Repositories.DanhMucRepository;
import Repositories.KichCoRepo;
import Repositories.MauSacRepo;
import Repositories.SanPhamRepository;
import ViewModels.SPCTViewModel;
import java.util.ArrayList;

/**
 *
 * @author X1
 */
public class SanPhamService {
    SanPhamRepository spRepo = new SanPhamRepository();
    DanhMucRepository dmRepo = new DanhMucRepository();
    MauSacRepo msRepo = new MauSacRepo();
    KichCoRepo szRepo = new KichCoRepo();
    
    //GETLIST
    public ArrayList<SPCTViewModel> getListSanPham(){
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
    
    //GET_NAME BY ID COLOR
    public MauSac getNameByID(String id){
        return msRepo.getNameByID(id);
    }
    //GET_NAME BY ID SZ
    public KichCo getNameByIDSz(String id){
        return szRepo.getNameByID(id);
    }
    
    //GETLIST BY SEARCH
    public ArrayList<SPCTViewModel> getListBySearch(Integer id){
        return spRepo.getListBySearch(id);
    }
    
    //GET_PRICE BY ID
    public SanPham getPriceById(String id){
        return spRepo.getPriceByID(id);
    }
    
    //ADD SP
    public String addSP(SanPham sp){
        Boolean check = spRepo.addSP(sp);
        if (check) {
            return "Thêm SP mới thành công!";
        }else{
            return "Thêm SP thất bại :(";
        }
    }
    //ADD SPCT
    public String addSPCT(SanPhamChiTiet spct){
        Boolean check = spRepo.addSPCT(spct);
        if (check) {
            return "Thêm SP mới thành công!";
        }else{
            return "Thêm SP thất bại :(";
        }
    }
}
