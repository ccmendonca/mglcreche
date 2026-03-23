
package skylink.mglcreche.modelo;

import java.util.Objects;


public class FormaPagamento {
    private Integer idFormaPagamento;
    private String descricaoFormaPagamento;

    public FormaPagamento() {
    }

    public FormaPagamento(Integer idFormaPagamento, String descricaoFormaPagamento) {
        this.idFormaPagamento = idFormaPagamento;
        this.descricaoFormaPagamento = descricaoFormaPagamento;
    }

    public Integer getIdFormaPagamento() {
        return idFormaPagamento;
    }

    public void setIdFormaPagamento(Integer idFormaPagamento) {
        this.idFormaPagamento = idFormaPagamento;
    }

    public String getDescricaoFormaPagamento() {
        return descricaoFormaPagamento;
    }

    public void setDescricaoFormaPagamento(String descricaoFormaPagamento) {
        this.descricaoFormaPagamento = descricaoFormaPagamento;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + Objects.hashCode(this.idFormaPagamento);
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
        final FormaPagamento other = (FormaPagamento) obj;
        return Objects.equals(this.idFormaPagamento, other.idFormaPagamento);
    }

    @Override
    public String toString() {
        return "FormaPagamento{" + "descricaoFormaPagamento=" + descricaoFormaPagamento + '}';
    }
    
}
