/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyectog3.ProyectoG3.controller;

import com.proyectog3.ProyectoG3.domain.Usuario;
import com.proyectog3.ProyectoG3.repository.UsuarioRepository;
import jakarta.servlet.http.HttpSession;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author paulasteller
 */

@Controller
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("/login")
    public String loginForm() {
        return "usuario/login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String correo,
                        @RequestParam String password,
                        HttpSession session,
                        Model model) {

        Optional<Usuario> user = usuarioRepository.findByCorreo(correo);

        if (user.isPresent() && user.get().getPassword().equals(password)) {
            session.setAttribute("usuario", user.get());
            return "redirect:/";
        } else {
            model.addAttribute("error", "Credenciales incorrectas");
            return "usuario/login";
        }
    }

    @GetMapping("/registro")
    public String registroForm(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "usuario/registro";
    }

    @PostMapping("/registro")
    public String registrar(@ModelAttribute Usuario usuario, Model model) {

        if (usuarioRepository.findByCorreo(usuario.getCorreo()).isPresent()) {
            model.addAttribute("error", "El correo ya está registrado");
            return "usuario/registro";
        }

        usuarioRepository.save(usuario);
        model.addAttribute("mensaje", "Registro exitoso");
        return "usuario/login";
    }

    @GetMapping("/recuperar")
    public String recuperarForm() {
        return "usuario/recuperar";
    }

    @PostMapping("/recuperar")
    public String recuperar(@RequestParam String correo, Model model) {

        Optional<Usuario> user = usuarioRepository.findByCorreo(correo);

        if (user.isPresent()) {
            model.addAttribute("mensaje", "Tu contraseña es: " + user.get().getPassword());
        } else {
            model.addAttribute("mensaje", "Correo no encontrado");
        }

        return "usuario/recuperar";
    }

    @GetMapping("/historial")
    public String historial(HttpSession session) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");

        if (usuario == null) {
            return "redirect:/login";
        }

        return "usuario/historial";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}
