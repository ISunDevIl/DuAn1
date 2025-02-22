package Service;

import Connection.DBContext;
import java.sql.Connection;
import Model.SanPhamChiTiet;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SPCTService {

    private Connection con = DBContext.getConnection();

    public List<SanPhamChiTiet> gettAll() {
        List<SanPhamChiTiet> lspct = new ArrayList<>();
        try {
            String sql = "Select * from SanPhamChiTiet";
            PreparedStatement pstm = con.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                SanPhamChiTiet spct = new SanPhamChiTiet();
                spct.setId(rs.getString("ID"));
                spct.setIdsp(rs.getString("IDSP"));
                spct.setIdcl(rs.getString("IDCL"));
                spct.setIdpl(rs.getString("IDPL"));
                spct.setIdms(rs.getString("IDMS"));
                spct.setIdth(rs.getString("IDTH"));
                spct.setIdxx(rs.getString("IDXX"));
                spct.setMaGG(rs.getString("MaGG"));
                spct.setKichThuoc(rs.getString("KichThuoc"));
                spct.setKhoiLuong(rs.getFloat("KhoiLuong"));
                spct.setSoLuong(rs.getInt("SoLuong"));
                spct.setGia(rs.getFloat("Gia"));
                spct.setMoTa(rs.getString("MoTa"));
                spct.setAnh(rs.getString("Anh"));
                spct.setTrangThai(rs.getByte("TrangThai"));
                lspct.add(spct);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return lspct;
    }

    public List<SanPhamChiTiet> SPCTtoKhuyenMai() {
        List<SanPhamChiTiet> listSpct = new ArrayList<>();
        try {
            String sql = "Select * from SanPhamChiTiet";
            PreparedStatement pstm = con.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                SanPhamChiTiet spct = new SanPhamChiTiet();
                spct.setId(rs.getString("ID"));
                spct.setIdsp(rs.getString("IDSP"));
                spct.setIdcl(rs.getString("IDCL"));
                spct.setIdpl(rs.getString("IDPL"));
                spct.setIdms(rs.getString("IDMS"));
                spct.setIdth(rs.getString("IDTH"));
                spct.setIdxx(rs.getString("IDXX"));
                spct.setKichThuoc(rs.getString("KichThuoc"));
                spct.setKhoiLuong(rs.getFloat("KhoiLuong"));
                spct.setSoLuong(rs.getInt("SoLuong"));
                spct.setGia(rs.getFloat("Gia"));

                spct.setTrangThai(rs.getByte("TrangThai"));
                listSpct.add(spct);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listSpct;
    }

    public Integer ThemSPCT(SanPhamChiTiet spct) {
        Integer row = null;
        String sql = "Insert into SanPhamChiTiet(IDSP,IDCL,IDPL,IDMS,IDTH,IDXX,"
                + "KichThuoc,KhoiLuong,SoLuong,Gia,MoTa,Anh,TrangThai) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,0)";
        try {
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setString(1, spct.getIdsp());
            stm.setString(2, spct.getIdcl());
            stm.setString(3, spct.getIdpl());
            stm.setString(4, spct.getIdms());
            stm.setString(5, spct.getIdth());
            stm.setString(6, spct.getIdxx());
            stm.setString(7, spct.getKichThuoc());
            stm.setFloat(8, spct.getKhoiLuong());
            stm.setInt(9, spct.getSoLuong());
            stm.setFloat(10, spct.getGia());
            stm.setString(11, spct.getMoTa());
            stm.setString(12, spct.getAnh());
            row = stm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return row;
    }

    public Integer SuaSPCT(SanPhamChiTiet spct) {
        Integer row = null;
        String sql = "UPDATE SanPhamChiTiet Set IDSP=?, IDCL=?,IDPL=?,IDMS=?,IDTH=?,IDXX=?,KichThuoc =?,KhoiLuong =?,"
                + "SoLuong = ? , Gia = ?, MoTa = ?, Anh = ? Where ID = ?";
        try {
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setString(1, spct.getIdsp());
            stm.setString(2, spct.getIdcl());
            stm.setString(3, spct.getIdpl());
            stm.setString(4, spct.getIdms());
            stm.setString(5, spct.getIdth());
            stm.setString(6, spct.getIdxx());
            stm.setString(7, spct.getKichThuoc());
            stm.setFloat(8, spct.getKhoiLuong());
            stm.setInt(9, spct.getSoLuong());
            stm.setFloat(10, spct.getGia());
            stm.setString(11, spct.getMoTa());
            stm.setString(12, spct.getAnh());
            stm.setString(13, spct.getId());
            stm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return row;
    }

//    public SanPhamChiTiet findSPCT(String idspct) {
//        SanPhamChiTiet spct = new SanPhamChiTiet();
//        try {
//            Connection cn = DBContext.getConnection();
//            String sql = "Select * from SanPhamChiTiet where ID = ?";
//            PreparedStatement ps = cn.prepareStatement(sql);
//            ps.setString(1, idspct);
//            ResultSet rs = ps.executeQuery();
//            while (rs.next()) {
//                spct.setMaSPCT(rs.getString("MaSPCT"));
//                spct.setMaSP(rs.getString("MaSP"));
//                spct.setMaCL(rs.getString("MaCL"));
//                spct.setMaPL(rs.getString("MaPL"));
//                spct.setMaMS(rs.getString("MaMS"));
//                spct.setMaTH(rs.getString("MaTH"));
//                spct.setMaXX(rs.getString("MaXX"));
//                spct.setKichThuoc(rs.getString("KichThuoc"));
//                spct.setKhoiLuong(rs.getFloat("KhoiLuong"));
//                spct.setSoLuong(rs.getInt("SoLuong"));
//                spct.setGia(rs.getFloat("Gia"));
//                spct.setMoTa(rs.getString("MoTa"));
//                spct.setAnh(rs.getString("Anh"));
//                spct.setTrangThai(rs.getByte("TrangThai"));
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return spct;
//    }
    public List<SanPhamChiTiet> findSPCTByIDSP(String IDSP) {
        List<SanPhamChiTiet> lspct = new ArrayList<>();
        try {
            String sql = "select * FROM SanPhamChiTiet where IDSP = ?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, IDSP);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                SanPhamChiTiet spct = new SanPhamChiTiet();
                spct.setId(rs.getString("ID"));
                spct.setIdsp(rs.getString("IDSP"));
                spct.setIdcl(rs.getString("IDCL"));
                spct.setIdpl(rs.getString("IDPL"));
                spct.setIdms(rs.getString("IDMS"));
                spct.setIdth(rs.getString("IDTH"));
                spct.setIdxx(rs.getString("IDXX"));
                spct.setKichThuoc(rs.getString("KichThuoc"));
                spct.setKhoiLuong(rs.getFloat("KhoiLuong"));
                spct.setSoLuong(rs.getInt("SoLuong"));
                spct.setGia(rs.getFloat("Gia"));
                spct.setMoTa(rs.getString("MoTa"));
                spct.setAnh(rs.getString("Anh"));
                spct.setTrangThai(rs.getByte("TrangThai"));
                lspct.add(spct);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return lspct;
    }

    public SanPhamChiTiet findidspct(String IDSP) {
        SanPhamChiTiet spct = new SanPhamChiTiet();
        try {
            String sql = "select ID FROM SanPhamChiTiet where IDSP = ?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, IDSP);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {

                spct.setId(rs.getString("ID"));

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return spct;
    }

    public List<SanPhamChiTiet> findSPCTyIDSP(String MaSP) {
        List<SanPhamChiTiet> lspct = new ArrayList<>();
        try {
            String sql = "select * FROM SanPhamChiTiet where IDSP = ?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, MaSP);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                SanPhamChiTiet spct = new SanPhamChiTiet();
                spct.setId(rs.getString("ID"));
                spct.setIdsp(rs.getString("IDSP"));
                spct.setIdcl(rs.getString("IDCL"));
                spct.setIdpl(rs.getString("IDPL"));
                spct.setIdms(rs.getString("IDMS"));
                spct.setIdth(rs.getString("IDTH"));
                spct.setIdxx(rs.getString("IDXX"));
                spct.setKichThuoc(rs.getString("KichThuoc"));
                spct.setKhoiLuong(rs.getFloat("KhoiLuong"));
                spct.setSoLuong(rs.getInt("SoLuong"));
                spct.setGia(rs.getFloat("Gia"));
                spct.setMoTa(rs.getString("MoTa"));
                spct.setAnh(rs.getString("Anh"));
                spct.setTrangThai(rs.getByte("TrangThai"));
                lspct.add(spct);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return lspct;
    }

    public List<SanPhamChiTiet> gettAllByTenPL(String tePl) {
        List<SanPhamChiTiet> lspct = new ArrayList<>();
        try {
            String sql = "Select * from SanPhamChiTiet "
                    + "Join PhanLoai on SanPhamChiTiet.IDPL=PhanLoai.ID  Where TenPL=? ";
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setString(1, tePl);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                SanPhamChiTiet spct = new SanPhamChiTiet();
                spct.setId(rs.getString("ID"));
                spct.setIdsp(rs.getString("IDSP"));
                spct.setMaGG(rs.getString("MaGG"));
                spct.setIdcl(rs.getString("IDCL"));
                spct.setIdpl(rs.getString("IDPL"));
                spct.setIdms(rs.getString("IDMS"));
                spct.setIdth(rs.getString("IDTH"));
                spct.setIdxx(rs.getString("IDXX"));
                spct.setKichThuoc(rs.getString("KichThuoc"));
                spct.setKhoiLuong(rs.getFloat("KhoiLuong"));
                spct.setSoLuong(rs.getInt("SoLuong"));
                spct.setGia(rs.getFloat("Gia"));
                spct.setMoTa(rs.getString("MoTa"));
                spct.setAnh(rs.getString("Anh"));
                spct.setTrangThai(rs.getByte("TrangThai"));
                lspct.add(spct);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lspct;
    }

    public List<SanPhamChiTiet> gettAllChuaGG() {
        List<SanPhamChiTiet> lspct = new ArrayList<>();
        try {
            String sql = "Select * from SanPhamChiTiet WHere MaGG is  null ";
            PreparedStatement pstm = con.prepareStatement(sql);

            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                SanPhamChiTiet spct = new SanPhamChiTiet();
                spct.setId(rs.getString("ID"));
                spct.setIdsp(rs.getString("IDSP"));
                spct.setIdcl(rs.getString("IDCL"));
                spct.setIdpl(rs.getString("IDPL"));
                spct.setIdms(rs.getString("IDMS"));
                spct.setIdth(rs.getString("IDTH"));
                spct.setIdxx(rs.getString("IDXX"));
                spct.setKichThuoc(rs.getString("KichThuoc"));
                spct.setKhoiLuong(rs.getFloat("KhoiLuong"));
                spct.setSoLuong(rs.getInt("SoLuong"));
                spct.setGia(rs.getFloat("Gia"));
                spct.setMoTa(rs.getString("MoTa"));
                spct.setAnh(rs.getString("Anh"));
                spct.setTrangThai(rs.getByte("TrangThai"));
                lspct.add(spct);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return lspct;
    }

    public List<SanPhamChiTiet> gettAllCoGG() {
        List<SanPhamChiTiet> lspct = new ArrayList<>();
        try {
            String sql = "Select * from SanPhamChiTiet WHere MaGG is not null ";
            PreparedStatement pstm = con.prepareStatement(sql);

            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                SanPhamChiTiet spct = new SanPhamChiTiet();
                spct.setId(rs.getString("ID"));
                spct.setIdsp(rs.getString("IDSP"));
                spct.setIdcl(rs.getString("IDCL"));
                spct.setIdpl(rs.getString("IDPL"));
                spct.setIdms(rs.getString("IDMS"));
                spct.setIdth(rs.getString("IDTH"));
                spct.setIdxx(rs.getString("IDXX"));
                spct.setMaGG(rs.getString("MaGG"));
                spct.setKichThuoc(rs.getString("KichThuoc"));
                spct.setKhoiLuong(rs.getFloat("KhoiLuong"));
                spct.setSoLuong(rs.getInt("SoLuong"));
                spct.setGia(rs.getFloat("Gia"));
                spct.setMoTa(rs.getString("MoTa"));
                spct.setAnh(rs.getString("Anh"));
                spct.setTrangThai(rs.getByte("TrangThai"));
                lspct.add(spct);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return lspct;
    }

    public List<SanPhamChiTiet> Fake() {
        List<SanPhamChiTiet> l = new ArrayList<>();
        return l;
    }

    public List<SanPhamChiTiet> findByMaGG(String MaGG) {
        List<SanPhamChiTiet> ggList = new ArrayList<>();
        try {
            String sql = "Select * from SanPhamChiTiet Where MaGG =? and TrangThai=0";
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setString(1, MaGG);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                SanPhamChiTiet spct = new SanPhamChiTiet();
                spct.setId(rs.getString("ID"));
                spct.setIdsp(rs.getString("IDSP"));
                spct.setIdcl(rs.getString("IDCL"));
                spct.setIdpl(rs.getString("IDPL"));
                spct.setIdms(rs.getString("IDMS"));
                spct.setIdth(rs.getString("IDTH"));
                spct.setIdxx(rs.getString("IDXX"));
                spct.setMaGG(rs.getString("MaGG"));
                spct.setKichThuoc(rs.getString("KichThuoc"));
                spct.setKhoiLuong(rs.getFloat("KhoiLuong"));
                spct.setSoLuong(rs.getInt("SoLuong"));
                spct.setGia(rs.getFloat("Gia"));
                spct.setMoTa(rs.getString("MoTa"));
                spct.setAnh(rs.getString("Anh"));
                spct.setTrangThai(rs.getByte("TrangThai"));
                ggList.add(spct);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ggList;
    }

    public List<SanPhamChiTiet> setMaGGtoSPCT() {
        List<SanPhamChiTiet> lspct = new ArrayList<>();
        try {
            String sql = "Select * from SanPhamChiTiet WHere MaGG is null ";
            PreparedStatement pstm = con.prepareStatement(sql);

            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                SanPhamChiTiet spct = new SanPhamChiTiet();
                spct.setId(rs.getString("ID"));
                spct.setIdsp(rs.getString("IDSP"));
                spct.setIdcl(rs.getString("IDCL"));
                spct.setIdpl(rs.getString("IDPL"));
                spct.setIdms(rs.getString("IDMS"));
                spct.setIdth(rs.getString("IDTH"));
                spct.setIdxx(rs.getString("IDXX"));
                spct.setMaGG(rs.getString("MaGG"));
                spct.setKichThuoc(rs.getString("KichThuoc"));
                spct.setKhoiLuong(rs.getFloat("KhoiLuong"));
                spct.setSoLuong(rs.getInt("SoLuong"));
                spct.setGia(rs.getFloat("Gia"));
                spct.setMoTa(rs.getString("MoTa"));
                spct.setAnh(rs.getString("Anh"));
                spct.setTrangThai(rs.getByte("TrangThai"));
                lspct.add(spct);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return lspct;
    }

    public boolean ApDungMaGGtoSPCT(SanPhamChiTiet spct) {
        String sql = "UPDATE SanPhamChiTiet SET MaGG=? Where ID = ?";
        try {
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setString(1, spct.getMaGG());
            pstm.setString(2, spct.getId());

            pstm.executeUpdate();
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();

        }
        return false;
    }

    public List<SanPhamChiTiet> TimKiem(String tePl,String checkmagg ) {
        List<SanPhamChiTiet> lspct = new ArrayList<>();
        try {
            String sql = "Select * from SanPhamChiTiet join PhanLoai on PhanLoai.ID = SanPhamChiTiet.IDPL"
                    + " where TenPL Like N'%" + tePl + "%' "+checkmagg +"";
            PreparedStatement pstm = con.prepareStatement(sql);
//            pstm.setString(1, tePl);

            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                SanPhamChiTiet spct = new SanPhamChiTiet();
                spct.setId(rs.getString("ID"));
                spct.setIdsp(rs.getString("IDSP"));
                spct.setMaGG(rs.getString("MaGG"));
                spct.setIdcl(rs.getString("IDCL"));
                spct.setIdpl(rs.getString("IDPL"));
                spct.setIdms(rs.getString("IDMS"));
                spct.setIdth(rs.getString("IDTH"));
                spct.setIdxx(rs.getString("IDXX"));
                spct.setKichThuoc(rs.getString("KichThuoc"));
                spct.setKhoiLuong(rs.getFloat("KhoiLuong"));
                spct.setSoLuong(rs.getInt("SoLuong"));
                spct.setGia(rs.getFloat("Gia"));
                spct.setMoTa(rs.getString("MoTa"));
                spct.setAnh(rs.getString("Anh"));
                spct.setTrangThai(rs.getByte("TrangThai"));
                lspct.add(spct);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lspct;
    }
    public void checkMaGG(String MaGG){
        try {
            con = DBContext.getConnection();
            String sql = "Update SanPhamChiTiet Set MaGG = null  where MaGG = ?";
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setString(1, MaGG);
            pstm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
