package skylink.mglcreche.modelo;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import skylink.mglcreche.modelo.Sexo;
/**
 *
 * @Henriques
 */
public class ResponsavelBuscaAluno implements Serializable {

    private Integer idResponsavel;
    private String nomeResponsavel;
    private String sobrenomeResponsavel;
    private Date dataNascimentoResponsavel;
    private String casaResponsavel;
    private String ruaResponsavel;
    private String bairroResponsavel;
    private Integer idMunicipio;
    private String telefoneResponsavel;
    private Date dataRegistoResponsavel;
    private String sexoResponsavel;
    private Integer idSexo;
    private Sexo sexo;

    public ResponsavelBuscaAluno() {
    }

    public ResponsavelBuscaAluno(Integer idResponsavel, String nomeResponsavel,
            String sobrenomeResponsavel, Date dataNascimentoResponsavel,
            String casaResponsavel, String ruaResponsavel,
            String bairroResponsavel, Integer idMunicipio, String telefoneResponsavel,
            Date dataRegistoResponsavel, String sexoResponsavel, Integer idSexo) {

        this.idResponsavel = idResponsavel;
        this.nomeResponsavel = nomeResponsavel;
        this.sobrenomeResponsavel = sobrenomeResponsavel;
        this.dataNascimentoResponsavel = dataNascimentoResponsavel;
        this.casaResponsavel = casaResponsavel;
        this.ruaResponsavel = ruaResponsavel;
        this.bairroResponsavel = bairroResponsavel;
        this.idMunicipio = idMunicipio;
        this.telefoneResponsavel = telefoneResponsavel;
        this.dataRegistoResponsavel = dataRegistoResponsavel;
        this.sexoResponsavel = sexoResponsavel;
        this.idSexo = idSexo;
    }

    public Integer getIdResponsavel() { return idResponsavel; }
    public void setIdResponsavel(Integer idResponsavel) { this.idResponsavel = idResponsavel; }

    public String getNomeResponsavel() { return nomeResponsavel; }
    public void setNomeResponsavel(String nomeResponsavel) { this.nomeResponsavel = nomeResponsavel; }

    public String getSobrenomeResponsavel() { return sobrenomeResponsavel; }
    public void setSobrenomeResponsavel(String sobrenomeResponsavel) { this.sobrenomeResponsavel = sobrenomeResponsavel; }

    public Date getDataNascimentoResponsavel() { return dataNascimentoResponsavel; }
    public void setDataNascimentoResponsavel(Date dataNascimentoResponsavel) { this.dataNascimentoResponsavel = dataNascimentoResponsavel; }

    public String getCasaResponsavel() { return casaResponsavel; }
    public void setCasaResponsavel(String casaResponsavel) { this.casaResponsavel = casaResponsavel; }

    public String getRuaResponsavel() { return ruaResponsavel; }
    public void setRuaResponsavel(String ruaResponsavel) { this.ruaResponsavel = ruaResponsavel; }

    public String getBairroResponsavel() { return bairroResponsavel; }
    public void setBairroResponsavel(String bairroResponsavel) { this.bairroResponsavel = bairroResponsavel; }

    public Integer getIdMunicipio() { return idMunicipio; }
    public void setIdMunicipio(Integer idMunicipio) { this.idMunicipio = idMunicipio; }

    public String getTelefoneResponsavel() { return telefoneResponsavel; }
    public void setTelefoneResponsavel(String telefoneResponsavel) { this.telefoneResponsavel = telefoneResponsavel; }

    public Date getDataRegistoResponsavel() { return dataRegistoResponsavel; }
    public void setDataRegistoResponsavel(Date dataRegistoResponsavel) { this.dataRegistoResponsavel = dataRegistoResponsavel; }

    public String getSexoResponsavel() { return sexoResponsavel; }
    public void setSexoResponsavel(String sexoResponsavel) { this.sexoResponsavel = sexoResponsavel; }

    public Integer getIdSexo() { return idSexo; }
    public void setIdSexo(Integer idSexo) { this.idSexo = idSexo; }

    public Sexo getSexo() { return sexo; }
    public void setSexo(Sexo sexo) { this.sexo = sexo; }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.idResponsavel);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        final ResponsavelBuscaAluno other = (ResponsavelBuscaAluno) obj;
        return Objects.equals(this.idResponsavel, other.idResponsavel);
    }

    @Override
    public String toString() {
        return "ResponsavelBuscaAluno{" + "idResponsavel=" + idResponsavel + ", nomeResponsavel=" + nomeResponsavel + ", idMunicipio=" + idMunicipio + '}';
    }
}