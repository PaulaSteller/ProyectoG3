package com.proyectog3.ProyectoG3.domain;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    @Column(unique = true, nullable = false)
    private String correo;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String rol = "USER"; // Por defecto todos son clientes

    public Usuario() {
    }

    public Usuario(String nombre, String correo, String password, String rol) {
        this.nombre = nombre;
        this.correo = correo;
        this.password = password;
        this.rol = rol;
    }
}