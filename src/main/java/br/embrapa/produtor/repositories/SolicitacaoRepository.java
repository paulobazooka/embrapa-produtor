package br.embrapa.produtor.repositories;

import br.embrapa.produtor.models.Foto;
import br.embrapa.produtor.models.Solicitacao;
import br.embrapa.produtor.models.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SolicitacaoRepository extends CrudRepository<Solicitacao, Long>{

    Solicitacao findTopByProdutor(Usuario produtor);

    Solicitacao findFirstByProdutor(Usuario produtor);

    Solicitacao findFirstByProdutorOrderByIdDesc(Usuario produtor);

    Iterable<Solicitacao> findSolicitacaosByProdutorId(Long id);

    Page<Solicitacao> findSolicitacaosByProdutorId(Long id, Pageable pageable);

    Solicitacao findSolicitacaoByFotos(Foto foto);

    Iterable<Solicitacao> findAll();

    Page<Solicitacao> findAll(Pageable pageable);
}
