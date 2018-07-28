package br.embrapa.produtor.models;

import javax.persistence.*;

@Entity
@Table(name = "doenca")
public class DoencaCultura extends Abstract {

    @Column(unique = true)
    private String doenca;
    private String nomecientifico;

    public DoencaCultura() {
    }

    public DoencaCultura(String doenca, String nomecientifico) {
        this.doenca = doenca;
        this.nomecientifico = nomecientifico;
    }


    public DoencaCultura(String doenca) {
        this.doenca = doenca;
    }

    public String getDoenca() {
        return doenca;
    }

    public void setDoenca(String doenca) {
        this.doenca = doenca;
    }

    public String getNomecientifico() {
        return nomecientifico;
    }

    public void setNomecientifico(String nomecientifico) {
        this.nomecientifico = nomecientifico;
    }
}
