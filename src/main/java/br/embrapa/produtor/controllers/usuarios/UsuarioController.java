package br.embrapa.produtor.controllers.usuarios;

import br.embrapa.produtor.auxiliar.UsuarioUtils;
import br.embrapa.produtor.models.Usuario;
import br.embrapa.produtor.serviceimpl.UsuarioServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.security.Principal;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    UsuarioServiceImpl usuarioService;

    @Autowired
    UsuarioUtils usuarioUtils;


    @GetMapping
    public ModelAndView buscarUsuario(Principal principal){
        ModelAndView mv = retornaModelAndView();
        mv.addObject("user", this.retornarUsuario(principal));

        return mv;
    }

    @PostMapping
    public ModelAndView alterarSenha(Principal principal,
                                     @RequestParam("senha") String senha,
                                     @RequestParam("confirma") String confirma){

        ModelAndView mv = this.retornaModelAndView();
        Usuario user = this.retornarUsuario(principal);

        mv.addObject("user", user);

        if (!senha.isEmpty() && senha != null && !confirma.isEmpty() && confirma != null){
            if(senha.equals(confirma)){
                user.setSenha(new BCryptPasswordEncoder().encode(senha));
                usuarioService.persistir(user);
                mv.addObject("senha", "Senha alterada com sucesso!");
            }
        }else{
            mv.addObject("senha", "senha errada!");
        }



        return mv;
    }


    @RequestMapping(value = "/alterar", method = RequestMethod.POST)
    public ModelAndView alterarDados(Principal principal,
                                     @RequestParam("nome") String nome,
                                     @RequestParam("email") String email,
                                     @RequestParam("telefone") String telefone){

        ModelAndView mv = retornaModelAndView();
        Usuario usuario = this.retornarUsuario(principal);

        usuario.setEmail(email);
        usuario.setTelefone(telefone);
        usuario.setNome(nome);
        mv.addObject("user", usuario);

        if(usuarioService.persistir(usuario) != null){
            mv.addObject("atualizado", "Usuário atualizado com sucesso!");
        }else{
            mv.addObject("atualizado", "Erro ao atualizar usuário!");
        }

        return mv;
    }


    @RequestMapping(value = "/apagar", method = RequestMethod.POST)
    public String apagarConta(HttpSession session, Principal principal){
        Usuario user = usuarioService.buscarPorEmail(principal.getName());

        user.setAtivo(false);
        usuarioService.persistir(user);

        session.invalidate();
        return "login";
    }

    protected ModelAndView retornaModelAndView(){
        ModelAndView mv = new ModelAndView("home/principal");
        mv.addObject("local","usuario/usuario");
        mv.addObject("fragmento", "usuario");
        return mv;
    }


    protected Usuario retornarUsuario(Principal principal){
        return usuarioService.buscarPorEmail(principal.getName());
    }
}
