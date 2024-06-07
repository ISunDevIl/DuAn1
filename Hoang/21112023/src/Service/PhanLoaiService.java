
package Service;

import Connection.DBContext;
import Model.PhanLoai;
import Model.ThuongHieu;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author admin
 */
public class PhanLoaiService {
    private Connection con = DBContext.getConnection();
    
    public boolean Them(PhanLoai ms){
        String sql = "Insert into PhanLoai (TenPL) VALUES (?)";
        try {
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setString(1, ms.getTen());
            stm.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean Sua(PhanLoai ms){
        String sql = "UPDATE PhanLoai SET  TenPL = ? WHERE ID = ?";
        try {
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setString(1, ms.getTen());
            stm.setString(2, ms.getId());
            stm.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean An(PhanLoai ms){
         String sql = "DELETE FROM PhanLoai Where ID = ?";
         try {
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setString(1, ms.getId());
            stm.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
         
    }
    public PhanLoai findPL(String id){
        PhanLoai pl = new PhanLoai();
        try {
            String sql = "select * from PhanLoai where ID = ?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, id);
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                pl.setId(rs.getString("id"));
                pl.setTen(rs.getString("TenPL"));
                
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        
        return pl;
    }
    
    public PhanLoai findidPL(String ten){
        PhanLoai ms= new PhanLoai();
        try {
            String sql = "select ID from PhanLoai where TenPL = ?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, ten);
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                ms.setId(rs.getString("id"));                
            }          
        } catch (Exception e) {
            e.printStackTrace();
        }   
        return ms;
    }
    
     public List<PhanLoai> gettAll() {
        List<PhanLoai> lspct = new ArrayList<>();
        try {
            String sql = "Select * from PhanLoai";
            PreparedStatement pstm = con.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                PhanLoai spct = new PhanLoai();
                spct.setId(rs.getString("ID"));
                spct.setTen(rs.getString("TenPL"));
                lspct.add(spct);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lspct;
    }
}
