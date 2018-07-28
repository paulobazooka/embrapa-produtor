package br.embrapa.produtor.repositories;

import br.embrapa.produtor.models.DoencaCultura;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoencaCulturaRepository extends CrudRepository<DoencaCultura, Long>{

    Iterable<DoencaCultura> findDoencaCulturasByDoenca(Long id);

    DoencaCultura findByDoenca(String nomeDoenca);

}
