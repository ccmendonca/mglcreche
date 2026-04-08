package skylink.mglcreche.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import skylink.mglcreche.bdutil.ConnectionDB;
import skylink.mglcreche.modelo.Turma;
import skylink.mglcreche.modelo.AnoLectivo;
import skylink.mglcreche.modelo.Classe;

/**
 * @Henriques
 */
public class TurmaDAO {

    private static final String INSERT = "INSERT INTO turma ( id_turma, descricao_turma, id_ano_lectivo, id_classe, numero_maximo, activa, data_criacao) VALUES (?, ?, ?, ?, ?, ?, ?)";

    private static final String UPDATE = "UPDATE turma SET descricao_turma = ?, id_ano_lectivo = ?, id_classe = ?, numero_maximo = ?, activa = ? WHERE id_turma = ?";

    private static final String DELETE = "DELETE FROM turma WHERE id_turma = ?";

    private static final String BUSCA_POR_ID = "SELECT t.*, al.ano_lectivo, al.descricao as desc_ano, c.descricao_classe FROM turma t JOIN ano_lectivo al ON t.id_ano_lectivo = al.id_ano_lectivo JOIN classe c ON t.id_classe = c.id_classe WHERE t.id_turma = ?";

    private static final String LISTAR_TODOS = "SELECT t.*, al.ano_lectivo, al.descricao as desc_ano, c.descricao_classe FROM turma t JOIN ano_lectivo al ON t.id_ano_lectivo = al.id_ano_lectivo JOIN classe c ON t.id_classe = c.id_classe ORDER BY t.descricao_turma";

    private static final String BUSCA_POR_DESCRICAO = "SELECT t.*, al.ano_lectivo, al.descricao as desc_ano, c.descricao_classe FROM turma t JOIN ano_lectivo al ON t.id_ano_lectivo = al.id_ano_lectivo JOIN classe c ON t.id_classe = c.id_classe WHERE t.descricao_turma LIKE ?";

    private static final String LISTAR_ATIVAS = "SELECT t.*, al.ano_lectivo, al.descricao as desc_ano, c.descricao_classe FROM turma t JOIN ano_lectivo al ON t.id_ano_lectivo = al.id_ano_lectivo JOIN classe c ON t.id_classe = c.id_classe WHERE t.activa = true ORDER BY t.descricao_turma";

    private static final String LISTAR_POR_ANO_LECTIVO = "SELECT t.*, al.ano_lectivo, al.descricao as desc_ano, c.descricao_classe FROM turma t JOIN ano_lectivo al ON t.id_ano_lectivo = al.id_ano_lectivo JOIN classe c ON t.id_classe = c.id_classe WHERE t.id_ano_lectivo = ? AND t.activa = true ORDER BY t.descricao_turma";

    private static final String LISTAR_POR_CLASSE = "SELECT t.*, al.ano_lectivo, al.descricao as desc_ano, c.descricao_classe FROM turma t JOIN ano_lectivo al ON t.id_ano_lectivo = al.id_ano_lectivo JOIN classe c ON t.id_classe = c.id_classe WHERE t.id_classe = ? ORDER BY t.descricao_turma";

    public boolean save(Turma turma) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = ConnectionDB.getConnection();
            ps = conn.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);

            ps.setString(1, turma.getDescricaoTurma());

            if (turma.getAnoLectivo() != null) {
                ps.setInt(2, turma.getAnoLectivo().getIdAnoLectivo());
            } else {
                ps.setNull(2, java.sql.Types.INTEGER);
            }

            if (turma.getClasse() != null) {
                ps.setInt(3, turma.getClasse().getIdClasse());
            } else {
                ps.setNull(3, java.sql.Types.INTEGER);
            }

            ps.setInt(4, turma.getNumeroMaximo());

            if (turma.getActiva() != null) {
                ps.setBoolean(5, turma.getActiva());
            } else {
                ps.setBoolean(5, true);
            }

            if (turma.getDataCriacao() != null) {
                ps.setTimestamp(6, new java.sql.Timestamp(turma.getDataCriacao().getTime()));
            } else {
                ps.setTimestamp(6, new java.sql.Timestamp(System.currentTimeMillis()));
            }

            int affectedRows = ps.executeUpdate();

            if (affectedRows > 0) {
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    turma.setIdTurma(rs.getInt(1));
                }
                rs.close();
            }

            return affectedRows > 0;

        } catch (SQLException e) {
            System.err.println("Erro ao inserir turma: " + e.getMessage());
            return false;
        } finally {
            ConnectionDB.closeConnection(conn, ps);
        }
    }

    public boolean update(Turma turma) {
        if (turma == null || turma.getIdTurma() == null) {
            return false;
        }

        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = ConnectionDB.getConnection();
            ps = conn.prepareStatement(UPDATE);

            ps.setString(1, turma.getDescricaoTurma());

            if (turma.getAnoLectivo() != null) {
                ps.setInt(2, turma.getAnoLectivo().getIdAnoLectivo());
            } else {
                ps.setNull(2, java.sql.Types.INTEGER);
            }

            if (turma.getClasse() != null) {
                ps.setInt(3, turma.getClasse().getIdClasse());
            } else {
                ps.setNull(3, java.sql.Types.INTEGER);
            }

            ps.setInt(4, turma.getNumeroMaximo());

            if (turma.getActiva() != null) {
                ps.setBoolean(5, turma.getActiva());
            } else {
                ps.setBoolean(5, true);
            }

            ps.setInt(6, turma.getIdTurma());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Erro ao atualizar turma: " + e.getMessage());
            return false;
        } finally {
            ConnectionDB.closeConnection(conn, ps);
        }
    }

    public boolean delete(Turma turma) {
        if (turma == null || turma.getIdTurma() == null) {
            return false;
        }

        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = ConnectionDB.getConnection();
            ps = conn.prepareStatement(DELETE);
            ps.setInt(1, turma.getIdTurma());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Erro ao eliminar turma: " + e.getMessage());
            return false;
        } finally {
            ConnectionDB.closeConnection(conn, ps);
        }
    }

    public Turma buscarPorId(Integer idTurma) {
        if (idTurma == null) {
            return null;
        }

        try (Connection conn = ConnectionDB.getConnection(); PreparedStatement ps = conn.prepareStatement(BUSCA_POR_ID)) {

            ps.setInt(1, idTurma);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Turma t = new Turma();
                popularComDados(t, rs);
                return t;
            }

        } catch (SQLException ex) {
            System.err.println("Erro ao buscar turma por ID: " + ex.getMessage());
            Logger.getLogger(TurmaDAO.class.getName()).log(Level.SEVERE, "Erro de busca", ex);
        }

        return null;
    }

    public List<Turma> listar() {
        List<Turma> lista = new ArrayList<>();

        try (Connection conn = ConnectionDB.getConnection(); PreparedStatement ps = conn.prepareStatement(LISTAR_TODOS)) {

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Turma t = new Turma();
                popularComDados(t, rs);
                lista.add(t);
            }

        } catch (SQLException ex) {
            System.err.println("Erro ao listar turmas: " + ex.getMessage());
            Logger.getLogger(TurmaDAO.class.getName()).log(Level.SEVERE, "Erro de listagem", ex);
        }

        return lista;
    }

    public List<Turma> listarAtivas() {
        List<Turma> lista = new ArrayList<>();

        try (Connection conn = ConnectionDB.getConnection(); PreparedStatement ps = conn.prepareStatement(LISTAR_ATIVAS)) {

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Turma t = new Turma();
                popularComDados(t, rs);
                lista.add(t);
            }

        } catch (SQLException ex) {
            System.err.println("Erro ao listar turmas ativas: " + ex.getMessage());
            Logger.getLogger(TurmaDAO.class.getName()).log(Level.SEVERE, "Erro de listagem", ex);
        }

        return lista;
    }

    public List<Turma> buscarPorDescricao(String descricao) {
        List<Turma> lista = new ArrayList<>();

        try (Connection conn = ConnectionDB.getConnection(); PreparedStatement ps = conn.prepareStatement(BUSCA_POR_DESCRICAO)) {

            ps.setString(1, "%" + descricao + "%");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Turma t = new Turma();
                popularComDados(t, rs);
                lista.add(t);
            }

        } catch (SQLException ex) {
            System.err.println("Erro ao buscar turmas por descrição: " + ex.getMessage());
            Logger.getLogger(TurmaDAO.class.getName()).log(Level.SEVERE, "Erro de busca", ex);
        }

        return lista;
    }

    public List<Turma> listarPorAnoLectivo(Integer idAnoLectivo) {
        List<Turma> lista = new ArrayList<>();

        try (Connection conn = ConnectionDB.getConnection(); PreparedStatement ps = conn.prepareStatement(LISTAR_POR_ANO_LECTIVO)) {

            ps.setInt(1, idAnoLectivo);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Turma t = new Turma();
                popularComDados(t, rs);
                lista.add(t);
            }

        } catch (SQLException ex) {
            System.err.println("Erro ao listar turmas por ano lectivo: " + ex.getMessage());
            Logger.getLogger(TurmaDAO.class.getName()).log(Level.SEVERE, "Erro de listagem", ex);
        }

        return lista;
    }

    public List<Turma> listarPorClasse(Integer idClasse) {
        List<Turma> lista = new ArrayList<>();

        try (Connection conn = ConnectionDB.getConnection(); PreparedStatement ps = conn.prepareStatement(LISTAR_POR_CLASSE)) {

            ps.setInt(1, idClasse);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Turma t = new Turma();
                popularComDados(t, rs);
                lista.add(t);
            }

        } catch (SQLException ex) {
            System.err.println("Erro ao listar turmas por classe: " + ex.getMessage());
            Logger.getLogger(TurmaDAO.class.getName()).log(Level.SEVERE, "Erro de listagem", ex);
        }

        return lista;
    }

    private void popularComDados(Turma t, ResultSet rs) {

    }

}
