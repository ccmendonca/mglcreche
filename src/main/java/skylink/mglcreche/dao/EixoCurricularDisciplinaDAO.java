
package skylink.mglcreche.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import skylink.mglcreche.bdutil.ConnectionDB;
import skylink.mglcreche.modelo.EixoCurricularDisciplina;
import skylink.mglcreche.modelo.GrauParentesco;

public class EixoCurricularDisciplinaDAO {
      private static final String INSERT = "INSERT INTO eixo_curricular_disciplina(descricao_eixo_curricular) VALUES (?)";
    private static final String UPDATE = "UPDATE eixo_curricular_disciplina SET descricao_eixo_curricular = ?  WHERE id_eixo_curricular = ?";
    private static final String DELETE = "DELETE FROM disciplina WHERE id_disciplina = ?";
    private static final String BUSCAR_POR_CODIGO = "SELECT id_eixo_curricular, descricao_eixo_curricular FROM eixo_curricular_disciplina WHERE id_eixo_curricular =?";
    private static final String LISTAR_TUDO = "SELECT id_eixo_curricular, descricao_eixo_curricular FROM eixo_curricular_disciplina";

    public boolean save(EixoCurricularDisciplina eixoCurricularDisciplina) {
        PreparedStatement ps = null;
        Connection conn = null;
        boolean flagControlo = false;
        try {
            conn = ConnectionDB.getConnection();
            ps = conn.prepareStatement(INSERT);
            ps.setString(1, eixoCurricularDisciplina.getDescricaoEixoCurricular());

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

    public boolean update(EixoCurricularDisciplina eixoCurricularDisciplina) {
        PreparedStatement ps = null;
        Connection conn = null;
        boolean flagControlo = false;
        try {
            conn = ConnectionDB.getConnection();
            ps = conn.prepareStatement(UPDATE);
            ps.setString(1, eixoCurricularDisciplina.getDescricaoEixoCurricular());

            ps.setInt(2, eixoCurricularDisciplina.getIdEixoCurricular());
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

    public boolean delete(EixoCurricularDisciplina eixoCurricularDisciplina) {
        Connection conn = null;
        PreparedStatement ps = null;
        boolean flagControlo = false;
        if (eixoCurricularDisciplina == null) {
            System.err.println("O campo anterior nao pode ser nulo");
        }

        try {
            conn = ConnectionDB.getConnection();
            ps = conn.prepareStatement(DELETE);
            ps.setInt(1, eixoCurricularDisciplina.getIdEixoCurricular());
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

    public EixoCurricularDisciplina findById(Integer id) {
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        EixoCurricularDisciplina eixoCurricularDisciplina = new EixoCurricularDisciplina();

        try {
            conn = ConnectionDB.getConnection();
            ps = conn.prepareStatement(BUSCAR_POR_CODIGO);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (!rs.next()) {
                System.err.println("Não foi encontrado nenhum registo com o id: " + id);
            }
            popularComDados(eixoCurricularDisciplina, rs);
        } catch (SQLException ex) {
            System.err.println("Erro ao ler dados: " + ex.getLocalizedMessage());
        } finally {
            ConnectionDB.closeConnection(conn, ps, rs);
        }

        return eixoCurricularDisciplina;
    }

    public List<EixoCurricularDisciplina> findAll() {
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        List<EixoCurricularDisciplina> eixoCurricularDisciplinas = new ArrayList<>();
        try {
            conn = ConnectionDB.getConnection();
            ps = conn.prepareStatement(LISTAR_TUDO);
            rs = ps.executeQuery();
            while (rs.next()) {
                EixoCurricularDisciplina eixoCurricularDisciplina = new EixoCurricularDisciplina();
                popularComDados(eixoCurricularDisciplina, rs);
                eixoCurricularDisciplinas.add(eixoCurricularDisciplina);
            }

        } catch (SQLException ex) {
            System.err.println("Erro ao ler dados: " + ex.getLocalizedMessage());
        } finally {
            ConnectionDB.closeConnection(conn);
        }
        return eixoCurricularDisciplinas;
    }

    private void popularComDados(EixoCurricularDisciplina eixoCurricularDisciplina, ResultSet rs) {
        try {

            eixoCurricularDisciplina.setIdEixoCurricular(rs.getInt("id_eixo_curricular"));
            eixoCurricularDisciplina.setDescricaoEixoCurricular(rs.getString("descricao_eixo_curricular"));

        } catch (SQLException ex) {
            System.err.println("Erro ao carregar dados: " + ex.getLocalizedMessage());
        }
    }
}
