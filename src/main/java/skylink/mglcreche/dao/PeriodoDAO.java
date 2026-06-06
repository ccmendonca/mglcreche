package skylink.mglcreche.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import skylink.mglcreche.bdutil.ConnectionDB;
import skylink.mglcreche.modelo.Periodo;

/**
 *
 * @Henriques
 */
public class PeriodoDAO implements Serializable {
    
    private static final long serialVersionUID = 1L; 

    private static final String SELECT_ALL = "SELECT * FROM periodo"; 
    private static final String SELECT_BY_ID = "SELECT * FROM periodo WHERE id_periodo = ?"; 
    
    public List<Periodo> findAll() throws SQLException {
        List<Periodo> lista = new ArrayList<>(); 
        
        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(SELECT_ALL);
             ResultSet rs = ps.executeQuery()) { 

            while (rs.next()) { 
                Periodo p = new Periodo(); 
                p.setIdPeriodo(rs.getInt("id_periodo")); 
                p.setDescricaoPeriodo(rs.getString("descricao_periodo")); 
                
                lista.add(p); 
            }
        } 
        
        return lista;
    }

    public Periodo findById(Integer id) throws SQLException {
        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(SELECT_BY_ID)) { 

            ps.setInt(1, id); 
            try (ResultSet rs = ps.executeQuery()) { 
                if (rs.next()) { 
                    Periodo p = new Periodo(); 
                    p.setIdPeriodo(rs.getInt("id_periodo")); 
                    p.setDescricaoPeriodo(rs.getString("descricao_periodo")); 
                    return p; 
                }
            }
        }
        return null; 
    }
}