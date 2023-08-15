package com.rf.ecommerce.Service.Mail;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import java.util.Properties;

@Service

public class EmailService {
    @Autowired
    private  JavaMailSender javaMailSender;

    public void sendMail(String email,String subject,String content){
        SimpleMailMessage message=new SimpleMailMessage();
        message.setTo(email);
        message.setSubject(subject);
        message.setText(content);
        javaMailSender.send(message);
        System.out.println("Mail gönderidli");
    }

}
