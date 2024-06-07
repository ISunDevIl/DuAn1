package Service;

import Connection.DBContext;
import Model.HoaDon;
import Model.SanPham;
import Model.SanPhamChiTiet;
import Model.SanPham_SPCT;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class HoaDonService {

    Connection con = DBContext.getConnection();
    PreparedStatement ps = null;
    ResultSet rs = null;

    public List<SanPhamChiTiet> getAllSanPham() {
        List<SanPhamChiTiet> listSP = new ArrayList<>();
        try {
            String sql = "  Select IDSP, KhoiLuong,SoLuong,Gia,KichThuoc,IDTH,IDMS,IDPL,IDCL,IDXX From SanPhamChiTiet";
//            String sql = "  Select IDSP, KhoiLuong,SoLuong,Gia,KichThuoc,IDTH,IDMS,IDPL,IDCL,IDXX From SanPham\n"
//                    + "  join SanPhamChiTiet on SanPham.ID = SanPhamChiTiet.IDSP";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                SanPhamChiTiet sp = new SanPhamChiTiet();
                sp.setIdsp(rs.getString("IDSP"));
                sp.setIdcl(rs.getString("IDCL"));
                sp.setIdpl(rs.getString("IDPL"));
                sp.setIdms(rs.getString("IDMS"));
                sp.setIdth(rs.getString("IDTH"));
                sp.setIdxx(rs.getString("IDXX"));
                sp.setKhoiLuong(rs.getFloat("KhoiLuong"));
                sp.setSoLuong(rs.getInt("SoLuong"));
                sp.setGia(rs.getFloat("Gia"));
                sp.setKichThuoc(rs.getString("KichThuoc"));
                listSP.add(sp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listSP;
    }

    public List<HoaDon> getAllHoaDon() {
        List<HoaDon> listhd = new ArrayList<>();
        try {
            String sql = "Select * from HoaDon where TrangThai = 0";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                HoaDon hd = new HoaDon();
                hd.setMaHD(rs.getString("MaHD"));
                hd.setMakh(rs.getString("MaKH"));
                hd.setManv(rs.getString("MaNV"));
                hd.setTrangThai(rs.getByte("TrangThai"));
                hd.setNgaytao(rs.getString("NgayTao"));

                listhd.add(hd);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listhd;
    }

    public boolean them(HoaDon th) {
        String sql = "Insert into HoaDon (MaHD,MaKH,NgayTao,TrangThai) Values (?,?,?,0)";
        try {

            PreparedStatement stm = con.prepareStatement(sql);
            stm.setString(1, th.getMaHD());
            stm.setString(2, th.getMakh());
            stm.setString(3, th.getNgaytao());
            stm.executeUpdate();
            return true;

        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }
    
    public SanPham findIDSP(String IDSP) {
        SanPham gh = new SanPham();
        try {
            String sql = "Select MaSP , TenSP From SanPham Where ID = ?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, IDSP);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                gh.setMaSP(rs.getString("MaSP"));
                gh.setTenSP(rs.getString("TenSP"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return gh;
    }
    
    public HoaDon findIDHD(String mahd) {
        HoaDon gh = new HoaDon();
        try {
            String sql = "Select IDHD From SanPham Where MaHD = ?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, mahd);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                gh.setId(rs.getString("IDHD"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return gh;
    }
}
