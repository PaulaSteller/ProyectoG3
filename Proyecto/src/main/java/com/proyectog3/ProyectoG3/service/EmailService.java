package com.proyectog3.ProyectoG3.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String remitente;

    public void enviarCorreo(String destinatario, String asunto, String mensaje) {
        try {
            SimpleMailMessage email = new SimpleMailMessage();
            email.setFrom(remitente);
            email.setTo(destinatario);
            email.setSubject(asunto);
            email.setText(mensaje);
            mailSender.send(email);
            System.out.println("✅ CORREO ENVIADO CON ÉXITO A: " + destinatario);
        } catch (Exception e) {
            System.out.println("❌ ERROR AL ENVIAR CORREO. Verifica tu application.properties o tu conexión a internet.");
            e.printStackTrace();
        }
    }
}