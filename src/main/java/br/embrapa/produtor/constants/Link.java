package br.embrapa.produtor.constants;

public enum Link {

    LOCAL("http://localhost:8080"),
    HEROKU("https://embrapa-produtor.herokuapp.com"),
    NOVA_SENHA(""),
    CONFIRMAR_CADASTRO("/confirmar-cadastro/");


    private String link;


    Link(String link) {
        this.link = link;
    }


    public String getUrl() {
        return link;
    }
}
