package br.embrapa.produtor.controllers;

import br.embrapa.produtor.auxiliar.ExportCSV;
import br.embrapa.produtor.constants.Modulo;
import br.embrapa.produtor.constants.TipoUsuario;
import br.embrapa.produtor.models.Role;
import br.embrapa.produtor.models.Usuario;
import br.embrapa.produtor.serviceimpl.CsvQuery;
import br.embrapa.produtor.serviceimpl.RoleServiceImplents;
import br.embrapa.produtor.serviceimpl.SolicitacaoServiceImplements;
import br.embrapa.produtor.serviceimpl.UsuarioServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/administrador")
public class AdministradorController {

    @Autowired
    UsuarioServiceImpl usuarioService;

    @Autowired
    RoleServiceImplents roleServiceImplents;

    @Autowired
    SolicitacaoServiceImplements solicitacaoService;

    @Autowired
    CsvQuery csvQuery;

    @RequestMapping("/pagina")
    public ModelAndView paginaAdministrador(Principal principal) {

        ModelAndView mv = carregarPrincipal(principal);

        return mv;
    }


    @RequestMapping(value = "/cadastro", method = RequestMethod.POST)
    public ModelAndView cadastroUsuario(Principal principal,
                                        @RequestParam("nome") String nome,
                                        @RequestParam("email") String email,
                                        @RequestParam("telefone") String telefone,
                                        @RequestParam("senha") String senha,
                                        @RequestParam("confirma") String confirma,
                                        @RequestParam("tipo") String tipo) {

        ModelAndView mv = carregarPrincipal(principal);

        List<Role> roles = new ArrayList<>();

        if (tipo.equals(TipoUsuario.ADMINISTRADOR.name())) {
            roles.add(roleServiceImplents.buscarRole(Modulo.ROLE_PRODUTOR.name()));
            roles.add(roleServiceImplents.buscarRole(Modulo.ROLE_PESQUISADOR.name()));
            roles.add(roleServiceImplents.buscarRole(Modulo.ROLE_ADMIN.name()));
        } else {
            if (tipo.equals(TipoUsuario.PESQUISADOR.name())) {
                roles.add(roleServiceImplents.buscarRole(Modulo.ROLE_PESQUISADOR.name()));
            } else {
                roles.add(roleServiceImplents.buscarRole(Modulo.ROLE_PRODUTOR.name()));
            }
        }

        Usuario usuario = new Usuario();

        usuario.setAtivo(true);
        usuario.setData_cadastro(LocalDateTime.now());
        usuario.setTipo(tipo);
        usuario.setNome(nome);
        usuario.setEmail(email);
        usuario.setTelefone(telefone);
        usuario.setSenha(new BCryptPasswordEncoder().encode(senha));
        usuario.setRoles(roles);

        usuarioService.persistir(usuario);

        return mv;

    }


    @RequestMapping(value = "/cadastro", method = RequestMethod.GET)
    public ModelAndView crudUsuarios(Principal principal) {

        ModelAndView mv = new ModelAndView("home/principal");

        Usuario user = usuarioService.buscarPorEmail(principal.getName());
        Iterable<Usuario> users = usuarioService.listarUsuarios();

        mv.addObject("user", user);
        mv.addObject("local", "administracao/usuarios");
        mv.addObject("fragmento", "usuarios");
        mv.addObject("users", users);
        return mv;
    }


    @RequestMapping(value = "/alterar/{id}", method = RequestMethod.GET)
    public ModelAndView alterarUsuario(Principal principal,
                                       @PathVariable Long id) {
        ModelAndView mv = new ModelAndView("/home/principal");
        mv.addObject("local", "administracao/usuario_alterar");
        mv.addObject("fragmento", "usuario_alterar");

        Usuario admin = usuarioService.buscarPorEmail(principal.getName());
        Usuario usuario = usuarioService.buscarPorId(id);

        mv.addObject("user", admin);
        mv.addObject("usuario", usuario);

        return mv;
    }


    @RequestMapping(value = "/alterar", method = RequestMethod.POST)
    public ModelAndView alterarUsuarioPost(Principal principal,
                                           @RequestParam("id") Long id,
                                           @RequestParam("nome") String nome,
                                           @RequestParam("telefone") String telefone,
                                           @RequestParam("email") String email,
                                           @RequestParam("senha") String senha) {

        ModelAndView mv = carregarPrincipal(principal);

        Usuario banco = usuarioService.buscarPorId(id);
        String pass = new BCryptPasswordEncoder().encode(senha);


        if (!pass.equals(banco.getSenha())) {
            banco.setSenha(pass);
        }

        if (!banco.getNome().equals(nome)) {
            banco.setNome(nome);
        }

        if (!banco.getEmail().equals(email)) {
            banco.setEmail(email);
        }

        if (!banco.getTelefone().equals(telefone)) {
            banco.setTelefone(telefone);
        }

        if (usuarioService.persistir(banco) != null) {
            System.out.println("************ Usuário atualizado com sucesso! *********");
        }

        return mv;
    }


    @RequestMapping(value = "/csv", method = RequestMethod.GET)
    public void baixarCsv(HttpServletResponse response) throws IOException {

        ExportCSV e = new ExportCSV();
        List<Object[]> lista = csvQuery.listaInteligencia();

        File file = e.exportCsv(lista);

        response.setContentType("application/csv");
        response.setHeader("Content-Disposition", "attachment;filename=" + file.getName());

        BufferedInputStream inStrem = new BufferedInputStream(new FileInputStream(file));
        BufferedOutputStream outStream = new BufferedOutputStream(response.getOutputStream());

        byte[] buffer = new byte[1024];
        int bytesRead = 0;

        while ((bytesRead = inStrem.read(buffer)) != -1) {
            outStream.write(buffer, 0, bytesRead);
        }

        outStream.flush();
        inStrem.close();
    }


    protected ModelAndView carregarPrincipal(Principal principal){
        ModelAndView mv = new ModelAndView("home/principal");

        mv.addObject("local","administracao/administracao");
        mv.addObject("fragmento", "administracao");

        Usuario admin = usuarioService.buscarPorEmail(principal.getName());



        List<String> tipos = new ArrayList<>();

        tipos.add(TipoUsuario.PRODUTOR.toString());
        tipos.add(TipoUsuario.PESQUISADOR.toString());
        tipos.add(TipoUsuario.ADMINISTRADOR.toString());

        mv.addObject("user", admin);
        mv.addObject("tipos", tipos);

        return mv;
    }
}




