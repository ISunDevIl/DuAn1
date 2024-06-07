package Service;

import Connection.DBContext;
import Model.KhuyenMai;
import Model.SanPham;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SanPhamService {

    Connection con = DBContext.getConnection();

    public List<SanPham> gettAll() {
        List<SanPham> lsp = new ArrayList<>();
        try {
            String sql = "Select * from SanPham where TrangThai = 0";
            PreparedStatement pstm = con.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                SanPham sp = new SanPham();
                sp.setId(rs.getString("ID"));
                sp.setMaSP(rs.getString("MaSP"));
                sp.setTenSP(rs.getString("TenSP"));
                sp.setTrangThai(rs.getByte("TrangThai"));
                lsp.add(sp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lsp;
    }

    public Integer AddSP(SanPham sp) {
        Integer row = null;
        String sql = "Insert into SanPham(MaSP,TenSP,TrangThai) values (?,?,0)";
        try {
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setString(1, sp.getMaSP());
            pstm.setString(2, sp.getTenSP());
            row = pstm.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return row;
    }

    public Integer UpdateSP(SanPham sp) {
        Integer row = null;
        String sql = "UPDATE SanPham SET TenSP=?,MaSP=? Where ID = ?";
        try {
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setString(1, sp.getTenSP());
            pstm.setString(2, sp.getMaSP());
            pstm.setString(3, sp.getId());
            row = pstm.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return row;
    }

    public Integer DeleteSP(String ID) {
        Integer row = null;
        String sql = "UPDATE SanPham SET TrangThai = 1 Where ID = ?";
        try {
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setString(1, ID);
            row = pstm.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return row;
    }

    public void HienThiLaiSP(String ID) {
        String sql = "UPDATE SanPham SET TrangThai = 0 Where ID = ?";
        try {
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setString(1, ID);
            pstm.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public SanPham findMaSP(String ma) {
        SanPham sp = new SanPham();
        try {
            String sql = "select * from SanPham where MaSP = ?";
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setString(1, ma);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                sp.setId(rs.getString("ID"));
                sp.setMaSP(rs.getString("MaSP"));
                sp.setTenSP(rs.getString("TenSP"));
                sp.setTrangThai(rs.getByte("TrangThai"));

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sp;
    }

    public SanPham findByID(String ID) {
        SanPham sp = new SanPham();
        try {
            String sql = "select * from SanPham where ID = ?";
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setString(1, ID);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                sp.setId(rs.getString("ID"));
                sp.setMaSP(rs.getString("MaSP"));
                sp.setTenSP(rs.getString("TenSP"));
                sp.setTrangThai(rs.getByte("TrangThai"));

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sp;
 
    }
    
      
}
