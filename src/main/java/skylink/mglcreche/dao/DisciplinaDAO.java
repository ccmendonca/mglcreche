package skylink.mglcreche.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import skylink.mglcreche.bdutil.ConnectionDB;
import skylink.mglcreche.modelo.Disciplina;
import skylink.mglcreche.modelo.EixoCurricularDisciplina;
import skylink.mglcreche.modelo.GrauParentesco;

public class DisciplinaDAO {

    private static final String INSERT = "INSERT INTO disciplina(descricao_disciplina, abreviatura, carga_horaria_semanal, id_eixo_curricular) VALUES (?, ?, ?, ?)";
    private static final String UPDATE = "UPDATE disciplina SET descricao_disciplina = ?, abreviatura = ?, carga_horaria_semanal = ? id_eixo_curricular = ?  WHERE id_disciplina = ?";
    private static final String DELETE = "DELETE FROM disciplina WHERE id_disciplina = ?";
    private static final String BUSCAR_POR_CODIGO = "SELECT  id_disciplina, descricao_disciplina, abreviatura, carga_horaria_semanal,descricao_eixo_curricular FROM disciplina d INNER JOIN eixo_curricular_disciplina e on d.id_eixo_curricular = e.id_eixo_curricular WHERE id_disciplina =?";
    private static final String LISTAR_TUDO = "SELECT  id_disciplina, descricao_disciplina, abreviatura, carga_horaria_semanal,descricao_eixo_curricular FROM disciplina d INNER JOIN eixo_curricular_disciplina e on d.id_eixo_curricular = e.id_eixo_curricular";

    public boolean save(Disciplina disciplina) {
        PreparedStatement ps = null;
        Connection conn = null;
        boolean flagControlo = false;
        try {
            conn = ConnectionDB.getConnection();
            ps = conn.prepareStatement(INSERT);
            ps.setString(1, disciplina.getDescricaoDisciplina());
            ps.setString(2, disciplina.getAbreviaturaDisciplina());
            ps.setString(3, disciplina.getCargaHorariaSemanal());
            ps.setInt(4, disciplina.getEixoCurricularDisciplina().getIdEixoCurricular());

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

    public boolean update(Disciplina disciplina) {
        PreparedStatement ps = null;
        Connection conn = null;
        boolean flagControlo = false;
        try {
            conn = ConnectionDB.getConnection();
            ps = conn.prepareStatement(UPDATE);
            ps.setString(1, disciplina.getDescricaoDisciplina());
            ps.setString(2, disciplina.getAbreviaturaDisciplina());
            ps.setString(3, disciplina.getCargaHorariaSemanal());
            ps.setInt(4, disciplina.getEixoCurricularDisciplina().getIdEixoCurricular());

            ps.setInt(5, disciplina.getIdDisciplina());
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

    public boolean delete(Disciplina disciplina) {
        Connection conn = null;
        PreparedStatement ps = null;
        boolean flagControlo = false;
        if (disciplina == null) {
            System.err.println("O campo anterior nao pode ser nulo");
        }

        try {
            conn = ConnectionDB.getConnection();
            ps = conn.prepareStatement(DELETE);
            ps.setInt(1, disciplina.getIdDisciplina());
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

    public Disciplina findById(Integer id) {
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        Disciplina disciplina = new Disciplina();

        try {
            conn = ConnectionDB.getConnection();
            ps = conn.prepareStatement(BUSCAR_POR_CODIGO);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (!rs.next()) {
                System.err.println("Não foi encontrado nenhum registo com o id: " + id);
            }
            popularComDados(disciplina, rs);
        } catch (SQLException ex) {
            System.err.println("Erro ao ler dados: " + ex.getLocalizedMessage());
        } finally {
            ConnectionDB.closeConnection(conn, ps, rs);
        }

        return disciplina;
    }

    public List<Disciplina> findAll() {
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        List<Disciplina> disciplinas = new ArrayList<>();
        try {
            conn = ConnectionDB.getConnection();
            ps = conn.prepareStatement(LISTAR_TUDO);
            rs = ps.executeQuery();
            while (rs.next()) {
                Disciplina disciplina = new Disciplina();
                popularComDados(disciplina, rs);
                disciplinas.add(disciplina);
            }

        } catch (SQLException ex) {
            System.err.println("Erro ao ler dados: " + ex.getLocalizedMessage());
        } finally {
            ConnectionDB.closeConnection(conn);
        }
        return disciplinas;
    }

    private void popularComDados(Disciplina disciplina, ResultSet rs) {
        try {

            disciplina.setIdDisciplina(rs.getInt("id_disciplina"));
            disciplina.setDescricaoDisciplina(rs.getString("descricao_disciplina"));
            disciplina.setAbreviaturaDisciplina(rs.getString("abreviatura"));
            disciplina.setCargaHorariaSemanal(rs.getString("carga_horaria_semanal"));
            EixoCurricularDisciplina eixo = new EixoCurricularDisciplina();
            eixo.setDescricaoEixoCurricular(rs.getString("descricao_eixo_curricular"));
            disciplina.setEixoCurricularDisciplina(eixo);

        } catch (SQLException ex) {
            System.err.println("Erro ao carregar dados: " + ex.getLocalizedMessage());
        }
    }
}
