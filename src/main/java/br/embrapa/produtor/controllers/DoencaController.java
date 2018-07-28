package br.embrapa.produtor.controllers;

import br.embrapa.produtor.auxiliar.IterableToList;
import br.embrapa.produtor.models.*;
import br.embrapa.produtor.serviceimpl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.List;

@Controller
public class DoencaController {

    @Autowired
    UsuarioServiceImpl usuarioService;

    @Autowired
    CulturaServiceImplements culturaService;

    @Autowired
    DoencaCulturaServiceImplements doencaCulturaService;

    @Autowired
    FotoServiceImplements fotoService;

    @Autowired
    SolicitacaoServiceImplements solicitacaoService;


    @RequestMapping(value = "/doenca", method = RequestMethod.GET)
    public ModelAndView paginaDoenca(Principal principal){
        ModelAndView mv = new ModelAndView("home/principal");

        mv.addObject("local","pesquisador/doenca");
        mv.addObject("fragmento", "doenca");

        Usuario user = usuarioService.buscarPorEmail(principal.getName());
        mv.addObject("user", user);

        Iterable<Cultura> culturas = culturaService.listarCulturasPorOrdemAlfabetica();
        mv.addObject("culturas", culturas);

        return mv;
    }

    @RequestMapping(value = "/doenca", method = RequestMethod.POST)
    public ModelAndView cadastrarDoenca(Principal principal,
                                        @RequestParam("cultura") String cultura,
                                        @RequestParam("nome") String nome,
                                        @RequestParam("nomecientifico") String nomecientifico){
        ModelAndView mv = new ModelAndView("home/principal");

        mv.addObject("local","pesquisador/doenca");
        mv.addObject("fragmento", "doenca");

        Usuario user = usuarioService.buscarPorEmail(principal.getName());
        mv.addObject("user", user);

        Iterable<Cultura> culturas = culturaService.listarCulturasPorOrdemAlfabetica();
        mv.addObject("culturas", culturas);

        if(cultura != null && nome != null && nomecientifico != null) {
            Cultura planta = culturaService.buscarCulturaPorNome(cultura);
            DoencaCultura novaDoenca = new DoencaCultura(nome, nomecientifico);
            planta.getDoencas().add(doencaCulturaService.cadastrarDoencacultura(novaDoenca));
            culturaService.atualizarCultura(planta);
        }

        return mv;
    }


    @RequestMapping(value = "/doenca-comentario/{id}", method = RequestMethod.POST)
    public ModelAndView cadastrarDoencaComentario(Principal principal,
                                                  @PathVariable("id") Long id,
                                                  @RequestParam("cultura") String cultura,
                                                  @RequestParam("nome") String nome,
                                                  @RequestParam("nomecientifico") String nomecientifico){


        ModelAndView mv = new ModelAndView("home/principal");
        Usuario user = usuarioService.buscarPorEmail(principal.getName());
        mv.addObject("user", user);
        mv.addObject("local","solicitacao/solicitacao_detalhe_foto");
        mv.addObject("fragmento", "solicitacao_detalhe_foto");

        Foto foto = fotoService.buscarFoto(id);
        Solicitacao solicitacao = solicitacaoService.buscarSolicitacaoPorFoto(foto);

        Cultura planta = solicitacao.getCultura();

        DoencaCultura novaDoenca = new DoencaCultura(nome, nomecientifico);
        novaDoenca = doencaCulturaService.cadastrarDoencacultura(novaDoenca);

        planta.getDoencas().add(novaDoenca);
        culturaService.atualizarCultura(planta);

        Iterable<DoencaCultura> doencas = planta.getDoencas();
        mv.addObject("doencas", doencas);
        mv.addObject("foto", foto);
        mv.addObject("cultura", planta);

        if (foto != null){

        }
        return mv;
    }


}
