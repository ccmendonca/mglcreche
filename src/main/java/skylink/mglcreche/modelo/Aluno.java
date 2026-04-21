
package skylink.mglcreche.modelo;


import java.io.Serializable;
import java.util.Date;

import java.util.Objects;


public class Aluno implements Serializable{
    
 
    private static final long serialVersionUID = 1L;
    
    private Integer idAluno;
    private String nomeAluno;
    private String sobrenomeAluno;
    private Date dataNascimentoAluno;
    private String grauSanguineoAluno;
    private String casaAluno;
    private String ruaAluno;
    private String bairroAluno;
    private String nomeMaeAluno;
    private String sobrenomeMaeAluno;
    private String telefoneMaeAluno;
    private String nomePaiAluno;
    private String sobrenomePaiAluno;
    private String telefonePaiAluno;
    private Date dataRegistoAluno;
    private Sexo sexo;
    private Municipio municipio;

    public Integer getIdAluno() {
        return idAluno;
    }

    public void setIdAluno(Integer idAluno) {
        this.idAluno = idAluno;
    }

    public String getNomeAluno() {
        return nomeAluno;
    }

    public void setNomeAluno(String nomeAluno) {
        this.nomeAluno = nomeAluno;
    }

    public String getSobrenomeAluno() {
        return sobrenomeAluno;
    }

    public void setSobrenomeAluno(String sobrenomeAluno) {
        this.sobrenomeAluno = sobrenomeAluno;
    }

    public Date getDataNascimentoAluno() {
        return dataNascimentoAluno;
    }

    public void setDataNascimentoAluno(Date dataNascimentoAluno) {
        this.dataNascimentoAluno = dataNascimentoAluno;
    }

    public String getGrauSanguineoAluno() {
        return grauSanguineoAluno;
    }

    public void setGrauSanguineoAluno(String grauSanguineoAluno) {
        this.grauSanguineoAluno = grauSanguineoAluno;
    }

    public String getCasaAluno() {
        return casaAluno;
    }

    public void setCasaAluno(String casaAluno) {
        this.casaAluno = casaAluno;
    }

    public String getRuaAluno() {
        return ruaAluno;
    }

    public void setRuaAluno(String ruaAluno) {
        this.ruaAluno = ruaAluno;
    }

    public String getBairroAluno() {
        return bairroAluno;
    }

    public void setBairroAluno(String bairroAluno) {
        this.bairroAluno = bairroAluno;
    }

    public String getNomeMaeAluno() {
        return nomeMaeAluno;
    }

    public void setNomeMaeAluno(String nomeMaeAluno) {
        this.nomeMaeAluno = nomeMaeAluno;
    }

    public String getSobrenomeMaeAluno() {
        return sobrenomeMaeAluno;
    }

    public void setSobrenomeMaeAluno(String sobrenomeMaeAluno) {
        this.sobrenomeMaeAluno = sobrenomeMaeAluno;
    }

    public String getTelefoneMaeAluno() {
        return telefoneMaeAluno;
    }

    public void setTelefoneMaeAluno(String telefoneMaeAluno) {
        this.telefoneMaeAluno = telefoneMaeAluno;
    }

    public String getNomePaiAluno() {
        return nomePaiAluno;
    }

    public void setNomePaiAluno(String nomePaiAluno) {
        this.nomePaiAluno = nomePaiAluno;
    }

    public String getSobrenomePaiAluno() {
        return sobrenomePaiAluno;
    }

    public void setSobrenomePaiAluno(String sobrenomePaiAluno) {
        this.sobrenomePaiAluno = sobrenomePaiAluno;
    }

    public String getTelefonePaiAluno() {
        return telefonePaiAluno;
    }

    public void setTelefonePaiAluno(String telefonePaiAluno) {
        this.telefonePaiAluno = telefonePaiAluno;
    }

    public Date getDataRegistoAluno() {
        return dataRegistoAluno;
    }

    public void setDataRegistoAluno(Date dataRegistoAluno) {
        this.dataRegistoAluno = dataRegistoAluno;
    }

    public Sexo getSexo() {
        return sexo;
    }

    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
    }

    public Municipio getMunicipio() {
        return municipio;
    }

    public void setMunicipio(Municipio municipio) {
        this.municipio = municipio;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.idAluno);
        hash = 89 * hash + Objects.hashCode(this.sexo);
        hash = 89 * hash + Objects.hashCode(this.municipio);
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
        final Aluno other = (Aluno) obj;
        if (!Objects.equals(this.idAluno, other.idAluno)) {
            return false;
        }
        if (!Objects.equals(this.sexo, other.sexo)) {
            return false;
        }
        return Objects.equals(this.municipio, other.municipio);
    }

    @Override
    public String toString() {
        return "Aluno{" + "idAluno=" + idAluno + ", nomeAluno=" + nomeAluno + ", sobrenomeAluno=" + sobrenomeAluno + ", dataNascimentoAluno=" + dataNascimentoAluno + ", grauSanguineoAluno=" + grauSanguineoAluno + ", casaAluno=" + casaAluno + ", ruaAluno=" + ruaAluno + ", bairroAluno=" + bairroAluno + ", nomeMaeAluno=" + nomeMaeAluno + ", sobrenomeMaeAluno=" + sobrenomeMaeAluno + ", telefoneMaeAluno=" + telefoneMaeAluno + ", nomePaiAluno=" + nomePaiAluno + ", sobrenomePaiAluno=" + sobrenomePaiAluno + ", telefonePaiAluno=" + telefonePaiAluno + ", dataRegistoAluno=" + dataRegistoAluno + ", sexo=" + sexo + ", municipio=" + municipio + '}';
    }
    
}
