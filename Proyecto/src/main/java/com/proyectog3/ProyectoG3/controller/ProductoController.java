/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyectog3.ProyectoG3.controller;

/**
 *
 * @author paulasteller
 */

import com.proyectog3.ProyectoG3.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ProductoController {

    @Autowired
    private ProductoRepository productoRepository;

    @GetMapping("/tienda")
    public String tienda(Model model) {
        var productos = productoRepository.findByDisponibleTrue();
        model.addAttribute("productos", productos);

        // Marca la sección activa
        model.addAttribute("page", "tienda");

        return "productos/catalogo"; // templates/productos/catalogo.html
    }
}