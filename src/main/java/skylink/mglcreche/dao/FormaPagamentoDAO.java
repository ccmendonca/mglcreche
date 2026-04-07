
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
import skylink.mglcreche.modelo.MesPropina;


public class FormaPagamentoDAO {
   private static final String INSERT = " INSERT INTO forma_pagamento(descricao_forma_pagamento) VALUES( ?)";
      
    private static final String UPDATE = "UPDATE forma_pagamento SET descricao_forma_pagamento = ? WHERE id_forma_pagamento = ?";
    private static final String DELETE = "DELETE FROM forma_pagamento WHERE id_forma_pagamento= ?";
    private static final String BUSCAR_POR_CODIGO = "SELECT id_forma_pagamento, descricao_forma_pagamento FROM forma_pagamento WHERE id_forma_pagamento =?";
    private static final String LISTAR_TUDO = "SELECT id_forma_pagamento, descricao_forma_pagamento FROM forma_pagamento";
   

    public boolean save(FormaPagamento formaPagamento) {
        PreparedStatement ps = null;
        Connection conn = null;
        boolean flagControlo = false;
        try {
            conn = ConnectionDB.getConnection();
            ps = conn.prepareStatement(INSERT);
            ps.setString(1, formaPagamento.getDescricaoFormaPagamento());
           

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

    public boolean update(FormaPagamento formaPagamento) {
        PreparedStatement ps = null;
        Connection conn = null;
        boolean flagControlo = false;
        try {
            conn = ConnectionDB.getConnection();
            ps = conn.prepareStatement(UPDATE);
            ps.setString(1, formaPagamento.getDescricaoFormaPagamento());

            ps.setInt(2, formaPagamento.getIdFormaPagamento());
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

    public boolean delete(FormaPagamento formaPagamento) {
        Connection conn = null;
        PreparedStatement ps = null;
        boolean flagControlo = false;
        if (formaPagamento == null) {
            System.err.println("O campo anterior nao pode ser nulo");
        }

        try {
            conn = ConnectionDB.getConnection();
            ps = conn.prepareStatement(DELETE);
            ps.setInt(1, formaPagamento.getIdFormaPagamento());
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

    public FormaPagamento findById(Integer id) {
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        FormaPagamento formaPagamento = new FormaPagamento();

        try {
            conn = ConnectionDB.getConnection();
            ps = conn.prepareStatement(BUSCAR_POR_CODIGO);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (!rs.next()) {
                System.err.println("Não foi encontrado nenhum registo com o id: " + id);
            }
            popularComDados(formaPagamento, rs);
        } catch (SQLException ex) {
            System.err.println("Erro ao ler dados: " + ex.getLocalizedMessage());
        } finally {
            ConnectionDB.closeConnection(conn, ps, rs);
        }

        return formaPagamento;
    }

    public List< FormaPagamento> findAll() {
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        List<FormaPagamento> formaPagamentos = new ArrayList<>();
        try {
            conn = ConnectionDB.getConnection();
            ps = conn.prepareStatement(LISTAR_TUDO);
            rs = ps.executeQuery();
            while (rs.next()) {
                FormaPagamento formaPagamento = new FormaPagamento();
                popularComDados(formaPagamento, rs);
                formaPagamentos.add(formaPagamento);
            }

        } catch (SQLException ex) {
            System.err.println("Erro ao ler dados: " + ex.getLocalizedMessage());
        } finally {
            ConnectionDB.closeConnection(conn);
        }
        return formaPagamentos;
    }

   

    private void popularComDados(FormaPagamento formaPagamento, ResultSet rs) {
        try {
         
            formaPagamento.setIdFormaPagamento(rs.getInt("id_forma_pagamento"));
            formaPagamento.setDescricaoFormaPagamento(rs.getString("descricao_forma_pagamento"));
            
        } catch (SQLException ex) {
            System.err.println("Erro ao carregar dados: " + ex.getLocalizedMessage());
        }
    }

   
  
}
