package br.embrapa.produtor.constants;

public enum Link {

    LOCAL("http://localhost:8080"),
    HEROKU("https://embrapa-produtor.herokuapp.com");

    private String link;

    Link(String link) {
        this.link = link;
    }

    public String getUrl() {
        return link;
    }
}
