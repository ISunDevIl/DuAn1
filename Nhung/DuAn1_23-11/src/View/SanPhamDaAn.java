package View;

import Connection.DBContext;
import Model.SanPham;
import Service.SanPhamService;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class SanPhamDaAn extends javax.swing.JFrame {

    private ArrayList<SanPham> listsp = new ArrayList<>();
    private DefaultTableModel dtm = new DefaultTableModel();
    private Connection con = DBContext.getConnection();
    private SanPhamService spservice = new SanPhamService();
    int index = -1;

    public SanPhamDaAn() {
        initComponents();
        loadDataSP();
        this.setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);
        
    }

    public void loadDataSP() {
        String sql = "Select * From SanPham where TrangThai = 1";
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);

            listsp.clear();

            while (rs.next()) {
                SanPham sp = new SanPham();
                sp.setId(rs.getString("ID"));
                sp.setMaSP(rs.getString("MaSP"));
                sp.setTenSP(rs.getString("TenSP"));
                sp.setTrangThai(rs.getByte("TrangThai"));
                listsp.add(sp);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        dtm = (DefaultTableModel) tblsp.getModel();
        dtm.setRowCount(0);
        for (SanPham x : listsp) {
            dtm.addRow(new Object[]{
                x.getId(), x.getMaSP(), x.getTenSP()
            });
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblsp = new javax.swing.JTable();
        btnhienthi = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Sản phẩm đã ẩn");

        tblsp.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "ID", "Tên SP"
            }
        ));
        tblsp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblspMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblsp);

        btnhienthi.setText("Hiển thị lại");
        btnhienthi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnhienthiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(202, 202, 202)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 698, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnhienthi, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(jLabel1)
                .addGap(26, 26, 26)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnhienthi)
                .addContainerGap(17, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnhienthiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnhienthiActionPerformed
        SanPham sp = new SanPham();
        sp.setId(listsp.get(index).getId());
        boolean anResult = spservice.hienthilaisp(sp);
        if(anResult){
            JOptionPane.showMessageDialog(this, "Hiển thị lại thành công");
        }else {
            JOptionPane.showMessageDialog(this, "Hiển thj lại thất bại");
        }
    }//GEN-LAST:event_btnhienthiActionPerformed

    private void tblspMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblspMouseClicked
        index = tblsp.getSelectedRow();
        listsp.get(index);
    }//GEN-LAST:event_tblspMouseClicked

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SanPhamDaAn().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnhienthi;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblsp;
    // End of variables declaration//GEN-END:variables
}
