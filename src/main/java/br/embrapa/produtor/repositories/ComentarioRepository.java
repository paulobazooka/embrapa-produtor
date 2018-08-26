package br.embrapa.produtor.repositories;

import br.embrapa.produtor.models.Comentario;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComentarioRepository extends CrudRepository<Comentario, Long>{
}
