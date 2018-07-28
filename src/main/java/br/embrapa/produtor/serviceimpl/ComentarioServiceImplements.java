package br.embrapa.produtor.serviceimpl;

import br.embrapa.produtor.models.Comentario;
import br.embrapa.produtor.repositories.ComentarioRepository;
import br.embrapa.produtor.services.ComentarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ComentarioServiceImplements implements ComentarioService{

    @Autowired
    ComentarioRepository comentarioRepository;

    @Override
    public Iterable<Comentario> listarTodosComentarios() {
        return this.comentarioRepository.findAll();
    }

    @Override
    public Iterable<Comentario> listarTodosComentariosDaFoto(Long fotoId) {
        return null;
    }

    @Override
    public Comentario inserirComentario(Comentario comentario) {
        return this.comentarioRepository.save(comentario);
    }

    @Override
    public Comentario buscarComentarioPorId(Long id) {
        return this.comentarioRepository.findById(id).get();
    }
}
