/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package skylink.mglcreche.modelo;

import java.util.Objects;

public class TipoAvaliacao {

    private Integer idTipoAvaliacao;
    private String descricaoTipoAvaliacao;
    private String siglaAvaliacao;
    private Double pesoTipoAvaliacao;

    public TipoAvaliacao() {
    }

    public TipoAvaliacao(Integer idTipoAvaliacao, String descricaoTipoAvaliacao, String siglaAvaliacao, Double pesoTipoAvaliacao) {
        this.idTipoAvaliacao = idTipoAvaliacao;
        this.descricaoTipoAvaliacao = descricaoTipoAvaliacao;
        this.siglaAvaliacao = siglaAvaliacao;
        this.pesoTipoAvaliacao = pesoTipoAvaliacao;
    }

    public Integer getIdTipoAvaliacao() {
        return idTipoAvaliacao;
    }

    public void setIdTipoAvaliacao(Integer idTipoAvaliacao) {
        this.idTipoAvaliacao = idTipoAvaliacao;
    }

    public String getDescricaoTipoAvaliacao() {
        return descricaoTipoAvaliacao;
    }

    public void setDescricaoTipoAvaliacao(String descricaoTipoAvaliacao) {
        this.descricaoTipoAvaliacao = descricaoTipoAvaliacao;
    }

    public String getSiglaAvaliacao() {
        return siglaAvaliacao;
    }

    public void setSiglaAvaliacao(String siglaAvaliacao) {
        this.siglaAvaliacao = siglaAvaliacao;
    }

    public Double getPesoTipoAvaliacao() {
        return pesoTipoAvaliacao;
    }

    public void setPesoTipoAvaliacao(Double pesoTipoAvaliacao) {
        this.pesoTipoAvaliacao = pesoTipoAvaliacao;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + Objects.hashCode(this.idTipoAvaliacao);
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
        final TipoAvaliacao other = (TipoAvaliacao) obj;
        return Objects.equals(this.idTipoAvaliacao, other.idTipoAvaliacao);
    }
    
    
      @Override
    public String toString() {
           return String.format("%s[idTipoAvaliacao=%d]", getClass().getSimpleName(), getIdTipoAvaliacao());
    }
   

}
