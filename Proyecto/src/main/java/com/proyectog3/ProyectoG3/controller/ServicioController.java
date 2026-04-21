/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyectog3.ProyectoG3.controller;

/**
 *
 * @author paulasteller
 */

import com.proyectog3.ProyectoG3.domain.Cita;
import com.proyectog3.ProyectoG3.repository.CitaRepository;
import com.proyectog3.ProyectoG3.repository.ServicioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;

@Controller
@RequestMapping("/servicio")
public class ServicioController {

    @Autowired
    private ServicioRepository servicioRepository;

    @Autowired
    private CitaRepository citaRepository;           // <-- nuevo

    // ── Rutas existentes (no tocar) ──────────────────────────
    @GetMapping("/")
    public String inicioPrincipal(Model model) {
        var servicios = servicioRepository.findAll();
        model.addAttribute("servicios", servicios);
        model.addAttribute("page", "inicio");
        return "servicios/listado";
    }

    @GetMapping("/guia")
    public String guiaUso(Model model) {
        model.addAttribute("page", "guia");
        return "servicios/guia";
    }

    @GetMapping("/listado")
    public String inicio(Model model) {
        var servicios = servicioRepository.findAll();
        model.addAttribute("servicios", servicios);
        model.addAttribute("page", "servicio");
        return "servicios/listado";
    }

    // ── HU 19: Agendamiento ──────────────────────────────────

    // Muestra el formulario
    @GetMapping("/agendar")
    public String mostrarFormulario(Model model) {
        model.addAttribute("servicios", servicioRepository.findAll());
        model.addAttribute("cita", new Cita());
        model.addAttribute("fechaMinima", LocalDate.now().plusDays(1).toString());
        model.addAttribute("page", "servicio");
        return "servicios/agendar";
    }

    // Procesa el formulario
    @PostMapping("/agendar")
    public String procesarCita(
            @RequestParam String nombreCliente,
            @RequestParam String correoCliente,
            @RequestParam String fechaCita,
            @RequestParam Long servicioId,
            @RequestParam(required = false) String notas,
            Model model) {

        Cita cita = new Cita();
        cita.setNombreCliente(nombreCliente);
        cita.setCorreoCliente(correoCliente);
        cita.setFechaCita(LocalDate.parse(fechaCita));
        cita.setServicio(servicioRepository.findById(servicioId).orElseThrow());
        cita.setNotas(notas);
        cita.setEstado("PENDIENTE");

        citaRepository.save(cita);

        // Pasamos la cita guardada a la vista de confirmación
        model.addAttribute("cita", cita);
        model.addAttribute("page", "servicio");
        return "servicios/cita-confirmacion";
    }
}
