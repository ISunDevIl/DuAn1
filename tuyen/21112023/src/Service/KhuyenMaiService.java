
package Service;

import Connection.DBContext;
import Model.KhuyenMai;
import Model.MauSac;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class KhuyenMaiService {
    Connection con  = DBContext.getConnection();
    
   public List<KhuyenMai> gettAll() {
        List<KhuyenMai> ggList = new ArrayList<>();
        try {
            String sql = "Select * from GiamGia";
            PreparedStatement pstm = con.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                KhuyenMai gg  = new KhuyenMai();
                gg.setMaGG(rs.getString("MaGG"));
                gg.setId(rs.getString("IDSPCT"));
                gg.setTenGG(rs.getString("Ten"));
                gg.setGiaTri(rs.getFloat("GiaTri"));
                gg.setNgayBatDau(rs.getDate("NgayBatDau"));
                gg.setNgayHetHan(rs.getDate("NgayHetHan"));
                gg.setGhiChu(rs.getString("GhiChu"));
                gg.setTrangThai(rs.getBoolean("TrangThai"));
                
                ggList.add(gg);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ggList;
    }
    
    public KhuyenMai findIDSP(String idspct) {
        KhuyenMai gh = new KhuyenMai();
        try {
            String sql = "Select MaGG,GiaTri From GiamGia Where IDSPCT = ?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, idspct);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                gh.setMaGG(rs.getString("MaGG"));
                gh.setGiaTri(rs.getFloat("GiaTri"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return gh;
    }
    
    public KhuyenMai findgiatri(String magg) {
        KhuyenMai gh = new KhuyenMai();
        try {
            String sql = "Select GiaTri From GiamGia Where MaGG = ?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, magg);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                gh.setGiaTri(rs.getFloat("GiaTri"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return gh;
    }
    public boolean ThemKhuyenMai( KhuyenMai km) {
        
        String sql = "Insert into GiamGia(MaGG,IDSPCT,Ten,GiaTri,NgayBatDau,NgayHetHan,GhiChu,TrangThai) values (?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setString(1, km.getMaGG());
            pstm.setString(2, km.getIdlgg());
            pstm.setString(3, km.getTenGG());
            pstm.setFloat(4, km.getGiaTri());
            pstm.setDate(5,new java.sql.Date( km.getNgayBatDau().getTime()));
            pstm.setDate(6,new java.sql.Date( km.getNgayHetHan().getTime()));
            pstm.setString(7, km.getGhiChu());
            pstm.setBoolean(8, km.getTrangThai());
            
             pstm.executeUpdate();
return  true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    
    
    
    
}
