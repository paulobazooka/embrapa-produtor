package br.embrapa.produtor.services;

import br.embrapa.produtor.models.Cultura;
import br.embrapa.produtor.models.DoencaCultura;

public interface DoencaCulturaService {

    DoencaCultura cadastrarDoencacultura(DoencaCultura doencaCultura);

    Iterable<DoencaCultura> listarTodasAsDoencasCulturas();

    Iterable<DoencaCultura> listarTodasAsDoencasCulturaPorCultura(Cultura cultura);

    Iterable<DoencaCultura> listarTodasAsDoencasCulturaPorCultura(Long id);

    DoencaCultura buscarDoencaCulturaPorDoencaCulturaId(Long id);

    DoencaCultura buscarDoencaCultura(String nomeDoenca);
}
