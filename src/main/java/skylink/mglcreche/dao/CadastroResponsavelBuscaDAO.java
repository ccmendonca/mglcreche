package skylink.mglcreche.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import skylink.mglcreche.bdutil.ConnectionDB;
import skylink.mglcreche.modelo.Aluno;
import skylink.mglcreche.modelo.AnoLectivo;
import skylink.mglcreche.modelo.CadastroResponsavelBusca;
import skylink.mglcreche.modelo.GrauParentesco;
import skylink.mglcreche.modelo.ResponsavelBuscaAluno;

/**
 *
 * @author Henriques
 */
public class CadastroResponsavelBuscaDAO implements Serializable {

    private static final long serialVersionUID = 1L;

    private static final String INSERT = "INSERT INTO cadastro_responsavel_busca (data_registo, id_aluno, id_grau_parentesco, id_responsavel, id_ano_lectivo) VALUES (?, ?, ?, ?, ?)";
    private static final String UPDATE = "UPDATE cadastro_responsavel_busca SET data_registo = ?, id_aluno = ?, id_grau_parentesco = ?, id_responsavel = ?, id_ano_lectivo = ? WHERE id_registo_responsavel_busca = ?";
    private static final String DELETE = "DELETE FROM cadastro_responsavel_busca WHERE id_registo_responsavel_busca = ?";
    private static final String SELECT_ALL = "SELECT * FROM cadastro_responsavel_busca ORDER BY id_registo_responsavel_busca";
    private static final String SELECT_BY_ID = "SELECT * FROM cadastro_responsavel_busca WHERE id_registo_responsavel_busca = ?";

    public boolean save(CadastroResponsavelBusca c) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = ConnectionDB.getConnection();
            ps = conn.prepareStatement(INSERT);

            if (c.getDataRegisto() != null) {
                ps.setDate(1, new java.sql.Date(c.getDataRegisto().getTime()));
            } else {
                ps.setNull(1, java.sql.Types.DATE);
            }

            if (c.getAluno() != null) {
                ps.setInt(2, c.getAluno().getIdAluno());
            } else {
                ps.setNull(2, java.sql.Types.INTEGER);
            }

            if (c.getGrauParentesco() != null) {
                ps.setInt(3, c.getGrauParentesco().getIdGrauParentesco());
            } else {
                ps.setNull(3, java.sql.Types.INTEGER);
            }

            if (c.getResponsavelBuscaAluno() != null) {
                ps.setInt(4, c.getResponsavelBuscaAluno().getIdResponsavel());
            } else {
                ps.setNull(4, java.sql.Types.INTEGER);
            }

            if (c.getAnoLectivo() != null) {
                ps.setInt(5, c.getAnoLectivo().getIdAnoLectivo());
            } else {
                ps.setNull(5, java.sql.Types.INTEGER);
            }

            int retorno = ps.executeUpdate();
            if (retorno > 0) {
                System.out.println("Dados inseridos com sucesso: " + retorno);
                return true;
            }
            return false;

        } catch (SQLException e) {
            System.out.println("Erro ao inserir dados: " + e.getMessage());
            return false;
        } finally {
            ConnectionDB.closeConnection(conn, ps);
        }
    }

    public boolean actualizar(CadastroResponsavelBusca c) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = ConnectionDB.getConnection();
            ps = conn.prepareStatement(UPDATE);

            if (c.getDataRegisto() != null) {
                ps.setDate(1, new java.sql.Date(c.getDataRegisto().getTime()));
            } else {
                ps.setNull(1, java.sql.Types.DATE);
            }

            if (c.getAluno() != null) {
                ps.setInt(2, c.getAluno().getIdAluno());
            } else {
                ps.setNull(2, java.sql.Types.INTEGER);
            }

            if (c.getGrauParentesco() != null) {
                ps.setInt(3, c.getGrauParentesco().getIdGrauParentesco());
            } else {
                ps.setNull(3, java.sql.Types.INTEGER);
            }

            if (c.getResponsavelBuscaAluno() != null) {
                ps.setInt(4, c.getResponsavelBuscaAluno().getIdResponsavel());
            } else {
                ps.setNull(4, java.sql.Types.INTEGER);
            }

            if (c.getAnoLectivo() != null) {
                ps.setInt(5, c.getAnoLectivo().getIdAnoLectivo());
            } else {
                ps.setNull(5, java.sql.Types.INTEGER);
            }

            ps.setInt(6, c.getIdResponsavelBusca());

            int retorno = ps.executeUpdate();
            if (retorno > 0) {
                System.out.println("Dados atualizados com sucesso: " + retorno);
                return true;
            }
            return false;

        } catch (SQLException e) {
            System.out.println("Erro ao atualizar dados: " + e.getMessage());
            return false;
        } finally {
            ConnectionDB.closeConnection(conn, ps);
        }
    }

    public boolean delete(Integer idResponsavelBusca) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = ConnectionDB.getConnection();
            ps = conn.prepareStatement(DELETE);
            ps.setInt(1, idResponsavelBusca);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Erro ao eliminar dados: " + e.getMessage());
            return false;
        } finally {
            ConnectionDB.closeConnection(conn, ps);
        }
    }

    public List<CadastroResponsavelBusca> findAll() throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<CadastroResponsavelBusca> lista = new ArrayList<>();

        try {
            conn = ConnectionDB.getConnection();
            ps = conn.prepareStatement(SELECT_ALL);
            rs = ps.executeQuery();

            while (rs.next()) {
                lista.add(mapear(rs));
            }
            return lista;

        } catch (SQLException e) {
            System.out.println("Erro ao listar dados: " + e.getMessage());
            return lista;
        } finally {
            ConnectionDB.closeConnection(conn, ps, rs);
        }
    }

    public CadastroResponsavelBusca findById(Integer idResponsavelBusca) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = ConnectionDB.getConnection();
            ps = conn.prepareStatement(SELECT_BY_ID);
            ps.setInt(1, idResponsavelBusca);
            rs = ps.executeQuery();

            if (rs.next()) {
                return mapear(rs);
            }
            return null;

        } catch (SQLException e) {
            System.out.println("Erro ao buscar dados: " + e.getMessage());
            return null;
        } finally {
            ConnectionDB.closeConnection(conn, ps, rs);
        }
    }

    private CadastroResponsavelBusca mapear(ResultSet rs) throws SQLException {
        Integer id = rs.getInt("id_registo_responsavel_busca");
        java.util.Date data = rs.getDate("data_registo");
        int idAluno = rs.getInt("id_aluno");
        int idGrauParentesco = rs.getInt("id_grau_parentesco");
        int idResponsavel = rs.getInt("id_responsavel");
        int idAnoLectivo = rs.getInt("id_ano_lectivo");

        Aluno aluno = new Aluno();
        aluno.setIdAluno(idAluno);

        GrauParentesco grau = new GrauParentesco();
        grau.setIdGrauParentesco(idGrauParentesco);

        ResponsavelBuscaAluno resp = new ResponsavelBuscaAluno();
        resp.setIdResponsavel(idResponsavel);

        AnoLectivo ano = new AnoLectivo();
        ano.setIdAnoLectivo(idAnoLectivo);

        return new CadastroResponsavelBusca(id, data, aluno, grau, resp, ano);
    }
}