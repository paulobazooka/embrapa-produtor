package br.embrapa.produtor.controllers;

import br.embrapa.produtor.constants.Link;
import br.embrapa.produtor.constants.Modulo;
import br.embrapa.produtor.constants.TipoUsuario;
import br.embrapa.produtor.models.Mensagem;
import br.embrapa.produtor.models.Role;
import br.embrapa.produtor.models.Usuario;
import br.embrapa.produtor.serviceimpl.EmailServiceImpl;
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

    @Autowired
    EmailServiceImpl emailService;


    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String home(){
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
        produtor.setTipo(TipoUsuario.PRODUTOR.name());
        produtor.setRoles(roles);
        produtor.setSenha(new BCryptPasswordEncoder().encode(senha));
        produtor = usuarioService.persistir(produtor);

        String link = Link.HEROKU.getUrl() + "/confirmar-cadastro/" + produtor.getId();
        String corpo  = "   Por favor, para confirmar o cadastro, clique no link:\n\n " + " " + link;
        String titulo = "Confirmação de cadastro de usuário.";
        String remetente = "Não responda esse email";

        Mensagem mensagem = new Mensagem(titulo,corpo,remetente);
        emailService.enviarEmail(mensagem, produtor.getEmail());

        model.addAttribute("email", email);
        model.addAttribute("confirmar","true");

        return "login";
    }

}
