package br.embrapa.produtor.controllers;

import br.embrapa.produtor.models.Cultura;
import br.embrapa.produtor.models.Solicitacao;
import br.embrapa.produtor.models.Usuario;
import br.embrapa.produtor.serviceimpl.CulturaServiceImplements;
import br.embrapa.produtor.serviceimpl.SolicitacaoServiceImplements;
import br.embrapa.produtor.serviceimpl.UsuarioServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@Controller
@RequestMapping("/produtor")
public class ProdutorController {

    @Autowired
    CulturaServiceImplements culturaService;

    @Autowired
    SolicitacaoServiceImplements solicitacaoService;

    @Autowired
    UsuarioServiceImpl usuarioService;

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


    /**
     *   Método que retorna o fragmento que contém as solicitações realizadas pelo usuário
     *
     * @param principal Retorna o usuário autenticado no sistema SpringSecurity
     * @param pageable  Retorna um objeto pageable para realizar a paginação
     * @param page      Retorna o número da página atual
     * @return
     */
    @RequestMapping(value = "solicitacoes", method = RequestMethod.GET)
    public ModelAndView getSolicitacoes(Principal principal,
                                        Pageable pageable,
                                        @RequestParam(defaultValue = "0") int page){

        ModelAndView mv = new ModelAndView("produtor/pagina_principal_produtor");
        Usuario user = usuarioService.buscarPorEmail(principal.getName());

        Iterable<Solicitacao> solicitacoes = solicitacaoService.listarTodasAsSolicitacoesPorProdutorId(user.getId(), pageable);
        mv.addObject("solicitacoes", solicitacoes);
        mv.addObject("page", page);
        Iterable<Cultura> culturas = culturaService.listarCulturasPorOrdemAlfabetica();
        mv.addObject("culturas", culturas);

       /* Solicitacao ultima = solicitacaoService.buscarUltimaSolicitacaoRealizada(user);
        if (ultima != null){
            mv.addObject("ultima", ultima);
        }*/

        mv.addObject("local", "solicitacao/solicitacao");
        mv.addObject("fragmento", "solicitacao");

        return mv;
    }


    @RequestMapping(value = "/minha-conta", method = RequestMethod.GET)
    public ModelAndView getMinhaConta(Principal principal) {
        ModelAndView mv = new ModelAndView("produtor/pagina_principal_produtor");
        mv.addObject("local", "usuario/usuario");
        mv.addObject("fragmento", "usuario");

        Usuario user = usuarioService.buscarPorEmail(principal.getName());
        mv.addObject("user", user);

        return mv;
    }
}
