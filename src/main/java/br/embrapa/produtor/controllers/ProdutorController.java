package br.embrapa.produtor.controllers;

import br.embrapa.produtor.models.Cultura;
import br.embrapa.produtor.serviceimpl.CulturaServiceImplements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/produtor")
public class ProdutorController {

    @Autowired
    CulturaServiceImplements culturaService;

    @RequestMapping("/cadastrar")
    public String cadastroProdutor(){
        return "fragment/cadastro_produtor";
    }

    @RequestMapping("/nova-solicitacao")
    public ModelAndView cadastrarSolicitacao(){
        ModelAndView mv = new ModelAndView("produtor/nova_solicitacao");

        Iterable<Cultura> culturas = culturaService.listarCulturasPorOrdemAlfabetica();
        mv.addObject("culturas", culturas);

        return mv;
    }
}
