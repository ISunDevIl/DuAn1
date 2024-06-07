/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import Connection.DBContext;
import Model.KhachHang;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author admin
 */
public class KhachHangService {

    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    String sql = null;

    public List<KhachHang> getAllKH() {
        List<KhachHang> listKH = new ArrayList<>();
        sql = "SELECT MaKH, HoTen, NgaySinh,GioiTinh, DiaChi, SDT, Email FROM KhachHang Where TrangThai = 0";
        try {
            con = DBContext.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                KhachHang kh = new KhachHang();
                kh.setMaKH(rs.getString("MaKH"));
                kh.setTenKH(rs.getString("HoTen"));
                kh.setNgaySinh(rs.getDate("NgaySinh"));
                kh.setGioiTinh(rs.getString("GioiTinh"));
                kh.setDiaChi(rs.getString("DiaChi"));
                kh.setSdt(rs.getString("SDT"));
                kh.setEmail(rs.getString("Email"));
                listKH.add(kh);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listKH;
    }

    public List<KhachHang> getAllKH1() {
        List<KhachHang> listKH = new ArrayList<>();
        sql = "SELECT MaKH, HoTen, NgaySinh,GioiTinh, DiaChi, SDT, Email FROM KhachHang Where TrangThai = 1";
        try {
            con = DBContext.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                KhachHang kh = new KhachHang();
                kh.setMaKH(rs.getString("MaKH"));
                kh.setTenKH(rs.getString("HoTen"));
                kh.setNgaySinh(rs.getDate("NgaySinh"));
                kh.setGioiTinh(rs.getString("GioiTinh"));
                kh.setDiaChi(rs.getString("DiaChi"));
                kh.setSdt(rs.getString("SDT"));
                kh.setEmail(rs.getString("Email"));
                listKH.add(kh);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listKH;
    }

    public Integer addKH(KhachHang kh) {
        Integer row = null;
        try {
            con = DBContext.getConnection();
            String sql = "Insert into KhachHang(MaKH, HoTen, NgaySinh,GioiTinh, DiaChi, SDT, Email,TrangThai) values(?,?,?,?,?,?,?,0)";
            ps = con.prepareStatement(sql);
            ps.setString(1, kh.getMaKH());
            ps.setString(2, kh.getTenKH());
            ps.setDate(3, new java.sql.Date(kh.getNgaySinh().getTime()));
            ps.setString(4, kh.getGioiTinh());
            ps.setString(5, kh.getDiaChi());
            ps.setString(6, kh.getSdt());
            ps.setString(7, kh.getEmail());

            row = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        
        }
        return row;
    }

    public Integer updateKH(KhachHang kh) {
        Integer row = null;
        try {
            con = DBContext.getConnection();
            String sql = "UPDATE KhachHang set HoTen=?, NgaySinh = ?, GioiTinh = ?, DiaChi = ?, SDT = ?, Email = ? where MaKH = ?";
            ps = con.prepareStatement(sql);

            ps.setString(1, kh.getTenKH());
            ps.setDate(2, new java.sql.Date(kh.getNgaySinh().getTime()));
            ps.setString(3, kh.getGioiTinh());
            ps.setString(4, kh.getDiaChi());
            ps.setString(5, kh.getSdt());
            ps.setString(6, kh.getEmail());
            ps.setString(7, kh.getMaKH());
            row = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        
        }
        return row;
    }

    public boolean anKH(KhachHang kh) {
        String sql = "UPDATE KhachHang SET TrangThai = 1 Where MaKH = ?";
        try {
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setString(1, kh.getMaKH());
            stm.executeUpdate();

            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();

            return false;
        }
    }

    public boolean hienthilaiKh(KhachHang kh) {
        String sql = "UPDATE KhachHang SET TrangThai = 0 Where MaKH = ?";
        try {
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setString(1, kh.getMaKH());
            stm.executeUpdate();

            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();

            return false;
        }
    }

}
