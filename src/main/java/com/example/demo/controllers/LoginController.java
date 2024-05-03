package com.example.demo.controllers;

import com.example.demo.entities.Usuario;
import com.example.demo.repositories.UsuarioServicio;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class LoginController {

  @Autowired
  private UsuarioServicio usuarioRepositorio;


  @GetMapping({"/login"})
  public String mostrarLogin(HttpServletRequest request) {
    if (request.getCookies() == null){
      return "login";
    } else
    {
      return "redirect:/";
    }
  }

  @RequestMapping(value = "/login", method = RequestMethod.POST)
  public String login(@RequestParam(value = "nombreuser") String user,
                      @RequestParam(value = "nombrepass") String pass,
                      HttpServletResponse response,
                      HttpServletRequest request) {

    Usuario usuarioEnSesion = usuarioRepositorio.buscarPorUsuario(user);

    if(usuarioEnSesion == null){
      return "login";
    }


    if (usuarioEnSesion.getContrasenia().equals(pass)) {
      Cookie cookie = new Cookie("userSessionId",usuarioEnSesion.getId().toString());
      cookie.setSecure(true);
      cookie.setMaxAge(300); // 5 minutos dura la sesion
      cookie.setHttpOnly(true);
      response.addCookie(cookie);
      System.out.println(cookie.getValue() + "usuarioEnSesion");

      return "redirect:/";
    } else {
      return "login";
    }
  }

}
