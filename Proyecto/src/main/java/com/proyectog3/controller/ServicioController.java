/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyectog3.controller;

/**
 *
 * @author paulasteller
 */

import com.proyectog3.repository.ServicioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/servicio")
public class ServicioController {

    @Autowired
    private ServicioRepository servicioRepository;

    // Página principal de servicios
    @GetMapping("/")
    public String inicioPrincipal(Model model) {
        var servicios = servicioRepository.findAll();
        model.addAttribute("servicios", servicios);

        model.addAttribute("page", "inicio");

        return "servicios/listado";
    }

    // Listado de servicios
    @GetMapping("/listado")
    public String inicio(Model model) {
        var servicios = servicioRepository.findAll();
        model.addAttribute("servicios", servicios);

        model.addAttribute("page", "servicio");

        return "servicios/listado";
    }
}
