
package skylink.mglcreche.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import skylink.mglcreche.bdutil.ConnectionDB;
import skylink.mglcreche.modelo.Aluno;
import skylink.mglcreche.modelo.Matricula;
import skylink.mglcreche.modelo.Turma;

/**
 * «claudiomendonca»
 */


public class MatriculaDAO {
    
    public static final String INSERT = "INSERT INTO matricula (id_aluno, id_turma) VALUES (?,?)";
    public static final String UPDATE = "UPDATE matricula SET id_aluno = ?, id_turma WHERE id_matricula = ?";
    public static final String DELETE = "DELETE matricula WHERE id_matricula = ? ";
    public static final String SELECT_ALL = "SELECT * FROM matricula";
    public static final String SELECT_BY_ID = "SELECT * FROM matricula WHERE id_matricula = ? ";
    public static final String SELECT_MAX_ID_MATRICULA = "SELECT MAX(id_matricula) FROM matricula";
    public static final String SELECT_BETEEWN_DATES = "SELECT * FROM matricula WHRE data_matricula BETWEEN ? AND ?";

    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    boolean flagControlo = false;
    
    
    public void popularDados(Matricula matricula, ResultSet rs){
        
        try {
            matricula.setIdMatricula(rs.getInt("id_matricula"));
            matricula.setDataMatricula(rs.getDate("data_matricula"));
            Aluno aluno = new Aluno();
            aluno.setIdAluno(rs.getInt("id_aluno"));
            aluno.setNomeAluno(rs.getNString("nome_aluno"));
            aluno.setSobrenomeAluno(rs.getNString("sobrenome_aluno"));
            matricula.setAluno(aluno);
            Turma turma = new Turma();
            turma.setIdTurma(rs.getInt("id_turma"));
            turma.setDescricaoTurma(rs.getString("descricao_turma"));
            matricula.setTurma(turma);
            
        } catch (SQLException e) {
            System.err.println("Erro ao carregar dados: " + e.getLocalizedMessage());
        }
        
    }
    
    public boolean save(Matricula matricula){
        
        if (matricula == null){
            System.err.println("O campo não pode ser nulo!");
        }
        
        try {
            
            conn = ConnectionDB.getConnection();
            ps = conn.prepareStatement(INSERT);
            ps.setInt(1, matricula.getAluno().getIdAluno());
            ps.setInt(2, matricula.getTurma().getIdTurma());
            
            int retorno = ps.executeUpdate();
            
            if(retorno > 0){
                System.out.println("Dados inserido com sucesso: " + ps.getUpdateCount());
                flagControlo = true;
            }
            return flagControlo;
        } catch (SQLException e) {
            System.err.println("Erro ao inserir dados: " + e.getLocalizedMessage());
            return false;
        }finally{
        ConnectionDB.closeConnection(conn, ps);
    }
        
    }
    
    public boolean update(Matricula matricula){
        
        //implementar
        return false;
    }
    
    public boolean delete(Matricula matricula){
        
        //implementar
        return false;
    }
    
    public List<Matricula> findAll(){
        List<Matricula> matriculas = new ArrayList<>();
        
        try {
            conn = ConnectionDB.getConnection();
            ps = conn.prepareStatement(SELECT_ALL);
            ps.executeQuery();
            
            while(rs.next()){
                Matricula matricula = new Matricula();
                popularDados(matricula, rs);
                matriculas.add(matricula);
            }
            
        } catch (SQLException e) {
            System.err.println("Erro ao carregar dados da base de dados: " + e.getLocalizedMessage());
        }finally{
            ConnectionDB.closeConnection(conn, ps, rs);
        }
        
        return matriculas;
    }
    
    public List<Matricula> findById(Integer id){
        List<Matricula> matriculas = new ArrayList<>();
        Matricula matricula = new Matricula();
        
        try {
            conn = ConnectionDB.getConnection();
            ps = conn.prepareStatement(SELECT_BY_ID);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            
            if (!rs.next()){
                System.err.println("Nenhum registo encontrado com o código inserido: " + id);
            }
            
            popularDados(matricula, rs);
            
        } catch (SQLException e) {
            System.err.println("Erro ao carregar dados da base de dados: " + e.getLocalizedMessage());
        }finally{
            ConnectionDB.closeConnection(conn, ps, rs);
        }
        return matriculas;
    }
    
    public List<Matricula> findBetweenDates(LocalDate start, LocalDate end){
        List<Matricula> matriculas = new ArrayList<>();
        Matricula matricula = new Matricula();
        
        try {
            conn = ConnectionDB.getConnection();
            ps = conn.prepareStatement(SELECT_BETEEWN_DATES);
            ps.setDate(1, Date.valueOf(start));
            ps.setDate(2, Date.valueOf(end));
            rs = ps.executeQuery();
            
            while(rs.next()){
                popularDados(matricula, rs);
                matriculas.add(matricula);
                
            }
        } catch (SQLException e) {
            System.err.println("Erro ao carregar dados da base de dados: " + e.getLocalizedMessage());
        }finally{
            ConnectionDB.closeConnection(conn, ps, rs);
        }
        
        return matriculas;
    }
    
    public Matricula buscaUltimaMatricula() {
        Matricula matricula = new Matricula();
        Integer ultimo = null;
        try {
            conn = ConnectionDB.getConnection();
            ps = conn.prepareStatement(SELECT_MAX_ID_MATRICULA);
            rs = ps.executeQuery();
            if (rs.next()) {
                   popularDados(matricula, rs);
            }

        } catch (SQLException ex) {
            System.out.println("MatriculaDAO: buscaUltimaMatricula -> Erro ao carregar dados" + ex.getMessage());
        } finally {
            ConnectionDB.closeConnection(conn);
        }
        return matricula;

    }
    
}
