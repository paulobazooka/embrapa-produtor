package br.embrapa.produtor.repositories;

import br.embrapa.produtor.models.Foto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FotoRepository extends CrudRepository<Foto, Long> {

}
