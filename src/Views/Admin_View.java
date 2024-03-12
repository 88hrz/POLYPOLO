/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Views;

import Models.User;
import com.formdev.flatlaf.themes.FlatMacLightLaf;
import java.awt.Color;
import javax.swing.JOptionPane;

/**
 *
 * @author X1
 */
public class Admin_View extends javax.swing.JFrame {
    Color DefaultColor, ClickedColor;
    /**
     * Creates new form Main
     * @param u
     */
    // ...

    public Admin_View(User u){
        initComponents();
        setLocationRelativeTo(null);
        DefaultColor = new Color(13, 36, 51);
        ClickedColor = new Color(240, 151, 57);
        dashBoard();
        
        lbl_UserID.setText(u.getUserName());
//        lbl_UserRole.setText(u.getRole());
       
        menuName.setText("| Trang Chủ");
        
//        QuanLyBanHang banHang = new QuanLyBanHang();
//        jGUIForms.removeAll();
//        jGUIForms.add(banHang).setVisible(true);
//
//        QuanLySanPham sanPham = new QuanLySanPham();
//        jGUIForms.removeAll();
//        jGUIForms.add(sanPham).setVisible(true);

//        QuanLyTaiKhoan taiKhoan = new QuanLyTaiKhoan();
//        jGUIForms.removeAll();
//        jGUIForms.add(taiKhoan).setVisible(true);

        TrangChu trangChu = new TrangChu();
        jGUIForms.removeAll();
        jGUIForms.add(trangChu).setVisible(true);
    }

    
    void dashBoard(){
        menu1.setBackground(DefaultColor);
        menu2.setBackground(DefaultColor);
        menu3.setBackground(DefaultColor);
        menu4.setBackground(DefaultColor);
        menu5.setBackground(DefaultColor);
        menu6.setBackground(DefaultColor);
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bg = new javax.swing.JPanel();
        ngang = new javax.swing.JPanel();
        menuName = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lbl_UserID = new javax.swing.JLabel();
        txtUser_ID = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        menu1 = new javax.swing.JPanel();
        lblTrangChu = new javax.swing.JLabel();
        menu2 = new javax.swing.JPanel();
        lblBanHang = new javax.swing.JLabel();
        menu3 = new javax.swing.JPanel();
        lblSanPham = new javax.swing.JLabel();
        menu4 = new javax.swing.JPanel();
        lblNhanSu = new javax.swing.JLabel();
        menu5 = new javax.swing.JPanel();
        lblKhachHang = new javax.swing.JLabel();
        menu6 = new javax.swing.JPanel();
        lblTaiKhoan = new javax.swing.JLabel();
        menu7 = new javax.swing.JPanel();
        lblExit = new javax.swing.JLabel();
        jGUIForms = new javax.swing.JDesktopPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(1100, 690));
        setMinimumSize(new java.awt.Dimension(1100, 690));

        bg.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        ngang.setBackground(new java.awt.Color(204, 102, 0));
        ngang.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        menuName.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N
        menuName.setForeground(new java.awt.Color(255, 255, 255));
        ngang.add(menuName, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 50, -1, -1));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 3, 20)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("PolyPolo");
        ngang.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("User ID:");
        ngang.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 20, -1, -1));

        lbl_UserID.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbl_UserID.setText("i");
        ngang.add(lbl_UserID, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 20, 60, 20));

        txtUser_ID.setBackground(new java.awt.Color(204, 204, 204));
        txtUser_ID.setEnabled(false);
        ngang.add(txtUser_ID, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 20, 110, 20));

        bg.add(ngang, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1100, 80));

        jPanel2.setBackground(new java.awt.Color(13, 36, 51));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        menu1.setBackground(new java.awt.Color(13, 36, 51));
        menu1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menu1MouseClicked(evt);
            }
        });

        lblTrangChu.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblTrangChu.setForeground(new java.awt.Color(255, 255, 255));
        lblTrangChu.setText("Trang Chủ");

        javax.swing.GroupLayout menu1Layout = new javax.swing.GroupLayout(menu1);
        menu1.setLayout(menu1Layout);
        menu1Layout.setHorizontalGroup(
            menu1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menu1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(lblTrangChu)
                .addContainerGap(23, Short.MAX_VALUE))
        );
        menu1Layout.setVerticalGroup(
            menu1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menu1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTrangChu)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.add(menu1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 70, 110, 30));

        menu2.setBackground(new java.awt.Color(13, 36, 51));
        menu2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menu2MouseClicked(evt);
            }
        });

        lblBanHang.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblBanHang.setForeground(new java.awt.Color(255, 255, 255));
        lblBanHang.setText("Bán Hàng");

        javax.swing.GroupLayout menu2Layout = new javax.swing.GroupLayout(menu2);
        menu2.setLayout(menu2Layout);
        menu2Layout.setHorizontalGroup(
            menu2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menu2Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(lblBanHang)
                .addContainerGap(26, Short.MAX_VALUE))
        );
        menu2Layout.setVerticalGroup(
            menu2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menu2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblBanHang)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.add(menu2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 120, 110, -1));

        menu3.setBackground(new java.awt.Color(13, 36, 51));
        menu3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menu3MouseClicked(evt);
            }
        });

        lblSanPham.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblSanPham.setForeground(new java.awt.Color(255, 255, 255));
        lblSanPham.setText("Sản Phẩm");

        javax.swing.GroupLayout menu3Layout = new javax.swing.GroupLayout(menu3);
        menu3.setLayout(menu3Layout);
        menu3Layout.setHorizontalGroup(
            menu3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menu3Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(lblSanPham)
                .addContainerGap(25, Short.MAX_VALUE))
        );
        menu3Layout.setVerticalGroup(
            menu3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menu3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblSanPham)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.add(menu3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 170, 110, -1));

        menu4.setBackground(new java.awt.Color(13, 36, 51));
        menu4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menu4MouseClicked(evt);
            }
        });

        lblNhanSu.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblNhanSu.setForeground(new java.awt.Color(255, 255, 255));
        lblNhanSu.setText("Nhân Sự");

        javax.swing.GroupLayout menu4Layout = new javax.swing.GroupLayout(menu4);
        menu4.setLayout(menu4Layout);
        menu4Layout.setHorizontalGroup(
            menu4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menu4Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(lblNhanSu)
                .addContainerGap(33, Short.MAX_VALUE))
        );
        menu4Layout.setVerticalGroup(
            menu4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menu4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblNhanSu)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.add(menu4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 220, 110, -1));

        menu5.setBackground(new java.awt.Color(13, 36, 51));
        menu5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menu5MouseClicked(evt);
            }
        });

        lblKhachHang.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblKhachHang.setForeground(new java.awt.Color(255, 255, 255));
        lblKhachHang.setText("Khách Hàng");

        javax.swing.GroupLayout menu5Layout = new javax.swing.GroupLayout(menu5);
        menu5.setLayout(menu5Layout);
        menu5Layout.setHorizontalGroup(
            menu5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menu5Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(lblKhachHang)
                .addContainerGap(12, Short.MAX_VALUE))
        );
        menu5Layout.setVerticalGroup(
            menu5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menu5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblKhachHang)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.add(menu5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 270, 110, -1));

        menu6.setBackground(new java.awt.Color(13, 36, 51));
        menu6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menu6MouseClicked(evt);
            }
        });

        lblTaiKhoan.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblTaiKhoan.setForeground(new java.awt.Color(255, 255, 255));
        lblTaiKhoan.setText("Tài Khoản");

        javax.swing.GroupLayout menu6Layout = new javax.swing.GroupLayout(menu6);
        menu6.setLayout(menu6Layout);
        menu6Layout.setHorizontalGroup(
            menu6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menu6Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(lblTaiKhoan)
                .addContainerGap(25, Short.MAX_VALUE))
        );
        menu6Layout.setVerticalGroup(
            menu6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menu6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTaiKhoan)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.add(menu6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 320, 110, -1));

        menu7.setBackground(new java.awt.Color(13, 36, 51));
        menu7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menu7MouseClicked(evt);
            }
        });

        lblExit.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblExit.setForeground(new java.awt.Color(255, 255, 255));
        lblExit.setText("Exit");

        javax.swing.GroupLayout menu7Layout = new javax.swing.GroupLayout(menu7);
        menu7.setLayout(menu7Layout);
        menu7Layout.setHorizontalGroup(
            menu7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menu7Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(lblExit)
                .addContainerGap(63, Short.MAX_VALUE))
        );
        menu7Layout.setVerticalGroup(
            menu7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menu7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblExit)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.add(menu7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 370, 110, -1));

        bg.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 70, 110, 620));

        jGUIForms.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jGUIFormsLayout = new javax.swing.GroupLayout(jGUIForms);
        jGUIForms.setLayout(jGUIFormsLayout);
        jGUIFormsLayout.setHorizontalGroup(
            jGUIFormsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 990, Short.MAX_VALUE)
        );
        jGUIFormsLayout.setVerticalGroup(
            jGUIFormsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 610, Short.MAX_VALUE)
        );

        bg.add(jGUIForms, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 80, 990, 610));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void menu1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menu1MouseClicked
        // TODO add your handling code here:
        menu1.setBackground(ClickedColor);
        menu2.setBackground(DefaultColor);
        menu3.setBackground(DefaultColor);
        menu4.setBackground(DefaultColor);
        menu5.setBackground(DefaultColor);
        menu6.setBackground(DefaultColor);
        
        menuName.setText("| Trang Chủ");
        TrangChu trangChu = new TrangChu();
        jGUIForms.removeAll();
        jGUIForms.add(trangChu).setVisible(true);
        
    }//GEN-LAST:event_menu1MouseClicked

    private void menu2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menu2MouseClicked
        // TODO add your handling code here:
        menu1.setBackground(DefaultColor);
        menu2.setBackground(ClickedColor);
        menu3.setBackground(DefaultColor);
        menu4.setBackground(DefaultColor);
        menu5.setBackground(DefaultColor);
        menu6.setBackground(DefaultColor);
        
        menuName.setText("| Bán Hàng");
        
        QuanLyBanHang banHang = new QuanLyBanHang();
        jGUIForms.removeAll();
        jGUIForms.add(banHang).setVisible(true);
    }//GEN-LAST:event_menu2MouseClicked

    private void menu3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menu3MouseClicked
        // TODO add your handling code here:
        menu1.setBackground(DefaultColor);
        menu2.setBackground(DefaultColor);
        menu3.setBackground(ClickedColor);
        menu4.setBackground(DefaultColor);
        menu5.setBackground(DefaultColor);
        menu6.setBackground(DefaultColor);
        
        menuName.setText("| Sản Phẩm");
        
        QuanLySanPham sanPham = new QuanLySanPham();
        jGUIForms.removeAll();
        jGUIForms.add(sanPham).setVisible(true);
    }//GEN-LAST:event_menu3MouseClicked

    private void menu4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menu4MouseClicked
        // TODO add your handling code here:
        menu1.setBackground(DefaultColor);
        menu2.setBackground(DefaultColor);
        menu3.setBackground(DefaultColor);
        menu4.setBackground(ClickedColor);
        menu5.setBackground(DefaultColor);
        menu6.setBackground(DefaultColor);
        
        menuName.setText("| Nhân Sự");
        
        QuanLyNhanSu ns = new QuanLyNhanSu();
        jGUIForms.removeAll();
        jGUIForms.add(ns).setVisible(true);
    }//GEN-LAST:event_menu4MouseClicked

    private void menu5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menu5MouseClicked
        // TODO add your handling code here:
        menu1.setBackground(DefaultColor);
        menu2.setBackground(DefaultColor);
        menu3.setBackground(DefaultColor);
        menu4.setBackground(DefaultColor);
        menu5.setBackground(ClickedColor);
        menu6.setBackground(DefaultColor);
        
        menuName.setText("| Khách Hàng");

        QuanLyKhachHang khachHang = new QuanLyKhachHang();
        jGUIForms.removeAll();
        jGUIForms.add(khachHang).setVisible(true);
    }//GEN-LAST:event_menu5MouseClicked

    private void menu6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menu6MouseClicked
        // TODO add your handling code here:
        menu1.setBackground(DefaultColor);
        menu2.setBackground(DefaultColor);
        menu3.setBackground(DefaultColor);
        menu4.setBackground(DefaultColor);
        menu5.setBackground(DefaultColor);
        menu6.setBackground(ClickedColor);
        
        menuName.setText("| Tài Khoản");

        QuanLyTaiKhoan taiKhoan = new QuanLyTaiKhoan();
        jGUIForms.removeAll();
        jGUIForms.add(taiKhoan).setVisible(true);
    }//GEN-LAST:event_menu6MouseClicked

    private void menu7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menu7MouseClicked
        // TODO add your handling code here:
        menu1.setBackground(DefaultColor);
        menu2.setBackground(DefaultColor);
        menu3.setBackground(DefaultColor);
        menu4.setBackground(DefaultColor);
        menu5.setBackground(DefaultColor);
        menu6.setBackground(DefaultColor);
        
        JOptionPane.showMessageDialog(this, "Thanks for using our programm!!~");
        System.exit(0);
    }//GEN-LAST:event_menu7MouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        FlatMacLightLaf.setup();
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
//                new Admin_View().setVisible(true);
                User user = new User();
                user.setUserName("vk jk");
                user.setRole("vjp");

                new Admin_View(user).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel bg;
    private javax.swing.JDesktopPane jGUIForms;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lblBanHang;
    private javax.swing.JLabel lblExit;
    private javax.swing.JLabel lblKhachHang;
    private javax.swing.JLabel lblNhanSu;
    private javax.swing.JLabel lblSanPham;
    private javax.swing.JLabel lblTaiKhoan;
    private javax.swing.JLabel lblTrangChu;
    private javax.swing.JLabel lbl_UserID;
    private javax.swing.JPanel menu1;
    private javax.swing.JPanel menu2;
    private javax.swing.JPanel menu3;
    private javax.swing.JPanel menu4;
    private javax.swing.JPanel menu5;
    private javax.swing.JPanel menu6;
    private javax.swing.JPanel menu7;
    private javax.swing.JLabel menuName;
    private javax.swing.JPanel ngang;
    private javax.swing.JTextField txtUser_ID;
    // End of variables declaration//GEN-END:variables
}
