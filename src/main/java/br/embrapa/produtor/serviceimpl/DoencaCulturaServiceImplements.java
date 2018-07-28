package br.embrapa.produtor.serviceimpl;

import br.embrapa.produtor.models.Cultura;
import br.embrapa.produtor.models.DoencaCultura;
import br.embrapa.produtor.repositories.DoencaCulturaRepository;
import br.embrapa.produtor.services.DoencaCulturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DoencaCulturaServiceImplements implements DoencaCulturaService {

    @Autowired
    DoencaCulturaRepository doencaCulturaRepository;


    @Override
    public DoencaCultura cadastrarDoencacultura(DoencaCultura doencaCultura) {
        return this.doencaCulturaRepository.save(doencaCultura);
    }

    @Override
    public Iterable<DoencaCultura> listarTodasAsDoencasCulturas() {
        return this.doencaCulturaRepository.findAll();
    }

    @Override
    public Iterable<DoencaCultura> listarTodasAsDoencasCulturaPorCultura(Cultura cultura) {
        return this.doencaCulturaRepository.findDoencaCulturasByDoenca(cultura.getId());
    }

    @Override
    public Iterable<DoencaCultura> listarTodasAsDoencasCulturaPorCultura(Long id) {
        return this.doencaCulturaRepository.findDoencaCulturasByDoenca(id);
    }

    @Override
    public DoencaCultura buscarDoencaCulturaPorDoencaCulturaId(Long id) {
        return null;
    }

    @Override
    public DoencaCultura buscarDoencaCultura(String nomeDoenca) {
        return this.doencaCulturaRepository.findByDoenca(nomeDoenca);
    }
}
