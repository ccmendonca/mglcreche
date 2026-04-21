
package skylink.mglcreche.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import skylink.mglcreche.bdutil.ConnectionDB;
import skylink.mglcreche.modelo.MatriculaDetalhe;

/**
 * «claudiomendonca»
 */

public class MatriculaDetalheDAO {
    
    public static final String INSERT = "INSERT INTO matricula_detalhes (id_servico, preco_servico, quantidade_servico) VALUES (?,?,?,?)";
    public static final String UPDATE = "UPDATE matricula_detalhes SET id_servico = ?, preco_servico = ?, quantidade_servico = ? WHERE id_matricula = ?";
    public static final String DELETE = "DELETE FROM matricula_detalhes WHERE id_matricula = ? ";
    public static final String SELECT_ALL = "SELECT * FROM matricula_detalhes";
    public static final String SELECT_BY_ID = "SELECT * FROM matricula_detalhes WHERE id_matricula = ? ";
    public static final String SELECT_BY_PARAMETER = "";

    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    boolean flagControlo = false;
    
    
    public void popularDados(MatriculaDetalhe det, ResultSet rs){
        
    }
    
    public boolean save(MatriculaDetalhe det){
        if(det == null){
            System.err.println("O campo não pode ser nulo!");
        }
        
        try {
            conn = ConnectionDB.getConnection();
            ps = conn.prepareStatement(INSERT);
            ps.setInt(1, det.getServico().getIdServico());
            ps.setInt(2, det.getQuantidadeServico());
            ps.setDouble(3, det.getPrecoServico());
            
            int retorno = ps.executeUpdate();
            
            if (retorno > 0){
                System.out.println("Daddos inserido com sucesso: " + ps.getUpdateCount());
                flagControlo = true;
            }
            
            return flagControlo;
            
        } catch (SQLException e) {
            System.err.println("Erro ao inserir dados: " + e.getMessage());
            return false;
        }finally{
            ConnectionDB.closeConnection(conn, ps);
        }
        
    }
    
    public boolean update(MatriculaDetalhe det){
        
        //implementar
        return false;
    }
    
    public boolean delete(MatriculaDetalhe det){
        
        //implementar
        return false;
    }
    
}

