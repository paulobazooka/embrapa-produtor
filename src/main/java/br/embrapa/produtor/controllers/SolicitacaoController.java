package br.embrapa.produtor.controllers;

import br.embrapa.produtor.models.*;
import br.embrapa.produtor.serviceimpl.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@Controller
public class SolicitacaoController {

    @Autowired
    SolicitacaoServiceImplements solicitacaoService;

    @Autowired
    ImageStorageService imageStorageService;

    @Autowired
    FotoServiceImplements fotoService;

    @Autowired
    UsuarioServiceImpl usuarioService;

    @Autowired
    CulturaServiceImplements culturaService;

    @Autowired
    DoencaCulturaServiceImplements doencaCulturaService;


    @RequestMapping(value = "/detalhes-solicitacao/{id}", method = RequestMethod.GET)
    public ModelAndView retornarSolicitacao(@PathVariable("id") Long id,
                                            Principal principal){
        ModelAndView mv = new ModelAndView("home/principal");
        Solicitacao solicitacao = solicitacaoService.buscarSolicitacao(id);
        Usuario user = usuarioService.buscarPorEmail(principal.getName());

        mv.addObject("user", user);
        mv.addObject("local","solicitacao/solicitacao_detalhe");
        mv.addObject("fragmento", "solicitacao_detalhe");

        if (solicitacao != null){
            mv.addObject("solicitacao", solicitacao);
            Iterable<Foto> fotos = solicitacao.getFotos();
            mv.addObject("fotos",fotos);
        }
        return mv;
    }

    @RequestMapping(value = "/detalhes-imagem/{id}", method = RequestMethod.GET)
    public ModelAndView retornaImagemComentarios(@PathVariable("id") Long id,
                                                 Principal principal){

        ModelAndView mv = new ModelAndView("home/principal");
        Usuario user = usuarioService.buscarPorEmail(principal.getName());
        mv.addObject("user", user);
        mv.addObject("local","solicitacao/solicitacao_detalhe_foto");
        mv.addObject("fragmento", "solicitacao_detalhe_foto");

        Foto foto = fotoService.buscarFoto(id);

        if(foto != null){
            Solicitacao solicitacao = solicitacaoService.buscarSolicitacaoPorFoto(foto);
            Cultura cultura = solicitacao.getCultura();
            Iterable<DoencaCultura> doencas = cultura.getDoencas();
            mv.addObject("doencas", doencas);
            mv.addObject("foto", foto);
            mv.addObject("cultura", cultura);
        }

        return mv;
    }
}
