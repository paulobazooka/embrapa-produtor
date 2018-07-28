package br.embrapa.produtor.serviceimpl;

import br.embrapa.produtor.models.TipoCultura;
import br.embrapa.produtor.repositories.TipoCulturaRepository;
import br.embrapa.produtor.services.TipoCulturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TipoCulturaServiceImplements implements TipoCulturaService{

    @Autowired
    TipoCulturaRepository tipoCulturaRepository;

    @Override
    public Iterable<TipoCultura> listarTodasOsTiposCultura() {
        return this.tipoCulturaRepository.findAll();
    }

    @Override
    public TipoCultura buscarTipoCulturaPorID(Long id) {
        return this.tipoCulturaRepository.findById(id).get();
    }

    @Override
    public TipoCultura buscarTipoCulturaPorTipo(String tipo) {
        return this.tipoCulturaRepository.findByTipo(tipo);
    }

    @Override
    public TipoCultura cadastrarTipoCultura(TipoCultura tipo) {
        return this.tipoCulturaRepository.save(tipo);
    }
}
