/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package View;

import Model.KhuyenMai;
import Model.PhanLoai;
import Model.SanPhamChiTiet;
import Service.ChatLieuService;
import Service.KhuyenMaiService;
import Service.MauSacService;
import Service.PhanLoaiService;
import Service.SPCTService;
import Service.SanPhamService;
import Service.ThuongHieuService;
import Service.XuatXuService;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author builo
 */
public class ViewKhuyenMai extends javax.swing.JFrame {

    DefaultTableModel mol = new DefaultTableModel();
    List<SanPhamChiTiet> list = new ArrayList<>();
    SPCTService service = new SPCTService();
    PhanLoaiService plservice = new PhanLoaiService();
    MauSacService msSer = new MauSacService();
    ThuongHieuService thSer = new ThuongHieuService();
    ChatLieuService clSer = new ChatLieuService();
    XuatXuService xxSer = new XuatXuService();
    SanPhamService spSer = new SanPhamService();
    List<KhuyenMai> listKM = new ArrayList<>();
    KhuyenMaiService kmSer = new KhuyenMaiService();

    int index = -1;

    public ViewKhuyenMai() {

        initComponents();

        DefaultComboBoxModel cbb = (DefaultComboBoxModel) cboLoaiApDung.getModel();
        cbb.removeAllElements();

        cboLoaiApDung.removeAllItems();
        List<PhanLoai> listPL = plservice.gettAll();
        for (PhanLoai pl : listPL) {
            cbb.addElement(pl.getTen());
        }
        cboLoaiApDung.addItem("Tất cả sản phẩm");
        this.fillTable(service.gettAll());
        this.fillTableKhuyenMai(kmSer.gettAll());

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jRadioButton1 = new javax.swing.JRadioButton();
        jpkhuyenmai = new javax.swing.JPanel();
        jPanel30 = new javax.swing.JPanel();
        jScrollPane15 = new javax.swing.JScrollPane();
        tblkmdssp = new javax.swing.JTable();
        jLabel108 = new javax.swing.JLabel();
        cboLoaiApDung = new javax.swing.JComboBox<>();
        jLabel114 = new javax.swing.JLabel();
        txtkmtu = new javax.swing.JTextField();
        jLabel115 = new javax.swing.JLabel();
        txtkmden = new javax.swing.JTextField();
        jLabel116 = new javax.swing.JLabel();
        cbokmhinhthuc = new javax.swing.JComboBox<>();
        jPanel31 = new javax.swing.JPanel();
        jScrollPane14 = new javax.swing.JScrollPane();
        tblkmdskm = new javax.swing.JTable();
        txtkmtim = new javax.swing.JTextField();
        btnkmtim = new javax.swing.JButton();
        jPanel32 = new javax.swing.JPanel();
        jLabel109 = new javax.swing.JLabel();
        jLabel110 = new javax.swing.JLabel();
        jLabel111 = new javax.swing.JLabel();
        jLabel112 = new javax.swing.JLabel();
        jLabel113 = new javax.swing.JLabel();
        rdokmdangdienra = new javax.swing.JRadioButton();
        rdokmdaketthuc = new javax.swing.JRadioButton();
        jPanel33 = new javax.swing.JPanel();
        btnkmluu = new javax.swing.JButton();
        btnkmmoi = new javax.swing.JButton();
        btnkmsua = new javax.swing.JButton();
        btnkmxoa = new javax.swing.JButton();
        txtkmbatdau = new com.toedter.calendar.JDateChooser();
        txtkmketthuc = new com.toedter.calendar.JDateChooser();
        txtkmmucgg = new javax.swing.JTextField();
        txtkmtenct = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        txtGhiChu = new javax.swing.JTextField();
        jTextField1 = new javax.swing.JTextField();

        jRadioButton1.setText("jRadioButton1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jpkhuyenmai.setBackground(new java.awt.Color(255, 0, 255));
        jpkhuyenmai.setPreferredSize(new java.awt.Dimension(919, 654));

        jPanel30.setBorder(javax.swing.BorderFactory.createTitledBorder("Danh sách sản phẩm"));

        tblkmdssp.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Sản PHẩm", "Chất Liệu", "Phân Loại", "Màu Sắc", "THương Hiệu", "Xuất Xứ", "Kích thước", "Khối lượng", "Số Lượng", "Giá", "TRạng Thái"
            }
        ));
        jScrollPane15.setViewportView(tblkmdssp);
        if (tblkmdssp.getColumnModel().getColumnCount() > 0) {
            tblkmdssp.getColumnModel().getColumn(0).setHeaderValue("ID");
            tblkmdssp.getColumnModel().getColumn(1).setHeaderValue("Sản PHẩm");
            tblkmdssp.getColumnModel().getColumn(2).setHeaderValue("Chất Liệu");
            tblkmdssp.getColumnModel().getColumn(3).setHeaderValue("Phân Loại");
            tblkmdssp.getColumnModel().getColumn(4).setHeaderValue("Màu Sắc");
            tblkmdssp.getColumnModel().getColumn(5).setHeaderValue("THương Hiệu");
            tblkmdssp.getColumnModel().getColumn(6).setHeaderValue("Xuất Xứ");
            tblkmdssp.getColumnModel().getColumn(7).setHeaderValue("Kích thước");
            tblkmdssp.getColumnModel().getColumn(8).setHeaderValue("Khối lượng");
            tblkmdssp.getColumnModel().getColumn(9).setHeaderValue("Số Lượng");
            tblkmdssp.getColumnModel().getColumn(10).setHeaderValue("Giá");
            tblkmdssp.getColumnModel().getColumn(11).setHeaderValue("TRạng Thái");
        }

        jLabel108.setText("Tìm Loại Sản Phẩm");

        cboLoaiApDung.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cboLoaiApDung.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboLoaiApDungItemStateChanged(evt);
            }
        });
        cboLoaiApDung.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboLoaiApDungActionPerformed(evt);
            }
        });

        jLabel114.setText("Từ");

        jLabel115.setText("Đến");

        jLabel116.setText("Hình thức");

        cbokmhinhthuc.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout jPanel30Layout = new javax.swing.GroupLayout(jPanel30);
        jPanel30.setLayout(jPanel30Layout);
        jPanel30Layout.setHorizontalGroup(
            jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel30Layout.createSequentialGroup()
                .addGroup(jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel30Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(jLabel108, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cboLoaiApDung, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel116, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
                        .addComponent(cbokmhinhthuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(jLabel114, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtkmtu, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel115, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtkmden, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane15))
                .addContainerGap())
        );
        jPanel30Layout.setVerticalGroup(
            jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel30Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel108)
                    .addComponent(cboLoaiApDung, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel114)
                    .addComponent(txtkmtu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel115)
                    .addComponent(txtkmden, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel116)
                    .addComponent(cbokmhinhthuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane15, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        jPanel31.setBorder(javax.swing.BorderFactory.createTitledBorder("Danh sách khuyến mãi"));

        tblkmdskm.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã Khuyến Mại", "ID spct", "Tên ", "Ngày Bắt Đầu", "Ngày Hết Hạn", "Giá Trị", "Ghi Chú", "Trạng Thái"
            }
        ));
        tblkmdskm.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblkmdskmMouseClicked(evt);
            }
        });
        jScrollPane14.setViewportView(tblkmdskm);

        btnkmtim.setText("Tìm kiếm");

        javax.swing.GroupLayout jPanel31Layout = new javax.swing.GroupLayout(jPanel31);
        jPanel31.setLayout(jPanel31Layout);
        jPanel31Layout.setHorizontalGroup(
            jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel31Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane14)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel31Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtkmtim, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnkmtim)
                .addGap(21, 21, 21))
        );
        jPanel31Layout.setVerticalGroup(
            jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel31Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtkmtim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnkmtim))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addComponent(jScrollPane14, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jLabel109.setText("Tên chương trình");

        jLabel110.setText("Mức giảm giá");

        jLabel111.setText("Thời gian bắt đầu");

        jLabel112.setText("Thời gian kết thúc");

        jLabel113.setText("Trạng thái :");

        buttonGroup1.add(rdokmdangdienra);
        rdokmdangdienra.setText("Đang diễn ra");

        buttonGroup1.add(rdokmdaketthuc);
        rdokmdaketthuc.setText("Đã kết thúc");
        rdokmdaketthuc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdokmdaketthucActionPerformed(evt);
            }
        });

        jPanel33.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 0, 0, 0, new java.awt.Color(0, 0, 0)));

        btnkmluu.setText("Lưu");
        btnkmluu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnkmluuActionPerformed(evt);
            }
        });

        btnkmmoi.setText("Mới");
        btnkmmoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnkmmoiActionPerformed(evt);
            }
        });

        btnkmsua.setText("Sửa");

        btnkmxoa.setText("Xóa");

        javax.swing.GroupLayout jPanel33Layout = new javax.swing.GroupLayout(jPanel33);
        jPanel33.setLayout(jPanel33Layout);
        jPanel33Layout.setHorizontalGroup(
            jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnkmluu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel33Layout.createSequentialGroup()
                .addComponent(btnkmxoa, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnkmsua, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(btnkmmoi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel33Layout.setVerticalGroup(
            jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel33Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(btnkmluu, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addGroup(jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnkmsua, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnkmxoa, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btnkmmoi, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        txtkmbatdau.setDateFormatString("dd-MM-yyyy");

        txtkmketthuc.setDateFormatString("dd-MM-yyyy");

        jLabel1.setText("Ghi Chú");

        javax.swing.GroupLayout jPanel32Layout = new javax.swing.GroupLayout(jPanel32);
        jPanel32.setLayout(jPanel32Layout);
        jPanel32Layout.setHorizontalGroup(
            jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel33, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel32Layout.createSequentialGroup()
                .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel32Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel109, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel110, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel111, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel112, javax.swing.GroupLayout.DEFAULT_SIZE, 99, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtkmbatdau, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(txtkmmucgg, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 101, Short.MAX_VALUE)
                            .addComponent(txtkmtenct, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtkmketthuc, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                    .addGroup(jPanel32Layout.createSequentialGroup()
                        .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel32Layout.createSequentialGroup()
                                .addGap(22, 22, 22)
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel32Layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel113, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)))
                        .addComponent(txtGhiChu, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel32Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(rdokmdangdienra, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(rdokmdaketthuc, javax.swing.GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)))
                .addContainerGap(12, Short.MAX_VALUE))
        );
        jPanel32Layout.setVerticalGroup(
            jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel32Layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel109)
                    .addComponent(txtkmtenct, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel110)
                    .addComponent(txtkmmucgg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel111)
                    .addComponent(txtkmbatdau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel112)
                    .addComponent(txtkmketthuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(44, 44, 44)
                .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel32Layout.createSequentialGroup()
                        .addComponent(txtGhiChu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18))
                    .addGroup(jPanel32Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel113)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rdokmdaketthuc)
                    .addComponent(rdokmdangdienra))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel33, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout jpkhuyenmaiLayout = new javax.swing.GroupLayout(jpkhuyenmai);
        jpkhuyenmai.setLayout(jpkhuyenmaiLayout);
        jpkhuyenmaiLayout.setHorizontalGroup(
            jpkhuyenmaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpkhuyenmaiLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jpkhuyenmaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel30, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel31, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel32, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(28, Short.MAX_VALUE))
        );
        jpkhuyenmaiLayout.setVerticalGroup(
            jpkhuyenmaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpkhuyenmaiLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jpkhuyenmaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel32, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jpkhuyenmaiLayout.createSequentialGroup()
                        .addComponent(jPanel30, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel31, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jTextField1.setText("jTextField1");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(813, Short.MAX_VALUE)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(93, 93, 93))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jpkhuyenmai, javax.swing.GroupLayout.PREFERRED_SIZE, 930, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(41, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(263, 263, 263)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(402, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jpkhuyenmai, javax.swing.GroupLayout.DEFAULT_SIZE, 675, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cboLoaiApDungActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboLoaiApDungActionPerformed
        // TODO add your handling code here:

        //
//        PhanLoai pl = (PhanLoai) cboLoaiApDung.getSelectedItem();
//        pl.getId();
//        for (SanPhamChiTiet sct : list) {
//            if (pl.getId()==sct.getIdpl()) {
//               Object[] toData = new Object[]{sct.getId(),sct.getIdsp(),sct.getIdcl(),sct.getIdpl(),sct.getIdms(),sct.getIdth(),sct.getIdxx(),sct.getKichThuoc(),sct.getKhoiLuong(),sct.getSoLuong(),sct.getGia(),sct.getTrangThai()};
//            mol.addRow(toData); 
//            } 
//            
//        }
    }//GEN-LAST:event_cboLoaiApDungActionPerformed

    private void cboLoaiApDungItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboLoaiApDungItemStateChanged
        // TODO add your handling code here:
        String id = (String) cboLoaiApDung.getSelectedItem();
        fillTable(service.gettAllByTenPL(id));
        System.out.println(id);

    }//GEN-LAST:event_cboLoaiApDungItemStateChanged

    private void tblkmdskmMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblkmdskmMouseClicked
        // TODO add your handling code here:
        int index = tblkmdskm.getSelectedRow();
        showBangKM(index);
    }//GEN-LAST:event_tblkmdskmMouseClicked

    private void btnkmmoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnkmmoiActionPerformed
        // TODO add your handling code here:
        txtkmtenct.setText("");
        txtkmmucgg.setText("");
        txtkmbatdau.setDate(null);
        txtkmketthuc.setDate(null);
        rdokmdaketthuc.setSelected(false);
        rdokmdangdienra.setSelected(false);

    }//GEN-LAST:event_btnkmmoiActionPerformed

    private void btnkmluuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnkmluuActionPerformed
        // TODO add your handling code here:
        String alpha = "QWERTYUIOPASDFGHJKLZXCVBNM0987654321";
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        int length = 5;
        for (int j = 0; j < length; j++) {
            int ind = random.nextInt(alpha.length());
            char randomchar = alpha.charAt(ind);
            sb.append(randomchar);
        }
        String tenCT = txtkmtenct.getText();
        String mucGG = txtkmmucgg.getText();
        Date ngayBatDau = txtkmbatdau.getDate();
        Date ngayHetHan = txtkmketthuc.getDate();
        rdokmdangdienra.isSelected();

//        Boolean trangThai = rdokmdangdienra.isSelected
                
  Boolean trangThai = null;
        if (rdokmdangdienra.isSelected()) {
            trangThai = true;
        } else {
            trangThai = false;
        }
        
        String MaGG = sb.toString();
        String IDspct = (String) tblkmdssp.getValueAt(tblkmdssp.getSelectedRow(), 0);

        KhuyenMai km = new KhuyenMai();

        km.setMaGG(MaGG);
        km.setIdlgg(IDspct);
        km.setTenGG(tenCT);
        km.setNgayBatDau(ngayBatDau);
        km.setNgayHetHan(ngayHetHan);
        km.setGiaTri(Float.parseFloat(mucGG));
//        if (trangThai) {
//            rdokmdangdienra.setSelected(true);
//        } else {
//            rdokmdaketthuc.setSelected(true);
//        }
        boolean addKM = kmSer.ThemKhuyenMai(km);
        if (addKM) {
            JOptionPane.showMessageDialog(this, "Bạn có chắc muốn thêm Khuyến mãi không?");
            listKM = kmSer.gettAll();
            fillTableKhuyenMai(listKM);

        } else {
            JOptionPane.showMessageDialog(this, "Thêm lỗi");
        }


    }//GEN-LAST:event_btnkmluuActionPerformed

    private void rdokmdaketthucActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdokmdaketthucActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdokmdaketthucActionPerformed

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
            java.util.logging.Logger.getLogger(ViewKhuyenMai.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ViewKhuyenMai.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ViewKhuyenMai.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ViewKhuyenMai.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ViewKhuyenMai().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnkmluu;
    private javax.swing.JButton btnkmmoi;
    private javax.swing.JButton btnkmsua;
    private javax.swing.JButton btnkmtim;
    private javax.swing.JButton btnkmxoa;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cboLoaiApDung;
    private javax.swing.JComboBox<String> cbokmhinhthuc;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel108;
    private javax.swing.JLabel jLabel109;
    private javax.swing.JLabel jLabel110;
    private javax.swing.JLabel jLabel111;
    private javax.swing.JLabel jLabel112;
    private javax.swing.JLabel jLabel113;
    private javax.swing.JLabel jLabel114;
    private javax.swing.JLabel jLabel115;
    private javax.swing.JLabel jLabel116;
    private javax.swing.JPanel jPanel30;
    private javax.swing.JPanel jPanel31;
    private javax.swing.JPanel jPanel32;
    private javax.swing.JPanel jPanel33;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JScrollPane jScrollPane14;
    private javax.swing.JScrollPane jScrollPane15;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JPanel jpkhuyenmai;
    private javax.swing.JRadioButton rdokmdaketthuc;
    private javax.swing.JRadioButton rdokmdangdienra;
    private javax.swing.JTable tblkmdskm;
    private javax.swing.JTable tblkmdssp;
    private javax.swing.JTextField txtGhiChu;
    private com.toedter.calendar.JDateChooser txtkmbatdau;
    private javax.swing.JTextField txtkmden;
    private com.toedter.calendar.JDateChooser txtkmketthuc;
    private javax.swing.JTextField txtkmmucgg;
    private javax.swing.JTextField txtkmtenct;
    private javax.swing.JTextField txtkmtim;
    private javax.swing.JTextField txtkmtu;
    // End of variables declaration//GEN-END:variables

    private void fillTable(List<SanPhamChiTiet> list) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        mol = (DefaultTableModel) tblkmdssp.getModel();
        mol.setRowCount(0);
        for (SanPhamChiTiet sct : list) {
            Object[] toData = new Object[]{sct.getId(),
                spSer.findByID(sct.getIdsp()),
                clSer.findCL(sct.getIdcl()),
                plservice.SElectbyIDPL(sct.getIdpl()).getTen(),
                msSer.findMS(sct.getIdms()).getTen(),
                thSer.findTH(sct.getIdth()).getTen(),
                xxSer.findXX(sct.getIdxx()).getTen(),
                sct.getKichThuoc(), sct.getKhoiLuong(), sct.getSoLuong(), sct.getGia(), sct.getTrangThai()};
            mol.addRow(toData);
        }

    }

    private void fillTableKhuyenMai(List<KhuyenMai> listKM) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        mol = (DefaultTableModel) tblkmdskm.getModel();
        mol.setRowCount(0);
        for (KhuyenMai km : listKM) {
            Object[] toData2 = new Object[]{km.getMaGG(), km.getId(), km.getTenGG(), km.getNgayBatDau(), km.getNgayHetHan(), km.getGiaTri(), km.getGhiChu(), km.getTrangThai()};
            mol.addRow(toData2);
        }
    }

    void showBangKM(int index) {

        KhuyenMai km = kmSer.gettAll().get(index);
        txtkmtenct.setText(km.getTenGG());
        txtkmmucgg.setText(km.getGiaTri().toString());
        txtkmbatdau.setDate(km.getNgayBatDau());
        txtkmketthuc.setDate(km.getNgayHetHan());
        if (rdokmdangdienra.isSelected()) {
            rdokmdangdienra.setSelected(true);

        } else {
            rdokmdaketthuc.setSelected(true);
        }
    }

}
