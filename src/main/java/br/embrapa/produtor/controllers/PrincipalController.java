package br.embrapa.produtor.controllers;

import br.embrapa.produtor.constants.TipoUsuario;
import br.embrapa.produtor.models.Cultura;
import br.embrapa.produtor.models.Foto;
import br.embrapa.produtor.models.Solicitacao;
import br.embrapa.produtor.models.Usuario;
import br.embrapa.produtor.serviceimpl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.time.LocalDateTime;

@Controller
public class PrincipalController {

    @Autowired
    private UsuarioServiceImpl usuarioService;

    @Autowired
    private CulturaServiceImplements culturaService;

    @Autowired
    private SolicitacaoServiceImplements solicitacaoService;

    @Autowired
    FotoServiceImplements fotoService;

    @Autowired
    ImageStorageService imageStorage;

    @Autowired
    AmazonS3Client amazonS3Client;

    @RequestMapping("/")
    public ModelAndView principal(Principal principal,
                                  Pageable pageable,
                                  @RequestParam(defaultValue = "0") int page){

        ModelAndView mv = new ModelAndView("home/principal");
        mv.addObject("local", "solicitacao/solicitacao");
        mv.addObject("fragmento", "solicitacao");

        Usuario user = usuarioService.buscarPorEmail(principal.getName());
        Iterable<Cultura> culturas = culturaService.listarCulturasPorOrdemAlfabetica();

        mv.addObject("page", page);
        mv.addObject("culturas", culturas);
        mv.addObject("user", user);


        if (user.getTipo().equals(TipoUsuario.PRODUTOR.name())){
            Iterable<Solicitacao> solicitacoes = solicitacaoService.listarTodasAsSolicitacoesPorProdutorId(user.getId(), pageable);
            mv.addObject("solicitacoes", solicitacoes);
            Solicitacao ultima = solicitacaoService.buscarUltimaSolicitacaoRealizada(user);
            if (ultima != null){
                mv.addObject("ultima", ultima);
            }
        }else{
            Page<Solicitacao> solicitacoes = solicitacaoService.listarTodasAsSolicitacoesPorPagina(pageable);
            mv.addObject("solicitacoes", solicitacoes);
        }

        return mv;
    }


    @PostMapping(value = "/storage")
    public String storage(@RequestParam MultipartFile[] fotos,
                          @RequestParam String titulo,
                          @RequestParam String descricao,
                          @RequestParam String cultura,
                          @RequestParam String cidades,
                          @RequestParam String estados,
                          Principal principal){

        Usuario produtor = usuarioService.buscarPorEmail(principal.getName());

        if(cultura != null) {
            Cultura planta = culturaService.buscarCulturaPorNome(cultura);

            if (planta != null) {

                Solicitacao solicitacao = new Solicitacao();
                solicitacao.setData_requisicao(LocalDateTime.now());
                solicitacao.setProdutor(produtor);
                solicitacao.setCultura(culturaService.cadastrarCultura(planta));
                solicitacao.setTitulo(titulo);
                solicitacao.setCidade(cidades);
                solicitacao.setEstado(estados);
                solicitacao.setDescricao(descricao);
                solicitacao = solicitacaoService.cadastrarSolicitacao(solicitacao);

                // Se a lista de fotos for maior que zero...
                if (fotos.length > 0) {
                    for (MultipartFile file: fotos) {
/*
                        String caminho = imageStorage.salvarImagens(file, solicitacao.getId().toString(), produtor.getId().toString());
*/
                        String caminho = amazonS3Client.uploadFile(file, solicitacao.getId().toString(), produtor.getId().toString());

                        Foto foto = new Foto();
                        foto.setCaminho_foto(caminho);

                        // persiste a foto e retorna id para adicionar na solicitação
                        solicitacao.getFotos().add(fotoService.cadastrarFoto(foto));
                    }

                    // atualiza a solicitação com a lista de fotos
                    solicitacao = solicitacaoService.atualizarSolicitacao(solicitacao);
                } else System.out.println("falha no envio do arquivo");
            }

        }

        return "redirect:/";
    }
}
