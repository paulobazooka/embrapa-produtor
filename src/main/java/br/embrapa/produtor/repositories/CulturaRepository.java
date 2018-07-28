package br.embrapa.produtor.repositories;

import br.embrapa.produtor.models.Cultura;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CulturaRepository extends CrudRepository<Cultura, Long> {
    Cultura findByNome(String nomeCultura);

    Iterable<Cultura> findAllByOrderByNome();
}
