/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyectog3.ProyectoG3.domain;

import jakarta.persistence.*;
import java.time.LocalDate;
import lombok.Data;

/**
 *
 * @author Axel, grupo4
 */



@Data
@Entity
@Table(name = "orden_servicio")
public class OrdenServicio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="numero_orden", unique = true, nullable = false)
    private String numeroOrden;

    private String cliente;
    private String equipo;
    private String estado; 

    @Column(name="fecha_estimada")
    private LocalDate fechaEstimada;

}