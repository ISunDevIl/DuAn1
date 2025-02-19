/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package View;

import Model.ChatLieu;
import Model.MauSac;
import Model.PhanLoai;
import Model.SanPhamChiTiet;
import Model.ThuongHieu;
import Model.XuatXu;
import Service.ChatLieuService;
import Service.MauSacService;
import Service.PhanLoaiService;
import Service.SPCTService;
import Service.ThuongHieuService;
import Service.XuatXuService;
import java.awt.HeadlessException;
import java.util.List;
import javax.swing.JDialog;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author admin
 */
public class SPCTDialog extends javax.swing.JFrame{
private DefaultTableModel dtm = new DefaultTableModel();
private SPCTService service = new SPCTService();
private ChatLieuService cls = new ChatLieuService();
private PhanLoaiService pls = new PhanLoaiService();
private MauSacService mss = new MauSacService();
private ThuongHieuService ths = new ThuongHieuService();
private XuatXuService xxs = new XuatXuService();
String ids;
    /**
     * Creates new form SPCTDialog
     */
    public SPCTDialog() {
        initComponents();
        setLocationRelativeTo(null);
        ids = id;
        LoadCBO();
        fillTable(service.findSPCTByIDSP(id));
    }

    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        lblspanh = new javax.swing.JLabel();
        txtsoluong = new javax.swing.JTextField();
        cbochatlieu = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        cbophanloai = new javax.swing.JComboBox<>();
        cbomausac = new javax.swing.JComboBox<>();
        cbothuonghieu = new javax.swing.JComboBox<>();
        cboxuatxu = new javax.swing.JComboBox<>();
        txtgia = new javax.swing.JTextField();
        lblmasp = new javax.swing.JLabel();
        lbltensp = new javax.swing.JLabel();
        txtid = new javax.swing.JTextField();
        txtkichthuoc = new javax.swing.JTextField();
        txtkhoiluong = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtamota = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblspct = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        btnthemth = new javax.swing.JButton();
        btnthemphanloai = new javax.swing.JButton();
        btnthemcl = new javax.swing.JButton();
        tbmthemms = new javax.swing.JButton();
        btnthemxx = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel1.setText("Sản phẩm chi tiết");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("ID:");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("Tên sản phẩm:");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setText("Mã sản phẩm:");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setText("Kích thước:");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setText("Khối lượng:");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel7.setText("Số lượng:");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel8.setText("Giá:");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel9.setText("Mô tả:");

        lblspanh.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        txtsoluong.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        cbochatlieu.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cbochatlieu.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel10.setText("Màu sắc:");

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel11.setText("Phân loại:");

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel12.setText("Chất liệu:");

        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel13.setText("Thương hiệu:");

        jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel14.setText("Xuất xứ:");

        cbophanloai.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cbophanloai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cbomausac.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cbomausac.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cbothuonghieu.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cbothuonghieu.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cboxuatxu.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cboxuatxu.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        txtgia.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        lblmasp.setText("MaSP");

        lbltensp.setText("MaSP");

        txtid.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        txtkichthuoc.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        txtkhoiluong.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        txtamota.setColumns(20);
        txtamota.setRows(5);
        jScrollPane1.setViewportView(txtamota);

        tblspct.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Phân loại", "Chất liệu", "Thương hiệu", "Màu sắc", "Xuất xứ", "Kích thước", "Khối lượng", "Số lượng", "Giá", "Mô tả", "Ảnh"
            }
        ));
        tblspct.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblspctMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblspct);

        jButton1.setText("Thêm");

        jButton2.setText("Sửa");

        jButton3.setText("Xóa");

        jButton4.setText("Mới");

        jButton5.setText("Đã xóa");

        jButton6.setText("Sản phẩm");

        btnthemth.setText("+");
        btnthemth.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnthemthActionPerformed(evt);
            }
        });

        btnthemphanloai.setText("+");
        btnthemphanloai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnthemphanloaiActionPerformed(evt);
            }
        });

        btnthemcl.setText("+");
        btnthemcl.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnthemclActionPerformed(evt);
            }
        });

        tbmthemms.setText("+");
        tbmthemms.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tbmthemmsActionPerformed(evt);
            }
        });

        btnthemxx.setText("+");
        btnthemxx.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnthemxxActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 820, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblspanh, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(23, 23, 23)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel2))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(txtid, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(lblmasp, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addComponent(lbltensp, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(42, 42, 42)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel9))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jScrollPane1)
                                    .addComponent(txtkhoiluong)
                                    .addComponent(txtkichthuoc)))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(40, 40, 40)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel13)
                                .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.TRAILING))
                            .addComponent(jLabel7)
                            .addComponent(jLabel8))
                        .addGap(26, 26, 26)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtsoluong, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtgia, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(cbochatlieu, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cbophanloai, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cbomausac, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cbothuonghieu, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cboxuatxu, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnthemth)
                                    .addComponent(btnthemphanloai)
                                    .addComponent(btnthemcl)
                                    .addComponent(tbmthemms)
                                    .addComponent(btnthemxx))
                                .addGap(21, 21, 21))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(60, 60, 60)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton3)
                            .addComponent(jButton4)
                            .addComponent(jButton6))
                        .addGap(26, 26, 26)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton1)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jButton5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                .addContainerGap(12, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jButton1, jButton2, jButton3, jButton4, jButton6});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(32, 32, 32)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel11)
                                    .addComponent(cbophanloai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnthemphanloai, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(6, 6, 6)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel10)
                                    .addComponent(cbomausac, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(tbmthemms, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel12)
                                .addComponent(cbochatlieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnthemcl, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(cbothuonghieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnthemth, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel14)
                            .addComponent(cboxuatxu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnthemxx, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(txtsoluong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(txtgia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton4)
                            .addComponent(jButton1))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton3)
                            .addComponent(jButton2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton6)
                            .addComponent(jButton5)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel2)
                                            .addComponent(txtid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel4)
                                            .addComponent(lblmasp)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(67, 67, 67)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel3)
                                            .addComponent(lbltensp))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(txtkichthuoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel5))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(txtkhoiluong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel6))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel9)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(lblspanh, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(15, 15, 15)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(68, 68, 68))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tblspctMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblspctMouseClicked
        // TODO add your handling code here:
        int i= tblspct.getSelectedRow();
        Show(i);
    }//GEN-LAST:event_tblspctMouseClicked

    private void btnthemthActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnthemthActionPerformed
        panelThuongHieu dialog = new panelThuongHieu(this, true);
        dialog.setVisible(true);
        dialog.setSize(780, 437);
        dialog.setLocationRelativeTo(this);
    }//GEN-LAST:event_btnthemthActionPerformed

    private void btnthemphanloaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnthemphanloaiActionPerformed
        JDialog dialog = new JDialog();
        dialog.setVisible(true);
        dialog.setSize(780, 437);
        dialog.add(panelPhanLoai);
        dialog.setLocationRelativeTo(this);
    }//GEN-LAST:event_btnthemphanloaiActionPerformed

    private void btnthemclActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnthemclActionPerformed
        JDialog dialog = new JDialog();
        dialog.setVisible(true);
        dialog.setSize(780, 437);
        dialog.add(panelChatLieu);
        dialog.setLocationRelativeTo(this);
    }//GEN-LAST:event_btnthemclActionPerformed

    private void tbmthemmsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tbmthemmsActionPerformed
        JDialog dialog = new JDialog();
        dialog.setVisible(true);
        dialog.setSize(780, 437);
        dialog.add(panelMauSac);
        dialog.setLocationRelativeTo(this);
    }//GEN-LAST:event_tbmthemmsActionPerformed

    private void btnthemxxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnthemxxActionPerformed
        JDialog dialog = new JDialog();
        dialog.setVisible(true);
        dialog.setSize(780, 437);
        dialog.add(panelXuatXu);
        dialog.setLocationRelativeTo(this);
    }//GEN-LAST:event_btnthemxxActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(SPCTDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SPCTDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SPCTDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SPCTDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SPCTDialog().setVisible(true);
            }
        });
    }
    private void fillTable(List<SanPhamChiTiet> l){
        dtm = (DefaultTableModel) tblspct.getModel();
        dtm.setRowCount(0);
        for(SanPhamChiTiet x:service.getAll()){
            dtm.addRow(new Object[]{
                x.getId(),pls.findPL(x.getIdpl()).getTen(),cls.findCL(x.getIdcl()).getTen(),ths.findTH(x.getIdth()).getTen(),mss.findMS(x.getIdms()).getTen(),
                xxs.findXX(x.getIdxx()).getTen(),x.getKichThuoc(),x.getKhoiLuong(),x.getSoLuong(),x.getGia(),x.getMoTa(),x.getAnh()
            });
        }
    }
    private void LoadCBO(){
        cbochatlieu.removeAllItems();
        cbomausac.removeAllItems();
        cbophanloai.removeAllItems();
        cbothuonghieu.removeAllItems();
        cboxuatxu.removeAllItems();
        for(ChatLieu x:cls.gettAll()){
            cbochatlieu.addItem(x.getTen());
        }
        for(PhanLoai x:pls.gettAll()){
            cbophanloai.addItem(x.getTen());
        }
        for(MauSac x:mss.gettAll()){
            cbomausac.addItem(x.getTen());
        }
        for(ThuongHieu x:ths.gettAll()){
            cbothuonghieu.addItem(x.getTen());
        }
        for(XuatXu x:xxs.gettAll()){
            cboxuatxu.addItem(x.getTen());
        }
    }
    private void Show(int i){
        txtid.setText(tblspct.getValueAt(i, 0).toString());
        cbophanloai.setSelectedItem(tblspct.getValueAt(i, 1).toString());
        cbochatlieu.setSelectedItem(tblspct.getValueAt(i, 2).toString());
        cbothuonghieu.setSelectedItem(tblspct.getValueAt(i, 3).toString());
        cbomausac.setSelectedItem(tblspct.getValueAt(i, 4).toString());
        cboxuatxu.setSelectedItem(tblspct.getValueAt(i, 5).toString());
        txtkichthuoc.setText(tblspct.getValueAt(i, 6).toString());
        txtkhoiluong.setText(tblspct.getValueAt(i, 7).toString());
        txtsoluong.setText(tblspct.getValueAt(i, 8).toString());
        txtgia.setText(tblspct.getValueAt(i, 9).toString());
        txtamota.setText(tblspct.getValueAt(i, 10).toString());
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnthemcl;
    private javax.swing.JButton btnthemphanloai;
    private javax.swing.JButton btnthemth;
    private javax.swing.JButton btnthemxx;
    private javax.swing.JComboBox<String> cbochatlieu;
    private javax.swing.JComboBox<String> cbomausac;
    private javax.swing.JComboBox<String> cbophanloai;
    private javax.swing.JComboBox<String> cbothuonghieu;
    private javax.swing.JComboBox<String> cboxuatxu;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblmasp;
    private javax.swing.JLabel lblspanh;
    private javax.swing.JLabel lbltensp;
    private javax.swing.JTable tblspct;
    private javax.swing.JButton tbmthemms;
    private javax.swing.JTextArea txtamota;
    private javax.swing.JTextField txtgia;
    private javax.swing.JTextField txtid;
    private javax.swing.JTextField txtkhoiluong;
    private javax.swing.JTextField txtkichthuoc;
    private javax.swing.JTextField txtsoluong;
    // End of variables declaration//GEN-END:variables
}
