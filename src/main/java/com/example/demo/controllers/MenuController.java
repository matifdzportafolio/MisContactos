package com.example.demo.controllers;

import com.example.demo.entities.Usuario;
import com.example.demo.repositories.UsuarioServicio;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.Arrays;
import java.util.List;


@Controller
public class MenuController {

  @Autowired
  UsuarioServicio usuarioServicio;

  @GetMapping({"/"})
  public String mostrarMenu(HttpServletRequest request, Model model) {
    if (request.getCookies() == null){
      return "redirect:/login";
    }
    else {
      Long idUsuarioEnSesion = Long.valueOf(Arrays.stream(request.getCookies()).filter(cookie -> cookie.getName().equals("userSessionId")).toList().get(0).getValue());
      Usuario usuarioEnSesion = usuarioServicio.buscaroPorId(idUsuarioEnSesion);
      model.addAttribute("usuario", usuarioEnSesion);
      return "menu";
    }
  }
}
