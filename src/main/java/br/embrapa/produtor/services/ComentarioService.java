package br.embrapa.produtor.services;

import br.embrapa.produtor.models.Comentario;

public interface ComentarioService {

    Iterable<Comentario> listarTodosComentarios();

    Iterable<Comentario> listarTodosComentariosDaFoto(Long fotoId);

    Comentario inserirComentario(Comentario comentario);

    Comentario buscarComentarioPorId(Long id);
}
