package skylink.mglcreche.modelo;

import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;

/**
 * @Henriques
 */
public class ResponsavelBuscaAluno implements Serializable {

    private static final long serialVersionUID = 1L;

   
    private Integer id_Responsavel;
    private String nome_Responsavel;
    private String sobrenome_Responsavel;
    private Date data_Nascimento_Responsavel;
    private String casa_Responsavel;
    private String rua_Responsavel;
    private String bairro_Responsavel;
    private String telefone_Responsavel;
    private Date data_Registo_Responsavel;

    public ResponsavelBuscaAluno() {
    }

    public ResponsavelBuscaAluno(Integer id_Responsavel, String nome_Responsavel, String sobrenome_Responsavel, Date data_Nascimento_Responsavel, String casa_Responsavel, String rua_Responsavel, String bairro_Responsavel, String telefone_Responsavel, Date data_Registo_Responsavel) {
        this.id_Responsavel = id_Responsavel;
        this.nome_Responsavel = nome_Responsavel;
        this.sobrenome_Responsavel = sobrenome_Responsavel;
        this.data_Nascimento_Responsavel = data_Nascimento_Responsavel;
        this.casa_Responsavel = casa_Responsavel;
        this.rua_Responsavel = rua_Responsavel;
        this.bairro_Responsavel = bairro_Responsavel;
        this.telefone_Responsavel = telefone_Responsavel;
        this.data_Registo_Responsavel = data_Registo_Responsavel;
    }

    

    public Integer getIdResponsavel() {
        return id_Responsavel;
    }

    public void setIdResponsavel(Integer id_Responsavel) {
        this.id_Responsavel = id_Responsavel;
    }

    public String getNomeResponsavel() {
        return nome_Responsavel;
    }

    public void setNomeResponsavel(String nome_Responsavel) {
        this.nome_Responsavel = nome_Responsavel;
    }

    public String getSobrenomeResponsavel() {
        return sobrenome_Responsavel;
    }

    public void setSobrenomeResponsavel(String sobrenome_Responsavel) {
        this.sobrenome_Responsavel = sobrenome_Responsavel;
    }

    public Date getDataNascimentoResponsavel() {
        return data_Nascimento_Responsavel;
    }

    public void setDataNascimentoResponsavel(Date data_Nascimento_Responsavel) {
        this.data_Nascimento_Responsavel = data_Nascimento_Responsavel;
    }

    public String getCasaResponsavel() {
        return casa_Responsavel;
    }

    public void setCasaResponsavel(String casa_Responsavel) {
        this.casa_Responsavel = casa_Responsavel;
    }

    public String getRuaResponsavel() {
        return rua_Responsavel;
    }

    public void setRuaResponsavel(String rua_Responsavel) {
        this.rua_Responsavel = rua_Responsavel;
    }

    public String getBairroResponsavel() {
        return bairro_Responsavel;
    }

    public void setBairroResponsavel(String bairro_Responsavel) {
        this.bairro_Responsavel = bairro_Responsavel;
    }

    public String getTelefoneResponsavel() {
        return telefone_Responsavel;
    }

    public void setTelefoneResponsavel(String telefone_Responsavel) {
        this.telefone_Responsavel = telefone_Responsavel;
    }

    public Date getDataRegistoResponsavel() {
        return data_Registo_Responsavel;
    }

    public void setDataRegistoResponsavel(Date data_Registo_Responsavel) {
        this.data_Registo_Responsavel = data_Registo_Responsavel;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        ResponsavelBuscaAluno other = (ResponsavelBuscaAluno) obj;
        return Objects.equals(id_Responsavel, other.id_Responsavel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_Responsavel);
    }
}