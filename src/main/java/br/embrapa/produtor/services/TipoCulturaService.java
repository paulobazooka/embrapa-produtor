package br.embrapa.produtor.services;

import br.embrapa.produtor.models.TipoCultura;

public interface TipoCulturaService {

    Iterable<TipoCultura> listarTodasOsTiposCultura();

    TipoCultura buscarTipoCulturaPorID(Long id);

    TipoCultura buscarTipoCulturaPorTipo(String tipo);

    TipoCultura cadastrarTipoCultura(TipoCultura tipo);
}
