/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package Views;

import Models.HoaDon;
import Models.HoaDonChiTiet;
import Models.HoaDonView;
import Models.NhanSu;
import Services.HoaDonService;
import Validator.Validate;
import ViewModels.GioHangViewModel;
import ViewModels.HoaDonViewModel;
import ViewModels.HoaDon_SPViewModel;
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
        loadComboBoxPhuongThuc(hdService.getList());
        loadTableHoaDonView(hdService.getListHoaDonView());
        loadTableSanPham(hdService.getListSanPham());
        loadComboBoxtenNV(hdService.getListTenNV()); 
        loadComboBoxtrangThai(hdService.getList());
    }
    
    //LOAD
    void loadDataToForm(int pos){
        Integer id = (Integer) tblHoaDon.getValueAt(pos, 0);
        HoaDonViewModel hd = hdService.getListHDById(id);
       
        cboTrangThai.setSelectedItem(hd.getTrangThai());
        cboTenNV.setSelectedItem(hd.getTenNV());
        txtTenKH.setText(hd.getTenKH());
        cboPhuongThuc.setSelectedItem(hd.getPhuongThuc());
        txtSDT.setText(String.valueOf(hd.getSoDT()));
        txtNgayLap.setText(hd.getNgayLap());
    }
    void loadTableGioHang(ArrayList<GioHangViewModel> ls){
        DefaultTableModel model = (DefaultTableModel) tblHoaDonChiTiet.getModel();
        model.setRowCount(0);
        
        for (GioHangViewModel gioHang : ls) {
            String formattedDonGia = formatter.format(gioHang.getDonGia());
            String formattedThanhTien = formatter.format(gioHang.getThanhTien());
            
            model.addRow(new Object []{
                gioHang.getMaSP(), gioHang.getTenSP(), gioHang.getMauSac()
                    , gioHang.getKichCo(), gioHang.getSoLuong(), formattedDonGia, formattedThanhTien
            });
        }
    }
    void loadComboBoxtrangThai(ArrayList<HoaDon> ls){
        DefaultComboBoxModel cboModel = (DefaultComboBoxModel) cboTrangThai.getModel();
        // Sử dụng HashSet để lưu các giá trị đã thêm vào ComboBox
        HashSet<String> trangThaiSet = new HashSet<>();

        for (HoaDon hoaDon : ls) {
            String trangThai = hoaDon.getTrangThai();
            // Kiểm tra xem phương thức đã tồn tại trong HashSet chưa
            if (!trangThaiSet.contains(trangThai)) {
                cboModel.addElement(trangThai);
                // Thêm phương thức vào HashSet để kiểm tra trùng lặp
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
        // Sử dụng HashSet để lưu các giá trị đã thêm vào ComboBox
        HashSet<String> phuongThucSet = new HashSet<>();

        for (HoaDon hoaDon : ls) {
            String phuongThuc = hoaDon.getPhuongThuc();
            // Kiểm tra xem phương thức đã tồn tại trong HashSet chưa
            if (!phuongThucSet.contains(phuongThuc)) {
                cboModel.addElement(phuongThuc);
                // Thêm phương thức vào HashSet để kiểm tra trùng lặp
                phuongThucSet.add(phuongThuc);
            }
        }
    }
    void loadTableHoaDonViewSearch(ArrayList<HoaDonView> ls){
        DefaultTableModel model = (DefaultTableModel) tblHoaDon.getModel();
        model.setRowCount(0);
        for (HoaDonView hoaDon : ls) {
            
            model.addRow(new Object []{
                hoaDon.getMaHD(), hoaDon.getTenNv(), hoaDon.getTenKH(), hoaDon.getpTTT(),
                hoaDon.getNgayLap()
            });
        }
    }
    void loadTableHoaDonView(ArrayList<HoaDonViewModel> ls){
        DefaultTableModel model = (DefaultTableModel) tblHoaDon.getModel();
        model.setRowCount(0);
        for (HoaDonViewModel hoaDon : ls) {
            
            model.addRow(new Object []{
                hoaDon.getMaHD(), hoaDon.getTenNV(), hoaDon.getTenKH(), hoaDon.getPhuongThuc(),
                hoaDon.getNgayLap()
            });
        }
    }
    void loadTableByTrangThai(ArrayList<HoaDon> ls){
        DefaultTableModel model = (DefaultTableModel) tblHoaDon.getModel();
        model.setRowCount(0);
        for (HoaDon hoaDon : ls) {
            String formattedTT = formatter.format(hoaDon.getTongTien());
            
            model.addRow(new Object []{
                hoaDon.getMaHD(), hoaDon.getTenNV(), hoaDon.getTenKH()
                    , hoaDon.getPhuongThuc(), hoaDon.getNgayLap(), formattedTT
            });
        }
    }
    void loadTableSanPham(ArrayList<HoaDon_SPViewModel> ls){
        DefaultTableModel model = (DefaultTableModel) tblSanPham.getModel();
        model.setRowCount(0);
        for (HoaDon_SPViewModel sanPham : ls) {
            String formattedDonGia = formatter.format(sanPham.getDonGia());
            
            model.addRow(new Object []{
                sanPham.getMaSP(), sanPham.getTenSP(), sanPham.getTenDM()
                    , sanPham.getMaSac(), sanPham.getKichCo()
                    , formattedDonGia, sanPham.getSoLuong(), sanPham.getTrangThai()
            });
        }
    }
    
    //VALIDATE
    public Boolean validateHoaDon(){
        StringBuilder stb = new  StringBuilder();
        Validate v = new Validate();
        
        v.isEmpty(txtTenKH, stb, "Chưa nhập tên khách hàng!");
        v.isEmpty(txtSDT, stb, "Chưa nhập số điện thoại!");
        v.isEmpty(txtNgayLap, stb, "Chưa điền ngày lập!");
        if (stb.length()>0) {
            JOptionPane.showMessageDialog(this, stb);
            return false;
        }else{
            return true;
        }
    }

    //CLEAR FORM
    void clearForm(){
        cboTenNV.setSelectedItem("Loi Choi");
        txtTenKH.setText("");
        txtSDT.setText("");
        cboPhuongThuc.setSelectedItem("Chuyển khoản");
        lblTongTien.setText("0");
        txtNgayLap.setText("");
    }
    
    public HoaDon getmodel(){
        HoaDon hd = new HoaDon();
        hd.setMaHD(Integer.valueOf(tblHoaDon.getValueAt(tblHoaDon.getSelectedRow(), 0).toString()));
        hd.setNgayLap(txtNgayLap.getText());
        hd.setPhuongThuc(String.valueOf(cboPhuongThuc.getSelectedItem()));
        hd.setTenKH(txtTenKH.getText());
        return hd;
    }
    
    public HoaDon getmodel2(){
        HoaDon hd = new HoaDon();

        String tenNv = String.valueOf(cboTenNV.getSelectedItem());
        System.out.println(tenNv);
        
        hd.setMaNV(hdService.getId(tenNv).get(0).getMaNhanVien());
        hd.setNgayLap(txtNgayLap.getText());
        hd.setTongTien(Double.valueOf(lblTongTien.getText()));
        hd.setPhuongThuc(String.valueOf(cboPhuongThuc.getSelectedItem()));
        hd.setTenKH(txtTenKH.getText());
        hd.setTenNV(cboTenNV.getSelectedItem().toString());
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
        btnXoaTatCa = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblHoaDonChiTiet = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtSDT = new javax.swing.JTextField();
        txtTenKH = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        cboPhuongThuc = new javax.swing.JComboBox<>();
        jPanel5 = new javax.swing.JPanel();
        btnThanhToan = new javax.swing.JButton();
        btnTaoHD = new javax.swing.JButton();
        btnHuyHD = new javax.swing.JButton();
        lblTongTien = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        txtNgayLap = new javax.swing.JTextField();
        cboTenNV = new javax.swing.JComboBox<>();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblSanPham = new javax.swing.JTable();
        btnSearch = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        txtSearch1 = new javax.swing.JTextField();

        setMaximumSize(new java.awt.Dimension(990, 610));
        setMinimumSize(new java.awt.Dimension(990, 610));

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP)), "Danh sách Hóa Đơn", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Segoe UI", 3, 14))); // NOI18N

        tblHoaDon.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        tblHoaDon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID", "Tên NV", "Tên KH", "PTTT", "Ngày Lập"
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
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 529, Short.MAX_VALUE)
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
                .addGap(14, 14, 14))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP)), "Hóa Đơn Chi Tiết", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Segoe UI", 3, 14))); // NOI18N

        btnXoaTatCa.setText("XÓA GIỎ HÀNG");
        btnXoaTatCa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnXoaTatCaMouseClicked(evt);
            }
        });

        tblHoaDonChiTiet.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "IDSP", "Sản Phẩm", "Màu Sắc", "Kích Cỡ", "Số Lượng", "Đơn Giá", "Thành Tiền"
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
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 529, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnXoaTatCa)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnXoaTatCa)
                .addGap(7, 7, 7))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tạo Hóa Đơn", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Segoe UI", 3, 14))); // NOI18N
        jPanel2.setMaximumSize(new java.awt.Dimension(401, 409));
        jPanel2.setMinimumSize(new java.awt.Dimension(401, 409));

        jLabel3.setText("Tên KH:");

        jLabel4.setText("SĐT:");

        jLabel6.setText("Tổng HĐ:");

        jLabel7.setText("Người Tạo:");

        jLabel13.setText("Hình thức TT:");

        jPanel5.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));

        btnThanhToan.setText("THANH TOÁN");
        btnThanhToan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnThanhToanMouseClicked(evt);
            }
        });

        btnTaoHD.setText("TẠO HĐ");
        btnTaoHD.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnTaoHDMouseClicked(evt);
            }
        });

        btnHuyHD.setText("HỦY HĐ");
        btnHuyHD.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnHuyHDMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(btnTaoHD)
                .addGap(18, 18, 18)
                .addComponent(btnThanhToan)
                .addGap(18, 18, 18)
                .addComponent(btnHuyHD)
                .addContainerGap(27, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThanhToan)
                    .addComponent(btnTaoHD)
                    .addComponent(btnHuyHD))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        lblTongTien.setText("0");

        jLabel14.setText("Ngày Lập:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33)
                        .addComponent(txtNgayLap, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(30, 30, 30)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cboPhuongThuc, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtTenKH, javax.swing.GroupLayout.DEFAULT_SIZE, 239, Short.MAX_VALUE)
                                .addComponent(txtSDT))
                            .addComponent(lblTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cboTenNV, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(27, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(cboTenNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtTenKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(cboPhuongThuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblTongTien))
                .addGap(15, 15, 15)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(txtNgayLap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP)), "Danh sách Sản Phẩm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Segoe UI", 3, 14))); // NOI18N

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

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 933, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtSearch1, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(15, 15, 15))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSearch)
                    .addComponent(jLabel2)
                    .addComponent(txtSearch1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(12, 12, 12))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 375, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tblHoaDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHoaDonMouseClicked
        // HOADON CLICK
        DecimalFormat format = new DecimalFormat("#,###");
        
        int pos = tblHoaDon.getSelectedRow();
        
        if (pos != -1) { // Kiểm tra xem có dòng nào được chọn không
            Integer maHD = (Integer) tblHoaDon.getValueAt(pos, 0);
            double tongTien = hdService.getTongTien(maHD).getTongTien();
            String formattedTongTien = format.format(tongTien);
            lblTongTien.setText(formattedTongTien);
            
            loadTableGioHang(hdService.getListGioHang(maHD));
            
            HoaDonViewModel hoaDonViewDetails = hdService.getListHDById(maHD); // Sử dụng phương thức mới để lấy đối tượng
            if (hoaDonViewDetails != null) {
                cboTrangThai.setSelectedItem(hoaDonViewDetails.getTrangThai());
                cboTenNV.setSelectedItem(hoaDonViewDetails.getTenNV());
                txtTenKH.setText(hoaDonViewDetails.getTenKH());
                txtSDT.setText(String.valueOf(hoaDonViewDetails.getSoDT()));
                cboPhuongThuc.setSelectedItem(hoaDonViewDetails.getPhuongThuc());
                txtNgayLap.setText(hoaDonViewDetails.getNgayLap());
            }
        }
    }//GEN-LAST:event_tblHoaDonMouseClicked

    private void btnThanhToanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnThanhToanMouseClicked
        String kq = hdService.thanhToan(getmodel());
        JOptionPane.showMessageDialog(this, kq);
        loadTableHoaDonView(hdService.getListHoaDonView());
        hdService.getListByTrangThai("Đã thanh toán");
    }//GEN-LAST:event_btnThanhToanMouseClicked

    private void btnTaoHDMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnTaoHDMouseClicked
        //ADD HOADON
        int result = JOptionPane.showConfirmDialog(this, "Bạn muốn tạo hóa đơn?", "POLYPOLO xác nhận", JOptionPane.YES_NO_OPTION);
        
        if (validateHoaDon() && result == JOptionPane.YES_OPTION) {
            btnThanhToan.setEnabled(true);
            String kq = hdService.Add(getmodel2());
            JOptionPane.showMessageDialog(this, kq);
            loadTableHoaDonView(hdService.getListHoaDonView());
            clearForm();
        }
    }//GEN-LAST:event_btnTaoHDMouseClicked

    private void btnSearchMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSearchMouseClicked
        // SEARCH
        String name = txtSearch1.getText();
        ArrayList<HoaDon_SPViewModel> ls = hdService.getListBySearchName(name);
        loadTableSanPham(ls);
    }//GEN-LAST:event_btnSearchMouseClicked

    private void tblSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSanPhamMouseClicked
        // CLICK SANPHAM -> GIOHANG
        DecimalFormat format = new DecimalFormat("#,###");
        
        String soL = JOptionPane.showInputDialog(this, "Nhập số lượng SP muốn mua: ");
        int pos = tblSanPham.getSelectedRow();
        HoaDon_SPViewModel sp = hdService.getListSanPham().get(pos);
        if (!soL.isEmpty()) {
            int posHD = tblHoaDon.getSelectedRow();
            Integer maHD = Integer.valueOf(tblHoaDon.getValueAt(posHD, 0).toString());
            Integer maSP = (Integer) tblSanPham.getValueAt(pos, 0);
            
            String giaV = tblSanPham.getValueAt(pos, 5).toString();           
            double gia = Double.parseDouble(giaV);
            
            HoaDonChiTiet hdct = new HoaDonChiTiet(maHD, maSP, Integer.parseInt(soL), gia);
            JOptionPane.showMessageDialog(this, hdService.addHDCT(hdct));
            hdService.updateSP(sp.getSoLuong()-Integer.parseInt(soL), maSP);
            loadTableGioHang(hdService.getListGioHang(maHD));
            loadTableSanPham(hdService.getListSanPham());
            
            double tongTien = hdService.getTongTien(maHD).getTongTien();
            String formattedTongTien = format.format(tongTien);
            lblTongTien.setText(formattedTongTien);
      //    lblTongTien.setText(String.valueOf(hdService.getTongTien(maHD).getTongTien()));
        }
    }//GEN-LAST:event_tblSanPhamMouseClicked

    private void btnHuyHDMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHuyHDMouseClicked
        // HUY HD
        clearForm();
        JOptionPane.showInternalMessageDialog(this, "Hủy hóa đơn thành công!", "POLYPOLO thông báo!", 0);
    }//GEN-LAST:event_btnHuyHDMouseClicked

    private void tblHoaDonChiTietMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHoaDonChiTietMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tblHoaDonChiTietMouseClicked

    private void btnSearchHDMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSearchHDMouseClicked
        // SEARCH
        String trangThai = (String) cboTrangThai.getSelectedItem();
        loadTableByTrangThai(hdService.getListByTrangThai(trangThai));
    }//GEN-LAST:event_btnSearchHDMouseClicked

    private void btnXoaTatCaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnXoaTatCaMouseClicked
        //DEL ALL
        Integer maSPCT = Integer.valueOf(tblHoaDon.getValueAt(tblHoaDon.getSelectedRow(), 0).toString());
        String rs = hdService.Xoa1SPDCT(maSPCT);
        JOptionPane.showMessageDialog(this, rs);
        lblTongTien.setText("");
        loadTableGioHang(hdService.getListGioHang(maSPCT));

        //        Integer maSPCT = Integer.valueOf(tblHoaDonChiTiet.getValueAt(tblHoaDonChiTiet.getSelectedRow(),0).toString());
        //        Integer maHD = Integer.valueOf(tblHoaDon.getValueAt(tblHoaDonChiTiet.getSelectedRow(), 1).toString());
        //        String kq = hdService.deleteSingle(maSPCT, maHD);
        //        JOptionPane.showMessageDialog(this, kq);
        //        loadTableHoaDonView(hdService.getListHoaDonView());
    }//GEN-LAST:event_btnXoaTatCaMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnHuyHD;
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton btnSearchHD;
    private javax.swing.JButton btnTaoHD;
    private javax.swing.JButton btnThanhToan;
    private javax.swing.JButton btnXoaTatCa;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cboPhuongThuc;
    private javax.swing.JComboBox<String> cboTenNV;
    private javax.swing.JComboBox<String> cboTrangThai;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
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
    private javax.swing.JLabel lblTongTien;
    private javax.swing.JTable tblHoaDon;
    private javax.swing.JTable tblHoaDonChiTiet;
    private javax.swing.JTable tblSanPham;
    private javax.swing.JTextField txtNgayLap;
    private javax.swing.JTextField txtSDT;
    private javax.swing.JTextField txtSearch1;
    private javax.swing.JTextField txtTenKH;
    // End of variables declaration//GEN-END:variables
}
