package br.embrapa.produtor.models;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "comentario")
public class Comentario extends Abstract{

    private LocalDateTime data_comentario;
    @OneToOne
    private DoencaCultura doenca;
    @Column(columnDefinition = "TEXT")
    private String diagnostico;
    @OneToOne
    private Usuario pesquisador;

    public Comentario() {
    }


    public LocalDateTime getData_comentario() {
        return data_comentario;
    }

    public void setData_comentario(LocalDateTime data_comentario) {
        this.data_comentario = data_comentario;
    }

    public DoencaCultura getDoenca() {
        return doenca;
    }

    public void setDoenca(DoencaCultura doenca) {
        this.doenca = doenca;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    public Usuario getPesquisador() {
        return pesquisador;
    }

    public void setPesquisador(Usuario pesquisador) {
        this.pesquisador = pesquisador;
    }
}
