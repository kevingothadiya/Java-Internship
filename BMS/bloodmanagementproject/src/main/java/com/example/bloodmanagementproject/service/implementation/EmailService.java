package com.example.bloodmanagementproject.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Async
    public void sendEmail(String to, String subject, String body) {
        // Fresh sender instance every time — avoids stale/reset SMTP connections
        JavaMailSenderImpl original = (JavaMailSenderImpl) mailSender;
        JavaMailSenderImpl sender = new JavaMailSenderImpl();
        sender.setHost(original.getHost());
        sender.setPort(original.getPort());
        sender.setUsername(original.getUsername());
        sender.setPassword(original.getPassword());
        sender.setJavaMailProperties(original.getJavaMailProperties());

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);
        sender.send(message);
    }
}
