package br.embrapa.produtor.email;


import br.embrapa.produtor.models.Mensagem;
import br.embrapa.produtor.serviceimpl.EmailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/email")
public class EmailController {

    @Autowired
    EmailServiceImpl emailService;

    @GetMapping
    public String envioEmail(){

        Mensagem mensagem = new Mensagem("e-mail de Teste","envio de mensagem de teste de acordo com o controller", "No-Reply");
        emailService.enviarEmail(mensagem, "psn.ads.ifsp@gmail.com");

        return "envio de email...";
    }
}
