/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Views;

import Models.SanPham;
import java.sql.*;
import Repositories.DbConnection;
import Services.SanPhamService;
import com.formdev.flatlaf.themes.FlatMacLightLaf;
import com.sun.jdi.connect.spi.Connection;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author X1
 */
public class Import extends javax.swing.JFrame {
    SanPhamService spService = new SanPhamService();
    
    /**
     * Creates new form Import
     */
    
    public Import() {
        initComponents();
        setLocationRelativeTo(null);
    }
    
    void importExcelToTable() {
        DefaultTableModel model = (DefaultTableModel) tblImportSP.getModel();
        model.setRowCount(0);

        File excelFile;
        FileInputStream fis = null;
        BufferedInputStream excelBIS = null;
        XSSFWorkbook excelImportToJTable = null;
        String defaultCurrentDirectoryPath = "C:\\Users\\X1\\OneDrive\\Documents\\Custom Office Templates";
        JFileChooser excelFileChooser = new JFileChooser(defaultCurrentDirectoryPath);
        excelFileChooser.setDialogTitle("Lựa File Excel");
        FileNameExtensionFilter fnef = new FileNameExtensionFilter("EXCEL FILES", "xls", "xlsx", "xlsm");
        excelFileChooser.setFileFilter(fnef);
        int excelChooser = excelFileChooser.showOpenDialog(null);
        if (excelChooser == JFileChooser.APPROVE_OPTION) {
            try {
                excelFile = excelFileChooser.getSelectedFile();
                txtFilePath.setText(excelFile.toString());
                fis = new FileInputStream(excelFile);
                excelBIS = new BufferedInputStream(fis);
                excelImportToJTable = new XSSFWorkbook(excelBIS);
                XSSFSheet excelSheet = excelImportToJTable.getSheetAt(0);

                for (int row = 1; row < excelSheet.getLastRowNum(); row++) {
                    XSSFRow excelRow = excelSheet.getRow(row);
                    if (excelRow != null && excelRow.getCell(0) != null) {
                        // Lấy dữ liệu từ các ô trong hàng
                        Integer excelNo = (int) excelRow.getCell(0).getNumericCellValue();
//                        Integer excelMaSP = (int) excelRow.getCell(1).getNumericCellValue();
                        Integer excelMaDM = (int) excelRow.getCell(1).getNumericCellValue();
                        Integer excelMaMau = (int) excelRow.getCell(2).getNumericCellValue();
                        Integer excelMaSize = (int) excelRow.getCell(3).getNumericCellValue();
                        Double excelGiaNhap = excelRow.getCell(4).getNumericCellValue();
                        Double excelGiaBan = excelRow.getCell(5).getNumericCellValue();
                        String excelTrangThai = excelRow.getCell(6).getStringCellValue();
                        Integer excelMaSPCT = (int) excelRow.getCell(7).getNumericCellValue();
                        Integer excelMSP = (int) excelRow.getCell(8).getNumericCellValue();
                        String excelTenSP = excelRow.getCell(9).getStringCellValue();
                        String excelSize = excelRow.getCell(10).getStringCellValue();
                        String excelMau = excelRow.getCell(11).getStringCellValue();
                        String excelTT = excelRow.getCell(12).getStringCellValue();
                        Integer excelSLT = (int) excelRow.getCell(13).getNumericCellValue();

                        // Thêm hàng mới vào bảng
                        model.addRow(new Object[]{excelNo, excelMaDM, excelMaMau, excelMaSize, excelGiaNhap, excelGiaBan, excelTrangThai, excelMaSPCT, excelMSP, excelTenSP, excelSize, excelMau, excelTT, excelSLT});
                    }
                }
                JOptionPane.showMessageDialog(null, "Import success!!!");
            } catch (IOException iOException) {
                JOptionPane.showMessageDialog(null, iOException.getMessage());
            } finally {
                try {
                    if (fis != null) {
                        fis.close();
                    }
                    if (excelBIS != null) {
                        excelBIS.close();
                    }
                    if (excelImportToJTable != null) {
                        excelImportToJTable.close();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //GETMODEL
    public SanPham getModelSP() {
        Integer pos = tblImportSP.getSelectedRow();
        if (pos == -1) {
            return null;
        }
        Integer maSP = (Integer) tblImportSP.getValueAt(pos, 0);
        Integer maDM = (Integer) tblImportSP.getValueAt(pos, 1);
        Integer maMau = (Integer) tblImportSP.getValueAt(pos, 2);
        Integer maSz = (Integer) tblImportSP.getValueAt(pos, 3);
        Double giaN = (Double) tblImportSP.getValueAt(pos, 4);
        Double giaB = (Double) tblImportSP.getValueAt(pos, 5);
        String trangT = (String) tblImportSP.getValueAt(pos, 6);
        String tenSP = (String) tblImportSP.getValueAt(pos, 10);
        Integer soL = (Integer) tblImportSP.getValueAt(pos, 13);
        
        SanPham sp = new SanPham();
        sp.setMaSP(Integer.valueOf(spService.getListSP().get(spService.getListSP().size()-1).getMaSP()));
        sp.setTenSP(tenSP);
        sp.setMaDM(maDM);
        sp.setTrangThai(trangT);
        sp.setGiaNhap(giaN);
        sp.setGiaBan(giaB);
        sp.setMaSz(maSz);
        sp.setMaMau(maMau);
        sp.setSoLuong(soL);

        return sp;
    }

    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtFilePath = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblImportSP = new javax.swing.JTable();
        btnChooseFile = new javax.swing.JButton();
        btnImport = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tblImportSP.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "MSP", "MDM", "Mã Màu", "Mã Size", "Giá Nhập", "Giá Bán", "Trạng Thái", "MSPCT", "MSP", "Tên SP", "Size", "Màu", "Trạng Thái"
            }
        ));
        jScrollPane1.setViewportView(tblImportSP);

        btnChooseFile.setText("Choose File");
        btnChooseFile.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnChooseFileMouseClicked(evt);
            }
        });

        btnImport.setText("IMPORT");
        btnImport.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnImportMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtFilePath, javax.swing.GroupLayout.PREFERRED_SIZE, 381, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(39, 39, 39)
                        .addComponent(btnChooseFile))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 848, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnImport, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(26, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtFilePath, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnChooseFile))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnImport)
                .addContainerGap(17, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnChooseFileMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnChooseFileMouseClicked
        // IMPORT
        importExcelToTable();
    }//GEN-LAST:event_btnChooseFileMouseClicked

    private void btnImportMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnImportMouseClicked
        // ADD
        JOptionPane.showMessageDialog(this, spService.addImport(getModelSP()));
    }//GEN-LAST:event_btnImportMouseClicked

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
                new Import().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnChooseFile;
    private javax.swing.JButton btnImport;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblImportSP;
    private javax.swing.JTextField txtFilePath;
    // End of variables declaration//GEN-END:variables
}
