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
import skylink.mglcreche.modelo.Periodo;
import skylink.mglcreche.modelo.Sala;

public class TurmaDAO {

    private static final String INSERT = "INSERT INTO turma (id_ano_lectivo, id_classe, codigo_turma, id_sala, id_periodo, numero_maximo, observacoes_turma, activa) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String UPDATE = "UPDATE turma SET descricao_turma = ?, id_ano_lectivo = ?, id_classe = ?, id_sala = ?, numero_maximo = ?, activa = ? WHERE id_turma = ?";
    private static final String DELETE = "DELETE FROM turma WHERE id_turma = ?";

    private static final String BUSCA_POR_ID = "SELECT t.id_turma, t.codigo_turma, al.descricao_ano_lectivo, c.descricao_classe, s.descricao_sala, p.descricao_periodo, t.numero_maximo, t.activa, t.data_registo, t.observacoes_turma  FROM turma t JOIN ano_lectivo al ON t.id_ano_lectivo = al.id_ano_lectivo JOIN classe c ON t.id_classe = c.id_classe JOIN sala s ON t.id_sala = s.id_sala INNER JOIN periodo p ON t.id_periodo = p.id_periodo  WHERE t.id_turma = ?";
    private static final String LISTAR_TODOS = "SELECT t.id_turma, t.codigo_turma, al.descricao_ano_lectivo, c.descricao_classe, s.descricao_sala, p.descricao_periodo, t.numero_maximo, t.activa, t.data_registo, t.observacoes_turma  FROM turma t JOIN ano_lectivo al ON t.id_ano_lectivo = al.id_ano_lectivo JOIN classe c ON t.id_classe = c.id_classe JOIN sala s ON t.id_sala = s.id_sala INNER JOIN periodo p ON t.id_periodo = p.id_periodo";
    private static final String BUSCA_POR_CODIGO = "SELECT t.id_turma, t.codigo_turma, al.descricao_ano_lectivo, c.descricao_classe, s.descricao_sala, p.descricao_periodo, t.numero_maximo, t.activa, t.data_registo, t.observacoes_turma  FROM turma t JOIN ano_lectivo al ON t.id_ano_lectivo = al.id_ano_lectivo JOIN classe c ON t.id_classe = c.id_classe JOIN sala s ON t.id_sala = s.id_sala INNER JOIN periodo p ON t.id_periodo = p.id_periodo  WHERE t.codigo_turma LIKE ? ORDER BY t.codigo_turma";
    private static final String LISTAR_POR_ANO_LECTVO = "SELECT t.id_turma, t.codigo_turma, al.descricao_ano_lectivo, c.descricao_classe, s.descricao_sala, p.descricao_periodo, t.numero_maximo, t.activa, t.data_registo, t.observacoes_turma  FROM turma t JOIN ano_lectivo al ON t.id_ano_lectivo = al.id_ano_lectivo JOIN classe c ON t.id_classe = c.id_classe JOIN sala s ON t.id_sala = s.id_sala INNER JOIN periodo p ON t.id_periodo = p.id_periodo WHERE al.id_ano_lectivo = ?";
    private static final String SELECT_MAX_ID_TURMA = "SELECT MAX(id_turma) FROM turma";
    
   
    public boolean save(Turma turma) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = ConnectionDB.getConnection();
            ps = conn.prepareStatement(INSERT);
            ps.setInt(1, turma.getAnoLectivo().getIdAnoLectivo());
            ps.setInt(2, turma.getClasse().getIdClasse());
            ps.setString(3, turma.getCodigoTurma());
            ps.setInt(4, turma.getSala().getIdSala());
            ps.setInt(5, turma.getPeriodo().getIdPeriodo());
            ps.setInt(6, turma.getNumeroMaximo());
            ps.setString(7, turma.getObservacoesTurma());
            ps.setBoolean(8, turma.getActiva());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("SEVERE: Erro ao inserir turma: " + e.getMessage());
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
            ps.setInt(1, turma.getAnoLectivo().getIdAnoLectivo());
            ps.setInt(2, turma.getClasse().getIdClasse());
            ps.setString(3, turma.getCodigoTurma());
            ps.setInt(4, turma.getSala().getIdSala());
            ps.setInt(5, turma.getPeriodo().getIdPeriodo());
            ps.setInt(6, turma.getNumeroMaximo());
            System.out.println("9999999999999>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+ turma.getNumeroMaximo());
            ps.setString(7, turma.getObservacoesTurma());
            ps.setBoolean(8, turma.getActiva());
            ps.setInt(9, turma.getIdTurma());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("SEVERE: Erro ao atualizar turma: " + e.getMessage());
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
            System.err.println("SEVERE: Erro ao eliminar turma: " + e.getMessage());
            return false;
        } finally {
            ConnectionDB.closeConnection(conn, ps);
        }
    }

    public List<Turma> findAll() {
        List<Turma> lista = new ArrayList<>();
        try (Connection conn = ConnectionDB.getConnection(); PreparedStatement ps = conn.prepareStatement(LISTAR_TODOS); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Turma t = new Turma();
                popularComDados(t, rs);
                lista.add(t);
            }
        } catch (SQLException ex) {
            System.err.println("Erro ao listar=>findAll() : " + ex.getMessage());
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
            ps = conn.prepareStatement(BUSCA_POR_CODIGO);
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

    public Integer buscaUltimaTurmaCriada() {
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;

        Integer ultimo = null;
        try {
            conn = ConnectionDB.getConnection();
            ps = conn.prepareStatement(SELECT_MAX_ID_TURMA);
            rs = ps.executeQuery();
            if (rs.next()) {
                ultimo = rs.getInt(1);
            }
            System.out.println("TurmaDAO: buscaUltimaTurmaCriada -> Maior propina" + ultimo);
        } catch (SQLException ex) {
            System.out.println("TurmaDAO: buscaUltimaTurmaCriada -> Erro ao carregar dados" + ex.getMessage());
        } finally {
            ConnectionDB.closeConnection((Connection) conn);
        }
        return ultimo;
    }

    public List<Turma> buscarPorAnoLectivo(Integer codigoAnoLectivo) {
        List<Turma> lista = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = ConnectionDB.getConnection();
            ps = conn.prepareStatement(LISTAR_POR_ANO_LECTVO);
            ps.setInt(1, codigoAnoLectivo);
            rs = ps.executeQuery();
            while (rs.next()) {
                Turma t = new Turma();
                popularComDados(t, rs);
                lista.add(t);
            }
        } catch (SQLException ex) {
            System.err.println("Erro em buscarPorAnoLectivo: " + ex.getMessage());
        } finally {
            ConnectionDB.closeConnection(conn, ps, rs);
        }
        return lista;
    }

    private void popularComDados(Turma turma, ResultSet rs) throws SQLException {
        turma.setIdTurma(rs.getInt("id_turma"));
        turma.setObservacoesTurma(rs.getString("observacoes_turma"));
        turma.setCodigoTurma(rs.getString("codigo_turma"));
        turma.setNumeroMaximo(rs.getInt("numero_maximo"));
        turma.setActiva(rs.getBoolean("activa"));
        turma.setDataRegisto(rs.getTimestamp("data_registo"));

        AnoLectivo ano = new AnoLectivo();
        ano.setDescricaoAnoLectivo(rs.getString("descricao_ano_lectivo"));
        turma.setAnoLectivo(ano);

        Classe classe = new Classe();
        classe.setDescricaoClasse(rs.getString("descricao_classe"));
        turma.setClasse(classe);

        Periodo periodo = new Periodo();
        periodo.setDescricaoPeriodo(rs.getString("descricao_periodo"));
        turma.setPeriodo(periodo);

        Sala sala = new Sala();
        sala.setDescricaoSala(rs.getString("descricao_sala"));
        turma.setSala(sala);
    }
}
