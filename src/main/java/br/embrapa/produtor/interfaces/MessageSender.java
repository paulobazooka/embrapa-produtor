package br.embrapa.produtor.interfaces;

import br.embrapa.produtor.models.Mensagem;

public interface MessageSender {

    void enviarMensagem(Mensagem mensagem);
}
