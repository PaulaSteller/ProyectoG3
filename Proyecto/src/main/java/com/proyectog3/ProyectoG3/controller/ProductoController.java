package com.proyectog3.ProyectoG3.controller;

import com.proyectog3.ProyectoG3.domain.Notificacion;
import com.proyectog3.ProyectoG3.domain.Producto;
import com.proyectog3.ProyectoG3.domain.Usuario;
import com.proyectog3.ProyectoG3.repository.NotificacionRepository;
import com.proyectog3.ProyectoG3.repository.ProductoRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ProductoController {

    @Autowired private ProductoRepository productoRepository;
    @Autowired private NotificacionRepository notificacionRepository;

    @GetMapping("/tienda")
    public String tienda(Model model, @RequestParam(required = false) String notificado) {
        model.addAttribute("productos", productoRepository.findAll());
        model.addAttribute("page", "tienda");
        if ("true".equals(notificado)) {
            model.addAttribute("mensajeNotificacion", "¡Notificacion activada! Te enviaremos un correo cuando haya stock.");
        }
        return "productos/catalogo";
    }

    @GetMapping("/tienda/buscar")
    public String buscar(@RequestParam(required = false) String q, @RequestParam(required = false) Double maxPrecio, @RequestParam(defaultValue = "false") boolean soloDisponibles, Model model) {
        model.addAttribute("productos", productoRepository.buscar(q, maxPrecio, soloDisponibles));
        model.addAttribute("q", q);
        model.addAttribute("maxPrecio", maxPrecio);
        model.addAttribute("soloDisponibles", soloDisponibles);
        model.addAttribute("page", "tienda");
        return "productos/catalogo";   
    }

    @PostMapping("/tienda/notificar")
    public String registrarNotificacion(@RequestParam Long productoId, HttpSession session) {
        Usuario usuarioLogueado = (Usuario) session.getAttribute("usuario");
        
        if (usuarioLogueado == null) {
            return "redirect:/login";
        }

        Producto p = productoRepository.findById(productoId).orElse(null);
        if (p != null) {
            Notificacion n = new Notificacion();
            n.setCorreo(usuarioLogueado.getCorreo());
            n.setProducto(p);
            n.setEstado("PENDIENTE");
            notificacionRepository.save(n);
        }
        return "redirect:/tienda?notificado=true";
    }

    // NUEVO ENDPOINT: Extrae la imagen de la base de datos y la envia a la web
    @GetMapping("/producto/imagen/{id}")
    public ResponseEntity<byte[]> obtenerImagen(@PathVariable Long id) {
        Producto p = productoRepository.findById(id).orElse(null);
        if (p != null && p.getImagen() != null) {
            return ResponseEntity.ok()
                    .contentType(MediaType.IMAGE_JPEG)
                    .body(p.getImagen());
        }
        return ResponseEntity.notFound().build();
    }
}