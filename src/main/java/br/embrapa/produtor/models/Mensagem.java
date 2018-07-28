package br.embrapa.produtor.models;

public class Mensagem {

    private String recipient;
    private String title;
    private String body;
    private String subBody;
    private String signature;

    public Mensagem() {
    }

    public Mensagem(String recipient, String title, String body, String subBody, String signature) {
        this.recipient = recipient;
        this.title = title;
        this.body = body;
        this.subBody = subBody;
        this.signature = signature;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getSubBody() {
        return subBody;
    }

    public void setSubBody(String subBody) {
        this.subBody = subBody;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }
}
