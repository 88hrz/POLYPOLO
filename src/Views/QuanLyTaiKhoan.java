 /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package Views;

import Models.TaiKhoan;
import Models.User;
import Repositories.H_TaiKhoanRepository;
import Services.NguoiDungService;
import Validator.Validate;
import ViewModels.UserViewModel;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Date;
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
        loadTableTaiKhoan(uService.getList());
        getData();
    }
    
    //<editor-fold defaultstate="collapsed" desc=" LOAD MY INFO">
    //LOAD MY INFO
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
    //</editor-fold>
    
    //LOAD
    void loadDataToFormTaiKhoan(){
        int pos = tblTaiKhoan.getSelectedRow();
        Integer id = (Integer) tblTaiKhoan.getValueAt(pos, 0);
        
        UserViewModel u = uService.getListById(id);
        
        txtMaNguoiDung.setText(u.getId().toString());
        txtHoTen.setText(uService.getListById(id).getTenNV());
        txtSDT.setText(u.getVaiTro());
        System.out.println(u.getSoDT());
        txtDiaChi.setText(u.getDiaC());
        if (u.getGioiT().equalsIgnoreCase("nam")) {
            rdoNam.setSelected(true);
        } else {
            rdoNu.setSelected(true);
        }
        dcsNgaySinh.setDate(u.getNgayS());
        if (u.getVaiTro().equalsIgnoreCase("admin")) {
            rdoVaiTroQuanLy.setSelected(true);
        } else {
            rdoVaiTroNhanVien.setSelected(true);
        }
        txtTenDangNhap.setText(u.getTenDN());
        txtMatKhau.setText(u.getMatKhau());
    }
    void loadTableTaiKhoan(ArrayList<UserViewModel> ls){
        DefaultTableModel model = (DefaultTableModel) tblTaiKhoan.getModel();
        model.setRowCount(0);
        for (UserViewModel u : ls) {
            model.addRow(new Object[]{
                u.getId(), u.getTenDN(), u.getMatKhau(), u.getTenNV()
                    , u.getSoDT(), u.getVaiTro()
            });
        }
    }
   
    //GETMODEL
    public TaiKhoan getModelTaiKhoan(){
        Integer id = Integer.valueOf(uService.getAll().get(uService.getAll().size()-1).getMaNguoiDung());
        
        String hoTen = txtHoTen.getText().trim();
        String soDT = txtSDT.getText().trim();
        String diaChi = txtDiaChi.getText().trim();
        String gioiT = rdoNam.isSelected() ? "Nam" : "Nữ";
        String vaiT = rdoVaiTroQuanLy.isSelected() ? "Admin" : "Staff";
        Date ngayS = dcsNgaySinh.getDate();
        
        String userName = txtTenDangNhap.getText();
        String matK = txtMatKhau.getText();
        
        TaiKhoan tk = new TaiKhoan(id, userName, matK, hoTen, soDT, vaiT, diaChi, gioiT, ngayS);
        return tk;
    }
    
    //VALIDATE
    public Boolean validateTaiKhoan(){
        StringBuilder stb = new  StringBuilder();
        Validate v = new Validate();
        
        v.isEmpty(txtHoTen, stb, "Họ tên đang bị trống!");
        v.isEmpty(txtSDT, stb, "SĐT đang bị trống!");
        v.isPhoneNumber(txtSDT, "Vui lòng nhập đúng định dạng SĐT!", stb);
        v.isEmpty(txtDiaChi, stb, "Địa chỉ đang bị trống!");
        
        v.isDateSelected(dcsNgaySinh, stb, "Vui lòng chọn ngày sinh!");
        v.isRadioButtonSelected(buttonGroup1, stb, "Vui lòng chọn vai trò của bạn!");
        v.isRadioButtonSelected(buttonGroup2, stb, "Vui lòng chọn giới tính!");
        
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
        txtHoTen.setText("");
        txtSDT.setText("");
        txtDiaChi.setText("");
        rdoNam.setSelected(true);
        rdoVaiTroQuanLy.setSelected(true);
        dcsNgaySinh.setDate(null);
        
        txtTenDangNhap.setText("");
        txtMatKhau.setText("");
    }
    void clearValidator(){
        txtMaNguoiDung.setBackground(Color.white);
        txtHoTen.setBackground(Color.white);
        txtSDT.setBackground(Color.white);
        txtDiaChi.setBackground(Color.white);
        rdoNam.setBackground(Color.white);
        rdoVaiTroQuanLy.setBackground(Color.white);
        dcsNgaySinh.setBackground(Color.white);
        
        txtTenDangNhap.setBackground(Color.white);
        txtMatKhau.setBackground(Color.white);
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
        PaneQL_TK = new javax.swing.JTabbedPane();
        ThuocTinh = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        txtTenDangNhap = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        txtMaNguoiDung = new javax.swing.JTextField();
        txtMatKhau = new javax.swing.JPasswordField();
        jLabel1 = new javax.swing.JLabel();
        rdoVaiTroNhanVien = new javax.swing.JRadioButton();
        rdoVaiTroQuanLy = new javax.swing.JRadioButton();
        jLabel26 = new javax.swing.JLabel();
        txtHoTen = new javax.swing.JTextField();
        chkShowPass = new javax.swing.JCheckBox();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        rdoNam = new javax.swing.JRadioButton();
        rdoNu = new javax.swing.JRadioButton();
        jLabel4 = new javax.swing.JLabel();
        txtSDT = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        txtDiaChi = new javax.swing.JTextField();
        dcsNgaySinh = new com.toedter.calendar.JDateChooser();
        jPanel1 = new javax.swing.JPanel();
        btnThem = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnClear = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblTaiKhoan = new javax.swing.JTable();
        jLabel21 = new javax.swing.JLabel();
        btnSearchByName = new javax.swing.JButton();
        txtSearchTK = new javax.swing.JTextField();
        rdoFilter_QL = new javax.swing.JRadioButton();
        rdoFilter_NV = new javax.swing.JRadioButton();
        jLabel22 = new javax.swing.JLabel();
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

        jLabel24.setText("Mã Người Dùng:");

        jLabel25.setText("Họ Tên:");

        txtMaNguoiDung.setEnabled(false);

        txtMatKhau.setText("jPasswordField2");

        jLabel1.setText("Tên Đăng Nhập:");

        buttonGroup1.add(rdoVaiTroNhanVien);
        rdoVaiTroNhanVien.setText("Nhân Viên");

        buttonGroup1.add(rdoVaiTroQuanLy);
        rdoVaiTroQuanLy.setText("Quản Lý");

        jLabel26.setText("Vai Trò:");

        chkShowPass.setText("Hiện mật khẩu");
        chkShowPass.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                chkShowPassMouseClicked(evt);
            }
        });

        jLabel2.setText("Số Điện Thoại:");

        jLabel3.setText("Giới Tính:");

        buttonGroup2.add(rdoNam);
        rdoNam.setText("Nam");

        buttonGroup2.add(rdoNu);
        rdoNu.setText("Nữ");

        jLabel4.setText("Địa Chỉ:");

        jLabel27.setText("Ngày Sinh:");

        jPanel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));

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

        btnClear.setText("MỚI");
        btnClear.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnClearMouseClicked(evt);
            }
        });

        btnDelete.setText("XÓA");
        btnDelete.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnDeleteMouseClicked(evt);
            }
        });
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(15, Short.MAX_VALUE)
                .addComponent(btnThem)
                .addGap(18, 18, 18)
                .addComponent(btnSua)
                .addGap(18, 18, 18)
                .addComponent(btnClear)
                .addGap(18, 18, 18)
                .addComponent(btnDelete)
                .addGap(23, 23, 23))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(15, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThem)
                    .addComponent(btnSua)
                    .addComponent(btnClear)
                    .addComponent(btnDelete))
                .addGap(16, 16, 16))
        );

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel12Layout.createSequentialGroup()
                                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel1))
                                        .addGroup(jPanel12Layout.createSequentialGroup()
                                            .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(13, 13, 13)))
                                    .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3))
                                .addGap(28, 28, 28)
                                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel12Layout.createSequentialGroup()
                                        .addComponent(txtMatKhau, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(chkShowPass))
                                    .addComponent(txtTenDangNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtMaNguoiDung, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel12Layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel12Layout.createSequentialGroup()
                                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel4)
                                            .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(39, 39, 39)
                                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel12Layout.createSequentialGroup()
                                                .addComponent(rdoVaiTroQuanLy)
                                                .addGap(92, 92, 92)
                                                .addComponent(rdoVaiTroNhanVien))
                                            .addComponent(dcsNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(34, 34, 34))))))
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGap(157, 157, 157)
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtHoTen, javax.swing.GroupLayout.DEFAULT_SIZE, 268, Short.MAX_VALUE)
                            .addGroup(jPanel12Layout.createSequentialGroup()
                                .addComponent(rdoNam)
                                .addGap(99, 99, 99)
                                .addComponent(rdoNu))
                            .addComponent(txtSDT)
                            .addComponent(txtDiaChi))))
                .addContainerGap(26, Short.MAX_VALUE))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24)
                    .addComponent(txtMaNguoiDung, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtTenDangNhap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel23)
                        .addComponent(txtMatKhau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(chkShowPass))
                .addGap(18, 18, 18)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel25)
                    .addComponent(txtHoTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(rdoNam)
                    .addComponent(rdoNu))
                .addGap(18, 18, 18)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel27))
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(dcsNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(22, 22, 22)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdoVaiTroQuanLy)
                    .addComponent(rdoVaiTroNhanVien)
                    .addComponent(jLabel26))
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Chi Tiết Tài Khoản", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 3, 18))); // NOI18N

        tblTaiKhoan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Tài Khoản", "Mật Khẩu", "Họ Tên", "SĐT", "Vai Trò"
            }
        ));
        tblTaiKhoan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblTaiKhoanMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblTaiKhoan);

        jLabel21.setText("Tìm kiếm:");

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

        buttonGroup3.add(rdoFilter_QL);
        rdoFilter_QL.setText("Quản Lý");

        buttonGroup3.add(rdoFilter_NV);
        rdoFilter_NV.setText("Nhân Viên");

        jLabel22.setText("Lọc:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel22)
                        .addGap(18, 18, 18)
                        .addComponent(rdoFilter_QL)
                        .addGap(18, 18, 18)
                        .addComponent(rdoFilter_NV))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel21)
                        .addGap(18, 18, 18)
                        .addComponent(txtSearchTK)
                        .addGap(18, 18, 18)
                        .addComponent(btnSearchByName))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 403, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(16, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(txtSearchTK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSearchByName))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rdoFilter_NV)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(rdoFilter_QL)
                        .addComponent(jLabel22)))
                .addGap(16, 16, 16))
        );

        javax.swing.GroupLayout ThuocTinhLayout = new javax.swing.GroupLayout(ThuocTinh);
        ThuocTinh.setLayout(ThuocTinhLayout);
        ThuocTinhLayout.setHorizontalGroup(
            ThuocTinhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ThuocTinhLayout.createSequentialGroup()
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        ThuocTinhLayout.setVerticalGroup(
            ThuocTinhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ThuocTinhLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(ThuocTinhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(24, Short.MAX_VALUE))
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
        String name = txtTenDangNhap.getText().trim();
        if (uService.checkName(name)) {
            JOptionPane.showMessageDialog(this, "Tên người dùng đã tồn tại, không thể thêm!");
            loadTableTaiKhoan(uService.getList());
            clearFormTaiKhoan();
        }else{
            if (validateTaiKhoan() && result == JOptionPane.YES_OPTION) {
                JOptionPane.showMessageDialog(this, uService.addAccount(getModelTaiKhoan()));
                loadTableTaiKhoan(uService.getList());
                clearFormTaiKhoan();
            }else{
                 JOptionPane.showMessageDialog(this, "Đã hủy thao tác thêm tài khoản!", "POLYPOLO thông báo", 0);
            }
        }
    }//GEN-LAST:event_btnThemMouseClicked

    private void btnSuaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSuaMouseClicked
        //UPDATE
        int result = JOptionPane.showConfirmDialog(this, "Bạn muốn sửa tài khoản này?", "POLYPOLO xác nhận", JOptionPane.YES_NO_OPTION);
        if (result == JOptionPane.YES_OPTION) {
            String resultNek = uService.updateAccount(getModelTaiKhoan());
            JOptionPane.showMessageDialog(this, resultNek);
            loadTableTaiKhoan(uService.getList());
        } else {
            JOptionPane.showMessageDialog(this, "Đã hủy thao tác sửa tài khoản!", "POLYPOLO thông báo", 0);
        }
    }//GEN-LAST:event_btnSuaMouseClicked

    private void btnClearMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnClearMouseClicked
        //NEW
        clearFormTaiKhoan();
        clearValidator();
    }//GEN-LAST:event_btnClearMouseClicked

    private void btnDeleteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDeleteMouseClicked
        //HIDE
//        int result = JOptionPane.showConfirmDialog(this, "Bạn muốn ẩn tài khoản này?", "POLYPOLO xác nhận", JOptionPane.YES_NO_OPTION);        
//        if (result == JOptionPane.YES_OPTION) {
//            uService.hideAccount(getModelUser());
//            JOptionPane.showMessageDialog(this, "Ẩn tài khoản thành công!", "POLYPOLO thông báo", 0);
//            loadTableTaiKhoan(uService.getList());
//            clearFormTaiKhoan();
//        } else {
//            JOptionPane.showMessageDialog(this, "Đã hủy thao tác ẩn tài khoản!", "POLYPOLO thông báo", 0);
//        }
    }//GEN-LAST:event_btnDeleteMouseClicked

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void tblTaiKhoanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblTaiKhoanMouseClicked
        // CLICK
        loadDataToFormTaiKhoan();
    }//GEN-LAST:event_tblTaiKhoanMouseClicked

    private void btnSearchByNameMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSearchByNameMouseClicked
        // SEARCH BY NAME
        String name = txtSearchTK.getText();
        ArrayList<UserViewModel> ls = uService.getListBySearch(name);
        loadTableTaiKhoan(ls);
        txtSearchTK.setText("");
    }//GEN-LAST:event_btnSearchByNameMouseClicked

    private void btnSearchByNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchByNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSearchByNameActionPerformed

    private void chkShowHideMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_chkShowHideMouseClicked
        // SHOW PASS
        if (chkShowHide.isSelected()) {
            txtPassCode.setEchoChar((char)0);
        }else{
            txtPassCode.setEchoChar('*');
        }
    }//GEN-LAST:event_chkShowHideMouseClicked

    private void chkShowPassMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_chkShowPassMouseClicked
        // SHOW PASS
        if (chkShowPass.isSelected()) {
            txtMatKhau.setEchoChar((char)0);
        }else{
            txtMatKhau.setEchoChar('*');
        }
    }//GEN-LAST:event_chkShowPassMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel ChiTietSanPham;
    private javax.swing.JTabbedPane PaneQL_TK;
    private javax.swing.JPanel ThuocTinh;
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnSearchByName;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.JCheckBox chkShowHide;
    private javax.swing.JCheckBox chkShowPass;
    private com.toedter.calendar.JDateChooser dcsNgaySinh;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lblDiaChi;
    private javax.swing.JLabel lblGioiTinh;
    private javax.swing.JLabel lblHoTen;
    private javax.swing.JLabel lblSDT;
    private javax.swing.JLabel lblTenDN;
    private javax.swing.JLabel lblVaiTro;
    private javax.swing.JRadioButton rdoFilter_NV;
    private javax.swing.JRadioButton rdoFilter_QL;
    private javax.swing.JRadioButton rdoNam;
    private javax.swing.JRadioButton rdoNu;
    private javax.swing.JRadioButton rdoVaiTroNhanVien;
    private javax.swing.JRadioButton rdoVaiTroQuanLy;
    private javax.swing.JTable tblTaiKhoan;
    private javax.swing.JTextField txtDiaChi;
    private javax.swing.JTextField txtHoTen;
    private javax.swing.JTextField txtMaNguoiDung;
    private javax.swing.JPasswordField txtMatKhau;
    private javax.swing.JPasswordField txtPassCode;
    private javax.swing.JTextField txtSDT;
    private javax.swing.JTextField txtSearchTK;
    private javax.swing.JTextField txtTenDangNhap;
    // End of variables declaration//GEN-END:variables
}
