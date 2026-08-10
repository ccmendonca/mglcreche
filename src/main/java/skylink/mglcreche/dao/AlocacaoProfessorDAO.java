package skylink.mglcreche.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import skylink.mglcreche.bdutil.ConnectionDB;
import skylink.mglcreche.modelo.AlocacaoProfessor;
import skylink.mglcreche.modelo.AnoLectivo;
import skylink.mglcreche.modelo.Disciplina;
import skylink.mglcreche.modelo.Trimestre;
import skylink.mglcreche.modelo.Turma;

/**
 * @author Henriques
 */
public class AlocacaoProfessorDAO implements Serializable {

    private static final long serialVersionUID = 1L;

    private static final String INSERT = "INSERT INTO alocacao_professor (carga_horaria_semanal, id_profissional, id_disciplina, id_turma, id_ano_lectivo, id_trimestre, data_registo) VALUES (?, ?, ?, ?, ?, ?, NOW())";
    private static final String UPDATE = "UPDATE alocacao_professor SET carga_horaria_semanal = ?, id_profissional = ?, id_disciplina = ?, id_turma = ?, id_ano_lectivo = ?, id_trimestre = ? WHERE id_alocacao_professor = ?";
    private static final String DELETE = "DELETE FROM alocacao_professor WHERE id_alocacao_professor = ?";

    private static final String SELECT_BASE = "SELECT a.id_alocacao_professor, a.carga_horaria_semanal, a.id_profissional, a.data_registo, a.id_disciplina, d.descricao_disciplina, a.id_turma, t.codigo_turma, a.id_ano_lectivo, an.descricao_ano_lectivo, a.id_trimestre, tr.descricao_trimestre FROM alocacao_professor a LEFT JOIN disciplina d ON a.id_disciplina = d.id_disciplina LEFT JOIN turma t ON a.id_turma = t.id_turma LEFT JOIN ano_lectivo an ON a.id_ano_lectivo = an.id_ano_lectivo LEFT JOIN trimestre tr ON a.id_trimestre = tr.id_trimestre ";

    private static final String SELECT_ALL = SELECT_BASE + " ORDER BY a.id_alocacao_professor DESC";
    private static final String SELECT_BY_ID = SELECT_BASE + " WHERE a.id_alocacao_professor = ?";
    private static final String SELECT_BY_PROFESSOR = SELECT_BASE + " WHERE a.id_profissional = ?";
    private static final String SELECT_BY_TURMA = SELECT_BASE + " WHERE a.id_turma = ?";

    public void save(AlocacaoProfessor alocacao) throws SQLException {
        try (Connection conn = ConnectionDB.getConnection(); PreparedStatement ps = conn.prepareStatement(INSERT)) {

            ps.setInt(1, alocacao.getCargaHorariaSemanal());
            ps.setInt(2, alocacao.getIdProfissional());

            ps.setObject(3, alocacao.getDisciplina() != null ? alocacao.getDisciplina().getIdDisciplina() : null);
            ps.setObject(4, alocacao.getTurma() != null ? alocacao.getTurma().getIdTurma() : null);
            ps.setObject(5, alocacao.getAnoLectivo() != null ? alocacao.getAnoLectivo().getIdAnoLectivo() : null);
            ps.setObject(6, alocacao.getTrimestre() != null ? alocacao.getTrimestre().getIdTrimestre() : null);

            ps.executeUpdate();
        }
    }

    public void update(AlocacaoProfessor alocacao) throws SQLException {
        try (Connection conn = ConnectionDB.getConnection(); PreparedStatement ps = conn.prepareStatement(UPDATE)) {

            ps.setInt(1, alocacao.getCargaHorariaSemanal());
            ps.setInt(2, alocacao.getIdProfissional());

            ps.setObject(3, alocacao.getDisciplina() != null ? alocacao.getDisciplina().getIdDisciplina() : null);
            ps.setObject(4, alocacao.getTurma() != null ? alocacao.getTurma().getIdTurma() : null);
            ps.setObject(5, alocacao.getAnoLectivo() != null ? alocacao.getAnoLectivo().getIdAnoLectivo() : null);
            ps.setObject(6, alocacao.getTrimestre() != null ? alocacao.getTrimestre().getIdTrimestre() : null);
            ps.setInt(7, alocacao.getIdAlocacaoProfessor());

            ps.executeUpdate();
        }
    }

    public void delete(Integer id) throws SQLException {
        try (Connection conn = ConnectionDB.getConnection(); PreparedStatement ps = conn.prepareStatement(DELETE)) {

            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    public List<AlocacaoProfessor> findAll() throws SQLException {
        List<AlocacaoProfessor> lista = new ArrayList<>();

        try (Connection conn = ConnectionDB.getConnection(); PreparedStatement ps = conn.prepareStatement(SELECT_ALL); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lista.add(mapearResultSet(rs));
            }
        }
        return lista;
    }

    public AlocacaoProfessor findById(Integer id) throws SQLException {
        try (Connection conn = ConnectionDB.getConnection(); PreparedStatement ps = conn.prepareStatement(SELECT_BY_ID)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapearResultSet(rs);
                }
            }
        }
        return null;
    }

    public List<AlocacaoProfessor> findByProfessor(Integer idProfissional) throws SQLException {
        List<AlocacaoProfessor> lista = new ArrayList<>();
        try (Connection conn = ConnectionDB.getConnection(); PreparedStatement ps = conn.prepareStatement(SELECT_BY_PROFESSOR)) {

            ps.setInt(1, idProfissional);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    lista.add(mapearResultSet(rs));
                }
            }
        }
        return lista;
    }

    public List<AlocacaoProfessor> findByTurma(Integer idTurma) throws SQLException {
        List<AlocacaoProfessor> lista = new ArrayList<>();
        try (Connection conn = ConnectionDB.getConnection(); PreparedStatement ps = conn.prepareStatement(SELECT_BY_TURMA)) {

            ps.setInt(1, idTurma);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    lista.add(mapearResultSet(rs));
                }
            }
        }
        return lista;
    }

    private AlocacaoProfessor mapearResultSet(ResultSet rs) throws SQLException {
        AlocacaoProfessor a = new AlocacaoProfessor();
        a.setIdAlocacaoProfessor(rs.getInt("id_alocacao_professor"));
        a.setCargaHorariaSemanal(rs.getInt("carga_horaria_semanal"));
        a.setIdProfissional(rs.getInt("id_profissional"));

        if (rs.getTimestamp("data_registo") != null) {
            a.setDataRegisto(rs.getTimestamp("data_registo").toLocalDateTime());
        }

        Disciplina d = new Disciplina();
        d.setIdDisciplina(rs.getInt("id_disciplina"));
        d.setDescricaoDisciplina(rs.getString("descricao_disciplina"));
        a.setDisciplina(d);

        Turma t = new Turma();
        t.setIdTurma(rs.getInt("id_turma"));
        t.setCodigoTurma(rs.getString("codigo_turma"));
        a.setTurma(t);

        AnoLectivo ano = new AnoLectivo();
        ano.setIdAnoLectivo(rs.getInt("id_ano_lectivo"));
        ano.setDescricaoAnoLectivo(rs.getString("descricao_ano_lectivo"));
        a.setAnoLectivo(ano);

        Trimestre trim = new Trimestre();
        trim.setIdTrimestre(rs.getInt("id_trimestre"));
        trim.setDescricaoTrimestre(rs.getString("descricao_trimestre"));
        a.setTrimestre(trim);

        return a;
    }
}
