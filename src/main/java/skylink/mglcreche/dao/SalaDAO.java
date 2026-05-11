package skylink.mglcreche.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import skylink.mglcreche.bdutil.ConnectionDB;
import skylink.mglcreche.modelo.Sala;

/**
 *
 * @author Henriques
 */
public class SalaDAO implements Serializable {

    private static final String SELECT_ALL = "SELECT id_sala, descricao_sala FROM sala ORDER BY descricao_sala";
    private static final String SELECT_BY_ID = "SELECT id_sala, descricao_sala FROM sala WHERE id_sala = ?";

    public List<Sala> findAll() {
        List<Sala> lista = new ArrayList<>();
        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(SELECT_ALL);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Sala s = new Sala();
                s.setIdSala(rs.getInt("id_sala"));
                s.setDescricaoSala(rs.getString("descricao_sala"));
                lista.add(s);
            }

        } catch (SQLException e) {
            System.err.println("Erro ao carregar salas: " + e.getLocalizedMessage());
        }
        return lista;
    }

    public Sala findById(Integer id) {
        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(SELECT_BY_ID)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Sala s = new Sala();
                    s.setIdSala(rs.getInt("id_sala"));
                    s.setDescricaoSala(rs.getString("descricao_sala"));
                    return s;
                }
            }

        } catch (SQLException e) {
            System.err.println("Erro ao buscar sala por id: " + e.getLocalizedMessage());
        }
        return null;
    }
}