/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package View;

/**
 *
 * @author builo
 */
public class SanPham extends javax.swing.JFrame {

    /**
     * Creates new form SanPham
     */
    public SanPham() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jpsanpham = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblsanpham = new javax.swing.JTable();
        txtmasp = new javax.swing.JTextField();
        txttensp = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txttimsp = new javax.swing.JTextField();
        tbmtimsp = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        btnthemsp = new javax.swing.JButton();
        btnsuasp = new javax.swing.JButton();
        tbnxoasp = new javax.swing.JButton();
        btnchitietsp = new javax.swing.JButton();
        btndsspan = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        lblidsp = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jpsanpham.setBackground(new java.awt.Color(255, 255, 255));
        jpsanpham.setPreferredSize(new java.awt.Dimension(965, 654));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel6.setText("Quản lý sản phẩm");

        jLabel7.setText("Mã sản phẩm:");

        jLabel8.setText("Tên sản phẩm:");

        tblsanpham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "ID Sản phẩm", "Mã sản phẩm", "Tên sản phẩm"
            }
        ));
        tblsanpham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblsanphamMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblsanpham);

        jLabel11.setText("Tìm kiếm:");

        tbmtimsp.setText("Tìm kiếm");

        btnthemsp.setText("Thêm");
        btnthemsp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnthemspActionPerformed(evt);
            }
        });

        btnsuasp.setText("Sửa");
        btnsuasp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsuaspActionPerformed(evt);
            }
        });

        tbnxoasp.setText("Xóa");
        tbnxoasp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tbnxoaspActionPerformed(evt);
            }
        });

        btnchitietsp.setText("Chi tiết");
        btnchitietsp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnchitietspActionPerformed(evt);
            }
        });

        btndsspan.setText("Danh sách ẩn");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btndsspan)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnthemsp)
                            .addComponent(tbnxoasp))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnsuasp, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnchitietsp, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(38, 38, 38))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(37, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnthemsp)
                    .addComponent(btnsuasp))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tbnxoasp)
                    .addComponent(btnchitietsp))
                .addGap(32, 32, 32)
                .addComponent(btndsspan)
                .addContainerGap())
        );

        jLabel12.setText("ID:");

        lblidsp.setText("S0281931");

        javax.swing.GroupLayout jpsanphamLayout = new javax.swing.GroupLayout(jpsanpham);
        jpsanpham.setLayout(jpsanphamLayout);
        jpsanphamLayout.setHorizontalGroup(
            jpsanphamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpsanphamLayout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addGroup(jpsanphamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 840, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jpsanphamLayout.createSequentialGroup()
                        .addGroup(jpsanphamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jpsanphamLayout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addGap(31, 31, 31)
                                .addComponent(txttimsp, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(37, 37, 37)
                                .addComponent(tbmtimsp))
                            .addGroup(jpsanphamLayout.createSequentialGroup()
                                .addGroup(jpsanphamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel12)
                                    .addComponent(jLabel8))
                                .addGap(38, 38, 38)
                                .addGroup(jpsanphamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtmasp, javax.swing.GroupLayout.DEFAULT_SIZE, 201, Short.MAX_VALUE)
                                    .addComponent(txttensp)
                                    .addComponent(lblidsp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addGap(181, 181, 181)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpsanphamLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel6)
                .addGap(345, 345, 345))
        );
        jpsanphamLayout.setVerticalGroup(
            jpsanphamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpsanphamLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel6)
                .addGap(20, 20, 20)
                .addGroup(jpsanphamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jpsanphamLayout.createSequentialGroup()
                        .addGroup(jpsanphamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblidsp)
                            .addComponent(jLabel12))
                        .addGap(18, 18, 18)
                        .addGroup(jpsanphamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(txtmasp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jpsanphamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(txttensp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jpsanphamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(txttimsp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tbmtimsp))))
                .addGap(33, 33, 33)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 906, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jpsanpham, javax.swing.GroupLayout.DEFAULT_SIZE, 894, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 548, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jpsanpham, javax.swing.GroupLayout.DEFAULT_SIZE, 536, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tblsanphamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblsanphamMouseClicked
        // TODO add your handling code here:
        i = tblsanpham.getSelectedRow();
        ShowSP(i);
    }//GEN-LAST:event_tblsanphamMouseClicked

    private void btnthemspActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnthemspActionPerformed
        // TODO add your handling code here:
        if (spservice.AddSP(readFormSP()) > 0) {
            JOptionPane.showMessageDialog(this, "Thêm thành công");
            fillTBLSanPham(spservice.gettAll());
        } else {
            JOptionPane.showMessageDialog(this, "Thêm thất bại");
        }
    }//GEN-LAST:event_btnthemspActionPerformed

    private void btnsuaspActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsuaspActionPerformed
        // TODO add your handling code here:
        if (spservice.UpdateSP(readFormSP()) > 0) {
            JOptionPane.showMessageDialog(this, "Sửa thành công");
            fillTBLSanPham(spservice.gettAll());
        } else {
            JOptionPane.showMessageDialog(this, "Sửa thất bại");
        }
    }//GEN-LAST:event_btnsuaspActionPerformed

    private void tbnxoaspActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tbnxoaspActionPerformed
        // TODO add your handling code here:
        i = tblsanpham.getSelectedRow();
        if (spservice.DeleteSP(tblsanpham.getValueAt(i, 0).toString()) > 0) {
            JOptionPane.showMessageDialog(this, "Xóa thành công");
            fillTBLSanPham(spservice.gettAll());
        } else {
            JOptionPane.showMessageDialog(this, "Xóa thất bại");
        }
    }//GEN-LAST:event_tbnxoaspActionPerformed

    private void btnchitietspActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnchitietspActionPerformed
        // TODO add your handling code here:
        i = tblsanpham.getSelectedRow();
        viewSPCT spct = new viewSPCT(spservice.findByID(tblsanpham.getValueAt(i, 0).toString()));
        spct.setVisible(true);
    }//GEN-LAST:event_btnchitietspActionPerformed

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
            java.util.logging.Logger.getLogger(SanPham.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SanPham.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SanPham.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SanPham.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SanPham().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnchitietsp;
    private javax.swing.JButton btndsspan;
    private javax.swing.JButton btnsuasp;
    private javax.swing.JButton btnthemsp;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel jpsanpham;
    private javax.swing.JLabel lblidsp;
    private javax.swing.JTable tblsanpham;
    private javax.swing.JButton tbmtimsp;
    private javax.swing.JButton tbnxoasp;
    private javax.swing.JTextField txtmasp;
    private javax.swing.JTextField txttensp;
    private javax.swing.JTextField txttimsp;
    // End of variables declaration//GEN-END:variables
}
