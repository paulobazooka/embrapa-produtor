package br.embrapa.produtor.serviceimpl;

import br.embrapa.produtor.models.Foto;
import br.embrapa.produtor.models.Solicitacao;
import br.embrapa.produtor.models.Usuario;
import br.embrapa.produtor.repositories.SolicitacaoRepository;
import br.embrapa.produtor.services.SolicitacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SolicitacaoServiceImplements implements SolicitacaoService {

    @Autowired
    SolicitacaoRepository solicitacaoRepository;

    @Override
    @Cacheable("solicitacoes")
    public Iterable<Solicitacao> listarTodasSolicitacoes() {
        return this.solicitacaoRepository.findAll();
    }

    @Override
    public Iterable<Solicitacao> listarTodasSolicitacoesPorProdutor(Usuario produtor) {
        return this.solicitacaoRepository.findSolicitacaosByProdutorId(produtor.getId());
    }

    @Override
    @CachePut("solicitacoes")
    public Solicitacao cadastrarSolicitacao(Solicitacao solicitacao) {
        return this.solicitacaoRepository.save(solicitacao);
    }

    @Override
    @CachePut("solicitacoes")
    public Solicitacao atualizarSolicitacao(Solicitacao solicitacao) {
        return this.solicitacaoRepository.save(solicitacao);
    }

    @Override
    public Solicitacao buscarSolicitacao(Long id) {
        return this.solicitacaoRepository.findById(id).get();
    }

    @Override
    public Solicitacao buscarSolicitacaoPorFoto(Foto foto) {
        return this.solicitacaoRepository.findSolicitacaoByFotos(foto);
    }

    @Override
    @Cacheable("solicitacoes")
    public Page<Solicitacao> listarTodasAsSolicitacoesPorPagina(Pageable pageable) {
        return this.solicitacaoRepository.findAll(pageable);
    }

    @Override
    public Page<Solicitacao> listarTodasAsSolicitacoesPorProdutorId(Long id, Pageable pageable) {
        return this.solicitacaoRepository.findSolicitacaosByProdutorId(id, pageable);
    }

    @Override
    public Solicitacao buscarUltimaSolicitacao(Usuario usuario) {
        Optional<Solicitacao> solicitacao = Optional.ofNullable(this.solicitacaoRepository.findTopByProdutor(usuario));
        if (solicitacao.isPresent())
            return solicitacao.get();
        else
            return null;
    }

    @Override
    public Solicitacao buscarUltimaSolicitacaoRealizada(Usuario usuario){
        Optional<Solicitacao> solicitacao = Optional.ofNullable(this.solicitacaoRepository.findFirstByProdutorOrderByIdDesc(usuario));
        if (solicitacao.isPresent())
            return solicitacao.get();
        else
            return null;
    }


}
