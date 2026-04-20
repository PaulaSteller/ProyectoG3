/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyectog3.ProyectoG3.controller;

/**
 *
 * @author samim
 */


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NosotrosController {

    @GetMapping("/nosotros")
    public String mostrarNosotros() {
        return "general/nosotros";
    }

}

