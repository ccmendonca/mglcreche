
package skylink.mglcreche.modelo;

import java.util.Objects;


public class EixoCurricularDisciplina {
    private Integer idEixoCurricular;
    private String descricaoEixoCurricular;

    public EixoCurricularDisciplina() {
    }

    public EixoCurricularDisciplina(Integer idEixoCurricular, String descricaoEixoCurricular) {
        this.idEixoCurricular = idEixoCurricular;
        this.descricaoEixoCurricular = descricaoEixoCurricular;
    }

    public Integer getIdEixoCurricular() {
        return idEixoCurricular;
    }

    public void setIdEixoCurricular(Integer idEixoCurricular) {
        this.idEixoCurricular = idEixoCurricular;
    }

    public String getDescricaoEixoCurricular() {
        return descricaoEixoCurricular;
    }

    public void setDescricaoEixoCurricular(String descricaoEixoCurricular) {
        this.descricaoEixoCurricular = descricaoEixoCurricular;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 13 * hash + Objects.hashCode(this.idEixoCurricular);
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
        final EixoCurricularDisciplina other = (EixoCurricularDisciplina) obj;
        return Objects.equals(this.idEixoCurricular, other.idEixoCurricular);
    }

    @Override
    public String toString() {
        return "EixoCurricularDisciplina{" + "idEixoCurricular=" + idEixoCurricular + '}';
    }
    
}
