/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services;

import Models.HoaDon;
import Repositories.HoaDonRepository;
import Repositories.ThongKeRespository;
import ViewModels.HoaDonViewModel;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author hmail
 */
public class ThongKeService {
    
    ThongKeRespository tkrp = new ThongKeRespository();
    HoaDonRepository hdrp = new HoaDonRepository();
    
    public ArrayList<HoaDonViewModel> getListHoaDonView(Date ngayLap){
        return tkrp.getListHoaDonView((java.sql.Date) ngayLap);
    }
    public HoaDonViewModel tongNgay(){
        return tkrp.tongDoanhThuNgay();
    }
    public HoaDonViewModel tongThang(){
        return tkrp.tongDoanhThuThang();
    }
    public HoaDonViewModel tongNam(){
        return tkrp.tongDoanhThuNam();
    }
    public HoaDon TT(){
        return tkrp.tongDaTT();
    }
     public ArrayList<HoaDonViewModel> Tim(Integer ma){
        return tkrp.getListHoaDonView(ma);
    }
      public ArrayList<HoaDonViewModel> TheoNgay(String bt, String kt){
        return tkrp.getListHoaDonView(bt, kt);
    }
       public ArrayList<HoaDon> TTAA(){
        return hdrp.getList();
    }
       public ArrayList<HoaDonViewModel> Tim(String ngay){
        return tkrp.Tim(ngay);
    }
}
