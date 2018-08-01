package br.embrapa.produtor.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import java.util.Properties;

/**
 *  @author Paulo Sérgio do Nascimento
 *
 */
@Configuration
public class SMTPConfiguration {

    protected final String USER_EMAIL = "embrapaprodutor@gmail.com";
    protected final String PASS_EMAIL = "ifspifsp";


    /**
     *
     *  @return retorna um objeto JavaMailSender com os parametros já configurados
     *
     */
    @Bean
    public JavaMailSender javaMail(){
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();

        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.ssl.enable", "true");
        props.put("mail.transport.protocol", "smtp");

        // Criar um objeto Authenticator para passar como parâmetro para o objeto da classe Session
        Authenticator auth = new Authenticator() {
            //Sobreescrever o método da classe
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                // Parâmentros de email de usuário e senha
                return new PasswordAuthentication(USER_EMAIL, PASS_EMAIL);
            }
        };
        // Seta as propriedades
        javaMailSender.setJavaMailProperties(props);
        // Cria a sessão com as propriedades e o autenticação
        Session session = Session.getInstance(props, auth);
        // Seta a sessão
        javaMailSender.setSession(session);

        return javaMailSender;
    }
}