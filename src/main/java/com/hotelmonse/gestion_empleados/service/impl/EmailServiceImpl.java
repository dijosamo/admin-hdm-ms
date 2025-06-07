package com.hotelmonse.gestion_empleados.service.impl;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.hotelmonse.gestion_empleados.service.EmailService;

@Service
public class EmailServiceImpl implements EmailService{

    private final JavaMailSender mailSender;

    public EmailServiceImpl(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void enviarEmail(String to, String subject, String texto) {
        SimpleMailMessage mensaje = new SimpleMailMessage();
        mensaje.setTo(to);
        mensaje.setSubject(subject);
        mensaje.setText(texto);
        // mensaje.setFrom("no-reply@tuempresa.com"); // opcional
        mailSender.send(mensaje);
    }
}