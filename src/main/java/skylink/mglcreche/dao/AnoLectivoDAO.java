/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package skylink.mglcreche.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import skylink.mglcreche.bdutil.ConnectionDB;
import skylink.mglcreche.modelo.AnoLectivo;


public class AnoLectivoDAO {
     private static final String INSERT = "INSERT INTO ano_lectivo(descricao_ano_lectivo) VALUES (?)";
    private static final String UPDATE = "UPDATE ano_lectivo SET descricao_ano_lectivo = ?  WHERE id_ano_lectivo = ?";
    private static final String DELETE = "DELETE FROM ano_lectivo WHERE id_ano_lectivo= ?";
    private static final String BUSCAR_POR_CODIGO = "SELECT id_ano_lectivo, descricao_ano_lectivo FROM ano_lectivo; WHERE id_ano_lectivo =?";
    private static final String LISTAR_TUDO = "SELECT id_ano_lectivo, descricao_ano_lectivo FROM ano_lectivo";

    public boolean save(AnoLectivo anoLectivo) {
        PreparedStatement ps = null;
        Connection conn = null;
        boolean flagControlo = false;
        try {
            conn = ConnectionDB.getConnection();
            ps = conn.prepareStatement(INSERT);
            ps.setString(1, anoLectivo.getDescricaoAnoLectivo());

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

    public boolean update(AnoLectivo anoLectivo) {
        PreparedStatement ps = null;
        Connection conn = null;
        boolean flagControlo = false;
        try {
            conn = ConnectionDB.getConnection();
            ps = conn.prepareStatement(UPDATE);
            ps.setString(1, anoLectivo.getDescricaoAnoLectivo());

            ps.setInt(2, anoLectivo.getIdAnoLectivo());
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

    public boolean delete(AnoLectivo anoLectivo) {
        Connection conn = null;
        PreparedStatement ps = null;
        boolean flagControlo = false;
        if (anoLectivo == null) {
            System.err.println("O campo anterior nao pode ser nulo");
        }

        try {
            conn = ConnectionDB.getConnection();
            ps = conn.prepareStatement(DELETE);
            ps.setInt(1, anoLectivo.getIdAnoLectivo());
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

    public AnoLectivo findById(Integer id) {
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
       AnoLectivo anoLectivo = new AnoLectivo();

        try {
            conn = ConnectionDB.getConnection();
            ps = conn.prepareStatement(BUSCAR_POR_CODIGO);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (!rs.next()) {
                System.err.println("Não foi encontrado nenhum registo com o id: " + id);
            }
            popularComDados(anoLectivo, rs);
        } catch (SQLException ex) {
            System.err.println("Erro ao ler dados: " + ex.getLocalizedMessage());
        } finally {
            ConnectionDB.closeConnection(conn, ps, rs);
        }

        return anoLectivo;
    }

    public List<AnoLectivo> findAll() {
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        List<AnoLectivo> anoLectivos = new ArrayList<>();
        try {
            conn = ConnectionDB.getConnection();
            ps = conn.prepareStatement(LISTAR_TUDO);
            rs = ps.executeQuery();
            while (rs.next()) {
               AnoLectivo anoLectivo = new AnoLectivo();
                popularComDados(anoLectivo, rs);
                anoLectivos.add(anoLectivo);
            }

        } catch (SQLException ex) {
            System.err.println("Erro ao ler dados: " + ex.getLocalizedMessage());
        } finally {
            ConnectionDB.closeConnection(conn);
        }
        return anoLectivos;
    }

    private void popularComDados(AnoLectivo anoLectivo, ResultSet rs) {
        try {

            anoLectivo.setIdAnoLectivo(rs.getInt("id_ano_lectivo"));
            anoLectivo.setDescricaoAnoLectivo(rs.getString("descricao_ano_lectivo"));

        } catch (SQLException ex) {
            System.err.println("Erro ao carregar dados: " + ex.getLocalizedMessage());
        }
    }
}