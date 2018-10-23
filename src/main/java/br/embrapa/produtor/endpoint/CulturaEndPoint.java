package br.embrapa.produtor.endpoint;

import br.embrapa.produtor.models.Cultura;
import br.embrapa.produtor.serviceimpl.CulturaServiceImplements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/cultura")
public class CulturaEndPoint {

    @Autowired
    CulturaServiceImplements culturaService;


    @GetMapping
    public Iterable<Cultura> getCulturas(){

        Iterable<Cultura> culturas = culturaService.listarCulturas();

        return culturas;
    }

}
