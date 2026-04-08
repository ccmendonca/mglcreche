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
import skylink.mglcreche.modelo.Sexo;

/**
 * @Henriques
 */
public class SexoDAO {

    private static final String INSERT = "INSERT INTO sexo(descricao_sexo) VALUES (?)";
    private static final String DELETE = "DELETE FROM sexo WHERE id_sexo = ?";
    private static final String LISTAR_TUDO = "SELECT id_sexo, descricao_sexo FROM sexo";

    public boolean save(Sexo sexo) {
        PreparedStatement ps = null;
        Connection conn = null;
        boolean flagControlo = false;
        try {
            conn = ConnectionDB.getConnection();
            ps = conn.prepareStatement(INSERT);
            ps.setString(1, sexo.getDescricaoSexo());

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

    public boolean delete(Sexo sexo) {
        Connection conn = null;
        PreparedStatement ps = null;
        boolean flagControlo = false;
        if (sexo == null) {
            System.err.println("O campo anterior nao pode ser nulo");
            return false;
        }

        try {
            conn = ConnectionDB.getConnection();
            ps = conn.prepareStatement(DELETE);
            ps.setInt(1, sexo.getIdSexo());
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

    public List<Sexo> findAll() {
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        List<Sexo> sexos = new ArrayList<>();
        try {
            conn = ConnectionDB.getConnection();
            ps = conn.prepareStatement(LISTAR_TUDO);
            rs = ps.executeQuery();
            while (rs.next()) {
                Sexo sexo = new Sexo();
                popularComDados(sexo, rs);
                sexos.add(sexo);
            }

        } catch (SQLException ex) {
            System.err.println("Erro ao ler dados: " + ex.getLocalizedMessage());
        } finally {
            ConnectionDB.closeConnection(conn);
        }
        return sexos;
    }

    private void popularComDados(Sexo sexo, ResultSet rs) {
        try {
            sexo.setIdSexo(rs.getInt("id_sexo"));
            sexo.setDescricaoSexo(rs.getString("descricao_sexo"));

        } catch (SQLException ex) {
            System.err.println("Erro ao carregar dados: " + ex.getLocalizedMessage());
        }
    }

    public Sexo findById(Integer id) {
    if (id == null) {
        return null;
    }
    
    String sql = "SELECT * FROM sexo WHERE id_sexo = ?";
    
    try (Connection conn = ConnectionDB.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {
        
        ps.setInt(1, id);
        
        try (ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                Sexo sexo = new Sexo();
                sexo.setIdSexo(rs.getInt("id_sexo"));
                sexo.setDescricaoSexo(rs.getString("descricao_sexo"));
                return sexo;
            }
        }
        
    } catch (SQLException e) {
        Logger.getLogger(SexoDAO.class.getName())
              .log(Level.SEVERE, "Erro ao buscar sexo por ID: " + id, e);
    }
    
    return null;
}
}
