
package skylink.mglcreche.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import skylink.mglcreche.bdutil.ConnectionDB;
import skylink.mglcreche.modelo.Aluno;

/**
 * «claudiomendonca»
 */

public class AlunoDAO {
    
    public static final String INSERT = "";
    public static final String UPDATE = "";
    public static final String DELETE = "";
    public static final String SELECT_ALL = "";
    public static final String SELECT_BY_ID = "";
    public static final String SELECT_BY_PARAMETER = "";
    
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    boolean flagControlo = false;
    
    public boolean save (Aluno aluno){
        
        try {
            
            conn = ConnectionDB.getConnection();
            ps = conn.prepareStatement(INSERT);
            ps.setInt(1, aluno.getIdAluno());
            ps.setString(2, aluno.getNomeAluno());
            ps.setString(3, aluno.getSobrenomeAluno());
             ps.setDate(4, new java.sql.Date(aluno.getDataNascimentoAluno().getTime()));
            return false;
        } catch (SQLException e) {
            System.out.println("Erro ao inserir dados: " + e.getMessage());
            return false;
        }finally{
        ConnectionDB.closeConnection(conn, ps);
    }
        
    }
}
