package com.example.demo.controllers;

import com.example.demo.entities.Categoria;
import com.example.demo.entities.Contacto;
import com.example.demo.entities.Usuario;
import com.example.demo.repositories.CategoriaServicio;
import com.example.demo.repositories.ContactoServicio;
import com.example.demo.repositories.UsuarioServicio;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Controller
public class EditarContactoController {

  @Autowired
  ContactoServicio contactoServicio;

  @Autowired
  CategoriaServicio categoriaServicio;

  @Autowired
  UsuarioServicio usuarioServicio;

  @GetMapping("/contactos/{id}")
  public String mostrarPaginaEdicion(@PathVariable("id") Long id, Model model, HttpServletRequest request) {
    if (request.getCookies() == null) {
      return "redirect:/login";
    } else {
      contactoServicio.actualizarContactos();
      Contacto contacto = contactoServicio.obtenerContactoPorId(id);
      categoriaServicio.actualizarCategorias();
      Long idUsuarioEnSesion = Long.valueOf(Arrays.stream(request.getCookies()).filter(cookie -> cookie.getName().equals("userSessionId")).toList().get(0).getValue());
      Usuario usuarioEnSesion = usuarioServicio.buscaroPorId(idUsuarioEnSesion);
      List<Categoria> categorias = usuarioEnSesion.getCategorias();
      model.addAttribute("categorias", categorias);
      model.addAttribute("contacto", contacto);
      int dia = contacto.getFechaNac().getDayOfMonth();
      int mes = contacto.getFechaNac().getMonthValue();
      int anio = contacto.getFechaNac().getYear();
      String fechaFormateada = anio + "-" + String.format("%02d", mes) + "-" + String.format("%02d", dia);
      model.addAttribute("fechaNacimientoString", fechaFormateada);
      return "editar";
    }
  }



  @PostMapping("/contactos/{id}")
  public String actualizarContacto(@PathVariable("id") Long id, @ModelAttribute("contacto") Contacto contacto, @RequestParam("fechacontacto") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaContacto) {
    Contacto contactoBuscado = contactoServicio.obtenerContactoPorId(id);
    contactoBuscado.setId(id);
    contactoBuscado.setNombre(contacto.getNombre());
    contactoBuscado.setApellido(contacto.getApellido());
    contactoBuscado.setCategoria(contacto.getCategoria());
    contactoBuscado.setTelefono(contacto.getTelefono());
    contactoBuscado.setEmail(contacto.getEmail());
    contactoBuscado.setFechaNac(fechaContacto);

    contactoServicio.guardarContacto(contactoBuscado);
    return "redirect:/contactos";
  }


}
