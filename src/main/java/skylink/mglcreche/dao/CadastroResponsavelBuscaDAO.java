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
    private static final String SELECT_ALL = "SELECT c.*, a.nome_aluno, g.descricao_grau_parentesco, r.nome_responsavel, ano.descricao_ano_lectivo FROM cadastro_responsavel_busca c LEFT JOIN aluno a ON c.id_aluno = a.id_aluno LEFT JOIN grau_parentesco g ON c.id_grau_parentesco = g.id_grau_parentesco LEFT JOIN responsavel_busca_aluno r ON c.id_responsavel = r.id_responsavel LEFT JOIN ano_lectivo ano ON c.id_ano_lectivo = ano.id_ano_lectivo ORDER BY c.id_registo_responsavel_busca";
    private static final String SELECT_BY_ID = "SELECT c.*, a.nome_aluno, g.descricao_grau_parentesco, r.nome_responsavel, ano.descricao_ano_lectivo FROM cadastro_responsavel_busca c LEFT JOIN aluno a ON c.id_aluno = a.id_aluno LEFT JOIN grau_parentesco g ON c.id_grau_parentesco = g.id_grau_parentesco LEFT JOIN responsavel_busca_aluno r ON c.id_responsavel = r.id_responsavel LEFT JOIN ano_lectivo ano ON c.id_ano_lectivo = ano.id_ano_lectivo WHERE c.id_registo_responsavel_busca = ?";

    public boolean save(CadastroResponsavelBusca c) throws SQLException {
        Connection conn = ConnectionDB.getConnection();
        PreparedStatement ps = conn.prepareStatement(INSERT);

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
        ConnectionDB.closeConnection(conn, ps);
        return retorno > 0;
    }

    public boolean actualizar(CadastroResponsavelBusca c) throws SQLException {
        Connection conn = ConnectionDB.getConnection();
        PreparedStatement ps = conn.prepareStatement(UPDATE);

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
        ConnectionDB.closeConnection(conn, ps);
        return retorno > 0;
    }

    public boolean delete(Integer idResponsavelBusca) throws SQLException {
        Connection conn = ConnectionDB.getConnection();
        PreparedStatement ps = conn.prepareStatement(DELETE);
        ps.setInt(1, idResponsavelBusca);

        int retorno = ps.executeUpdate();
        ConnectionDB.closeConnection(conn, ps);
        return retorno > 0;
    }

    public List<CadastroResponsavelBusca> findAll() throws SQLException {
        Connection conn = ConnectionDB.getConnection();
        PreparedStatement ps = conn.prepareStatement(SELECT_ALL);
        ResultSet rs = ps.executeQuery();

        List<CadastroResponsavelBusca> lista = new ArrayList<>();
        while (rs.next()) {
            lista.add(mapear(rs));
        }

        ConnectionDB.closeConnection(conn, ps, rs);
        return lista;
    }

    public CadastroResponsavelBusca findById(Integer idResponsavelBusca) throws SQLException {
        Connection conn = ConnectionDB.getConnection();
        PreparedStatement ps = conn.prepareStatement(SELECT_BY_ID);
        ps.setInt(1, idResponsavelBusca);
        ResultSet rs = ps.executeQuery();

        CadastroResponsavelBusca c = null;
        if (rs.next()) {
            c = mapear(rs);
        }

        ConnectionDB.closeConnection(conn, ps, rs);
        return c;
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
        aluno.setNomeAluno(rs.getString("nome_aluno"));

        GrauParentesco grau = new GrauParentesco();
        grau.setIdGrauParentesco(idGrauParentesco);
        grau.setDescricaoGrauParentesco(rs.getString("descricao_grau_parentesco"));

        ResponsavelBuscaAluno resp = new ResponsavelBuscaAluno();
        resp.setIdResponsavel(idResponsavel);
        resp.setNomeResponsavel(rs.getString("nome_responsavel"));

        AnoLectivo ano = new AnoLectivo();
        ano.setIdAnoLectivo(idAnoLectivo);
        ano.setDescricaoAnoLectivo(rs.getString("descricao_ano_lectivo"));

        return new CadastroResponsavelBusca(id, data, aluno, grau, resp, ano);
    }
}