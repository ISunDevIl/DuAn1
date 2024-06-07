package Service;

import Connection.DBContext;
import Model.HoaDonChiTiet;
import Model.GioHang;
import Model.SanPhamChiTiet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class HDCTService {

    Connection con = DBContext.getConnection();
    PreparedStatement ps = null;
    ResultSet rs = null;

    public List<HoaDonChiTiet> getAllHoaDon() {
        List<HoaDonChiTiet> listhd = new ArrayList<>();
        try {
            String sql = "Select * from HoaDon where TrangThai = 0";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                HoaDonChiTiet hd = new HoaDonChiTiet();
                hd.setIdhd(rs.getString("IDHD"));
                hd.setIdspct(rs.getString("IDSPCT"));
                hd.setSoLuong(rs.getInt("SoLuong"));
                hd.setDongia(rs.getFloat("DonGia"));
                hd.setThanhtien(rs.getFloat("ThanhTien"));
                hd.setMagg(rs.getString("MaGG"));
                listhd.add(hd);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listhd;
    }

    public GioHang findIDSP(String IDSPCT) {
        GioHang gh = new GioHang();
        try {
            String sql = "Select MaSP , TenSP From SanPham\n"
                    + "join SanPhamChiTiet on SanPham.ID = SanPhamChiTiet.IDSP\n"
                    + "where SanPhamChiTiet.ID = ?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, IDSPCT);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                gh.setMasp(rs.getString("MaSP"));
                gh.setTensp(rs.getString("TenSP"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return gh;
    }

    public GioHang findMaGG(String magg) {
        GioHang gh = new GioHang();
        try {
            String sql = "Select GiaTri From GiamGia Where MaGG = ?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, magg);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                gh.setGiatri(rs.getFloat("GiaTri"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return gh;
    }

    public boolean them(HoaDonChiTiet th) {
        String sql = "Insert into HoaDonChiTiet (IDHD,IDSPCT,SoLuong,DonGia,ThanhTien,MaGG) Values (?,?,?,?,?,?)";
        try {

            PreparedStatement stm = con.prepareStatement(sql);
            stm.setString(1, th.getIdhd());
            stm.setString(2, th.getIdspct());
            stm.setInt(3, th.getSoLuong());
            stm.setFloat(4, th.getDongia());
            stm.setFloat(5, th.getThanhtien());
            stm.setString(6, th.getMagg());
            stm.executeUpdate();
            return true;

        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean xoa(HoaDonChiTiet th) {
        String sql = "DELETE FROM HoaDonChiTiet Where ID = ?";
        try {

            PreparedStatement stm = con.prepareStatement(sql);
            stm.setString(1, th.getId());
            stm.executeUpdate();
            return true;

        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean suasp(SanPhamChiTiet th) {
        String sql = "Update SanPhamChiTiet set SoLuong = ? where ID = ?";
        try {

            PreparedStatement stm = con.prepareStatement(sql);
            stm.setInt(1, th.getSoLuong());
            stm.setString(2, th.getId());

            stm.executeUpdate();
            return true;

        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean suahd(HoaDonChiTiet th) {
        String sql = "Update HoaDonChiTiet set SoLuong = ? where ID = ?";
        try {

            PreparedStatement stm = con.prepareStatement(sql);
            stm.setInt(1, th.getSoLuong());
            stm.setString(2, th.getId());

            stm.executeUpdate();
            return true;

        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public SanPhamChiTiet findSoLuong(String IDHDCT) {
        SanPhamChiTiet gh = new SanPhamChiTiet();
        try {
            String sql = "    select SanPhamChiTiet.SoLuong From SanPhamChiTiet \n"
                    + "  join HoaDonChiTiet on SanPhamChiTiet.ID = HoaDonChiTiet.IDSPCT \n"
                    + "  where HoaDonChiTiet.ID = ?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, IDHDCT);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                gh.setSoLuong(rs.getInt("SoLuong"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return gh;
    }
    
    public HoaDonChiTiet findIDSPCT(String IDHDCT) {
        HoaDonChiTiet gh = new HoaDonChiTiet();
        try {
            String sql = "Select IDSPCT From HoaDonChiTiet where ID = ?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, IDHDCT);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                gh.setIdspct(rs.getString("IDSPCT"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return gh;
    }
    
    
    public HoaDonChiTiet selectGioHang(int id) {
        String sql = """
                     Select * FROM HoaDonChiTiet Where ID = ?
                     """;
        HoaDonChiTiet hdct = null;
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {                
                hdct = new HoaDonChiTiet();
                hdct.setId(rs.getString(sql));
                hdct.setIdhd(rs.getString("IDHD"));
                hdct.setIdspct(rs.getString("IDSPCT"));
                hdct.setSoLuong(rs.getInt("SoLuong"));
                hdct.setDongia(rs.getFloat("DonGia"));
                hdct.setThanhtien(rs.getFloat("ThanhTien"));
                hdct.setMagg(rs.getString("MaGG"));
            }
            return hdct;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
}
