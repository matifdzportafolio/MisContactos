package com.example.demo.controllers;

import com.example.demo.entities.Contacto;
import com.example.demo.entities.Usuario;
import com.example.demo.repositories.ContactoServicio;
import com.example.demo.repositories.UsuarioServicio;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@Controller
public class PerfilController {

  @Autowired
  UsuarioServicio usuarioServicio;

  @Autowired
  ContactoServicio contactoServicio;

  @GetMapping({"/perfil"})
  public String mostrarPerfil(HttpServletRequest request, Model model) {
    if(request.getCookies() == null){
      return "redirect:/login";
    }
    else {
      contactoServicio.actualizarContactos();
      Long idUsuarioEnSesion = Long.valueOf(Arrays.stream(request.getCookies()).filter(cookie -> cookie.getName().equals("userSessionId")).toList().get(0).getValue());
      Usuario usuarioEnSesion = usuarioServicio.buscaroPorId(idUsuarioEnSesion);
      List<Contacto> contactosUsuario = usuarioEnSesion.getContactos();

      if(contactosUsuario.isEmpty()){
        model.addAttribute("usuario", usuarioEnSesion);
        return "sincontactosperfil";
      }
      else{
        model.addAttribute("usuario", usuarioEnSesion);
        return "perfil";
      }
    }
  }








  @RequestMapping(value = "/perfil", method = RequestMethod.POST)
  public String logout(HttpServletRequest request, HttpServletResponse response) {
    if (request.getCookies() == null) {
      return "redirect:/login";
    } else {
      Cookie cookieSession = Arrays.stream(request.getCookies()).filter(cookie -> cookie.getName().equals("userSessionId")).toList().get(0);
      cookieSession.setMaxAge(0);
      response.addCookie(cookieSession);

      return "redirect:/login";
    }
  }
}

