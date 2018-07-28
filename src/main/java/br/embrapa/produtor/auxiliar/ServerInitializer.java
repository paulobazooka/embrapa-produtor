package br.embrapa.produtor.auxiliar;
import br.embrapa.produtor.EmbrapaProdutorApplication;
import br.embrapa.produtor.constants.Modulo;
import br.embrapa.produtor.constants.TipoUsuario;
import br.embrapa.produtor.models.*;
import br.embrapa.produtor.serviceimpl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 *  @Autor Paulo Sérgio do Nascimento
 *
 *  Classe auxiliar para inicializar com persistência de dados
 *
 * */

@Component
public class ServerInitializer implements ApplicationRunner {

    @Autowired
    RoleServiceImplents roleServiceImplents;

    @Autowired
    UsuarioServiceImpl usuarioService;

    @Autowired
    SolicitacaoServiceImplements solicitacaoService;

    @Autowired
    CulturaServiceImplements culturaService;

    @Autowired
    FotoServiceImplements fotoService;

    @Autowired
    ImageStorageService imageStorage;

    @Autowired
    TipoCulturaServiceImplements tipoCulturaService;

    @Autowired
    DoencaCulturaServiceImplements doencaCulturaService;

    @Autowired
    ComentarioServiceImplements comentarioService;

    @Autowired
    JavaMailSender mailSender;

    @Autowired
    AmazonS3Client amazonS3Client;

    @Override
    public void run(ApplicationArguments applicationArguments){

        this.enviarEmailTeste();
        this.persistirRoles();
        this.persistirTiposCulturas();
        this.persistirCultura();
        this.persistirDoencaCultura();
        this.persistirUsuarioAdministrador();
        this.persistirUsuarioProdutor();
        this.persistirUsuarioPesquisador();

    }

    protected void persistirRoles() {
        Role admin = roleServiceImplents.buscarRole(Modulo.ROLE_ADMIN.name());
        Role pesq = roleServiceImplents.buscarRole(Modulo.ROLE_PESQUISADOR.name());
        Role prod = roleServiceImplents.buscarRole(Modulo.ROLE_PRODUTOR.name());

        if (admin == null) {
            admin = new Role(Modulo.ROLE_ADMIN.name());
            System.out.println(roleServiceImplents.cadastrarRole(admin));
        }

        if (pesq == null) {
            pesq = new Role(Modulo.ROLE_PESQUISADOR.name());
            System.out.println(roleServiceImplents.cadastrarRole(pesq));
        }

        if (prod == null) {
            prod = new Role(Modulo.ROLE_PRODUTOR.name());
            System.out.println(roleServiceImplents.cadastrarRole(prod));
        }
    }

    protected void persistirTiposCulturas(){

        List<TipoCultura> tipoCulturas = new ArrayList<>();

        TipoCultura outra = new TipoCultura();
        outra.setTipo("Outra");
        tipoCulturas.add(outra);

        TipoCultura grao = new TipoCultura();
        grao.setTipo("Grão");
        tipoCulturas.add(grao);

        TipoCultura rutacea = new TipoCultura();
        rutacea.setTipo("Rutácea");
        tipoCulturas.add(rutacea);

        TipoCultura malvacea = new TipoCultura();
        malvacea.setTipo("Malvaceae");
        tipoCulturas.add(malvacea);

        TipoCultura tuberculo = new TipoCultura();
        tuberculo.setTipo("Tubérculo");
        tipoCulturas.add(tuberculo);

        TipoCultura legume = new TipoCultura();
        legume.setTipo("Legume");
        tipoCulturas.add(legume);

        TipoCultura legseco = new TipoCultura();
        legseco.setTipo("Legume Seco");
        tipoCulturas.add(legseco);

        TipoCultura legufolha = new TipoCultura();
        legufolha.setTipo("Legume de Folha");
        tipoCulturas.add(legufolha);

        TipoCultura legfruto = new TipoCultura();
        legfruto.setTipo("Legume de Fruto");
        tipoCulturas.add(legfruto);

        TipoCultura oleaginosas = new TipoCultura();
        oleaginosas.setTipo("Planta Oleaginosa");
        tipoCulturas.add(oleaginosas);

        TipoCultura erva = new TipoCultura();
        erva.setTipo("Erva");
        tipoCulturas.add(erva);

        TipoCultura especiaria = new TipoCultura();
        especiaria.setTipo("Especiarias");
        tipoCulturas.add(especiaria);

        TipoCultura medicinal = new TipoCultura();
        medicinal.setTipo("Planta Medicinal");
        tipoCulturas.add(medicinal);

        TipoCultura arvfruto = new TipoCultura();
        arvfruto.setTipo("Árvore de Fruto");
        tipoCulturas.add(arvfruto);

        TipoCultura arvfrutoseco = new TipoCultura();
        arvfrutoseco.setTipo("Árvore de Fruto Seco");
        tipoCulturas.add(arvfrutoseco);

        TipoCultura arvore = new TipoCultura();
        arvore.setTipo("Árvore");
        tipoCulturas.add(arvore);

        TipoCultura forragem = new TipoCultura();
        forragem.setTipo("Planta de Forragem");
        tipoCulturas.add(forragem);

        TipoCultura composto = new TipoCultura();
        composto.setTipo("Composto");
        tipoCulturas.add(composto);

        TipoCultura conservacao = new TipoCultura();
        conservacao.setTipo("Conservação do Solo");
        tipoCulturas.add(conservacao);


        for (TipoCultura tipo: tipoCulturas) {
            tipoCulturaService.cadastrarTipoCultura(tipo);
        }

    }

    protected void persistirCultura() {

        List<Cultura> culturas = new ArrayList<>();

        Cultura outra = new Cultura("Outra", "Outra", tipoCulturaService.buscarTipoCulturaPorTipo("Outra"));
        culturas.add(outra);

        Cultura laranja = new Cultura("Laranja Pêra", "Citrus sinensis L. Osbeck", tipoCulturaService.buscarTipoCulturaPorTipo("Rutácea"));
        culturas.add(laranja);

        Cultura milho   = new Cultura("Milho", "Zea mays", tipoCulturaService.buscarTipoCulturaPorTipo("Grão"));
        culturas.add(milho);

        Cultura batata  = new Cultura("Batata", "Solanum tuberosum", tipoCulturaService.buscarTipoCulturaPorTipo("Tubérculo"));
        culturas.add(batata);

        Cultura bananaNanica = new Cultura("Banana Nanica", "Musa acuminata", tipoCulturaService.buscarTipoCulturaPorTipo("Erva"));
        culturas.add(bananaNanica);

        Cultura feijãoComum = new Cultura("Feijão Carioca", "Phaseolus vulgaris", tipoCulturaService.buscarTipoCulturaPorTipo("Legume Seco"));
        culturas.add(feijãoComum);

        Cultura eucalipto = new Cultura("Eucalipto", "Eucalyptus globulus", tipoCulturaService.buscarTipoCulturaPorTipo("Árvore"));
        culturas.add(eucalipto);

        Cultura cana = new Cultura("Cana de Açúcar", "Saccharum officinarum L.", tipoCulturaService.buscarTipoCulturaPorTipo("Erva"));
        culturas.add(cana);

        Cultura algodao = new Cultura("Algodão", "Gossypium hirsutum",tipoCulturaService.buscarTipoCulturaPorTipo("Malvaceae"));
        culturas.add(algodao);

        for (Cultura planta: culturas) {
            culturaService.cadastrarCultura(planta);
        }

    }

    protected void persistirDoencaCultura(){
        Cultura laranja = culturaService.buscarCulturaPorNome("Laranja Pêra");
        List<DoencaCultura> doencaLaranja = new ArrayList<>();

        DoencaCultura cancrocitrico = new DoencaCultura("Cancro Cítrico","Xanthomonas citri subsp. citri");
        doencaLaranja.add(doencaCulturaService.cadastrarDoencacultura(cancrocitrico));

        DoencaCultura antracnose = new DoencaCultura("Antracnose", "Colletotrichum truncatum");
        doencaLaranja.add(doencaCulturaService.cadastrarDoencacultura(antracnose));

        DoencaCultura bolorverde = new DoencaCultura("Bolor Verde", "Penicillium digitatum");
        doencaLaranja.add(doencaCulturaService.cadastrarDoencacultura(bolorverde));

        DoencaCultura clorose = new DoencaCultura("Clorose Variegada dos Citros", "Xylella fastidiosa");
        doencaLaranja.add(doencaCulturaService.cadastrarDoencacultura(clorose));

        laranja.setDoencas(doencaLaranja);
        culturaService.atualizarCultura(laranja);

        List<DoencaCultura> doencaMilho = new ArrayList<>();
        Cultura milho = culturaService.buscarCulturaPorNome("Milho");

        DoencaCultura cercosporiose = new DoencaCultura("Cercosporiose", "Cercospora zea-maydis ");
        doencaMilho.add(doencaCulturaService.cadastrarDoencacultura(cercosporiose));

        DoencaCultura ferrugem = new DoencaCultura(" Ferrugem Polissora ", "Puccinia polysora");
        doencaMilho.add(doencaCulturaService.cadastrarDoencacultura(ferrugem));

        DoencaCultura ferTropical = new DoencaCultura("Ferrugem Tropical", "Physopella zeae");
        doencaMilho.add(doencaCulturaService.cadastrarDoencacultura(ferTropical));

        DoencaCultura ferComum = new DoencaCultura("Ferrugem Comum","Puccinia sorghi");
        doencaMilho.add(doencaCulturaService.cadastrarDoencacultura(ferComum));

        DoencaCultura helmin = new DoencaCultura("Helmintosporiose", "Exserohilumturcicum");
        doencaMilho.add(doencaCulturaService.cadastrarDoencacultura(helmin));

        milho.setDoencas(doencaMilho);
        culturaService.atualizarCultura(milho);

        List<DoencaCultura> doencaBatata = new ArrayList<>();
        Cultura batata = culturaService.buscarCulturaPorNome("Batata");
        doencaBatata.add(doencaCulturaService.cadastrarDoencacultura(new DoencaCultura("Pinta Preta", "Alternaria grandis")));
        doencaBatata.add(doencaCulturaService.cadastrarDoencacultura(new DoencaCultura("Requeima", "Phytophthora infestans")));
        doencaBatata.add(doencaCulturaService.cadastrarDoencacultura(new DoencaCultura("Rizoctoniose","Rhizoctonia solani")));
        doencaBatata.add(doencaCulturaService.cadastrarDoencacultura(new DoencaCultura("Sarna Prateada","Helminthosporium solani")));
        batata.setDoencas(doencaBatata);
        culturaService.atualizarCultura(batata);

    }

    protected void persistirUsuarioAdministrador() {

        Usuario admin = usuarioService.buscarPorEmail("admin@ifsp");

        if (admin == null) {

            List<Role> roles = new ArrayList<>();
            roles.add(roleServiceImplents.buscarRole(Modulo.ROLE_PRODUTOR.name()));
            roles.add(roleServiceImplents.buscarRole(Modulo.ROLE_PESQUISADOR.name()));
            roles.add(roleServiceImplents.buscarRole(Modulo.ROLE_ADMIN.name()));

            admin = new Usuario();
            admin.setNome("Administrador");
            admin.setEmail("admin@ifsp");
            admin.setSenha(new BCryptPasswordEncoder().encode("ifsp"));
            admin.setData_cadastro(LocalDateTime.now());
            admin.setAtivo(true);
            admin.setTipo(TipoUsuario.ADMINISTRADOR.name());
            admin.setTelefone("123456789");
            admin.setRoles(roles);

            System.out.println(usuarioService.persistir(admin));
        }
    }

    protected void persistirUsuarioProdutor() {
        Usuario produtor = usuarioService.buscarPorEmail("prod@ifsp");

        if (produtor == null) {

            List<Role> roles = new ArrayList<>();
            roles.add(roleServiceImplents.buscarRole(Modulo.ROLE_PRODUTOR.name()));

            produtor = new Usuario();
            produtor.setNome("Produtor");
            produtor.setEmail("prod@ifsp");
            produtor.setSenha(new BCryptPasswordEncoder().encode("123"));
            produtor.setData_cadastro(LocalDateTime.now());
            produtor.setAtivo(true);
            produtor.setTipo(TipoUsuario.PRODUTOR.name());
            produtor.setTelefone("987654321");
            produtor.setRoles(roles);

          /*  for (int i = 0; i < 1; i++) {
                Cultura laranja = culturaService.buscarCulturaPorNome("Laranja Pêra");

                Foto foto1 = new Foto("/home/paulo/Documentos/TCC 2018/codes/project/embrapa/src/fotos-laranja/foto1.jpg");
                Foto foto2 = new Foto("/home/paulo/Documentos/TCC 2018/codes/project/embrapa/src/fotos-laranja/foto2.jpg");
                Foto foto3 = new Foto("/home/paulo/Documentos/TCC 2018/codes/project/embrapa/src/fotos-laranja/foto3.jpg");
                Foto foto4 = new Foto("/home/paulo/Documentos/TCC 2018/codes/project/embrapa/src/fotos-laranja/foto4.jpg");
                Foto foto5 = new Foto("/home/paulo/Documentos/TCC 2018/codes/project/embrapa/src/fotos-laranja/foto5.jpg");

                Solicitacao solicitacao = new Solicitacao();
                solicitacao.setCidade("Campinas");
                solicitacao.setEstado("São Paulo");
                solicitacao.setData_requisicao(LocalDateTime.now());
                solicitacao.setTitulo("Pés de Laranja com folhas queimadas");
                solicitacao.setDescricao("Percebi no dia de hoje que algumas plantas apresentaram sinais de queimadura, como se fosse uma fuligem escura.");
                solicitacao.setCultura(laranja);

                solicitacao.getFotos().add(fotoService.cadastrarFoto(foto1));
                solicitacao.getFotos().add(fotoService.cadastrarFoto(foto2));
                solicitacao.getFotos().add(fotoService.cadastrarFoto(foto3));
                solicitacao.getFotos().add(fotoService.cadastrarFoto(foto4));
                solicitacao.getFotos().add(fotoService.cadastrarFoto(foto5));

                solicitacao.setProdutor(usuarioService.persistir(produtor));
                solicitacaoService.cadastrarSolicitacao(solicitacao);
            }*/
        }
    }

    protected void persistirUsuarioPesquisador(){

        Usuario pesquisador = usuarioService.buscarPorEmail("pesq@ifsp");

        if (pesquisador == null) {

            List<Role> roles = new ArrayList<>();
            roles.add(roleServiceImplents.buscarRole(Modulo.ROLE_PESQUISADOR.name()));

            pesquisador = new Usuario();
            pesquisador.setNome("Pesquisador");
            pesquisador.setEmail("pesq@ifsp");
            pesquisador.setSenha(new BCryptPasswordEncoder().encode("123"));
            pesquisador.setData_cadastro(LocalDateTime.now());
            pesquisador.setAtivo(true);
            pesquisador.setTipo(TipoUsuario.PESQUISADOR.name());
            pesquisador.setTelefone("741963852");
            pesquisador.setRoles(roles);

            usuarioService.persistir(pesquisador);
        }

        Foto foto1 = null;

        try{
            List<Foto> fotos = IterableToList.toList(fotoService.listarTodasFotos());
            foto1 = fotos.get(0);
        }catch (Exception e){
            System.out.println("Erro ao buscar a primeira foto: " + e.getMessage());
        }



        if (foto1 != null) {

            Comentario comentario = new Comentario();
            comentario.setPesquisador(pesquisador);
            comentario.setDoenca(doencaCulturaService.buscarDoencaCultura("Cancro Cítrico"));
            comentario.setData_comentario(LocalDateTime.now());
            comentario.setDiagnostico("O cancro cítrico, causado pela bactéria Xanthomonas citri subsp. citri, afeta todas as espécies e variedades de citros de importância comercial. Com origem na Ásia, onde ocorre de forma endêmica em todos os países produtores, foi constatado pela primeira vez no Brasil em 1957, nos Estados de São Paulo e Paraná.\n" +
                    "\n" +
                    "Os impactos desta doença estão relacionados à desfolha de plantas, à depreciação da qualidade da produção pela presença de lesões em frutos, à redução na produção pela queda prematura de frutos e à restrição da comercialização da produção para áreas livres da doença.");

            comentario = comentarioService.inserirComentario(comentario);
            List<Comentario> comentarios = new ArrayList<>();
            comentarios.add(comentario);
            foto1.setComentarios(comentarios);
            fotoService.cadastrarFoto(foto1);
        }
    }

    protected void enviarEmailTeste(){

        SimpleMailMessage message = new SimpleMailMessage();

        message.setText("e-mail teste");
        message.setTo("embrapaprodutor@gmail.com");
        message.setFrom("embrapaprodutor@gmail.com");

        try {
            mailSender.send(message);
            // return "Email enviado com sucesso!";
        } catch (Exception e) {
            e.printStackTrace();
            // return "Erro ao enviar email.";
        }
    }
}