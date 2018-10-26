package br.embrapa.produtor.endpoint;

import br.embrapa.produtor.models.Cultura;
import br.embrapa.produtor.models.Foto;
import br.embrapa.produtor.models.Solicitacao;
import br.embrapa.produtor.models.Usuario;
import br.embrapa.produtor.serviceimpl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/solicitacao")
public class SolicitacaoEndPoint {

    @Autowired
    SolicitacaoServiceImplements solicitacaoService;

    @Autowired
    UsuarioServiceImpl usuarioService;


    @Autowired
    FotoServiceImplements fotoService;

    @Autowired
    ImageStorageService imageStorage;

    @Autowired
    AmazonS3Client amazonS3Client;

    @Autowired
    CulturaServiceImplements culturaService;


    @GetMapping
    public ResponseEntity getSolicitacoes(){
        Optional<Iterable<Solicitacao>> solicitacoes = Optional.ofNullable(solicitacaoService.listarTodasSolicitacoes());

        if(solicitacoes.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(solicitacoes);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{mensagem: nenhuma solicitação encontrada}");
    }


    @GetMapping("/{id}")
    public ResponseEntity getSolicitacao(@PathVariable Long id){
        Optional<Solicitacao> solicitacao = Optional.ofNullable(solicitacaoService.buscarSolicitacao(id));

        if(solicitacao.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(solicitacao.get());
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{mensagem: solicitação não encontrada}");
    }


    @GetMapping("/usuario/{id}")
    public ResponseEntity getSolicitacaoUsuario(@PathVariable Long id){
        Usuario usuario = usuarioService.buscarPorId(id);
        Optional<Iterable<Solicitacao>> solicitacoes = Optional.ofNullable(solicitacaoService.listarTodasSolicitacoesPorProdutor(usuario));

        if(solicitacoes.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(solicitacoes);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{mensagem: solicitação não encontrada para o usuario informado}");
    }


    @PostMapping
    public ResponseEntity postSolicitacao(@RequestBody Solicitacao solicitacao) {
        Solicitacao sol = solicitacaoService.cadastrarSolicitacao(solicitacao);

        return ResponseEntity.status(HttpStatus.CREATED).body(sol);
    }


    /**
     *
     * @param solicitacao   objeto solicitacao
     * @param fotos         figuras
     * @param cultura       nome da cultura
     * @param cidade        cidade
     * @param estado        estado
     * @param id            id do Usuario
     * @return              retorna um objeto solicitacao,,b,
     */
    @PutMapping
    public ResponseEntity putSolicitacao(@RequestBody Solicitacao solicitacao,
                                         @RequestParam MultipartFile[] fotos,
                                         @RequestParam String cultura,
                                         @RequestParam String cidade,
                                         @RequestParam String estado,
                                         @RequestParam Long id){

            Usuario produtor = usuarioService.buscarPorId(id);

            if(cultura != null) {
                Cultura planta = culturaService.buscarCulturaPorNome(cultura);

                if (planta != null) {
                    solicitacao.setCidade(cidade);
                    solicitacao.setEstado(estado);
                    solicitacao = solicitacaoService.cadastrarSolicitacao(solicitacao);

                    // Se a lista de fotos for maior que zero...
                    if (fotos.length > 0) {
                        for (MultipartFile file : fotos) {
                            String caminho = amazonS3Client.uploadFile(file, solicitacao.getId().toString(), produtor.getId().toString());

                            Foto foto = new Foto();
                            foto.setCaminho_foto(caminho);

                            // persiste a foto e retorna id para adicionar na solicitação
                            solicitacao.getFotos().add(fotoService.cadastrarFoto(foto));
                        }

                        // atualiza a solicitação com a lista de fotos
                        solicitacaoService.atualizarSolicitacao(solicitacao);
                    } else
                        System.out.println("falha no envio do arquivo");
                }

            }

            return ResponseEntity.status(HttpStatus.CREATED).body(solicitacao);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity deleteSolicitacao(@PathVariable Long id){

        return ResponseEntity.status(HttpStatus.OK).body("{mensagem: método não implementado}");
    }

}
