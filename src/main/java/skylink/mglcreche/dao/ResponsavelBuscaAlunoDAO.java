package skylink.mglcreche.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import skylink.mglcreche.bdutil.ConnectionDB;
import skylink.mglcreche.modelo.ResponsavelBuscaAluno;

/**
 * @Henriques
 */
public class ResponsavelBuscaAlunoDAO {

    private static final String INSERT = "INSERT INTO responsavel_busca_aluno (nome_responsavel, sobrenome_responsavel, data_nascimento_responsavel, casa_responsavel, rua_responsavel, bairro_responsavel, telefone_responsavel, data_registo_responsavel) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String UPDATE = "UPDATE responsavel_busca_aluno SET nome_responsavel = ?, sobrenome_responsavel = ?, data_nascimento_responsavel = ?, casa_responsavel = ?, rua_responsavel = ?, bairro_responsavel = ?, telefone_responsavel = ? WHERE id_responsavel = ?";
    private static final String DELETE = "DELETE FROM responsavel_busca_aluno WHERE id_responsavel = ?";
    private static final String BUSCAR_POR_CODIGO = "SELECT * FROM responsavel_busca_aluno WHERE id_responsavel = ?";
    private static final String LISTAR_TUDO = "SELECT * FROM responsavel_busca_aluno";

    public boolean save(ResponsavelBuscaAluno responsavel) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = ConnectionDB.getConnection();
            ps = conn.prepareStatement(INSERT);
            
            ps.setString(1, responsavel.getNomeResponsavel());
            ps.setString(2, responsavel.getSobrenomeResponsavel());
            
         
            ps.setDate(3, responsavel.getDataNascimentoResponsavel());
            
            ps.setString(4, responsavel.getCasaResponsavel());
            ps.setString(5, responsavel.getRuaResponsavel());
            ps.setString(6, responsavel.getBairroResponsavel());
            ps.setString(7, responsavel.getTelefoneResponsavel());
            
           
            ps.setDate(8, responsavel.getDataRegistoResponsavel());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Erro ao inserir responsável: " + e.getMessage());
            return false;
        } finally {
            ConnectionDB.closeConnection(conn, ps);
        }
    }

    public boolean update(ResponsavelBuscaAluno responsavel) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = ConnectionDB.getConnection();
            ps = conn.prepareStatement(UPDATE);
            
            ps.setString(1, responsavel.getNomeResponsavel());
            ps.setString(2, responsavel.getSobrenomeResponsavel());
            ps.setDate(3, responsavel.getDataNascimentoResponsavel());
            ps.setString(4, responsavel.getCasaResponsavel());
            ps.setString(5, responsavel.getRuaResponsavel());
            ps.setString(6, responsavel.getBairroResponsavel());
            ps.setString(7, responsavel.getTelefoneResponsavel());
            ps.setInt(8, responsavel.getIdResponsavel());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar dados: " + e.getMessage());
            return false;
        } finally {
            ConnectionDB.closeConnection(conn, ps);
        }
    }

    public boolean delete(ResponsavelBuscaAluno responsavel) {
        if (responsavel == null || responsavel.getIdResponsavel() == null) return false;
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = ConnectionDB.getConnection();
            ps = conn.prepareStatement(DELETE);
            ps.setInt(1, responsavel.getIdResponsavel());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            return false;
        } finally {
            ConnectionDB.closeConnection(conn, ps);
        }
    }

    public List<ResponsavelBuscaAluno> findAll() {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<ResponsavelBuscaAluno> responsaveis = new ArrayList<>();
        try {
            conn = ConnectionDB.getConnection();
            ps = conn.prepareStatement(LISTAR_TUDO);
            rs = ps.executeQuery();
            while (rs.next()) {
                ResponsavelBuscaAluno r = new ResponsavelBuscaAluno();
                popularComDados(r, rs);
                responsaveis.add(r);
            }
        } catch (SQLException ex) {
            System.err.println("Erro ao listar: " + ex.getMessage());
        } finally {
            ConnectionDB.closeConnection(conn, ps, rs);
        }
        return responsaveis;
    }

    private void popularComDados(ResponsavelBuscaAluno r, ResultSet rs) throws SQLException {
        r.setIdResponsavel(rs.getInt("id_responsavel"));
        r.setNomeResponsavel(rs.getString("nome_responsavel"));
        r.setSobrenomeResponsavel(rs.getString("sobrenome_responsavel"));
        
       
        r.setDataNascimentoResponsavel(rs.getDate("data_nascimento_responsavel"));
        
        r.setCasaResponsavel(rs.getString("casa_responsavel"));
        r.setRuaResponsavel(rs.getString("rua_responsavel"));
        r.setBairroResponsavel(rs.getString("bairro_responsavel"));
        r.setTelefoneResponsavel(rs.getString("telefone_responsavel"));
        
        
        r.setDataRegistoResponsavel(rs.getDate("data_registo_responsavel"));
    }
}