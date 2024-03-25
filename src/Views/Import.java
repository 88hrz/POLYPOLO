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
    
    void loadTable(ArrayList<SanPham> ls){
        DefaultTableModel model = (DefaultTableModel) tblImportSP.getModel();
        model.setRowCount(0);
        for (SanPham sp : ls) {
            model.addRow(new Object[]{
                sp.getMaSP(), sp.getMaDM(), sp.getMaMau(), sp.getMaSz()
                    , sp.getGiaNhap(), sp.getGiaBan(), sp.getTrangThai()
            });
        }
    }

    void importExcelToTable(){
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
 
                    XSSFCell excelNo = excelRow.getCell(0);
                    XSSFCell excelMaSP = excelRow.getCell(1);
                    XSSFCell excelMaDM = excelRow.getCell(2);
                    XSSFCell excelMaMau = excelRow.getCell(3);
                    XSSFCell excelMaSize = excelRow.getCell(4);
                    XSSFCell excelGiaNhap = excelRow.getCell(5);
                    XSSFCell excelGiaBan = excelRow.getCell(6);
                    XSSFCell excelTrangThai = excelRow.getCell(7);
                    
                //    JLabel excelJL = new JLabel(new ImageIcon(new ImageIcon(excelImage.getStringCellValue()).getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH)));
                    model.addRow(new Object[]{excelNo, excelMaSP, excelMaDM, excelMaMau, excelMaSize, excelGiaNhap, excelGiaBan, excelTrangThai});
                }
                JOptionPane.showMessageDialog(null, "Import success!!!");
            } catch (IOException iOException) {
                JOptionPane.showMessageDialog(null, iOException.getMessage());
            }finally{
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
    Integer maSP = (Integer) tblImportSP.getValueAt(pos, 1);
    Integer maDM = (Integer) tblImportSP.getValueAt(pos, 2);
    Integer maMau = (Integer) tblImportSP.getValueAt(pos, 3);
    Integer maSz = (Integer) tblImportSP.getValueAt(pos, 4);
    Double giaN = (Double) tblImportSP.getValueAt(pos, 5);
    Double giaB = (Double) tblImportSP.getValueAt(pos, 6);
    String trangT = (String) tblImportSP.getValueAt(pos, 7);

    SanPham sp = new SanPham();
    sp.setMaSP(maSP);
    sp.setMaDM(maDM);sp.setMaMau(maMau);
    sp.setMaSz(maSz);
    sp.setGiaNhap(giaN);
    sp.setGiaBan(giaB);
    sp.setTrangThai(trangT);

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
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "MSP", "MDM", "MaMau", "MaSize", "Giá Nhập", "Giá Bán", "Trạng Thái"
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
                    .addComponent(btnImport, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtFilePath, javax.swing.GroupLayout.PREFERRED_SIZE, 381, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(39, 39, 39)
                        .addComponent(btnChooseFile))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 709, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(53, Short.MAX_VALUE))
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

                //IMPORT
                try {
                    // Bước 1: Đọc dữ liệu từ tệp Excel vào Java
                    String excelFilePath = "C:\\Users\\X1\\OneDrive\\Documents\\Custom Office Templates\\IMPORT.xlsx";
                    FileInputStream excelFile = new FileInputStream(new File(excelFilePath));

                    //           FileInputStream excelFile = new FileInputStream(new File("C:\\Users\\ACER\\Desktop"));
                    Workbook workbook = new XSSFWorkbook(excelFile);
                    Sheet sheet = workbook.getSheetAt(0); // Đọc sheet đầu tiên

                    // Bước 2: Kết nối với cơ sở dữ liệu SQL
                    Connection conn = (Connection) DbConnection.getConnection();

                    if (conn != null) {
                        // Bước 3: Lưu dữ liệu vào cơ sở dữ liệu SQL
                        for (Row row : sheet) {
                            // Đọc dữ liệu từ các ô trong mỗi dòng và lưu vào cơ sở dữ liệu SQL
                            String tenSach = row.getCell(0).getStringCellValue(); // Tên sách
                            String theLoai = row.getCell(1).getStringCellValue(); // Thể loại

                            Cell soTrangCell = row.getCell(2);
                            int soTrang;
                            if (soTrangCell.getCellType() == CellType.NUMERIC) {
                                soTrang = (int) soTrangCell.getNumericCellValue();
                            } else {
                                soTrang = 0; // hoặc thông báo lỗi tùy thuộc vào yêu cầu của bạn
                            }

                            int soLuongTon = (int) row.getCell(3).getNumericCellValue(); // Số lượng tồn
                            int idTacGia = (int) row.getCell(4).getNumericCellValue(); // ID tác giả
                            String trangThai = row.getCell(5).getStringCellValue(); // Trạng thái

                            // Thực hiện truy vấn INSERT
                            String sql = "INSERT INTO Sach (TenSach, TheLoai, SoTrang, SoLuongTon, idTacGia, TrangThai) VALUES (?, ?, ?, ?, ?, ?)";
                            PreparedStatement statement = conn.prepareCall(sql);
                            statement.setString(1, tenSach);
                            statement.setString(2, theLoai);
                            statement.setInt(3, soTrang);
                            statement.setInt(4, soLuongTon);
                            statement.setInt(5, idTacGia);
                            statement.setString(6, trangThai);

                            statement.executeUpdate();
                        }

                        // Đóng kết nối
                        conn.close();

                        System.out.println("Import tu Excel vao SQL thanh cong.");
                    } else {
                        System.out.println("Không thể kết nối đến cơ sở dữ liệu.");
                    }

                    // Đóng workbook sau khi sử dụng xong
                    workbook.close();
                } catch (IOException | SQLException ex) {
                    ex.printStackTrace();
                }

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
