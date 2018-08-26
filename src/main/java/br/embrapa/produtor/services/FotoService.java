package br.embrapa.produtor.services;

import br.embrapa.produtor.models.Foto;

public interface FotoService {

    Iterable<Foto> listarTodasFotos();

    Foto cadastrarFoto(Foto foto);

    Foto buscarFoto(Long id);
}
