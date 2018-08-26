package br.embrapa.produtor.models;

import org.hibernate.annotations.Fetch;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "solicitacao")
public class Solicitacao extends Abstract {

    private String cidade;
    private String estado;
    private LocalDateTime data_requisicao;
    private boolean notificacao;
    private String titulo;
    @Column(columnDefinition = "TEXT")
    private String descricao;
    @ManyToOne
    private Cultura cultura;
    @ManyToOne
    private Usuario produtor;
    @OneToMany
    private List<Foto> fotos = new ArrayList<>();


    public Solicitacao() {
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public LocalDateTime getData_requisicao() {
        return data_requisicao;
    }

    public void setData_requisicao(LocalDateTime data_requisicao) {
        this.data_requisicao = data_requisicao;
    }

    public boolean isNotificacao() {
        return notificacao;
    }

    public void setNotificacao(boolean notificacao) {
        this.notificacao = notificacao;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Cultura getCultura() {
        return cultura;
    }

    public void setCultura(Cultura cultura) {
        this.cultura = cultura;
    }

    public Usuario getProdutor() {
        return produtor;
    }

    public void setProdutor(Usuario produtor) {
        this.produtor = produtor;
    }


    public List<Foto> getFotos() {
        return fotos;
    }

    public void setFotos(List<Foto> fotos) {
        this.fotos = fotos;
    }

}
