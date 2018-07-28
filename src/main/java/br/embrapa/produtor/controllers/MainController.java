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
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
public class MainController {


    @Autowired
    UsuarioServiceImpl usuarioService;

    @Autowired
    RoleServiceImplents roleServiceImplents;


    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String home(Model model){

        return "login";
    }


    @GetMapping(value = "/cadastrar-produtor")
    public String cadastroProdutor(){
        return "home/cadastro_produtor";
    }



    @PostMapping(value = "/cadastrar-novo-produtor")
    public String cadastrarNovoProdutor(Model model,
                                    @RequestParam("nome") String nome,
                                    @RequestParam("email") String email,
                                    @RequestParam("telefone") String telefone,
                                    @RequestParam("senha") String senha){

        List<Role> roles = new ArrayList<>();
        Role role = roleServiceImplents.buscarRole(Modulo.ROLE_PRODUTOR.name());
        roles.add(role);

        Usuario produtor = new Usuario();
        produtor.setData_cadastro(LocalDateTime.now());
        produtor.setNome(nome);
        produtor.setEmail(email);
        produtor.setTelefone(telefone);
        produtor.setAtivo(true);
        produtor.setTipo(TipoUsuario.PRODUTOR.name());
        produtor.setRoles(roles);
        produtor.setSenha(new BCryptPasswordEncoder().encode(senha));
        usuarioService.persistir(produtor);

        model.addAttribute("email", email);

        return "login";
    }

}
