package skylink.mglcreche.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import skylink.mglcreche.bdutil.ConnectionDB;
import skylink.mglcreche.modelo.Aluno;
import skylink.mglcreche.modelo.Avaliacao;
import skylink.mglcreche.modelo.Disciplina;
import skylink.mglcreche.modelo.Profissional;
import skylink.mglcreche.modelo.TipoAvaliacao;
import skylink.mglcreche.modelo.Trimestre;
import skylink.mglcreche.modelo.Turma;

public class AvaliacaoDAO {

    private static final String INSERT = "INSERT INTO avaliacao(descricao_avaliacao, data_aplicacao, peso_avaliacao, nota_avaliacao, id_disciplina, id_trimestre, id_profissional, id_tipo_avaliacao, id_turma, id_aluno) VALUES(?,?,?,?,?, ?,?,?,?,?)";
    private static final String UPDATE = "UPDATE avaliacao SET descricao_avaliacao = ?, data_aplicacao = ?, peso_avaliacao = ? nota_avaliacao = ?, id_disciplina = ?, id_trimestre = ?, id_profissional = ?, id_tipo_avaliacao = ?, id_turma = ?, id_aluno = ? WHERE id_avaliacao = ?";
    private static final String DELETE = "DELETE FROM avaliacao WHERE id_avaliacao= ?";
    private static final String BUSCAR_POR_CODIGO = "SELECT id_avaliacao, descricao_avaliacao,data_aplicacao,peso_avaliacao,nota_avaliacao,descricao_disciplina, abreviatura_disciplina, descricao_trimestre,nome_profissional,sobrenome_profissional,descricao_tipo_avaliacao, sigla_tipo_avaliacao,turma.id_turma, aluno.id_aluno, nome_aluno, sobrenome_aluno,data_registo_avaliacao FROM avaliacao INNER JOIN disciplina ON avaliacao.id_disciplina = disciplina.id_disciplina INNER JOIN trimestre ON avaliacao.id_trimestre = trimestre.id_trimestre INNER JOIN profissional ON avaliacao.id_profissional=profissional.id_profissional INNER JOIN tipo_avaliacao ON avaliacao.id_tipo_avaliacao = tipo_avaliacao.id_tipo_avaliacao INNER JOIN turma ON avaliacao.id_turma = turma.id_turma INNER JOIN aluno ON avaliacao.id_aluno=aluno.id_aluno WHERE id_avaliacao =?";
    private static final String LISTAR_TUDO = "SELECT id_avaliacao, descricao_avaliacao,data_aplicacao,peso_avaliacao,nota_avaliacao,descricao_disciplina, abreviatura_disciplina, descricao_trimestre,nome_profissional,sobrenome_profissional,descricao_tipo_avaliacao, sigla_tipo_avaliacao,turma.id_turma,codigo_turma,aluno.id_aluno, nome_aluno, sobrenome_aluno,data_registo_avaliacao FROM avaliacao INNER JOIN disciplina ON avaliacao.id_disciplina = disciplina.id_disciplina INNER JOIN trimestre ON avaliacao.id_trimestre = trimestre.id_trimestre INNER JOIN profissional ON avaliacao.id_profissional=profissional.id_profissional INNER JOIN tipo_avaliacao ON avaliacao.id_tipo_avaliacao = tipo_avaliacao.id_tipo_avaliacao INNER JOIN turma ON avaliacao.id_turma = turma.id_turma INNER JOIN aluno ON avaliacao.id_aluno=aluno.id_aluno";
    private static final String LISTAR_POR_TURMA = "SELECT id_avaliacao, descricao_avaliacao,data_aplicacao,peso_avaliacao,nota_avaliacao,descricao_disciplina, abreviatura_disciplina, descricao_trimestre,nome_profissional,sobrenome_profissional,descricao_tipo_avaliacao, sigla_tipo_avaliacao,turma.id_turma, codigo_turma,aluno.id_aluno, nome_aluno, sobrenome_aluno,data_registo_avaliacao FROM avaliacao INNER JOIN disciplina ON avaliacao.id_disciplina = disciplina.id_disciplina INNER JOIN trimestre ON avaliacao.id_trimestre = trimestre.id_trimestre INNER JOIN profissional ON avaliacao.id_profissional=profissional.id_profissional INNER JOIN tipo_avaliacao ON avaliacao.id_tipo_avaliacao = tipo_avaliacao.id_tipo_avaliacao INNER JOIN turma ON avaliacao.id_turma = turma.id_turma INNER JOIN aluno ON avaliacao.id_aluno=aluno.id_aluno WHERE turma.id_turma = ?";
    private static final String LISTAR_ENTRE_DATAS_APLICACAO = "SELECT id_avaliacao, descricao_avaliacao,data_aplicacao,peso_avaliacao,nota_avaliacao,descricao_disciplina, abreviatura_disciplina, descricao_trimestre,nome_profissional,sobrenome_profissional,descricao_tipo_avaliacao, sigla_tipo_avaliacao,turma.id_turma,codigo_turma,aluno.id_aluno, nome_aluno, sobrenome_aluno,data_registo_avaliacao FROM avaliacao INNER JOIN disciplina ON avaliacao.id_disciplina = disciplina.id_disciplina INNER JOIN trimestre ON avaliacao.id_trimestre = trimestre.id_trimestre INNER JOIN profissional ON avaliacao.id_profissional=profissional.id_profissional INNER JOIN tipo_avaliacao ON avaliacao.id_tipo_avaliacao = tipo_avaliacao.id_tipo_avaliacao INNER JOIN turma ON avaliacao.id_turma = turma.id_turma INNER JOIN aluno ON avaliacao.id_aluno=aluno.id_aluno WHERE disciplina.id_disciplina = ?";
    private static final String LISTAR_POR_DISCIPLINA = "SELECT id_avaliacao, descricao_avaliacao,data_aplicacao,peso_avaliacao,nota_avaliacao,descricao_disciplina, abreviatura_disciplina, descricao_trimestre,nome_profissional,sobrenome_profissional,descricao_tipo_avaliacao, sigla_tipo_avaliacao,turma.id_turma, codigo_turma, aluno.id_aluno,nome_aluno, sobrenome_aluno,data_registo_avaliacao FROM avaliacao INNER JOIN disciplina ON avaliacao.id_disciplina = disciplina.id_disciplina INNER JOIN trimestre ON avaliacao.id_trimestre = trimestre.id_trimestre INNER JOIN profissional ON avaliacao.id_profissional=profissional.id_profissional INNER JOIN tipo_avaliacao ON avaliacao.id_tipo_avaliacao = tipo_avaliacao.id_tipo_avaliacao INNER JOIN turma ON avaliacao.id_turma = turma.id_turma INNER JOIN aluno ON avaliacao.id_aluno=aluno.id_aluno WHERE data_aplicacao BETWEEN ? AND  ?";
    private static final String LISTAR_POR_TIPO_AVALICAO = "SELECT id_avaliacao, descricao_avaliacao,data_aplicacao,peso_avaliacao,nota_avaliacao,descricao_disciplina, abreviatura_disciplina, descricao_trimestre,nome_profissional,sobrenome_profissional,descricao_tipo_avaliacao, sigla_tipo_avaliacao,turma.id_turma,codigo_turma,aluno.id_aluno, nome_aluno, sobrenome_aluno,data_registo_avaliacao FROM avaliacao INNER JOIN disciplina ON avaliacao.id_disciplina = disciplina.id_disciplina INNER JOIN trimestre ON avaliacao.id_trimestre = trimestre.id_trimestre INNER JOIN profissional ON avaliacao.id_profissional=profissional.id_profissional INNER JOIN tipo_avaliacao ON avaliacao.id_tipo_avaliacao = tipo_avaliacao.id_tipo_avaliacao INNER JOIN turma ON avaliacao.id_turma = turma.id_turma INNER JOIN aluno ON avaliacao.id_aluno=aluno.id_aluno WHERE tipo_avaliacao.id_tipo_avaliacao =  ?";
    private static final String LISTAR_POR_ALUNO_TURMA="SELECT  data_aplicacao,  nota_avaliacao, descricao_disciplina,  descricao_trimestre,nome_profissional,sobrenome_profissional, descricao_tipo_avaliacao, sigla_tipo_avaliacao,turma.id_turma,codigo_turma, aluno.id_aluno, aluno.nome_aluno, aluno.sobrenome_aluno,data_registo_avaliacao FROM avaliacao INNER JOIN disciplina ON avaliacao.id_disciplina=disciplina.id_disciplina INNER JOIN trimestre ON avaliacao.id_trimestre = trimestre.id_trimestre INNER JOIN profissional ON avaliacao.id_profissional = profissional.id_profissional INNER JOIN tipo_avaliacao ON avaliacao.id_tipo_avaliacao=tipo_avaliacao.id_tipo_avaliacao INNER JOIN turma ON avaliacao.id_turma = turma.id_turma INNER JOIN aluno ON avaliacao.id_aluno =aluno.id_aluno";
    
    public boolean save(Avaliacao avaliacao) {
        PreparedStatement ps = null;
        Connection conn = null;
        boolean flagControlo = false;
        try {
            conn = ConnectionDB.getConnection();
            ps = conn.prepareStatement(INSERT);
            ps.setString(1, avaliacao.getDescricaoAvaliacao());
            ps.setDate(2, new java.sql.Date(avaliacao.getDataAplicacao().getTime()));
            // ps.setDouble(3, avaliacao.getPesoAvaliacao());
            ps.setDouble(3, 0);
            ps.setDouble(4, avaliacao.getNotaAvaliacao());
            ps.setInt(5, avaliacao.getDisciplina().getIdDisciplina());
            ps.setInt(6, avaliacao.getTrimestre().getIdTrimestre());
            ps.setInt(7, avaliacao.getProfissional().getCodigo());
            ps.setInt(8, avaliacao.getTipoAvaliacao().getIdTipoAvaliacao());
            ps.setInt(9, avaliacao.getTurma().getIdTurma());
            ps.setInt(10, avaliacao.getAluno().getIdAluno());

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

    public boolean update(Avaliacao avaliacao) {
        PreparedStatement ps = null;
        Connection conn = null;
        boolean flagControlo = false;
        try {
            conn = ConnectionDB.getConnection();
            ps.setString(1, avaliacao.getDescricaoAvaliacao());
            ps.setDate(2, new java.sql.Date(avaliacao.getDataAplicacao().getTime()));
            ps.setDouble(3, avaliacao.getPesoAvaliacao());
            ps.setDouble(4, avaliacao.getNotaAvaliacao());
            ps.setInt(5, avaliacao.getDisciplina().getIdDisciplina());
            ps.setInt(6, avaliacao.getTrimestre().getIdTrimestre());
            ps.setInt(7, avaliacao.getProfissional().getCodigo());
            ps.setInt(8, avaliacao.getTipoAvaliacao().getIdTipoAvaliacao());
            ps.setInt(9, avaliacao.getTurma().getIdTurma());
            ps.setInt(10, avaliacao.getAluno().getIdAluno());
            ps.setInt(11, avaliacao.getIdAvaliacao());
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

    public boolean delete(Avaliacao avaliacao) {
        Connection conn = null;
        PreparedStatement ps = null;
        boolean flagControlo = false;
        if (avaliacao == null) {
            System.err.println("O campo anterior nao pode ser nulo");
        }

        try {
            conn = ConnectionDB.getConnection();
            ps = conn.prepareStatement(DELETE);
            ps.setInt(1, avaliacao.getIdAvaliacao());
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

    public Avaliacao findById(Integer id) {
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        Avaliacao avaliacao = new Avaliacao();

        try {
            conn = ConnectionDB.getConnection();
            ps = conn.prepareStatement(BUSCAR_POR_CODIGO);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (!rs.next()) {
                System.err.println("Não foi encontrado nenhum registo com o id: " + id);
            }
            popularComDados(avaliacao, rs);
        } catch (SQLException ex) {
            System.err.println("Erro ao ler dados: " + ex.getLocalizedMessage());
        } finally {
            ConnectionDB.closeConnection(conn, ps, rs);
        }

        return avaliacao;
    }

    public List<Avaliacao> findAll() {
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        List<Avaliacao> avaliacaos = new ArrayList<>();
        try {
            conn = ConnectionDB.getConnection();
            ps = conn.prepareStatement(LISTAR_TUDO);
            rs = ps.executeQuery();
            while (rs.next()) {
                Avaliacao avaliacao = new Avaliacao();
                popularComDados(avaliacao, rs);
                avaliacaos.add(avaliacao);
            }

        } catch (SQLException ex) {
            System.err.println("Erro ao ler dados: " + ex.getLocalizedMessage());
        } finally {
            ConnectionDB.closeConnection(conn);
        }
        return avaliacaos;
    }

    private void popularComDados(Avaliacao avaliacao, ResultSet rs) {
        try {
            /*, , */
            avaliacao.setIdAvaliacao(rs.getInt("id_avaliacao"));
            avaliacao.setDescricaoAvaliacao(rs.getString("descricao_avaliacao"));
            avaliacao.setDataAplicacao(rs.getDate("data_aplicacao"));
            avaliacao.setPesoAvaliacao(rs.getDouble("peso_avaliacao"));
            avaliacao.setNotaAvaliacao(rs.getDouble("nota_avaliacao"));
            avaliacao.setDataRegistoAvaliacao(rs.getDate("data_registo_avaliacao"));

            Disciplina disciplina = new Disciplina();
            disciplina.setDescricaoDisciplina(rs.getString("descricao_disciplina"));
            disciplina.setAbreviaturaDisciplina(rs.getString("abreviatura_disciplina"));
            Trimestre trimestre = new Trimestre();
            trimestre.setDescricaoTrimestre(rs.getString("descricao_trimestre"));
            Profissional profissional = new Profissional();
            profissional.setNome(rs.getString("nome_profissional"));
            profissional.setSobrenome("sobrenome_profissional");
            TipoAvaliacao tipoAvaliacao = new TipoAvaliacao();
            tipoAvaliacao.setDescricaoTipoAvaliacao(rs.getString("descricao_tipo_avaliacao"));
            tipoAvaliacao.setSiglaAvaliacao(rs.getString("sigla_tipo_avaliacao"));
            Turma turma = new Turma();
            turma.setIdTurma(rs.getInt("turma.id_turma"));
            turma.setCodigoTurma(rs.getString("codigo_turma,"));
            Aluno aluno = new Aluno();
            aluno.setIdAluno(rs.getInt("aluno.id_aluno,"));
            aluno.setNomeAluno(rs.getString("nome_aluno"));
            aluno.setSobrenomeAluno(rs.getString("sobrenome_aluno"));
            avaliacao.setDisciplina(disciplina);
            avaliacao.setTrimestre(trimestre);
            avaliacao.setProfissional(profissional);
            avaliacao.setTipoAvaliacao(tipoAvaliacao);
            avaliacao.setTurma(turma);
            avaliacao.setAluno(aluno);

        } catch (SQLException ex) {
            System.err.println("Erro ao carregar dados: " + ex.getLocalizedMessage());
        }
    }
}
