package skylink.mglcreche.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import skylink.mglcreche.bdutil.ConnectionDB;
import skylink.mglcreche.modelo.ResponsavelBuscaAluno;

public class ResponsavelBuscaAlunoDAO {

    private static final String INSERT = "INSERT INTO responsavel_busca_aluno (nome_responsavel, sobrenome_responsavel, data_nascimento_responsavel, casa_responsavel, rua_responsavel, municipio_responsavel, bairro_responsavel, telefone_responsavel) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

    private static final String UPDATE = "UPDATE responsavel_busca_aluno SET nome_responsavel = ?, sobrenome_responsavel = ?, data_nascimento_responsavel = ?, casa_responsavel = ?, rua_responsavel = ?,  municipio_responsavel=?,  bairro_responsavel = ?, telefone_responsavel = ? WHERE id_responsavel = ?";

    private static final String DELETE = "DELETE FROM responsavel_busca_aluno WHERE id_responsavel = ?";

    private static final String BUSCA_POR_NOME = "SELECT * FROM responsavel_busca_aluno WHERE   nome_responsavel like ? OR sobrenome_responsavel like ? OR telefone_responsavel like ?";

    public boolean save(ResponsavelBuscaAluno responsavel) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = ConnectionDB.getConnection();
            ps = conn.prepareStatement(INSERT);

            ps.setString(1, responsavel.getNomeResponsavel());
            ps.setString(2, responsavel.getSobrenomeResponsavel());
            if (responsavel.getDataNascimentoResponsavel() != null) {
                ps.setDate(3, new java.sql.Date(responsavel.getDataNascimentoResponsavel().getTime()));
            } else {
                ps.setDate(3, null);
            }
            ps.setString(4, responsavel.getCasaResponsavel());
            ps.setString(5, responsavel.getRuaResponsavel());
            ps.setString(7, responsavel.getBairroResponsavel());
            ps.setString(8, responsavel.getTelefoneResponsavel());
            ps.setString(6, responsavel.getMunicipioResponsavel());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Erro ao inserir responsável: " + e.getMessage());
            return false;
        } finally {
            ConnectionDB.closeConnection(conn, ps);
        }
    }

    public boolean update(ResponsavelBuscaAluno responsavel) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = ConnectionDB.getConnection();
            ps = conn.prepareStatement(UPDATE);

            ps.setString(1, responsavel.getNomeResponsavel());
            ps.setString(2, responsavel.getSobrenomeResponsavel());

            if (responsavel.getDataNascimentoResponsavel() != null) {
                ps.setDate(3, new java.sql.Date(responsavel.getDataNascimentoResponsavel().getTime()));
            } else {
                ps.setDate(3, null);
            }

            ps.setString(4, responsavel.getCasaResponsavel());
            ps.setString(5, responsavel.getRuaResponsavel());
            ps.setString(6, responsavel.getBairroResponsavel());
            ps.setString(7, responsavel.getMunicipioResponsavel());
            ps.setString(8, responsavel.getTelefoneResponsavel());
            ps.setInt(9, responsavel.getIdResponsavel());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Erro ao atualizar: " + e.getMessage());
            return false;
        } finally {
            ConnectionDB.closeConnection(conn, ps);
        }
    }

    public boolean delete(ResponsavelBuscaAluno responsavel) {
        if (responsavel == null || responsavel.getIdResponsavel() == null) {
            return false;
        }

        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = ConnectionDB.getConnection();
            ps = conn.prepareStatement(DELETE);
            ps.setInt(1, responsavel.getIdResponsavel());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Erro ao eliminar: " + e.getMessage());
            return false;
        } finally {
            ConnectionDB.closeConnection(conn, ps);
        }
    }

    public List<ResponsavelBuscaAluno> listar(String nomeResponsavel) {

        List<ResponsavelBuscaAluno> lista = new ArrayList<>();

        try (Connection conn = ConnectionDB.getConnection(); PreparedStatement ps = conn.prepareStatement(BUSCA_POR_NOME)) {
            ps.setString(1, "%" + nomeResponsavel + "%");
            ps.setString(2, "%" + nomeResponsavel + "%");
            ps.setString(3, "%" + nomeResponsavel + "%");

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ResponsavelBuscaAluno r = new ResponsavelBuscaAluno();
                popularComDados(r, rs);
                lista.add(r);
            }

        } catch (SQLException ex) {
            System.err.println("Erro ao listar: " + ex.getMessage());
            Logger.getLogger(ResponsavelBuscaAlunoDAO.class.getName()).log(Level.SEVERE, "Erro de listagem", ex);
        }

        return lista;
    }

    private void popularComDados(ResponsavelBuscaAluno r, ResultSet rs) throws SQLException {

        r.setIdResponsavel(rs.getInt("id_responsavel"));
        r.setNomeResponsavel(rs.getString("nome_responsavel"));
        r.setSobrenomeResponsavel(rs.getString("sobrenome_responsavel"));

        r.setDataNascimentoResponsavel(rs.getDate("data_nascimento_responsavel"));

        r.setCasaResponsavel(rs.getString("casa_responsavel"));
        r.setRuaResponsavel(rs.getString("rua_responsavel"));
        r.setMunicipioResponsavel(rs.getString("municipio_responsavel"));
        r.setBairroResponsavel(rs.getString("bairro_responsavel"));
        r.setTelefoneResponsavel(rs.getString("telefone_responsavel"));

        r.setDataRegistoResponsavel(rs.getTimestamp("data_registo_responsavel"));
    }
}
