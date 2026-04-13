package skylink.mglcreche.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import skylink.mglcreche.bdutil.ConnectionDB;
import skylink.mglcreche.modelo.Sexo;

public class SexoDAO implements Serializable {

    private static final String SELECT_ALL = "SELECT * FROM sexo";
    private static final String SELECT_BY_ID = "SELECT * FROM sexo WHERE id_sexo = ?";

    public List<Sexo> findAll() {
        List<Sexo> lista = new ArrayList<>();
        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(SELECT_ALL);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Sexo s = new Sexo();
                s.setIdSexo(rs.getInt("id_sexo"));
                s.setDescricaoSexo(rs.getString("descricao_sexo"));
                lista.add(s);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao carregar sexos: " + e.getLocalizedMessage());
        }
        return lista;
    }

    public Sexo findById(Integer id) {
        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(SELECT_BY_ID)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Sexo s = new Sexo();
                    s.setIdSexo(rs.getInt("id_sexo"));
                    s.setDescricaoSexo(rs.getString("descricao_sexo"));
                    return s;
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar sexo por id: " + e.getLocalizedMessage());
        }
        return null;
    }
}