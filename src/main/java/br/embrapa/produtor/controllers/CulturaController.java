package br.embrapa.produtor.controllers;

import br.embrapa.produtor.models.Cultura;
import br.embrapa.produtor.models.TipoCultura;
import br.embrapa.produtor.models.Usuario;
import br.embrapa.produtor.serviceimpl.CulturaServiceImplements;
import br.embrapa.produtor.serviceimpl.TipoCulturaServiceImplements;
import br.embrapa.produtor.serviceimpl.UsuarioServiceImpl;
import br.embrapa.produtor.services.CulturaService;
import br.embrapa.produtor.services.TipoCulturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@Controller
@RequestMapping("/pesquisador")
public class CulturaController {

    @Autowired
    private TipoCulturaServiceImplements tipoCulturaService;

    @Autowired
    private UsuarioServiceImpl usuarioService;

    @Autowired
    private CulturaServiceImplements culturaService;


    @RequestMapping(value = "/pagina-cadastro-cultura", method = RequestMethod.GET)
    public ModelAndView paginaDeCadastroCultura(Principal principal){
        ModelAndView mv = new ModelAndView("home/principal");

        Usuario user = usuarioService.buscarPorEmail(principal.getName());
        Iterable<TipoCultura> tipos = tipoCulturaService.listarTodasOsTiposCultura();
        Iterable<Cultura> culturas = culturaService.listarCulturasPorOrdemAlfabetica();

        mv.addObject("user", user);
        mv.addObject("tipos", tipos);
        mv.addObject("culturas", culturas);
        mv.addObject("local","pesquisador/cultura");
        mv.addObject("fragmento", "cultura");

        return mv;
    }


    @RequestMapping(value = "/cadastro-cultura", method = RequestMethod.POST)
    public ModelAndView cadastroDeCultura(Principal principal,
                                          @RequestParam("nome") String nome,
                                          @RequestParam("nomecientifico") String nomecientifico,
                                          @RequestParam("tipo") String tipo){

        ModelAndView mv = new ModelAndView("home/principal");

        if(nome != null && nomecientifico != null && tipo != null){
            TipoCultura tipoCultura = tipoCulturaService.buscarTipoCulturaPorTipo(tipo);
            if(tipoCultura != null){
                Cultura cultura = new Cultura(nome, nomecientifico, tipoCultura);
                culturaService.cadastrarCultura(cultura);
            }
        }

        Usuario user = usuarioService.buscarPorEmail(principal.getName());
        Iterable<TipoCultura> tipos = tipoCulturaService.listarTodasOsTiposCultura();
        Iterable<Cultura> culturas = culturaService.listarCulturasPorOrdemAlfabetica();

        mv.addObject("user", user);
        mv.addObject("tipos", tipos);
        mv.addObject("culturas", culturas);
        mv.addObject("local","pesquisador/cultura");
        mv.addObject("fragmento", "cultura");

        return mv;
    }

}
