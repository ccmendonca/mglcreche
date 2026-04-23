
package skylink.mglcreche.modelo;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * «claudiomendonca
 */

// Classe responsável por mapear a entidade profissional

public class Profissional implements Serializable {
    
    public static final long serialVersionUID = 1L;
    
    private int codigo;
    private String numeroContribuinte;
    private String nome;
    private String sobrenome;
    private Date dataNascimento;
    private String numeroCasa;
    private String rua;
    private String bairro;
    private String distrito;
    private String telefoneUnitel;
    private String telefoneMovicel;
    private String telefoneAfricell;
    private String telefoneFixo;
    private String email;
    private String habilitacoesLiterarias;
    private String observacoes;
    private Sexo sexo;
    private Municipio municipio;
    private CategoriaProfissional categoriaprofissional;

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNumeroContribuinte() {
        return numeroContribuinte;
    }

    public void setNumeroContribuinte(String numeroContribuinte) {
        this.numeroContribuinte = numeroContribuinte;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getNumeroCasa() {
        return numeroCasa;
    }

    public void setNumeroCasa(String numeroCasa) {
        this.numeroCasa = numeroCasa;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getDistrito() {
        return distrito;
    }

    public void setDistrito(String distrito) {
        this.distrito = distrito;
    }

    public String getTelefoneUnitel() {
        return telefoneUnitel;
    }

    public void setTelefoneUnitel(String telefoneUnitel) {
        this.telefoneUnitel = telefoneUnitel;
    }

    public String getTelefoneMovicel() {
        return telefoneMovicel;
    }

    public void setTelefoneMovicel(String telefoneMovicel) {
        this.telefoneMovicel = telefoneMovicel;
    }

    public String getTelefoneAfricell() {
        return telefoneAfricell;
    }

    public void setTelefoneAfricell(String telefoneAfricell) {
        this.telefoneAfricell = telefoneAfricell;
    }

    public String getTelefoneFixo() {
        return telefoneFixo;
    }

    public void setTelefoneFixo(String telefoneFixo) {
        this.telefoneFixo = telefoneFixo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHabilitacoesLiterarias() {
        return habilitacoesLiterarias;
    }

    public void setHabilitacoesLiterarias(String habilitacoesLiterarias) {
        this.habilitacoesLiterarias = habilitacoesLiterarias;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public Sexo getSexo() {
        return sexo;
    }

    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
    }

    public Municipio getMunicipio() {
        return municipio;
    }

    public void setMunicipio(Municipio municipio) {
        this.municipio = municipio;
    }

    public CategoriaProfissional getCategoriaprofissional() {
        return categoriaprofissional;
    }

    public void setCategoriaprofissional(CategoriaProfissional categoriaprofissional) {
        this.categoriaprofissional = categoriaprofissional;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + this.codigo;
        hash = 79 * hash + Objects.hashCode(this.sexo);
        hash = 79 * hash + Objects.hashCode(this.municipio);
        hash = 79 * hash + Objects.hashCode(this.categoriaprofissional);
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
        final Profissional other = (Profissional) obj;
        if (this.codigo != other.codigo) {
            return false;
        }
        if (!Objects.equals(this.sexo, other.sexo)) {
            return false;
        }
        if (!Objects.equals(this.municipio, other.municipio)) {
            return false;
        }
        return Objects.equals(this.categoriaprofissional, other.categoriaprofissional);
    }

    @Override
    public String toString() {
        return "Profissional{" + "codigo=" + codigo + ","
                + " numeroContribuinte=" + numeroContribuinte + ","
                + " nome=" + nome + ", sobrenome=" + sobrenome + ","
                + " dataNascimento=" + dataNascimento + ","
                + " numeroCasa=" + numeroCasa + ","
                + " rua=" + rua + ", bairro=" + bairro + ","
                + " distrito=" + distrito + ","
                + " telefoneUnitel=" + telefoneUnitel + ","
                + " telefoneMovicel=" + telefoneMovicel + ","
                + " telefoneAfricell=" + telefoneAfricell + ","
                + " telefoneFixo=" + telefoneFixo + ", email=" + email + ","
                + " habilitacoesLiterarias=" + habilitacoesLiterarias + ","
                + " observacoes=" + observacoes + ","
                + " sexo=" + sexo + ","
                + " municipio=" + municipio + ","
                + " categoriaprofissional=" + categoriaprofissional + '}';
    }
}
