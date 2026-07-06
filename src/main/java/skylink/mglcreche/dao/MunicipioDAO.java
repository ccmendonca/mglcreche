
package skylink.mglcreche.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import skylink.mglcreche.bdutil.ConnectionDB;
import skylink.mglcreche.modelo.Municipio;

/**
 *
 * @Henriques
 */
public class MunicipioDAO implements Serializable {

    private static final String SELECT_ALL = "SELECT * FROM municipio ORDER BY nome_municipio";
    private static final String SELECT_BY_ID = "SELECT * FROM municipio WHERE id_municipio = ?";
    private static final String SELECT_BY_PROVINCIA = "SELECT * FROM municipio WHERE id_provincia = ? ORDER BY nome_municipio";

    public List<Municipio> findAll() {
        List<Municipio> lista = new ArrayList<>();
        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(SELECT_ALL);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Municipio m = new Municipio();
                m.setIdMunicipio(rs.getInt("id_municipio"));
                m.setNomeMunicipio(rs.getString("nome_municipio"));
                m.setIdProvincia(rs.getInt("id_provincia"));
                lista.add(m);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao carregar municípios: " + e.getLocalizedMessage());
        }
        return lista;
    }

    public Municipio findById(Integer id) {
        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(SELECT_BY_ID)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Municipio m = new Municipio();
                    m.setIdMunicipio(rs.getInt("id_municipio"));
                    m.setNomeMunicipio(rs.getString("nome_municipio"));
                    m.setIdProvincia(rs.getInt("id_provincia"));
                    return m;
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar município por id: " + e.getLocalizedMessage());
        }
        return null;
    }

    public List<Municipio> findByProvincia(Integer idProvincia) {
        List<Municipio> lista = new ArrayList<>();
        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(SELECT_BY_PROVINCIA)) {

            ps.setInt(1, idProvincia);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Municipio m = new Municipio();
                    m.setIdMunicipio(rs.getInt("id_municipio"));
                    m.setNomeMunicipio(rs.getString("nome_municipio"));
                    m.setIdProvincia(rs.getInt("id_provincia"));
                    lista.add(m);
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar municípios por província: " + e.getLocalizedMessage());
        }
        return lista;
    }
}
