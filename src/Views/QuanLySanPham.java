/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package Views;

import Models.DanhMuc;
import Models.KichCo;
import Models.MauSac;
import Models.SanPham;
import Services.SanPhamService;
import Validator.Validate;
import ViewModels.SanPhamViewModel;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashSet;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author X1
 */
public class QuanLySanPham extends javax.swing.JInternalFrame {
    SanPhamService spService = new SanPhamService();
    DecimalFormat formatter = new DecimalFormat("#,###");
    /**
     * Creates new form QuanLySanPham
     */
    public QuanLySanPham() {
        initComponents();
        this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0,0,0,0));
        BasicInternalFrameUI ui = (BasicInternalFrameUI) this.getUI();
        ui.setNorthPane(null);
        load();
    }
    
    void load() {
        loadTableSanPhamChiTiet(spService.getListSanPham());
        loadCboTenDM(spService.getCboDM());
        loadCboMau(spService.getCboMau());
        loadCboSz(spService.getCboSz());
    }

    //<editor-fold defaultstate="collapsed" desc=" LOAD ">
    //LOAD
    void loadTableByThuocTinhMauSac(ArrayList<MauSac> ls) {
        String tenTT = "Màu Sắc";
        DefaultTableModel model = (DefaultTableModel) tblThuocTinh.getModel();
        model.setRowCount(0);
        for (MauSac ms : ls) {
            model.addRow(new Object[]{
                ms.getMaMau(), tenTT, ms.getTenMau()
            });
        }
    }
    void loadTableByThuocTinhSz(ArrayList<KichCo> ls) {
        String tenTT = "Kích Cỡ";
        DefaultTableModel model = (DefaultTableModel) tblThuocTinh.getModel();
        model.setRowCount(0);
        for (KichCo sz : ls) {
            model.addRow(new Object[]{
                sz.getMaSize(), tenTT, sz.getTenSize()
            });
        }
    }
    void loadTableSanPhamChiTiet(ArrayList<SanPhamViewModel> ls) {
        DefaultTableModel model = (DefaultTableModel) tblSPCT.getModel();
        model.setRowCount(0);

        for (SanPhamViewModel sp : ls) {
            String formatgiaN = formatter.format(sp.getGiaNhap());
            String formatgiaB = formatter.format(sp.getGiaBan());

            model.addRow(new Object[]{
                sp.getMaSP(), sp.getTenSP(), sp.getTenDM(),
                sp.getMauSac(), sp.getKichCo(),
                formatgiaN, formatgiaB, sp.getTrangThai(), sp.getSoLuong()
            });
        }
    }
    void loadCboTenDM(ArrayList<DanhMuc> ls) {
        DefaultComboBoxModel cboDM1 = (DefaultComboBoxModel) cboDanhMuc.getModel();
        DefaultComboBoxModel cboDM2 = (DefaultComboBoxModel) cboLocDM.getModel();
        HashSet<String> danhMucSet = new HashSet<>();

        for (DanhMuc dm : ls) {
            String danhMuc = dm.getTenDM();
            // Kiểm tra xem phương thức đã tồn tại trong HashSet chưa
            if (!danhMucSet.contains(danhMuc)) {
                cboDM1.addElement(danhMuc);
                cboDM2.addElement(dm.getTenDM());
                // Thêm phương thức vào HashSet để kiểm tra trùng lặp
                danhMucSet.add(danhMuc);
            }
        }
    }
    void loadCboMau(ArrayList<MauSac> ls) {
        DefaultComboBoxModel cboMS = (DefaultComboBoxModel) cboMauSac.getModel();
        for (MauSac ms : ls) {
            cboMS.addElement(ms.getTenMau());
        }
    }
    void loadCboSz(ArrayList<KichCo> ls) {
        DefaultComboBoxModel cboKC = (DefaultComboBoxModel) cboKichCo.getModel();
        for (KichCo sz : ls) {
            cboKC.addElement(sz.getTenSize());
        }
    }
    //</editor-fold>
    
    void loadDataToFormSPCT() {
        int pos = tblSPCT.getSelectedRow();
        Integer id = (Integer) tblSPCT.getValueAt(pos, 0);
        
        SanPhamViewModel sp = spService.getListById(id);
        
        cboDanhMuc.setSelectedItem(sp.getTenDM());
        txtMSP.setText(sp.getMaSP().toString());
        txtTSPCT.setText(sp.getTenSP());
        txtSoLuong.setText(sp.getSoLuong().toString());
        txtGiaNhap.setText(sp.getGiaNhap().toString());
        txtGiaBan.setText(sp.getGiaBan().toString());
        if (sp.getTrangThai().equalsIgnoreCase("Còn hàng")) {
            rdoConHang.setSelected(true);
        }else{
            rdoHetHang.setSelected(true);
        }
        cboMauSac.setSelectedItem(sp.getMauSac());
        cboKichCo.setSelectedItem(sp.getKichCo());
    }
    //CLEARTEXT
    void clearText(){
        txtSearch.setText("");
    }
    void clearFormSPCT(){
        cboDanhMuc.setSelectedItem("Polo Nam");
        txtMSP.setText("");
        txtTSPCT.setText("");
        txtSoLuong.setText("");
        rdoConHang.isSelected();
        txtGiaNhap.setText("");
        txtGiaBan.setText("");
        cboMauSac.setSelectedItem("Đen");
        cboKichCo.setSelectedItem("XS");
    }
    void clearTableThuocTinh(){
        DefaultTableModel model = (DefaultTableModel) tblThuocTinh.getModel();
        model.setRowCount(0);
    }
    
    //GET MODEL
    public SanPham getModel(){
        String danhMuc = cboDanhMuc.getSelectedItem().toString();
        Integer maDM = spService.getIdByNameee(danhMuc).getMaDM();
        
        int pos = tblSPCT.getSelectedRow();
        Integer maSP = (Integer) tblSPCT.getValueAt(pos, 0);
        String tenSP = txtTSPCT.getText();
        
        String tenMau = cboMauSac.getSelectedItem().toString();
        Integer maMau = spService.getIdByName(tenMau).getMaMau();
        
        String tenSz = cboKichCo.getSelectedItem().toString();
        Integer maSz = spService.getIdByNamee(tenSz).getMaSize();
        
        Double giaNhap = Double.valueOf(txtGiaNhap.getText());
        Double giaBan = Double.valueOf(txtGiaBan.getText());
        String trangThai = rdoConHang.isSelected() ? "Còn hàng" : "Hết hàng";
        Integer soL = Integer.valueOf(txtSoLuong.getText());
        
        SanPham sp = new SanPham(maSP, tenSP, maDM, trangThai, giaNhap, giaBan, maSz, maMau, soL);
        return sp;
    }
    public SanPham getModelSP(){
        String danhMuc = cboDanhMuc.getSelectedItem().toString();
        Integer maDM = spService.getIdByNameee(danhMuc).getMaDM();
        
        Integer maSP = Integer.valueOf(spService.getListSP().get(spService.getListSP().size()-1).getMaSP());
        String tenSP = txtTSPCT.getText();
        
        String tenMau = cboMauSac.getSelectedItem().toString();
        Integer maMau = spService.getIdByName(tenMau).getMaMau();
        
        String tenSz = cboKichCo.getSelectedItem().toString();
        Integer maSz = spService.getIdByNamee(tenSz).getMaSize();
        
        Double giaNhap = Double.valueOf(txtGiaNhap.getText());
        Double giaBan = Double.valueOf(txtGiaBan.getText());
        String trangThai = rdoConHang.isSelected() ? "Còn hàng" : "Hết hàng";
        Integer soL = Integer.valueOf(txtSoLuong.getText());
        
        SanPham sp = new SanPham(maSP, tenSP, maDM, trangThai, giaNhap, giaBan, maSz, maMau, soL);
        return sp;
    }
    
    //GET MODEL THUỘC TÍNH
    public MauSac getModelAddColor(){
        MauSac msAdd = new MauSac();
        
        msAdd.setTenMau(txtTenTT.getText());
        msAdd.setMaMau(Integer.valueOf(txtMaTT.getText()));
        return msAdd;
    }
    
    public KichCo getModelAddSz(){
        KichCo szAdd = new KichCo();
        
        szAdd.setTenSize(txtTenTT.getText());
        szAdd.setMaSize(Integer.valueOf(txtMaTT.getText()));
        return szAdd;
    }
    
    public MauSac getModelMs() {
        MauSac ms = new MauSac();
        String tenM = txtTenTT.getText();

        Integer maMau = spService.getIdByName(tenM).getMaMau();
        ms.setMaMau(maMau);
        ms.setTenMau(tenM);
        return ms;
    }

    public KichCo getModelSz(){
        KichCo sz = new KichCo();
        sz.setMaSize(Integer.valueOf(txtMaTT.getText()));
        sz.setTenSize(txtTenTT.getText());
        return sz;
    }
    
    //VALIDATE
    public Boolean validateSanPham(){
        StringBuilder stb = new  StringBuilder();
        Validate v = new Validate();
        
        v.isEmpty(txtTSPCT, stb, "Chưa nhập tên sản phẩm!");
        v.isEmpty(txtSoLuong, stb, "Số lượng sản phẩm bị trống!");
        v.NumberLimit(txtGiaNhap, stb, "Giá nhập phải là 1 số nguyên!",0,10000);
        v.NumberLimit(txtGiaBan, stb, "Giá bán phải là 1 số nguyên!",0,10000);
        if (stb.length()>0) {
            JOptionPane.showMessageDialog(this, stb);
            return false;
        }else{
            return true;
        }
    }
    public Boolean validateThuocTinh(){
        StringBuilder stb = new  StringBuilder();
        Validate v = new Validate();
        
        v.isEmpty(txtTenTT, stb, "Tên thuộc tính bị trống!");
        if (stb.length()>0) {
            JOptionPane.showMessageDialog(this, stb);
            return false;
        }else{
            return true;
        }
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
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        PaneQLSP = new javax.swing.JTabbedPane();
        ChiTietSanPham = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        txtMSP = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtTSPCT = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        cboDanhMuc = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtGiaBan = new javax.swing.JTextField();
        jPanel8 = new javax.swing.JPanel();
        btnSua = new javax.swing.JButton();
        btnThem = new javax.swing.JButton();
        btnClear = new javax.swing.JButton();
        btnHide = new javax.swing.JButton();
        LoadHide = new javax.swing.JButton();
        btnHide1 = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        txtSoLuong = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        txtGiaNhap = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        rdoConHang = new javax.swing.JRadioButton();
        rdoHetHang = new javax.swing.JRadioButton();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        cboMauSac = new javax.swing.JComboBox<>();
        cboKichCo = new javax.swing.JComboBox<>();
        jPanel11 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblSPCT = new javax.swing.JTable();
        jLabel20 = new javax.swing.JLabel();
        txtSearch = new javax.swing.JTextField();
        btnSearch = new javax.swing.JButton();
        cboLocDM = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        txtMaTT1 = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        btnThemTT1 = new javax.swing.JButton();
        btnSuaTT1 = new javax.swing.JButton();
        btnClearTT1 = new javax.swing.JButton();
        jLabel26 = new javax.swing.JLabel();
        txtTenTT1 = new javax.swing.JTextField();
        btnHideTT1 = new javax.swing.JButton();
        jPanel15 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblThuocTinh1 = new javax.swing.JTable();
        jLabel27 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        ThuocTinh = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        txtMaTT = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        btnThemTT = new javax.swing.JButton();
        btnSuaTT = new javax.swing.JButton();
        btnClearTT = new javax.swing.JButton();
        jLabel24 = new javax.swing.JLabel();
        txtTenTT = new javax.swing.JTextField();
        btnHideTT = new javax.swing.JButton();
        rdoTTMS = new javax.swing.JRadioButton();
        rdoTTKC = new javax.swing.JRadioButton();
        rdoMauSac = new javax.swing.JRadioButton();
        rdoKichCo = new javax.swing.JRadioButton();
        jPanel13 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblThuocTinh = new javax.swing.JTable();
        jLabel21 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();

        setMaximumSize(new java.awt.Dimension(990, 610));
        setMinimumSize(new java.awt.Dimension(990, 610));
        setName(""); // NOI18N
        setPreferredSize(new java.awt.Dimension(990, 610));

        PaneQLSP.setAutoscrolls(true);
        PaneQLSP.setPreferredSize(new java.awt.Dimension(990, 610));

        ChiTietSanPham.setPreferredSize(new java.awt.Dimension(990, 610));

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP)), "Thông tin Chi Tiết", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Segoe UI", 3, 14))); // NOI18N

        jLabel7.setText("Mã Sản Phẩm:");

        txtMSP.setEnabled(false);

        jLabel8.setText("Tên Sản Phẩm:");

        jLabel9.setText("Danh Mục:");

        cboDanhMuc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboDanhMucActionPerformed(evt);
            }
        });

        jLabel10.setText("Trạng Thái:");

        jLabel11.setText("Giá Bán:");

        jPanel8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        btnSua.setText("SỬA");
        btnSua.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSuaMouseClicked(evt);
            }
        });

        btnThem.setText("THÊM");
        btnThem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnThemMouseClicked(evt);
            }
        });

        btnClear.setText("MỚI");
        btnClear.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnClearMouseClicked(evt);
            }
        });

        btnHide.setText("ẨN");
        btnHide.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnHideMouseClicked(evt);
            }
        });

        LoadHide.setText("HIỂN THỊ SP ĐÃ ẨN");
        LoadHide.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                LoadHideMouseClicked(evt);
            }
        });

        btnHide1.setText("BỎ ẨN");
        btnHide1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnHide1MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnHide1, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnHide, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(LoadHide))
                .addContainerGap(21, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThem)
                    .addComponent(btnSua)
                    .addComponent(btnClear)
                    .addComponent(btnHide))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LoadHide)
                    .addComponent(btnHide1))
                .addContainerGap())
        );

        jLabel12.setText("Số Lượng Tồn:");

        jLabel13.setText("VND");

        jLabel14.setText("Giá Nhập:");

        jLabel15.setText("VND");

        buttonGroup1.add(rdoConHang);
        rdoConHang.setText("Còn hàng");

        buttonGroup1.add(rdoHetHang);
        rdoHetHang.setText("Hết hàng");

        jLabel16.setText("Màu Sắc:");

        jLabel17.setText("Kích cỡ:");

        cboMauSac.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboMauSacActionPerformed(evt);
            }
        });

        cboKichCo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboKichCoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(64, 64, 64)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtTSPCT, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtMSP, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cboDanhMuc, javax.swing.GroupLayout.Alignment.LEADING, 0, 251, Short.MAX_VALUE)
                            .addComponent(txtSoLuong))
                        .addGap(58, 58, 58)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel7Layout.createSequentialGroup()
                                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(cboMauSac, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel7Layout.createSequentialGroup()
                                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(rdoConHang, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(28, 28, 28)
                                        .addComponent(rdoHetHang, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel7Layout.createSequentialGroup()
                                        .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(cboKichCo, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(46, 46, 46))))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtGiaNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtGiaBan, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap())))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(cboDanhMuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jLabel9))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(rdoConHang)
                                .addComponent(jLabel10)
                                .addComponent(rdoHetHang)))
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel16)
                                    .addComponent(cboMauSac, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel17)
                                    .addComponent(cboKichCo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(27, 27, 27))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel7Layout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addComponent(jLabel7)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel8)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel12)
                                        .addGap(3, 3, 3))
                                    .addGroup(jPanel7Layout.createSequentialGroup()
                                        .addComponent(txtMSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtTSPCT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGap(15, 15, 15)
                                .addComponent(jLabel14)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel11))
                            .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtGiaNhap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel15))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtGiaBan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel13))))
                        .addGap(96, 96, 96))))
        );

        jPanel11.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP)), "Chi Tiết Sản Phẩm", javax.swing.border.TitledBorder.LEADING, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Segoe UI", 3, 17), new java.awt.Color(0, 0, 153))); // NOI18N

        tblSPCT.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã SP", "Tên SP", "Danh Mục", "Màu Sắc", "Kích Cỡ", "Giá Nhập", "Giá Bán", "Trạng Thái", "Số Lượng"
            }
        ));
        tblSPCT.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSPCTMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblSPCT);

        jLabel20.setText("Tìm kiếm theo tên:");

        btnSearch.setText("SEARCH");
        btnSearch.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSearchMouseClicked(evt);
            }
        });

        jLabel1.setText("Lọc:");

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(cboLocDM, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel20)
                        .addGap(18, 18, 18)
                        .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 903, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSearch)
                    .addComponent(jLabel20)
                    .addComponent(btnSearch)
                    .addComponent(cboLocDM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout ChiTietSanPhamLayout = new javax.swing.GroupLayout(ChiTietSanPham);
        ChiTietSanPham.setLayout(ChiTietSanPhamLayout);
        ChiTietSanPhamLayout.setHorizontalGroup(
            ChiTietSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ChiTietSanPhamLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(ChiTietSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 933, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 15, Short.MAX_VALUE))
        );
        ChiTietSanPhamLayout.setVerticalGroup(
            ChiTietSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ChiTietSanPhamLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        PaneQLSP.addTab("Quản Lý Sản Phẩm", ChiTietSanPham);

        jPanel14.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP)), "Thông tin Danh Mục", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Segoe UI", 3, 14))); // NOI18N

        txtMaTT1.setEnabled(false);

        jLabel25.setText("Tên Danh Mục:");

        btnThemTT1.setText("THÊM");
        btnThemTT1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnThemTT1MouseClicked(evt);
            }
        });

        btnSuaTT1.setText("SỬA");
        btnSuaTT1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSuaTT1MouseClicked(evt);
            }
        });

        btnClearTT1.setText("MỚI");
        btnClearTT1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnClearTT1MouseClicked(evt);
            }
        });

        jLabel26.setText("Mã Danh Mục:");

        btnHideTT1.setText("ẨN");
        btnHideTT1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnHideTT1MouseClicked(evt);
            }
        });
        btnHideTT1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHideTT1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnThemTT1))
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtTenTT1, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtMaTT1, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGap(51, 51, 51)
                        .addComponent(btnSuaTT1)
                        .addGap(49, 49, 49)
                        .addComponent(btnClearTT1)
                        .addGap(54, 54, 54)
                        .addComponent(btnHideTT1)))
                .addContainerGap(444, Short.MAX_VALUE))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel26)
                    .addComponent(txtMaTT1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel25)
                    .addComponent(txtTenTT1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThemTT1)
                    .addComponent(btnSuaTT1)
                    .addComponent(btnClearTT1)
                    .addComponent(btnHideTT1))
                .addContainerGap(25, Short.MAX_VALUE))
        );

        jPanel15.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP)), "Chi Tiết Danh Mục", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Segoe UI", 3, 14))); // NOI18N

        tblThuocTinh1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "STT", "Mã Danh Mục", "Tên Danh Mục"
            }
        ));
        tblThuocTinh1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblThuocTinh1MouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tblThuocTinh1);

        jLabel27.setText("Tìm kiếm danh mục:");

        jButton1.setText("SEARCH");

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel27)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton1))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel15Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 903, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(21, Short.MAX_VALUE))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton1))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jLabel27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        PaneQLSP.addTab("Danh Mục", jPanel1);

        jPanel12.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP)), "Thông tin Thuộc Tính", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Segoe UI", 3, 14))); // NOI18N

        txtMaTT.setEnabled(false);

        jLabel23.setText("Tên Thuộc Tính:");

        btnThemTT.setText("THÊM");
        btnThemTT.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnThemTTMouseClicked(evt);
            }
        });

        btnSuaTT.setText("SỬA");
        btnSuaTT.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSuaTTMouseClicked(evt);
            }
        });

        btnClearTT.setText("MỚI");
        btnClearTT.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnClearTTMouseClicked(evt);
            }
        });

        jLabel24.setText("Mã Thuộc Tính:");

        btnHideTT.setText("ẨN");
        btnHideTT.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnHideTTMouseClicked(evt);
            }
        });
        btnHideTT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHideTTActionPerformed(evt);
            }
        });

        buttonGroup3.add(rdoTTMS);
        rdoTTMS.setText("Màu Sắc");

        buttonGroup3.add(rdoTTKC);
        rdoTTKC.setText("Kich Cỡ");

        buttonGroup2.add(rdoMauSac);
        rdoMauSac.setText("Màu Sắc");
        rdoMauSac.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rdoMauSacMouseClicked(evt);
            }
        });

        buttonGroup2.add(rdoKichCo);
        rdoKichCo.setText("Kích Cỡ");
        rdoKichCo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rdoKichCoMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnThemTT))
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel12Layout.createSequentialGroup()
                                .addComponent(txtTenTT, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(71, 71, 71)
                                .addComponent(rdoTTMS)
                                .addGap(62, 62, 62)
                                .addComponent(rdoTTKC))
                            .addComponent(txtMaTT, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGap(51, 51, 51)
                        .addComponent(btnSuaTT)
                        .addGap(49, 49, 49)
                        .addComponent(btnClearTT)
                        .addGap(54, 54, 54)
                        .addComponent(btnHideTT)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(rdoMauSac)
                        .addGap(40, 40, 40)
                        .addComponent(rdoKichCo)
                        .addGap(52, 52, 52))))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel24)
                            .addComponent(txtMaTT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(26, 26, 26)
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel23)
                            .addComponent(txtTenTT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rdoTTMS)
                            .addComponent(rdoTTKC))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnThemTT)
                            .addComponent(btnSuaTT)
                            .addComponent(btnClearTT)
                            .addComponent(btnHideTT))
                        .addGap(0, 19, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(rdoMauSac)
                            .addComponent(rdoKichCo))))
                .addContainerGap())
        );

        jPanel13.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP)), "Chi Tiết Thuộc Tính", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Segoe UI", 3, 14))); // NOI18N

        tblThuocTinh.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "STT", "Loại Thuộc Tính", "Tên Thuộc Tính"
            }
        ));
        tblThuocTinh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblThuocTinhMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblThuocTinh);

        jLabel21.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel21.setText("Lọc:");

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 903, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jComboBox1)
                    .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 186, Short.MAX_VALUE)
                .addGap(13, 13, 13))
        );

        javax.swing.GroupLayout ThuocTinhLayout = new javax.swing.GroupLayout(ThuocTinh);
        ThuocTinh.setLayout(ThuocTinhLayout);
        ThuocTinhLayout.setHorizontalGroup(
            ThuocTinhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ThuocTinhLayout.createSequentialGroup()
                .addGroup(ThuocTinhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(21, Short.MAX_VALUE))
        );
        ThuocTinhLayout.setVerticalGroup(
            ThuocTinhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ThuocTinhLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        PaneQLSP.addTab("Thuộc Tính", ThuocTinh);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(PaneQLSP, javax.swing.GroupLayout.PREFERRED_SIZE, 954, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(PaneQLSP, javax.swing.GroupLayout.PREFERRED_SIZE, 549, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSearchMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSearchMouseClicked
        // SEARCH BY NAME
        String searchID = txtSearch.getText();
        ArrayList<SanPhamViewModel> ls = spService.getListBySearch(searchID);
        loadTableSanPhamChiTiet(ls);
        clearText();
    }//GEN-LAST:event_btnSearchMouseClicked

    private void tblSPCTMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSPCTMouseClicked
        //CLICK
        loadDataToFormSPCT();
    }//GEN-LAST:event_tblSPCTMouseClicked

    private void cboKichCoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboKichCoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboKichCoActionPerformed

    private void cboMauSacActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboMauSacActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboMauSacActionPerformed

    private void btnClearMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnClearMouseClicked
        // CLEAR
        clearFormSPCT();
        loadTableSanPhamChiTiet(spService.getListSanPham());
    }//GEN-LAST:event_btnClearMouseClicked

    private void btnThemMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnThemMouseClicked
        // ADD
        String checkName = txtTSPCT.getText().trim();
        int result = JOptionPane.showConfirmDialog(this, "Bạn muốn thêm sản phẩm?", "POLYPOLO xác nhận", JOptionPane.YES_NO_OPTION);       
        if (spService.checkName(checkName)) {
            JOptionPane.showMessageDialog(this, "Tên sản phẩm bị trùng, không thể thêm!", "POLY POLO thông báo", 0);
            clearFormSPCT();
        }else{
            if (validateSanPham() && result == JOptionPane.YES_OPTION) {
            JOptionPane.showMessageDialog(this, spService.addSP(getModelSP()));
            loadTableSanPhamChiTiet(spService.getListSanPham());
            clearFormSPCT();
        } else {
            JOptionPane.showMessageDialog(this, "Đã hủy thao tác thêm sản phẩm!", "POLYPOLO thông báo", 0);
        }
        }
    }//GEN-LAST:event_btnThemMouseClicked

    private void cboDanhMucActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboDanhMucActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboDanhMucActionPerformed

    private void btnSuaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSuaMouseClicked
        // UPDATE
        int result = JOptionPane.showConfirmDialog(this, "Bạn muốn sửa sản phẩm này?", "POLYPOLO xác nhận", JOptionPane.YES_NO_OPTION);
        if (result == JOptionPane.YES_OPTION) {
            String resultNek = spService.updateSP(getModel());
            JOptionPane.showMessageDialog(this, resultNek);
            loadTableSanPhamChiTiet(spService.getListSanPham());
        } else {
            JOptionPane.showMessageDialog(this, "Đã hủy thao tác sửa sản phẩm!", "POLYPOLO thông báo", 0);
        }
    }//GEN-LAST:event_btnSuaMouseClicked

    private void btnHideMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHideMouseClicked
      //HIDE
        int result = JOptionPane.showConfirmDialog(this, "Bạn muốn ẩn sản phẩm không?", "POLYPOLO xác nhận", JOptionPane.YES_NO_OPTION);        
        if (result == JOptionPane.YES_OPTION) {
            spService.hideSP(getModel());
            JOptionPane.showMessageDialog(this, "Ẩn sản phẩm thành công!", "POLYPOLO thông báo", 0);
            loadTableSanPhamChiTiet(spService.getListSanPham());
            clearFormSPCT();
        } else {
            JOptionPane.showMessageDialog(this, "Đã hủy thao tác ẩn sản phẩm!", "POLYPOLO thông báo", 0);
        }
    }//GEN-LAST:event_btnHideMouseClicked

    private void tblThuocTinhMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblThuocTinhMouseClicked
        // CLICK
        int selectedRow = tblThuocTinh.getSelectedRow();        
        if (selectedRow != -1) {
            Integer idMau = Integer.valueOf(tblThuocTinh.getValueAt(selectedRow, 0).toString());
            Integer idSz = Integer.valueOf(tblThuocTinh.getValueAt(selectedRow, 0).toString());
            
            String mauSac = tblThuocTinh.getValueAt(selectedRow,2).toString();
            String kichCo = tblThuocTinh.getValueAt(selectedRow, 2).toString();
            txtMaTT.setText(idMau+"");
            txtMaTT.setText(idSz+"");
            
            txtTenTT.setText(mauSac);
            txtTenTT.setText(kichCo);
        }
    }//GEN-LAST:event_tblThuocTinhMouseClicked

    private void btnThemTTMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnThemTTMouseClicked
        //ADD TT
        int result = JOptionPane.showConfirmDialog(this, "Bạn muốn thêm mới thuộc tính?", "POLYPOLO xác nhận", JOptionPane.YES_NO_OPTION);       
        if (result == JOptionPane.YES_OPTION && validateThuocTinh()) {
            if (rdoTTMS.isSelected()) {
                JOptionPane.showMessageDialog(this, spService.addColor(getModelAddColor()));
                loadTableByThuocTinhMauSac(spService.loadDataColor());
            }else if(rdoTTKC.isSelected()){
                JOptionPane.showMessageDialog(this, spService.addSz(getModelAddSz()));
                loadTableByThuocTinhSz(spService.loadDataSz());
            }
        }else{
            JOptionPane.showMessageDialog(this, "Đã hủy thao tác thêm mới thuộc tính!", "POLYPOLO thông báo", 0);
        }
    }//GEN-LAST:event_btnThemTTMouseClicked

    private void btnClearTTMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnClearTTMouseClicked
        //CLEAR SEARCH
        txtTenTT.setText("");
        clearTableThuocTinh();
    }//GEN-LAST:event_btnClearTTMouseClicked

    private void LoadHideMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LoadHideMouseClicked
        //LOAD_UNHIDE
        loadTableSanPhamChiTiet(spService.getListHide());
        clearFormSPCT();
    }//GEN-LAST:event_LoadHideMouseClicked

    private void btnHide1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHide1MouseClicked
        //UNHIDE
        int result = JOptionPane.showConfirmDialog(this, "Bạn muốn bỏ ẩn sản phẩm không?", "Xác nhận", JOptionPane.YES_NO_OPTION);
        if (result == JOptionPane.YES_OPTION) {
            int selectedRow = tblSPCT.getSelectedRow();
            if (selectedRow != -1) {
                Integer maSP = Integer.valueOf(tblSPCT.getModel().getValueAt(selectedRow, 0).toString());
                SanPham sp = new SanPham();
                sp.setMaSP(maSP);
                if (spService.unhideSP(sp)) {
                    JOptionPane.showMessageDialog(this, "Bỏ ẩn sản phẩm thành công!", "POLYPOLO thông báo", 0);
                    loadTableSanPhamChiTiet(spService.getListSanPham());
                    clearFormSPCT(); 
                }
            } else {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn một sản phẩm từ bảng!", "POLYPOLO thông báo", 0);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Đã hủy thao tác bỏ ẩn sản phẩm!", "POLYPOLO thông báo", 0);
        }
    }//GEN-LAST:event_btnHide1MouseClicked

    private void btnHideTTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHideTTActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnHideTTActionPerformed

    private void btnSuaTTMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSuaTTMouseClicked
        //UPDATE TT
        int result = JOptionPane.showConfirmDialog(this, "Bạn muốn cập nhật thuộc tính?", "POLYPOLO xác nhận", JOptionPane.YES_NO_OPTION);
        if (result == JOptionPane.YES_OPTION && validateThuocTinh()) {
            if (rdoTTMS.isSelected()) {
                JOptionPane.showMessageDialog(this, spService.updateCl(getModelAddColor()));
                loadTableByThuocTinhMauSac(spService.loadDataColor());
            }else if(rdoTTKC.isSelected()){
                JOptionPane.showMessageDialog(this, spService.updateSz(getModelAddSz()));
                loadTableByThuocTinhSz(spService.loadDataSz());
            }
        }else{
            JOptionPane.showMessageDialog(this, "Đã hủy thao tác cập nhật thuộc tính!", "POLYPOLO thông báo", 0);
        }
    }//GEN-LAST:event_btnSuaTTMouseClicked

    private void btnHideTTMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHideTTMouseClicked
        // HIDE TT
        int result = JOptionPane.showConfirmDialog(this, "Bạn muốn ẩn thuộc tính không?", "POLYPOLO xác nhận", JOptionPane.YES_NO_OPTION);
        if (result == JOptionPane.YES_OPTION) {
            spService.hidetTTMS(getModelMs());
            spService.hideTTSz(getModelSz());
            JOptionPane.showMessageDialog(this, "Ẩn thuộc tính thành công!", "POLYPOLO thông báo", 0);
            txtTenTT.setText("");
        } else {
            JOptionPane.showMessageDialog(this, "Đã hủy thao tác ẩn thuộc tính", "POLYPOLO thông báo", 0);
        }
    }//GEN-LAST:event_btnHideTTMouseClicked

    private void rdoMauSacMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rdoMauSacMouseClicked
        // FILTER BY COLOR
        loadTableByThuocTinhMauSac(spService.loadDataColor());
    }//GEN-LAST:event_rdoMauSacMouseClicked

    private void rdoKichCoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rdoKichCoMouseClicked
        // FILTER BY SZ
        loadTableByThuocTinhSz(spService.loadDataSz());
    }//GEN-LAST:event_rdoKichCoMouseClicked

    private void btnThemTT1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnThemTT1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnThemTT1MouseClicked

    private void btnSuaTT1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSuaTT1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSuaTT1MouseClicked

    private void btnClearTT1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnClearTT1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnClearTT1MouseClicked

    private void btnHideTT1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHideTT1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnHideTT1MouseClicked

    private void btnHideTT1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHideTT1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnHideTT1ActionPerformed

    private void tblThuocTinh1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblThuocTinh1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tblThuocTinh1MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel ChiTietSanPham;
    private javax.swing.JButton LoadHide;
    private javax.swing.JTabbedPane PaneQLSP;
    private javax.swing.JPanel ThuocTinh;
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnClearTT;
    private javax.swing.JButton btnClearTT1;
    private javax.swing.JButton btnHide;
    private javax.swing.JButton btnHide1;
    private javax.swing.JButton btnHideTT;
    private javax.swing.JButton btnHideTT1;
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnSuaTT;
    private javax.swing.JButton btnSuaTT1;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnThemTT;
    private javax.swing.JButton btnThemTT1;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.JComboBox<String> cboDanhMuc;
    private javax.swing.JComboBox<String> cboKichCo;
    private javax.swing.JComboBox<String> cboLocDM;
    private javax.swing.JComboBox<String> cboMauSac;
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JRadioButton rdoConHang;
    private javax.swing.JRadioButton rdoHetHang;
    private javax.swing.JRadioButton rdoKichCo;
    private javax.swing.JRadioButton rdoMauSac;
    private javax.swing.JRadioButton rdoTTKC;
    private javax.swing.JRadioButton rdoTTMS;
    private javax.swing.JTable tblSPCT;
    private javax.swing.JTable tblThuocTinh;
    private javax.swing.JTable tblThuocTinh1;
    private javax.swing.JTextField txtGiaBan;
    private javax.swing.JTextField txtGiaNhap;
    private javax.swing.JTextField txtMSP;
    private javax.swing.JTextField txtMaTT;
    private javax.swing.JTextField txtMaTT1;
    private javax.swing.JTextField txtSearch;
    private javax.swing.JTextField txtSoLuong;
    private javax.swing.JTextField txtTSPCT;
    private javax.swing.JTextField txtTenTT;
    private javax.swing.JTextField txtTenTT1;
    // End of variables declaration//GEN-END:variables
}
