package skylink.mglcreche.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import skylink.mglcreche.bdutil.ConnectionDB;
import skylink.mglcreche.modelo.TipoAvaliacao;

public class TipoAvaliacaoDAO {

    private static final String INSERT = "INSERT INTO tipo_avaliacao(descricao_tipo_avaliacao, sigla_avaliacao,peso_tipo_avaliacao) VALUES (?,?, ?)";
    private static final String UPDATE = "UPDATE tipo_avaliacao SET descricao_tipo_avaliacao = ?, sigla_avaliacao = ?, peso_tipo_avaliacao = ? WHERE id_tipo_avaliacao = ?";
    private static final String DELETE = "DELETE FROM tipo_avaliacao WHERE id_tipo_avaliacao= ?";
    private static final String BUSCAR_POR_CODIGO = "SELECT id_tipo_avaliacao, descricao_tipo_avaliacao,sigla_tipo_avaliacao,peso_tipo_avaliacao FROM tipo_avaliacao WHERE id_tipo_avaliacao =?";
    private static final String LISTAR_TUDO = "SELECT id_tipo_avaliacao, descricao_tipo_avaliacao,sigla_tipo_avaliacao,peso_tipo_avaliacao FROM tipo_avaliacao";

    public boolean save(TipoAvaliacao tipoAvaliacao) {
        PreparedStatement ps = null;
        Connection conn = null;
        boolean flagControlo = false;
        try {
            conn = ConnectionDB.getConnection();
            ps = conn.prepareStatement(INSERT);
            ps.setString(1, tipoAvaliacao.getDescricaoTipoAvaliacao());
            ps.setString(2, tipoAvaliacao.getSiglaAvaliacao());
            ps.setDouble(3, tipoAvaliacao.getPesoTipoAvaliacao());

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

    public boolean update(TipoAvaliacao tipoAvaliacao) {
        PreparedStatement ps = null;
        Connection conn = null;
        boolean flagControlo = false;
        try {
            conn = ConnectionDB.getConnection();
            ps = conn.prepareStatement(UPDATE);
          ps.setString(1, tipoAvaliacao.getDescricaoTipoAvaliacao());
            ps.setString(2, tipoAvaliacao.getSiglaAvaliacao());
            ps.setDouble(3, tipoAvaliacao.getPesoTipoAvaliacao());

            ps.setInt(4, tipoAvaliacao.getIdTipoAvaliacao());
            int retorno = ps.executeUpdate();
            if (retorno > 0) {
                System.out.println("Dados actualizados com sucesso: " + ps.getUpdateCount());
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

    public boolean delete(TipoAvaliacao tipoAvaliacao) {
        Connection conn = null;
        PreparedStatement ps = null;
        boolean flagControlo = false;
        if (tipoAvaliacao == null) {
            System.err.println("O campo anterior nao pode ser nulo");
        }

        try {
            conn = ConnectionDB.getConnection();
            ps = conn.prepareStatement(DELETE);
            ps.setInt(1, tipoAvaliacao.getIdTipoAvaliacao());
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

    public TipoAvaliacao findById(Integer id) {
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        TipoAvaliacao tipoAvaliacao = new TipoAvaliacao();

        try {
            conn = ConnectionDB.getConnection();
            ps = conn.prepareStatement(BUSCAR_POR_CODIGO);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (!rs.next()) {
                System.err.println("Não foi encontrado nenhum registo com o id: " + id);
            }
            popularComDados(tipoAvaliacao, rs);
        } catch (SQLException ex) {
            System.err.println("Erro ao ler dados: " + ex.getLocalizedMessage());
        } finally {
            ConnectionDB.closeConnection(conn, ps, rs);
        }

        return tipoAvaliacao;
    }

    public List<TipoAvaliacao> findAll() {
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        List<TipoAvaliacao> tipoAvaliacaos = new ArrayList<>();
        try {
            conn = ConnectionDB.getConnection();
            ps = conn.prepareStatement(LISTAR_TUDO);
            rs = ps.executeQuery();
            while (rs.next()) {
                TipoAvaliacao tipoAvaliacao = new TipoAvaliacao();
                popularComDados(tipoAvaliacao, rs);
                tipoAvaliacaos.add(tipoAvaliacao);
            }

        } catch (SQLException ex) {
            System.err.println("Erro ao ler dados: " + ex.getLocalizedMessage());
        } finally {
            ConnectionDB.closeConnection(conn);
        }
        return tipoAvaliacaos;
    }

    private void popularComDados(TipoAvaliacao tipoAvaliacao, ResultSet rs) {
        try {
            /*, ,,*/
            tipoAvaliacao.setIdTipoAvaliacao(rs.getInt("id_tipo_avaliacao"));
            tipoAvaliacao.setDescricaoTipoAvaliacao(rs.getString("descricao_tipo_avaliacao"));
            tipoAvaliacao.setSiglaAvaliacao(rs.getString("sigla_tipo_avaliacao"));
            tipoAvaliacao.setPesoTipoAvaliacao(rs.getDouble("peso_tipo_avaliacao"));

        } catch (SQLException ex) {
            System.err.println("Erro ao carregar dados: " + ex.getLocalizedMessage());
        }
    }
}
