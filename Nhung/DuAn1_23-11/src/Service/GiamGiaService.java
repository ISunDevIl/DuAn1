
package Service;

import Connection.DBContext;
import Model.GiamGia;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class GiamGiaService {
    Connection con  = DBContext.getConnection();
    
    public GiamGia findIDSP(String idspct) {
        GiamGia gh = new GiamGia();
        try {
            String sql = "Select GiaTri From GiamGia Where IDSPCT = ?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, idspct);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                gh.setGiaTri(rs.getFloat("GiaTri"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return gh;
    }
    
    
}
