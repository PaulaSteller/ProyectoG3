package com.proyectog3.ProyectoG3.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ResenaController {

    @GetMapping("/resenas")
    public String mostrarResenas() {
        return "general/resenas";
    }

}
