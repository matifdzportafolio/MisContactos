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
public class NuevoContactoController {

  @Autowired
  ContactoServicio contactoServicio;

  @Autowired
  UsuarioServicio usuarioServicio;

  @Autowired
  CategoriaServicio categoriaServicio;


    @GetMapping({"/nuevo_contacto"})
    public String mostrarLogin(HttpServletRequest request, Model model) {

      if (request.getCookies() == null) {
        return "redirect:/login";
      } else {
        categoriaServicio.actualizarCategorias();
        Long idUsuarioEnSesion = Long.valueOf(Arrays.stream(request.getCookies()).filter(cookie -> cookie.getName().equals("userSessionId")).toList().get(0).getValue());
        Usuario usuarioEnSesion = usuarioServicio.buscaroPorId(idUsuarioEnSesion);
        List<Categoria> categorias = usuarioEnSesion.getCategorias();
        model.addAttribute("categorias",categorias);
        return "nuevocontacto";

      }
    }


  @RequestMapping(value = "/nuevo_contacto", method = RequestMethod.POST)
  public String login(@RequestParam(value = "nombrecontacto") String nombre,
                      @RequestParam(value = "apellidocontacto") String apellido,
                      @RequestParam(value = "telcontacto") String tel,
                      @RequestParam(value = "emailcontacto") String email,
                      @RequestParam(value = "fechacontacto") String fecha,
                      @RequestParam(value = "categoria") Long categoria,
                      HttpServletRequest request) {

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    Contacto contactoNuevo = new Contacto();
    contactoNuevo.setNombre(nombre);
    contactoNuevo.setApellido(apellido);
    contactoNuevo.setTelefono(tel);
    contactoNuevo.setEmail(email);


    LocalDate fechaParseada = LocalDate.parse(fecha, formatter);

    contactoNuevo.setFechaNac(fechaParseada);
    Long idUsuarioEnSesion = Long.valueOf(Arrays.stream(request.getCookies()).filter(cookie -> cookie.getName().equals("userSessionId")).toList().get(0).getValue());
    Usuario usuarioEnSesion = usuarioServicio.buscaroPorId(idUsuarioEnSesion);

    Categoria categoriaIngresada = usuarioEnSesion.getCategorias().stream().filter(categoria1 -> categoria1.getId().equals(categoria)).toList().get(0);
    contactoNuevo.setCategoria(categoriaIngresada);


    usuarioEnSesion.nuevoContacto(contactoNuevo);
    contactoServicio.guardarContacto(contactoNuevo);


      return "redirect:/";
    }
  }

