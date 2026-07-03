package skylink.mglcreche.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import skylink.mglcreche.bdutil.ConnectionDB;
import skylink.mglcreche.modelo.Turma;
import skylink.mglcreche.modelo.AnoLectivo;
import skylink.mglcreche.modelo.Classe;
import skylink.mglcreche.modelo.Sala;

public class TurmaDAO {

    private static final String INSERT = "INSERT INTO turma (id_ano_lectivo, id_classe, codigo_turma, id_sala, id_periodo, numero_maximo, observacoes_turma, activa) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String UPDATE = "UPDATE turma SET descricao_turma = ?, id_ano_lectivo = ?, id_classe = ?, id_sala = ?, numero_maximo = ?, activa = ? WHERE id_turma = ?";
    private static final String DELETE = "DELETE FROM turma WHERE id_turma = ?";
    
    private static final String BUSCA_POR_ID = "SELECT t.*, al.descricao_ano_lectivo, c.descricao_classe, s.descricao_sala FROM turma t JOIN ano_lectivo al ON t.id_ano_lectivo = al.id_ano_lectivo JOIN classe c ON t.id_classe = c.id_classe JOIN sala s ON t.id_sala = s.id_sala WHERE t.id_turma = ?";
    private static final String LISTAR_TODOS = "SELECT t.*, al.descricao_ano_lectivo, c.descricao_classe, s.descricao_sala FROM turma t JOIN ano_lectivo al ON t.id_ano_lectivo = al.id_ano_lectivo JOIN classe c ON t.id_classe = c.id_classe JOIN sala s ON t.id_sala = s.id_sala ORDER BY t.descricao_turma";
    private static final String BUSCA_POR_DESCRICAO = "SELECT t.*, al.descricao_ano_lectivo, c.descricao_classe, s.descricao_sala FROM turma t JOIN ano_lectivo al ON t.id_ano_lectivo = al.id_ano_lectivo JOIN classe c ON t.id_classe = c.id_classe JOIN sala s ON t.id_sala = s.id_sala WHERE t.descricao_turma LIKE ? ORDER BY t.descricao_turma";

    public boolean save(Turma turma) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = ConnectionDB.getConnection();
            ps = conn.prepareStatement(INSERT);

            ps.setString(1, turma.getDescricaoTurma());
            ps.setInt(2, (turma.getAnoLectivo() != null) ? turma.getAnoLectivo().getIdAnoLectivo() : 0);
            ps.setInt(3, (turma.getClasse() != null) ? turma.getClasse().getIdClasse() : 0);
            ps.setInt(4, (turma.getSala() != null) ? turma.getSala().getIdSala() : 0); 
            ps.setInt(5, turma.getNumeroMaximo());
            ps.setBoolean(6, (turma.getActiva() != null) ? turma.getActiva() : true);
            
        

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("SEVERE: Erro ao inserir turma: " + e.getMessage());
            return false;
        } finally {
            ConnectionDB.closeConnection(conn, ps);
        }
    }

    public boolean update(Turma turma) {
        if (turma == null || turma.getIdTurma() == null) return false;
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = ConnectionDB.getConnection();
            ps = conn.prepareStatement(UPDATE);

            ps.setString(1, turma.getDescricaoTurma());
            ps.setInt(2, turma.getAnoLectivo().getIdAnoLectivo());
            ps.setInt(3, turma.getClasse().getIdClasse());
            ps.setInt(4, turma.getSala().getIdSala());
            ps.setInt(5, turma.getNumeroMaximo());
            ps.setBoolean(6, turma.getActiva());
            ps.setInt(7, turma.getIdTurma());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("SEVERE: Erro ao atualizar turma: " + e.getMessage());
            return false;
        } finally {
            ConnectionDB.closeConnection(conn, ps);
        }
    }

    public boolean delete(Turma turma) {
        if (turma == null || turma.getIdTurma() == null) return false;
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = ConnectionDB.getConnection();
            ps = conn.prepareStatement(DELETE);
            ps.setInt(1, turma.getIdTurma());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("SEVERE: Erro ao eliminar turma: " + e.getMessage());
            return false;
        } finally {
            ConnectionDB.closeConnection(conn, ps);
        }
    }

    public List<Turma> findAll() {
        List<Turma> lista = new ArrayList<>();
        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(LISTAR_TODOS);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Turma t = new Turma();
                popularComDados(t, rs);
                lista.add(t);
            }
        } catch (SQLException ex) {
            System.err.println("Erro ao listar: " + ex.getMessage());
        }
        return lista;
    }

    public List<Turma> buscarPorDescricao(String descricao) {
        List<Turma> lista = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = ConnectionDB.getConnection();
            ps = conn.prepareStatement(BUSCA_POR_DESCRICAO);
            ps.setString(1, "%" + descricao + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                Turma t = new Turma();
                popularComDados(t, rs);
                lista.add(t);
            }
        } catch (SQLException ex) {
            System.err.println("Erro ao buscar por descrição: " + ex.getMessage());
        } finally {
            ConnectionDB.closeConnection(conn, ps, rs);
        }
        return lista;
    }

    private void popularComDados(Turma turma, ResultSet rs) throws SQLException {
        turma.setIdTurma(rs.getInt("id_turma"));
        turma.setDescricaoTurma(rs.getString("descricao_turma"));
        turma.setNumeroMaximo(rs.getInt("numero_maximo"));
        turma.setActiva(rs.getBoolean("activa"));
        turma.setDataRegisto(rs.getTimestamp("data_registo"));

        AnoLectivo ano = new AnoLectivo();
        ano.setIdAnoLectivo(rs.getInt("id_ano_lectivo"));
        ano.setDescricaoAnoLectivo(rs.getString("descricao_ano_lectivo"));
        turma.setAnoLectivo(ano);

        Classe classe = new Classe();
        classe.setIdClasse(rs.getInt("id_classe"));
        classe.setDescricaoClasse(rs.getString("descricao_classe"));
        turma.setClasse(classe);

        Sala sala = new Sala();
        sala.setIdSala(rs.getInt("id_sala"));
        sala.setDescricaoSala(rs.getString("descricao_sala"));
        turma.setSala(sala);
    }
}