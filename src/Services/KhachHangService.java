/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services;

import Models.KhachHang;
import Models.KhachHangViewBang2;
import Repositories.KhachHangRepository;
import ViewModels.KhachHangViewModel;
import java.util.ArrayList;

/**
 *
 * @author X1
 */
public class KhachHangService {
    KhachHangRepository kHRepo = new KhachHangRepository();
    
    public ArrayList<KhachHangViewModel> getList(){
        return kHRepo.getList();
    }
    public ArrayList<KhachHangViewBang2> getList_Bang2(Integer MaHD){
        return kHRepo.getList_Bang2(MaHD);
    }
    public ArrayList<KhachHangViewModel> getListSearch(String id){
        return kHRepo.getListSearch(id);
    }
    public ArrayList<KhachHangViewModel> danhSchAn(){
        return kHRepo.DanhSachAn();
    }
    public boolean boAN(KhachHang kh){
        return kHRepo.boAn(kh);
    }
    public String addNew(KhachHang kh){
        boolean check = kHRepo.addNew(kh);
        if (check) {
            return "Thêm thành công!";
        }else{
            return "Thêm thất bại :(";
        }
    }
    public String update(KhachHang kh){
        boolean check = kHRepo.update(kh);
        if (check) {
            return "Cập nhật thành công!";
        }else{
            return "Cập nhật thất bại :(";
        }
    }
    public String XoaKH(KhachHang kh){
        boolean check = kHRepo.XoaKH(kh);
        if (check) {
            return "Xóa thành công!";
        }else{
            return "Xóa thất bại :(";
        }
    }
    
}
