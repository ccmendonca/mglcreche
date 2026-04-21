
package skylink.mglcreche.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import skylink.mglcreche.bdutil.ConnectionDB;
import skylink.mglcreche.modelo.Servico;

/**
 * «claudiomendonca»
 */

public class ServicoDAO {

    public static final String INSERT = "INSERT INTO (descricao_servico, preco_servico) VALUES (?,?)";
    public static final String UPDATE = "UPDATE servico SET descricao_servico = ?, preco_servico = ? WHERE id_servico = ?";
    public static final String DELETE = "DELETE FROM servico WHERE id_servico = ? ";
    public static final String SELECT_ALL = "SELECT * FROM servico";
    public static final String SELECT_BY_ID = "SELECT * FROM servico WHERE id_servico = ? ";

    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    boolean flagControlo = false;
    
    public void popularDados(Servico servico, ResultSet rs){
        
        try {
            servico.setIdServico(rs.getInt("id_servico"));
            servico.setDescricaoServico(rs.getString("descricao_servico"));
            servico.setPrecoServico(rs.getDouble("preco_servico"));
            
        } catch (SQLException e) {
            System.err.println("Erro ao carregar dados: " + e.getLocalizedMessage());
        }
    }

    public boolean save(Servico servico) {

        try {

            conn = ConnectionDB.getConnection();
            ps = conn.prepareStatement(INSERT);
            ps.setString(1, servico.getDescricaoServico());
            ps.setDouble(2, servico.getPrecoServico());

            int retorno = ps.executeUpdate();
            if (retorno > 0) {
                System.out.println("Dados inseridos com sucesso: " + ps.getUpdateCount());
                flagControlo = true;
            }
            
            return flagControlo;
        } catch (SQLException e) {
            System.err.println("Erro ao inserir dados: " + e.getMessage());
            return false;
        } finally {
            ConnectionDB.closeConnection(conn);
        }
    }
    
    public boolean update(Servico servico){
        
        try {
            
            conn = ConnectionDB.getConnection();
            ps = conn.prepareStatement(INSERT);
            ps.setString(1, servico.getDescricaoServico());
            ps.setDouble(2, servico.getPrecoServico());

            int retorno = ps.executeUpdate();
            if (retorno > 0) {
                System.out.println("Dados inseridos com sucesso: " + ps.getUpdateCount());
                flagControlo = true;
            }
            
            return flagControlo;
            
        } catch (SQLException e) {
            System.err.println("Erro ao actualizar dados: " + e.getLocalizedMessage());
            return false;
        }finally{
            ConnectionDB.closeConnection(conn);
        }
        
    }

    public boolean delete(Servico servico){
        
        if(servico == null){
            System.err.println("O campo não pode ser nulo!");
        }
        try {
            conn = ConnectionDB.getConnection();
            ps = conn.prepareStatement(DELETE);
            ps.setInt(1, servico.getIdServico());
            
            int retorno = ps.executeUpdate();
            if (retorno > 0){
                System.out.println("Dados eliminados com sucesso!");
                flagControlo = true;
            }
            return flagControlo;
            
        } catch (SQLException e) {
            System.err.println("Erro ao eliminar dados: " + e.getLocalizedMessage());
            return false;
        }finally{
            ConnectionDB.closeConnection(conn, ps);
        }
        
    }
    
    public List<Servico> findAll(){
        List<Servico> servicos = new ArrayList<>();
        
        try {
            conn = ConnectionDB.getConnection();
            ps = conn.prepareStatement(SELECT_ALL);
            rs = ps.executeQuery();
            
            while(rs.next()){
                
                Servico servico = new Servico();
                popularDados(servico, rs);
                servicos.add(servico);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao carregar dados da base de dados: " + e.getLocalizedMessage());
        }finally{
            ConnectionDB.closeConnection(conn, ps);
        }
        
        return servicos;
    }
    
    public List<Servico> findById(Integer id){
        List<Servico> servicos = new ArrayList<>();
        Servico servico = new Servico();
        
        try {
            conn = ConnectionDB.getConnection();
            ps = conn.prepareStatement(SELECT_BY_ID);
            ps.setInt(1, id);
            ps.executeQuery();
            
            if (!rs.next()){
                System.err.println("Não foi encontrado nenhum serviço com o código inserido: " + id);
            }
            
            popularDados(servico, rs);
            
        } catch (SQLException e) {
            System.err.println("Erro ao carregar dados da base de dados: " + e.getLocalizedMessage());
        }finally{
            ConnectionDB.closeConnection(conn, ps, rs);
        }
            return servicos;
        
    }

}
