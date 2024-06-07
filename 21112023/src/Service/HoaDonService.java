package Service;

import Connection.DBContext;
import Model.HoaDon;
import Model.SanPham;
import Model.SanPhamChiTiet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class HoaDonService {

//    Connection con = DBContext.getConnection();
//    PreparedStatement ps = null;
//    ResultSet rs = null;
//
//    public List<SanPham> getAllSanPham() {
//        List<SanPham> listSP = new ArrayList<>();
//        try {
//            String sql = "  Select MaSP,TenSP , KhoiLuong,SoLuong,Gia,KichThuoc,IDTH,IDMS,IDPL,IDCL,IDXX From SanPham\n"
//                    + "  Join SanPham_SanPhamChiTiet on SanPham.ID = SanPham_SanPhamChiTiet.IDSanPham\n"
//                    + "  join SanPhamChiTiet on SanPham_SanPhamChiTiet.IDSanPhamChiTiet = SanPhamChiTiet.ID";
//            ps = con.prepareStatement(sql);
//            rs = ps.executeQuery();
//            while (rs.next()) {
//                SanPham_SPCT sp = new SanPham_SPCT();
//                sp.setMaSP(rs.getString("MaSP"));
//                sp.setTenSP(rs.getString("TenSP")); 
//                sp.setIdCL(rs.getString("IDCL"));
//                sp.setIdPL(rs.getString("IDPL"));
//                sp.setIdMS(rs.getString("IDMS"));
//                sp.setIdTH(rs.getString("IDTH"));
//                sp.setIdXX(rs.getString("IDXX"));
//                sp.setKhoiLuong(rs.getFloat("KhoiLuong"));
//                sp.setSoLuong(rs.getInt("SoLuong"));
//                sp.setGia(rs.getFloat("Gia"));
//                sp.setKichThuoc(rs.getString("KichThuoc"));
//                listSP.add(sp);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return listSP;
//    }
//    
//    public List<HoaDon> getAllHoaDon(){
//        List<HoaDon> listhd = new ArrayList<>();
//        try {
//            String sql = "Select * from HoaDon where TrangThai = 0";
//             ps = con.prepareStatement(sql);
//            rs = ps.executeQuery();
//            while (rs.next()) {                
//                HoaDon hd = new HoaDon();
//                hd.setMaHD(rs.getString("MaHD"));
//                hd.setIdkh(rs.getString("IDKH"));
//                hd.setTrangThai(rs.getByte("TrangThai"));
//                hd.setThoiGianTao(rs.getString("ThoiGianTao"));
//                
//                listhd.add(hd);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return listhd;
//    }
//    
//    public boolean them(HoaDon th) {
//        String sql = "Insert into HoaDon (MaHD,IDKH,ThoiGianTao,TrangThai) Values (?,?,?,0)";
//        try {
//
//            PreparedStatement stm = con.prepareStatement(sql);
//            stm.setString(1, th.getMaHD());
//            stm.setString(2, th.getIdkh());
//            stm.setString(3, th.getThoiGianTao());
//            stm.executeUpdate();
//            return true;
//
//        } catch (Exception ex) {
//            ex.printStackTrace();
//            return false;
//        }
//    }
}
