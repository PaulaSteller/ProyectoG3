/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyectog3.ProyectoG3.repository;

/**
 *
 * @author paulasteller
 */

import com.proyectog3.ProyectoG3.domain.Producto;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProductoRepository extends JpaRepository<Producto, Long> {

    // Existente
    List<Producto> findByDisponibleTrue();

    // HU 15: búsqueda por nombre o marca, con filtro opcional de precio máximo
    @Query("SELECT p FROM Producto p WHERE " +
           "(:q IS NULL OR LOWER(p.nombre) LIKE LOWER(CONCAT('%', :q, '%')) " +
           "              OR LOWER(p.marca)  LIKE LOWER(CONCAT('%', :q, '%'))) " +
           "AND (:maxPrecio IS NULL OR p.precio <= :maxPrecio) " +
           "AND (:soloDisponibles = false OR p.disponible = true)")
    List<Producto> buscar(
        @Param("q")              String q,
        @Param("maxPrecio")      Double maxPrecio,
        @Param("soloDisponibles") boolean soloDisponibles
    );
}