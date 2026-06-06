package skylink.mglcreche.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import skylink.mglcreche.bdutil.ConnectionDB;
import skylink.mglcreche.modelo.Aluno;
import skylink.mglcreche.modelo.AnoLectivo;
import skylink.mglcreche.modelo.FormaPagamento;
import skylink.mglcreche.modelo.Propina;

public class PropinaDAO {

    private static final String INSERT = "INSERT INTO propina(id_aluno, id_ano_lectivo, id_forma_pagamento, observacoes) VALUES(?,?,?, ? )";
    private static final String UPDATE = "";
    private static final String DELETE = "";
    private static final String SELECT_ALL = "SELECT p.id_propina, data_pagamento, a.id_aluno, nome_aluno, sobrenome_aluno, descricao_ano_lectivo, descricao_forma_pagamento, observacoes, data_hora_registo FROM propina p INNER JOIN aluno a ON p.id_aluno=a.id_aluno INNER JOIN ano_lectivo av ON p.id_ano_lectivo = av.id_ano_lectivo INNER JOIN forma_pagamento fp ON p.id_forma_pagamento =fp.id_forma_pagamento";
    private static final String SELECT_BY_ID = "SELECT p.id_propina, data_pagamento, a.id_aluno, nome_aluno, sobrenome_aluno, descricao_ano_lectivo, descricao_forma_pagamento, observacoes, data_hora_registo FROM propina p INNER JOIN aluno a ON p.id_aluno=a.id_aluno INNER JOIN ano_lectivo av ON p.id_ano_lectivo = av.id_ano_lectivo INNER JOIN forma_pagamento fp ON p.id_forma_pagamento =fp.id_forma_pagamento WHERE p.id_propina = ?";

    private static final String SELECT_MAX_ID_FACTURA = "SELECT MAX(id_propina) FROM propina";
    private static final String SELECT_FORMA_PAGAMENTO = "SELECT id_forma_pagamento FROM propina where id_propina = ?";

    public boolean save(Propina propina) {
        PreparedStatement ps = null;
        Connection conn = null;
        boolean flagControlo = false;
        if (propina == null) {
            System.err.println("PropinaDAO:save: O valor oassado nao pode ser nulo!");
        }
        try {
            conn = ConnectionDB.getConnection();

            ps = conn.prepareStatement(INSERT);
            ps.setInt(1, propina.getAluno().getIdAluno());
            ps.setInt(2, propina.getAnoLectivo().getIdAnoLectivo());
            ps.setInt(3, propina.getFormaPagamento().getIdFormaPagamento());
            ps.setString(4, propina.getObservacoes());
            int retorno = ps.executeUpdate();
            if (retorno > 0) {
                System.out.println("PropinaDAO:save:Dados inseridos com sucesso: " + ps.getUpdateCount());
                flagControlo = true;
            }

            return flagControlo;

        } catch (SQLException e) {
            System.out.println("PropinaDAO:save:Erro ao inserir dados: " + e.getMessage());
            return false;
        } finally {
            ConnectionDB.closeConnection(conn, ps);
        }
    }

    public boolean update(Propina propina) {
        //implementar
        return false;
    }

    public boolean delete(Propina propina) {
        //implementar
        return false;
    }

    public Propina findById(Integer id) {
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        Propina propina = new Propina();
        try {
            conn = ConnectionDB.getConnection();
            ps = conn.prepareStatement(SELECT_BY_ID);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (!rs.next()) {
                System.err.println("PropinaDAO:findById: nenhum registo com o id: " + id);
            }
            popularComDados(propina, rs);
        } catch (SQLException ex) {
            System.err.println("PropinaDAO:findeByID: Erro ao ler dados: " + ex.getLocalizedMessage());
        } finally {
            ConnectionDB.closeConnection(conn, ps, rs);
        }
        return propina;
    }

    public List<Propina> findAll() {
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        List<Propina> propinas = new ArrayList<>();
        try {
            conn = ConnectionDB.getConnection();
            ps = conn.prepareStatement(SELECT_ALL);
            rs = ps.executeQuery();
            while (rs.next()) {
                Propina propina = new Propina();
                popularComDados(propina, rs);
                propinas.add(propina);
            }
        } catch (SQLException ex) {
            System.err.println("Erro ao ler dados: " + ex.getLocalizedMessage());
        } finally {
            ConnectionDB.closeConnection(conn);
        }
        return propinas;
    }

     public Integer buscaIdFormaPagamento(Integer idPropina) {
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;

        Integer formaPagamento = null;
        try {
            conn = ConnectionDB.getConnection();
            ps = conn.prepareStatement(SELECT_FORMA_PAGAMENTO);
            ps.setInt(1, idPropina);
            rs = ps.executeQuery();
            if (rs.next()) {
                formaPagamento = rs.getInt(1);
            }
            System.out.println("PropinaDAO: buscaIdFormaPagamento -> Forma de pagamento" + formaPagamento);
        } catch (SQLException ex) {
            System.out.println("PropinaDAO: buscaIdFormaPagamento -> Erro ao carregar dados" + ex.getMessage());
        } finally {
            ConnectionDB.closeConnection((Connection) conn);
        }
        return formaPagamento;
    }
    
    public Integer buscaUltimaFactura() {
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;

        Integer ultimo = null;
        try {
            conn = ConnectionDB.getConnection();
            ps = conn.prepareStatement(SELECT_MAX_ID_FACTURA);
            rs = ps.executeQuery();
            if (rs.next()) {
                ultimo = rs.getInt(1);
            }
            System.out.println("PropinaDAO: buscaUltimaPropina -> Maior propina" + ultimo);
        } catch (SQLException ex) {
            System.out.println("PropinaDAO: buscaUltimaFactura -> Erro ao carregar dados" + ex.getMessage());
        } finally {
            ConnectionDB.closeConnection((Connection) conn);
        }
        return ultimo;
    }

    public void popularComDados(Propina propina, ResultSet rs) {
        try {
            propina.setIdPropina(rs.getInt("p.id_propina"));
            propina.setDataPagamento(rs.getDate("p.data_pagamento"));
            propina.setDataHoraRegisto(rs.getTimestamp("data_hora_registo"));
            propina.setObservacoes(rs.getString("observacoes"));
            Aluno aluno = new Aluno();
            aluno.setIdAluno(rs.getInt("a.id_aluno"));
            aluno.setNomeAluno(rs.getString("nome_aluno"));
            aluno.setSobrenomeAluno(rs.getString("sobrenome_aluno"));
            propina.setAluno(aluno);
            FormaPagamento formaPagamento = new FormaPagamento();
            formaPagamento.setDescricaoFormaPagamento(rs.getString("fp.descricao_forma_pagamento"));
            AnoLectivo anoLectivo = new AnoLectivo();
            anoLectivo.setDescricaoAnoLectivo(rs.getString("descricao_ano_lectivo"));
            propina.setFormaPagamento(formaPagamento);
            propina.setAnoLectivo(anoLectivo);

        } catch (SQLException ex) {
            System.err.println("Error on fill data PropinaDAO: " + ex.getLocalizedMessage());
        }

    }

}
