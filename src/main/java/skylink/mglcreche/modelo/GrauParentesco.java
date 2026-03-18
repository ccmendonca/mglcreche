
package skylink.mglcreche.modelo;

import java.io.Serializable;
import java.util.Objects;


public class GrauParentesco implements Serializable{
    
    private Integer idGrauParentesco;
    private String descricaoGrauParentesco;

    public GrauParentesco() {
    }

    public GrauParentesco(Integer idGrauParentesco, String descricaoGrauParentesco) {
        this.idGrauParentesco = idGrauParentesco;
        this.descricaoGrauParentesco = descricaoGrauParentesco;
    }

    public Integer getIdGrauParentesco() {
        return idGrauParentesco;
    }

    public void setIdGrauParentesco(Integer idGrauParentesco) {
        this.idGrauParentesco = idGrauParentesco;
    }

    public String getDescricaoGrauParentesco() {
        return descricaoGrauParentesco;
    }

    public void setDescricaoGrauParentesco(String descricaoGrauParentesco) {
        this.descricaoGrauParentesco = descricaoGrauParentesco;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 13 * hash + Objects.hashCode(this.idGrauParentesco);
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
        final GrauParentesco other = (GrauParentesco) obj;
        return Objects.equals(this.idGrauParentesco, other.idGrauParentesco);
    }
   
    
}
