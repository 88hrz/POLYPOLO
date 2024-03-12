/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package Views;

import Models.DanhMuc;
import Models.HoaDon;
import Models.HoaDonChiTiet;
import Models.NhanSu;
import Models.SanPham;
import Models.SanPhamChiTiet;
import Services.HoaDonService;
import Validator.Validate;
import ViewModels.HD_GioHangViewModel;
import ViewModels.HD_HoaDonViewModel;
import ViewModels.HD_SanPhamViewModel;
import ViewModels.SanPhamViewModel;
import java.awt.Color;
import java.sql.Date;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashSet;
import javax.swing.DefaultComboBoxModel;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane;

/**
 *
 * @author X1
 */
public class QuanLyBanHang extends javax.swing.JInternalFrame {
    HoaDonService hdService = new HoaDonService();
    DecimalFormat formatter = new DecimalFormat("#,###");
    /**
     * Creates new form QuanLyBanHang
     */
    public QuanLyBanHang() {
        initComponents();
        this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0,0,0,0));
        BasicInternalFrameUI ui = (BasicInternalFrameUI) this.getUI();
        ui.setNorthPane(null);
        load();
    }
    
    //<editor-fold defaultstate="collapsed" desc=" LOAD ">
    void loadTableGioHang(ArrayList<HD_GioHangViewModel> ls){
        DefaultTableModel model = (DefaultTableModel) tblHoaDonChiTiet.getModel();
        model.setRowCount(0);
        
        for (HD_GioHangViewModel gioHang : ls) {
            String formattedDonGia = formatter.format(gioHang.getDonGia());
            String formattedThanhTien = formatter.format(gioHang.getThanhTien());
            
            model.addRow(new Object []{
                gioHang.getMaHDCT(), gioHang.getMaSP(), gioHang.getTenSP(), gioHang.getMauSac()
                    , gioHang.getKichCo(), gioHang.getSoLuong(), formattedDonGia, formattedThanhTien
            });
        }
    }
    void loadTableSanPham(ArrayList<HD_SanPhamViewModel> ls){
        DefaultTableModel model = (DefaultTableModel) tblSanPham.getModel();
        model.setRowCount(0);
        for (HD_SanPhamViewModel sanPham : ls) {
            String formattedDonGia = formatter.format(sanPham.getDonGia());
            
            model.addRow(new Object []{
                sanPham.getMaSP(), sanPham.getTenSP(), sanPham.getTenDM()
                    , sanPham.getMaSac(), sanPham.getKichCo()
                    , formattedDonGia, sanPham.getSoLuong(), sanPham.getTrangThai()
            });
        }
    }
    void loadComboBoxSanPham(ArrayList<DanhMuc> ls) {
        DefaultComboBoxModel cboModel = (DefaultComboBoxModel) cboDanhMuc.getModel();
        HashSet<String> dmSet = new HashSet<>();

        for (DanhMuc dm : ls) {
            String sanPham = dm.getTenDM();
            if (!dmSet.contains(sanPham)) {
                cboModel.addElement(sanPham);
                dmSet.add(sanPham);
            }
        }
    }
    void loadComboBoxtrangThai(ArrayList<HoaDon> ls){
        DefaultComboBoxModel cboModel = (DefaultComboBoxModel) cboTrangThai.getModel();
        HashSet<String> trangThaiSet = new HashSet<>();

        for (HoaDon hoaDon : ls) {
            String trangThai = hoaDon.getTrangThai();
            if (!trangThaiSet.contains(trangThai)) {
                cboModel.addElement(trangThai);
                trangThaiSet.add(trangThai);
            }
        }
    }
    void loadComboBoxtenNV(ArrayList<NhanSu> ls){
        DefaultComboBoxModel cboModel = (DefaultComboBoxModel) cboTenNV.getModel();
        cboModel.removeAllElements();
        for (NhanSu nv : ls) {
            cboModel.addElement(nv.getTenNhanVien());
        }
    }
    void loadComboBoxPhuongThuc(ArrayList<HoaDon> ls){
        DefaultComboBoxModel cboModel = (DefaultComboBoxModel) cboPhuongThuc.getModel();
        HashSet<String> phuongThucSet = new HashSet<>();

        for (HoaDon hoaDon : ls) {
            String phuongThuc = hoaDon.getPhuongThuc();
            if (!phuongThucSet.contains(phuongThuc)) {
                cboModel.addElement(phuongThuc);
                phuongThucSet.add(phuongThuc);
            }
        }
    }
    //</editor-fold>
    
    void load(){
        loadComboBoxPhuongThuc(hdService.getList());
        loadTableHoaDonView(hdService.getListHoaDon());
        loadTableSanPham(hdService.getListSanPham());
        loadComboBoxtenNV(hdService.getListTenNV()); 
        loadComboBoxtrangThai(hdService.getList());
        loadComboBoxSanPham(hdService.getListDM());
    }
    void loadTableHoaDonView(ArrayList<HD_HoaDonViewModel> ls){
        DefaultTableModel model = (DefaultTableModel) tblHoaDon.getModel();
        model.setRowCount(0);
        for (HD_HoaDonViewModel hoaDon : ls) {
            
            model.addRow(new Object []{
                hoaDon.getMaHD(), hoaDon.getTenNV(), hoaDon.getTenKH(), hoaDon.getPhuongThuc(),
                hoaDon.getNgayLap(), hoaDon.getTrangThai()
            });
        }
    }
    
    //<editor-fold defaultstate="collapsed" desc=" Validate ">
    //VALIDATE
    public Boolean validateTel() {
        StringBuilder stb = new StringBuilder();
        Validate v = new Validate();

        v.isEmpty(txtSDT, stb, "Chưa nhập số điện thoại để tìm!");
        v.isPhoneNumber(txtSDT, "Vui lòng nhập đúng định dạng SĐT!", stb);

        if (stb.length() > 0) {
            JOptionPane.showMessageDialog(this, stb);
            return false;
        } else {
            return true;
        }
    }
    public Boolean validateHoaDon(){
        StringBuilder stb = new  StringBuilder();
        Validate v = new Validate();
        
        v.isEmpty(txtTenKH, stb, "Chưa nhập tên khách hàng!");
        if (stb.length()>0) {
            JOptionPane.showMessageDialog(this, stb);
            btnThanhToan.setEnabled(false);
            return false;
        }else{
            return true;
        }
    }
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc=" Clear form">
    //CLEAR FORM
    void clearForm(){
        cboTenNV.setSelectedItem("Loi Choi");
        txtTenKH.setText("");
        txtSDT.setText("");
        cboPhuongThuc.setSelectedItem("Chuyển khoản");
        lblTongTien.setText("0");
        dcsNgayLap.setDate(null);
    }
    void clearGioHang() {
        DefaultTableModel model = (DefaultTableModel) tblHoaDonChiTiet.getModel();
        model.setRowCount(0);
    }
    void clearData(){
        txtTenKH.setBackground(Color.white);
        txtSDT.setBackground(Color.WHITE);
        dcsNgayLap.setBackground(Color.white);
    }
    //</editor-fold>
    
    //GET MODEL
    public HoaDon getModel(){
        java.util.Date utilDate = dcsNgayLap.getDate();
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
   
        String tenNv = cboTenNV.getSelectedItem().toString();
        Integer maNv = hdService.getIdByName(tenNv).getMaNhanVien();
        String tenKh = txtTenKH.getText();
        String phuongThuc = cboPhuongThuc.getSelectedItem().toString();
        
        HoaDon hd = new HoaDon(maNv, tenNv, tenKh, phuongThuc, sqlDate);
        return hd;
    }
    public HoaDon getModelThanhToan(){
        HoaDon hd = new HoaDon();
        
        java.util.Date utilDate = dcsNgayLap.getDate();
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
        
        int pos = tblHoaDon.getSelectedRow();
        hd.setMaHD((Integer) tblHoaDon.getValueAt(pos, 0));
        hd.setTenNV(cboTenNV.getSelectedItem().toString());
        hd.setTenKH(txtTenKH.getText());
        hd.setSoDT(txtSDT.getText());
        hd.setPhuongThuc(cboPhuongThuc.getSelectedItem().toString());
        lblTongTien.setText(hdService.getTotal(pos).getTongTien().toString());
        hd.setNgayLap(sqlDate);
        
        return hd;
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblHoaDon = new javax.swing.JTable();
        btnSearchHD = new javax.swing.JButton();
        cboTrangThai = new javax.swing.JComboBox<>();
        jPanel3 = new javax.swing.JPanel();
        btnXoaSanPham = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblHoaDonChiTiet = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtSDT = new javax.swing.JTextField();
        txtTenKH = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        cboPhuongThuc = new javax.swing.JComboBox<>();
        jPanel5 = new javax.swing.JPanel();
        btnTaoHD = new javax.swing.JButton();
        btnMoi = new javax.swing.JButton();
        btnThanhToan = new javax.swing.JButton();
        lblTongTien = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        cboTenNV = new javax.swing.JComboBox<>();
        jSeparator1 = new javax.swing.JSeparator();
        btnSearchKH = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        dcsNgayLap = new com.toedter.calendar.JDateChooser();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblSanPham = new javax.swing.JTable();
        btnSearch = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        txtSearch1 = new javax.swing.JTextField();
        cboDanhMuc = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        btnFilterSP = new javax.swing.JButton();

        setMaximumSize(new java.awt.Dimension(990, 610));
        setMinimumSize(new java.awt.Dimension(990, 610));

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP)), "Danh sách Hóa Đơn", javax.swing.border.TitledBorder.LEADING, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Segoe UI", 3, 14))); // NOI18N

        tblHoaDon.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        tblHoaDon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Tên NV", "Tên KH", "PTTT", "Ngày Lập", "Trạng Thái"
            }
        ));
        tblHoaDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblHoaDonMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblHoaDon);

        btnSearchHD.setText("SEARCH");
        btnSearchHD.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSearchHDMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 524, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(cboTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnSearchHD, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSearchHD)
                    .addComponent(cboTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(8, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP)), "Giỏ hàng\n", javax.swing.border.TitledBorder.LEADING, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Segoe UI", 3, 14))); // NOI18N

        btnXoaSanPham.setText("XÓA SẢN PHẨM");
        btnXoaSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnXoaSanPhamMouseClicked(evt);
            }
        });

        tblHoaDonChiTiet.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "IDSP", "Sản Phẩm", "Màu Sắc", "Kích Cỡ", "Số Lượng", "Đơn Giá", "Thành Tiền"
            }
        ));
        tblHoaDonChiTiet.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblHoaDonChiTietMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblHoaDonChiTiet);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 524, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnXoaSanPham)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7)
                .addComponent(btnXoaSanPham)
                .addContainerGap())
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tạo Hóa Đơn", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Segoe UI", 3, 16), new java.awt.Color(0, 0, 204))); // NOI18N
        jPanel2.setMaximumSize(new java.awt.Dimension(401, 409));
        jPanel2.setMinimumSize(new java.awt.Dimension(401, 409));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jLabel3.setText("Tên KH:");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jLabel6.setText("Tổng HĐ:");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jLabel7.setText("Người Tạo:");

        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jLabel13.setText("Hình thức TT:");

        jPanel5.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));

        btnTaoHD.setText("TẠO HĐ");
        btnTaoHD.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnTaoHDMouseClicked(evt);
            }
        });

        btnMoi.setText("MỚI");
        btnMoi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnMoiMouseClicked(evt);
            }
        });

        btnThanhToan.setText("THANH TOÁN");
        btnThanhToan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnThanhToanMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(32, Short.MAX_VALUE)
                .addComponent(btnTaoHD)
                .addGap(18, 18, 18)
                .addComponent(btnThanhToan)
                .addGap(18, 18, 18)
                .addComponent(btnMoi)
                .addGap(25, 25, 25))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnTaoHD)
                    .addComponent(btnMoi)
                    .addComponent(btnThanhToan))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        lblTongTien.setText("0");

        jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jLabel14.setText("Ngày Lập:");

        btnSearchKH.setText("SEARCH");
        btnSearchKH.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSearchKHMouseClicked(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Segoe UI", 3, 13)); // NOI18N
        jLabel5.setText("SĐT Khách:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jSeparator1)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel6))
                            .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(0, 2, Short.MAX_VALUE)
                                .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnSearchKH))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cboTenNV, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(lblTongTien, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtTenKH, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(cboPhuongThuc, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(dcsNgayLap, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSearchKH)
                    .addComponent(jLabel5))
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(cboTenNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTenKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(cboPhuongThuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTongTien)
                    .addComponent(jLabel6))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(dcsNgayLap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14))
                .addGap(18, 18, 18)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP)), "Danh sách Sản Phẩm\n", javax.swing.border.TitledBorder.LEADING, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Segoe UI", 3, 14))); // NOI18N

        tblSanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "IDSP", "Sản Phẩm", "Danh Mục", "Màu Sắc", "Kích Cỡ", "Đơn Giá", "Số Lượng", "Trạng Thái"
            }
        ));
        tblSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSanPhamMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblSanPham);

        btnSearch.setText("SEARCH");
        btnSearch.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSearchMouseClicked(evt);
            }
        });

        jLabel2.setText("Tìm kiếm theo tên:");

        jLabel1.setText("Lọc:");

        btnFilterSP.setText("SEARCH");
        btnFilterSP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnFilterSPMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 939, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(cboDanhMuc, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnFilterSP, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(txtSearch1, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)))
                .addGap(15, 15, 15))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(txtSearch1)
                        .addComponent(btnSearch, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cboDanhMuc)
                            .addComponent(jLabel1)
                            .addComponent(btnFilterSP))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(40, 40, 40))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tblHoaDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHoaDonMouseClicked
        // HOADON CLICK
        DecimalFormat format = new DecimalFormat("#,###");
        
        int pos = tblHoaDon.getSelectedRow();
        if (pos != -1) {
            Integer maHD = (Integer) tblHoaDon.getValueAt(pos, 0);
            double tongTien = hdService.getTotal(maHD).getTongTien();
            String formattedTongTien = format.format(tongTien);
            lblTongTien.setText(formattedTongTien);

            loadTableGioHang(hdService.getListGioHangById(maHD));

            HD_HoaDonViewModel hoaDonViewDetails = hdService.getListHDById(maHD);

            if (hoaDonViewDetails != null) {
                cboTrangThai.setSelectedItem(hoaDonViewDetails.getTrangThai());
                cboTenNV.setSelectedItem(hoaDonViewDetails.getTenNV());
                txtTenKH.setText(hoaDonViewDetails.getTenKH());
                txtSDT.setText(hoaDonViewDetails.getSoDT());
                cboPhuongThuc.setSelectedItem(hoaDonViewDetails.getPhuongThuc());
                dcsNgayLap.setDate(hoaDonViewDetails.getNgayLap());
            }
        }
    }//GEN-LAST:event_tblHoaDonMouseClicked

    private void btnThanhToanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnThanhToanMouseClicked
        if (validateHoaDon()) {
            String result = hdService.thanhToan(getModelThanhToan());
            JOptionPane.showMessageDialog(this, result);
            loadTableHoaDonView(hdService.getListHoaDon());
            clearForm();
            clearGioHang();
        }
    }//GEN-LAST:event_btnThanhToanMouseClicked

    private void btnTaoHDMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnTaoHDMouseClicked
        //ADD HOADON
        int result = JOptionPane.showConfirmDialog(this, "Bạn muốn tạo hóa đơn?", "POLYPOLO xác nhận", JOptionPane.YES_NO_OPTION);

        if (validateHoaDon() && result == JOptionPane.YES_OPTION) {
            btnThanhToan.setEnabled(true);
            String kq = hdService.add(getModel());
            JOptionPane.showMessageDialog(this, kq);
            //LOAD LIST CHX THANHTOANN
            loadTableHoaDonView(hdService.getListByTrangThai("Chưa thanh toán"));
            clearForm();
        } else {
            JOptionPane.showMessageDialog(this, "Đã hủy thao tác tạo hóa đơn!", "POLYPOLO thông báo", 0);
        }
    }//GEN-LAST:event_btnTaoHDMouseClicked

    private void btnSearchMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSearchMouseClicked
        // SEARCH
        String name = txtSearch1.getText();
        ArrayList<HD_SanPhamViewModel> ls = hdService.getListBySearchName(name);
        loadTableSanPham(ls);
    }//GEN-LAST:event_btnSearchMouseClicked

    private void tblSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSanPhamMouseClicked
        // CLICK SANPHAM -> GIOHANG
        DecimalFormat format = new DecimalFormat("#,###");

        if (tblHoaDon.getSelectedRow() != -1) {
            String soL = JOptionPane.showInputDialog(this, "Nhập số lượng SP muốn mua: ", "POLYPOLO xác nhận", 0);
            if (soL != null && !soL.isEmpty()) {
                int soLuongMua = Integer.parseInt(soL);
                int pos = tblSanPham.getSelectedRow();
                HD_SanPhamViewModel sp = hdService.getListSanPham().get(pos);

                if (sp.getSoLuong() >= soLuongMua) {
                    int posHD = tblHoaDon.getSelectedRow();
                    Integer maHD = Integer.valueOf(tblHoaDon.getValueAt(posHD, 0).toString());
                    Integer maSP = (Integer) tblSanPham.getValueAt(pos, 0);
                    String giaV = tblSanPham.getValueAt(pos, 5).toString();
                    Double gia = Double.parseDouble(giaV);

                    HoaDonChiTiet hdct = new HoaDonChiTiet(maHD, maSP, soLuongMua, gia);
                    JOptionPane.showMessageDialog(this, hdService.addHDCT(hdct));

                    hdService.updateSP(sp.getSoLuong() - soLuongMua, maSP);

                    loadTableGioHang(hdService.getListGioHangById(maHD));
                    loadTableSanPham(hdService.getListSanPham());

                    Double tongTien = hdService.getTotal(maHD).getTongTien();
                    String formattedTongTien = format.format(tongTien);
                    lblTongTien.setText(formattedTongTien);
                } else {
                    JOptionPane.showMessageDialog(this, "Số lượng sản phẩm hiện tại không đủ!", "POLYPOLO thông báo", JOptionPane.WARNING_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Bạn chưa nhập số lượng!", "POLYPOLO thông báo", JOptionPane.WARNING_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Bạn cần chọn hoặc tạo hóa đơn mới trước khi thêm sản phẩm vào giỏ hàng!", "POLYPOLO thông báo", JOptionPane.WARNING_MESSAGE);
        }
//        DecimalFormat format = new DecimalFormat("#,###");
//        
//        String soL = JOptionPane.showInputDialog(this, "Nhập số lượng SP muốn mua: ");
//        int pos = tblSanPham.getSelectedRow();
//        HD_SanPhamViewModel sp = hdService.getListSanPham().get(pos);
//        if (!soL.isEmpty()) {
//            int posHD = tblHoaDon.getSelectedRow();
//            Integer maHD = Integer.valueOf(tblHoaDon.getValueAt(posHD, 0).toString());
//            Integer maSP = (Integer) tblSanPham.getValueAt(pos, 0);
//            String giaV = tblSanPham.getValueAt(pos, 5).toString();           
//            Double gia = Double.parseDouble(giaV);
//            
//            HoaDonChiTiet hdct = new HoaDonChiTiet(maHD, maSP, Integer.parseInt(soL), gia);
//            JOptionPane.showMessageDialog(this, hdService.addHDCT(hdct));
//            
//            hdService.updateSP(sp.getSoLuong()-Integer.parseInt(soL), maSP);
//            
//            loadTableGioHang(hdService.getListGioHangById(maHD));
//            loadTableSanPham(hdService.getListSanPham());
//
//            Double tongTien = hdService.getTotal(maHD).getTongTien();
//            String formattedTongTien = format.format(tongTien);
//            lblTongTien.setText(formattedTongTien);
//        }
    }//GEN-LAST:event_tblSanPhamMouseClicked

    private void btnMoiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMoiMouseClicked
        // RESET
        clearForm();
        clearData();
        clearGioHang();
        JOptionPane.showInternalMessageDialog(this, "Làm mới thành công!", "POLYPOLO thông báo!", 0);
        loadTableHoaDonView(hdService.getListHoaDon());
        btnThanhToan.setEnabled(true);
    }//GEN-LAST:event_btnMoiMouseClicked

    private void tblHoaDonChiTietMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHoaDonChiTietMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tblHoaDonChiTietMouseClicked

    private void btnSearchHDMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSearchHDMouseClicked
        //SEARCH
        if (cboTrangThai.getSelectedItem().equals("Đã thanh toán")) {;
            loadTableHoaDonView(hdService.getListByTrangThai("Đã thanh toán"));
            btnThanhToan.setEnabled(false);
            btnXoaSanPham.setEnabled(false);
        } else {
            loadTableHoaDonView(hdService.getListByTrangThai("Chưa thanh toán"));
            btnThanhToan.setEnabled(true);
            btnXoaSanPham.setEnabled(true);
        }
    }//GEN-LAST:event_btnSearchHDMouseClicked

    private void btnXoaSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnXoaSanPhamMouseClicked
        //DELETE SP
        int pos = tblHoaDonChiTiet.getSelectedRow();
        int posHD = tblHoaDon.getSelectedRow();
        if (pos == -1) {
            JOptionPane.showMessageDialog(this, "Hãy chọn sản phẩm từ giỏ hàng để xóa!", "POLYPOLO thông báo", JOptionPane.WARNING_MESSAGE);
        }else{
            Integer maHDCT = Integer.valueOf(tblHoaDonChiTiet.getValueAt(pos, 0).toString());
            Integer maSPCT = Integer.valueOf(tblHoaDonChiTiet.getValueAt(pos, 1).toString());
            Integer soL = Integer.valueOf(tblHoaDonChiTiet.getValueAt(pos, 5).toString());
            Integer maHD = Integer.valueOf(tblHoaDon.getValueAt(posHD, 0).toString());
            JOptionPane.showMessageDialog(this, hdService.deleteProduct(maHDCT));    
            
            SanPhamChiTiet spct = hdService.getById(maSPCT);
            spct.setSoLuong(spct.getSoLuong()+soL);
            
            hdService.updateSPTon(spct.getSoLuong(), maSPCT);
            
            loadTableGioHang(hdService.getListGioHangById(maHD));
            loadTableSanPham(hdService.getListSanPham());
        }
    }//GEN-LAST:event_btnXoaSanPhamMouseClicked

    private void btnSearchKHMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSearchKHMouseClicked
        //SEARCH
        if (validateTel()) {
            String soDT = txtSDT.getText();
            loadTableHoaDonView(hdService.searchTel(soDT));
        }
    }//GEN-LAST:event_btnSearchKHMouseClicked

    private void btnFilterSPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnFilterSPMouseClicked
        // FILTER
        String danhMuc = cboDanhMuc.getSelectedItem().toString();
        loadTableSanPham(hdService.getListByDanhMuc(danhMuc));
    }//GEN-LAST:event_btnFilterSPMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnFilterSP;
    private javax.swing.JButton btnMoi;
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton btnSearchHD;
    private javax.swing.JButton btnSearchKH;
    private javax.swing.JButton btnTaoHD;
    private javax.swing.JButton btnThanhToan;
    private javax.swing.JButton btnXoaSanPham;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cboDanhMuc;
    private javax.swing.JComboBox<String> cboPhuongThuc;
    private javax.swing.JComboBox<String> cboTenNV;
    private javax.swing.JComboBox<String> cboTrangThai;
    private com.toedter.calendar.JDateChooser dcsNgayLap;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lblTongTien;
    private javax.swing.JTable tblHoaDon;
    private javax.swing.JTable tblHoaDonChiTiet;
    private javax.swing.JTable tblSanPham;
    private javax.swing.JTextField txtSDT;
    private javax.swing.JTextField txtSearch1;
    private javax.swing.JTextField txtTenKH;
    // End of variables declaration//GEN-END:variables
}
