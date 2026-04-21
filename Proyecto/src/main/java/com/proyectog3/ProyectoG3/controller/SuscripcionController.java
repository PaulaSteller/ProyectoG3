package com.proyectog3.ProyectoG3.controller;

import com.proyectog3.ProyectoG3.service.EmailService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
/**
 * 
 * 
 * 
 */


@Controller
public class SuscripcionController {

    @Autowired
    private EmailService emailService;

    @PostMapping("/suscribirse")
    public String suscribirse(@RequestParam String correoSuscripcion, HttpServletRequest request) {
        
        String asunto = "¡Bienvenido a las novedades de Refill!";
        String mensaje = "Hola,\n\n"
                + "¡Gracias por suscribirte a nuestro boletín!\n\n"
                + "A partir de ahora serás el primero en recibir nuestras mejores ofertas, novedades de productos, "
                + "notificaciones de stock y noticias tecnológicas.\n\n"
                + "¡Saludos de parte de todo el equipo de Refill!";

        // Envia el correo real
        emailService.enviarCorreo(correoSuscripcion, asunto, mensaje);

        // Redirige al usuario exactamente a la misma pestaña en la que estaba
        String referer = request.getHeader("Referer");
        return "redirect:" + (referer != null ? referer : "/");
    }
}