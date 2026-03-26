package skylink.mglcreche.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import skylink.mglcreche.bdutil.ConnectionDB;
import skylink.mglcreche.modelo.Aluno;

/**
 * «claudiomendonca»
 */
public class AlunoDAO implements Serializable {

    public static final String INSERT = "INSERT INTO aluno (id_aluno,nome_aluno,sobrenome_aluno,data_nascimento_aluno,grupo_sanguineo_aluno,casa_aluno,rua_aluno,bairro_aluno,nome_mae_aluno,sobrenome_mae_aluno,telefone_mae_aluno,nome_pai_aluno,sobrenome_pai_aluno,telefone_pai_aluno,data_registo_aluno) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    public static final String UPDATE = "UPDATE aluno SET id_aluno = ?, nome_aluno = ?, sobrenome_aluno = ?, data_nascimento_aluno = ?,grupo_sanguineo_aluno = ?, casa_aluno = ?, rua_aluno = ?, bairro_aluno = ?, nome_mae_aluno = ?, sobrenome_mae_aluno = ?, telefone_mae_aluno = , nome_pai_aluno = ?, sobrenome_pai_aluno = ?, telefone_pai_aluno = ?, data_registo_aluno = CURRENT_TIMESTAMP WHERE id_aluno = ?";
    public static final String DELETE = "DELETE FROM aluno WHERE id_aluno = ? ";
    public static final String SELECT_ALL = "SELECT * FROM aluno";
    public static final String SELECT_BY_ID = "SELECT * FROM aluno WHERE id_aluno = ? ";
    public static final String SELECT_BY_PARAMETER = "SELECT id_aluno, nome_aluno, sobrenome_aluno, data_nascimento_aluno, grupo_sanguineo_aluno, casa_aluno, rua_aluno, bairro_aluno, nome_mae_aluno, sobrenome_mae_aluno, telefone_mae_aluno, nome_pai_aluno, sobrenome_pai_aluno, telefone_pai_aluno, data_registo_aluno"
                                                    + "FROM aluno WHERE nome_aluno LIKE ?"
                                                    + "OR sobrenome_aluno LIKE ?";

    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    boolean flagControlo = false;

    private void popularDados(Aluno aluno, ResultSet rs) {
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

        } catch (SQLException e) {
            System.err.println("Erro ao carregar dados: " + e.getLocalizedMessage());
        }
    }

    public boolean save(Aluno aluno) {

        try {

            conn = ConnectionDB.getConnection();
            ps = conn.prepareStatement(INSERT);
            ps.setInt(1, aluno.getIdAluno());
            ps.setString(2, aluno.getNomeAluno());
            ps.setString(3, aluno.getSobrenomeAluno());
            ps.setDate(4, new java.sql.Date(aluno.getDataNascimentoAluno().getTime()));
            ps.setString(5, aluno.getGrauSanguineoAluno());
            ps.setString(6, aluno.getCasaAluno());
            ps.setString(7, aluno.getRuaAluno());
            ps.setString(8, aluno.getBairroAluno());
            ps.setString(9, aluno.getNomeMaeAluno());
            ps.setString(10, aluno.getSobrenomeMaeAluno());
            ps.setString(11, aluno.getTelefoneMaeAluno());
            ps.setString(12, aluno.getNomePaiAluno());
            ps.setString(13, aluno.getSobrenomePaiAluno());
            ps.setString(14, aluno.getTelefonePaiAluno());

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

    public boolean update(Aluno aluno) {

        try {
            conn = ConnectionDB.getConnection();
            ps = conn.prepareStatement(UPDATE);
            ps.setInt(1, aluno.getIdAluno());
            ps.setString(2, aluno.getNomeAluno());
            ps.setString(3, aluno.getSobrenomeAluno());
            ps.setDate(4, new java.sql.Date(aluno.getDataNascimentoAluno().getTime()));
            ps.setString(5, aluno.getGrauSanguineoAluno());
            ps.setString(6, aluno.getCasaAluno());
            ps.setString(7, aluno.getRuaAluno());
            ps.setString(8, aluno.getBairroAluno());
            ps.setString(9, aluno.getNomeMaeAluno());
            ps.setString(10, aluno.getSobrenomeMaeAluno());
            ps.setString(11, aluno.getTelefoneMaeAluno());
            ps.setString(12, aluno.getNomePaiAluno());
            ps.setString(13, aluno.getSobrenomePaiAluno());
            ps.setString(7, aluno.getTelefonePaiAluno());
            ps.setTimestamp(15, new java.sql.Timestamp(aluno.getDataRegistoAluno().getTime()));

            int retorno = ps.executeUpdate();
            if (retorno > 0) {
                System.out.println("Dados actualizados com sucesso: " + ps.getUpdateCount());
                flagControlo = true;

            }
            return flagControlo;
        } catch (SQLException e) {
            System.err.println("Erro ao actualizar dados: " + e.getMessage());
            return false;
        } finally {
            ConnectionDB.closeConnection(conn, ps);
        }

    }

    public boolean delete(Aluno aluno) {
        if (aluno == null) {
            System.err.println("O campo não pode ser nulo!");
        }
        try {
            conn = ConnectionDB.getConnection();
            ps = conn.prepareStatement(DELETE);
            ps.setInt(1, aluno.getIdAluno());
            int retorno = ps.executeUpdate();
            if (retorno > 0) {
                System.out.println("Dados eliminados com sucesso: " + ps.getUpdateCount());
                flagControlo = true;
            }
            return flagControlo;

        } catch (SQLException e) {
            System.err.println("Erro ao eliminar dados: " + e.getMessage());
            return false;
        } finally {
            ConnectionDB.closeConnection(conn, ps);
        }
    }

    public List<Aluno> findAll() {
        List<Aluno> alunos = new ArrayList<>();
        try {
            conn = ConnectionDB.getConnection();
            ps = conn.prepareStatement(SELECT_ALL);
            rs = ps.executeQuery();
            while (rs.next()) {
                Aluno aluno = new Aluno();
                popularDados(aluno, rs);
                alunos.add(aluno);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao carregar dados da base de dados: " + e.getLocalizedMessage());
        } finally {
            ConnectionDB.closeConnection(conn);
        }
        return alunos;
    }

    public List<Aluno> findById(Integer id) {
        List<Aluno> alunos = new ArrayList<>();
        Aluno aluno = new Aluno();
        try {
            conn = ConnectionDB.getConnection();
            ps = conn.prepareStatement(SELECT_BY_ID);
            ps.setInt(1, id);
            ps.executeQuery();
            if (!rs.next()) {
                System.err.println("Não foi encontrado nenhum registo com o código inserido: " + id);
            }
            popularDados(aluno, rs);
        } catch (SQLException e) {
            System.err.println("Erro ao carregar dados da base de dados: " + e.getLocalizedMessage());
        } finally {
            ConnectionDB.closeConnection(conn, ps, rs);
        }
        return alunos;
    }
    
    public List<Aluno> aleatory(Object especifily){
        List<Aluno> alunos = new ArrayList<>();
        Aluno aluno = new Aluno();
        try {
            conn = ConnectionDB.getConnection();
            ps = conn.prepareStatement(SELECT_BY_PARAMETER);
            String padron = "%" + especifily + "%";
            ps.setString(1, padron);
            ps.setString(2, padron);
            rs = ps.executeQuery();
            if(!rs.next()){
                System.err.println("Nenhum aluno encontrado com os valores especificados: " + especifily);
            }
            popularDados(aluno, rs);
        } catch (SQLException e) {
            System.err.println("Erro ao carregar dados da base de dados: " + e.getLocalizedMessage());
        }finally{
            ConnectionDB.closeConnection(conn, ps, rs);
        }
        return alunos;
    }
}
