package br.embrapa.produtor.serviceimpl;

import br.embrapa.produtor.models.Cultura;
import br.embrapa.produtor.repositories.CulturaRepository;
import br.embrapa.produtor.services.CulturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 *  SERVIÇO PARA CONSULTAR E PERSISTIR CULTURAS
 *
 * */
@Service
public class CulturaServiceImplements implements CulturaService{

    @Autowired
    CulturaRepository culturaRepository;



    /**
     *  LISTAR TODAS AS CULTURAS EXISTENTES NO BANCO DE DADOS
     *
     * */
    @Override
    public Iterable<Cultura> listarCulturas() {
        return this.culturaRepository.findAll();
    }

    @Override
    public Iterable<Cultura> listarCulturasPorOrdemAlfabetica() {
        return this.culturaRepository.findAllByOrderByNome();
    }


    /**
     *
     *  PERSISTIR UMA NOVA CULTURA
     *
     **/
    @Override
    public Cultura cadastrarCultura(Cultura cultura) {
        return this.culturaRepository.save(cultura);
    }



    /**
     *  BUSCAR CULTURA ESPECÍFICA NO BANCO DE DADOS
     *          POR ID
     * */
    @Override
    public Cultura buscarCulturaPorId(Long id) {
        return this.culturaRepository.findById(id).get();
    }



    /**
     *
     * BUSCAR CULTURA POR ESPECÍFICA NO BANCO DE DADOS
     *    PELO NOME
     *
     * */
    @Override
    public Cultura buscarCulturaPorNome(String nomeCultura) {
        return this.culturaRepository.findByNome(nomeCultura);
    }

    @Override
    public Cultura atualizarCultura(Cultura cultura) {
        return this.culturaRepository.save(cultura);
    }
}
