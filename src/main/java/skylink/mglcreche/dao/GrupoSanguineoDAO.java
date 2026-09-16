
package skylink.mglcreche.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import skylink.mglcreche.bdutil.ConnectionDB;
import skylink.mglcreche.modelo.Classe;
import skylink.mglcreche.modelo.GrupoSanguineo;


public class GrupoSanguineoDAO {
    private static final String SELECT_ALL = "SELECT id_grupo_sanguineo, descricao_grupo_sanguineo FROM grupo_sanguineo";
    private static final String SELECT_BY_ID = "SELECT id_grupo_sanguineo, descricao_grupo_sanguineo FROM grupo_sanguineo WHERE id_grupo_sanguineo = ?";
    
    
     public boolean save(Classe classe) {
        PreparedStatement ps = null;
        Connection conn = null;
        boolean flagControlo = false;
        try {
            conn = ConnectionDB.getConnection();
          //  ps = conn.prepareStatement(INSERT);
            ps.setString(1, classe.getDescricaoClasse());

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

    public List<GrupoSanguineo> findAll() {
        List<GrupoSanguineo> lista = new ArrayList<>();
        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(SELECT_ALL);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                GrupoSanguineo gs = new GrupoSanguineo();
                gs.setIdGrupoSanguineo(rs.getInt("id_grupo_sanguineo"));
                gs.setDescricaoGrupoSanguineo(rs.getString("descricao_grupo_sanguineo"));
                lista.add(gs);
            }

        } catch (SQLException e) {
            System.err.println("Erro ao carregar classes: " + e.getLocalizedMessage());
        }
        return lista;
    }

    public GrupoSanguineo findById(Integer id) {
        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(SELECT_BY_ID)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    GrupoSanguineo gs = new GrupoSanguineo();
                    gs.setIdGrupoSanguineo(rs.getInt("id_grupo_sanguineo"));
                    gs.setDescricaoGrupoSanguineo(rs.getString("descricao_grupo_sanguineo"));
                    return gs;
                }
            }

        } catch (SQLException e) {
            System.err.println("Erro ao buscar classe por id: " + e.getLocalizedMessage());
        }
        return null;
    }
}
