package br.embrapa.produtor.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "tipo")
public class TipoCultura extends Abstract {

    @Column(unique = true)
    private String tipo;

    public TipoCultura() {
    }

    public TipoCultura(String tipo) {
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
