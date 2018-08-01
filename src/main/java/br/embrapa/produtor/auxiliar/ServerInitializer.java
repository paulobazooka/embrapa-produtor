package br.embrapa.produtor.auxiliar;

import br.embrapa.produtor.constants.Modulo;
import br.embrapa.produtor.constants.TipoUsuario;
import br.embrapa.produtor.models.*;
import br.embrapa.produtor.serviceimpl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

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
    AmazonS3Client amazonS3Client;

    @Autowired
    EmailServiceImpl emailService;

    @Override
    public void run(ApplicationArguments applicationArguments){

        this.enviarEmailTeste();
      /*  this.persistirRoles();
        this.persistirTiposCulturas();
        this.persistirCultura();
        this.persistirDoencaCultura();
        this.persistirUsuarioAdministrador();
        this.persistirUsuarioProdutor();
        this.persistirUsuarioPesquisador();*/

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

        if (tipoCulturaService.buscarTipoCulturaPorTipo("Outra") == null) {
            TipoCultura outra = new TipoCultura();
            outra.setTipo("Outra");
            tipoCulturas.add(outra);
        }

        if (tipoCulturaService.buscarTipoCulturaPorTipo("Grão") == null) {
            TipoCultura grao = new TipoCultura();
            grao.setTipo("Grão");
            tipoCulturas.add(grao);
        }

        if (tipoCulturaService.buscarTipoCulturaPorTipo("Rutácea") == null) {
            TipoCultura rutacea = new TipoCultura();
            rutacea.setTipo("Rutácea");
            tipoCulturas.add(rutacea);
        }

        if (tipoCulturaService.buscarTipoCulturaPorTipo("Malvaceae") == null) {
            TipoCultura malvacea = new TipoCultura();
            malvacea.setTipo("Malvaceae");
            tipoCulturas.add(malvacea);
        }

        if (tipoCulturaService.buscarTipoCulturaPorTipo("Tubérculo") == null) {
            TipoCultura tuberculo = new TipoCultura();
            tuberculo.setTipo("Tubérculo");
            tipoCulturas.add(tuberculo);
        }

        if (tipoCulturaService.buscarTipoCulturaPorTipo("Legume") == null) {
            TipoCultura legume = new TipoCultura();
            legume.setTipo("Legume");
            tipoCulturas.add(legume);
        }

        if (tipoCulturaService.buscarTipoCulturaPorTipo("Legume Seco") == null) {
            TipoCultura legseco = new TipoCultura();
            legseco.setTipo("Legume Seco");
            tipoCulturas.add(legseco);
        }

        if (tipoCulturaService.buscarTipoCulturaPorTipo("Legume de Folha") == null) {
            TipoCultura legufolha = new TipoCultura();
            legufolha.setTipo("Legume de Folha");
            tipoCulturas.add(legufolha);
        }

        if (tipoCulturaService.buscarTipoCulturaPorTipo("Legume de Fruto") == null) {
            TipoCultura legfruto = new TipoCultura();
            legfruto.setTipo("Legume de Fruto");
            tipoCulturas.add(legfruto);
        }

        if (tipoCulturaService.buscarTipoCulturaPorTipo("Planta Oleaginosa") == null) {
            TipoCultura oleaginosas = new TipoCultura();
            oleaginosas.setTipo("Planta Oleaginosa");
            tipoCulturas.add(oleaginosas);
        }

        if (tipoCulturaService.buscarTipoCulturaPorTipo("Erva") == null) {
            TipoCultura erva = new TipoCultura();
            erva.setTipo("Erva");
            tipoCulturas.add(erva);
        }

        if (tipoCulturaService.buscarTipoCulturaPorTipo("Especiarias") == null) {
            TipoCultura especiaria = new TipoCultura();
            especiaria.setTipo("Especiarias");
            tipoCulturas.add(especiaria);
        }

        if (tipoCulturaService.buscarTipoCulturaPorTipo("Planta Medicinal") == null) {
            TipoCultura medicinal = new TipoCultura();
            medicinal.setTipo("Planta Medicinal");
            tipoCulturas.add(medicinal);
        }

        if (tipoCulturaService.buscarTipoCulturaPorTipo("Árvore de Fruto") == null) {
            TipoCultura arvfruto = new TipoCultura();
            arvfruto.setTipo("Árvore de Fruto");
            tipoCulturas.add(arvfruto);
        }

        if (tipoCulturaService.buscarTipoCulturaPorTipo("Árvore de Fruto Seco") == null) {
            TipoCultura arvfrutoseco = new TipoCultura();
            arvfrutoseco.setTipo("Árvore de Fruto Seco");
            tipoCulturas.add(arvfrutoseco);
        }

        if (tipoCulturaService.buscarTipoCulturaPorTipo("Árvore") == null) {
            TipoCultura arvore = new TipoCultura();
            arvore.setTipo("Árvore");
            tipoCulturas.add(arvore);
        }

        if (tipoCulturaService.buscarTipoCulturaPorTipo("Planta de Forragem") == null) {
            TipoCultura forragem = new TipoCultura();
            forragem.setTipo("Planta de Forragem");
            tipoCulturas.add(forragem);
        }

        if (tipoCulturaService.buscarTipoCulturaPorTipo("Composto") == null) {
            TipoCultura composto = new TipoCultura();
            composto.setTipo("Composto");
            tipoCulturas.add(composto);
        }

        if (tipoCulturaService.buscarTipoCulturaPorTipo("Conservação do Solo") == null) {
            TipoCultura conservacao = new TipoCultura();
            conservacao.setTipo("Conservação do Solo");
            tipoCulturas.add(conservacao);
        }

        if(!tipoCulturas.isEmpty()) {
            for (TipoCultura tipo : tipoCulturas) {
                tipoCulturaService.cadastrarTipoCultura(tipo);
            }
        }

    }

    protected void persistirCultura() {

        List<Cultura> culturas = new ArrayList<>();

        if (culturaService.buscarCulturaPorNome("Outra") == null) {
            culturas.add(new Cultura("Outra", "Outra", tipoCulturaService.buscarTipoCulturaPorTipo("Outra")));
        }

        if (culturaService.buscarCulturaPorNome("Laranja Pêra") == null) {
            culturas.add(new Cultura("Laranja Pêra", "Citrus sinensis L. Osbeck", tipoCulturaService.buscarTipoCulturaPorTipo("Rutácea")));
        }

        if (culturaService.buscarCulturaPorNome("Milho") == null) {
            culturas.add(new Cultura("Milho", "Zea mays", tipoCulturaService.buscarTipoCulturaPorTipo("Grão")));
        }

        if (culturaService.buscarCulturaPorNome("Batata") == null) {
            culturas.add(new Cultura("Batata", "Solanum tuberosum", tipoCulturaService.buscarTipoCulturaPorTipo("Tubérculo")));
        }

        if (culturaService.buscarCulturaPorNome("Banana Nanica") == null) {
            culturas.add(new Cultura("Banana Nanica", "Musa acuminata", tipoCulturaService.buscarTipoCulturaPorTipo("Erva")));
        }

        if (culturaService.buscarCulturaPorNome("Feijão Carioca") == null) {
            culturas.add(new Cultura("Feijão Carioca", "Phaseolus vulgaris", tipoCulturaService.buscarTipoCulturaPorTipo("Legume Seco")));
        }

        if (culturaService.buscarCulturaPorNome("Eucalipto") == null) {
            culturas.add(new Cultura("Eucalipto", "Eucalyptus globulus", tipoCulturaService.buscarTipoCulturaPorTipo("Árvore")));
        }

        if (culturaService.buscarCulturaPorNome("Cana de Açúcar") == null){
            culturas.add(new Cultura("Cana de Açúcar", "Saccharum officinarum L.", tipoCulturaService.buscarTipoCulturaPorTipo("Erva")));
        }

        if (culturaService.buscarCulturaPorNome("Algodão") == null) {
            culturas.add(new Cultura("Algodão", "Gossypium hirsutum", tipoCulturaService.buscarTipoCulturaPorTipo("Malvaceae")));
        }

        if (!culturas.isEmpty()) {
            for (Cultura planta : culturas) {
                culturaService.cadastrarCultura(planta);
            }
        }

    }

    protected void persistirDoencaCultura(){

        // Doenças da Laranja
        Cultura laranja = culturaService.buscarCulturaPorNome("Laranja Pêra");
        List<DoencaCultura> doencaLaranja = new ArrayList<>();

        if (doencaCulturaService.buscarDoencaCultura("Cancro Cítrico") == null) {
            DoencaCultura cancrocitrico = new DoencaCultura("Cancro Cítrico", "Xanthomonas citri subsp. citri");
            doencaLaranja.add(doencaCulturaService.cadastrarDoencacultura(cancrocitrico));
        }

        if (doencaCulturaService.buscarDoencaCultura("Antracnose") == null) {
            DoencaCultura antracnose = new DoencaCultura("Antracnose", "Colletotrichum truncatum");
            doencaLaranja.add(doencaCulturaService.cadastrarDoencacultura(antracnose));
        }

        if (doencaCulturaService.buscarDoencaCultura("Bolor Verde") == null) {
            DoencaCultura bolorverde = new DoencaCultura("Bolor Verde", "Penicillium digitatum");
            doencaLaranja.add(doencaCulturaService.cadastrarDoencacultura(bolorverde));
        }

        if (doencaCulturaService.buscarDoencaCultura("Clorose Variegada dos Citros") == null) {
            DoencaCultura clorose = new DoencaCultura("Clorose Variegada dos Citros", "Xylella fastidiosa");
            doencaLaranja.add(doencaCulturaService.cadastrarDoencacultura(clorose));
        }

        if (!doencaLaranja.isEmpty()) {
            laranja.setDoencas(doencaLaranja);
            culturaService.atualizarCultura(laranja);
        }


        // Doenças do Milho
        List<DoencaCultura> doencaMilho = new ArrayList<>();
        Cultura milho = culturaService.buscarCulturaPorNome("Milho");

        if (doencaCulturaService.buscarDoencaCultura("Cercosporiose") == null) {
            DoencaCultura cercosporiose = new DoencaCultura("Cercosporiose", "Cercospora zea-maydis ");
            doencaMilho.add(doencaCulturaService.cadastrarDoencacultura(cercosporiose));
        }

        if (doencaCulturaService.buscarDoencaCultura("Ferrugem Polissora") == null) {
            DoencaCultura ferrugem = new DoencaCultura("Ferrugem Polissora", "Puccinia polysora");
            doencaMilho.add(doencaCulturaService.cadastrarDoencacultura(ferrugem));
        }

        if (doencaCulturaService.buscarDoencaCultura("Ferrugem Tropical") == null) {
            DoencaCultura ferTropical = new DoencaCultura("Ferrugem Tropical", "Physopella zeae");
            doencaMilho.add(doencaCulturaService.cadastrarDoencacultura(ferTropical));
        }

        if (doencaCulturaService.buscarDoencaCultura("Ferrugem Comum") == null) {
            DoencaCultura ferComum = new DoencaCultura("Ferrugem Comum", "Puccinia sorghi");
            doencaMilho.add(doencaCulturaService.cadastrarDoencacultura(ferComum));
        }

        if (doencaCulturaService.buscarDoencaCultura("Helmintosporiose") == null) {
            DoencaCultura helmin = new DoencaCultura("Helmintosporiose", "Exserohilumturcicum");
            doencaMilho.add(doencaCulturaService.cadastrarDoencacultura(helmin));
        }

        if (!doencaMilho.isEmpty()) {
            milho.setDoencas(doencaMilho);
            culturaService.atualizarCultura(milho);
        }


        // Doenças da Batata
        List<DoencaCultura> doencaBatata = new ArrayList<>();
        Cultura batata = culturaService.buscarCulturaPorNome("Batata");

        if (doencaCulturaService.buscarDoencaCultura("Pinta Preta") == null) {
            doencaBatata.add(doencaCulturaService.cadastrarDoencacultura(new DoencaCultura("Pinta Preta", "Alternaria grandis")));
        }

        if (doencaCulturaService.buscarDoencaCultura("Requeima") == null) {
            doencaBatata.add(doencaCulturaService.cadastrarDoencacultura(new DoencaCultura("Requeima", "Phytophthora infestans")));
        }

        if (doencaCulturaService.buscarDoencaCultura("Rizoctoniose") == null) {
            doencaBatata.add(doencaCulturaService.cadastrarDoencacultura(new DoencaCultura("Rizoctoniose", "Rhizoctonia solani")));
        }

        if (doencaCulturaService.buscarDoencaCultura("Sarna Prateada") == null) {
            doencaBatata.add(doencaCulturaService.cadastrarDoencacultura(new DoencaCultura("Sarna Prateada", "Helminthosporium solani")));
        }

        if (!doencaBatata.isEmpty()) {
            batata.setDoencas(doencaBatata);
            culturaService.atualizarCultura(batata);
        }

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

            usuarioService.persistir(produtor);
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
    }

    protected void enviarEmailTeste(){
        Mensagem mensagem = new Mensagem("e-mail de teste", "A aplicação está iniciando a operação de deploy!","No-Replay");
        emailService.enviarEmail(mensagem, "psn.ads.ifsp@gmail.com");

    }
}