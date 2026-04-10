package skylink.mglcreche.modelo;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.validator.ValidatorException;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
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
    private String municipioResponsavel;             
    private String telefoneResponsavel;              
    private Date dataRegistoResponsavel;             
    private String sexoResponsavel;        
    private Integer idSexo;                         


    private Sexo sexo;

    public ResponsavelBuscaAluno() {
    }

    public ResponsavelBuscaAluno(Integer idResponsavel, String nomeResponsavel,
            String sobrenomeResponsavel, Date dataNascimentoResponsavel,
            String casaResponsavel, String ruaResponsavel, String municipioResponsavel,
            String bairroResponsavel, String telefoneResponsavel,
            Date dataRegistoResponsavel, String sexoResponsavel, Integer idSexo) {

        this.idResponsavel = idResponsavel;
        this.nomeResponsavel = nomeResponsavel;
        this.sobrenomeResponsavel = sobrenomeResponsavel;
        this.dataNascimentoResponsavel = dataNascimentoResponsavel;
        this.casaResponsavel = casaResponsavel;
        this.ruaResponsavel = ruaResponsavel;
        this.municipioResponsavel = municipioResponsavel;
        this.bairroResponsavel = bairroResponsavel;
        this.telefoneResponsavel = telefoneResponsavel;
        this.dataRegistoResponsavel = dataRegistoResponsavel;
        this.sexoResponsavel = sexoResponsavel; 
        this.idSexo = idSexo; 
    }

    public Integer getIdResponsavel() {
        return idResponsavel;
    }

    public void setIdResponsavel(Integer idResponsavel) {
        this.idResponsavel = idResponsavel;
    }

    public String getNomeResponsavel() {
        return nomeResponsavel;
    }

    
    public void setNomeResponsavel(String nomeResponsavel) {
        this.nomeResponsavel = nomeResponsavel;
    }

    public String getSobrenomeResponsavel() {
        return sobrenomeResponsavel;
    }

    public void setSobrenomeResponsavel(String sobrenomeResponsavel) {
        this.sobrenomeResponsavel = sobrenomeResponsavel;
    }

    public void setDataNascimentoResponsavel(Date dataNascimentoResponsavel) {
        this.dataNascimentoResponsavel = dataNascimentoResponsavel;
    }

    public Date getDataNascimentoResponsavel() {
        return dataNascimentoResponsavel;
    }

    public void setDataRegistoResponsavel(Date dataRegistoResponsavel) {
        this.dataRegistoResponsavel = dataRegistoResponsavel;
    }

    public String getMunicipioResponsavel() {
        return municipioResponsavel;
    }

    public void setMunicipioResponsavel(String municipioResponsavel) {
        this.municipioResponsavel = municipioResponsavel;
    }

    public String getBairroResponsavel() {
        return bairroResponsavel;
    }

    
    public void setBairroResponsavel(String bairroResponsavel) {
        this.bairroResponsavel = bairroResponsavel;
    }

    public String getRuaResponsavel() {
        return ruaResponsavel;
    }

    public void setRuaResponsavel(String ruaResponsavel) {
        this.ruaResponsavel = ruaResponsavel;
    }

    public String getCasaResponsavel() {
        return casaResponsavel;
    }

    public void setCasaResponsavel(String casaResponsavel) {
        this.casaResponsavel = casaResponsavel;
    }

    public String getTelefoneResponsavel() {
        return telefoneResponsavel;
    }

    public void setTelefoneResponsavel(String telefoneResponsavel) {
        this.telefoneResponsavel = telefoneResponsavel;
    }

    public String getSexoResponsavel() {
        return sexoResponsavel;
    }

    public void setSexoResponsavel(String sexoResponsavel) {
        this.sexoResponsavel = sexoResponsavel;
    }

    public Integer getIdSexo() {
        return idSexo;
    }

    public void setIdSexo(Integer idSexo) {
        this.idSexo = idSexo;
    }

    public Sexo getSexo() {
        return sexo;
    }

    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
    }
    
    
    


    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.idResponsavel);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ResponsavelBuscaAluno other = (ResponsavelBuscaAluno) obj;
        return Objects.equals(this.idResponsavel, other.idResponsavel);
    }

    @Override
    public String toString() {
        return "ResponsavelBuscaAluno{" + "idResponsavel=" + idResponsavel + ", nomeResponsavel=" + nomeResponsavel + ", sobrenomeResponsavel=" + sobrenomeResponsavel + ", dataNascimentoResponsavel=" + dataNascimentoResponsavel + ", casaResponsavel=" + casaResponsavel + ", ruaResponsavel=" + ruaResponsavel + ", bairroResponsavel=" + bairroResponsavel + ", municipioResponsavel=" + municipioResponsavel + ", telefoneResponsavel=" + telefoneResponsavel + ", dataRegistoResponsavel=" + dataRegistoResponsavel + ", sexoResponsavel=" + sexoResponsavel + ", idSexo=" + idSexo + ", sexo=" + sexo + '}';
    }

    
    
    
}