package br.embrapa.produtor.services;

import br.embrapa.produtor.models.Mensagem;

public interface EmailService {

    boolean enviarEmail(Mensagem mensagem, String destinatario);
}
