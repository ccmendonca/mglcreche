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

public class TurmaDAO {

    private static final String INSERT = "INSERT INTO turma (descricao_turma, id_ano_lectivo, id_classe, numero_maximo, activa, data_criacao) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String UPDATE = "UPDATE turma SET descricao_turma = ?, id_ano_lectivo = ?, id_classe = ?, numero_maximo = ?, activa = ? WHERE id_turma = ?";
    private static final String DELETE = "DELETE FROM turma WHERE id_turma = ?";
    private static final String BUSCA_POR_ID = "SELECT t.id_turma, t.descricao_turma, t.id_ano_lectivo, t.id_classe, t.numero_maximo, t.activa, t.data_criacao, al.descricao_ano_lectivo, c.descricao_classe FROM turma t JOIN ano_lectivo al ON t.id_ano_lectivo = al.id_ano_lectivo JOIN classe c ON t.id_classe = c.id_classe WHERE t.id_turma = ?";
    private static final String LISTAR_TODOS = "SELECT t.id_turma, t.descricao_turma, t.id_ano_lectivo, t.id_classe, t.numero_maximo, t.activa, t.data_criacao, al.descricao_ano_lectivo, c.descricao_classe FROM turma t JOIN ano_lectivo al ON t.id_ano_lectivo = al.id_ano_lectivo JOIN classe c ON t.id_classe = c.id_classe ORDER BY t.descricao_turma";
    private static final String BUSCA_POR_DESCRICAO = "SELECT t.id_turma, t.descricao_turma, t.id_ano_lectivo, t.id_classe, t.numero_maximo, t.activa, t.data_criacao, al.descricao_ano_lectivo, c.descricao_classe FROM turma t JOIN ano_lectivo al ON t.id_ano_lectivo = al.id_ano_lectivo JOIN classe c ON t.id_classe = c.id_classe WHERE t.descricao_turma LIKE ?";
    private static final String LISTAR_ATIVAS = "SELECT t.id_turma, t.descricao_turma, t.id_ano_lectivo, t.id_classe, t.numero_maximo, t.activa, t.data_criacao, al.descricao_ano_lectivo, c.descricao_classe FROM turma t JOIN ano_lectivo al ON t.id_ano_lectivo = al.id_ano_lectivo JOIN classe c ON t.id_classe = c.id_classe WHERE t.activa = true ORDER BY t.descricao_turma";
    private static final String LISTAR_POR_ANO_LECTIVO = "SELECT t.id_turma, t.descricao_turma, t.id_ano_lectivo, t.id_classe, t.numero_maximo, t.activa, t.data_criacao, al.descricao_ano_lectivo, c.descricao_classe FROM turma t JOIN ano_lectivo al ON t.id_ano_lectivo = al.id_ano_lectivo JOIN classe c ON t.id_classe = c.id_classe WHERE t.id_ano_lectivo = ? AND t.activa = true ORDER BY t.descricao_turma";
    private static final String LISTAR_POR_CLASSE = "SELECT t.id_turma, t.descricao_turma, t.id_ano_lectivo, t.id_classe, t.numero_maximo, t.activa, t.data_criacao, al.descricao_ano_lectivo, c.descricao_classe FROM turma t JOIN ano_lectivo al ON t.id_ano_lectivo = al.id_ano_lectivo JOIN classe c ON t.id_classe = c.id_classe WHERE t.id_classe = ? ORDER BY t.descricao_turma";

    public boolean save(Turma turma) {
        PreparedStatement ps = null;
        Connection conn = null;
        boolean flagControlo = false;
        try {
            conn = ConnectionDB.getConnection();
            ps = conn.prepareStatement(INSERT);

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

            int retorno = ps.executeUpdate();
            if (retorno > 0) {
                System.out.println("Dados inseridos com sucesso: " + ps.getUpdateCount());
                flagControlo = true;
            }
            return flagControlo;

        } catch (SQLException e) {
            System.out.println("Erro ao inserir dados: " + e.getMessage());
            return false;
        } finally {
            ConnectionDB.closeConnection(conn, ps);
        }
    }

    public boolean update(Turma turma) {
        if (turma == null || turma.getIdTurma() == null) {
            return false;
        }
        PreparedStatement ps = null;
        Connection conn = null;
        boolean flagControlo = false;
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

            int retorno = ps.executeUpdate();
            if (retorno > 0) {
                System.out.println("Dados actualizados com sucesso: " + ps.getUpdateCount());
                flagControlo = true;
            }
            return flagControlo;

        } catch (SQLException e) {
            System.out.println("Erro ao actualizar dados: " + e.getMessage());
            return false;
        } finally {
            ConnectionDB.closeConnection(conn, ps);
        }
    }

    public boolean delete(Turma turma) {
        Connection conn = null;
        PreparedStatement ps = null;
        boolean flagControlo = false;
        if (turma == null || turma.getIdTurma() == null) {
            System.err.println("O campo anterior nao pode ser nulo");
            return false;
        }
        try {
            conn = ConnectionDB.getConnection();
            ps = conn.prepareStatement(DELETE);
            ps.setInt(1, turma.getIdTurma());

            int retorno = ps.executeUpdate();
            if (retorno > 0) {
                System.out.println("Dados eliminados com sucesso: " + ps.getUpdateCount());
                flagControlo = true;
            }
            return flagControlo;

        } catch (SQLException e) {
            System.out.println("Erro ao eliminar dados: " + e.getMessage());
            return false;
        } finally {
            ConnectionDB.closeConnection(conn, ps);
        }
    }

    public Turma buscarPorId(Integer id) {
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        Turma turma = new Turma();

        try {
            conn = ConnectionDB.getConnection();
            ps = conn.prepareStatement(BUSCA_POR_ID);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (!rs.next()) {
                System.err.println("Não foi encontrado nenhum registo com o id: " + id);
            } else {
                popularComDados(turma, rs);
            }
        } catch (SQLException ex) {
            System.err.println("Erro ao ler dados: " + ex.getLocalizedMessage());
        } finally {
            ConnectionDB.closeConnection(conn, ps, rs);
        }

        return turma;
    }

    public List<Turma> listar() {
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        List<Turma> lista = new ArrayList<>();
        try {
            conn = ConnectionDB.getConnection();
            ps = conn.prepareStatement(LISTAR_TODOS);
            rs = ps.executeQuery();
            while (rs.next()) {
                Turma turma = new Turma();
                popularComDados(turma, rs);
                lista.add(turma);
            }
        } catch (SQLException ex) {
            System.err.println("Erro ao ler dados: " + ex.getLocalizedMessage());
        } finally {
            ConnectionDB.closeConnection(conn, ps, rs);
        }
        return lista;
    }

    public List<Turma> listarAtivas() {
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        List<Turma> lista = new ArrayList<>();
        try {
            conn = ConnectionDB.getConnection();
            ps = conn.prepareStatement(LISTAR_ATIVAS);
            rs = ps.executeQuery();
            while (rs.next()) {
                Turma turma = new Turma();
                popularComDados(turma, rs);
                lista.add(turma);
            }
        } catch (SQLException ex) {
            System.err.println("Erro ao ler dados: " + ex.getLocalizedMessage());
        } finally {
            ConnectionDB.closeConnection(conn, ps, rs);
        }
        return lista;
    }

    public List<Turma> buscarPorDescricao(String descricao) {
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        List<Turma> lista = new ArrayList<>();
        try {
            conn = ConnectionDB.getConnection();
            ps = conn.prepareStatement(BUSCA_POR_DESCRICAO);
            ps.setString(1, "%" + descricao + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                Turma turma = new Turma();
                popularComDados(turma, rs);
                lista.add(turma);
            }
        } catch (SQLException ex) {
            System.err.println("Erro ao ler dados: " + ex.getLocalizedMessage());
        } finally {
            ConnectionDB.closeConnection(conn, ps, rs);
        }
        return lista;
    }

    public List<Turma> listarPorAnoLectivo(Integer idAnoLectivo) {
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        List<Turma> lista = new ArrayList<>();
        try {
            conn = ConnectionDB.getConnection();
            ps = conn.prepareStatement(LISTAR_POR_ANO_LECTIVO);
            ps.setInt(1, idAnoLectivo);
            rs = ps.executeQuery();
            while (rs.next()) {
                Turma turma = new Turma();
                popularComDados(turma, rs);
                lista.add(turma);
            }
        } catch (SQLException ex) {
            System.err.println("Erro ao ler dados: " + ex.getLocalizedMessage());
        } finally {
            ConnectionDB.closeConnection(conn, ps, rs);
        }
        return lista;
    }

    public List<Turma> listarPorClasse(Integer idClasse) {
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        List<Turma> lista = new ArrayList<>();
        try {
            conn = ConnectionDB.getConnection();
            ps = conn.prepareStatement(LISTAR_POR_CLASSE);
            ps.setInt(1, idClasse);
            rs = ps.executeQuery();
            while (rs.next()) {
                Turma turma = new Turma();
                popularComDados(turma, rs);
                lista.add(turma);
            }
        } catch (SQLException ex) {
            System.err.println("Erro ao ler dados: " + ex.getLocalizedMessage());
        } finally {
            ConnectionDB.closeConnection(conn, ps, rs);
        }
        return lista;
    }

    private void popularComDados(Turma turma, ResultSet rs) {
        try {
            turma.setIdTurma(rs.getInt("id_turma"));
            turma.setDescricaoTurma(rs.getString("descricao_turma"));
            turma.setNumeroMaximo(rs.getInt("numero_maximo"));
            turma.setActiva(rs.getBoolean("activa"));
            turma.setDataCriacao(rs.getTimestamp("data_criacao"));

            AnoLectivo anoLectivo = new AnoLectivo();
            anoLectivo.setIdAnoLectivo(rs.getInt("id_ano_lectivo"));
            anoLectivo.setDescricaoAnoLectivo(rs.getString("descricao_ano_lectivo"));
            turma.setAnoLectivo(anoLectivo);

            Classe classe = new Classe();
            classe.setIdClasse(rs.getInt("id_classe"));
            classe.setDescricaoClasse(rs.getString("descricao_classe"));
            turma.setClasse(classe);

        } catch (SQLException ex) {
            System.err.println("Erro ao carregar dados: " + ex.getLocalizedMessage());
        }
    }
}
