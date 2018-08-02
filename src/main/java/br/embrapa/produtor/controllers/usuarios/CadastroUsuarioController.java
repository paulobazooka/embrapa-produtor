package br.embrapa.produtor.controllers.usuarios;

import br.embrapa.produtor.models.Usuario;
import br.embrapa.produtor.serviceimpl.UsuarioServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class CadastroUsuarioController {

    @Autowired
    UsuarioServiceImpl usuarioService;


    @RequestMapping(value = "/confirmar-cadastro/{id}", method = RequestMethod.GET)
    public String confirmarCadastroUsuario(@PathVariable Long id, Model model){

        Usuario usuario = usuarioService.buscarPorId(id);

        if (usuario != null){
            usuario.setAtivo(true);
            usuarioService.persistir(usuario);

            model.addAttribute("flag","true");
            model.addAttribute("email", usuario.getEmail());

            return "confirma";
        } else {
            model.addAttribute("flag","false");
            return "confirma";
        }
    }
}
