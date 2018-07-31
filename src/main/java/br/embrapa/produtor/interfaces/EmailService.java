package br.embrapa.produtor.interfaces;

import br.embrapa.produtor.models.Mensagem;

public interface EmailService {

    boolean enviarEmail(Mensagem mensagem, String destinatario);
}
