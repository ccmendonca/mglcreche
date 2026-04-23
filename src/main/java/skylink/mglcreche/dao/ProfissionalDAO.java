
package skylink.mglcreche.dao;

import java.lang.invoke.MethodHandles;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import skylink.mglcreche.bdutil.ConnectionDB;
import skylink.mglcreche.modelo.CategoriaProfissional;
import skylink.mglcreche.modelo.Municipio;
import skylink.mglcreche.modelo.Profissional;
import skylink.mglcreche.modelo.Sexo;

/**
 * «claudiomendonca
 */

public class ProfissionalDAO {
    
    private static final Logger LOG = Logger.getLogger(MethodHandles.lookup().lookupClass().getName());
    private static final String INSERT = "INSERT INTO profissional (bi_profissional, nome_profissional, sobrenome_profissional, dataNascimento_profissional, numeroCasa_profissional, rua_profissional, bairro_profissional, distrito_profissional, telefoneUnitel_profissional, telefoneMovicel_profissional, telefoneAfricell_profissional, telefoneFixo_profissional, email_profissional, habilitacoesLiterarias_profissional, observacoes_profissional, id_sexo, id_municipio, id_categoriaprofissional) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    private static final String UPDATE = "UPDATE profissional SET id_profissional=?, bi_profissional=?, nome_profissional=?, sobrenome_profissional=?, dataNascimento_profissional=?, sexo_profissional=?, numeroCasa_profissional=?, rua_profissional=?, bairro_profissional=?, distrito_profissional=?, telefoneUnitel_profissional=?, telefoneMovicel_profissional=?, telefoneAfricell_profissional=?, telefoneFixo_profissional=?, email_profissional=?, habilitacoesLiterarias_profissional=?, observacoes_profissional=?, id_sexo=?, id_municipio=?, id_categoriaprofissional=? where id_profissional = ?";
    private static final String DELETE = "DELETE FROM profissional WHERE id_profissional = ?";
    private static final String SELECT_ALL = "SELECT * FROM profissional p INNER JOIN sexo s on p.id_sexo=s.id_sexo INNER JOIN municipio m on p.id_municipio=m.id_municipio INNER JOIN categoria_profissional cp on p.id_categoriaprofissional";
    private static final String SELECT_BY_ID = "SELECT * FROM profissional WHERE id_profissional = ?";
    private static final String SELECT_BY_NAME_SURNAME = "SELECT id_profissional, bi_profissional, nome_profissional, sobrenome_profissional, dataNascimento_profissional, descricao_sexo, nome_municipio, descricao_categoriaprofissional"
            + " FROM profissional p INNER JOIN sexo s on p.id_sexo=s.id_sexo INNER JOIN municipio m on p.id_municipio = m.id_municipio INNER JOIN categoria_profissional cp on p.id_categoriaprofissional = cp.id_categoriaprofissional"
            + " WHERE id_profissional LIKE ? "
            + " OR bi_profissional LIKE ? "
            + " OR nome_profissional LIKE ? "
            + " OR sobrenome_profissional LIKE ?";

    /**
     * Variáveis de acesso... 'Use estas variáveis para conectar-se à base de
     * dados preparar o método de inserção, mostrando a lista dos dados em uma
     * página...
     */
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    boolean verifique = false;

   
    public boolean save(Profissional profissional) {
        System.out.println("Cadatro do profissional: " + profissional);
        try {
            conn = ConnectionDB.getConnection();
            ps = conn.prepareStatement(INSERT);
            ps.setString(1, profissional.getNumeroContribuinte());
            ps.setString(2, profissional.getNome());
            ps.setString(3, profissional.getSobrenome());
            ps.setInt(4, profissional.getSexo().getIdSexo());
            ps.setDate(5, new java.sql.Date(profissional.getDataNascimento().getTime()));
            ps.setString(6, profissional.getNumeroCasa());
            ps.setString(7, profissional.getRua());
            ps.setString(8, profissional.getBairro());
            ps.setString(9, profissional.getDistrito());
            ps.setString(10, profissional.getTelefoneUnitel());
            ps.setString(11, profissional.getTelefoneMovicel());
            ps.setString(12, profissional.getTelefoneAfricell());
            ps.setString(13, profissional.getTelefoneFixo());
            ps.setString(14, profissional.getEmail());
            ps.setString(15, profissional.getHabilitacoesLiterarias());
            ps.setString(16, profissional.getObservacoes());
            ps.setInt(17, profissional.getMunicipio().getIdMunicipio());
            ps.setInt(18, profissional.getCategoriaprofissional().getCodigo());

            int p = ps.executeUpdate();
            if (p > 0) {
                System.out.println("Dados inseridos com sucesso!" + ps.getUpdateCount());
                verifique = true;
            }
            return verifique;

        } catch (SQLException ex) {
            String msg = ex.getLocalizedMessage();
            LOG.log(Level.SEVERE, ex, () -> "Erro de inserção de dados: {0}" + msg);
            return false;
        }

    }

    //Método usado para actualizar os dados da tabela profissional
    public boolean update(Profissional profissional) {
        try {
            conn = ConnectionDB.getConnection();
            ps = conn.prepareStatement(UPDATE);
            ps.setInt(1, profissional.getCodigo());
            ps.setString(2, profissional.getNumeroContribuinte());
            ps.setString(3, profissional.getNome());
            ps.setString(4, profissional.getSobrenome());
            ps.setDate(5, new java.sql.Date(profissional.getDataNascimento().getTime()));
            ps.setInt(6, profissional.getSexo().getIdSexo());
            ps.setString(7, profissional.getNumeroCasa());
            ps.setString(8, profissional.getRua());
            ps.setString(9, profissional.getBairro());
            ps.setString(10, profissional.getDistrito());
            ps.setString(11, profissional.getTelefoneUnitel());
            ps.setString(12, profissional.getTelefoneMovicel());
            ps.setString(13, profissional.getTelefoneAfricell());
            ps.setString(14, profissional.getTelefoneFixo());
            ps.setString(15, profissional.getEmail());
            ps.setString(16, profissional.getHabilitacoesLiterarias());
            ps.setString(17, profissional.getObservacoes());
            ps.setInt(18, profissional.getMunicipio().getIdMunicipio());
            ps.setInt(19, profissional.getCategoriaprofissional().getCodigo());
            ps.setInt(20, profissional.getCodigo());

            int execute = ps.executeUpdate();
            if (execute > 0) {
                System.out.println("Dados actualizados com sucesso" + ps.getUpdateCount());
                verifique = true;
            }
        } catch (SQLException ex) {
            String msg = ex.getLocalizedMessage();
            LOG.log(Level.SEVERE, ex, () -> "Erro ao actualiazar dados do profissional." + msg);
        }
        return false;

    }

    /**
     * Mostrar os dados do banco de dados
     */
    public List<Profissional> findAll() {
        List<Profissional> profissionais = new ArrayList<>();
        try {
            conn = ConnectionDB.getConnection();
            ps = conn.prepareStatement(SELECT_ALL);
            rs = ps.executeQuery();
            while (rs.next()) {
                Profissional profissional = new Profissional();
                dados(profissional, rs);
                profissionais.add(profissional);
            }
        } catch (SQLException ex) {
            String msg = ex.getLocalizedMessage();
            LOG.log(Level.SEVERE, ex, () -> "Erro ao carregar dados" + msg);
        }
        return profissionais;

    }

    /**
     * pesquisa aleatória ao banco de dados
     */
    public List<Profissional> aleatory(Object especifily) {
        List<Profissional> profissionais = new ArrayList<>();
        try {
            conn = ConnectionDB.getConnection();
            String padron = "%" + especifily + "%";
            ps = conn.prepareStatement(SELECT_BY_NAME_SURNAME);
            ps.setObject(1, padron);
            ps.setObject(2, padron);
            ps.setObject(3, padron);
            ps.setObject(4, padron);
            rs = ps.executeQuery();
            while (rs.next()) {
                Profissional profissional = new Profissional();
                popular(profissional, rs);
                profissionais.add(profissional);
            }

        } catch (SQLException ex) {
            String alert = ex.getLocalizedMessage();
            LOG.log(Level.SEVERE, ex, () -> "Erro ao carregar dados" + alert);
        }
        return profissionais;
    }

    //carregar dados                                                
    public void dados(Profissional prof, ResultSet rs){
        try {
            Logger.getGlobal().log(Level.FINE, "Profissional: {0}", prof);
            prof.setCodigo(rs.getInt("id_profissional"));
            prof.setNumeroContribuinte(rs.getString("bi_profissional"));
            prof.setNome(rs.getString("nome_profissional"));
            prof.setSobrenome(rs.getString("sobrenome_profissional"));
            Sexo sexo = new Sexo();
            sexo.setDescricaoSexo(rs.getString("descricao_sexo"));
            prof.setSexo(sexo);
            prof.setDataNascimento(rs.getDate("dataNascimento_profissional"));
            prof.setNumeroCasa(rs.getString("numeroCasa_profissional"));
            prof.setRua(rs.getString("rua_profissional"));
            prof.setBairro(rs.getString("bairro_profissional"));
            prof.setDistrito(rs.getString("distrito_profissional"));
            prof.setTelefoneUnitel(rs.getString("telefoneUnitel_profissional"));
            prof.setTelefoneMovicel(rs.getString("telefoneMovicel_profissional"));
            prof.setTelefoneAfricell(rs.getString("telefoneAfricell_profissional"));
            prof.setTelefoneFixo(rs.getString("telefoneFixo_profissional"));
            prof.setEmail(rs.getString("email_profissional"));
            prof.setHabilitacoesLiterarias(rs.getString("habilitacoesliterarias_profissional"));
            prof.setObservacoes(rs.getString("observacoes_profissional"));
            Municipio municipio = new Municipio();
            municipio.setNomeMunicipio(rs.getString("nome_municipio"));
            prof.setMunicipio(municipio);
            CategoriaProfissional catProfissional = new CategoriaProfissional();
            catProfissional.setDescricao(rs.getString("descricao_categoriaprofissional"));
            prof.setCategoriaprofissional(catProfissional);
        } catch (SQLException e) {
            String infor = e.getLocalizedMessage();
            LOG.log(Level.SEVERE, e, () -> "Erro de leitura de dados" + infor);
        }
    }

    public void popular(Profissional p, ResultSet rs) {
        try {
            p.setCodigo(rs.getInt("id_profissional"));
            p.setNumeroContribuinte(rs.getString("bi_profissional"));
            p.setNome(rs.getString("nome_profissional"));
            p.setSobrenome(rs.getString("sobrenome_profissional"));
            p.setDataNascimento(rs.getDate("dataNascimento_profissional"));
            Sexo sexo = new Sexo();
            sexo.setDescricaoSexo(rs.getString("descricao_sexo"));
            p.setSexo(sexo);
            Municipio municipio = new Municipio();
            municipio.setNomeMunicipio(rs.getString("nome_municipio"));
            p.setMunicipio(municipio);
            CategoriaProfissional catProfissional = new CategoriaProfissional();
            catProfissional.setDescricao(rs.getString("descricao_categoriaprofissional"));
            p.setCategoriaprofissional(catProfissional);

        } catch (SQLException e) {
            String infor = e.getLocalizedMessage();
            LOG.log(Level.SEVERE, e, () -> "Erro de leitura de dados" + infor);
        }
    }

}
