/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyectog3.ProyectoG3.controller;

import com.proyectog3.ProyectoG3.domain.OrdenServicio;
import com.proyectog3.ProyectoG3.repository.OrdenServicioRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
/**
 *
 * @author Axel, grupo4
 */



@Controller
public class SeguimientoController {

    @Autowired
    private OrdenServicioRepository ordenServicioRepository;

    @GetMapping("/seguimiento")
    public String mostrarFormulario() {
        return "servicios/seguimiento";
    }

    @PostMapping("/seguimiento")
    public String buscarOrden(@RequestParam("numeroOrden") String numeroOrden, Model model) {
        Optional<OrdenServicio> orden = ordenServicioRepository.findByNumeroOrden(numeroOrden);

        if (orden.isPresent()) {
            model.addAttribute("orden", orden.get());
        } else {
            model.addAttribute("error", "No se encontro ninguna orden con ese identificador.");
        }

        // Mantenemos el numero en el input despues de buscar
        model.addAttribute("numeroOrden", numeroOrden);
        return "servicios/seguimiento";
    }
}
