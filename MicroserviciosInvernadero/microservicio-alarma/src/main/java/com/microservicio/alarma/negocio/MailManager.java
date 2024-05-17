package com.microservicio.alarma.negocio;

import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.messaging.MessagingException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

@Component
public class MailManager {

    @Autowired
    private JavaMailSender mailSender;

    public void enviarEmail(String destinatario, String asunto, String cuerpo) throws MessagingException {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("jorgeaestrada45@gmail.com");
        message.setTo(destinatario);
        message.setSubject(asunto);
        message.setText(cuerpo);
        mailSender.send(message);
        System.out.println("Correo enviado a: "+destinatario);
    }
}
