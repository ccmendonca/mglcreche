
package skylink.mglcreche.modelo;

import java.util.Objects;


public class GrupoSanguineo {
    private Integer idGrupoSanguineo;
    private String descricaoGrupoSanguineo;

    public GrupoSanguineo() {
    }

    public GrupoSanguineo(Integer idGrupoSanguineo, String descricaoGrupoSanguineo) {
        this.idGrupoSanguineo = idGrupoSanguineo;
        this.descricaoGrupoSanguineo = descricaoGrupoSanguineo;
    }

    public Integer getIdGrupoSanguineo() {
        return idGrupoSanguineo;
    }

    public void setIdGrupoSanguineo(Integer idGrupoSanguineo) {
        this.idGrupoSanguineo = idGrupoSanguineo;
    }

    public String getDescricaoGrupoSanguineo() {
        return descricaoGrupoSanguineo;
    }

    public void setDescricaoGrupoSanguineo(String descricaoGrupoSanguineo) {
        this.descricaoGrupoSanguineo = descricaoGrupoSanguineo;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.idGrupoSanguineo);
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
        final GrupoSanguineo other = (GrupoSanguineo) obj;
        return Objects.equals(this.idGrupoSanguineo, other.idGrupoSanguineo);
    }

    
       @Override
    public String toString() {
           return String.format("%s[idGrupoSanguineo=%d]", getClass().getSimpleName(), getIdGrupoSanguineo());
    }
    
   
}
