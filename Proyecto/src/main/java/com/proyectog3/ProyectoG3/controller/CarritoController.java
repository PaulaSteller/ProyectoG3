package com.proyectog3.ProyectoG3.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CarritoController {

    @GetMapping("/carrito")
    public String mostrarCarrito() {
        return "productos/carrito";
    }

}
