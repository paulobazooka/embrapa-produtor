package br.embrapa.produtor.serviceimpl;

import br.embrapa.produtor.interfaces.EmailService;
import br.embrapa.produtor.models.Mensagem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;


@Service
public class EmailServiceImpl implements EmailService {

    protected final String USER_MAIL = "embrapaprodutor@gmail.com";

    @Autowired
    private JavaMailSender mailSender;


    @Override
    public boolean enviarEmail(Mensagem mensagem, String destinatario) {

        MimeMessagePreparator messagePreparator = mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setFrom(USER_MAIL);
            messageHelper.setTo(destinatario);
            messageHelper.setSubject(mensagem.getTitulo());
            messageHelper.setText(mensagem.getCorpo());
        };

        try {
            mailSender.send(messagePreparator);
            return true;
        } catch (MailException e) {
            System.out.println("ERROR: " + e);
            return false;

        }
    }
}
