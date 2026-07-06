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
import skylink.mglcreche.modelo.AnoLectivo;
import skylink.mglcreche.modelo.Classe;
import skylink.mglcreche.modelo.FormaPagamento;
import skylink.mglcreche.modelo.Matricula;
import skylink.mglcreche.modelo.Periodo;
import skylink.mglcreche.modelo.Sala;
import skylink.mglcreche.modelo.Turma;

public class MatriculaDAO {

    public static final String INSERT = "INSERT INTO matricula (id_aluno, id_turma, id_forma_pagamento) VALUES (?,?,?)";
    public static final String UPDATE = "UPDATE matricula SET id_aluno = ?, id_turma WHERE id_matricula = ?";
    public static final String DELETE = "DELETE matricula WHERE id_matricula = ? ";
    public static final String SELECT_ALL = "SELECT * FROM matricula";
    public static final String SELECT_BY_ID = "SELECT * FROM matricula WHERE id_matricula = ? ";
    public static final String SELECT_MAX_ID_MATRICULA = "SELECT MAX(id_matricula) FROM matricula";
    public static final String SELECT_BETEEWN_DATES = "SELECT * FROM matricula WHRE data_matricula BETWEEN ? AND ?";
    private static final String SELECT_FORMA_PAGAMENTO = "SELECT id_forma_pagamento FROM propina where id_propina = ?";
    public static final String SELECT_ESTUDANTES_BY_ID_TURMA = "SELECT a.id_aluno, a.nome_aluno, a.sobrenome_aluno, t.id_turma, t.codigo_turma,al.descricao_ano_lectivo,c.descricao_classe,s.descricao_sala, p.descricao_periodo  FROM aluno a INNER JOIN matricula m ON a.id_aluno= m.id_aluno INNER JOIN turma t ON m.id_turma =t.id_turma  INNER JOIN ano_lectivo al ON t.id_ano_lectivo = al.id_ano_lectivo  INNER JOIN classe c ON c.id_classe= t.id_classe INNER JOIN sala s ON s.id_sala = t.id_sala INNER JOIN periodo p ON t.id_periodo=p.id_periodo WHERE t.id_turma = 1";

    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    boolean flagControlo = false;

  

    public boolean save(Matricula matricula) {

        if (matricula == null) {
            System.err.println("O campo não pode ser nulo!");
        }

        try {

            conn = ConnectionDB.getConnection();
            ps = conn.prepareStatement(INSERT);
            ps.setInt(1, matricula.getAluno().getIdAluno());
            ps.setInt(2, matricula.getTurma().getIdTurma());
            ps.setInt(3, matricula.getFormaPagamento().getIdFormaPagamento());

            int retorno = ps.executeUpdate();

            if (retorno > 0) {
                System.out.println("Dados inserido com sucesso: " + ps.getUpdateCount());
                flagControlo = true;
            }
            return flagControlo;
        } catch (SQLException e) {
            System.err.println("Erro ao inserir dados: " + e.getLocalizedMessage());
            return false;
        } finally {
            ConnectionDB.closeConnection(conn, ps);
        }

    }

    public boolean update(Matricula matricula) {

        //implementar
        return false;
    }

    public boolean delete(Matricula matricula) {

        //implementar
        return false;
    }

    public List<Matricula> findAll() {
        List<Matricula> matriculas = new ArrayList<>();

        try {
            conn = ConnectionDB.getConnection();
            ps = conn.prepareStatement(SELECT_ALL);
            ps.executeQuery();

            while (rs.next()) {
                Matricula matricula = new Matricula();
                popularDados(matricula, rs);
                matriculas.add(matricula);
            }

        } catch (SQLException e) {
            System.err.println("Erro ao carregar dados da base de dados: " + e.getLocalizedMessage());
        } finally {
            ConnectionDB.closeConnection(conn, ps, rs);
        }

        return matriculas;
    }

    public Integer buscaIdFormaPagamento(Integer idMatricula) {
        Integer formaPagamento = null;
        try {
            conn = ConnectionDB.getConnection();
            ps = conn.prepareStatement(SELECT_FORMA_PAGAMENTO);
            ps.setInt(1, idMatricula);
            rs = ps.executeQuery();
            if (rs.next()) {
                formaPagamento = rs.getInt(1);
            }
            System.out.println("MatriculaDAO: buscaIdFormaPagamento -> Forma de pagamento" + formaPagamento);
        } catch (SQLException ex) {
            System.out.println("MatriculaDAO: buscaIdFormaPagamento -> Erro ao carregar dados" + ex.getMessage());
        } finally {
            ConnectionDB.closeConnection((Connection) conn);
        }
        return formaPagamento;
    }

    public List<Matricula> findById(Integer id) {
        List<Matricula> matriculas = new ArrayList<>();
        Matricula matricula = new Matricula();

        try {
            conn = ConnectionDB.getConnection();
            ps = conn.prepareStatement(SELECT_BY_ID);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            if (!rs.next()) {
                System.err.println("Nenhum registo encontrado com o código inserido: " + id);
            }

            popularDados(matricula, rs);

        } catch (SQLException e) {
            System.err.println("Erro ao carregar dados da base de dados: " + e.getLocalizedMessage());
        } finally {
            ConnectionDB.closeConnection(conn, ps, rs);
        }
        return matriculas;
    }
    
    
    
    
     public Matricula findTurmaComMatriculados(Integer idTurma) {
         Matricula matricula = new Matricula();
        try {
            conn = ConnectionDB.getConnection();
            ps = conn.prepareStatement(SELECT_ESTUDANTES_BY_ID_TURMA);
            ps.setInt(1, idTurma);
            rs = ps.executeQuery();

            if (!rs.next()) {
                System.err.println("Nenhum registo encontrado com o código inserido: " + idTurma);
            }

            popularDados(matricula, rs);

        } catch (SQLException e) {
            System.err.println("Erro ao carregar dados da base de dados:findAlunosMatriculados " + e.getLocalizedMessage());
        } finally {
            ConnectionDB.closeConnection(conn, ps, rs);
        }
        return matricula;
    }
     
     
     
        public List<Matricula> findAllAlunosDaTurma(Integer idTurma) {
        List<Matricula> matriculas = new ArrayList<>();

        try {
            conn = ConnectionDB.getConnection();
            ps = conn.prepareStatement(SELECT_ESTUDANTES_BY_ID_TURMA);
            ps.setInt(1, idTurma);
            ps.executeQuery();

            while (rs.next()) {
                Matricula matricula = new Matricula();
                popularDados(matricula, rs);
                matriculas.add(matricula);
            }

        } catch (SQLException e) {
            System.err.println("Erro ao carregar dados da base de dados: " + e.getLocalizedMessage());
        } finally {
            ConnectionDB.closeConnection(conn, ps, rs);
        }

        return matriculas;
    }

    public List<Matricula> findBetweenDates(LocalDate start, LocalDate end) {
        List<Matricula> matriculas = new ArrayList<>();
        Matricula matricula = new Matricula();

        try {
            conn = ConnectionDB.getConnection();
            ps = conn.prepareStatement(SELECT_BETEEWN_DATES);
            ps.setDate(1, Date.valueOf(start));
            ps.setDate(2, Date.valueOf(end));
            rs = ps.executeQuery();

            while (rs.next()) {
                popularDados(matricula, rs);
                matriculas.add(matricula);

            }
        } catch (SQLException e) {
            System.err.println("Erro ao carregar dados da base de dados: " + e.getLocalizedMessage());
        } finally {
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
    
    
    
    
      public void popularDados(Matricula matricula, ResultSet rs) {

        try {
            matricula.setIdMatricula(rs.getInt("id_matricula"));
            matricula.setDataMatricula(rs.getDate("data_matricula"));
            Aluno aluno = new Aluno();
            aluno.setIdAluno(rs.getInt("id_aluno"));
            aluno.setNomeAluno(rs.getNString("nome_aluno"));
            aluno.setSobrenomeAluno(rs.getNString("sobrenome_aluno"));
            aluno.setDataNascimentoAluno(rs.getDate("data_nascimento_aluno"));
            matricula.setAluno(aluno);
            Turma turma = new Turma();
            turma.setIdTurma(rs.getInt("id_turma"));
            turma.setCodigoTurma(rs.getString("codigo_turma"));
            matricula.setTurma(turma);
            FormaPagamento formaPagamento = new FormaPagamento();
            formaPagamento.setDescricaoFormaPagamento(rs.getString("fp.descricao_forma_pagamento"));
            matricula.setFormaPagamento(formaPagamento);
            turma.setIdTurma(rs.getInt("id_turma"));
            turma.setObservacoesTurma(rs.getString("observacoes_turma"));
            turma.setCodigoTurma(rs.getString("codigo_turma"));
            turma.setNumeroMaximo(rs.getInt("numero_maximo"));
            turma.setActiva(rs.getBoolean("activa"));
            turma.setDataRegisto(rs.getTimestamp("data_registo"));

            AnoLectivo ano = new AnoLectivo();
            ano.setDescricaoAnoLectivo(rs.getString("descricao_ano_lectivo"));
            turma.setAnoLectivo(ano);

            Classe classe = new Classe();
            classe.setDescricaoClasse(rs.getString("descricao_classe"));
            turma.setClasse(classe);

            Periodo periodo = new Periodo();
            periodo.setDescricaoPeriodo(rs.getString("descricao_periodo"));
            turma.setPeriodo(periodo);

            Sala sala = new Sala();
            sala.setDescricaoSala(rs.getString("descricao_sala"));
            turma.setSala(sala);

        } catch (SQLException e) {
            System.err.println("Erro ao carregar dados: " + e.getLocalizedMessage());
        }

    }

}
