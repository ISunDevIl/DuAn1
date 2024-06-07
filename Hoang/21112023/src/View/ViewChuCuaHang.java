package View;

import Connection.DBContext;
import Model.ChatLieu;
import Model.GiamGia;
import Model.HoaDon;
import Model.HoaDonChiTiet;
import Model.MauSac;
import Model.NhanVien;
import Model.PhanLoai;
import Model.SanPham;
import Model.SanPhamChiTiet;
import Model.SanPham_SPCT;
import Model.TaiKhoan;
import Model.ThuongHieu;
import Model.XuatXu;
import Service.ChatLieuService;
import Service.GiamGiaService;
import Service.HoaDonService;
import Service.HDCTService;
import Service.MauSacService;
import Service.NhanVienService;
import Service.PhanLoaiService;
import Service.SPCTService;
import Service.SanPhamService;
import Service.SanPham_SPCT_Service;
import Service.TaiKhoanService;
import Service.ThuongHieuService;
import Service.XuatXuService;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Random;
import java.util.random.RandomGenerator;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class ViewChuCuaHang extends javax.swing.JFrame {

    //Khai báo list ở đây
    List<String> lsttentt = new ArrayList<>();
    private ArrayList<NhanVien> listnv = new ArrayList<>();
    List<SanPham_SPCT> listsp_ct = new ArrayList<>();
    List<SanPham_SPCT> listsp_ct1 = new ArrayList<>();
    private Connection con = DBContext.getConnection();
    private DefaultTableModel dtm = new DefaultTableModel();
    private TaiKhoanService servicetk = new TaiKhoanService();
    private ThuongHieuService serviceth = new ThuongHieuService();
    private ChatLieuService servicecl = new ChatLieuService();
    private MauSacService servicems = new MauSacService();
    private PhanLoaiService servicepl = new PhanLoaiService();
    private XuatXuService servicexx = new XuatXuService();
    private NhanVienService servicenv = new NhanVienService();
    private SPCTService spctservice = new SPCTService();
    private SanPhamService spservice = new SanPhamService();
    private SanPham_SPCT_Service sp_ctservice = new SanPham_SPCT_Service();
    private GiamGiaService ggservice = new GiamGiaService();
    private HoaDonService servicehd = new HoaDonService();
    private HDCTService hdctservice = new HDCTService();
    private ArrayList<ThuongHieu> lthuonghieu = new ArrayList<>();
    private ArrayList<ChatLieu> lchatlieu = new ArrayList<>();
    private ArrayList<MauSac> lmausac = new ArrayList<>();
    private ArrayList<PhanLoai> lphanloai = new ArrayList<>();
    private ArrayList<XuatXu> lxuatxu = new ArrayList<>();
    private ArrayList<HoaDonChiTiet> lsthdct = new ArrayList<>();
    String input = null;
    private int i = -1;
    private int index = -1;

    private int row = -1;
    private Integer id = -1;
    private int page = 1;

    public ViewChuCuaHang() {
        initComponents();
        jpthanhtoan.setVisible(true);
        jphoadon.setVisible(false);
        jpthongke.setVisible(false);
        jpsanpham.setVisible(false);
        jpnhanvien.setVisible(false);
        jpkhachhang.setVisible(false);
        jplichsu.setVisible(false);
        jpkhuyenmai.setVisible(false);
        jpdoimk.setVisible(false);

    }

    private void suahdct() {
        i = tblhddsgh.getSelectedRow();

        String input = JOptionPane.showInputDialog("Nhập số lượng");
        int soluong = Integer.parseInt(input) - Integer.parseInt(tblhddsgh.getValueAt(i, 3).toString());
        int soluongcu = hdctservice.findSoLuong(tblhddsgh.getValueAt(i, 0).toString()).getSoLuong();
        int soluongmoi = soluongcu - soluong;
        SanPhamChiTiet spct = new SanPhamChiTiet();
        spct.setSoLuong(soluongmoi);
        spct.setId(hdctservice.findIDSPCT(tblhddsgh.getValueAt(i, 0).toString()).getIdspct());
        boolean suaResult = hdctservice.suasp(spct);

        HoaDonChiTiet hdct = new HoaDonChiTiet();
        hdct.setSoLuong(Integer.parseInt(input));
        hdct.setId(tblhddsgh.getValueAt(i, 0).toString());
        boolean suahdResult = hdctservice.suahd(hdct);
    }

    private void loadGioHang() {
        i = tblhddshd.getSelectedRow();
        String sql = "Select ID,IDSPCT,SoLuong,DonGia,ThanhTien,MaGG FROM HoaDonChiTiet Where IDHD = ?";
        try {
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, tblhddshd.getValueAt(i, 0).toString());
            ResultSet rs = st.executeQuery();
            lsthdct.clear();
            while (rs.next()) {

                String id = rs.getString(1);
                String idspct = rs.getString(2);
                int soluong = rs.getInt(3);
                Float dongia = rs.getFloat(4);
                float thanhien = rs.getFloat(5);
                String magg = rs.getString(6);

                HoaDonChiTiet d = new HoaDonChiTiet(id, id, idspct, soluong, dongia, thanhien, magg);
                lsthdct.add(d);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        dtm = (DefaultTableModel) tblhddsgh.getModel();
        dtm.setRowCount(0);
        for (HoaDonChiTiet x : lsthdct) {
            // float thanhtien = x.getDongia() - hdctservice.findMaGG(x.getMagg()).getGiatri();
            dtm.addRow(new Object[]{
                x.getId(), hdctservice.findIDSP(x.getIdspct()).getMasp(), hdctservice.findIDSP(x.getIdspct()).getTensp(), x.getSoLuong(),
                x.getDongia(), ggservice.findgiatri(x.getMagg()).getGiaTri(), x.getThanhtien()
            });
        }
    }

    private void xoagh() {
        i = tblhddsgh.getSelectedRow();
        String idspct = tblhddsgh.getValueAt(i, 0).toString();
        HoaDonChiTiet hdct = new HoaDonChiTiet();
        hdct.setId(idspct);
        boolean xoaResult = hdctservice.xoa(hdct);
        if (xoaResult) {
            JOptionPane.showMessageDialog(this, "Xóa thành công.");
        } else {
            JOptionPane.showMessageDialog(this, "Xóa không thành công.");
        }
    }

    private void themgh() {

        index = tblhddshd.getSelectedRow();
        i = tblhddssp.getSelectedRow();

        int soluong = (int) tblhddssp.getValueAt(i, 4);
        String idspct = tblhddssp.getValueAt(i, 0).toString();
        String idhd = tblhddshd.getValueAt(index, 0).toString();
        input = JOptionPane.showInputDialog("Nhập số lượng");
        int soluongspct = soluong - Integer.parseInt(input);

        float dongia = (float) tblhddssp.getValueAt(i, 3);
        float magg = ggservice.findIDSP(idspct).getGiaTri();
        float thanhtien = (dongia - magg) * Integer.parseInt(input);
        System.out.println(thanhtien);
        System.out.println(input);
        HoaDonChiTiet dd = new HoaDonChiTiet();
        dd.setIdhd(idhd);
        dd.setIdspct(idspct);
        dd.setSoLuong(Integer.parseInt(input));
        dd.setDongia(dongia);
        dd.setMagg(ggservice.findIDSP(idspct).getMaGG());
        dd.setThanhtien(thanhtien);

        boolean themResult = hdctservice.them(dd);
        if (themResult) {
            JOptionPane.showMessageDialog(this, "Thêm thành công.");
            loadDataNV();
        } else {
            JOptionPane.showMessageDialog(this, "Thêm không thành công.");
        }

        SanPhamChiTiet spct = new SanPhamChiTiet();
        spct.setSoLuong(soluongspct);
        spct.setId(idspct);
        boolean suaResult = hdctservice.suasp(spct);

    }

    private String taoNgay() {
        // Lấy ngày hiện tại
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);
    }

    public void TaoHD() {
        String ngaytao = taoNgay();
        HoaDon hd = new HoaDon();
        hd.setNgaytao(ngaytao);
        hd.setMakh(txthdtenkh.getText());

        String alpha = "QWERTYUIOPASDFGHJKLZXCVBNM0987654321";
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        int length = 5;
        for (int j = 0; j < length; j++) {
            int ind = random.nextInt(alpha.length());
            char randomchar = alpha.charAt(ind);
            sb.append(randomchar);
        }
        String mahd = sb.toString();
        txthdma.setText(mahd);
        hd.setMaHD(txthdma.getText());

        boolean taoResult = servicehd.them(hd);

    }

    private Integer loadSTT() {
        int n = tblhddshd.getRowCount() + 1;
        return n;
    }

    public void LoadDataHD() {
        dtm = (DefaultTableModel) tblhddshd.getModel();
        dtm.setRowCount(0);
        for (HoaDon hd : servicehd.getAllHoaDonbyPage(page)) {
            String trangthai = "Chưa thanh toán";
            dtm.addRow(new Object[]{
                hd.getId(), hd.getMaHD(), hd.getMakh(), hd.getNgaytao(), trangthai
            });
        }
    }

    public void setPageHD() {
        txtPageHD.setText(String.valueOf(page));
        int tongItem = servicehd.getAllHoaDon().size();
        int itemPage = 5;
        boolean first = page == 1;
        boolean last = page == (tongItem + itemPage - 1) / itemPage;
        btnFirstHD.setEnabled(!first);
        btnPrevHD.setEnabled(!first);
        btnNextHD.setEnabled(!last);
        btnLastHD.setEnabled(!last);
        this.LoadDataHD();
    }

    public void LoadDataHDSP() {
        dtm = (DefaultTableModel) tblhddssp.getModel();
        dtm.setRowCount(0);
        for (SanPhamChiTiet sp : servicehd.getAllSanPham(page)) {
            dtm.addRow(new Object[]{
                sp.getId(), servicehd.findIDSP(sp.getIdsp()).getMaSP(), servicehd.findIDSP(sp.getIdsp()).getTenSP(), sp.getGia(),
                sp.getSoLuong(), sp.getKhoiLuong(), sp.getKichThuoc(), servicepl.findPL(sp.getIdpl()).getTen(),
                serviceth.findTH(sp.getIdth()).getTen(), servicems.findMS(sp.getIdms()).getTen(),
                servicexx.findXX(sp.getIdxx()).getTen(), servicecl.findCL(sp.getIdcl()).getTen()
            });
        }
    }
    
    public void setPageSP() {
        txtPageSP.setText(String.valueOf(page));
        int tongItem = servicehd.getAllSanPham(1000).size();
        int itemPage = 5;
        boolean first = page == 1;
        boolean last = page == (tongItem + itemPage - 1) / itemPage;
        btnFirstSP.setEnabled(!first);
        btnPrevSP.setEnabled(!first);
        btnNextSP.setEnabled(!last);
        btnLastSP.setEnabled(!last);
        this.LoadDataHDSP();
    }

    /*------------------------------Hóa Đơn----------------------------------------------------------*/
//    public void them1spct() {
//        SanPhamChiTiet spct = new SanPhamChiTiet();
//        String idspct = lblrandomidspct.getText();
//        String kichthuoc = txtspkichthuoc.getText();
//        Float gia = Float.parseFloat(txtspdongia.getText());
//        int soluong = Integer.parseInt(txtspsoluong.getText());
//        Float khoiluong = Float.parseFloat(txtspkhoiluong.getText());
//        String mota = txtspmota.getText();
//        String idth = serviceth.findidTH(cbospthuonghieu.getSelectedItem().toString()).getId();
//        String idms = servicems.findidMS(cbospmausac.getSelectedItem().toString()).getId();
//        String idpl = servicepl.findidPL(cbospphanloai.getSelectedItem().toString()).getId();
//        String idcl = servicecl.findidCL(cbospchatlieu.getSelectedItem().toString()).getId();
//        String idxx = servicexx.findidXX(cbospxuatxu.getSelectedItem().toString()).getId();
//        String anh = pathFile;
//        spct.setId(idspct);
//        spct.setAnh(anh);
//        spct.setGia(gia);
//        spct.setKhoiLuong(khoiluong);
//        spct.setSoLuong(soluong);
//        spct.setKichThuoc(kichthuoc);
//        spct.setMoTa(mota);
//        spct.setIdcl(idcl);
//        spct.setIdms(idms);
//        spct.setIdpl(idpl);
//        spct.setIdth(idth);
//        spct.setIdxx(idxx);
//
//        SanPham_SPCT sp_spct = new SanPham_SPCT();
//        String ma = txtspmasp.getText();
//        sp_spct.setIdsp(spservice.findMaSP(ma).getId());
//        sp_spct.setIdspct(lblrandomidspct.getText());
//
//        boolean themResult = spctservice.Themspct(spct);
//        boolean themidResult = sp_ctservice.ThemSP_SPCT(sp_spct);
//        if (themResult) {
//            if(themidResult){
//                JOptionPane.showMessageDialog(this, "Thêm thành công2");
//            }           
//        } else {
//            JOptionPane.showMessageDialog(this, "Thêm thất bại");
//        }
//    }
//
//    public boolean checksuasp() {
//        Float dongia = Float.parseFloat(txtspdongia.getText());
//        Float khoiluong = Float.parseFloat(txtspkhoiluong.getText());
//        int soluong = Integer.parseInt(txtspsoluong.getText());
//        try {
//            if (dongia < 0) {
//                JOptionPane.showConfirmDialog(this, "Giá không hợp lệ");
//                return false;
//            }
//        } catch (Exception e) {
//            JOptionPane.showMessageDialog(this, "Giá không hợp lệ");
//        }
//        try {
//            if (khoiluong < 0) {
//                JOptionPane.showConfirmDialog(this, "Khlượng không hợp lệ");
//                return false;
//            }
//        } catch (Exception e) {
//            JOptionPane.showMessageDialog(this, "Khối lượng không hợp lệ");
//        }
//        try {
//            if (soluong < 0) {
//                JOptionPane.showConfirmDialog(this, "Số lượng không hợp lệ");
//                return false;
//            }
//        } catch (Exception e) {
//            JOptionPane.showMessageDialog(this, "Số lượng không hợp lệ");
//        }
//
//        if (txtspmasp.getText().isEmpty() || txtsptensp.getText().isEmpty() || txtspdongia.getText().isEmpty()
//                || txtspkhoiluong.getText().isEmpty() || txtspkichthuoc.getText().isEmpty() || txtspsoluong.getText().isEmpty()
//                || txtspmota.getText().isEmpty()) {
//            JOptionPane.showMessageDialog(this, "Không được để trống thông tin");
//            return false;
//        }
//        return true;
//    }
//
//    public boolean checksp() {
//        Float dongia = Float.parseFloat(txtspdongia.getText());
//        Float khoiluong = Float.parseFloat(txtspkhoiluong.getText());
//        int soluong = Integer.parseInt(txtspsoluong.getText());
//        try {
//            if (dongia < 0) {
//                JOptionPane.showConfirmDialog(this, "Giá không hợp lệ");
//                return false;
//            }
//        } catch (Exception e) {
//            JOptionPane.showMessageDialog(this, "Giá không hợp lệ");
//        }
//        try {
//            if (khoiluong < 0) {
//                JOptionPane.showConfirmDialog(this, "Khlượng không hợp lệ");
//                return false;
//            }
//        } catch (Exception e) {
//            JOptionPane.showMessageDialog(this, "Khối lượng không hợp lệ");
//        }
//        try {
//            if (soluong < 0) {
//                JOptionPane.showConfirmDialog(this, "Số lượng không hợp lệ");
//                return false;
//            }
//        } catch (Exception e) {
//            JOptionPane.showMessageDialog(this, "Số lượng không hợp lệ");
//        }
//
//        if (txtspmasp.getText().isEmpty() || txtsptensp.getText().isEmpty() || txtspdongia.getText().isEmpty()
//                || txtspkhoiluong.getText().isEmpty() || txtspkichthuoc.getText().isEmpty() || txtspsoluong.getText().isEmpty()
//                || txtspmota.getText().isEmpty()) {
//            JOptionPane.showMessageDialog(this, "Không được để trống thông tin");
//            return false;
//        } else if (i != -1) {
//            them1spct();
//            return true;
//        } else if (i == -1) {
//            for (SanPham x : spservice.gettAll()) {
//                if (x.getMaSP().contains(txtspmasp.getText())) {
//                    them1spct();
//
//                } else {
//                    themsp();
//                    break;
//                }
//            }
//
//        }
//        return true;
//    }
//
//    public void ansp() {
//        SanPham sp = new SanPham();
//        sp.setId(lblidsp.getText());
//        boolean anResult = spservice.ansp(sp);
//        if (anResult) {
//            JOptionPane.showMessageDialog(this, "Ẩn thành công");
//        } else {
//            JOptionPane.showMessageDialog(this, "Ẩn thất bại");
//        }
//    }
//
//    public void Suasp() {
//        SanPhamChiTiet spct = new SanPhamChiTiet();
//        String idspct = lblrandomidspct.getText();
//        String kichthuoc = txtspkichthuoc.getText();
//        Float gia = Float.parseFloat(txtspdongia.getText());
//        int soluong = Integer.parseInt(txtspsoluong.getText());
//        Float khoiluong = Float.parseFloat(txtspkhoiluong.getText());
//        String mota = txtspmota.getText();
//        String idth = serviceth.findidTH(cbospthuonghieu.getSelectedItem().toString()).getId();
//        String idms = servicems.findidMS(cbospmausac.getSelectedItem().toString()).getId();
//        String idpl = servicepl.findidPL(cbospphanloai.getSelectedItem().toString()).getId();
//        String idcl = servicecl.findidCL(cbospchatlieu.getSelectedItem().toString()).getId();
//        String idxx = servicexx.findidXX(cbospxuatxu.getSelectedItem().toString()).getId();
//        String anh = pathFile;
//        spct.setId(idspct);
//        spct.setAnh(anh);
//        spct.setGia(gia);
//        spct.setKhoiLuong(khoiluong);
//        spct.setSoLuong(soluong);
//        spct.setKichThuoc(kichthuoc);
//        spct.setMoTa(mota);
//        spct.setIdcl(idcl);
//        spct.setIdms(idms);
//        spct.setIdpl(idpl);
//        spct.setIdth(idth);
//        spct.setIdxx(idxx);
//
//        boolean themResult = spctservice.Sua(spct);
//        if (themResult) {
//            JOptionPane.showMessageDialog(this, "Sửa thành công.");
//        } else {
//            JOptionPane.showMessageDialog(this, "Sửa không thành công.");
//        }
//    }
//
//    
//
//    public ArrayList<String> loadidsp() {
//        ArrayList<String> lstidsp = new ArrayList<String>();
//        String sql = "Select ID From SanPham";
//        try {
//            PreparedStatement st = con.prepareStatement(sql);
//            ResultSet rs = st.executeQuery();
//            while (rs.next()) {
//                lstidsp.add(rs.getString("ID"));
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        for (String item : lstidsp) {
//            lblidsp.setText(item);
//        }
//        return lstidsp;
//    }
//
//    public void Displayspct(int b) {
//        List<SanPhamChiTiet> test = new ArrayList<>();
//
//        for (SanPham_SPCT sp_ct : listsp_ct) {
//            test.add(spctservice.findSPCT(sp_ct.getIdspct()));
//        }
//
//        SanPhamChiTiet db = test.get(b);
//        lblrandomidspct.setText(db.getId());
//        cbospchatlieu.setSelectedItem(servicecl.findCL(db.getIdcl()).getTen());
//        cbospmausac.setSelectedItem(servicems.findMS(db.getIdms()).getTen());
//        cbospphanloai.setSelectedItem(servicepl.findPL(db.getIdpl()).getTen());
//        cbospthuonghieu.setSelectedItem(serviceth.findTH(db.getIdth()).getTen());
//        cbospxuatxu.setSelectedItem(servicexx.findXX(db.getIdxx()).getTen());
//        txtspkichthuoc.setText(db.getKichThuoc());
//        txtspmota.setText(db.getMoTa());
//        txtspkhoiluong.setText(String.valueOf(db.getKhoiLuong()));
//        txtspdongia.setText(String.valueOf(db.getGia()));
//        txtspsoluong.setText(String.valueOf(db.getSoLuong()));
//        if (db.getAnh() != null) {
//            lblspanh.setToolTipText(db.getAnh());
//            lblspanh.setIcon(ResizeImage(String.valueOf(db.getAnh())));
//        }
//
//    }
//
//    public void Displaysp(int b) {
//        SanPham sp = listsp.get(b);
//        txtspmasp.setText(sp.getMaSP());
//        txtsptensp.setText(sp.getTenSP());
//    }
//
//    public void clearcbo() {
//        cbospthuonghieu.removeAllItems();
//        cbospphanloai.removeAllItems();
//        cbospchatlieu.removeAllItems();
//        cbospmausac.removeAllItems();
//        cbospxuatxu.removeAllItems();
//    }
//
//    public ArrayList<String> loadcboth() {
//        cbospthuonghieu.removeAllItems();
//        ArrayList<String> lstth = new ArrayList<String>();
//        String sql = "Select TenTH From ThuongHieu";
//        try {
//            PreparedStatement st = con.prepareStatement(sql);
//            ResultSet rs = st.executeQuery();
//            while (rs.next()) {
//                lstth.add(rs.getString("TenTH"));
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        for (String item : lstth) {
//            cbospthuonghieu.addItem(item.toString());
//        }
//        return lstth;
//    }
//
//    public ArrayList<String> loadcboms() {
//        cbospmausac.removeAllItems();
//        ArrayList<String> lstms = new ArrayList<String>();
//        String sql = "Select TenMS From MauSac";
//        try {
//            PreparedStatement st = con.prepareStatement(sql);
//            ResultSet rs = st.executeQuery();
//            while (rs.next()) {
//                lstms.add(rs.getString("TenMS"));
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        for (String item : lstms) {
//            cbospmausac.addItem(item.toString());
//        }
//        return lstms;
//    }
//
//    public ArrayList<String> loadcbopl() {
//        cbospphanloai.removeAllItems();
//        ArrayList<String> lstpl = new ArrayList<String>();
//        String sql = "Select TenPL From PhanLoai";
//        try {
//            PreparedStatement st = con.prepareStatement(sql);
//            ResultSet rs = st.executeQuery();
//            while (rs.next()) {
//                lstpl.add(rs.getString("TenPL"));
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        for (String item : lstpl) {
//            cbospphanloai.addItem(item.toString());
//        }
//        return lstpl;
//    }
//
//    public ArrayList<String> loadcbocl() {
//        cbospchatlieu.removeAllItems();
//        ArrayList<String> lstcl = new ArrayList<String>();
//        String sql = "Select TenCL From ChatLieu";
//        try {
//            PreparedStatement st = con.prepareStatement(sql);
//            ResultSet rs = st.executeQuery();
//            while (rs.next()) {
//                lstcl.add(rs.getString("TenCL"));
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        for (String item : lstcl) {
//            cbospchatlieu.addItem(item.toString());
//        }
//        return lstcl;
//    }
//
//    public ArrayList<String> loadcboxx() {
//        cbospxuatxu.removeAllItems();
//        ArrayList<String> lstxx = new ArrayList<String>();
//        String sql = "Select TenXX From XuatXu";
//        try {
//            PreparedStatement st = con.prepareStatement(sql);
//            ResultSet rs = st.executeQuery();
//            while (rs.next()) {
//                lstxx.add(rs.getString("TenXX"));
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        for (String item : lstxx) {
//            cbospxuatxu.addItem(item.toString());
//        }
//        return lstxx;
//    }
//
//    public void loadDataSP() {
//        String sql = "Select * From SanPham where TrangThai = 0";
//        try {
//            Statement st = con.createStatement();
//            ResultSet rs = st.executeQuery(sql);
//
//            listsp.clear();
//
//            while (rs.next()) {
//                SanPham sp = new SanPham();
//                sp.setId(rs.getString("ID"));
//                sp.setMaSP(rs.getString("MaSP"));
//                sp.setTenSP(rs.getString("TenSP"));
//                sp.setTrangThai(rs.getByte("TrangThai"));
//                listsp.add(sp);
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        dtm = (DefaultTableModel) tblsp.getModel();
//        dtm.setRowCount(0);
//        for (SanPham x : listsp) {
//            dtm.addRow(new Object[]{
//                x.getId(), x.getMaSP(), x.getTenSP()
//            });
//        }
//
//    }
//
//    public String Displaysanpham(int c) {
//        String idSP = null;
//        SanPham db = listsp.get(c);
//        txtspmasp.setText(db.getMaSP());
//        txtsptensp.setText(db.getTenSP());
//        lblidsp.setText(db.getId());
//        idSP = db.getId();
//        return idSP;
//
//    }
//    String pathFile = "C:\\Users\\builo\\OneDrive\\Pictures\\Saved Pictures";
//
//    void chonAnhsp() throws IOException {
//        try {
//            JFileChooser fileChooser = new JFileChooser();
//            fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
//            int returnValue = fileChooser.showOpenDialog(this);
//            if (returnValue == JFileChooser.APPROVE_OPTION) {
//                File file = fileChooser.getSelectedFile();
//                pathFile = file.getAbsolutePath();
//                lblspanh.setIcon(ResizeImage(String.valueOf(pathFile)));
//            }
//        } catch (Exception e) {
//            System.out.println("chua chon anh");
//        }
//    }
//
//    public ImageIcon ResizeImage(String Imagepath) {
//        ImageIcon MyImage = new ImageIcon(Imagepath);
//        Image img = MyImage.getImage();
//        Image newImg = img.getScaledInstance(lblspanh.getWidth(), lblspanh.getHeight(), Image.SCALE_SMOOTH);
//        ImageIcon image = new ImageIcon(newImg);
//        return image;
//    }

    /*------------------------------Nhân Viên----------------------------------------------------------*/
    public void loadDataNV() {
        String sql = "Select MaNV,HoTen,GioiTinh,NgaySinh,SDT,Email,DiaChi,TrangThai FROM NhanVien";
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);

            listnv.clear();

            while (rs.next()) {
                String ma = rs.getString(1);
                String ten = rs.getString(2);
                String gioitinh = rs.getString(3);
                Date ngaysinh = rs.getDate(4);
                String sdt = rs.getString(5);
                String email = rs.getString(6);
                String diachi = rs.getString(7);

                NhanVien d = new NhanVien(ma, ma, ma, ten, gioitinh, ngaysinh, diachi, sdt, email, Byte.MIN_VALUE);
                listnv.add(d);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        dtm = (DefaultTableModel) tblnv.getModel();
        dtm.setRowCount(0);
        for (NhanVien x : listnv) {
            dtm.addRow(x.toDataRow());
        }

    }

    public void Display(int b) {
        NhanVien db = listnv.get(b);
        txtnvmanv.setText(db.getMaNV());
        txtnvtennv.setText(db.getTenNV());
        txtnvgioitinh.setText(db.getGioiTinh());
        txtnvngaysinh.setDate(db.getNgaySinh());
        txtnvdiachi.setText(db.getDiaChi());
        txtnvsodt.setText(db.getSdt());
        txtnvemail.setText(db.getEmail());

    }

    private void themnv() {
        String manv = txtnvmanv.getText();
        String ten = txtnvtennv.getText();
        String gioitinh = txtnvgioitinh.getText();
        Date ngaysinh = txtnvngaysinh.getDate();
        String diachi = txtnvdiachi.getText();
        String sdt = txtnvsodt.getText();
        String email = txtnvemail.getText();

        NhanVien dd = new NhanVien();
        dd.setMaNV(manv);
        dd.setTenNV(ten);
        dd.setGioiTinh(gioitinh);
        dd.setNgaySinh(ngaysinh);
        dd.setDiaChi(diachi);
        dd.setSdt(sdt);
        dd.setEmail(email);

        boolean themResult = servicenv.them(dd);
        if (themResult) {
            JOptionPane.showMessageDialog(this, "Thêm thành công.");
            loadDataNV();
        } else {
            JOptionPane.showMessageDialog(this, "Thêm không thành công.");
        }
    }

    private void xoanv() {
        String manv = txtnvmanv.getText();
        String ten = txtnvtennv.getText();
        String gioitinh = txtnvgioitinh.getText();
        Date ngaysinh = txtnvngaysinh.getDate();

        String diachi = txtnvdiachi.getText();
        String sdt = txtnvsodt.getText();
        String email = txtnvemail.getText();

        NhanVien dd = new NhanVien();
        dd.setMaNV(manv);
        dd.setTenNV(ten);
        dd.setGioiTinh(gioitinh);
        dd.setNgaySinh(ngaysinh);
        dd.setDiaChi(diachi);
        dd.setSdt(sdt);
        dd.setEmail(email);

        boolean xoaResult = servicenv.xoa(dd);
        if (xoaResult) {
            JOptionPane.showMessageDialog(this, "Xóa thành công.");
            loadDataNV();
        } else {
            JOptionPane.showMessageDialog(this, "Xóa không thành công.");
        }
    }

    private void suanv() {
        String manv = txtnvmanv.getText();
        String ten = txtnvtennv.getText();
        String gioitinh = txtnvgioitinh.getText();
        Date ngaysinh = txtnvngaysinh.getDate();

        String diachi = txtnvdiachi.getText();
        String sdt = txtnvsodt.getText();
        String email = txtnvemail.getText();

        NhanVien dd = new NhanVien();
        dd.setMaNV(manv);
        dd.setTenNV(ten);
        dd.setGioiTinh(gioitinh);
        dd.setNgaySinh(ngaysinh);
        dd.setDiaChi(diachi);
        dd.setSdt(sdt);
        dd.setEmail(email);

        boolean suaResult = servicenv.sua(dd);
        if (suaResult) {
            JOptionPane.showMessageDialog(this, "Sửa thành công.");
            loadDataNV();
        } else {
            JOptionPane.showMessageDialog(this, "Sửa không thành công.");
        }
    }

    /*------------------------------Nhân Viên----------------------------------------------------------*/

 /*------------------------------Thuộc tính sản phẩm----------------------------------------------------------*/
    public boolean checkchatlieu() {
        boolean check = true;
        if (txttenchatlieu.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Chưa nhập giá trị");
            check = false;
        } else {
            for (ChatLieu x : servicecl.gettAll()) {
                if (x.getTen().contains(txttenchatlieu.getText())) {
                    JOptionPane.showMessageDialog(this, "Đã tồn tại");
                    check = false;
                    break;
                }
            }
        }
        return check;
    }

    public boolean checkmausac() {
        boolean check = true;
        if (txttenmausac.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Chưa nhập giá trị");
            check = false;
        } else {
            for (MauSac x : servicems.gettAll()) {
                if (x.getTen().contains(txttenmausac.getText())) {
                    JOptionPane.showMessageDialog(this, "Đã tồn tại");
                    check = false;
                    break;
                }
            }
        }
        return check;
    }

    public boolean checkphanloai() {
        boolean check = true;
        if (txttenphanloai.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Chưa nhập giá trị");
            check = false;
        } else {
            for (PhanLoai x : servicepl.gettAll()) {
                if (x.getTen().contains(txttenphanloai.getText())) {
                    JOptionPane.showMessageDialog(this, "Đã tồn tại");
                    check = false;
                    break;
                }
            }
        }

        return check;
    }

    public boolean checkthuonghieu() {
        boolean check = true;
        if (txttenthuonghieu.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Chưa nhập giá trị");
            check = false;
        } else {
            for (ThuongHieu x : serviceth.gettAll()) {
                if (x.getTen().contains(txttenthuonghieu.getText())) {
                    JOptionPane.showMessageDialog(this, "Đã tồn tại");
                    check = false;
                    break;
                }
            }
        }

        return check;
    }

    public boolean checkxuatxu() {
        boolean check = true;
        if (txttenxuatxu.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Chưa nhập giá trị");
            check = false;
        } else {
            for (XuatXu x : servicexx.gettAll()) {
                if (x.getTen().contains(txttenxuatxu.getText())) {
                    JOptionPane.showMessageDialog(this, "Đã tồn tại");
                    check = false;
                    break;
                }
            }
        }

        return check;
    }

    public void displaythuonghieu(int a) {
        ThuongHieu m0 = lthuonghieu.get(a);
        txttenthuonghieu.setText(m0.getTen());
        txtidthuonghieu.setText(m0.getId());
    }

    public void displaymausac(int a) {
        MauSac m1 = lmausac.get(a);
        txttenmausac.setText(m1.getTen());
        txtidmausac.setText(m1.getId());
    }

    public void displayphanloai(int a) {
        PhanLoai m2 = lphanloai.get(a);
        txttenphanloai.setText(m2.getTen());
        txtidphanloai.setText(m2.getId());
    }

    public void displaychatlieu(int a) {
        ChatLieu m3 = lchatlieu.get(a);
        txttenchatlieu.setText(m3.getTen());
        txtidchatlieu.setText(m3.getId());
    }

    public void displayxuatxu(int a) {
        XuatXu m4 = lxuatxu.get(a);
        txttenxuatxu.setText(m4.getTen());
        txtidxuatxu.setText(m4.getId());
    }

    /*------------------------------Thuộc tính sản phẩm----------------------------------------------------------*/
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        thuoctinhsp = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        lblthongke = new javax.swing.JLabel();
        lblsanpham = new javax.swing.JLabel();
        lblnhanvien = new javax.swing.JLabel();
        lblhoadon = new javax.swing.JLabel();
        lblkhachhang = new javax.swing.JLabel();
        lbllichsu = new javax.swing.JLabel();
        lblkhuyenmai = new javax.swing.JLabel();
        lbldangxuat = new javax.swing.JLabel();
        lbltaikhoan = new javax.swing.JLabel();
        jLabel107 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jpthanhtoan = new javax.swing.JPanel();
        jpkhuyenmai = new javax.swing.JPanel();
        jPanel30 = new javax.swing.JPanel();
        jScrollPane15 = new javax.swing.JScrollPane();
        tblkmdssp = new javax.swing.JTable();
        jLabel108 = new javax.swing.JLabel();
        cbokmapdung = new javax.swing.JComboBox<>();
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
        txtkmmucgg = new javax.swing.JTextField();
        txtkmtenct = new javax.swing.JTextField();
        jphoadon = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblhddshd = new javax.swing.JTable();
        btnFirstHD = new javax.swing.JButton();
        btnPrevHD = new javax.swing.JButton();
        txtPageHD = new javax.swing.JTextField();
        btnNextHD = new javax.swing.JButton();
        btnLastHD = new javax.swing.JButton();
        jPanel10 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblhddsgh = new javax.swing.JTable();
        btnhdxoa = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jPanel11 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblhddssp = new javax.swing.JTable();
        btnFirstSP = new javax.swing.JButton();
        btnPrevSP = new javax.swing.JButton();
        btnNextSP = new javax.swing.JButton();
        btnLastSP = new javax.swing.JButton();
        txtPageSP = new javax.swing.JTextField();
        jPanel12 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        txthdtenkh = new javax.swing.JTextField();
        txthdsdt = new javax.swing.JTextField();
        jPanel15 = new javax.swing.JPanel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        txthdtongtien = new javax.swing.JLabel();
        txthdkhachtra = new javax.swing.JLabel();
        txthdtienthua = new javax.swing.JLabel();
        cbohdthanhtoan = new javax.swing.JComboBox<>();
        jLabel30 = new javax.swing.JLabel();
        txthdkhachdua = new javax.swing.JTextField();
        txthdma = new javax.swing.JLabel();
        btnhdthanhtoan = new javax.swing.JButton();
        btnhdtaohoadon = new javax.swing.JButton();
        btnhdhuy = new javax.swing.JButton();
        btnhdinhoadon = new javax.swing.JButton();
        jpdoimk = new javax.swing.JPanel();
        lblid = new javax.swing.JLabel();
        jLabel62 = new javax.swing.JLabel();
        jLabel63 = new javax.swing.JLabel();
        jLabel64 = new javax.swing.JLabel();
        txttendangnhap = new javax.swing.JTextField();
        txtmk = new javax.swing.JTextField();
        txtmkm = new javax.swing.JTextField();
        btnthem = new javax.swing.JButton();
        btnsua = new javax.swing.JButton();
        jLabel120 = new javax.swing.JLabel();
        jScrollPane17 = new javax.swing.JScrollPane();
        tbltaikhoan = new javax.swing.JTable();
        rdocch = new javax.swing.JRadioButton();
        rdonv = new javax.swing.JRadioButton();
        txtmkxn = new javax.swing.JTextField();
        lblmkm = new javax.swing.JLabel();
        lblmkxn = new javax.swing.JLabel();
        btnxoa = new javax.swing.JButton();
        btndoimk = new javax.swing.JButton();
        btnxacnhan = new javax.swing.JButton();
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
        jpthongke = new javax.swing.JPanel();
        jPanel20 = new javax.swing.JPanel();
        jLabel66 = new javax.swing.JLabel();
        jLabel67 = new javax.swing.JLabel();
        jLabel68 = new javax.swing.JLabel();
        jLabel69 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel70 = new javax.swing.JLabel();
        jPanel21 = new javax.swing.JPanel();
        jLabel71 = new javax.swing.JLabel();
        jLabel72 = new javax.swing.JLabel();
        jLabel73 = new javax.swing.JLabel();
        jLabel74 = new javax.swing.JLabel();
        jLabel75 = new javax.swing.JLabel();
        jLabel76 = new javax.swing.JLabel();
        jPanel22 = new javax.swing.JPanel();
        jLabel77 = new javax.swing.JLabel();
        jLabel78 = new javax.swing.JLabel();
        jLabel79 = new javax.swing.JLabel();
        jLabel80 = new javax.swing.JLabel();
        jLabel81 = new javax.swing.JLabel();
        jLabel82 = new javax.swing.JLabel();
        jPanel23 = new javax.swing.JPanel();
        jLabel83 = new javax.swing.JLabel();
        jLabel84 = new javax.swing.JLabel();
        jLabel85 = new javax.swing.JLabel();
        jLabel86 = new javax.swing.JLabel();
        jLabel87 = new javax.swing.JLabel();
        jLabel88 = new javax.swing.JLabel();
        jLabel89 = new javax.swing.JLabel();
        jComboBox6 = new javax.swing.JComboBox<>();
        jLabel90 = new javax.swing.JLabel();
        jLabel91 = new javax.swing.JLabel();
        jButton19 = new javax.swing.JButton();
        jTabbedPane3 = new javax.swing.JTabbedPane();
        jPanel24 = new javax.swing.JPanel();
        jLabel92 = new javax.swing.JLabel();
        jComboBox7 = new javax.swing.JComboBox<>();
        jPanel26 = new javax.swing.JPanel();
        jLabel93 = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTable8 = new javax.swing.JTable();
        jPanel25 = new javax.swing.JPanel();
        jLabel94 = new javax.swing.JLabel();
        jScrollPane11 = new javax.swing.JScrollPane();
        jTable9 = new javax.swing.JTable();
        jpnhanvien = new javax.swing.JPanel();
        jPanel27 = new javax.swing.JPanel();
        jLabel95 = new javax.swing.JLabel();
        jLabel96 = new javax.swing.JLabel();
        jLabel97 = new javax.swing.JLabel();
        jLabel98 = new javax.swing.JLabel();
        jLabel99 = new javax.swing.JLabel();
        txtnvmanv = new javax.swing.JTextField();
        txtnvtennv = new javax.swing.JTextField();
        txtnvgioitinh = new javax.swing.JTextField();
        txtnvsodt = new javax.swing.JTextField();
        jLabel100 = new javax.swing.JLabel();
        jLabel105 = new javax.swing.JLabel();
        jScrollPane13 = new javax.swing.JScrollPane();
        txtnvdiachi = new javax.swing.JTextArea();
        jLabel9 = new javax.swing.JLabel();
        txtnvemail = new javax.swing.JTextField();
        jPanel28 = new javax.swing.JPanel();
        jScrollPane12 = new javax.swing.JScrollPane();
        tblnv = new javax.swing.JTable();
        jLabel103 = new javax.swing.JLabel();
        jLabel104 = new javax.swing.JLabel();
        jPanel29 = new javax.swing.JPanel();
        txtnvthem = new javax.swing.JButton();
        txtnvsua = new javax.swing.JButton();
        txtnvxoa = new javax.swing.JButton();
        jpkhachhang = new javax.swing.JPanel();
        jplichsu = new javax.swing.JPanel();
        jPanel17 = new javax.swing.JPanel();
        jScrollPane8 = new javax.swing.JScrollPane();
        jTable6 = new javax.swing.JTable();
        jPanel18 = new javax.swing.JPanel();
        jScrollPane9 = new javax.swing.JScrollPane();
        jTable7 = new javax.swing.JTable();
        jPanel19 = new javax.swing.JPanel();
        jLabel44 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        jLabel51 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        jLabel53 = new javax.swing.JLabel();
        jLabel54 = new javax.swing.JLabel();
        jLabel55 = new javax.swing.JLabel();
        jLabel56 = new javax.swing.JLabel();
        jLabel57 = new javax.swing.JLabel();
        jLabel58 = new javax.swing.JLabel();
        jLabel59 = new javax.swing.JLabel();
        jScrollPane10 = new javax.swing.JScrollPane();
        jTextArea3 = new javax.swing.JTextArea();
        jLabel60 = new javax.swing.JLabel();
        panelThuongHieu = new javax.swing.JPanel();
        mausac1 = new javax.swing.JPanel();
        txttenthuonghieu = new javax.swing.JTextField();
        btnsuathuonghieu = new javax.swing.JButton();
        jLabel27 = new javax.swing.JLabel();
        btnthemthuonghieu = new javax.swing.JButton();
        btnxoathuonghieu = new javax.swing.JButton();
        txtidthuonghieu = new javax.swing.JLabel();
        jScrollPane18 = new javax.swing.JScrollPane();
        tbldsthuonghieu = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        panelChatLieu = new javax.swing.JPanel();
        mausac = new javax.swing.JPanel();
        txttenchatlieu = new javax.swing.JTextField();
        btnsuachatlieu = new javax.swing.JButton();
        jLabel19 = new javax.swing.JLabel();
        btnthemchatlieu = new javax.swing.JButton();
        btnxoachatlieu = new javax.swing.JButton();
        txtidchatlieu = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbldschatlieu = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        panelPhanLoai = new javax.swing.JPanel();
        mausac2 = new javax.swing.JPanel();
        txttenphanloai = new javax.swing.JTextField();
        btnsuaphanloai = new javax.swing.JButton();
        jLabel28 = new javax.swing.JLabel();
        btnthemphanloai = new javax.swing.JButton();
        btnxoaphanloai = new javax.swing.JButton();
        txtidphanloai = new javax.swing.JLabel();
        jScrollPane19 = new javax.swing.JScrollPane();
        tbldsphanloai = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        panelXuatXu = new javax.swing.JPanel();
        mausac3 = new javax.swing.JPanel();
        txttenxuatxu = new javax.swing.JTextField();
        btnsuaxuatxu = new javax.swing.JButton();
        jLabel29 = new javax.swing.JLabel();
        btnthemxuatxu = new javax.swing.JButton();
        btnxoaxuatxu = new javax.swing.JButton();
        txtidxuatxu = new javax.swing.JLabel();
        jScrollPane21 = new javax.swing.JScrollPane();
        tbldsxuatxu = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        panelMauSac = new javax.swing.JPanel();
        mausac4 = new javax.swing.JPanel();
        txttenmausac = new javax.swing.JTextField();
        btnsuamausac = new javax.swing.JButton();
        jLabel37 = new javax.swing.JLabel();
        btnthemmausac = new javax.swing.JButton();
        btnxoamausac = new javax.swing.JButton();
        txtidmausac = new javax.swing.JLabel();
        jScrollPane22 = new javax.swing.JScrollPane();
        tbldsmausac = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 153, 153));

        lblthongke.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblthongke.setText("Thống kê");
        lblthongke.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblthongkeMouseClicked(evt);
            }
        });

        lblsanpham.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblsanpham.setText("Sản phẩm");
        lblsanpham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblsanphamMouseClicked(evt);
            }
        });

        lblnhanvien.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblnhanvien.setText("Nhân viên");
        lblnhanvien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblnhanvienMouseClicked(evt);
            }
        });

        lblhoadon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblhoadon.setText("Hóa đơn");
        lblhoadon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblhoadonMouseClicked(evt);
            }
        });

        lblkhachhang.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblkhachhang.setText("Khách hàng");
        lblkhachhang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblkhachhangMouseClicked(evt);
            }
        });

        lbllichsu.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbllichsu.setText("Lịch sử");
        lbllichsu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbllichsuMouseClicked(evt);
            }
        });

        lblkhuyenmai.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblkhuyenmai.setText("Khuyến mại");
        lblkhuyenmai.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblkhuyenmaiMouseClicked(evt);
            }
        });

        lbldangxuat.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbldangxuat.setText("Đăng xuất");

        lbltaikhoan.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbltaikhoan.setText("Quản lý tài khoản");
        lbltaikhoan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbltaikhoanMouseClicked(evt);
            }
        });

        jLabel107.setForeground(new java.awt.Color(51, 51, 255));
        jLabel107.setText("Tên nhân viên");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbltaikhoan, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lblkhuyenmai, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lbllichsu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lblhoadon, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lblnhanvien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lblthongke, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lblsanpham, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lblkhachhang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lbldangxuat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(jLabel107, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(45, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(39, Short.MAX_VALUE)
                .addComponent(jLabel107, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblthongke, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblsanpham, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblnhanvien, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblhoadon, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblkhachhang, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbllichsu, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblkhuyenmai, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbltaikhoan, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbldangxuat, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(155, 155, 155))
        );

        jPanel5.setBackground(new java.awt.Color(51, 255, 51));

        jpthanhtoan.setPreferredSize(new java.awt.Dimension(919, 654));

        javax.swing.GroupLayout jpthanhtoanLayout = new javax.swing.GroupLayout(jpthanhtoan);
        jpthanhtoan.setLayout(jpthanhtoanLayout);
        jpthanhtoanLayout.setHorizontalGroup(
            jpthanhtoanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 919, Short.MAX_VALUE)
        );
        jpthanhtoanLayout.setVerticalGroup(
            jpthanhtoanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 654, Short.MAX_VALUE)
        );

        jpkhuyenmai.setBackground(new java.awt.Color(255, 0, 255));
        jpkhuyenmai.setPreferredSize(new java.awt.Dimension(919, 654));

        jPanel30.setBorder(javax.swing.BorderFactory.createTitledBorder("Danh sách sản phẩm"));

        tblkmdssp.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane15.setViewportView(tblkmdssp);

        jLabel108.setText("Áp dụng cho");

        cbokmapdung.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

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
                        .addComponent(cbokmapdung, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                    .addComponent(cbokmapdung, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel114)
                    .addComponent(txtkmtu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel115)
                    .addComponent(txtkmden, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel116)
                    .addComponent(cbokmhinhthuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addComponent(jScrollPane15, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel31.setBorder(javax.swing.BorderFactory.createTitledBorder("Danh sách khuyến mãi"));

        tblkmdskm.setModel(new javax.swing.table.DefaultTableModel(
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                .addComponent(jScrollPane14, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jLabel109.setText("Tên chương trình");

        jLabel110.setText("Mức giảm giá");

        jLabel111.setText("Thời gian bắt đầu");

        jLabel112.setText("Thời gian kết thúc");

        jLabel113.setText("Trạng thái :");

        rdokmdangdienra.setText("Đang diễn ra");

        rdokmdaketthuc.setText("Đã kết thúc");

        jPanel33.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 0, 0, 0, new java.awt.Color(0, 0, 0)));

        btnkmluu.setText("Lưu");

        btnkmmoi.setText("Mới");

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
                .addGap(36, 36, 36)
                .addComponent(btnkmluu, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addGroup(jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnkmsua, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnkmxoa, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btnkmmoi, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel32Layout = new javax.swing.GroupLayout(jPanel32);
        jPanel32.setLayout(jPanel32Layout);
        jPanel32Layout.setHorizontalGroup(
            jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel32Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel32Layout.createSequentialGroup()
                        .addComponent(rdokmdangdienra, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(rdokmdaketthuc, javax.swing.GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE))
                    .addGroup(jPanel32Layout.createSequentialGroup()
                        .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel109, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel110, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel111, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel112, javax.swing.GroupLayout.DEFAULT_SIZE, 99, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtkmmucgg, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 101, Short.MAX_VALUE)
                            .addComponent(txtkmtenct, javax.swing.GroupLayout.Alignment.LEADING)))
                    .addComponent(jLabel113, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(12, Short.MAX_VALUE))
            .addComponent(jPanel33, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                .addComponent(jLabel111)
                .addGap(8, 8, 8)
                .addComponent(jLabel112)
                .addGap(24, 24, 24)
                .addComponent(jLabel113)
                .addGap(18, 18, 18)
                .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdokmdangdienra)
                    .addComponent(rdokmdaketthuc))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 96, Short.MAX_VALUE)
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
                .addContainerGap(94, Short.MAX_VALUE))
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

        jphoadon.setBackground(new java.awt.Color(0, 153, 153));

        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder("Danh sách hóa đơn"));

        tblhddshd.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã HD", "Tên KH", "Ngày tạo", "Trạng thái", "Tên NV"
            }
        ));
        tblhddshd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblhddshdMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblhddshd);

        btnFirstHD.setText("|<");
        btnFirstHD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFirstHDActionPerformed(evt);
            }
        });

        btnPrevHD.setText("<<");
        btnPrevHD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrevHDActionPerformed(evt);
            }
        });

        txtPageHD.setText("1");

        btnNextHD.setText(">>");
        btnNextHD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextHDActionPerformed(evt);
            }
        });

        btnLastHD.setText(">|");
        btnLastHD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLastHDActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addComponent(jScrollPane3)
                .addContainerGap())
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(395, 395, 395)
                .addComponent(btnFirstHD, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnPrevHD, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtPageHD, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnNextHD, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnLastHD, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(52, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnFirstHD)
                    .addComponent(btnPrevHD)
                    .addComponent(txtPageHD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnNextHD)
                    .addComponent(btnLastHD))
                .addContainerGap())
        );

        jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder("Giỏ hàng"));

        tblhddsgh.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Mã SP", "Tên SP", "Số lượng", "Đơn giá", "Giảm giá", "Thành tiền"
            }
        ));
        tblhddsgh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblhddsghMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tblhddsgh);

        btnhdxoa.setText("Xóa");
        btnhdxoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnhdxoaActionPerformed(evt);
            }
        });

        jButton1.setText("Sửa");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addComponent(jScrollPane4)
                .addContainerGap())
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(btnhdxoa, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnhdxoa)
                    .addComponent(jButton1))
                .addContainerGap())
        );

        jPanel11.setBorder(javax.swing.BorderFactory.createTitledBorder("Danh sách sản phẩm"));

        tblhddssp.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Mã SP", "Tên SP", "Giá", "Số lượng", "Khối lượng", "Kích thước", "Phân loại", "Thương Hiệu", "Màu sắc", "Xuất xứ", "Chất liệu"
            }
        ));
        tblhddssp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblhddsspMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(tblhddssp);

        btnFirstSP.setText("|<");
        btnFirstSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFirstSPActionPerformed(evt);
            }
        });

        btnPrevSP.setText("<<");
        btnPrevSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrevSPActionPerformed(evt);
            }
        });

        btnNextSP.setText(">>");
        btnNextSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextSPActionPerformed(evt);
            }
        });

        btnLastSP.setText(">|");
        btnLastSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLastSPActionPerformed(evt);
            }
        });

        txtPageSP.setText("1");

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 694, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnFirstSP, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnPrevSP, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtPageSP, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnNextSP, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnLastSP, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(77, 77, 77))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnFirstSP)
                    .addComponent(btnPrevSP)
                    .addComponent(btnNextSP)
                    .addComponent(btnLastSP)
                    .addComponent(txtPageSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel12.setBorder(javax.swing.BorderFactory.createTitledBorder("Tạo hóa đơn"));

        jLabel21.setText("Tên KH");

        jLabel22.setText("Số ĐT");

        jPanel15.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        jLabel23.setText("Tổng tiền hàng :");

        jLabel24.setText("Khách cần trả :");

        jLabel25.setText("HT thanh toán :");

        jLabel26.setText("Tiền thừa :");

        txthdtongtien.setText("0");

        txthdkhachtra.setText("0");

        txthdtienthua.setText("0");

        cbohdthanhtoan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel30.setText("Tiền khách đưa :");

        txthdkhachdua.setText("0");

        txthdma.setText("jLabel1");

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel30, javax.swing.GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)
                    .addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(txthdtongtien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txthdkhachtra, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                        .addComponent(txthdtienthua, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                        .addComponent(cbohdthanhtoan, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(txthdkhachdua, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txthdma, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(txthdtongtien))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24)
                    .addComponent(txthdkhachtra))
                .addGap(12, 12, 12)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel25)
                    .addComponent(cbohdthanhtoan, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel30)
                    .addComponent(txthdkhachdua, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel26)
                    .addComponent(txthdtienthua, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 65, Short.MAX_VALUE)
                .addComponent(txthdma, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        btnhdthanhtoan.setText("Thanh toán");

        btnhdtaohoadon.setText("Tạo hóa đơn");
        btnhdtaohoadon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnhdtaohoadonActionPerformed(evt);
            }
        });

        btnhdhuy.setText("Hủy");

        btnhdinhoadon.setText("In hóa đơn");

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel15, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnhdthanhtoan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel13Layout.createSequentialGroup()
                                .addComponent(btnhdtaohoadon, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnhdhuy, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(26, 26, 26)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txthdtenkh, javax.swing.GroupLayout.DEFAULT_SIZE, 136, Short.MAX_VALUE)
                            .addComponent(txthdsdt))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnhdinhoadon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(txthdtenkh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22)
                    .addComponent(txthdsdt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(58, 58, 58)
                .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnhdthanhtoan, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnhdhuy, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnhdtaohoadon, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnhdinhoadon, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                .addGap(17, 17, 17))
        );

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(0, 29, Short.MAX_VALUE)
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 30, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jphoadonLayout = new javax.swing.GroupLayout(jphoadon);
        jphoadon.setLayout(jphoadonLayout);
        jphoadonLayout.setHorizontalGroup(
            jphoadonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jphoadonLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jphoadonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jphoadonLayout.createSequentialGroup()
                        .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(5, 5, 5))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jphoadonLayout.createSequentialGroup()
                        .addGroup(jphoadonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jphoadonLayout.setVerticalGroup(
            jphoadonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jphoadonLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jphoadonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jphoadonLayout.createSequentialGroup()
                        .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jpdoimk.setBackground(new java.awt.Color(255, 255, 255));
        jpdoimk.setPreferredSize(new java.awt.Dimension(919, 654));

        lblid.setText("ID");

        jLabel62.setText("Tên đăng nhập");

        jLabel63.setText("Mật khẩu");

        jLabel64.setText("Vai trò");

        btnthem.setText("Thêm");

        btnsua.setText("Sửa");

        jLabel120.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel120.setText("QUẢN LÝ TÀI KHOẢN");

        tbltaikhoan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ID", "Tên đăng nhập", "Mật khẩu", "Vai trò"
            }
        ));
        jScrollPane17.setViewportView(tbltaikhoan);

        buttonGroup1.add(rdocch);
        rdocch.setText("Chủ cửa hàng");

        buttonGroup1.add(rdonv);
        rdonv.setText("Nhân viên");

        lblmkm.setText("Mật khẩu mới");

        lblmkxn.setText("Mật khẩu xác nhận");

        btnxoa.setText("Xóa");

        btndoimk.setText("Đổi mật khẩu");

        btnxacnhan.setText("Xác nhận");

        javax.swing.GroupLayout jpdoimkLayout = new javax.swing.GroupLayout(jpdoimk);
        jpdoimk.setLayout(jpdoimkLayout);
        jpdoimkLayout.setHorizontalGroup(
            jpdoimkLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpdoimkLayout.createSequentialGroup()
                .addGap(76, 76, 76)
                .addGroup(jpdoimkLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane17, javax.swing.GroupLayout.PREFERRED_SIZE, 800, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jpdoimkLayout.createSequentialGroup()
                        .addGroup(jpdoimkLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(lblid, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jpdoimkLayout.createSequentialGroup()
                                .addGroup(jpdoimkLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel62)
                                    .addComponent(jLabel63)
                                    .addComponent(jLabel64)
                                    .addComponent(btnthem))
                                .addGap(41, 41, 41)
                                .addGroup(jpdoimkLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txttendangnhap, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtmk, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpdoimkLayout.createSequentialGroup()
                                        .addGroup(jpdoimkLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(rdocch, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(btnsua))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(jpdoimkLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(btnxoa)
                                            .addComponent(rdonv, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jpdoimkLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jpdoimkLayout.createSequentialGroup()
                                .addComponent(lblmkm)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtmkm, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jpdoimkLayout.createSequentialGroup()
                                .addComponent(lblmkxn)
                                .addGap(27, 27, 27)
                                .addComponent(txtmkxn, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jpdoimkLayout.createSequentialGroup()
                                .addComponent(btnxacnhan)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btndoimk)
                                .addGap(75, 75, 75)))))
                .addContainerGap(43, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpdoimkLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel120)
                .addGap(328, 328, 328))
        );
        jpdoimkLayout.setVerticalGroup(
            jpdoimkLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpdoimkLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jLabel120)
                .addGap(18, 18, 18)
                .addComponent(lblid)
                .addGap(18, 18, 18)
                .addGroup(jpdoimkLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel62)
                    .addComponent(txttendangnhap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtmkm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblmkm))
                .addGap(18, 18, 18)
                .addGroup(jpdoimkLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel63)
                    .addComponent(txtmk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtmkxn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblmkxn))
                .addGap(18, 18, 18)
                .addGroup(jpdoimkLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel64)
                    .addComponent(rdocch)
                    .addComponent(rdonv))
                .addGap(33, 33, 33)
                .addGroup(jpdoimkLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnthem)
                    .addComponent(btnsua)
                    .addComponent(btnxoa)
                    .addComponent(btndoimk)
                    .addComponent(btnxacnhan))
                .addGap(32, 32, 32)
                .addComponent(jScrollPane17, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(141, Short.MAX_VALUE))
        );

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

        jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnchitietsp, btnsuasp, btnthemsp, tbnxoasp});

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
                .addContainerGap(77, Short.MAX_VALUE))
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
                .addContainerGap(124, Short.MAX_VALUE))
        );

        jpthongke.setBackground(new java.awt.Color(0, 102, 255));
        jpthongke.setPreferredSize(new java.awt.Dimension(919, 654));

        jPanel20.setPreferredSize(new java.awt.Dimension(202, 149));

        jLabel66.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel66.setText("Tổng doanh thu ngày");

        jLabel67.setText("0 đơn hàng");

        jLabel68.setText("Thành công");

        jLabel69.setText("Bị hủy");

        jLabel10.setText("0");

        jLabel70.setText("0");

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel20Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel66, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel20Layout.createSequentialGroup()
                                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel68, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel69, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel70, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(jPanel20Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel67, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(44, Short.MAX_VALUE))
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel66)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel67)
                .addGap(18, 18, 18)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel68)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel69)
                    .addComponent(jLabel70))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel21.setPreferredSize(new java.awt.Dimension(202, 149));

        jLabel71.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel71.setText("Tổng doanh thu năm");

        jLabel72.setText("0 đơn hàng");

        jLabel73.setText("Thành công");

        jLabel74.setText("Bị hủy");

        jLabel75.setText("0");

        jLabel76.setText("0");

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel21Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel71, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel21Layout.createSequentialGroup()
                                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel73, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel74, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel75, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel76, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(jPanel21Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel72, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(44, Short.MAX_VALUE))
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel71)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel72)
                .addGap(18, 18, 18)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel73)
                    .addComponent(jLabel75))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel74)
                    .addComponent(jLabel76))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel22.setPreferredSize(new java.awt.Dimension(202, 149));

        jLabel77.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel77.setText("Tổng doanh thu tháng");

        jLabel78.setText("0 đơn hàng");

        jLabel79.setText("Thành công");

        jLabel80.setText("Bị hủy");

        jLabel81.setText("0");

        jLabel82.setText("0");

        javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
        jPanel22.setLayout(jPanel22Layout);
        jPanel22Layout.setHorizontalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel22Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel77, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel22Layout.createSequentialGroup()
                                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel79, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel80, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel81, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel82, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(jPanel22Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel78, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(44, Short.MAX_VALUE))
        );
        jPanel22Layout.setVerticalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel77)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel78)
                .addGap(18, 18, 18)
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel79)
                    .addComponent(jLabel81))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel80)
                    .addComponent(jLabel82))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel23.setPreferredSize(new java.awt.Dimension(202, 149));

        jLabel83.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel83.setText("Tổng đơn hàng");

        jLabel84.setText("0 đơn hàng");

        jLabel85.setText("Thành công");

        jLabel86.setText("Bị hủy");

        jLabel87.setText("0");

        jLabel88.setText("0");

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
                            .addComponent(jLabel87, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel88, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel23Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel83, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel84, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(44, Short.MAX_VALUE))
        );
        jPanel23Layout.setVerticalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel83)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel84)
                .addGap(18, 18, 18)
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel85)
                    .addComponent(jLabel87))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel86)
                    .addComponent(jLabel88))
                .addContainerGap(28, Short.MAX_VALUE))
        );

        jLabel89.setText("Loại thời gian");

        jComboBox6.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel90.setText("Ngày bắt đầu");

        jLabel91.setText("Ngày kết thúc");

        jButton19.setText("Tìm kiếm");

        jLabel92.setText("Năm");

        jComboBox7.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jPanel26.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 1, 0, 0, new java.awt.Color(0, 0, 0)));

        jLabel93.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel93.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel93.setText("Chi tiết doanh thu");

        jTable8.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane6.setViewportView(jTable8);

        javax.swing.GroupLayout jPanel26Layout = new javax.swing.GroupLayout(jPanel26);
        jPanel26.setLayout(jPanel26Layout);
        jPanel26Layout.setHorizontalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel26Layout.createSequentialGroup()
                .addGap(269, 269, 269)
                .addComponent(jLabel93, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(282, Short.MAX_VALUE))
            .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel26Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 694, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        jPanel26Layout.setVerticalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel26Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel93, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel26Layout.createSequentialGroup()
                    .addContainerGap(62, Short.MAX_VALUE)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 303, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap()))
        );

        javax.swing.GroupLayout jPanel24Layout = new javax.swing.GroupLayout(jPanel24);
        jPanel24.setLayout(jPanel24Layout);
        jPanel24Layout.setHorizontalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel92, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox7, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(46, 46, 46)
                .addComponent(jPanel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel24Layout.setVerticalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addComponent(jLabel92)
                .addGap(18, 18, 18)
                .addComponent(jComboBox7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(272, Short.MAX_VALUE))
            .addComponent(jPanel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jTabbedPane3.addTab("Doanh thu", jPanel24);

        jLabel94.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel94.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel94.setText("Thông tin chi tiết sản phẩm");

        jTable9.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane11.setViewportView(jTable9);

        javax.swing.GroupLayout jPanel25Layout = new javax.swing.GroupLayout(jPanel25);
        jPanel25.setLayout(jPanel25Layout);
        jPanel25Layout.setHorizontalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addGap(336, 336, 336)
                .addComponent(jLabel94, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(344, Short.MAX_VALUE))
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane11)
                .addContainerGap())
        );
        jPanel25Layout.setVerticalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jLabel94)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 303, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(11, Short.MAX_VALUE))
        );

        jTabbedPane3.addTab("Sản phẩm", jPanel25);

        javax.swing.GroupLayout jpthongkeLayout = new javax.swing.GroupLayout(jpthongke);
        jpthongke.setLayout(jpthongkeLayout);
        jpthongkeLayout.setHorizontalGroup(
            jpthongkeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpthongkeLayout.createSequentialGroup()
                .addGap(63, 63, 63)
                .addComponent(jLabel89, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jComboBox6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(63, 63, 63)
                .addComponent(jLabel90, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(155, 155, 155)
                .addComponent(jLabel91, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton19)
                .addGap(40, 40, 40))
            .addGroup(jpthongkeLayout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jpthongkeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTabbedPane3)
                    .addGroup(jpthongkeLayout.createSequentialGroup()
                        .addComponent(jPanel23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(95, Short.MAX_VALUE))
        );
        jpthongkeLayout.setVerticalGroup(
            jpthongkeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpthongkeLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jpthongkeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel21, javax.swing.GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE)
                    .addComponent(jPanel20, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE)
                    .addComponent(jPanel23, javax.swing.GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE)
                    .addComponent(jPanel22, javax.swing.GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jpthongkeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jpthongkeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel89)
                        .addComponent(jComboBox6, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel90)
                        .addComponent(jButton19))
                    .addComponent(jLabel91))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTabbedPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 402, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
        );

        jpnhanvien.setBackground(new java.awt.Color(255, 255, 153));
        jpnhanvien.setPreferredSize(new java.awt.Dimension(919, 654));

        jPanel27.setBorder(javax.swing.BorderFactory.createTitledBorder("Thông tin nhân viên"));

        jLabel95.setText("Tên nhân viên");

        jLabel96.setText("Mã nhân viên");

        jLabel97.setText("Giới tính");

        jLabel98.setText("Ngày sinh");

        jLabel99.setText("Số ĐT");

        jLabel100.setBorder(new javax.swing.border.MatteBorder(null));

        jLabel105.setText("Địa chỉ :");

        txtnvdiachi.setColumns(20);
        txtnvdiachi.setRows(5);
        jScrollPane13.setViewportView(txtnvdiachi);

        jLabel9.setText("Email");

        javax.swing.GroupLayout jPanel27Layout = new javax.swing.GroupLayout(jPanel27);
        jPanel27.setLayout(jPanel27Layout);
        jPanel27Layout.setHorizontalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel27Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel27Layout.createSequentialGroup()
                        .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel27Layout.createSequentialGroup()
                                .addComponent(jLabel99, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtnvsodt, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel98, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(30, 30, 30)
                        .addComponent(jScrollPane13))
                    .addGroup(jPanel27Layout.createSequentialGroup()
                        .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel27Layout.createSequentialGroup()
                                .addComponent(jLabel97, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtnvgioitinh, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel27Layout.createSequentialGroup()
                                .addComponent(jLabel95, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtnvtennv, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel27Layout.createSequentialGroup()
                                .addComponent(jLabel96, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtnvmanv, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(30, 30, 30)
                        .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel27Layout.createSequentialGroup()
                                .addComponent(jLabel105, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel27Layout.createSequentialGroup()
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 98, Short.MAX_VALUE)
                                .addComponent(txtnvemail, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(18, 18, 18)
                .addComponent(jLabel100, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel27Layout.setVerticalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel27Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel100, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel27Layout.createSequentialGroup()
                        .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel96)
                            .addComponent(txtnvmanv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9)
                            .addComponent(txtnvemail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel95)
                            .addComponent(txtnvtennv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel97)
                            .addComponent(txtnvgioitinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel105))
                        .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel27Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jLabel98)
                                .addGap(18, 18, 18)
                                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel99)
                                    .addComponent(txtnvsodt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel27Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jScrollPane13, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(28, Short.MAX_VALUE))
        );

        jPanel28.setBorder(javax.swing.BorderFactory.createTitledBorder("Danh sách nhân viên"));

        tblnv.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã NV", "Tên", "Giới tính", "Ngày sinh", "Số ĐT", "Email", "Địa chỉ"
            }
        ));
        tblnv.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblnvMouseClicked(evt);
            }
        });
        jScrollPane12.setViewportView(tblnv);

        jLabel103.setText("Tổng nhân viên :");

        jLabel104.setText("-");

        javax.swing.GroupLayout jPanel28Layout = new javax.swing.GroupLayout(jPanel28);
        jPanel28.setLayout(jPanel28Layout);
        jPanel28Layout.setHorizontalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel28Layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addComponent(jLabel103, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel104, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel28Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane12)
                .addContainerGap())
        );
        jPanel28Layout.setVerticalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel28Layout.createSequentialGroup()
                .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel103)
                    .addComponent(jLabel104))
                .addGap(0, 9, Short.MAX_VALUE))
        );

        txtnvthem.setText("Thêm");
        txtnvthem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtnvthemActionPerformed(evt);
            }
        });

        txtnvsua.setText("Sửa");
        txtnvsua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtnvsuaActionPerformed(evt);
            }
        });

        txtnvxoa.setText("Xóa");
        txtnvxoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtnvxoaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel29Layout = new javax.swing.GroupLayout(jPanel29);
        jPanel29.setLayout(jPanel29Layout);
        jPanel29Layout.setHorizontalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel29Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel29Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(txtnvthem))
                    .addComponent(txtnvsua, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtnvxoa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel29Layout.setVerticalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel29Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtnvthem, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtnvsua, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                .addComponent(txtnvxoa, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jpnhanvienLayout = new javax.swing.GroupLayout(jpnhanvien);
        jpnhanvien.setLayout(jpnhanvienLayout);
        jpnhanvienLayout.setHorizontalGroup(
            jpnhanvienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnhanvienLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jpnhanvienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jpnhanvienLayout.createSequentialGroup()
                        .addComponent(jPanel27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel29, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jpnhanvienLayout.setVerticalGroup(
            jpnhanvienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnhanvienLayout.createSequentialGroup()
                .addGroup(jpnhanvienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpnhanvienLayout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(jPanel29, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpnhanvienLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel27, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)))
                .addComponent(jPanel28, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(40, Short.MAX_VALUE))
        );

        jpkhachhang.setBackground(new java.awt.Color(51, 255, 204));
        jpkhachhang.setPreferredSize(new java.awt.Dimension(919, 654));

        javax.swing.GroupLayout jpkhachhangLayout = new javax.swing.GroupLayout(jpkhachhang);
        jpkhachhang.setLayout(jpkhachhangLayout);
        jpkhachhangLayout.setHorizontalGroup(
            jpkhachhangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 919, Short.MAX_VALUE)
        );
        jpkhachhangLayout.setVerticalGroup(
            jpkhachhangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 654, Short.MAX_VALUE)
        );

        jplichsu.setBackground(new java.awt.Color(204, 0, 0));
        jplichsu.setPreferredSize(new java.awt.Dimension(882, 654));

        jPanel17.setBorder(javax.swing.BorderFactory.createTitledBorder("Danh sách hóa đơn"));

        jTable6.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane8.setViewportView(jTable6);

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 616, Short.MAX_VALUE)
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 263, Short.MAX_VALUE)
        );

        jPanel18.setBorder(javax.swing.BorderFactory.createTitledBorder("Thông tin sản phẩm"));

        jTable7.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane9.setViewportView(jTable7);

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane9)
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );

        jLabel44.setText("Mã nhân viên");

        jLabel45.setText("Tên NV");

        jLabel46.setText("Tên KH");

        jLabel47.setText("Số ĐT");

        jLabel48.setText("Địa chỉ");

        jLabel49.setText("Tổng tiền hàng");

        jLabel50.setText("Ngày tạo");

        jLabel51.setText("Trạng thái");

        jLabel52.setText("Lý do hủy");

        jLabel53.setText("-");

        jLabel54.setText("-");

        jLabel55.setText("-");

        jLabel56.setText("-");

        jLabel57.setText("-");

        jLabel58.setText("-");

        jLabel59.setText("-");

        jTextArea3.setColumns(20);
        jTextArea3.setRows(5);
        jScrollPane10.setViewportView(jTextArea3);

        jLabel60.setText("0");

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel52, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addComponent(jLabel45, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel54, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addComponent(jLabel44, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel53, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addComponent(jLabel46, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel55, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addComponent(jLabel47, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel56, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addComponent(jLabel48, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel57, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addComponent(jLabel49, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel60, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addComponent(jLabel50, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel58, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addComponent(jLabel51, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel59, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane10))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel44)
                    .addComponent(jLabel53))
                .addGap(18, 18, 18)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel45)
                    .addComponent(jLabel54))
                .addGap(18, 18, 18)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel46)
                    .addComponent(jLabel55))
                .addGap(18, 18, 18)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel47)
                    .addComponent(jLabel56))
                .addGap(18, 18, 18)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel48)
                    .addComponent(jLabel57))
                .addGap(18, 18, 18)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel49)
                    .addComponent(jLabel60))
                .addGap(18, 18, 18)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel50)
                    .addComponent(jLabel58))
                .addGap(18, 18, 18)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel51)
                    .addComponent(jLabel59))
                .addGap(18, 18, 18)
                .addComponent(jLabel52)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(204, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jplichsuLayout = new javax.swing.GroupLayout(jplichsu);
        jplichsu.setLayout(jplichsuLayout);
        jplichsuLayout.setHorizontalGroup(
            jplichsuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jplichsuLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jplichsuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jplichsuLayout.setVerticalGroup(
            jplichsuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jplichsuLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jplichsuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jplichsuLayout.createSequentialGroup()
                        .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );

        mausac1.setBackground(new java.awt.Color(255, 255, 255));
        mausac1.setBorder(javax.swing.BorderFactory.createTitledBorder("Thông tin thuộc tính"));
        mausac1.setForeground(new java.awt.Color(102, 102, 255));

        javax.swing.GroupLayout mausac1Layout = new javax.swing.GroupLayout(mausac1);
        mausac1.setLayout(mausac1Layout);
        mausac1Layout.setHorizontalGroup(
            mausac1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        mausac1Layout.setVerticalGroup(
            mausac1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        txttenthuonghieu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txttenthuonghieuActionPerformed(evt);
            }
        });

        btnsuathuonghieu.setText("Sửa");
        btnsuathuonghieu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsuathuonghieuActionPerformed(evt);
            }
        });

        jLabel27.setText("Tên thuộc tính");

        btnthemthuonghieu.setText("Thêm");
        btnthemthuonghieu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnthemthuonghieuActionPerformed(evt);
            }
        });

        btnxoathuonghieu.setText("Xóa");
        btnxoathuonghieu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnxoathuonghieuActionPerformed(evt);
            }
        });

        tbldsthuonghieu.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "ID", "Tên thuộc tính"
            }
        ));
        tbldsthuonghieu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbldsthuonghieuMouseClicked(evt);
            }
        });
        jScrollPane18.setViewportView(tbldsthuonghieu);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Thương hiệu");

        javax.swing.GroupLayout panelThuongHieuLayout = new javax.swing.GroupLayout(panelThuongHieu);
        panelThuongHieu.setLayout(panelThuongHieuLayout);
        panelThuongHieuLayout.setHorizontalGroup(
            panelThuongHieuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelThuongHieuLayout.createSequentialGroup()
                .addGap(65, 65, 65)
                .addComponent(jLabel27)
                .addGap(18, 18, 18)
                .addGroup(panelThuongHieuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelThuongHieuLayout.createSequentialGroup()
                        .addComponent(txttenthuonghieu, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelThuongHieuLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(344, 344, 344))))
            .addGroup(panelThuongHieuLayout.createSequentialGroup()
                .addGroup(panelThuongHieuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelThuongHieuLayout.createSequentialGroup()
                        .addGap(119, 119, 119)
                        .addComponent(btnthemthuonghieu)
                        .addGap(44, 44, 44)
                        .addComponent(btnsuathuonghieu)
                        .addGap(45, 45, 45)
                        .addComponent(btnxoathuonghieu)
                        .addGap(93, 93, 93)
                        .addComponent(txtidthuonghieu, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelThuongHieuLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane18, javax.swing.GroupLayout.PREFERRED_SIZE, 738, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(panelThuongHieuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelThuongHieuLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(mausac1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(744, Short.MAX_VALUE)))
        );
        panelThuongHieuLayout.setVerticalGroup(
            panelThuongHieuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelThuongHieuLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addGroup(panelThuongHieuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txttenthuonghieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel27))
                .addGroup(panelThuongHieuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelThuongHieuLayout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addGroup(panelThuongHieuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnthemthuonghieu, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnsuathuonghieu, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnxoathuonghieu, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(30, 30, 30)
                        .addComponent(jScrollPane18, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelThuongHieuLayout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addComponent(txtidthuonghieu, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(panelThuongHieuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelThuongHieuLayout.createSequentialGroup()
                    .addContainerGap(428, Short.MAX_VALUE)
                    .addComponent(mausac1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap()))
        );

        mausac.setBackground(new java.awt.Color(255, 255, 255));
        mausac.setBorder(javax.swing.BorderFactory.createTitledBorder("Thông tin thuộc tính"));
        mausac.setForeground(new java.awt.Color(102, 102, 255));

        javax.swing.GroupLayout mausacLayout = new javax.swing.GroupLayout(mausac);
        mausac.setLayout(mausacLayout);
        mausacLayout.setHorizontalGroup(
            mausacLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        mausacLayout.setVerticalGroup(
            mausacLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        txttenchatlieu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txttenchatlieuActionPerformed(evt);
            }
        });

        btnsuachatlieu.setText("Sửa");
        btnsuachatlieu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsuachatlieuActionPerformed(evt);
            }
        });

        jLabel19.setText("Tên thuộc tính");

        btnthemchatlieu.setText("Thêm");
        btnthemchatlieu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnthemchatlieuActionPerformed(evt);
            }
        });

        btnxoachatlieu.setText("Xóa");
        btnxoachatlieu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnxoachatlieuActionPerformed(evt);
            }
        });

        tbldschatlieu.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "ID", "Tên thuộc tính"
            }
        ));
        tbldschatlieu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbldschatlieuMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbldschatlieu);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Chất liệu");

        javax.swing.GroupLayout panelChatLieuLayout = new javax.swing.GroupLayout(panelChatLieu);
        panelChatLieu.setLayout(panelChatLieuLayout);
        panelChatLieuLayout.setHorizontalGroup(
            panelChatLieuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelChatLieuLayout.createSequentialGroup()
                .addGap(65, 65, 65)
                .addComponent(jLabel19)
                .addGap(18, 18, 18)
                .addGroup(panelChatLieuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelChatLieuLayout.createSequentialGroup()
                        .addComponent(txttenchatlieu, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelChatLieuLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(344, 344, 344))))
            .addGroup(panelChatLieuLayout.createSequentialGroup()
                .addGroup(panelChatLieuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelChatLieuLayout.createSequentialGroup()
                        .addGap(119, 119, 119)
                        .addComponent(btnthemchatlieu)
                        .addGap(44, 44, 44)
                        .addComponent(btnsuachatlieu)
                        .addGap(41, 41, 41)
                        .addComponent(btnxoachatlieu)
                        .addGap(97, 97, 97)
                        .addComponent(txtidchatlieu, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelChatLieuLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 738, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(panelChatLieuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelChatLieuLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(mausac, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(744, Short.MAX_VALUE)))
        );
        panelChatLieuLayout.setVerticalGroup(
            panelChatLieuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelChatLieuLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addGroup(panelChatLieuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txttenchatlieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19))
                .addGroup(panelChatLieuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelChatLieuLayout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addGroup(panelChatLieuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnthemchatlieu, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnsuachatlieu, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnxoachatlieu, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(30, 30, 30)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelChatLieuLayout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addComponent(txtidchatlieu, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(panelChatLieuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelChatLieuLayout.createSequentialGroup()
                    .addContainerGap(428, Short.MAX_VALUE)
                    .addComponent(mausac, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap()))
        );

        mausac2.setBackground(new java.awt.Color(255, 255, 255));
        mausac2.setBorder(javax.swing.BorderFactory.createTitledBorder("Thông tin thuộc tính"));
        mausac2.setForeground(new java.awt.Color(102, 102, 255));

        javax.swing.GroupLayout mausac2Layout = new javax.swing.GroupLayout(mausac2);
        mausac2.setLayout(mausac2Layout);
        mausac2Layout.setHorizontalGroup(
            mausac2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        mausac2Layout.setVerticalGroup(
            mausac2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        txttenphanloai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txttenphanloaiActionPerformed(evt);
            }
        });

        btnsuaphanloai.setText("Sửa");
        btnsuaphanloai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsuaphanloaiActionPerformed(evt);
            }
        });

        jLabel28.setText("Tên thuộc tính");

        btnthemphanloai.setText("Thêm");
        btnthemphanloai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnthemphanloaiActionPerformed(evt);
            }
        });

        btnxoaphanloai.setText("Xóa");
        btnxoaphanloai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnxoaphanloaiActionPerformed(evt);
            }
        });

        tbldsphanloai.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "ID", "Tên thuộc tính"
            }
        ));
        tbldsphanloai.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbldsphanloaiMouseClicked(evt);
            }
        });
        jScrollPane19.setViewportView(tbldsphanloai);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Phân loại");

        javax.swing.GroupLayout panelPhanLoaiLayout = new javax.swing.GroupLayout(panelPhanLoai);
        panelPhanLoai.setLayout(panelPhanLoaiLayout);
        panelPhanLoaiLayout.setHorizontalGroup(
            panelPhanLoaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPhanLoaiLayout.createSequentialGroup()
                .addGap(65, 65, 65)
                .addComponent(jLabel28)
                .addGap(18, 18, 18)
                .addGroup(panelPhanLoaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelPhanLoaiLayout.createSequentialGroup()
                        .addComponent(txttenphanloai, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelPhanLoaiLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(panelPhanLoaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelPhanLoaiLayout.createSequentialGroup()
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(344, 344, 344))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelPhanLoaiLayout.createSequentialGroup()
                                .addComponent(btnsuaphanloai)
                                .addGap(38, 38, 38)
                                .addComponent(btnxoaphanloai)
                                .addGap(331, 331, 331))))))
            .addGroup(panelPhanLoaiLayout.createSequentialGroup()
                .addGroup(panelPhanLoaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelPhanLoaiLayout.createSequentialGroup()
                        .addGap(119, 119, 119)
                        .addComponent(btnthemphanloai)
                        .addGap(326, 326, 326)
                        .addComponent(txtidphanloai, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelPhanLoaiLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane19, javax.swing.GroupLayout.PREFERRED_SIZE, 738, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(panelPhanLoaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelPhanLoaiLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(mausac2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(744, Short.MAX_VALUE)))
        );
        panelPhanLoaiLayout.setVerticalGroup(
            panelPhanLoaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPhanLoaiLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addGroup(panelPhanLoaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txttenphanloai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel28))
                .addGroup(panelPhanLoaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelPhanLoaiLayout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addGroup(panelPhanLoaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnthemphanloai, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnsuaphanloai, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnxoaphanloai, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(30, 30, 30)
                        .addComponent(jScrollPane19, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelPhanLoaiLayout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addComponent(txtidphanloai, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(panelPhanLoaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelPhanLoaiLayout.createSequentialGroup()
                    .addContainerGap(428, Short.MAX_VALUE)
                    .addComponent(mausac2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap()))
        );

        mausac3.setBackground(new java.awt.Color(255, 255, 255));
        mausac3.setBorder(javax.swing.BorderFactory.createTitledBorder("Thông tin thuộc tính"));
        mausac3.setForeground(new java.awt.Color(102, 102, 255));

        javax.swing.GroupLayout mausac3Layout = new javax.swing.GroupLayout(mausac3);
        mausac3.setLayout(mausac3Layout);
        mausac3Layout.setHorizontalGroup(
            mausac3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        mausac3Layout.setVerticalGroup(
            mausac3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        txttenxuatxu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txttenxuatxuActionPerformed(evt);
            }
        });

        btnsuaxuatxu.setText("Sửa");
        btnsuaxuatxu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsuaxuatxuActionPerformed(evt);
            }
        });

        jLabel29.setText("Tên thuộc tính");

        btnthemxuatxu.setText("Thêm");
        btnthemxuatxu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnthemxuatxuActionPerformed(evt);
            }
        });

        btnxoaxuatxu.setText("Xóa");
        btnxoaxuatxu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnxoaxuatxuActionPerformed(evt);
            }
        });

        tbldsxuatxu.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "ID", "Tên thuộc tính"
            }
        ));
        tbldsxuatxu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbldsxuatxuMouseClicked(evt);
            }
        });
        jScrollPane21.setViewportView(tbldsxuatxu);

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Xuất xứ");

        javax.swing.GroupLayout panelXuatXuLayout = new javax.swing.GroupLayout(panelXuatXu);
        panelXuatXu.setLayout(panelXuatXuLayout);
        panelXuatXuLayout.setHorizontalGroup(
            panelXuatXuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelXuatXuLayout.createSequentialGroup()
                .addGap(65, 65, 65)
                .addComponent(jLabel29)
                .addGap(18, 18, 18)
                .addGroup(panelXuatXuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelXuatXuLayout.createSequentialGroup()
                        .addComponent(txttenxuatxu, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelXuatXuLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(panelXuatXuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelXuatXuLayout.createSequentialGroup()
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(344, 344, 344))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelXuatXuLayout.createSequentialGroup()
                                .addComponent(btnsuaxuatxu)
                                .addGap(39, 39, 39)
                                .addComponent(btnxoaxuatxu)
                                .addGap(327, 327, 327))))))
            .addGroup(panelXuatXuLayout.createSequentialGroup()
                .addGroup(panelXuatXuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelXuatXuLayout.createSequentialGroup()
                        .addGap(119, 119, 119)
                        .addComponent(btnthemxuatxu)
                        .addGap(326, 326, 326)
                        .addComponent(txtidxuatxu, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelXuatXuLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane21, javax.swing.GroupLayout.PREFERRED_SIZE, 738, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(panelXuatXuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelXuatXuLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(mausac3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(744, Short.MAX_VALUE)))
        );
        panelXuatXuLayout.setVerticalGroup(
            panelXuatXuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelXuatXuLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addGroup(panelXuatXuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txttenxuatxu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel29))
                .addGroup(panelXuatXuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelXuatXuLayout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addGroup(panelXuatXuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnthemxuatxu, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnsuaxuatxu, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnxoaxuatxu, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(30, 30, 30)
                        .addComponent(jScrollPane21, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelXuatXuLayout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addComponent(txtidxuatxu, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(panelXuatXuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelXuatXuLayout.createSequentialGroup()
                    .addContainerGap(428, Short.MAX_VALUE)
                    .addComponent(mausac3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap()))
        );

        mausac4.setBackground(new java.awt.Color(255, 255, 255));
        mausac4.setBorder(javax.swing.BorderFactory.createTitledBorder("Thông tin thuộc tính"));
        mausac4.setForeground(new java.awt.Color(102, 102, 255));

        javax.swing.GroupLayout mausac4Layout = new javax.swing.GroupLayout(mausac4);
        mausac4.setLayout(mausac4Layout);
        mausac4Layout.setHorizontalGroup(
            mausac4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        mausac4Layout.setVerticalGroup(
            mausac4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        txttenmausac.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txttenmausacActionPerformed(evt);
            }
        });

        btnsuamausac.setText("Sửa");
        btnsuamausac.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsuamausacActionPerformed(evt);
            }
        });

        jLabel37.setText("Tên thuộc tính");

        btnthemmausac.setText("Thêm");
        btnthemmausac.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnthemmausacActionPerformed(evt);
            }
        });

        btnxoamausac.setText("Xóa");
        btnxoamausac.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnxoamausacActionPerformed(evt);
            }
        });

        tbldsmausac.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "ID", "Tên thuộc tính"
            }
        ));
        tbldsmausac.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbldsmausacMouseClicked(evt);
            }
        });
        jScrollPane22.setViewportView(tbldsmausac);

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Màu sắc");

        javax.swing.GroupLayout panelMauSacLayout = new javax.swing.GroupLayout(panelMauSac);
        panelMauSac.setLayout(panelMauSacLayout);
        panelMauSacLayout.setHorizontalGroup(
            panelMauSacLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelMauSacLayout.createSequentialGroup()
                .addGap(65, 65, 65)
                .addComponent(jLabel37)
                .addGap(18, 18, 18)
                .addGroup(panelMauSacLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelMauSacLayout.createSequentialGroup()
                        .addComponent(txttenmausac, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelMauSacLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(344, 344, 344))))
            .addGroup(panelMauSacLayout.createSequentialGroup()
                .addGroup(panelMauSacLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelMauSacLayout.createSequentialGroup()
                        .addGap(119, 119, 119)
                        .addComponent(btnthemmausac)
                        .addGap(47, 47, 47)
                        .addComponent(btnsuamausac)
                        .addGap(40, 40, 40)
                        .addComponent(btnxoamausac)
                        .addGap(95, 95, 95)
                        .addComponent(txtidmausac, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelMauSacLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane22, javax.swing.GroupLayout.PREFERRED_SIZE, 738, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(panelMauSacLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelMauSacLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(mausac4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(744, Short.MAX_VALUE)))
        );
        panelMauSacLayout.setVerticalGroup(
            panelMauSacLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelMauSacLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addGroup(panelMauSacLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txttenmausac, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel37))
                .addGroup(panelMauSacLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelMauSacLayout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addGroup(panelMauSacLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnthemmausac, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnsuamausac, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnxoamausac, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(30, 30, 30)
                        .addComponent(jScrollPane22, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelMauSacLayout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addComponent(txtidmausac, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(panelMauSacLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelMauSacLayout.createSequentialGroup()
                    .addContainerGap(428, Short.MAX_VALUE)
                    .addComponent(mausac4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap()))
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1001, Short.MAX_VALUE)
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jpdoimk, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1001, Short.MAX_VALUE))
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jpkhuyenmai, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1001, Short.MAX_VALUE))
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jpsanpham, javax.swing.GroupLayout.DEFAULT_SIZE, 1001, Short.MAX_VALUE))
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jpthongke, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1001, Short.MAX_VALUE))
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jpnhanvien, javax.swing.GroupLayout.DEFAULT_SIZE, 1001, Short.MAX_VALUE))
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jpkhachhang, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1001, Short.MAX_VALUE))
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jplichsu, javax.swing.GroupLayout.DEFAULT_SIZE, 1001, Short.MAX_VALUE))
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jpthanhtoan, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1001, Short.MAX_VALUE))
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jphoadon, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel5Layout.createSequentialGroup()
                    .addGap(362, 362, 362)
                    .addComponent(panelChatLieu, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(363, Short.MAX_VALUE)))
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel5Layout.createSequentialGroup()
                    .addGap(362, 362, 362)
                    .addComponent(panelThuongHieu, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(363, Short.MAX_VALUE)))
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel5Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(panelPhanLoai, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(205, Short.MAX_VALUE)))
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel5Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(panelXuatXu, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(205, Short.MAX_VALUE)))
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel5Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(panelMauSac, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(205, Short.MAX_VALUE)))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 654, Short.MAX_VALUE)
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jpdoimk, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jpkhuyenmai, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jpsanpham, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jpthongke, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jpnhanvien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jpkhachhang, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jplichsu, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jpthanhtoan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jphoadon, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel5Layout.createSequentialGroup()
                    .addGap(283, 283, 283)
                    .addComponent(panelChatLieu, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(284, Short.MAX_VALUE)))
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel5Layout.createSequentialGroup()
                    .addGap(283, 283, 283)
                    .addComponent(panelThuongHieu, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(284, Short.MAX_VALUE)))
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel5Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(panelPhanLoai, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(210, Short.MAX_VALUE)))
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel5Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(panelXuatXu, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(210, Short.MAX_VALUE)))
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel5Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(panelMauSac, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(210, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void lblthongkeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblthongkeMouseClicked
        jpthongke.setVisible(true);
        jpsanpham.setVisible(false);
        jpnhanvien.setVisible(false);
        jphoadon.setVisible(false);
        jpkhachhang.setVisible(false);
        jplichsu.setVisible(false);
        jpkhuyenmai.setVisible(false);
        jpdoimk.setVisible(false);
    }//GEN-LAST:event_lblthongkeMouseClicked

    private void lblsanphamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblsanphamMouseClicked

        jpsanpham.setVisible(true);
        jpthongke.setVisible(false);
        jpnhanvien.setVisible(false);
        jphoadon.setVisible(false);
        jpkhachhang.setVisible(false);
        jplichsu.setVisible(false);
        jpkhuyenmai.setVisible(false);
        jpdoimk.setVisible(false);
        fillTBLSanPham(spservice.gettAll());
    }//GEN-LAST:event_lblsanphamMouseClicked
    private void fillTBLSanPham(List<SanPham> l) {
        dtm = (DefaultTableModel) tblsanpham.getModel();
        dtm.setRowCount(0);
        for (SanPham x : spservice.gettAll()) {
            dtm.addRow(new Object[]{
                x.getId(), x.getMaSP(), x.getTenSP()
            });
        }
    }

    private void ShowSP(int i) {
        lblidsp.setText(tblsanpham.getValueAt(i, 0).toString());
        txtmasp.setText(tblsanpham.getValueAt(i, 1).toString());
        txttensp.setText(tblsanpham.getValueAt(i, 2).toString());
    }

    private SanPham readFormSP() {
        SanPham sp = new SanPham();
        sp.setMaSP(txtmasp.getText());
        sp.setTenSP(txttensp.getText());
        return sp;
    }
    private void lblnhanvienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblnhanvienMouseClicked
        jpnhanvien.setVisible(true);
        jpthongke.setVisible(false);
        jpsanpham.setVisible(false);
        jphoadon.setVisible(false);
        jpkhachhang.setVisible(false);
        jplichsu.setVisible(false);
        jpkhuyenmai.setVisible(false);
        jpdoimk.setVisible(false);
        loadDataNV();
    }//GEN-LAST:event_lblnhanvienMouseClicked

    private void lblhoadonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblhoadonMouseClicked
        jphoadon.setVisible(true);
        jpthongke.setVisible(false);
        jpsanpham.setVisible(false);
        jpnhanvien.setVisible(false);
        jpkhachhang.setVisible(false);
        jplichsu.setVisible(false);
        jpkhuyenmai.setVisible(false);
        jpdoimk.setVisible(false);
        jpthanhtoan.setVisible(false);
        LoadDataHDSP();
        LoadDataHD();
        setPageHD();
        setPageSP();
    }//GEN-LAST:event_lblhoadonMouseClicked

    private void lblkhachhangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblkhachhangMouseClicked
        jpkhachhang.setVisible(true);
        jpthongke.setVisible(false);
        jpsanpham.setVisible(false);
        jpnhanvien.setVisible(false);
        jphoadon.setVisible(false);
        jplichsu.setVisible(false);
        jpkhuyenmai.setVisible(false);
        jpdoimk.setVisible(false);
    }//GEN-LAST:event_lblkhachhangMouseClicked

    private void lbllichsuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbllichsuMouseClicked
        jplichsu.setVisible(true);
        jpthongke.setVisible(false);
        jpsanpham.setVisible(false);
        jpnhanvien.setVisible(false);
        jphoadon.setVisible(false);
        jpkhachhang.setVisible(false);
        jpkhuyenmai.setVisible(false);
        jpdoimk.setVisible(false);
    }//GEN-LAST:event_lbllichsuMouseClicked

    private void lblkhuyenmaiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblkhuyenmaiMouseClicked
        jpkhuyenmai.setVisible(true);
        jpthongke.setVisible(false);
        jpsanpham.setVisible(false);
        jpnhanvien.setVisible(false);
        jphoadon.setVisible(false);
        jpkhachhang.setVisible(false);
        jplichsu.setVisible(false);
        jpdoimk.setVisible(false);
    }//GEN-LAST:event_lblkhuyenmaiMouseClicked

    private void lbltaikhoanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbltaikhoanMouseClicked
        jpdoimk.setVisible(true);
        jpthongke.setVisible(false);
        jpsanpham.setVisible(false);
        jpnhanvien.setVisible(false);
        jphoadon.setVisible(false);
        jpkhachhang.setVisible(false);
        jplichsu.setVisible(false);
        jpkhuyenmai.setVisible(false);

        lblmkm.setVisible(false);
        lblmkxn.setVisible(false);
        txtmkm.setVisible(false);
        txtmkxn.setVisible(false);

        fillTableTK();
    }//GEN-LAST:event_lbltaikhoanMouseClicked

    private void btnthemchatlieuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnthemchatlieuActionPerformed
        if (checkchatlieu()) {
            ChatLieu cl = new ChatLieu();
            cl.setTen(txttenchatlieu.getText());
            boolean clResult = servicecl.Them(cl);
            if (clResult) {
                loadttsp();
                loadcbocl();
            }
        }

    }//GEN-LAST:event_btnthemchatlieuActionPerformed

    private void tbldschatlieuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbldschatlieuMouseClicked
        i = tbldschatlieu.getSelectedRow();
        displaychatlieu(i);
    }//GEN-LAST:event_tbldschatlieuMouseClicked

    private void btnsuachatlieuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsuachatlieuActionPerformed
        if (checkchatlieu()) {
            ChatLieu th1 = new ChatLieu();
            th1.setTen(txttenchatlieu.getText());
            th1.setId(txtidchatlieu.getText());
            boolean thResult = servicecl.Sua(th1);
            if (thResult) {
                loadttsp();
                loadcbocl();
            }
        }
    }//GEN-LAST:event_btnsuachatlieuActionPerformed

    private void btnxoachatlieuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnxoachatlieuActionPerformed
        ChatLieu th1 = new ChatLieu();
        th1.setTen(txttenchatlieu.getText());
        th1.setId(txtidchatlieu.getText());
        boolean thResult = servicecl.An(th1);
        if (thResult) {
            loadttsp();
            loadcbocl();
        }
    }//GEN-LAST:event_btnxoachatlieuActionPerformed

    private void txtnvthemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtnvthemActionPerformed
        themnv();
    }//GEN-LAST:event_txtnvthemActionPerformed

    private void txtnvsuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtnvsuaActionPerformed
        suanv();
    }//GEN-LAST:event_txtnvsuaActionPerformed

    private void txtnvxoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtnvxoaActionPerformed
        xoanv();
    }//GEN-LAST:event_txtnvxoaActionPerformed

    private void tblnvMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblnvMouseClicked
        i = tblnv.getSelectedRow();
        Display(i);
    }//GEN-LAST:event_tblnvMouseClicked

    private void btnhdtaohoadonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnhdtaohoadonActionPerformed
        TaoHD();
        LoadDataHD();
    }//GEN-LAST:event_btnhdtaohoadonActionPerformed

    private void txttenchatlieuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txttenchatlieuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txttenchatlieuActionPerformed

    private void txttenthuonghieuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txttenthuonghieuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txttenthuonghieuActionPerformed

    private void btnsuathuonghieuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsuathuonghieuActionPerformed
        if (checkthuonghieu()) {
            ThuongHieu th1 = new ThuongHieu();
            th1.setTen(txttenthuonghieu.getText());
            th1.setId(txtidthuonghieu.getText());
            boolean thResult = serviceth.sua(th1);
            if (thResult) {
                loadttsp();
                loadcboth();
            }
        }
    }//GEN-LAST:event_btnsuathuonghieuActionPerformed

    private void btnthemthuonghieuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnthemthuonghieuActionPerformed
        if (checkthuonghieu()) {
            ThuongHieu cl = new ThuongHieu();
            cl.setTen(txttenthuonghieu.getText());
            boolean clResult = serviceth.them(cl);
            if (clResult) {
                loadttsp();
                loadcboth();
            }
        }
    }//GEN-LAST:event_btnthemthuonghieuActionPerformed

    private void btnxoathuonghieuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnxoathuonghieuActionPerformed
        ThuongHieu th1 = new ThuongHieu();
        th1.setTen(txttenthuonghieu.getText());
        th1.setId(txtidthuonghieu.getText());
        boolean thResult = serviceth.an(th1);
        if (thResult) {
            loadttsp();
            loadcboth();
        }
    }//GEN-LAST:event_btnxoathuonghieuActionPerformed

    private void tbldsthuonghieuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbldsthuonghieuMouseClicked
        i = tbldsthuonghieu.getSelectedRow();
        displaythuonghieu(i);
    }//GEN-LAST:event_tbldsthuonghieuMouseClicked

    private void txttenphanloaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txttenphanloaiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txttenphanloaiActionPerformed

    private void btnsuaphanloaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsuaphanloaiActionPerformed
        if (checkphanloai()) {
            PhanLoai th1 = new PhanLoai();
            th1.setTen(txttenphanloai.getText());
            th1.setId(txtidphanloai.getText());
            boolean thResult = servicepl.Sua(th1);
            if (thResult) {
                loadttsp();
                loadcbopl();
            }
        }
    }//GEN-LAST:event_btnsuaphanloaiActionPerformed

    private void btnthemphanloaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnthemphanloaiActionPerformed
        if (checkphanloai()) {
            PhanLoai cl = new PhanLoai();
            cl.setTen(txttenphanloai.getText());
            boolean clResult = servicepl.Them(cl);
            if (clResult) {
                loadttsp();
                loadcbopl();
            }
        }
    }//GEN-LAST:event_btnthemphanloaiActionPerformed

    private void btnxoaphanloaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnxoaphanloaiActionPerformed
        PhanLoai th1 = new PhanLoai();
        th1.setTen(txttenphanloai.getText());
        th1.setId(txtidphanloai.getText());
        boolean thResult = servicepl.An(th1);
        if (thResult) {
            loadttsp();
            loadcbopl();
        }

    }//GEN-LAST:event_btnxoaphanloaiActionPerformed

    private void tbldsphanloaiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbldsphanloaiMouseClicked
        i = tbldsphanloai.getSelectedRow();
        displayphanloai(i);
    }//GEN-LAST:event_tbldsphanloaiMouseClicked

    private void txttenxuatxuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txttenxuatxuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txttenxuatxuActionPerformed

    private void btnsuaxuatxuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsuaxuatxuActionPerformed
        if (checkxuatxu()) {
            XuatXu th1 = new XuatXu();
            th1.setTen(txttenxuatxu.getText());
            th1.setId(txtidxuatxu.getText());
            boolean thResult = servicexx.sua(th1);
            if (thResult) {
                loadttsp();
                loadcboxx();
            }
        }
    }//GEN-LAST:event_btnsuaxuatxuActionPerformed

    private void btnthemxuatxuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnthemxuatxuActionPerformed
        if (checkxuatxu()) {
            XuatXu cl = new XuatXu();
            cl.setTen(txttenxuatxu.getText());
            boolean clResult = servicexx.them(cl);
            if (clResult) {
                loadttsp();
                loadcboxx();
            }
        }
    }//GEN-LAST:event_btnthemxuatxuActionPerformed

    private void btnxoaxuatxuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnxoaxuatxuActionPerformed
        XuatXu th1 = new XuatXu();
        th1.setTen(txttenxuatxu.getText());
        th1.setId(txtidxuatxu.getText());
        boolean thResult = servicexx.an(th1);
        if (thResult) {
            loadttsp();
            loadcboxx();
        }
    }//GEN-LAST:event_btnxoaxuatxuActionPerformed

    private void tbldsxuatxuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbldsxuatxuMouseClicked
        i = tbldsxuatxu.getSelectedRow();
        displayxuatxu(i);
    }//GEN-LAST:event_tbldsxuatxuMouseClicked

    private void txttenmausacActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txttenmausacActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txttenmausacActionPerformed

    private void btnsuamausacActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsuamausacActionPerformed
        if (checkmausac()) {
            MauSac th1 = new MauSac();
            th1.setTen(txttenmausac.getText());
            th1.setId(txtidmausac.getText());
            boolean thResult = servicems.Sua(th1);
            if (thResult) {
                loadttsp();
                loadcboms();
            }
        }
    }//GEN-LAST:event_btnsuamausacActionPerformed

    private void btnthemmausacActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnthemmausacActionPerformed
        if (checkmausac()) {
            MauSac cl = new MauSac();
            cl.setTen(txttenmausac.getText());
            boolean clResult = servicems.Them(cl);
            if (clResult) {
                loadttsp();
                loadcboms();
            }
        }
    }//GEN-LAST:event_btnthemmausacActionPerformed

    private void btnxoamausacActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnxoamausacActionPerformed
        MauSac th1 = new MauSac();
        th1.setTen(txttenmausac.getText());
        th1.setId(txtidmausac.getText());
        boolean thResult = servicems.Sua(th1);
        if (thResult) {
            loadttsp();
            loadcboms();
        }
    }//GEN-LAST:event_btnxoamausacActionPerformed

    private void tbldsmausacMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbldsmausacMouseClicked
        i = tbldsmausac.getSelectedRow();
        displaymausac(i);
    }//GEN-LAST:event_tbldsmausacMouseClicked

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

    private void btnsuaspActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsuaspActionPerformed
        // TODO add your handling code here:
        if (spservice.UpdateSP(readFormSP()) > 0) {
            JOptionPane.showMessageDialog(this, "Sửa thành công");
            fillTBLSanPham(spservice.gettAll());
        } else {
            JOptionPane.showMessageDialog(this, "Sửa thất bại");
        }
    }//GEN-LAST:event_btnsuaspActionPerformed

    private void btnchitietspActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnchitietspActionPerformed
        // TODO add your handling code here:    
        i = tblsanpham.getSelectedRow();
        viewSPCT spct = new viewSPCT(spservice.findByID(tblsanpham.getValueAt(i, 0).toString()));
        spct.setVisible(true);
    }//GEN-LAST:event_btnchitietspActionPerformed

    private void tblhddsspMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblhddsspMouseClicked
        i = tblhddssp.getSelectedRow();
        themgh();
        loadGioHang();
        LoadDataHDSP();
    }//GEN-LAST:event_tblhddsspMouseClicked

    private void tblhddshdMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblhddshdMouseClicked
        loadGioHang();

    }//GEN-LAST:event_tblhddshdMouseClicked

    private void btnhdxoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnhdxoaActionPerformed
        xoagh();
        loadGioHang();
    }//GEN-LAST:event_btnhdxoaActionPerformed

    private void tblhddsghMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblhddsghMouseClicked

    }//GEN-LAST:event_tblhddsghMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        suahdct();
        LoadDataHDSP();
        loadGioHang();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnNextSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextSPActionPerformed
        page++;
        this.setPageSP();
    }//GEN-LAST:event_btnNextSPActionPerformed

    private void btnFirstHDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFirstHDActionPerformed
        page = 1;
        this.setPageHD();
    }//GEN-LAST:event_btnFirstHDActionPerformed

    private void btnPrevHDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrevHDActionPerformed
        page--;
        this.setPageHD();
    }//GEN-LAST:event_btnPrevHDActionPerformed

    private void btnNextHDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextHDActionPerformed
        page++;
        this.setPageHD();
    }//GEN-LAST:event_btnNextHDActionPerformed

    private void btnLastHDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLastHDActionPerformed
        page = (servicehd.getAllHoaDon().size() + 5 - 1)/5;
        this.setPageHD();
//         int hoaDonListSize = servicehd.getAllHoaDon(1000).size();
//        try {
//        int hoaDonListSize = servicehd.getAllHoaDon(1000).size();
//        if (hoaDonListSize > 0) {
//            int rowsPerPage = 5;
//            int totalPages = (hoaDonListSize + rowsPerPage - 1) / rowsPerPage;
//            
//            // Đặt page để chỉ đến trang cuối cùng
//            page = totalPages;
//            
//            this.setPageHD();
//        } else {
//            // Xử lý trường hợp danh sách trống
//            System.out.println("Danh sách hoá đơn trống.");
//        }
//    } catch (Exception e) {
//        e.printStackTrace();
//        // Xử lý ngoại lệ nếu có
//    }
    }//GEN-LAST:event_btnLastHDActionPerformed

    private void btnFirstSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFirstSPActionPerformed
        page = 1;
        this.setPageSP();
    }//GEN-LAST:event_btnFirstSPActionPerformed

    private void btnPrevSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrevSPActionPerformed
        page--;
        this.setPageSP();
    }//GEN-LAST:event_btnPrevSPActionPerformed

    private void btnLastSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLastSPActionPerformed
        try {
        int SPListSize = servicehd.getAllSanPham(1000).size();
        if (SPListSize > 0) {
            int rowsPerPage = 5;
            int totalPages = (SPListSize + rowsPerPage - 1) / rowsPerPage;
            
            // Đặt page để chỉ đến trang cuối cùng
            page = totalPages;
            
            this.setPageSP();
        } else {
            // Xử lý trường hợp danh sách trống
            System.out.println("Danh sách sản phẩm trống.");
        }
    } catch (Exception e) {
        e.printStackTrace();
        // Xử lý ngoại lệ nếu có
    }
    }//GEN-LAST:event_btnLastSPActionPerformed

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ViewChuCuaHang().setVisible(true);
            }
        });
    }

    private void fillTableTK() {
        dtm = (DefaultTableModel) tbltaikhoan.getModel();
        dtm.setRowCount(0);
        for (TaiKhoan x : servicetk.getAll()) {
            dtm.addRow(new Object[]{
                x.getId(), x.getTenDangNhap(), x.getMatKhau(), x.getVaiTro()
            });
        }
    }

    void FillTH() {
        String sql = "Select * from ThuongHieu ";
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            lthuonghieu.clear();
            while (rs.next()) {
                String id = rs.getString(1);
                String ten = rs.getString(2);
                ThuongHieu d = new ThuongHieu(id, ten);
                lthuonghieu.add(d);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        dtm = (DefaultTableModel) tbldsthuonghieu.getModel();
        dtm.setRowCount(0);
        for (ThuongHieu x : lthuonghieu) {
            dtm.addRow(new Object[]{
                x.getId(), x.getTen()
            });
        }
    }

    void FillMS() {
        String sql1 = "Select * from MauSac";
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql1);
            lmausac.clear();
            while (rs.next()) {
                String id = rs.getString(1);
                String ten = rs.getString(2);
                MauSac d = new MauSac(id, ten);
                lmausac.add(d);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        dtm = (DefaultTableModel) tbldsmausac.getModel();
        dtm.setRowCount(0);
        for (MauSac x : lmausac) {
            dtm.addRow(new Object[]{
                x.getId(), x.getTen()
            });
        }
    }

    void FillPL() {
        String sql2 = "Select * from PhanLoai";
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql2);
            lphanloai.clear();
            while (rs.next()) {
                String id = rs.getString(1);
                String ten = rs.getString(2);
                PhanLoai d = new PhanLoai(id, ten);
                lphanloai.add(d);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        dtm = (DefaultTableModel) tbldsphanloai.getModel();
        dtm.setRowCount(0);
        for (PhanLoai x : lphanloai) {
            dtm.addRow(new Object[]{
                x.getId(), x.getTen()
            });
        }
    }

    void FillCL() {
        String sql3 = "Select * from ChatLieu";
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql3);
            lchatlieu.clear();
            while (rs.next()) {
                String id = rs.getString(1);
                String ten = rs.getString(2);
                ChatLieu d = new ChatLieu(id, ten);
                lchatlieu.add(d);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        dtm = (DefaultTableModel) tbldschatlieu.getModel();
        dtm.setRowCount(0);
        for (ChatLieu x : lchatlieu) {
            dtm.addRow(new Object[]{
                x.getId(), x.getTen()
            });
        }
    }

    void FillXX() {
        String sql4 = "Select * from XuatXu";
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql4);
            lxuatxu.clear();
            while (rs.next()) {
                String id = rs.getString(1);
                String ten = rs.getString(2);
                XuatXu d = new XuatXu(id, ten);
                lxuatxu.add(d);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        dtm = (DefaultTableModel) tbldsxuatxu.getModel();
        dtm.setRowCount(0);
        for (XuatXu x : lxuatxu) {
            dtm.addRow(new Object[]{
                x.getId(), x.getTen()
            });
        }
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnFirstHD;
    private javax.swing.JButton btnFirstSP;
    private javax.swing.JButton btnLastHD;
    private javax.swing.JButton btnLastSP;
    private javax.swing.JButton btnNextHD;
    private javax.swing.JButton btnNextSP;
    private javax.swing.JButton btnPrevHD;
    private javax.swing.JButton btnPrevSP;
    private javax.swing.JButton btnchitietsp;
    private javax.swing.JButton btndoimk;
    private javax.swing.JButton btndsspan;
    private javax.swing.JButton btnhdhuy;
    private javax.swing.JButton btnhdinhoadon;
    private javax.swing.JButton btnhdtaohoadon;
    private javax.swing.JButton btnhdthanhtoan;
    private javax.swing.JButton btnhdxoa;
    private javax.swing.JButton btnkmluu;
    private javax.swing.JButton btnkmmoi;
    private javax.swing.JButton btnkmsua;
    private javax.swing.JButton btnkmtim;
    private javax.swing.JButton btnkmxoa;
    private javax.swing.JButton btnsua;
    private javax.swing.JButton btnsuachatlieu;
    private javax.swing.JButton btnsuamausac;
    private javax.swing.JButton btnsuaphanloai;
    private javax.swing.JButton btnsuasp;
    private javax.swing.JButton btnsuathuonghieu;
    private javax.swing.JButton btnsuaxuatxu;
    private javax.swing.JButton btnthem;
    private javax.swing.JButton btnthemchatlieu;
    private javax.swing.JButton btnthemmausac;
    private javax.swing.JButton btnthemphanloai;
    private javax.swing.JButton btnthemsp;
    private javax.swing.JButton btnthemthuonghieu;
    private javax.swing.JButton btnthemxuatxu;
    private javax.swing.JButton btnxacnhan;
    private javax.swing.JButton btnxoa;
    private javax.swing.JButton btnxoachatlieu;
    private javax.swing.JButton btnxoamausac;
    private javax.swing.JButton btnxoaphanloai;
    private javax.swing.JButton btnxoathuonghieu;
    private javax.swing.JButton btnxoaxuatxu;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cbohdthanhtoan;
    private javax.swing.JComboBox<String> cbokmapdung;
    private javax.swing.JComboBox<String> cbokmhinhthuc;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton19;
    private javax.swing.JComboBox<String> jComboBox6;
    private javax.swing.JComboBox<String> jComboBox7;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel100;
    private javax.swing.JLabel jLabel103;
    private javax.swing.JLabel jLabel104;
    private javax.swing.JLabel jLabel105;
    private javax.swing.JLabel jLabel107;
    private javax.swing.JLabel jLabel108;
    private javax.swing.JLabel jLabel109;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel110;
    private javax.swing.JLabel jLabel111;
    private javax.swing.JLabel jLabel112;
    private javax.swing.JLabel jLabel113;
    private javax.swing.JLabel jLabel114;
    private javax.swing.JLabel jLabel115;
    private javax.swing.JLabel jLabel116;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel120;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel75;
    private javax.swing.JLabel jLabel76;
    private javax.swing.JLabel jLabel77;
    private javax.swing.JLabel jLabel78;
    private javax.swing.JLabel jLabel79;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel80;
    private javax.swing.JLabel jLabel81;
    private javax.swing.JLabel jLabel82;
    private javax.swing.JLabel jLabel83;
    private javax.swing.JLabel jLabel84;
    private javax.swing.JLabel jLabel85;
    private javax.swing.JLabel jLabel86;
    private javax.swing.JLabel jLabel87;
    private javax.swing.JLabel jLabel88;
    private javax.swing.JLabel jLabel89;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel90;
    private javax.swing.JLabel jLabel91;
    private javax.swing.JLabel jLabel92;
    private javax.swing.JLabel jLabel93;
    private javax.swing.JLabel jLabel94;
    private javax.swing.JLabel jLabel95;
    private javax.swing.JLabel jLabel96;
    private javax.swing.JLabel jLabel97;
    private javax.swing.JLabel jLabel98;
    private javax.swing.JLabel jLabel99;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel27;
    private javax.swing.JPanel jPanel28;
    private javax.swing.JPanel jPanel29;
    private javax.swing.JPanel jPanel30;
    private javax.swing.JPanel jPanel31;
    private javax.swing.JPanel jPanel32;
    private javax.swing.JPanel jPanel33;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JScrollPane jScrollPane14;
    private javax.swing.JScrollPane jScrollPane15;
    private javax.swing.JScrollPane jScrollPane17;
    private javax.swing.JScrollPane jScrollPane18;
    private javax.swing.JScrollPane jScrollPane19;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane21;
    private javax.swing.JScrollPane jScrollPane22;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTabbedPane jTabbedPane3;
    private javax.swing.JTable jTable6;
    private javax.swing.JTable jTable7;
    private javax.swing.JTable jTable8;
    private javax.swing.JTable jTable9;
    private javax.swing.JTextArea jTextArea3;
    private javax.swing.JPanel jpdoimk;
    private javax.swing.JPanel jphoadon;
    private javax.swing.JPanel jpkhachhang;
    private javax.swing.JPanel jpkhuyenmai;
    private javax.swing.JPanel jplichsu;
    private javax.swing.JPanel jpnhanvien;
    private javax.swing.JPanel jpsanpham;
    private javax.swing.JPanel jpthanhtoan;
    private javax.swing.JPanel jpthongke;
    private javax.swing.JLabel lbldangxuat;
    private javax.swing.JLabel lblhoadon;
    private javax.swing.JLabel lblid;
    private javax.swing.JLabel lblidsp;
    private javax.swing.JLabel lblkhachhang;
    private javax.swing.JLabel lblkhuyenmai;
    private javax.swing.JLabel lbllichsu;
    private javax.swing.JLabel lblmkm;
    private javax.swing.JLabel lblmkxn;
    private javax.swing.JLabel lblnhanvien;
    private javax.swing.JLabel lblsanpham;
    private javax.swing.JLabel lbltaikhoan;
    private javax.swing.JLabel lblthongke;
    private javax.swing.JPanel mausac;
    private javax.swing.JPanel mausac1;
    private javax.swing.JPanel mausac2;
    private javax.swing.JPanel mausac3;
    private javax.swing.JPanel mausac4;
    private javax.swing.JPanel panelChatLieu;
    private javax.swing.JPanel panelMauSac;
    private javax.swing.JPanel panelPhanLoai;
    private javax.swing.JPanel panelThuongHieu;
    private javax.swing.JPanel panelXuatXu;
    private javax.swing.JRadioButton rdocch;
    private javax.swing.JRadioButton rdokmdaketthuc;
    private javax.swing.JRadioButton rdokmdangdienra;
    private javax.swing.JRadioButton rdonv;
    private javax.swing.JTable tbldschatlieu;
    private javax.swing.JTable tbldsmausac;
    private javax.swing.JTable tbldsphanloai;
    private javax.swing.JTable tbldsthuonghieu;
    private javax.swing.JTable tbldsxuatxu;
    private javax.swing.JTable tblhddsgh;
    private javax.swing.JTable tblhddshd;
    private javax.swing.JTable tblhddssp;
    private javax.swing.JTable tblkmdskm;
    private javax.swing.JTable tblkmdssp;
    private javax.swing.JTable tblnv;
    private javax.swing.JTable tblsanpham;
    private javax.swing.JTable tbltaikhoan;
    private javax.swing.JButton tbmtimsp;
    private javax.swing.JButton tbnxoasp;
    private javax.swing.ButtonGroup thuoctinhsp;
    private javax.swing.JTextField txtPageHD;
    private javax.swing.JTextField txtPageSP;
    private javax.swing.JTextField txthdkhachdua;
    private javax.swing.JLabel txthdkhachtra;
    private javax.swing.JLabel txthdma;
    private javax.swing.JTextField txthdsdt;
    private javax.swing.JTextField txthdtenkh;
    private javax.swing.JLabel txthdtienthua;
    private javax.swing.JLabel txthdtongtien;
    private javax.swing.JLabel txtidchatlieu;
    private javax.swing.JLabel txtidmausac;
    private javax.swing.JLabel txtidphanloai;
    private javax.swing.JLabel txtidthuonghieu;
    private javax.swing.JLabel txtidxuatxu;
    private javax.swing.JTextField txtkmden;
    private javax.swing.JTextField txtkmmucgg;
    private javax.swing.JTextField txtkmtenct;
    private javax.swing.JTextField txtkmtim;
    private javax.swing.JTextField txtkmtu;
    private javax.swing.JTextField txtmasp;
    private javax.swing.JTextField txtmk;
    private javax.swing.JTextField txtmkm;
    private javax.swing.JTextField txtmkxn;
    private javax.swing.JTextArea txtnvdiachi;
    private javax.swing.JTextField txtnvemail;
    private javax.swing.JTextField txtnvgioitinh;
    private javax.swing.JTextField txtnvmanv;
    private javax.swing.JTextField txtnvsodt;
    private javax.swing.JButton txtnvsua;
    private javax.swing.JTextField txtnvtennv;
    private javax.swing.JButton txtnvthem;
    private javax.swing.JButton txtnvxoa;
    private javax.swing.JTextField txttenchatlieu;
    private javax.swing.JTextField txttendangnhap;
    private javax.swing.JTextField txttenmausac;
    private javax.swing.JTextField txttenphanloai;
    private javax.swing.JTextField txttensp;
    private javax.swing.JTextField txttenthuonghieu;
    private javax.swing.JTextField txttenxuatxu;
    private javax.swing.JTextField txttimsp;
    // End of variables declaration//GEN-END:variables
    void loadttsp() {

        FillTH();

        FillMS();

        FillPL();

        FillCL();

        FillXX();

    }

}
