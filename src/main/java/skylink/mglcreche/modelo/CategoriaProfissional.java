
package skylink.mglcreche.modelo;

import java.io.Serializable;

/**
 * «claudiomendonca»
 */

// Classe responsável por mapear as categorias profissionais

public class CategoriaProfissional implements Serializable {
    
    public static final long serialVersionUID = 1L;
    
    private int codigo;
    private String descricao;

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 73 * hash + this.codigo;
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
        final CategoriaProfissional other = (CategoriaProfissional) obj;
        return this.codigo == other.codigo;
    }

    @Override
    public String toString() {
        return "CategoriaProfissional{" + "codigo=" + codigo + ","
                + " descricao=" + descricao + '}';
    }
    
}
