package br.embrapa.produtor.models;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cultura")
public class Cultura extends Abstract{

    private String nome;
    private String nomecientifico;
    @ManyToOne
    private TipoCultura tipo;
    @OneToMany(fetch = FetchType.LAZY)
    private List<DoencaCultura> doencas = new ArrayList<>();

    public Cultura() {
    }

    public Cultura(String nome, String nomecientifico, TipoCultura tipo) {
        this.nome = nome;
        this.nomecientifico = nomecientifico;
        this.tipo = tipo;
    }

    public Cultura(String nome, String nomecientifico) {
        this.nome = nome;
        this.nomecientifico = nomecientifico;
    }


    @Column(unique = true)
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public TipoCultura getTipo() {
        return tipo;
    }

    public void setTipo(TipoCultura tipo) {
        this.tipo = tipo;
    }

    public String getNomecientifico() {
        return nomecientifico;
    }

    public void setNomecientifico(String nomecientifico) {
        this.nomecientifico = nomecientifico;
    }

    public List<DoencaCultura> getDoencas() {
        return doencas;
    }

    public void setDoencas(List<DoencaCultura> doencas) {
        this.doencas = doencas;
    }
}
