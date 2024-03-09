/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services;

import Models.HoaDon;
import Repositories.HoaDonRepository;
import Repositories.ThongKeRespository;
import ViewModels.HD_GioHangViewModel;
import ViewModels.HD_HoaDonViewModel;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author hmail
 */
public class ThongKeService {

    ThongKeRespository tkrp = new ThongKeRespository();
    HoaDonRepository hdrp = new HoaDonRepository();

    //PIE CHART
    //LOAD CBO
    public ArrayList<Integer> showYear() {
        return tkrp.showYear();
    }

    public ArrayList<Integer> showMonth() {
        return tkrp.showMonth();
    }

    public ArrayList<HoaDon> getListHD() {
        return hdrp.getList();
    }

    //LOAD
    public ArrayList<HD_HoaDonViewModel> getListHoaDonView(Date ngayLap) {
        return tkrp.getListHoaDonView((java.sql.Date) ngayLap);
    }

    public HD_HoaDonViewModel tongNgay() {
        return tkrp.tongDoanhThuNgay();
    }

    public HD_HoaDonViewModel tongThang() {
        return tkrp.tongDoanhThuThang();
    }

    public HD_HoaDonViewModel tongNam() {
        return tkrp.tongDoanhThuNam();
    }

    public HD_HoaDonViewModel TT() {
        return tkrp.tongDaTT();
    }

    public HD_HoaDonViewModel Treo() {
        return tkrp.tongHDTreo();
    }
    public HD_HoaDonViewModel TienThanh() {
        return tkrp.tongTienThanh();
    }
    public HD_GioHangViewModel SPBan() {
        return tkrp.tongSPBan();
    }

    public ArrayList<HD_HoaDonViewModel> Tim(Integer ma) {
        return tkrp.getListHoaDonView(ma);
    }

    public ArrayList<HD_HoaDonViewModel> TheoNgay(String bt, String kt) {
        return tkrp.getListHoaDonView(bt, kt);
    }

    public ArrayList<HoaDon> TTAA() {
        return hdrp.getList();
    }

    public ArrayList<HD_HoaDonViewModel> Tim(String ngay) {
        return tkrp.Tim(ngay);
    }

    public HD_HoaDonViewModel DonHang() {
        return tkrp.TonDonHang();
    }
}
