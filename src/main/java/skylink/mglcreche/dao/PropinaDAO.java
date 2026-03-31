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
import skylink.mglcreche.modelo.Aluno;
import skylink.mglcreche.modelo.FormaPagamento;
import skylink.mglcreche.modelo.Propina;

public class PropinaDAO {

    private static final String INSERT = "INSERT INTO proprina(id_aluno, id_ano_lectivo, id_forma_pagamento) VALUES(?,?,? )";
    private static final String UPDATE = "";
    private static final String DELETE = "";
    private static final String SELECT_ALL = "";
    private static final String SELECT_BY_ID = "";

    private static final String SELECT_MAX_ID_FACTURA = "SELECT MAX(id_propina) FROM propina";

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

    public Propina buscaUltimaPropina() {
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        Propina propina = new Propina();
        Integer ultimo = null;
        try {
            conn = ConnectionDB.getConnection();
            ps = conn.prepareStatement(SELECT_MAX_ID_FACTURA);
            rs = ps.executeQuery();
            if (rs.next()) {
                   popularComDados(propina, rs);
             //   ultimo = rs.getInt(1);
            }

        } catch (SQLException ex) {
            System.out.println("PropinaDAO: buscaUltimaFactura -> Erro ao carregar dados" + ex.getMessage());
        } finally {
            ConnectionDB.closeConnection(conn);
        }
        return propina;

    }

    public void popularComDados(Propina propina, ResultSet rs) {
        try {

            propina.setIdPropina(rs.getInt("id_proprina"));
            propina.setDataPagamento(rs.getDate("data_pagamento"));
            propina.setDataHoraRegisto(rs.getTimestamp("data_hora_registo"));
            Aluno aluno = new Aluno();
            aluno.setIdAluno(rs.getInt("id_aluno"));
            aluno.setNomeAluno(rs.getString("nome_aluno"));
            aluno.setSobrenomeAluno(rs.getString("sobrenome_aluno"));
            propina.setAluno(aluno);
            FormaPagamento formaPagamento = new FormaPagamento();
            formaPagamento.setDescricaoFormaPagamento(rs.getString("fp.descricao_forma_de_pagamento"));

        } catch (SQLException ex) {
            System.err.println("Error on fill data Propina: " + ex.getLocalizedMessage());
        }

    }

}
