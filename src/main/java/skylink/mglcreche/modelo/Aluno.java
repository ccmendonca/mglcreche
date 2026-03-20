
package skylink.mglcreche.modelo;

import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;

/**
 * «claudiomendonca»
 */
public class Aluno implements Serializable{
    
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

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 73 * hash + Objects.hashCode(this.idAluno);
        hash = 73 * hash + Objects.hashCode(this.nomeAluno);
        hash = 73 * hash + Objects.hashCode(this.sobrenomeAluno);
        hash = 73 * hash + Objects.hashCode(this.dataNascimentoAluno);
        hash = 73 * hash + Objects.hashCode(this.grauSanguineoAluno);
        hash = 73 * hash + Objects.hashCode(this.casaAluno);
        hash = 73 * hash + Objects.hashCode(this.ruaAluno);
        hash = 73 * hash + Objects.hashCode(this.bairroAluno);
        hash = 73 * hash + Objects.hashCode(this.nomeMaeAluno);
        hash = 73 * hash + Objects.hashCode(this.sobrenomeMaeAluno);
        hash = 73 * hash + Objects.hashCode(this.telefoneMaeAluno);
        hash = 73 * hash + Objects.hashCode(this.nomePaiAluno);
        hash = 73 * hash + Objects.hashCode(this.sobrenomePaiAluno);
        hash = 73 * hash + Objects.hashCode(this.telefonePaiAluno);
        hash = 73 * hash + Objects.hashCode(this.dataRegistoAluno);
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
        if (!Objects.equals(this.nomeAluno, other.nomeAluno)) {
            return false;
        }
        if (!Objects.equals(this.sobrenomeAluno, other.sobrenomeAluno)) {
            return false;
        }
        if (!Objects.equals(this.grauSanguineoAluno, other.grauSanguineoAluno)) {
            return false;
        }
        if (!Objects.equals(this.casaAluno, other.casaAluno)) {
            return false;
        }
        if (!Objects.equals(this.ruaAluno, other.ruaAluno)) {
            return false;
        }
        if (!Objects.equals(this.bairroAluno, other.bairroAluno)) {
            return false;
        }
        if (!Objects.equals(this.nomeMaeAluno, other.nomeMaeAluno)) {
            return false;
        }
        if (!Objects.equals(this.sobrenomeMaeAluno, other.sobrenomeMaeAluno)) {
            return false;
        }
        if (!Objects.equals(this.telefoneMaeAluno, other.telefoneMaeAluno)) {
            return false;
        }
        if (!Objects.equals(this.nomePaiAluno, other.nomePaiAluno)) {
            return false;
        }
        if (!Objects.equals(this.sobrenomePaiAluno, other.sobrenomePaiAluno)) {
            return false;
        }
        if (!Objects.equals(this.telefonePaiAluno, other.telefonePaiAluno)) {
            return false;
        }
        if (!Objects.equals(this.idAluno, other.idAluno)) {
            return false;
        }
        if (!Objects.equals(this.dataNascimentoAluno, other.dataNascimentoAluno)) {
            return false;
        }
        return Objects.equals(this.dataRegistoAluno, other.dataRegistoAluno);
    }

    @Override
    public String toString() {
        return "Aluno{" + "idAluno=" + idAluno + ", nomeAluno=" + nomeAluno + ", sobrenomeAluno=" + sobrenomeAluno + ", dataNascimentoAluno=" + dataNascimentoAluno + ", grauSanguineoAluno=" + grauSanguineoAluno + ", casaAluno=" + casaAluno + ", ruaAluno=" + ruaAluno + ", bairroAluno=" + bairroAluno + ", nomeMaeAluno=" + nomeMaeAluno + ", sobrenomeMaeAluno=" + sobrenomeMaeAluno + ", telefoneMaeAluno=" + telefoneMaeAluno + ", nomePaiAluno=" + nomePaiAluno + ", sobrenomePaiAluno=" + sobrenomePaiAluno + ", telefonePaiAluno=" + telefonePaiAluno + ", dataRegistoAluno=" + dataRegistoAluno + '}';
    }
    
    
}
