/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.proyectog3.ProyectoG3.repository;

import com.proyectog3.ProyectoG3.domain.OrdenServicio;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Axel grupo4
 */


public interface OrdenServicioRepository extends JpaRepository<OrdenServicio, Long> {
    
    // Consulta automatica generada por Spring Data para buscar por el numero de orden exacto
    Optional<OrdenServicio> findByNumeroOrden(String numeroOrden);
    
}