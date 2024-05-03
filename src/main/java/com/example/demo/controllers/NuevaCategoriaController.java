package com.example.demo.controllers;


import com.example.demo.entities.Categoria;
import com.example.demo.entities.Contacto;
import com.example.demo.entities.Usuario;
import com.example.demo.repositories.CategoriaServicio;
import com.example.demo.repositories.ContactoServicio;
import com.example.demo.repositories.UsuarioServicio;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

@Controller
public class NuevaCategoriaController {


  @Autowired
  ContactoServicio contactoServicio;

  @Autowired
  UsuarioServicio usuarioServicio;

  @Autowired
  CategoriaServicio categoriaServicio;


  @GetMapping({"/nueva_categoria"})
  public String mostrarNuevaCategoria(HttpServletRequest request) {

    if (request.getCookies() == null) {
      return "redirect:/login";
    } else {
      return "nuevacategoria";

    }
  }


  @RequestMapping(value = "/nueva_categoria", method = RequestMethod.POST)
  public String login(@RequestParam(value = "nombrecategoria") String nombre,
                      HttpServletRequest request) {

    if (request.getCookies() == null) {
      return "redirect:/login";
    } else {
      Categoria categoria = new Categoria();
      categoria.setNombre(nombre);

      Long idUsuarioEnSesion = Long.valueOf(Arrays.stream(request.getCookies()).filter(cookie -> cookie.getName().equals("userSessionId")).toList().get(0).getValue());
      Usuario usuarioEnSesion = usuarioServicio.buscaroPorId(idUsuarioEnSesion);

      usuarioEnSesion.agregarCategoria(categoria);
      categoriaServicio.guardarCategoria(categoria);


      return "redirect:/nuevo_contacto";
    }
  }
}

