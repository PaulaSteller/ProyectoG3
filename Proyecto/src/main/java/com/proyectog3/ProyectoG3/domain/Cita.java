/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyectog3.ProyectoG3.domain;

/**
 *
 * @author samim
 */
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import lombok.Data;

@Data
@Entity
@Table(name = "cita")
public class Cita implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    @NotNull
    private String nombreCliente;

    @Column(nullable = false, length = 100)
    @NotNull
    private String correoCliente;

    @Column(nullable = false)
    @NotNull
    private LocalDate fechaCita;

    @ManyToOne
    @JoinColumn(name = "servicio_id", nullable = false)
    private Servicio servicio;

    @Column(length = 255)
    private String notas;

    // Estado: PENDIENTE, CONFIRMADA, CANCELADA
    @Column(nullable = false)
    private String estado = "PENDIENTE";
}