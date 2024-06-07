
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
import com.sun.source.tree.BreakTree;
import java.awt.Color;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


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
        for(KhuyenMai x:kmSer.getHetHan()){
            service.checkMaGG(x.getMaGG());
        }
        DefaultComboBoxModel cbb = (DefaultComboBoxModel) cboLoaiApDung.getModel();
        cbb.removeAllElements();

        cboLoaiApDung.removeAllItems();
        cboLoaiApDung.addItem("Tất cả Loại Sản Phẩm");
        List<PhanLoai> listPL = plservice.gettAll();
        for (PhanLoai pl : listPL) {
            cbb.addElement(pl.getTen());
        }
        cbokmhinhthuc.removeAllItems();
        cbokmhinhthuc.addItem("Tất Cả ");
        cbokmhinhthuc.addItem("Đã có giảm giá");
        cbokmhinhthuc.addItem("Chưa có giảm giá");

//checkValidate();
        cboKhuyenMai.removeAllItems();
        cboKhuyenMai.addItem("Đã áp dụng ");
        cboKhuyenMai.addItem("Chưa áp dụng ");
        cboKhuyenMai.addItem("Tất cả Mã Giảm giá ");
        XetHetHan();
        this.fillTable(service.gettAll());
        this.fillTableKhuyenMai(kmSer.gettAll());

    }
    
    private void XetHetHan() {
        // Lấy ngày hiện tại
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        boolean xethethan = kmSer.XetHetHan(dateFormat.format(date));
        
    }

    public void Timkiem() {
        String checkmagg = "";
        String tePl = "";

        if (cboLoaiApDung.getSelectedIndex() == 0) {
            tePl = "";

        } else {
            tePl = (String) cboLoaiApDung.getSelectedItem();

        }

        if (((String) cbokmhinhthuc.getSelectedItem()) == null || (cbokmhinhthuc.getSelectedIndex() == 0)) {
            checkmagg = "";
        } else if (cbokmhinhthuc.getSelectedIndex() == 1) {
            checkmagg = " and MaGG Is not null";
        } else {
            checkmagg = " and MaGG is null";
        }
        mol = (DefaultTableModel) tblkmdssp.getModel();
        mol.setRowCount(0);
        for (SanPhamChiTiet sct : service.TimKiem(tePl, checkmagg)) {

            Object[] toData = new Object[]{sct.getId(),
                spSer.findByID(sct.getIdsp()).getTenSP(),
                clSer.findCL(sct.getIdcl()),
                plservice.SElectbyIDPL(sct.getIdpl()).getTen(),
                msSer.findMS(sct.getIdms()).getTen(),
                thSer.findTH(sct.getIdth()).getTen(),
                xxSer.findXX(sct.getIdxx()).getTen(),
                sct.getKichThuoc(), sct.getKhoiLuong(), sct.getSoLuong(), sct.getGia(),
                sct.getMaGG()};
            mol.addRow(toData);

        }
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
        jLabel116 = new javax.swing.JLabel();
        cbokmhinhthuc = new javax.swing.JComboBox<>();
        btnxemgg = new javax.swing.JButton();
        jPanel31 = new javax.swing.JPanel();
        jScrollPane14 = new javax.swing.JScrollPane();
        tblkmdskm = new javax.swing.JTable();
        txtTimKiem = new javax.swing.JTextField();
        btnkmTim = new javax.swing.JButton();
        cboKhuyenMai = new javax.swing.JComboBox<>();
        btnXemMaDaAD = new javax.swing.JButton();
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
        btnApDung = new javax.swing.JButton();
        txtkmmucgg = new javax.swing.JTextField();
        txtkmtenct = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        txtGhiChu = new javax.swing.JTextField();
        txtkmbatdau = new com.toedter.calendar.JDateChooser();
        txtkmketthuc = new com.toedter.calendar.JDateChooser();
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
                "ID", "Tên ", "Chất Liệu", "Phân Loại", "Màu Sắc", "THương Hiệu", "Xuất Xứ", "Kích thước", "Khối lượng", "Số Lượng", "Giá", "MaGG"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, true, true, true, true, true, false, true, true, true, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblkmdssp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblkmdsspMouseClicked(evt);
            }
        });
        jScrollPane15.setViewportView(tblkmdssp);

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

        jLabel116.setText("Hình thức");

        cbokmhinhthuc.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbokmhinhthuc.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbokmhinhthucItemStateChanged(evt);
            }
        });
        cbokmhinhthuc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbokmhinhthucActionPerformed(evt);
            }
        });

        btnxemgg.setText("Xem giảm giá");
        btnxemgg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnxemggActionPerformed(evt);
            }
        });

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
                        .addGap(37, 37, 37)
                        .addComponent(btnxemgg)
                        .addGap(156, 156, 156))
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
                    .addComponent(jLabel116)
                    .addComponent(cbokmhinhthuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnxemgg))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane15, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        jPanel31.setBorder(javax.swing.BorderFactory.createTitledBorder("Danh sách khuyến mãi"));

        tblkmdskm.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã Khuyến Mại", "Tên ", "Ngày Bắt Đầu", "Ngày Hết Hạn", "Giá Trị", "Ghi Chú", "Trạng Thái"
            }
        ));
        tblkmdskm.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblkmdskmMouseClicked(evt);
            }
        });
        jScrollPane14.setViewportView(tblkmdskm);

        btnkmTim.setText("Tìm kiếm");
        btnkmTim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnkmTimActionPerformed(evt);
            }
        });

        cboKhuyenMai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cboKhuyenMai.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboKhuyenMaiItemStateChanged(evt);
            }
        });

        btnXemMaDaAD.setText("Xem Sản Phẩm đã áp dụng");
        btnXemMaDaAD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXemMaDaADActionPerformed(evt);
            }
        });

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
                .addComponent(cboKhuyenMai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnXemMaDaAD)
                .addGap(34, 34, 34)
                .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnkmTim)
                .addGap(21, 21, 21))
        );
        jPanel31Layout.setVerticalGroup(
            jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel31Layout.createSequentialGroup()
                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel31Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnkmTim)))
                    .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cboKhuyenMai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnXemMaDaAD)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
        btnkmsua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnkmsuaActionPerformed(evt);
            }
        });

        btnkmxoa.setText("Xóa");
        btnkmxoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnkmxoaActionPerformed(evt);
            }
        });

        btnApDung.setText("Áp Dụng");
        btnApDung.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnApDungActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel33Layout = new javax.swing.GroupLayout(jPanel33);
        jPanel33.setLayout(jPanel33Layout);
        jPanel33Layout.setHorizontalGroup(
            jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel33Layout.createSequentialGroup()
                .addGroup(jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(btnkmmoi, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel33Layout.createSequentialGroup()
                        .addGroup(jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnkmluu, javax.swing.GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE)
                            .addComponent(btnkmxoa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnApDung, javax.swing.GroupLayout.DEFAULT_SIZE, 107, Short.MAX_VALUE)
                            .addComponent(btnkmsua, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(38, Short.MAX_VALUE))
        );
        jPanel33Layout.setVerticalGroup(
            jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel33Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnkmluu, javax.swing.GroupLayout.DEFAULT_SIZE, 72, Short.MAX_VALUE)
                    .addComponent(btnApDung, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addGroup(jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnkmsua, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnkmxoa, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btnkmmoi, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        txtkmtenct.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtkmtenctMouseClicked(evt);
            }
        });

        jLabel1.setText("Ghi Chú");

        javax.swing.GroupLayout jPanel32Layout = new javax.swing.GroupLayout(jPanel32);
        jPanel32.setLayout(jPanel32Layout);
        jPanel32Layout.setHorizontalGroup(
            jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
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
                        .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtkmmucgg)
                            .addComponent(txtkmtenct)
                            .addComponent(txtkmbatdau, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtkmketthuc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
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
                        .addComponent(rdokmdaketthuc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(110, 110, 110))
            .addGroup(jPanel32Layout.createSequentialGroup()
                .addComponent(jPanel33, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel111)
                    .addComponent(txtkmbatdau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel112)
                    .addComponent(txtkmketthuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(50, 50, 50)
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel32, javax.swing.GroupLayout.PREFERRED_SIZE, 314, Short.MAX_VALUE)
                .addContainerGap())
        );
        jpkhuyenmaiLayout.setVerticalGroup(
            jpkhuyenmaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpkhuyenmaiLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jPanel30, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel31, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(jPanel32, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jTextField1.setText("jTextField1");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(93, 93, 93))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jpkhuyenmai, javax.swing.GroupLayout.DEFAULT_SIZE, 1023, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jpkhuyenmai, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cboLoaiApDungActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboLoaiApDungActionPerformed

    }//GEN-LAST:event_cboLoaiApDungActionPerformed

    private void cboLoaiApDungItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboLoaiApDungItemStateChanged
        Timkiem();
    }//GEN-LAST:event_cboLoaiApDungItemStateChanged

    private void tblkmdskmMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblkmdskmMouseClicked
        int index = tblkmdskm.getSelectedRow();
        showBangKM(index);
    }//GEN-LAST:event_tblkmdskmMouseClicked

    private void btnkmmoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnkmmoiActionPerformed
        txtkmtenct.setText("");
        txtkmmucgg.setText("");
        txtkmbatdau.setDate(null);
        txtkmketthuc.setDate(null);
        rdokmdaketthuc.setSelected(false);
        rdokmdangdienra.setSelected(false);
    }//GEN-LAST:event_btnkmmoiActionPerformed

    private void btnkmluuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnkmluuActionPerformed
        boolean check = checkTrong();
        try {
            if (check) {
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

//        rdokmdangdienra.isSelected();
//        Boolean trangThai = rdokmdangdienra.isSelected
                Boolean trangThai = null;
                if (rdokmdangdienra.isSelected()) {
                    trangThai = true;
                } else {
                    trangThai = false;
                }

                String ghiChu = txtGhiChu.getText();
                String MaGG = sb.toString();
//        String IDspct = (String) tblkmdssp.getValueAt(tblkmdssp.getSelectedRow(), 0);

                KhuyenMai km = new KhuyenMai();

                km.setMaGG(MaGG);
//        km.setIdlgg(IDspct);
                km.setGhiChu(ghiChu);
                km.setTenGG(tenCT);
                km.setNgayBatDau(ngayBatDau);
                km.setNgayHetHan(ngayHetHan);
                km.setGiaTri(Float.parseFloat(mucGG));
                {
                    boolean addKM = kmSer.ThemKhuyenMai(km);
                    if (addKM) {
                        JOptionPane.showMessageDialog(this, "Thêm thành công");
                        listKM = kmSer.gettAll();
                        fillTableKhuyenMai(listKM);

                    } else {
                        JOptionPane.showMessageDialog(this, "Thêm lỗi");

                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("333");

        }


    }//GEN-LAST:event_btnkmluuActionPerformed

    public boolean checkTrong() {
        boolean x = true;
        {
            String tenCT = txtkmtenct.getText();
            txtkmtenct.setBackground(Color.white);
            txtkmmucgg.setBackground(Color.white);
            txtkmbatdau.setBackground(Color.white);
            txtkmketthuc.setBackground(Color.white);
            if (tenCT.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Tên không được để trống");
                txtkmtenct.setBackground(Color.red);
                x = false;
            }
            if (txtkmmucgg.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Giá trị không được để trống");
                txtkmmucgg.setBackground(Color.red);
                x = false;
            }
            if (Float.parseFloat(txtkmmucgg.getText()) <= 0) {
                JOptionPane.showMessageDialog(this, "Giá trị phải lớn hơn 0");
                txtkmmucgg.setBackground(Color.red);
                x = false;
            }

            if (txtkmbatdau.getDate() == null) {
                JOptionPane.showMessageDialog(this, "Ko được để trống ngày");
                txtkmbatdau.setBackground(Color.red);
                x = false;
            }
            if (txtkmketthuc.getDate() == null) {
                JOptionPane.showMessageDialog(this, "Ko được để trống ngày");
                txtkmketthuc.setBackground(Color.red);
                x = false;
            }
            if (txtkmketthuc.getDate().before(txtkmbatdau.getDate())) {
                JOptionPane.showMessageDialog(this, "Ngày bắt đầu phải sau ngày kết thúc");
                txtkmketthuc.setBackground(Color.red);
                x = false;
            } else {
            }
        }

        return x;
    }


    private void rdokmdaketthucActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdokmdaketthucActionPerformed

    }//GEN-LAST:event_rdokmdaketthucActionPerformed

    private void cbokmhinhthucItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbokmhinhthucItemStateChanged
        Timkiem();
    }//GEN-LAST:event_cbokmhinhthucItemStateChanged

    private void tblkmdsspMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblkmdsspMouseClicked

    }//GEN-LAST:event_tblkmdsspMouseClicked

    private void cboKhuyenMaiItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboKhuyenMaiItemStateChanged
        int i = cboKhuyenMai.getSelectedIndex();
        if (i == 2) {
            fillTableKhuyenMai(kmSer.gettAll());
//        } else {
//            fillTable(service.gettAllChuaGG());
        }
    }//GEN-LAST:event_cboKhuyenMaiItemStateChanged

    private void btnkmsuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnkmsuaActionPerformed
        suaKM();
        
    }//GEN-LAST:event_btnkmsuaActionPerformed

    private void btnkmxoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnkmxoaActionPerformed
        int index = tblkmdskm.getSelectedRow();
        xoaKM(index);
    }//GEN-LAST:event_btnkmxoaActionPerformed

    private void btnXemMaDaADActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXemMaDaADActionPerformed
        int index = tblkmdskm.getSelectedRow();
        String magg = (String) tblkmdskm.getValueAt(index, 0);
        fillTable(service.findByMaGG(magg));
    }//GEN-LAST:event_btnXemMaDaADActionPerformed

    private void btnApDungActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnApDungActionPerformed

        int index = tblkmdskm.getSelectedRow();
        int index2 = tblkmdssp.getSelectedRow();
        String xgetMagg = (String) tblkmdskm.getValueAt(index, 0);

        String idSP = (String) tblkmdssp.getValueAt(index2, 0);

        SanPhamChiTiet spct = new SanPhamChiTiet();
        spct.setMaGG(xgetMagg);
        spct.setId(idSP);
        if(tblkmdskm.getValueAt(index, 6).toString().equals("Đang diễn ra")){
            if (service.ApDungMaGGtoSPCT(spct)) {
                fillTable(service.gettAll());
                JOptionPane.showMessageDialog(this, "Áp dụng mã thành công");
            } else {
                JOptionPane.showMessageDialog(this, "Áp dụng thất bại");
            }
        }else {
            JOptionPane.showMessageDialog(this, "Chương trình đã kết thúc");
        }
        


    }//GEN-LAST:event_btnApDungActionPerformed

    private void btnkmTimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnkmTimActionPerformed
        timKiem();
    }//GEN-LAST:event_btnkmTimActionPerformed

    private void btnxemggActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnxemggActionPerformed
        int i = tblkmdssp.getSelectedRow();
        String magg = (String) tblkmdssp.getValueAt(i, 11);
        fillTableKhuyenMai(kmSer.findBySPCT(magg));
    }//GEN-LAST:event_btnxemggActionPerformed

    private void cbokmhinhthucActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbokmhinhthucActionPerformed

    }//GEN-LAST:event_cbokmhinhthucActionPerformed

    private void txtkmtenctMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtkmtenctMouseClicked
        txtkmtenct.setBackground(Color.white);
    }//GEN-LAST:event_txtkmtenctMouseClicked

    public void timKiem() {
        mol = (DefaultTableModel) tblkmdskm.getModel();
        mol.setRowCount(0);
        String keyword = txtTimKiem.getText();
        List<KhuyenMai> kmList = kmSer.TimKiem(keyword);
        for (KhuyenMai km : kmList) {
            Object[] toData = new Object[]{km.getMaGG(), km.getTenGG(), km.getNgayBatDau(), km.getNgayHetHan(), km.getGiaTri(), km.getGhiChu(), km.getTrangThai()};

            mol.addRow(toData);
        }
    }

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ViewKhuyenMai().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnApDung;
    private javax.swing.JButton btnXemMaDaAD;
    private javax.swing.JButton btnkmTim;
    private javax.swing.JButton btnkmluu;
    private javax.swing.JButton btnkmmoi;
    private javax.swing.JButton btnkmsua;
    private javax.swing.JButton btnkmxoa;
    private javax.swing.JButton btnxemgg;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cboKhuyenMai;
    private javax.swing.JComboBox<String> cboLoaiApDung;
    private javax.swing.JComboBox<String> cbokmhinhthuc;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel108;
    private javax.swing.JLabel jLabel109;
    private javax.swing.JLabel jLabel110;
    private javax.swing.JLabel jLabel111;
    private javax.swing.JLabel jLabel112;
    private javax.swing.JLabel jLabel113;
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
    private javax.swing.JTextField txtTimKiem;
    private com.toedter.calendar.JDateChooser txtkmbatdau;
    private com.toedter.calendar.JDateChooser txtkmketthuc;
    private javax.swing.JTextField txtkmmucgg;
    private javax.swing.JTextField txtkmtenct;
    // End of variables declaration//GEN-END:variables

    private void fillTable(List<SanPhamChiTiet> list) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        mol = (DefaultTableModel) tblkmdssp.getModel();
        mol.setRowCount(0);
        for (SanPhamChiTiet sct : list) {
            Object[] toData = new Object[]{sct.getId(),
                spSer.findByID(sct.getIdsp()).getTenSP(),
                clSer.findCL(sct.getIdcl()),
                plservice.SElectbyIDPL(sct.getIdpl()).getTen(),
                msSer.findMS(sct.getIdms()).getTen(),
                thSer.findTH(sct.getIdth()).getTen(),
                xxSer.findXX(sct.getIdxx()).getTen(),
                sct.getKichThuoc(), sct.getKhoiLuong(), sct.getSoLuong(), sct.getGia(),
                sct.getMaGG()};
            mol.addRow(toData);
        }

    }

    private void fillTableKhuyenMai(List<KhuyenMai> listKM) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        mol = (DefaultTableModel) tblkmdskm.getModel();
        mol.setRowCount(0);
        for (KhuyenMai km : listKM) {
            Object[] toData2 = new Object[]{km.getMaGG(), km.getTenGG(), km.getNgayBatDau(), km.getNgayHetHan(), km.getGiaTri(), km.getGhiChu(), km.getTrangThai() ? "Đã kết thúc" : "Đang diễn ra"};
            mol.addRow(toData2);
        }
        
    }

    void showBangKM(int index) {

        KhuyenMai km = kmSer.gettAll().get(index);
        txtkmtenct.setText(km.getTenGG());
        txtkmmucgg.setText(km.getGiaTri().toString());
        txtkmbatdau.setDate(km.getNgayBatDau());
        txtkmketthuc.setDate(km.getNgayHetHan());
        if (!km.getTrangThai()) {
            rdokmdangdienra.setSelected(true);

        } else {
            rdokmdaketthuc.setSelected(true);
        }
    }

    private void suaKM() {

        String tenCT = txtkmtenct.getText();
//        String mucGG = txtkmmucgg.getText();
        Date ngayBatDau = txtkmbatdau.getDate();
        Date ngayHetHan = txtkmketthuc.getDate();
        float giaTri = Float.parseFloat(txtkmmucgg.getText());
        Boolean trangThai = null;
        if (rdokmdangdienra.isSelected()) {
            trangThai = true;
        } else {
            trangThai = false;
        }
        String ghiChu = txtGhiChu.getText();

        String MaGG = (String) tblkmdskm.getValueAt(tblkmdskm.getSelectedRow(), 0);
        KhuyenMai km = new KhuyenMai();

        km.setMaGG(MaGG);
        km.setGiaTri(giaTri);
        km.setGhiChu(ghiChu);
        km.setTenGG(tenCT);
        km.setNgayBatDau(ngayBatDau);
        km.setNgayHetHan(ngayHetHan);

//        km.setGiaTri(Float.parseFloat(mucGG));
        if (kmSer.UpdateKM(km)) {
            JOptionPane.showMessageDialog(this, "Sửa thành công");
            XetHetHan();
            fillTableKhuyenMai(kmSer.gettAll());
        } else {
            JOptionPane.showMessageDialog(this, "Sửa thất bại");
        }

    }

    private void xoaKM(int index) {
        DefaultTableModel model = (DefaultTableModel) tblkmdskm.getModel();
        String maXoa = tblkmdskm.getValueAt(tblkmdskm.getSelectedRow(), 0) + "";
        KhuyenMai km = new KhuyenMai();
        km.setMaGG(maXoa);
        try {
            kmSer.XoaKhuyenMai(km);
            JOptionPane.showMessageDialog(this, "Xóa thành công");
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Xóa thất bại");

        }
        fillTableKhuyenMai(kmSer.gettAll());
    }

//        if (txtkmtenct.getText().isEmpty() || txtkmmucgg.getText().isEmpty() ) {
//            JOptionPane.showMessageDialog(this, "Không được để trống thông tin");
//           
//        }
//
//        return true;
}
