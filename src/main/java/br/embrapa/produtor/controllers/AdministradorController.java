package br.embrapa.produtor.controllers;

import br.embrapa.produtor.models.Usuario;
import br.embrapa.produtor.serviceimpl.UsuarioServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

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
        mv.addObject("user", user);
        return mv;
    }
}
