package br.embrapa.produtor.endpoint;

import br.embrapa.produtor.models.DoencaCultura;
import br.embrapa.produtor.serviceimpl.DoencaCulturaServiceImplements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/doenca")
public class DoencaEndPoint {

    @Autowired
    DoencaCulturaServiceImplements doencaCulturaService;

    @GetMapping("/{id}")
    public List<DoencaCultura> getDoenca(@PathVariable Long id){
        List<DoencaCultura> doencas = (List<DoencaCultura>) doencaCulturaService.buscarDoencaCulturaPorDoencaCulturaId(id);
        return doencas;
    }
}
