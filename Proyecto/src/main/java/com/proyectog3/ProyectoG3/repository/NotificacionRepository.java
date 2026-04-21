package com.proyectog3.ProyectoG3.repository;

/**
 * 
 * 
 */
import com.proyectog3.ProyectoG3.domain.Notificacion;
import com.proyectog3.ProyectoG3.domain.Producto;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificacionRepository extends JpaRepository<Notificacion, Long> {
    List<Notificacion> findByProductoAndEstado(Producto producto, String estado);
}