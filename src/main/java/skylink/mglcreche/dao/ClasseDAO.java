package skylink.mglcreche.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import skylink.mglcreche.bdutil.ConnectionDB;
import skylink.mglcreche.modelo.Classe;
/**
 *
 * @Henriques
 */
public class ClasseDAO implements Serializable {

    private static final String SELECT_ALL = "SELECT id_classe, descricao_classe FROM classe ORDER BY descricao_classe";
    private static final String SELECT_BY_ID = "SELECT id_classe, descricao_classe FROM classe WHERE id_classe = ?";

    public List<Classe> findAll() {
        List<Classe> lista = new ArrayList<>();
        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(SELECT_ALL);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Classe c = new Classe();
                c.setIdClasse(rs.getInt("id_classe"));
                c.setDescricaoClasse(rs.getString("descricao_classe"));
                lista.add(c);
            }

        } catch (SQLException e) {
            System.err.println("Erro ao carregar classes: " + e.getLocalizedMessage());
        }
        return lista;
    }

    public Classe findById(Integer id) {
        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(SELECT_BY_ID)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Classe c = new Classe();
                    c.setIdClasse(rs.getInt("id_classe"));
                    c.setDescricaoClasse(rs.getString("descricao_classe"));
                    return c;
                }
            }

        } catch (SQLException e) {
            System.err.println("Erro ao buscar classe por id: " + e.getLocalizedMessage());
        }
        return null;
    }
}