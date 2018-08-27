package br.embrapa.produtor.controllers;

import br.embrapa.produtor.constants.Modulo;
import br.embrapa.produtor.constants.TipoUsuario;
import br.embrapa.produtor.models.Role;
import br.embrapa.produtor.models.Usuario;
import br.embrapa.produtor.serviceimpl.RoleServiceImplents;
import br.embrapa.produtor.serviceimpl.UsuarioServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

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

    @RequestMapping("/pagina")
    public ModelAndView paginaAdministrador(Principal principal){

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
                                        @RequestParam("tipo") String tipo){

        ModelAndView mv = carregarPrincipal(principal);

        List<Role> roles = new ArrayList<>();

        if(tipo.equals(TipoUsuario.ADMINISTRADOR.name())) {
            roles.add(roleServiceImplents.buscarRole(Modulo.ROLE_PRODUTOR.name()));
            roles.add(roleServiceImplents.buscarRole(Modulo.ROLE_PESQUISADOR.name()));
            roles.add(roleServiceImplents.buscarRole(Modulo.ROLE_ADMIN.name()));
        }else{
            if(tipo.equals(TipoUsuario.PESQUISADOR.name())){
                roles.add(roleServiceImplents.buscarRole(Modulo.ROLE_PESQUISADOR.name()));
            }else{
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

     //   usuarioService.persistir(usuario);

        return mv;

    }



    @RequestMapping(value = "/cadastro", method = RequestMethod.GET)
    public ModelAndView crudUsuarios(Principal principal){
        ModelAndView mv = new ModelAndView("home/principal");

        Usuario user = usuarioService.buscarPorEmail(principal.getName());
        mv.addObject("user", user);
        mv.addObject("local","administracao/usuarios");
        mv.addObject("fragmento", "usuarios");


        return mv;
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




