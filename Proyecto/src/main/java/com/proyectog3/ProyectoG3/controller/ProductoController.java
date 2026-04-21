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
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ProductoController {

    @Autowired
    private ProductoRepository productoRepository;

    // Ruta original — muestra todos los disponibles
    @GetMapping("/tienda")
    public String tienda(Model model) {
        var productos = productoRepository.findByDisponibleTrue();
        model.addAttribute("productos", productos);
        model.addAttribute("page", "tienda");
        return "productos/catalogo";
    }

    // HU 15 — búsqueda con filtros
    @GetMapping("/tienda/buscar")
    public String buscar(
            @RequestParam(required = false) String q,
            @RequestParam(required = false) Double maxPrecio,
            @RequestParam(defaultValue = "false") boolean soloDisponibles,
            Model model) {

        var resultados = productoRepository.buscar(q, maxPrecio, soloDisponibles);

        model.addAttribute("productos", resultados);
        model.addAttribute("q", q);
        model.addAttribute("maxPrecio", maxPrecio);
        model.addAttribute("soloDisponibles", soloDisponibles);
        model.addAttribute("totalResultados", resultados.size());
        model.addAttribute("page", "tienda");
        return "productos/catalogo";   // misma vista, distintos datos
    }
}