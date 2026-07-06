package skylink.mglcreche.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import skylink.mglcreche.bdutil.ConnectionDB;
import skylink.mglcreche.modelo.Trimestre;

/**
 *
 * @Henriques
 */
public class TrimestreDAO implements Serializable {
    
    private static final long serialVersionUID = 1L;

    private static final String INSERT = "INSERT INTO trimestre(descricao_trimestre) VALUES (?)";
    private static final String SELECT_ALL = "SELECT id_trimestre, descricao_trimestre FROM trimestre ORDER BY descricao_trimestre";
    private static final String SELECT_BY_ID = "SELECT id_trimestre, descricao_trimestre FROM trimestre WHERE id_trimestre = ?";

    public boolean save(Trimestre trimestre) {
        PreparedStatement ps = null;
        Connection conn = null;
        boolean flagControlo = false;
        try {
            conn = ConnectionDB.getConnection();
            ps = conn.prepareStatement(INSERT);
            ps.setString(1, trimestre.getDescricaoTrimestre());

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

    public List<Trimestre> findAll() {
        List<Trimestre> lista = new ArrayList<>();
        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(SELECT_ALL);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Trimestre t = new Trimestre();
                t.setIdTrimestre(rs.getInt("id_trimestre"));
                t.setDescricaoTrimestre(rs.getString("descricao_trimestre"));
                lista.add(t);
            }

        } catch (SQLException e) {
            System.err.println("Erro ao carregar trimestres: " + e.getLocalizedMessage());
        }
        return lista;
    }

    public Trimestre findById(Integer id) {
        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(SELECT_BY_ID)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Trimestre t = new Trimestre();
                    t.setIdTrimestre(rs.getInt("id_trimestre"));
                    t.setDescricaoTrimestre(rs.getString("descricao_trimestre"));
                    return t;
                }
            }

        } catch (SQLException e) {
            System.err.println("Erro ao buscar trimestre por id: " + e.getLocalizedMessage());
        }
        return null;
    }
}