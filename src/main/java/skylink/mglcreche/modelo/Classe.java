package skylink.mglcreche.modelo;

import java.io.Serializable;
import java.util.Objects;

public class Classe implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer idClasse;
    private String descricaoClasse;

    public Classe() {
    }

    public Classe(Integer idClasse, String descricaoClasse) {
        this.idClasse = idClasse;
        this.descricaoClasse = descricaoClasse;
    }

    public Integer getIdClasse() {
        return idClasse;
    }

    public void setIdClasse(Integer idClasse) {
        this.idClasse = idClasse;
    }

    public String getDescricaoClasse() {
        return descricaoClasse;
    }

    public void setDescricaoClasse(String descricaoClasse) {
        this.descricaoClasse = descricaoClasse;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + Objects.hashCode(this.idClasse);
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
        final Classe other = (Classe) obj;
        return Objects.equals(this.idClasse, other.idClasse);
    }

    @Override
    public String toString() {
        return "Classe{" + "descricaoClasse=" + descricaoClasse + '}';
    }
}