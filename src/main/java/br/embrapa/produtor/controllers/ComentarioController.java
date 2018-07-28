package br.embrapa.produtor.controllers;

import br.embrapa.produtor.models.*;
import br.embrapa.produtor.serviceimpl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.w3c.dom.Text;

import java.awt.*;
import java.security.Principal;
import java.time.LocalDateTime;

@Controller
@RequestMapping("/pesquisador")
public class ComentarioController {

    @Autowired
    ComentarioServiceImplements comentarioService;

    @Autowired
    FotoServiceImplements fotoService;

    @Autowired
    UsuarioServiceImpl usuarioService;

    @Autowired
    SolicitacaoServiceImplements solicitacaoService;

    @Autowired
    DoencaCulturaServiceImplements doencaCulturaService;

    @RequestMapping(value = "/inserir-comentario/{id}", method = RequestMethod.POST)
    public ModelAndView inserirComentario(@PathVariable("id") Long id,
                                          @RequestParam("descricao") String descricao,
                                          @RequestParam("do") String doenca,
                                          Principal principal){

        ModelAndView mv = new ModelAndView("home/principal");
        Usuario pesquisador = usuarioService.buscarPorEmail(principal.getName());
        mv.addObject("user", pesquisador);
        mv.addObject("local","solicitacao/solicitacao_detalhe_foto");
        mv.addObject("fragmento", "solicitacao_detalhe_foto");

        if(doenca != null) {

            Comentario comentario = new Comentario();
            comentario.setDiagnostico(descricao);
            comentario.setDoenca(doencaCulturaService.buscarDoencaCultura(doenca));
            comentario.setPesquisador(pesquisador);
            comentario.setData_comentario(LocalDateTime.now());
            comentario = comentarioService.inserirComentario(comentario);

            Foto foto = fotoService.buscarFoto(id);
            foto.getComentarios().add(comentario);
            foto = fotoService.cadastrarFoto(foto);

            Solicitacao solicitacao = solicitacaoService.buscarSolicitacaoPorFoto(foto);
            Cultura cultura = solicitacao.getCultura();
            Iterable<DoencaCultura> doencas = cultura.getDoencas();

            mv.addObject("doencas", doencas);
            mv.addObject("foto", foto);
        }


        return mv;
    }
}
