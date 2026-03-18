package skylink.mglcreche.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import skylink.mglcreche.bdutil.ConnectionDB;
import skylink.mglcreche.modelo.GrauParentesco;

public class GrauParentescoDAO {

    private static final String INSERT = "INSERT INTO grau_parentesco(descricao_grau_parentesco) VALUES (?)";
    private static final String UPDATE = "UPDATE grau_parentesco SET descricao_grau_parentesco = ?  WHERE id_grau_parentesco = ?";
    private static final String DELETE = "DELETE FROM grau_parentesco WHERE id_grau_parentesco= ?";
    private static final String BUSCAR_POR_CODIGO = "SELECT id_grau_parentesco, descricao_grau_parentesco FROM grau_parentesco WHERE id_grau_parentesco =?";
    private static final String LISTAR_TUDO = "SELECT id_grau_parentesco, descricao_grau_parentesco FROM grau_parentesco;";

    public boolean save(GrauParentesco grauParentesco) {
        PreparedStatement ps = null;
        Connection conn = null;
        boolean flagControlo = false;
        try {
            conn = ConnectionDB.getConnection();
            ps = conn.prepareStatement(INSERT);
            ps.setString(1, grauParentesco.getDescricaoGrauParentesco());

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

    public boolean update(GrauParentesco grauParentesco) {
        PreparedStatement ps = null;
        Connection conn = null;
        boolean flagControlo = false;
        try {
            conn = ConnectionDB.getConnection();
            ps = conn.prepareStatement(UPDATE);
            ps.setString(1, grauParentesco.getDescricaoGrauParentesco());

            ps.setInt(2, grauParentesco.getIdGrauParentesco());
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

    public boolean delete(GrauParentesco grauParentesco) {
        Connection conn = null;
        PreparedStatement ps = null;
        boolean flagControlo = false;
        if (grauParentesco == null) {
            System.err.println("O campo anterior nao pode ser nulo");
        }

        try {
            conn = ConnectionDB.getConnection();
            ps = conn.prepareStatement(DELETE);
            ps.setInt(1, grauParentesco.getIdGrauParentesco());
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

    public GrauParentesco findById(Integer id) {
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        GrauParentesco grauParentesco = new GrauParentesco();

        try {
            conn = ConnectionDB.getConnection();
            ps = conn.prepareStatement(BUSCAR_POR_CODIGO);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (!rs.next()) {
                System.err.println("Não foi encontrado nenhum registo com o id: " + id);
            }
            popularComDados(grauParentesco, rs);
        } catch (SQLException ex) {
            System.err.println("Erro ao ler dados: " + ex.getLocalizedMessage());
        } finally {
            ConnectionDB.closeConnection(conn, ps, rs);
        }

        return grauParentesco;
    }

    public List< GrauParentesco> findAll() {
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        List<GrauParentesco> grauParentescos = new ArrayList<>();
        try {
            conn = ConnectionDB.getConnection();
            ps = conn.prepareStatement(LISTAR_TUDO);
            rs = ps.executeQuery();
            while (rs.next()) {
                GrauParentesco grauParentesco = new GrauParentesco();
                popularComDados(grauParentesco, rs);
                grauParentescos.add(grauParentesco);
            }

        } catch (SQLException ex) {
            System.err.println("Erro ao ler dados: " + ex.getLocalizedMessage());
        } finally {
            ConnectionDB.closeConnection(conn);
        }
        return grauParentescos;
    }

    private void popularComDados(GrauParentesco grauParentesco, ResultSet rs) {
        try {

            grauParentesco.setIdGrauParentesco(rs.getInt("id_grau_parentesco"));
            grauParentesco.setDescricaoGrauParentesco(rs.getString("descricao_grau_parentesco"));

        } catch (SQLException ex) {
            System.err.println("Erro ao carregar dados: " + ex.getLocalizedMessage());
        }
    }

}
