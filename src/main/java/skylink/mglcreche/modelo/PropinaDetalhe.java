
package skylink.mglcreche.modelo;


public class PropinaDetalhe {
    private Propina propina;
    private MesPropina mesPropina;
    private Double valorPropina;
    

    public PropinaDetalhe() {
    }

    public PropinaDetalhe(Propina propina, MesPropina mesPropina, Double valorPropina) {
        this.propina = propina;
        this.mesPropina = mesPropina;
        this.valorPropina = valorPropina;
    }

    
     public PropinaDetalhe(MesPropina mesPropina, Double valorPropina) {
        this.mesPropina = mesPropina;
        this.valorPropina = valorPropina;
    }
   
    
    
    public Propina getPropina() {
        return propina;
    }

    public void setPropina(Propina propina) {
        this.propina = propina;
    }

    public MesPropina getMesPropina() {
        return mesPropina;
    }

    public void setMesPropina(MesPropina mesPropina) {
        this.mesPropina = mesPropina;
    }

    public Double getValorPropina() {
        return valorPropina;
    }

    public void setValorPropina(Double valorPropina) {
        this.valorPropina = valorPropina;
    }
    
    
}
