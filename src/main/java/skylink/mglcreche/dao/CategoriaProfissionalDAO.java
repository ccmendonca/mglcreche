package skylink.mglcreche.dao;

import java.lang.invoke.MethodHandles;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import skylink.mglcreche.bdutil.ConnectionDB;
import skylink.mglcreche.modelo.CategoriaProfissional;

/**
 * «claudiomendonca
 */

public class CategoriaProfissionalDAO {

    private static final Logger LOG = Logger.getLogger(MethodHandles.lookup().lookupClass().getName());
    private static final String INSERT = "INSERT INTO categoria_profissional (id_categoriaprofissional, descricao_categoriaprofissional) values (?,?)";
    private static final String UPDATE = "UPDATE categoria_profissional set descricao_categoriaprofissional ?";
    private static final String DELETE = "DELETE categoria_profissional WHERE id_categoriaprofissional = ? ";
    private static final String SELECT_ALL = "SELECT * FROM categoria_profissional";
    private static final String SELECT_BY_ID = "SELECT * FROM categoria_profissional WHERE id_categoriaprofissional = ?";
    private static final String SELECT_BY_NAME = "SELECT * FROM categoria_profissional WHERE descricao_categoriaprofissional LIKE ?";

    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    boolean verifique = false;

    public void dados(CategoriaProfissional cp, ResultSet rs) {
        try {
            cp.setCodigo(rs.getInt("id_categoriaProfissional"));
            cp.setDescricao(rs.getString("descricao_categoriaprofissional"));
        } catch (SQLException e) {
            System.err.println("Erro ao carregar dados: " + e.getLocalizedMessage());
        }

    }

    public boolean save(CategoriaProfissional categoriaProfissional) {
        try {
            conn = ConnectionDB.getConnection();
            ps = conn.prepareStatement(INSERT);
            ps.setInt(1, categoriaProfissional.getCodigo());
            ps.setString(2, categoriaProfissional.getDescricao());

            int p = ps.executeUpdate();
            if (p > 0) {
                System.out.println("Dados inseridos com sucesso" + ps.getUpdateCount());
                verifique = true;
            }
            return verifique;

        } catch (SQLException e) {
            String pott = e.getLocalizedMessage();
            LOG.log(Level.SEVERE, e, () -> "Erro de inserção de dados: {0}" + pott);
            return false;
        }

    }

    public List<CategoriaProfissional> findAll() {
        List<CategoriaProfissional> cprofissionais = new ArrayList<>();

        try {
            conn = ConnectionDB.getConnection();
            ps = conn.prepareStatement(SELECT_ALL);
            rs = ps.executeQuery();
            while (rs.next()) {
                CategoriaProfissional cp = new CategoriaProfissional();
                dados(cp, rs);
                cprofissionais.add(cp);
            }
        } catch (SQLException ex) {
            String msg = ex.getLocalizedMessage();
            LOG.log(Level.SEVERE, ex, () -> "Erro ao ler dados:" + msg);
        }
        return cprofissionais;
    }

    public List<CategoriaProfissional> findById(Integer id) {
        List<CategoriaProfissional> ctprofissionais = new ArrayList<>();

        try {
            conn = ConnectionDB.getConnection();
            ps = conn.prepareStatement(SELECT_BY_ID);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                CategoriaProfissional cap = new CategoriaProfissional();
                dados(cap, rs);
                ctprofissionais.add(cap);

            }

        } catch (SQLException ex) {
            String msg = ex.getLocalizedMessage();
            LOG.log(Level.SEVERE, ex, () -> "Erro ao ler os dados:" + msg);
        }
        return ctprofissionais;
    }

    public List<CategoriaProfissional> findByName(String name) {
        List<CategoriaProfissional> categorias = new ArrayList<>();
        try {
            conn = ConnectionDB.getConnection();
            ps = conn.prepareStatement(SELECT_BY_NAME);
            ps.setString(1, "%" + name + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                CategoriaProfissional categoria = new CategoriaProfissional();
                dados(categoria, rs);
                categorias.add(categoria);
            }
        } catch (SQLException i) {
            String msg = i.getMessage();
            LOG.log(Level.SEVERE, i, () -> "Erro de leitura de dados" + msg);
        }

        return categorias;
    }

}
