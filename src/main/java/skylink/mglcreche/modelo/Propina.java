
package skylink.mglcreche.modelo;

import java.util.Date;
import java.util.Objects;


public class Propina {
    private Integer idPropina;
    private Date dataPagamento;
    private Aluno aluno;
    private AnoLectivo anoLectivo;
    private FormaPagamento formaPagamento;
    private Date dataHoraRegisto;
    

    public Propina() {
    }

    public Propina(Integer idPropina, Date dataPagamento, Aluno aluno, AnoLectivo anoLectivo, FormaPagamento formaPagamento, Date dataHoraRegisto) {
        this.idPropina = idPropina;
        this.dataPagamento = dataPagamento;
        this.aluno = aluno;
        this.anoLectivo = anoLectivo;
        this.formaPagamento = formaPagamento;
        this.dataHoraRegisto = dataHoraRegisto;
    }

   
   

    public AnoLectivo getAnoLectivo() {
        return anoLectivo;
    }

    public void setAnoLectivo(AnoLectivo anoLectivo) {
        this.anoLectivo = anoLectivo;
    }

  

    public Integer getIdPropina() {
        return idPropina;
    }

    public void setIdPropina(Integer idPropina) {
        this.idPropina = idPropina;
    }

    public Date getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(Date dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public Date getDataHoraRegisto() {
        return dataHoraRegisto;
    }

    public void setDataHoraRegisto(Date dataHoraRegisto) {
        this.dataHoraRegisto = dataHoraRegisto;
    }

    public FormaPagamento getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(FormaPagamento formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + Objects.hashCode(this.idPropina);
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
        final Propina other = (Propina) obj;
        return Objects.equals(this.idPropina, other.idPropina);
    }
    
}
