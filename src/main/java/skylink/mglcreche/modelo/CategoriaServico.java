package skylink.mglcreche.modelo;

import java.util.Objects;

public class CategoriaServico {

    private Integer idCategoriaServico;
    private String descricaoCategoriaServico;

    public CategoriaServico() {
    }

    public CategoriaServico(Integer idCategoriaServico, String descricaoCategoriaServico) {
        this.idCategoriaServico = idCategoriaServico;
        this.descricaoCategoriaServico = descricaoCategoriaServico;
    }

    public Integer getIdCategoriaServico() {
        return idCategoriaServico;
    }

    public void setIdCategoriaServico(Integer idCategoriaServico) {
        this.idCategoriaServico = idCategoriaServico;
    }

    public String getDescricaoCategoriaServico() {
        return descricaoCategoriaServico;
    }

    public void setDescricaoCategoriaServico(String descricaoCategoriaServico) {
        this.descricaoCategoriaServico = descricaoCategoriaServico;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.idCategoriaServico);
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
        final CategoriaServico other = (CategoriaServico) obj;
        return Objects.equals(this.idCategoriaServico, other.idCategoriaServico);
    }

    
         @Override
    public String toString() {
           return String.format("%s[idCategoriaServico=%d]", getClass().getSimpleName(), getIdCategoriaServico());
    }
    
}
