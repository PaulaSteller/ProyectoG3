package com.proyectog3.ProyectoG3.service;

/**
 * 
 * 
 */

import com.proyectog3.ProyectoG3.domain.Usuario;
import com.proyectog3.ProyectoG3.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public void run(String... args) throws Exception {
        // Crea el administrador automaticamente si no existe en la base de datos
        if (usuarioRepository.findByCorreo("admin@123").isEmpty()) {
            Usuario admin = new Usuario();
            admin.setNombre("Administrador Principal");
            admin.setCorreo("admin@123");
            admin.setPassword("12345");
            admin.setRol("ADMIN");
            usuarioRepository.save(admin);
            System.out.println("✅ Cuenta de administrador creada con exito.");
        }
    }
}