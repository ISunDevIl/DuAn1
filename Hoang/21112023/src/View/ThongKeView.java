package View;

import Model.SanPhamChiTiet;
import Service.SPCTService;
import Service.ThongKeService;
import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.DecimalFormat;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.data.category.DefaultCategoryDataset;

public class ThongKeView extends javax.swing.JFrame {

    DefaultTableModel tblModel = new DefaultTableModel();
    SPCTService spctService = new SPCTService();
    ThongKeService tkService = new ThongKeService();
    private int page = 1;

    public ThongKeView() {
        initComponents();
        init();
    }

    public void init() {
        setLocationRelativeTo(null);
        initTableSPCT();
        fillTableSPSapHet();
        setPageSPSapHet();
        fillTongDonHangNgay();
        fillThongKeNgay();
        fillThongKeThang();
        fillThongKeNam();
        getNam();

    }

    public void initTableSPCT() {
        tblModel = new DefaultTableModel();
        String[] cols = new String[]{
            "ID_SPCT", "ID_SP", "ID_CL", "ID_PL", "ID_TH", "ID_XX", "ID_MS", "Kich Co", "Khoi Luong", "Số lượng", "Gia"
        };
        tblModel.setColumnIdentifiers(cols);
        tblSPCT.setModel(tblModel);
    }

    public void fillTableSPSapHet() {
        tblModel = (DefaultTableModel) tblSPCT.getModel();
        tblModel.setRowCount(0);
        List<SanPhamChiTiet> list = spctService.gettAllSPSapHetPage(page);
        for (SanPhamChiTiet spct : list) {
            Object[] rows = {
                spct.getId(),
                spct.getIdsp(),
                spct.getIdcl(),
                spct.getIdpl(),
                spct.getIdth(),
                spct.getIdxx(),
                spct.getIdms(),
                spct.getKichThuoc(),
                spct.getKhoiLuong(),
                spct.getSoLuong(),
                spct.getGia(),};
            tblModel.addRow(rows);
        }
    }

    public void fillTableDoanhThu() {
        DecimalFormat df = new DecimalFormat("#########");
        tblModel = (DefaultTableModel) tblDoanhThu.getModel();
        tblModel.setRowCount(0);
        int year = (int) cboNam.getSelectedItem();
        for (int i = 1; i <= 12; i++) {
            double doanhThu = tkService.getDoanhThuThang(year, i) - tkService.getTongGiaGiamThang(year, i);
            Object[] rows = {
                i,
                tkService.getSoLuongSPThang(year, i),
                df.format(tkService.getDoanhThuThang(year, i)),
                df.format(tkService.getTongGiaGiamThang(year, i)),
                df.format(doanhThu)
            };
            tblModel.addRow(rows);
        }
    }

    public void fillTongDonHangNgay() {
        lblTongDH.setText(tkService.getDonHangNgayHienTai() + " " + "Đơn");
        lblDHThanhCong.setText(tkService.getDonHangNgayThanhCong() + " " + "Đơn");
        lblDHHuy.setText(tkService.getDonHangNgayBiHuy() + " " + "Đơn");
    }

    public void fillThongKeNgay() {
        DecimalFormat x = new DecimalFormat("###,###,###");
        lblHDTCNgay.setText(tkService.getDonHangNgayThanhCong() + " " + "Đơn");
        lblDHHuyNgay.setText(tkService.getDonHangNgayBiHuy() + " " + "Đơn");
        lblDoanhThuNgay.setText(x.format(tkService.getDoanhThuNgayHienTai()) + " " + "VNĐ");
    }

    public void fillThongKeThang() {
        DecimalFormat x = new DecimalFormat("###,###,###");
        lblHDTCThang.setText(tkService.getDonHangThangHienTaiThanhCong() + " " + "Đơn");
        lblDHHuyThang.setText(tkService.getDonHangThangHienTaiBiHuy() + " " + "Đơn");
        lblDoanhThuThang.setText(x.format(tkService.getDoanhThuThangHienTai()) + " " + "VNĐ");
    }

    public void fillThongKeNam() {
        DecimalFormat x = new DecimalFormat("###,###,###");
        lblHDTCNam.setText(tkService.getDonHangNamHienTaiThanhCong() + " " + "Đơn");
        lblDHHuyNam.setText(tkService.getDonHangNamHienTaiBiHuy() + " " + "Đơn");
        lblDoanhThuNam.setText(x.format(tkService.getDoanhThuNamHienTai()) + " " + "VNĐ");
    }

    public void getNam() {
        DefaultComboBoxModel cbo = (DefaultComboBoxModel) cboNam.getModel();
        cbo.removeAllElements();
        List<Integer> listNam = tkService.getYear();
        for (Integer nam : listNam) {
            cbo.addElement(nam);
        }
    }

    public void setPageSPSapHet() {
        lblPage.setText(String.valueOf(page));
        int tongItem = spctService.gettAllSPSapHet().size();
        int itemPage = 5;
        boolean first = page == 1;
        boolean last = page == (tongItem + itemPage - 1) / itemPage;
        btnFirst.setEnabled(!first);
        btnPrev.setEnabled(!first);
        btnNext.setEnabled(!last);
        btnLast.setEnabled(!last);
        this.fillTableSPSapHet();
    }

    public String formatDouble(String txt) {
        String pattern = txt;
        return pattern.replaceAll(",", "");
    }

    public void setBieuDo() {
        int row = tblDoanhThu.getRowCount();
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (int i = 0; i < row; i++) {
            String thang = tblDoanhThu.getValueAt(i, 0).toString();
            String doanhThuStr = tblDoanhThu.getValueAt(i, 4).toString();

            try {
                double doanhThu = Double.parseDouble(doanhThuStr);
                dataset.addValue(doanhThu, "", thang);
            } catch (NumberFormatException e) {
                // Xử lý lỗi chuyển đổi số
                System.err.println("Lỗi chuyển đổi số tại dòng " + (i + 1) + ": " + doanhThuStr);
            }
        }
        JFreeChart chart = ChartFactory.createBarChart("Biểu Đồ", "Thời Gian", "Doanh Thu", dataset);
        CategoryPlot plot = chart.getCategoryPlot();
        plot.setRangeGridlinePaint(Color.BLACK);
        ChartFrame cf = new ChartFrame("Biểu đồ doanh thu", chart);
        cf.setVisible(true);
        cf.setSize(600, 450);
        cf.setLocationRelativeTo(null);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jpthongke = new javax.swing.JPanel();
        jPanel20 = new javax.swing.JPanel();
        jLabel66 = new javax.swing.JLabel();
        lblDoanhThuNgay = new javax.swing.JLabel();
        jLabel68 = new javax.swing.JLabel();
        jLabel69 = new javax.swing.JLabel();
        lblHDTCNgay = new javax.swing.JLabel();
        lblDHHuyNgay = new javax.swing.JLabel();
        jPanel21 = new javax.swing.JPanel();
        jLabel71 = new javax.swing.JLabel();
        lblDoanhThuNam = new javax.swing.JLabel();
        jLabel73 = new javax.swing.JLabel();
        jLabel74 = new javax.swing.JLabel();
        lblHDTCNam = new javax.swing.JLabel();
        lblDHHuyNam = new javax.swing.JLabel();
        jPanel22 = new javax.swing.JPanel();
        jLabel77 = new javax.swing.JLabel();
        lblDoanhThuThang = new javax.swing.JLabel();
        jLabel79 = new javax.swing.JLabel();
        jLabel80 = new javax.swing.JLabel();
        lblHDTCThang = new javax.swing.JLabel();
        lblDHHuyThang = new javax.swing.JLabel();
        jPanel23 = new javax.swing.JPanel();
        jLabel83 = new javax.swing.JLabel();
        lblTongDH = new javax.swing.JLabel();
        jLabel85 = new javax.swing.JLabel();
        jLabel86 = new javax.swing.JLabel();
        lblDHThanhCong = new javax.swing.JLabel();
        lblDHHuy = new javax.swing.JLabel();
        jTabbedPane3 = new javax.swing.JTabbedPane();
        jPanel24 = new javax.swing.JPanel();
        jLabel92 = new javax.swing.JLabel();
        cboNam = new javax.swing.JComboBox<>();
        jPanel26 = new javax.swing.JPanel();
        jLabel93 = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        tblDoanhThu = new javax.swing.JTable();
        btnXuatBaoCao = new javax.swing.JButton();
        btnBieuDo = new javax.swing.JButton();
        jPanel25 = new javax.swing.JPanel();
        jLabel94 = new javax.swing.JLabel();
        jScrollPane11 = new javax.swing.JScrollPane();
        tblSPCT = new javax.swing.JTable();
        btnFirst = new javax.swing.JButton();
        btnPrev = new javax.swing.JButton();
        lblPage = new javax.swing.JLabel();
        btnNext = new javax.swing.JButton();
        btnLast = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jpthongke.setBackground(new java.awt.Color(0, 102, 255));
        jpthongke.setPreferredSize(new java.awt.Dimension(919, 654));

        jPanel20.setPreferredSize(new java.awt.Dimension(202, 149));

        jLabel66.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel66.setText("Tổng doanh thu ngày");

        lblDoanhThuNgay.setText("0 VNĐ");

        jLabel68.setText("Thành công");

        jLabel69.setText("Bị hủy");

        lblHDTCNgay.setText("0");

        lblDHHuyNgay.setText("0");

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel66, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(lblDoanhThuNgay, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel20Layout.createSequentialGroup()
                            .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel68, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel69, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(lblHDTCNgay, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblDHHuyNgay, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(44, Short.MAX_VALUE))
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel66)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblDoanhThuNgay)
                .addGap(18, 18, 18)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel68)
                    .addComponent(lblHDTCNgay))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel69)
                    .addComponent(lblDHHuyNgay))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel21.setPreferredSize(new java.awt.Dimension(202, 149));

        jLabel71.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel71.setText("Tổng doanh thu năm");

        lblDoanhThuNam.setText("0 VNĐ");

        jLabel73.setText("Thành công");

        jLabel74.setText("Bị hủy");

        lblHDTCNam.setText("0");

        lblDHHuyNam.setText("0");

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel71, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(lblDoanhThuNam, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel21Layout.createSequentialGroup()
                            .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel73, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel74, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(lblHDTCNam, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblDHHuyNam, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(44, Short.MAX_VALUE))
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel71)
                .addGap(12, 12, 12)
                .addComponent(lblDoanhThuNam)
                .addGap(18, 18, 18)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel73)
                    .addComponent(lblHDTCNam))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel74)
                    .addComponent(lblDHHuyNam))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        jPanel22.setPreferredSize(new java.awt.Dimension(202, 149));

        jLabel77.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel77.setText("Tổng doanh thu tháng");

        lblDoanhThuThang.setText("0 VNĐ");

        jLabel79.setText("Thành công");

        jLabel80.setText("Bị hủy");

        lblHDTCThang.setText("0");

        lblDHHuyThang.setText("0");

        javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
        jPanel22.setLayout(jPanel22Layout);
        jPanel22Layout.setHorizontalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel77, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(lblDoanhThuThang, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel22Layout.createSequentialGroup()
                            .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel79, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel80, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(lblHDTCThang, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblDHHuyThang, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(44, Short.MAX_VALUE))
        );
        jPanel22Layout.setVerticalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel77)
                .addGap(12, 12, 12)
                .addComponent(lblDoanhThuThang)
                .addGap(18, 18, 18)
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel79)
                    .addComponent(lblHDTCThang))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel80)
                    .addComponent(lblDHHuyThang))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        jPanel23.setPreferredSize(new java.awt.Dimension(202, 149));

        jLabel83.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel83.setText("Tổng đơn hàng ngày");

        lblTongDH.setText("0 đơn hàng");

        jLabel85.setText("Thành công");

        jLabel86.setText("Bị hủy");

        lblDHThanhCong.setText("0");

        lblDHHuy.setText("0");

        javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
        jPanel23.setLayout(jPanel23Layout);
        jPanel23Layout.setHorizontalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel23Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel85, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel86, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblDHThanhCong, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblDHHuy, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel23Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(lblTongDH, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel23Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel83)))
                .addContainerGap(44, Short.MAX_VALUE))
        );
        jPanel23Layout.setVerticalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel83)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblTongDH)
                .addGap(18, 18, 18)
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel85)
                    .addComponent(lblDHThanhCong))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel86)
                    .addComponent(lblDHHuy))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel92.setText("Năm");

        cboNam.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cboNam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboNamActionPerformed(evt);
            }
        });

        jPanel26.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 1, 0, 0, new java.awt.Color(0, 0, 0)));

        jLabel93.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel93.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel93.setText("Chi tiết doanh thu");

        tblDoanhThu.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Tháng", "Sản phẩm bán", "Tổng giá bán", "Tổng giá giảm", "Doanh thu"
            }
        ));
        jScrollPane6.setViewportView(tblDoanhThu);

        javax.swing.GroupLayout jPanel26Layout = new javax.swing.GroupLayout(jPanel26);
        jPanel26.setLayout(jPanel26Layout);
        jPanel26Layout.setHorizontalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel26Layout.createSequentialGroup()
                .addGap(269, 269, 269)
                .addComponent(jLabel93, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(282, Short.MAX_VALUE))
            .addComponent(jScrollPane6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 694, Short.MAX_VALUE)
        );
        jPanel26Layout.setVerticalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel26Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel93, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(50, Short.MAX_VALUE))
        );

        btnXuatBaoCao.setBackground(new java.awt.Color(0, 153, 153));
        btnXuatBaoCao.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnXuatBaoCao.setForeground(new java.awt.Color(255, 255, 255));
        btnXuatBaoCao.setText("Xuất báo cáo");
        btnXuatBaoCao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXuatBaoCaoActionPerformed(evt);
            }
        });

        btnBieuDo.setBackground(new java.awt.Color(204, 153, 0));
        btnBieuDo.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnBieuDo.setForeground(new java.awt.Color(255, 255, 255));
        btnBieuDo.setText("Biểu đồ");
        btnBieuDo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBieuDoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel24Layout = new javax.swing.GroupLayout(jPanel24);
        jPanel24.setLayout(jPanel24Layout);
        jPanel24Layout.setHorizontalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnBieuDo, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXuatBaoCao)
                    .addComponent(jLabel92, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboNam, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addComponent(jPanel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel24Layout.setVerticalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addComponent(jLabel92)
                .addGap(18, 18, 18)
                .addComponent(cboNam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41)
                .addComponent(btnBieuDo, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addComponent(btnXuatBaoCao, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jPanel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jTabbedPane3.addTab("Doanh thu", jPanel24);

        jLabel94.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel94.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel94.setText("Thông tin chi tiết sản phẩm sắp hết hàng");

        tblSPCT.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane11.setViewportView(tblSPCT);

        btnFirst.setText("|<");
        btnFirst.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFirstActionPerformed(evt);
            }
        });

        btnPrev.setText("<<");
        btnPrev.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrevActionPerformed(evt);
            }
        });

        lblPage.setText("    1");

        btnNext.setText(">>");
        btnNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextActionPerformed(evt);
            }
        });

        btnLast.setText(">|");
        btnLast.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLastActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel25Layout = new javax.swing.GroupLayout(jPanel25);
        jPanel25.setLayout(jPanel25Layout);
        jPanel25Layout.setHorizontalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane11, javax.swing.GroupLayout.DEFAULT_SIZE, 879, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addGap(299, 299, 299)
                .addComponent(jLabel94)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel25Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnFirst)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnPrev)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblPage, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnNext)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnLast)
                .addGap(23, 23, 23))
        );
        jPanel25Layout.setVerticalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jLabel94)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnFirst)
                    .addComponent(btnPrev)
                    .addComponent(lblPage)
                    .addComponent(btnNext)
                    .addComponent(btnLast))
                .addContainerGap(26, Short.MAX_VALUE))
        );

        jTabbedPane3.addTab("Sản phẩm", jPanel25);

        javax.swing.GroupLayout jpthongkeLayout = new javax.swing.GroupLayout(jpthongke);
        jpthongke.setLayout(jpthongkeLayout);
        jpthongkeLayout.setHorizontalGroup(
            jpthongkeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpthongkeLayout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jpthongkeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTabbedPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jpthongkeLayout.createSequentialGroup()
                        .addComponent(jPanel23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jpthongkeLayout.setVerticalGroup(
            jpthongkeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpthongkeLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jpthongkeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel21, javax.swing.GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE)
                    .addComponent(jPanel20, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE)
                    .addComponent(jPanel23, javax.swing.GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE)
                    .addComponent(jPanel22, javax.swing.GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE))
                .addGap(41, 41, 41)
                .addComponent(jTabbedPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 402, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jpthongke, javax.swing.GroupLayout.DEFAULT_SIZE, 926, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jpthongke, javax.swing.GroupLayout.DEFAULT_SIZE, 632, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnXuatBaoCaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXuatBaoCaoActionPerformed
        try {
            XSSFWorkbook worbook = new XSSFWorkbook();
            XSSFSheet sheet = worbook.createSheet("Doanh Thu");

            XSSFRow row = null;
            Cell cell = null;

            row = sheet.createRow(3);
            cell = row.createCell(0, CellType.NUMERIC);
            cell.setCellValue("STT");

            cell = row.createCell(1, CellType.NUMERIC);
            cell.setCellValue("Tháng");

            cell = row.createCell(2, CellType.STRING);
            cell.setCellValue("Tổng sản phẩm bán ra");

            cell = row.createCell(3, CellType.STRING);
            cell.setCellValue("Tổng giá bán");

            cell = row.createCell(4, CellType.STRING);
            cell.setCellValue("Tổng giá giảm");

            cell = row.createCell(5, CellType.STRING);
            cell.setCellValue("Doanh thu");
            
            int s = tblDoanhThu.getRowCount();

            for (int i = 0; i < s; i++) {

                row = sheet.createRow(4 + i);

                cell = row.createCell(0, CellType.NUMERIC);
                cell.setCellValue(i + 1);
                
                cell = row.createCell(1, CellType.NUMERIC);
                cell.setCellValue(tblDoanhThu.getValueAt(i, 0).toString());

                cell = row.createCell(2, CellType.STRING);
                cell.setCellValue(tblDoanhThu.getValueAt(i, 1).toString());

                cell = row.createCell(3, CellType.STRING);
                cell.setCellValue(tblDoanhThu.getValueAt(i, 2).toString());

                cell = row.createCell(4, CellType.STRING);
                cell.setCellValue(tblDoanhThu.getValueAt(i, 3).toString());
                
                cell = row.createCell(5, CellType.STRING);
                cell.setCellValue(tblDoanhThu.getValueAt(i, 4).toString());

            }
            File f = new File("D:\\FileThongKe.xlsx");
            try {
                FileOutputStream fis = new FileOutputStream(f);

                worbook.write(fis);
                fis.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        JOptionPane.showMessageDialog(this, "Xuất Thành Công");
    }//GEN-LAST:event_btnXuatBaoCaoActionPerformed

    private void cboNamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboNamActionPerformed
        try {
            // Kiểm tra giá trị từ combo box
            Object selectedItem = cboNam.getSelectedItem();

            if (selectedItem != null) {
                fillTableDoanhThu();
            } else {
                // Xử lý trường hợp selectedItem là null (nếu cần)
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_cboNamActionPerformed

    private void btnFirstActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFirstActionPerformed
        page = 1;
        this.setPageSPSapHet();
    }//GEN-LAST:event_btnFirstActionPerformed

    private void btnPrevActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrevActionPerformed
        page--;
        this.setPageSPSapHet();
    }//GEN-LAST:event_btnPrevActionPerformed

    private void btnNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextActionPerformed
        page++;
        this.setPageSPSapHet();
    }//GEN-LAST:event_btnNextActionPerformed

    private void btnLastActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLastActionPerformed
        page = (spctService.gettAllSPSapHet().size() + 5 - 1) / 5;
        this.setPageSPSapHet();
    }//GEN-LAST:event_btnLastActionPerformed

    private void btnBieuDoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBieuDoActionPerformed
        this.setBieuDo();
    }//GEN-LAST:event_btnBieuDoActionPerformed

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
            java.util.logging.Logger.getLogger(ThongKeView.class  

.getName()).log(java.util.logging.Level.SEVERE, null, ex);

} catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ThongKeView.class  

.getName()).log(java.util.logging.Level.SEVERE, null, ex);

} catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ThongKeView.class  

.getName()).log(java.util.logging.Level.SEVERE, null, ex);

} catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ThongKeView.class  

.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ThongKeView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBieuDo;
    private javax.swing.JButton btnFirst;
    private javax.swing.JButton btnLast;
    private javax.swing.JButton btnNext;
    private javax.swing.JButton btnPrev;
    private javax.swing.JButton btnXuatBaoCao;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cboNam;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel77;
    private javax.swing.JLabel jLabel79;
    private javax.swing.JLabel jLabel80;
    private javax.swing.JLabel jLabel83;
    private javax.swing.JLabel jLabel85;
    private javax.swing.JLabel jLabel86;
    private javax.swing.JLabel jLabel92;
    private javax.swing.JLabel jLabel93;
    private javax.swing.JLabel jLabel94;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JTabbedPane jTabbedPane3;
    private javax.swing.JPanel jpthongke;
    private javax.swing.JLabel lblDHHuy;
    private javax.swing.JLabel lblDHHuyNam;
    private javax.swing.JLabel lblDHHuyNgay;
    private javax.swing.JLabel lblDHHuyThang;
    private javax.swing.JLabel lblDHThanhCong;
    private javax.swing.JLabel lblDoanhThuNam;
    private javax.swing.JLabel lblDoanhThuNgay;
    private javax.swing.JLabel lblDoanhThuThang;
    private javax.swing.JLabel lblHDTCNam;
    private javax.swing.JLabel lblHDTCNgay;
    private javax.swing.JLabel lblHDTCThang;
    private javax.swing.JLabel lblPage;
    private javax.swing.JLabel lblTongDH;
    private javax.swing.JTable tblDoanhThu;
    private javax.swing.JTable tblSPCT;
    // End of variables declaration//GEN-END:variables
}
