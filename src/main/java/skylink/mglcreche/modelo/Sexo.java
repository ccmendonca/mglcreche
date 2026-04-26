
package skylink.mglcreche.modelo;

import java.util.Objects;


public class Sexo {
    
    private Integer idSexo;
    private String descricaoSexo;
    
    public Sexo(){
    }

    public Sexo(Integer idSexo, String descricaoSexo) {
        this.idSexo = idSexo;
        this.descricaoSexo = descricaoSexo;
    }

    public Integer getIdSexo() {
        return idSexo;
    }

    public void setIdSexo(Integer idSexo) {
        this.idSexo = idSexo;
    }

    public String getDescricaoSexo() {
        return descricaoSexo;
    }

    public void setDescricaoSexo(String descricaoSexo) {
        this.descricaoSexo = descricaoSexo;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.idSexo);
        hash = 37 * hash + Objects.hashCode(this.descricaoSexo);
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
        final Sexo other = (Sexo) obj;
        return true;
    }

    @Override
    public String toString() {
           return String.format("%s[idSexo=%d]", getClass().getSimpleName(), getIdSexo());
    }
   
    
}
