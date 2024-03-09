 /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package Views;

import Models.User;
import Repositories.H_TaiKhoanRepository;
import Services.NguoiDungService;
import Validator.Validate;
import ViewModels.UserViewModel;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author X1
 */
public class QuanLyTaiKhoan extends javax.swing.JInternalFrame {
    NguoiDungService uService = new  NguoiDungService();
    H_TaiKhoanRepository taiKhoanRepository = new H_TaiKhoanRepository();

    /**
     * Creates new form QuanLyTaiKhoan
     */
    
    public QuanLyTaiKhoan() {
        initComponents();
        this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0,0,0,0));
        BasicInternalFrameUI ui = (BasicInternalFrameUI) this.getUI();
        ui.setNorthPane(null);
    //    loadTableUser(uService.getListUser());
//        loadTableTaiKhoan(uService.getList());
        getData();
    }
    
    public static UserViewModel u;
    private String d;
    public String Data(String d){
        this.d = d;
        return d;
    }
    
    public void getData(){        
        lblTenDN.setText(taiKhoanRepository.getTenDN(Data(Login.dataStatic)));
        txtMatKhau.setText(taiKhoanRepository.getMK(Data(Login.dataStatic)));
        lblHoTen.setText(taiKhoanRepository.getName(Data(Login.dataStatic)));
        lblGioiTinh.setText(taiKhoanRepository.getGioiTinh(Data(Login.dataStatic)));
        lblVaiTro.setText(taiKhoanRepository.getVaiTro(Data(Login.dataStatic)));
        lblSDT.setText(taiKhoanRepository.getSDT(Data(Login.dataStatic)));
        lblDiaChi.setText(taiKhoanRepository.getDiaChi(Data(Login.dataStatic)));
    }
    
    //LOAD
    void loadDataToFormTaiKhoan(int pos){
        txtMaNguoiDung.setText(uService.getList().get(pos).getId().toString());
        txtTenDangNhap.setText(uService.getList().get(pos).getTenDN());
        txtMatKhau.setText(uService.getList().get(pos).getMatKhau());
        if (uService.getList().get(pos).getVaiTro().equalsIgnoreCase("Admin")) {
            rdoQuanLy.setSelected(true);
        }else{
            rdoNhanVien.setSelected(true);
        }
    }
    void loadTableUser(ArrayList<User> ls){
        DefaultTableModel model = (DefaultTableModel) tblTaiKhoan.getModel();
        model.setRowCount(0);
        for (User u : ls) {
            model.addRow(new Object[]{
                u.getUserID(), u.getUserName(), u.getPassCode(), u.getRole()
            });
        }
    }
    void loadTableTaiKhoan(ArrayList<UserViewModel> ls){
        DefaultTableModel model = (DefaultTableModel) tblTaiKhoan.getModel();
        model.setRowCount(0);
        for (UserViewModel u : ls) {
            model.addRow(new Object[]{
                u.getId(), u.getTenDN(), u.getMatKhau(), u.getVaiTro(), u.getTenNV()
                    , u.getGioiTinh(), u.getSoDT(), u.getDiaChi()
            });
        }
    }
   
    //GETMODEL
    public User getModelUser(){
        Integer id = Integer.valueOf(txtMaNguoiDung.getText());
        String userName = txtTenDangNhap.getText();
        String matK = txtMatKhau.getText();
        String vaiT = rdoQuanLy.isSelected() ? "Quản Lý" : "Nhân Viên";
        
        User u = new User(id, userName, matK, vaiT);
        return u;
        
    }
    
    //VALIDATE
    public Boolean validateTaiKhoan(){
        StringBuilder stb = new  StringBuilder();
        Validate v = new Validate();
        
        v.isEmpty(txtMaNguoiDung, stb, "Mã người dùng bị trống!");
        v.isEmpty(txtTenDangNhap, stb, "Tên đăng nhập bị trống!");
        v.isEmpty(txtMatKhau, stb, "Mật khẩu bị trống!");
        if (stb.length()>0) {
            JOptionPane.showMessageDialog(this, stb);
            return false;
        }else{
            return true;
        }
    }
    
    //CLEAR FORM
    void clearFormTaiKhoan(){
        txtMaNguoiDung.setText("");
        txtTenDangNhap.setText("");
        txtMatKhau.setText("");
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
        PaneQL_TK = new javax.swing.JTabbedPane();
        ThuocTinh = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jLabel23 = new javax.swing.JLabel();
        btnThem = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnMoi = new javax.swing.JButton();
        jLabel24 = new javax.swing.JLabel();
        txtTenDangNhap = new javax.swing.JTextField();
        btnAn = new javax.swing.JButton();
        rdoQuanLy = new javax.swing.JRadioButton();
        rdoNhanVien = new javax.swing.JRadioButton();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        txtMaNguoiDung = new javax.swing.JTextField();
        txtMatKhau = new javax.swing.JPasswordField();
        jCheckBox2 = new javax.swing.JCheckBox();
        jPanel13 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblTaiKhoan = new javax.swing.JTable();
        jLabel21 = new javax.swing.JLabel();
        txtSearch = new javax.swing.JTextField();
        btnSearchByName = new javax.swing.JButton();
        ChiTietSanPham = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        txtPassCode = new javax.swing.JPasswordField();
        jButton1 = new javax.swing.JButton();
        lblTenDN = new javax.swing.JLabel();
        lblHoTen = new javax.swing.JLabel();
        lblGioiTinh = new javax.swing.JLabel();
        lblVaiTro = new javax.swing.JLabel();
        lblSDT = new javax.swing.JLabel();
        lblDiaChi = new javax.swing.JLabel();
        chkShowHide = new javax.swing.JCheckBox();

        setMaximumSize(new java.awt.Dimension(990, 610));
        setMinimumSize(new java.awt.Dimension(990, 610));

        PaneQL_TK.setAutoscrolls(true);
        PaneQL_TK.setPreferredSize(new java.awt.Dimension(990, 610));

        jPanel12.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP)), "Thông tin Tài Khoản", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Segoe UI", 3, 14))); // NOI18N

        jLabel23.setText("Mật Khẩu:");

        btnThem.setText("THÊM");
        btnThem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnThemMouseClicked(evt);
            }
        });

        btnSua.setText("SỬA");
        btnSua.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSuaMouseClicked(evt);
            }
        });

        btnMoi.setText("MỚI");
        btnMoi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnMoiMouseClicked(evt);
            }
        });

        jLabel24.setText("Mã Người Dùng:");

        btnAn.setText("ẨN");
        btnAn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnAnMouseClicked(evt);
            }
        });
        btnAn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnActionPerformed(evt);
            }
        });

        buttonGroup1.add(rdoQuanLy);
        rdoQuanLy.setText("Quản Lý");

        buttonGroup1.add(rdoNhanVien);
        rdoNhanVien.setText("Nhân Viên");

        jLabel25.setText("Tên Đăng Nhập:");

        jLabel26.setText("Vai Trò:");

        txtMaNguoiDung.setEnabled(false);

        txtMatKhau.setText("jPasswordField2");

        jCheckBox2.setText("Hiện mật khẩu");

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnThem)
                    .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addComponent(btnSua)
                        .addGap(50, 50, 50)
                        .addComponent(btnMoi)
                        .addGap(43, 43, 43)
                        .addComponent(btnAn))
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtTenDangNhap, javax.swing.GroupLayout.DEFAULT_SIZE, 262, Short.MAX_VALUE)
                            .addComponent(txtMaNguoiDung))
                        .addGap(76, 76, 76)
                        .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(rdoQuanLy)
                        .addGap(65, 65, 65)
                        .addComponent(rdoNhanVien))
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addComponent(txtMatKhau, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jCheckBox2)))
                .addContainerGap(115, Short.MAX_VALUE))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel24)
                        .addComponent(txtMaNguoiDung, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(rdoQuanLy)
                        .addComponent(rdoNhanVien)
                        .addComponent(jLabel26)))
                .addGap(18, 18, 18)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel25)
                    .addComponent(txtTenDangNhap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(txtMatKhau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCheckBox2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThem)
                    .addComponent(btnSua)
                    .addComponent(btnMoi)
                    .addComponent(btnAn))
                .addGap(31, 31, 31))
        );

        jPanel13.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP)), "Danh Sách Tài Khoản", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Segoe UI", 3, 14))); // NOI18N

        tblTaiKhoan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã Người Dùng", "Tên Tài Khoản", "Mật Khẩu", "Vai Trò", "Tên Nhân Viên", "Giới Tính", "Số Điện Thoại", "Địa Chỉ"
            }
        ));
        tblTaiKhoan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblTaiKhoanMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblTaiKhoan);

        jLabel21.setText("Tìm kiếm theo tên:");

        btnSearchByName.setText("SEARCH");
        btnSearchByName.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSearchByNameMouseClicked(evt);
            }
        });
        btnSearchByName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchByNameActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel21)
                        .addGap(18, 18, 18)
                        .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32)
                        .addComponent(btnSearchByName, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel13Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 903, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21)
                    .addComponent(btnSearchByName))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE)
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
                .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        PaneQL_TK.addTab("Quản Lý Tài Khoản", ThuocTinh);

        ChiTietSanPham.setPreferredSize(new java.awt.Dimension(990, 610));

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP)), "Thông tin Chi Tiết", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Segoe UI", 3, 14))); // NOI18N

        jLabel7.setText("Họ Tên:");

        jLabel8.setText("Vai Trò:");

        jLabel10.setText("Tên Đăng Nhập:");

        jLabel11.setText("Giới Tính:");

        jLabel12.setText("Số Điện Thoại:");

        jLabel14.setText("Địa Chỉ:");

        jLabel18.setText("Mật Khẩu:");

        txtPassCode.setText("jPasswordField1");

        jButton1.setText("ĐỔI MẬT KHẨU");

        lblTenDN.setText("----------");

        lblHoTen.setText("----------");

        lblGioiTinh.setText("----------");

        lblVaiTro.setText("----------");

        lblSDT.setText("----------");

        lblDiaChi.setText("----------");

        chkShowHide.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        chkShowHide.setText("Hiển thị mật khẩu");
        chkShowHide.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                chkShowHideMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(64, 64, 64)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtPassCode, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblTenDN, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblHoTen)
                            .addComponent(lblGioiTinh)
                            .addComponent(lblVaiTro)
                            .addComponent(lblSDT)
                            .addComponent(lblDiaChi)
                            .addComponent(chkShowHide, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(344, 344, 344)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(459, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(lblTenDN))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(txtPassCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblHoTen)
                    .addComponent(jLabel7))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(lblGioiTinh))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(lblVaiTro))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(lblSDT))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(lblDiaChi))
                .addGap(18, 18, 18)
                .addComponent(chkShowHide)
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addContainerGap(34, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout ChiTietSanPhamLayout = new javax.swing.GroupLayout(ChiTietSanPham);
        ChiTietSanPham.setLayout(ChiTietSanPhamLayout);
        ChiTietSanPhamLayout.setHorizontalGroup(
            ChiTietSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ChiTietSanPhamLayout.createSequentialGroup()
                .addContainerGap(15, Short.MAX_VALUE)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        ChiTietSanPhamLayout.setVerticalGroup(
            ChiTietSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ChiTietSanPhamLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(113, Short.MAX_VALUE))
        );

        PaneQL_TK.addTab("My Info", ChiTietSanPham);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(PaneQL_TK, javax.swing.GroupLayout.PREFERRED_SIZE, 954, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(PaneQL_TK, javax.swing.GroupLayout.PREFERRED_SIZE, 549, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnThemMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnThemMouseClicked
        //ADD
        int result = JOptionPane.showConfirmDialog(this, "Bạn muốn tạo tài khoản?", "POLYPOLO xác nhận", JOptionPane.YES_NO_OPTION);
//        if (uService.checkId(id)) {
//            JOptionPane.showMessageDialog(this, "Mã người dùng đã tồn tại, không thể thêm!");
//            loadTableTaiKhoan(uService.getList());
//            clearFormTaiKhoan();
//        }else{
            if (validateTaiKhoan() && result == JOptionPane.YES_OPTION) {
                JOptionPane.showMessageDialog(this, uService.addAccount(getModelUser()));
                loadTableTaiKhoan(uService.getList());
                clearFormTaiKhoan();
            }else{
                 JOptionPane.showMessageDialog(this, "Đã hủy thao tác thêm tài khoản!", "POLYPOLO thông báo", 0);
            }
    }//GEN-LAST:event_btnThemMouseClicked

    private void btnSuaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSuaMouseClicked
        //UPDATE
        int result = JOptionPane.showConfirmDialog(this, "Bạn muốn sửa tài khoản này?", "POLYPOLO xác nhận", JOptionPane.YES_NO_OPTION);
        if (result == JOptionPane.YES_OPTION) {
            String resultNek = uService.updateAccount(getModelUser());
            JOptionPane.showMessageDialog(this, resultNek);
            loadTableTaiKhoan(uService.getList());
        } else {
            JOptionPane.showMessageDialog(this, "Đã hủy thao tác sửa tài khoản!", "POLYPOLO thông báo", 0);
        }
    }//GEN-LAST:event_btnSuaMouseClicked

    private void btnMoiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMoiMouseClicked
        //NEW
        txtMaNguoiDung.setText("");
        txtTenDangNhap.setText("");
        txtMatKhau.setText("");
        rdoQuanLy.isSelected();
        loadTableTaiKhoan(uService.getList());
    }//GEN-LAST:event_btnMoiMouseClicked

    private void btnAnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAnMouseClicked
        //HIDE
        int result = JOptionPane.showConfirmDialog(this, "Bạn muốn ẩn tài khoản này?", "POLYPOLO xác nhận", JOptionPane.YES_NO_OPTION);        
        if (result == JOptionPane.YES_OPTION) {
            uService.hideAccount(getModelUser());
            JOptionPane.showMessageDialog(this, "Ẩn tài khoản thành công!", "POLYPOLO thông báo", 0);
            loadTableTaiKhoan(uService.getList());
            clearFormTaiKhoan();
        } else {
            JOptionPane.showMessageDialog(this, "Đã hủy thao tác ẩn tài khoản!", "POLYPOLO thông báo", 0);
        }
    }//GEN-LAST:event_btnAnMouseClicked

    private void btnAnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAnActionPerformed

    private void tblTaiKhoanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblTaiKhoanMouseClicked
        // CLICK
        int pos = tblTaiKhoan.getSelectedRow();
        loadDataToFormTaiKhoan(pos);
    }//GEN-LAST:event_tblTaiKhoanMouseClicked

    private void btnSearchByNameMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSearchByNameMouseClicked
        // SEARCH BY NAME
        String name = txtSearch.getText();
        ArrayList<UserViewModel> ls = uService.getListBySearch(name);
        loadTableTaiKhoan(ls);
        txtSearch.setText("");
    }//GEN-LAST:event_btnSearchByNameMouseClicked

    private void btnSearchByNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchByNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSearchByNameActionPerformed

    private void chkShowHideMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_chkShowHideMouseClicked
        // SHOW HIDE
        if (chkShowHide.isSelected()) {
            txtPassCode.setEchoChar((char)0);
        }else{
            txtPassCode.setEchoChar('*');
        }
    }//GEN-LAST:event_chkShowHideMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel ChiTietSanPham;
    private javax.swing.JTabbedPane PaneQL_TK;
    private javax.swing.JPanel ThuocTinh;
    private javax.swing.JButton btnAn;
    private javax.swing.JButton btnMoi;
    private javax.swing.JButton btnSearchByName;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JCheckBox chkShowHide;
    private javax.swing.JButton jButton1;
    private javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lblDiaChi;
    private javax.swing.JLabel lblGioiTinh;
    private javax.swing.JLabel lblHoTen;
    private javax.swing.JLabel lblSDT;
    private javax.swing.JLabel lblTenDN;
    private javax.swing.JLabel lblVaiTro;
    private javax.swing.JRadioButton rdoNhanVien;
    private javax.swing.JRadioButton rdoQuanLy;
    private javax.swing.JTable tblTaiKhoan;
    private javax.swing.JTextField txtMaNguoiDung;
    private javax.swing.JPasswordField txtMatKhau;
    private javax.swing.JPasswordField txtPassCode;
    private javax.swing.JTextField txtSearch;
    private javax.swing.JTextField txtTenDangNhap;
    // End of variables declaration//GEN-END:variables
}