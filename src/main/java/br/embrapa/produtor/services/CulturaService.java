package br.embrapa.produtor.services;

import br.embrapa.produtor.models.Cultura;

import java.util.List;

public interface CulturaService {

    Iterable<Cultura> listarCulturas();

    Iterable<Cultura> listarCulturasPorOrdemAlfabetica();

    Cultura cadastrarCultura(Cultura cultura);

    Cultura buscarCulturaPorId(Long id);

    Cultura buscarCulturaPorNome(String nomeCultura);

    Cultura atualizarCultura(Cultura cultura);
}
