package br.embrapa.produtor.controllers.usuarios;

import br.embrapa.produtor.auxiliar.UsuarioUtils;
import br.embrapa.produtor.constants.Link;
import br.embrapa.produtor.models.Mensagem;
import br.embrapa.produtor.models.Usuario;
import br.embrapa.produtor.serviceimpl.EmailServiceImpl;
import br.embrapa.produtor.serviceimpl.UsuarioServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

    @Autowired
    EmailServiceImpl emailService;


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



    @RequestMapping(value = "/solicitar-nova-senha")
    public ModelAndView esqueciSenha(@RequestParam("email") String email){

        ModelAndView mv = new ModelAndView("login");
        Usuario user = usuarioService.buscarPorEmail(email);

        if (user != null){

            String corpo = "Caro " + user.getNome() + ".\n" +
                    "Para cadastrar uma nova senha favor clicar no link abaixo:\n\n" +
                    Link.HEROKU.getUrl() + Link.NOVA_SENHA.getUrl() + user.getId();

            Mensagem mensagem = new Mensagem("Solicitação de nova senha",
                                                corpo,"Não Responda");


            if (emailService.enviarEmail(mensagem, user.getEmail())){
                user.setNova_senha(true);
                usuarioService.persistir(user);
                mv.addObject("user", user);
            }else{
                System.out.println("E-mail não enviado");
                mv.addObject("nemail", "nemail");
            }
        } else {
            System.out.println("Usuário não existe no sistema");
            mv.addObject("nulo", "nulo");
        }

        return mv;
    }




    @RequestMapping(value = "/pagina-solicitacao-nova-senha/{id}")
    public String retornaPaginaDeNovaSenha(@PathVariable Long id, Model model){

        Usuario user = usuarioService.buscarPorId(id);
        System.out.println("******** " + user.getNome() + "**********");

        if ((user != null) && (user.isNova_senha())){
                model.addAttribute("id", user.getId());
                return "novasenha";
        }

        return "negado";
    }

    @RequestMapping(value = "/enviar-nova-senha", method = RequestMethod.POST)
    public ModelAndView recuperaSenha(@RequestParam("id") Long id,
                                      @RequestParam("senha") String senha,
                                      @RequestParam("confirma") String confirma){

        ModelAndView mv = new ModelAndView("login");
        Usuario user = usuarioService.buscarPorId(id);

        if ((user != null) && user.isNova_senha()){

            if (!senha.isEmpty() && senha != null && !confirma.isEmpty() && confirma != null){
                if(senha.equals(confirma)){
                    user.setNova_senha(false);
                    user.setSenha(new BCryptPasswordEncoder().encode(senha));
                    usuarioService.atualizarUsuario(user, user.getId());
                    mv.addObject("email", user.getEmail());
                }
            }else{

            }
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
