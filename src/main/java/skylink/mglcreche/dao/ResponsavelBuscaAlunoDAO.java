package skylink.mglcreche.dao;
 
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import skylink.mglcreche.bdutil.ConnectionDB;
import skylink.mglcreche.modelo.ResponsavelBuscaAluno;
import skylink.mglcreche.modelo.Sexo;
 /**
 *
 * @Henriques
 */
public class ResponsavelBuscaAlunoDAO implements Serializable {
 
    private static final String INSERT = "INSERT INTO responsavel_busca_aluno (nome_responsavel, sobrenome_responsavel, data_nascimento_responsavel, casa_responsavel, rua_responsavel, bairro_responsavel, id_municipio, telefone_responsavel, sexo_responsavel_busca_aluno, id_sexo, data_registo_responsavel) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
    private static final String UPDATE = "UPDATE responsavel_busca_aluno SET nome_responsavel = ?, sobrenome_responsavel = ?, data_nascimento_responsavel = ?, casa_responsavel = ?, rua_responsavel = ?, bairro_responsavel = ?, id_municipio = ?, telefone_responsavel = ?, sexo_responsavel_busca_aluno = ?, id_sexo = ?, data_registo_responsavel = ? WHERE id_responsavel = ?";
    private static final String DELETE = "DELETE FROM responsavel_busca_aluno WHERE id_responsavel = ?";
    private static final String SELECT_ALL = "SELECT * FROM responsavel_busca_aluno ORDER BY nome_responsavel";
    private static final String SELECT_BY_ID = "SELECT * FROM responsavel_busca_aluno WHERE id_responsavel = ?";
    private static final String SELECT_BY_PARAMETER = "SELECT * FROM responsavel_busca_aluno WHERE nome_responsavel LIKE ? OR sobrenome_responsavel LIKE ? OR telefone_responsavel LIKE ? ORDER BY nome_responsavel";
 
 
    public List<ResponsavelBuscaAluno> buscar(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            return findAll();
        }
 
        List<ResponsavelBuscaAluno> lista = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = ConnectionDB.getConnection();
            ps = conn.prepareStatement(SELECT_BY_PARAMETER);
            String padrao = "%" + nome.trim() + "%";
            ps.setString(1, padrao);
            ps.setString(2, padrao);
            ps.setString(3, padrao);
            rs = ps.executeQuery();
            while (rs.next()) {
                ResponsavelBuscaAluno r = new ResponsavelBuscaAluno();
                popularDados(r, rs);
                lista.add(r);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao pesquisar: " + e.getLocalizedMessage());
        } finally {
            ConnectionDB.closeConnection(conn, ps, rs);
        }
        return lista;
    }
 
 
    public boolean save(ResponsavelBuscaAluno r) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = ConnectionDB.getConnection();
            ps = conn.prepareStatement(INSERT);
            ps.setString(1, r.getNomeResponsavel());
            ps.setString(2, r.getSobrenomeResponsavel());
 
            if (r.getDataNascimentoResponsavel() != null) {
                ps.setDate(3, new java.sql.Date(r.getDataNascimentoResponsavel().getTime()));
            } else {
                ps.setNull(3, java.sql.Types.DATE);
            }
 
            ps.setString(4, r.getCasaResponsavel());
            ps.setString(5, r.getRuaResponsavel());
            ps.setString(6, r.getBairroResponsavel());
            ps.setInt(7, r.getIdMunicipio());
            ps.setString(8, r.getTelefoneResponsavel());
            ps.setString(9, r.getSexoResponsavel());
            ps.setInt(10, r.getIdSexo());
            ps.setTimestamp(11, new java.sql.Timestamp(System.currentTimeMillis()));
 
            return ps.executeUpdate() > 0;
 
        } catch (SQLException e) {
            System.err.println("Erro ao inserir dados: " + e.getMessage());
            return false;
        } finally {
            ConnectionDB.closeConnection(conn, ps);
        }
    }
 
    public boolean update(ResponsavelBuscaAluno r) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = ConnectionDB.getConnection();
            ps = conn.prepareStatement(UPDATE);
            ps.setString(1, r.getNomeResponsavel());
            ps.setString(2, r.getSobrenomeResponsavel());
 
            if (r.getDataNascimentoResponsavel() != null) {
                ps.setDate(3, new java.sql.Date(r.getDataNascimentoResponsavel().getTime()));
            } else {
                ps.setNull(3, java.sql.Types.DATE);
            }
 
            ps.setString(4, r.getCasaResponsavel());
            ps.setString(5, r.getRuaResponsavel());
            ps.setString(6, r.getBairroResponsavel());
            ps.setInt(7, r.getIdMunicipio());
            ps.setString(8, r.getTelefoneResponsavel());
            ps.setString(9, r.getSexoResponsavel());
 
            if (r.getIdSexo() != null) {
                ps.setInt(10, r.getIdSexo());
            } else {
                ps.setNull(10, java.sql.Types.INTEGER);
            }
 
            ps.setTimestamp(11, new java.sql.Timestamp(System.currentTimeMillis()));
            ps.setInt(12, r.getIdResponsavel());
 
            return ps.executeUpdate() > 0;
 
        } catch (SQLException e) {
            System.err.println("Erro ao actualizar dados: " + e.getLocalizedMessage());
            return false;
        } finally {
            ConnectionDB.closeConnection(conn, ps);
        }
    }
 
    public boolean delete(ResponsavelBuscaAluno r) {
        if (r == null) {
            System.err.println("O objecto não pode ser nulo!");
            return false;
        }
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = ConnectionDB.getConnection();
            ps = conn.prepareStatement(DELETE);
            ps.setInt(1, r.getIdResponsavel());
            return ps.executeUpdate() > 0;
 
        } catch (SQLException e) {
            System.err.println("Erro ao eliminar dados: " + e.getMessage());
            return false;
        } finally {
            ConnectionDB.closeConnection(conn, ps);
        }
    }
 
    public List<ResponsavelBuscaAluno> findAll() {
        List<ResponsavelBuscaAluno> lista = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = ConnectionDB.getConnection();
            ps = conn.prepareStatement(SELECT_ALL);
            rs = ps.executeQuery();
            while (rs.next()) {
                ResponsavelBuscaAluno r = new ResponsavelBuscaAluno();
                popularDados(r, rs);
                lista.add(r);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao carregar dados: " + e.getLocalizedMessage());
        } finally {
            ConnectionDB.closeConnection(conn, ps, rs);
        }
        return lista;
    }
 
    public ResponsavelBuscaAluno findById(Integer id) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = ConnectionDB.getConnection();
            ps = conn.prepareStatement(SELECT_BY_ID);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                ResponsavelBuscaAluno r = new ResponsavelBuscaAluno();
                popularDados(r, rs);
                return r;
            }
        } catch (SQLException e) {
            System.err.println("Erro ao carregar dados: " + e.getLocalizedMessage());
        } finally {
            ConnectionDB.closeConnection(conn, ps, rs);
        }
        return null;
    }
 
    private void popularDados(ResponsavelBuscaAluno r, ResultSet rs) {
        try {
            r.setIdResponsavel(rs.getInt("id_responsavel"));
            r.setNomeResponsavel(rs.getString("nome_responsavel"));
            r.setSobrenomeResponsavel(rs.getString("sobrenome_responsavel"));
            r.setDataNascimentoResponsavel(rs.getDate("data_nascimento_responsavel"));
            r.setCasaResponsavel(rs.getString("casa_responsavel"));
            r.setRuaResponsavel(rs.getString("rua_responsavel"));
            r.setBairroResponsavel(rs.getString("bairro_responsavel"));
            r.setIdMunicipio(rs.getInt("id_municipio"));
            r.setTelefoneResponsavel(rs.getString("telefone_responsavel"));
            r.setSexoResponsavel(rs.getString("sexo_responsavel_busca_aluno"));
            r.setIdSexo(rs.getInt("id_sexo"));
            r.setDataRegistoResponsavel(rs.getTimestamp("data_registo_responsavel"));
 
            Sexo sexo = new Sexo();
            sexo.setIdSexo(rs.getInt("id_sexo"));
            sexo.setDescricaoSexo(rs.getString("sexo_responsavel_busca_aluno"));
            r.setSexo(sexo);
 
        } catch (SQLException e) {
            System.err.println("Erro ao mapear dados: " + e.getLocalizedMessage());
        }
    }
}