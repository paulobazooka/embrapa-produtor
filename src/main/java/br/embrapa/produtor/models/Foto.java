package br.embrapa.produtor.models;

import br.embrapa.produtor.auxiliar.FilePathToString64;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "foto")
public class Foto extends Abstract{

    private String caminho_foto;
    @OneToMany
    private List<Comentario> comentarios = new ArrayList<>();


    public Foto() {
    }

    public Foto(String caminho_foto){
        this.caminho_foto = caminho_foto;
    }


    public String getCaminho_foto() {
        /*return FilePathToString64.retornaString64(caminho_foto);*/
        return this.caminho_foto;
    }

    public void setCaminho_foto(String caminho_foto) {
        this.caminho_foto = caminho_foto;
    }

    public List<Comentario> getComentarios() {
        return comentarios;
    }

    public void setComentarios(List<Comentario> comentarios) {
        this.comentarios = comentarios;
    }
}
