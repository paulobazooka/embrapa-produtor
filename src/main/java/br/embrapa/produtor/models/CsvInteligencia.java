package br.embrapa.produtor.models;


import java.time.LocalDateTime;

public class CsvInteligencia {

    private Long produtor_id;
    private LocalDateTime data_requisicao;
    private String caminho;
    private Long cultura_id;
    private String doenca;
    private Long pesquisador_id;

    public CsvInteligencia() {
    }

    public CsvInteligencia(Long produtor_id, LocalDateTime data_requisicao, String caminho, Long cultura_id, String doenca, Long pesquisador_id) {
        this.produtor_id = produtor_id;
        this.data_requisicao = data_requisicao;
        this.caminho = caminho;
        this.cultura_id = cultura_id;
        this.doenca = doenca;
        this.pesquisador_id = pesquisador_id;
    }


    public Long getProdutor_id() {
        return produtor_id;
    }

    public void setProdutor_id(Long produtor_id) {
        this.produtor_id = produtor_id;
    }

    public LocalDateTime getData_requisicao() {
        return data_requisicao;
    }

    public void setData_requisicao(LocalDateTime data_requisicao) {
        this.data_requisicao = data_requisicao;
    }

    public String getCaminho() {
        return caminho;
    }

    public void setCaminho(String caminho) {
        this.caminho = caminho;
    }

    public Long getCultura_id() {
        return cultura_id;
    }

    public void setCultura_id(Long cultura_id) {
        this.cultura_id = cultura_id;
    }

    public String getDoenca() {
        return doenca;
    }

    public void setDoenca(String doenca) {
        this.doenca = doenca;
    }

    public Long getPesquisador_id() {
        return pesquisador_id;
    }

    public void setPesquisador_id(Long pesquisador_id) {
        this.pesquisador_id = pesquisador_id;
    }
}
