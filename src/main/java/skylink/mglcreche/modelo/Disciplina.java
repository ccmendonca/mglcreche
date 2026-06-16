
package skylink.mglcreche.modelo;

import java.util.Objects;

public class Disciplina {
    private Integer idDisciplina;
    private String descricaoDisciplina;
    private String abreviaturaDisciplina;
    private String cargaHorariaSemanal;
    private EixoCurricularDisciplina eixoCurricularDisciplina;

    public Disciplina() {
    }

    public Disciplina(Integer idDisciplina, String descricaoDisciplina, String abreviaturaDisciplina, String cargaHorariaSemanal, EixoCurricularDisciplina eixoCurricularDisciplina) {
        this.idDisciplina = idDisciplina;
        this.descricaoDisciplina = descricaoDisciplina;
        this.abreviaturaDisciplina = abreviaturaDisciplina;
        this.cargaHorariaSemanal = cargaHorariaSemanal;
        this.eixoCurricularDisciplina = eixoCurricularDisciplina;
    }

    public Integer getIdDisciplina() {
        return idDisciplina;
    }

    public void setIdDisciplina(Integer idDisciplina) {
        this.idDisciplina = idDisciplina;
    }

    public String getDescricaoDisciplina() {
        return descricaoDisciplina;
    }

    public void setDescricaoDisciplina(String descricaoDisciplina) {
        this.descricaoDisciplina = descricaoDisciplina;
    }

    public String getAbreviaturaDisciplina() {
        return abreviaturaDisciplina;
    }

    public void setAbreviaturaDisciplina(String abreviaturaDisciplina) {
        this.abreviaturaDisciplina = abreviaturaDisciplina;
    }

    public String getCargaHorariaSemanal() {
        return cargaHorariaSemanal;
    }

    public void setCargaHorariaSemanal(String cargaHorariaSemanal) {
        this.cargaHorariaSemanal = cargaHorariaSemanal;
    }

    public EixoCurricularDisciplina getEixoCurricularDisciplina() {
        return eixoCurricularDisciplina;
    }

    public void setEixoCurricularDisciplina(EixoCurricularDisciplina eixoCurricularDisciplina) {
        this.eixoCurricularDisciplina = eixoCurricularDisciplina;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + Objects.hashCode(this.idDisciplina);
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
        final Disciplina other = (Disciplina) obj;
        return Objects.equals(this.idDisciplina, other.idDisciplina);
    }

    @Override
    public String toString() {
        return "Disciplina{" + "idDisciplina=" + idDisciplina + '}';
    }
    
    
    
}
