package br.embrapa.produtor.controllers;

import br.embrapa.produtor.constants.TipoUsuario;
import br.embrapa.produtor.models.Usuario;
import br.embrapa.produtor.serviceimpl.UsuarioServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/administrador")
public class AdministradorController {

    @Autowired
    UsuarioServiceImpl usuarioService;

    @RequestMapping("/pagina")
    public ModelAndView paginaAdministrador(Principal principal){
        ModelAndView mv = new ModelAndView("home/principal");

        mv.addObject("local","administracao/administracao");
        mv.addObject("fragmento", "administracao");

        Usuario user = usuarioService.buscarPorEmail(principal.getName());

        List<String> tipos = new ArrayList<>();

        tipos.add(TipoUsuario.PRODUTOR.toString());
        tipos.add(TipoUsuario.PESQUISADOR.toString());
        tipos.add(TipoUsuario.ADMINISTRADOR.toString());

       // tipos.forEach(tipo -> System.out.println(tipo));


        mv.addObject("user", user);
        mv.addObject("tipos", tipos);
        return mv;
    }
}
