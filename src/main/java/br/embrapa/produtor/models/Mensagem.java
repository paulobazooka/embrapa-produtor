package br.embrapa.produtor.models;

/**
 *
 */
public class Mensagem {
    /**
     *
     */
    private String titulo;
    private String corpo;
    private String remetente;


    /**
     *
     * @param titulo titulo da mensagem a ser enviada
     * @param corpo texto da mensagem a ser enviada
     * @param remetente remetente da mensagem a ser enviada
     */
    public Mensagem(String titulo, String corpo, String remetente) {
        this.titulo = titulo;
        this.corpo = corpo;
        this.remetente = remetente;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getCorpo() {
        return corpo;
    }

    public void setCorpo(String corpo) {
        this.corpo = corpo;
    }

    public String getRemetente() {
        return remetente;
    }

    public void setRemetente(String remetente) {
        this.remetente = remetente;
    }
}
