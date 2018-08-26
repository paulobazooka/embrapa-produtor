package br.embrapa.produtor.services;


import br.embrapa.produtor.models.Foto;
import br.embrapa.produtor.models.Solicitacao;
import br.embrapa.produtor.models.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SolicitacaoService {

    Iterable<Solicitacao> listarTodasSolicitacoes();

    Iterable<Solicitacao> listarTodasSolicitacoesPorProdutor(Usuario produtor);

    Solicitacao cadastrarSolicitacao(Solicitacao solicitacao);

    Solicitacao atualizarSolicitacao(Solicitacao solicitacao);

    Solicitacao buscarSolicitacao(Long id);

    Solicitacao buscarSolicitacaoPorFoto(Foto foto);

    Page<Solicitacao> listarTodasAsSolicitacoesPorPagina(Pageable pageable);

    Page<Solicitacao> listarTodasAsSolicitacoesPorProdutorId(Long id, Pageable pageable);

    Solicitacao buscarUltimaSolicitacao(Usuario usuario);

    Solicitacao buscarUltimaSolicitacaoRealizada(Usuario usuario);

}
