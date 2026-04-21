/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyectog3.ProyectoG3.repository;

/**
 *
 * @author samim
 */
import com.proyectog3.ProyectoG3.domain.Cita;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CitaRepository extends JpaRepository<Cita, Long> {

    // Para validar que una fecha no esté saturada (opcional a futuro)
    List<Cita> findByFechaCita(LocalDate fechaCita);
}
