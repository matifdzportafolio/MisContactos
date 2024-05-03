package com.example.demo.controllers;

import com.example.demo.entities.Contacto;
import com.example.demo.entities.Usuario;
import com.example.demo.repositories.ContactoServicio;
import com.example.demo.repositories.UsuarioServicio;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.Arrays;
import java.util.List;

@Controller
public class ContactosController {

  @Autowired
  ContactoServicio contactoServicio;

  @Autowired
  UsuarioServicio usuarioServicio;

  @GetMapping({"/contactos"})
  public String mostrarLogin(HttpServletRequest request, Model model) {
    if(request.getCookies() == null){
      return "redirect:/login";
    }
    else {
      contactoServicio.actualizarContactos();
      Long idUsuarioEnSesion = Long.valueOf(Arrays.stream(request.getCookies()).filter(cookie -> cookie.getName().equals("userSessionId")).toList().get(0).getValue());
      Usuario usuarioEnSesion = usuarioServicio.buscaroPorId(idUsuarioEnSesion);
      List<Contacto> contactosUsuario = usuarioEnSesion.getContactos();

      if(contactosUsuario.isEmpty()){
        return "sincontactoslistado";
      }
      else {
        model.addAttribute("contactos", contactosUsuario);
        return "contactos";
      }
    }
  }
}
