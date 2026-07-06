
package skylink.mglcreche.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import skylink.mglcreche.bdutil.ConnectionDB;
import skylink.mglcreche.modelo.Aluno;
import skylink.mglcreche.modelo.MesPropina;

public class MesPropinaDAO {

    private static final String INSERT = "INSERT INTO mes_propina (descricao_mes_propina, valor_mes_propina)VALUES(?, ?)";
    private static final String UPDATE = "UPDATE mes_propina SET valor_mes_propina = ? WHERE id_mes_propina = ?";
    private static final String DELETE = "DELETE FROM mes_propina WHERE id_mes_propina= ?";
    private static final String BUSCAR_POR_CODIGO = "SELECT id_mes_propina, descricao_mes_propina, valor_mes_propina FROM mes_propina WHERE id_mes_propina =?";
    private static final String LISTAR_TUDO = "SELECT id_mes_propina, descricao_mes_propina, valor_mes_propina FROM mes_propina";
    private static final String BUSCA_ALUNO_NOME = "SELECT id_aluno,  nome_aluno,sobrenome_aluno, data_nascimento_aluno, grupo_sanguineo_aluno, casa_aluno, rua_aluno, bairro_aluno, nome_mae_aluno, sobrenome_mae_aluno, telefone_mae_aluno, nome_pai_aluno, sobrenome_pai_aluno, telefone_pai_aluno, data_registo_aluno FROM aluno WHERE id_aluno LIKE? OR nome_aluno LIKE ? OR sobrenome_aluno LIKE ?";

    public boolean save(MesPropina mesPropina) {
        PreparedStatement ps = null;
        Connection conn = null;
        boolean flagControlo = false;
        try {
            conn = ConnectionDB.getConnection();
            ps = conn.prepareStatement(INSERT);
            ps.setString(1, mesPropina.getDescricaoMesPropina());
            ps.setDouble(2, mesPropina.getValorMesPropina());

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

    public boolean update(MesPropina mesPropina) {
        PreparedStatement ps = null;
        Connection conn = null;
        boolean flagControlo = false;
        try {
            conn = ConnectionDB.getConnection();
            ps = conn.prepareStatement(UPDATE);
            ps.setDouble(1, mesPropina.getValorMesPropina());

            ps.setInt(2, mesPropina.getIdMesPropina());
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

    public boolean delete(MesPropina mesPropina) {
        Connection conn = null;
        PreparedStatement ps = null;
        boolean flagControlo = false;
        if (mesPropina == null) {
            System.err.println("O campo anterior nao pode ser nulo");
        }

        try {
            conn = ConnectionDB.getConnection();
            ps = conn.prepareStatement(DELETE);
            ps.setInt(1, mesPropina.getIdMesPropina());
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

    public MesPropina findById(Integer id) {
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        MesPropina mesPropina = new MesPropina();

        try {
            conn = ConnectionDB.getConnection();
            ps = conn.prepareStatement(BUSCAR_POR_CODIGO);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (!rs.next()) {
                System.err.println("Não foi encontrado nenhum registo com o id: " + id);
            }
            popularComDados(mesPropina, rs);
        } catch (SQLException ex) {
            System.err.println("Erro ao ler dados: " + ex.getLocalizedMessage());
        } finally {
            ConnectionDB.closeConnection(conn, ps, rs);
        }

        return mesPropina;
    }

    public List< MesPropina> findAll() {
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        List<MesPropina> mesPropinas = new ArrayList<>();
        try {
            conn = ConnectionDB.getConnection();
            ps = conn.prepareStatement(LISTAR_TUDO);
            rs = ps.executeQuery();
            while (rs.next()) {
                MesPropina mesPropina = new MesPropina();
                popularComDados(mesPropina, rs);
                mesPropinas.add(mesPropina);
            }

        } catch (SQLException ex) {
            System.err.println("Erro ao ler dados: " + ex.getLocalizedMessage());
        } finally {
            ConnectionDB.closeConnection(conn);
        }
        return mesPropinas;
    }

    public List<Aluno> findAllAlunosByNome(String valor) {
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        List<Aluno> alunos = new ArrayList<>();
        try {
            conn = ConnectionDB.getConnection();
            ps = conn.prepareStatement(BUSCA_ALUNO_NOME);
            ps.setString(1, "%" + valor + "%");
            ps.setString(2, "%" + valor + "%");
            ps.setString(3, "%" + valor + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                Aluno aluno = new Aluno();
                popularAluno(aluno, rs);
                alunos.add(aluno);
            }

        } catch (SQLException ex) {
            System.err.println("Erro ao ler dados: " + ex.getLocalizedMessage());
        } finally {
            ConnectionDB.closeConnection(conn);
        }
        return alunos;
    }

    private void popularComDados(MesPropina mesPropina, ResultSet rs) {
        try {
            mesPropina.setIdMesPropina(rs.getInt("id_mes_propina"));
            mesPropina.setDescricaoMesPropina(rs.getString("descricao_mes_propina"));
            mesPropina.setValorMesPropina(rs.getDouble("valor_mes_propina"));
        } catch (SQLException ex) {
            System.err.println("Erro ao carregar dados: " + ex.getLocalizedMessage());
        }
    }

    public void popularAluno(Aluno aluno, ResultSet rs) {
        try {

            aluno.setIdAluno(rs.getInt("id_aluno"));
            aluno.setNomeAluno(rs.getString("nome_aluno"));
            aluno.setSobrenomeAluno(rs.getString("sobrenome_aluno"));
            aluno.setDataNascimentoAluno(rs.getDate("data_nascimento_aluno"));
            aluno.setGrauSanguineoAluno(rs.getString("grupo_sanguineo_aluno"));
            aluno.setCasaAluno(rs.getString("casa_aluno"));
            aluno.setRuaAluno(rs.getString("rua_aluno"));
            aluno.setBairroAluno(rs.getString("bairro_aluno"));
            aluno.setNomeMaeAluno(rs.getString("nome_mae_aluno"));
            aluno.setSobrenomeMaeAluno(rs.getString("sobrenome_mae_aluno"));
            aluno.setTelefoneMaeAluno(rs.getString("telefone_mae_aluno"));
            aluno.setNomePaiAluno(rs.getString("nome_pai_aluno"));
            aluno.setSobrenomePaiAluno(rs.getString("sobrenome_pai_aluno"));
            aluno.setTelefonePaiAluno(rs.getString("telefone_pai_aluno"));
            aluno.setDataRegistoAluno(rs.getDate("data_registo_aluno"));

        } catch (SQLException ex) {
            System.err.println("Error on fill data Cliente: " + ex.getLocalizedMessage());
        }

    }

}
