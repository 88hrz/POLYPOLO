/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Views;

import Models.User;
import Services.UserService;
import Utils.QRScanner;
import com.formdev.flatlaf.themes.FlatMacLightLaf;
import java.awt.Color;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author X1
 */
public class Login extends javax.swing.JFrame {
    UserService uService = new UserService();
    
    public static String dataStatic;

    /**
     * Creates new form Login
     */
    
    public Login() {
        initComponents();
        txtID.requestFocus();
        setLocationRelativeTo(null);
        
    }
    
    //VALIDATOR
    public Boolean validateLogin() {
        if (txtID.getText().trim().equals("")
                && txtPasscode.getText().trim().equals("")) {
            txtID.setBackground(Color.red);
            txtPasscode.setBackground(Color.red);
            JOptionPane.showMessageDialog(this, "ID và mật khẩu trống!!");
            return false;
        }
        txtID.setBackground(Color.white);
        txtPasscode.setBackground(Color.white);

        if (txtID.getText().trim().equals("")) {
            txtID.setBackground(Color.red);
            JOptionPane.showMessageDialog(this, "ID trống!");
            return false;
        }
        txtID.setBackground(Color.white);

        if (txtPasscode.getText().trim().equals("")) {
            txtPasscode.setBackground(Color.red);
            JOptionPane.showMessageDialog(this, "Mật khẩu trống!!");
            return false;
        }
        txtPasscode.setBackground(Color.white);

        return true;

    }
    
    public void clearForm(){
        txtID.setText("");
        txtPasscode.setText("");
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        login = new javax.swing.JLabel();
        txtID = new javax.swing.JTextField();
        txtPasscode = new javax.swing.JPasswordField();
        btnLogin = new javax.swing.JButton();
        chkShowHide = new javax.swing.JCheckBox();
        btnScanQR = new javax.swing.JButton();
        copyright = new javax.swing.JLabel();
        btnGoogleLogin = new javax.swing.JButton();
        txtOR = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        jButton2 = new javax.swing.JButton();
        btnforgotPass = new javax.swing.JButton();
        jSeparator4 = new javax.swing.JSeparator();
        user = new javax.swing.JLabel();
        pass = new javax.swing.JLabel();
        logo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(619, 360));

        jPanel5.setBackground(new java.awt.Color(224, 234, 243));

        login.setBackground(new java.awt.Color(0, 0, 0));
        login.setFont(new java.awt.Font("Montserrat ExtraBold", 1, 28)); // NOI18N
        login.setForeground(new java.awt.Color(51, 51, 51));
        login.setText("LOGIN");

        txtID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIDActionPerformed(evt);
            }
        });

        btnLogin.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        btnLogin.setText(" LOGIN   ");
        btnLogin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnLoginMouseClicked(evt);
            }
        });

        chkShowHide.setFont(new java.awt.Font("Montserrat Medium", 0, 11)); // NOI18N
        chkShowHide.setForeground(new java.awt.Color(51, 51, 51));
        chkShowHide.setText("Show password");
        chkShowHide.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                chkShowHideMouseClicked(evt);
            }
        });

        btnScanQR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/login-qr.png"))); // NOI18N
        btnScanQR.setText("SCAN QR");
        btnScanQR.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnScanQRMouseClicked(evt);
            }
        });

        copyright.setFont(new java.awt.Font("Montserrat Medium", 0, 10)); // NOI18N
        copyright.setForeground(new java.awt.Color(102, 102, 102));
        copyright.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/login-copyright.png"))); // NOI18N
        copyright.setText("2024 | POLYPOLO");

        btnGoogleLogin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/login-gg.png"))); // NOI18N
        btnGoogleLogin.setText("GOOGLE");

        txtOR.setFont(new java.awt.Font("Montserrat Medium", 0, 11)); // NOI18N
        txtOR.setForeground(new java.awt.Color(102, 102, 102));
        txtOR.setText("OR");

        jButton2.setFont(new java.awt.Font("Poppins", 0, 9)); // NOI18N
        jButton2.setForeground(new java.awt.Color(102, 102, 102));
        jButton2.setText("SIGN UP");

        btnforgotPass.setFont(new java.awt.Font("Poppins", 0, 9)); // NOI18N
        btnforgotPass.setForeground(new java.awt.Color(102, 102, 102));
        btnforgotPass.setText("FORGOT PASSWORD");

        user.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/login-user20.png"))); // NOI18N

        pass.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/login-pass24.png"))); // NOI18N

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jSeparator4)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtOR, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btnScanQR))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnGoogleLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jSeparator3)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(btnforgotPass, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(pass)
                                .addGap(23, 23, 23))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(user)
                                .addGap(23, 23, 23)))
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(chkShowHide)
                            .addComponent(txtPasscode)
                            .addComponent(btnLogin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtID, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(33, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addComponent(copyright)
                        .addGap(128, 128, 128))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addComponent(login, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(117, 117, 117))))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(109, 115, Short.MAX_VALUE)
                        .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(login, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34)
                        .addComponent(user)))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtPasscode, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(pass, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(18, 18, 18)
                        .addComponent(chkShowHide)
                        .addGap(18, 18, 18)
                        .addComponent(btnLogin)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(txtOR, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(9, 9, 9)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnScanQR)
                    .addComponent(btnGoogleLogin))
                .addGap(18, 18, 18)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnforgotPass)
                    .addComponent(jButton2))
                .addGap(18, 18, 18)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(copyright)
                .addGap(19, 19, 19))
        );

        logo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/login-logo.png"))); // NOI18N
        logo.setText("jLabel10");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(39, Short.MAX_VALUE)
                .addComponent(logo, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(84, 84, 84)
                .addComponent(logo)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnLoginMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLoginMouseClicked
        // LOGIN
        if (validateLogin()) {
            String userID = txtID.getText();
            String passCode = txtPasscode.getText();
            Boolean check = uService.checkLogin(userID, passCode);

            if (check) {
                User u = new User();
                u.setUserName(txtID.getText().trim());
                u.setRole(uService.getListByUserId(userID).getRole());

                dataStatic = txtID.getText();
                JOptionPane.showMessageDialog(this,"Đăng nhập thành công!");
                
                Admin_View mainView = new Admin_View(u);
                mainView.setVisible(true);
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(this, "ID hoặc mật khẩu sai!");
                clearForm();
            }
        }
    }//GEN-LAST:event_btnLoginMouseClicked

    private void chkShowHideMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_chkShowHideMouseClicked
        // SHOW HIDE
        if (chkShowHide.isSelected()) {
            txtPasscode.setEchoChar((char)0);
        }else{
            txtPasscode.setEchoChar('*');
        }
    }//GEN-LAST:event_chkShowHideMouseClicked

    private void txtIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIDActionPerformed

    private void btnScanQRMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnScanQRMouseClicked
        // SCAN QR
        JFileChooser fileChooser = new JFileChooser();
        
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            try {
                File selectedFile = fileChooser.getSelectedFile();
                String imageFile = selectedFile.getAbsolutePath();
                String qrContent = QRScanner.scanQR(imageFile);

                if (qrContent != null) {
                    String[] loginInfo = qrContent.split(":");
                    String userID = loginInfo[0];
                    String password = loginInfo[1];

                    Boolean check = uService.checkLogin(userID, password);
                    if (check) {
                        User u = new User();
                        u.setUserName(userID.trim());
                        u.setRole(uService.getListByUserId(userID).getRole());

                        dataStatic = userID;
                        JOptionPane.showMessageDialog(this, "Đăng nhập thành công!","POLYPOLO thông báo", 0);
                        Admin_View mainView = new Admin_View(u);
                        mainView.setVisible(true);
                        this.dispose();
                    } else {
                        JOptionPane.showMessageDialog(this, "ID hoặc mật khẩu sai!","POLYPOLO thông báo", ERROR);
                        clearForm();
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Ảnh không đúng định dạng!","POLYPOLO thông báo", ERROR);
                }
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Đã xảy ra lỗi khi xử lý mã QR!","POLYPOLO thông báo", ERROR);
            }
        }
    }//GEN-LAST:event_btnScanQRMouseClicked

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
                new Login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGoogleLogin;
    private javax.swing.JButton btnLogin;
    private javax.swing.JButton btnScanQR;
    private javax.swing.JButton btnforgotPass;
    private javax.swing.JCheckBox chkShowHide;
    private javax.swing.JLabel copyright;
    private javax.swing.JButton jButton2;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JLabel login;
    private javax.swing.JLabel logo;
    private javax.swing.JLabel pass;
    private javax.swing.JTextField txtID;
    private javax.swing.JLabel txtOR;
    private javax.swing.JPasswordField txtPasscode;
    private javax.swing.JLabel user;
    // End of variables declaration//GEN-END:variables
}
