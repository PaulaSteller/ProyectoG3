package com.proyectog3.ProyectoG3.controller;

/**
 * 
 * 
 */
import com.proyectog3.ProyectoG3.domain.Notificacion;
import com.proyectog3.ProyectoG3.domain.Producto;
import com.proyectog3.ProyectoG3.domain.Usuario;
import com.proyectog3.ProyectoG3.repository.NotificacionRepository;
import com.proyectog3.ProyectoG3.repository.ProductoRepository;
import com.proyectog3.ProyectoG3.service.EmailService;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired private ProductoRepository productoRepository;
    @Autowired private NotificacionRepository notificacionRepository;
    @Autowired private EmailService emailService;

    // Metodo de seguridad para verificar si es ADMIN
    private boolean isAdmin(HttpSession session) {
        Usuario u = (Usuario) session.getAttribute("usuario");
        return u != null && "ADMIN".equals(u.getRol());
    }

    @GetMapping("/inventario")
    public String inventario(Model model, HttpSession session) {
        if (!isAdmin(session)) return "redirect:/login";
        model.addAttribute("productos", productoRepository.findAll());
        return "admin/inventario";
    }

    // --- CRUD ---
    @GetMapping("/nuevo")
    public String nuevoProducto(Model model, HttpSession session) {
        if (!isAdmin(session)) return "redirect:/login";
        model.addAttribute("producto", new Producto());
        return "admin/formulario";
    }

    @GetMapping("/editar/{id}")
    public String editarProducto(@PathVariable Long id, Model model, HttpSession session) {
        if (!isAdmin(session)) return "redirect:/login";
        Producto p = productoRepository.findById(id).orElse(new Producto());
        model.addAttribute("producto", p);
        return "admin/formulario";
    }

    @PostMapping("/guardar")
    public String guardarProducto(Producto producto, @RequestParam("archivo") MultipartFile archivo, HttpSession session) {
        if (!isAdmin(session)) return "redirect:/login";
        try {
            if (!archivo.isEmpty()) {
                producto.setImagen(archivo.getBytes());
            } else if (producto.getId() != null) {
                // Mantiene la imagen actual si se edita sin subir una nueva
                Producto actual = productoRepository.findById(producto.getId()).orElse(null);
                if (actual != null) producto.setImagen(actual.getImagen());
            }
            productoRepository.save(producto);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "redirect:/admin/inventario";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarProducto(@PathVariable Long id, HttpSession session) {
        if (!isAdmin(session)) return "redirect:/login";
        productoRepository.deleteById(id);
        return "redirect:/admin/inventario";
    }

    // --- CONTROL DE STOCK Y HU 20 ---
    @PostMapping("/agotar")
    public String agotar(@RequestParam Long id, HttpSession session) {
        if (!isAdmin(session)) return "redirect:/login";
        Producto p = productoRepository.findById(id).orElse(null);
        if (p != null) {
            p.setDisponible(false);
            productoRepository.save(p);
        }
        return "redirect:/admin/inventario";
    }

    @PostMapping("/restablecer")
    public String restablecer(@RequestParam Long id, HttpSession session) {
        if (!isAdmin(session)) return "redirect:/login";
        Producto p = productoRepository.findById(id).orElse(null);
        if (p != null) {
            p.setDisponible(true);
            productoRepository.save(p);

            // HU 20: Disparador de correo real
            List<Notificacion> pendientes = notificacionRepository.findByProductoAndEstado(p, "PENDIENTE");
            for (Notificacion n : pendientes) {
                String asunto = "¡" + p.getNombre() + " disponible en Refill!";
                String mensaje = "Hola, nos alegra informarte que el producto " + p.getNombre() + " vuelve a estar en stock. ¡Ingresa a nuestra tienda para adquirirlo!";
                
                emailService.enviarCorreo(n.getCorreo(), asunto, mensaje);
                
                n.setEstado("ENVIADA"); // Desactiva la notificacion
                notificacionRepository.save(n);
            }
        }
        return "redirect:/admin/inventario";
    }
}