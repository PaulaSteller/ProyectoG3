package com.proyectog3.ProyectoG3.domain;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "notificacion")
public class Notificacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String correo;

    @ManyToOne
    @JoinColumn(name = "producto_id", nullable = false)
    private Producto producto;

    @Column(nullable = false)
    private String estado = "PENDIENTE";
}