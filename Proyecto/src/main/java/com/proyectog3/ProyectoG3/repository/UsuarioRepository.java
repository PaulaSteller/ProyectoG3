/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyectog3.ProyectoG3.repository;

/**
 *
 * @author paulasteller
 */
import com.proyectog3.ProyectoG3.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    // 🔹 Buscar usuario por correo (para login y recuperación)
    Optional<Usuario> findByCorreo(String correo);
}