package br.embrapa.produtor.serviceimpl;

import br.embrapa.produtor.interfaces.EmailService;
import br.embrapa.produtor.models.Mensagem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


@Service
public class EmailServiceImpl implements EmailService {


    @Autowired
    JavaMailSender javaMailSender;

    @Override
    public boolean enviarEmail(Mensagem mensagem, String destinatario) {

        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(destinatario);
        mail.setFrom(mensagem.getRemetente());
        mail.setSubject(mensagem.getTitulo());
        mail.setText(mensagem.getCorpo());

        try {
            javaMailSender.send(mail);
            return true;
        }catch (Exception e){
            return false;
        }

    }
}
