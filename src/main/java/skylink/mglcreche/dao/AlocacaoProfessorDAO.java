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
import skylink.mglcreche.modelo.Turma;
import skylink.mglcreche.modelo.AnoLectivo;
import skylink.mglcreche.modelo.Trimestre;

/**
 *
 * @Henriques
 */
public class AlocacaoProfessorDAO implements Serializable {

    private static final long serialVersionUID = 1L;

    private static final String INSERT = "INSERT INTO alocacao_professor (carga_horaria_semanal, id_profissional, id_disciplina, id_turma, id_ano_lectivo, id_trimestre) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String UPDATE = "UPDATE alocacao_professor SET carga_horaria_semanal = ?, id_profissional = ?, id_disciplina = ?, id_turma = ?, id_ano_lectivo = ?, id_trimestre = ? WHERE id_alocacao_professor = ?";
    private static final String DELETE = "DELETE FROM alocacao_professor WHERE id_alocacao_professor = ?";
    private static final String SELECT_ALL = "SELECT * FROM alocacao_professor";
    private static final String SELECT_BY_ID = "SELECT * FROM alocacao_professor WHERE id_alocacao_professor = ?";
    
    private static final String SELECT_BY_PROFESSOR = "SELECT * FROM alocacao_professor WHERE id_profissional = ?";
    private static final String SELECT_BY_TURMA = "SELECT * FROM alocacao_professor WHERE id_turma = ?";

    public void save(AlocacaoProfessor alocacao) throws SQLException {
        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(INSERT)) {
            
            ps.setInt(1, alocacao.getCargaHorariaSemanal());
            ps.setInt(2, alocacao.getIdProfissional());
            
           // ps.setObject(3, alocacao.getDisciplina() != null ? alocacao.getDisciplina().getIdDisciplina() : null);
            ps.setObject(4, alocacao.getTurma() != null ? alocacao.getTurma().getIdTurma() : null);
            ps.setObject(5, alocacao.getAnoLectivo() != null ? alocacao.getAnoLectivo().getIdAnoLectivo() : null);
            ps.setObject(6, alocacao.getTrimestre() != null ? alocacao.getTrimestre().getIdTrimestre() : null);
            
            ps.executeUpdate();
        }
    }

    public void update(AlocacaoProfessor alocacao) throws SQLException {
        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(UPDATE)) {
            
            ps.setInt(1, alocacao.getCargaHorariaSemanal());
            ps.setInt(2, alocacao.getIdProfissional());
            
           // ps.setObject(3, alocacao.getDisciplina() != null ? alocacao.getDisciplina().getIdDisciplina() : null);
            ps.setObject(4, alocacao.getTurma() != null ? alocacao.getTurma().getIdTurma() : null);
            ps.setObject(5, alocacao.getAnoLectivo() != null ? alocacao.getAnoLectivo().getIdAnoLectivo() : null);
            ps.setObject(6, alocacao.getTrimestre() != null ? alocacao.getTrimestre().getIdTrimestre() : null);
            ps.setInt(7, alocacao.getIdAlocacaoProfessor());
            
            ps.executeUpdate();
        }
    }

    public void delete(Integer id) throws SQLException {
        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(DELETE)) {
            
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    public List<AlocacaoProfessor> findAll() throws SQLException {
        List<AlocacaoProfessor> lista = new ArrayList<>();
        
        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(SELECT_ALL);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lista.add(mapearResultSet(rs));
            }
        }
        return lista;
    }

    public AlocacaoProfessor findById(Integer id) throws SQLException {
        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(SELECT_BY_ID)) {

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
        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(SELECT_BY_PROFESSOR)) {
            
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
        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(SELECT_BY_TURMA)) {
            
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
        
       // Disciplina d = new Disciplina();
      //  d.setIdDisciplina(rs.getInt("id_disciplina"));
       // a.setDisciplina(d);
        
        Turma t = new Turma();
        t.setIdTurma(rs.getInt("id_turma"));
        a.setTurma(t);
        
        AnoLectivo ano = new AnoLectivo();
        ano.setIdAnoLectivo(rs.getInt("id_ano_lectivo"));
        a.setAnoLectivo(ano);
        
        Trimestre trim = new Trimestre();
        trim.setIdTrimestre(rs.getInt("id_trimestre"));
        a.setTrimestre(trim);
        
        return a;
    }

    public List<AlocacaoProfessor> findByParameter(String dado) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}