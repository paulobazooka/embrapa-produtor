package br.embrapa.produtor.repositories;

import br.embrapa.produtor.models.TipoCultura;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoCulturaRepository extends CrudRepository<TipoCultura, Long> {

    TipoCultura findByTipo(String tipo);
}
