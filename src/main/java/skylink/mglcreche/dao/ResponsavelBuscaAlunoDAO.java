package skylink.mglcreche.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import skylink.mglcreche.bdutil.ConnectionDB;
import skylink.mglcreche.modelo.Municipio;
import skylink.mglcreche.modelo.ResponsavelBuscaAluno;
import skylink.mglcreche.modelo.Sexo;

/**
 *
 * @author Henriques
 */
public class ResponsavelBuscaAlunoDAO implements Serializable {
private static final String INSERT = "INSERT INTO responsavel_busca_aluno (nome_responsavel, sobrenome_responsavel, data_nascimento_responsavel, casa_responsavel, rua_responsavel, bairro_responsavel, telefone_responsavel, email_responsavel, id_sexo, id_municipio, data_registo_responsavel) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
private static final String UPDATE = "UPDATE responsavel_busca_aluno SET nome_responsavel = ?, sobrenome_responsavel = ?, data_nascimento_responsavel = ?, casa_responsavel = ?, rua_responsavel = ?, bairro_responsavel = ?, telefone_responsavel = ?, email_responsavel = ?, id_sexo = ?, id_municipio = ?, data_registo_responsavel = ? WHERE id_responsavel = ?";
private static final String DELETE = "DELETE FROM responsavel_busca_aluno WHERE id_responsavel = ?";

private static final String SELECT_BASE = "SELECT r.*, s.descricao_sexo, m.nome_municipio FROM responsavel_busca_aluno r LEFT JOIN sexo s ON r.id_sexo = s.id_sexo LEFT JOIN municipio m ON r.id_municipio = m.id_municipio ";
private static final String SELECT_ALL = SELECT_BASE + "ORDER BY r.nome_responsavel";
private static final String SELECT_BY_ID = SELECT_BASE + "WHERE r.id_responsavel = ?";
private static final String SELECT_BY_PARAMETER = SELECT_BASE + "WHERE r.nome_responsavel LIKE ? OR r.sobrenome_responsavel LIKE ? OR r.telefone_responsavel LIKE ? ORDER BY r.nome_responsavel";

    public List<ResponsavelBuscaAluno> buscar(String nome) throws SQLException {
        if (nome == null || nome.trim().isEmpty()) {
            return findAll();
        }

        List<ResponsavelBuscaAluno> lista = new ArrayList<>();
        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(SELECT_BY_PARAMETER)) {
            
            String padrao = "%" + nome.trim() + "%";
            ps.setString(1, padrao);
            ps.setString(2, padrao);
            ps.setString(3, padrao);
            
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    ResponsavelBuscaAluno r = new ResponsavelBuscaAluno();
                    popularDados(r, rs);
                    lista.add(r);
                }
            }
        }
        return lista;
    }

    public boolean save(ResponsavelBuscaAluno r) throws SQLException {
        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(INSERT)) {
            
            ps.setString(1, r.getNomeResponsavel());
            ps.setString(2, r.getSobrenomeResponsavel());

            if (r.getDataNascimentoResponsavel() != null) {
                ps.setDate(3, new java.sql.Date(r.getDataNascimentoResponsavel().getTime()));
            } else {
                ps.setNull(3, java.sql.Types.DATE);
            }

            ps.setString(4, r.getCasaResponsavel());
            ps.setString(5, r.getRuaResponsavel());
            ps.setString(6, r.getBairroResponsavel());
            ps.setString(7, r.getTelefoneResponsavel());
            ps.setString(8, r.getEmailResponsavel());

            if (r.getSexo() != null && r.getSexo().getIdSexo() != null) {
                ps.setInt(9, r.getSexo().getIdSexo());
            } else {
                ps.setNull(9, java.sql.Types.INTEGER);
            }

            if (r.getMunicipio() != null && r.getMunicipio().getIdMunicipio() != null) {
                ps.setInt(10, r.getMunicipio().getIdMunicipio());
            } else {
                ps.setNull(10, java.sql.Types.INTEGER);
            }

            ps.setTimestamp(11, new java.sql.Timestamp(System.currentTimeMillis()));

            return ps.executeUpdate() > 0;
        }
    }

    public boolean update(ResponsavelBuscaAluno r) throws SQLException {
        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(UPDATE)) {
            
            ps.setString(1, r.getNomeResponsavel());
            ps.setString(2, r.getSobrenomeResponsavel());

            if (r.getDataNascimentoResponsavel() != null) {
                ps.setDate(3, new java.sql.Date(r.getDataNascimentoResponsavel().getTime()));
            } else {
                ps.setNull(3, java.sql.Types.DATE);
            }

            ps.setString(4, r.getCasaResponsavel());
            ps.setString(5, r.getRuaResponsavel());
            ps.setString(6, r.getBairroResponsavel());
            ps.setString(7, r.getTelefoneResponsavel());
            ps.setString(8, r.getEmailResponsavel());

            if (r.getSexo() != null && r.getSexo().getIdSexo() != null) {
                ps.setInt(9, r.getSexo().getIdSexo());
            } else {
                ps.setNull(9, java.sql.Types.INTEGER);
            }

            if (r.getMunicipio() != null && r.getMunicipio().getIdMunicipio() != null) {
                ps.setInt(10, r.getMunicipio().getIdMunicipio());
            } else {
                ps.setNull(10, java.sql.Types.INTEGER);
            }

            ps.setTimestamp(11, new java.sql.Timestamp(System.currentTimeMillis()));
            ps.setInt(12, r.getIdResponsavel());

            return ps.executeUpdate() > 0;
        }
    }

    public boolean delete(ResponsavelBuscaAluno r) throws SQLException {
        if (r == null || r.getIdResponsavel() == null) {
            return false;
        }
        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(DELETE)) {
            
            ps.setInt(1, r.getIdResponsavel());
            return ps.executeUpdate() > 0;
        }
    }

    public List<ResponsavelBuscaAluno> findAll() throws SQLException {
        List<ResponsavelBuscaAluno> lista = new ArrayList<>();
        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(SELECT_ALL);
             ResultSet rs = ps.executeQuery()) {
            
            while (rs.next()) {
                ResponsavelBuscaAluno r = new ResponsavelBuscaAluno();
                popularDados(r, rs);
                lista.add(r);
            }
        }
        return lista;
    }

    public ResponsavelBuscaAluno findById(Integer id) throws SQLException {
        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(SELECT_BY_ID)) {
            
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    ResponsavelBuscaAluno r = new ResponsavelBuscaAluno();
                    popularDados(r, rs);
                    return r;
                }
            }
        }
        return null;
    }

    private void popularDados(ResponsavelBuscaAluno r, ResultSet rs) throws SQLException {
        r.setIdResponsavel(rs.getInt("id_responsavel"));
        r.setNomeResponsavel(rs.getString("nome_responsavel"));
        r.setSobrenomeResponsavel(rs.getString("sobrenome_responsavel"));
        r.setDataNascimentoResponsavel(rs.getDate("data_nascimento_responsavel"));
        r.setCasaResponsavel(rs.getString("casa_responsavel"));
        r.setRuaResponsavel(rs.getString("rua_responsavel"));
        r.setBairroResponsavel(rs.getString("bairro_responsavel"));
        r.setTelefoneResponsavel(rs.getString("telefone_responsavel"));
        r.setEmailResponsavel(rs.getString("email_responsavel"));
        r.setDataRegistoResponsavel(rs.getTimestamp("data_registo_responsavel"));

        int idSexo = rs.getInt("id_sexo");
        if (!rs.wasNull()) {
            Sexo sexo = new Sexo();
            sexo.setIdSexo(idSexo);
            sexo.setDescricaoSexo(rs.getString("descricao_sexo"));
            r.setSexo(sexo);
        }

        int idMunicipio = rs.getInt("id_municipio");
        if (!rs.wasNull()) {
            Municipio municipio = new Municipio();
            municipio.setIdMunicipio(idMunicipio);
            municipio.setNomeMunicipio(rs.getString("nome_municipio"));
            r.setMunicipio(municipio);
        }
    }
}