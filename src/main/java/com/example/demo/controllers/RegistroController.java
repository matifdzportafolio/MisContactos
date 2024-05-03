package com.example.demo.controllers;

import com.example.demo.entities.Categoria;
import com.example.demo.entities.Usuario;
import com.example.demo.repositories.CategoriaServicio;
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
import java.util.ArrayList;
import java.util.List;

@Controller
public class RegistroController {


  @Autowired
  UsuarioServicio usuarioServicio;

  @Autowired
  CategoriaServicio categoriaServicio;

  @GetMapping({"/registro"})
  public String mostrarRegistro(HttpServletRequest request) {
    if (request.getCookies() == null){
      return "registro";
    } else
    {
      return "redirect:/";
    }
  }


  @RequestMapping(value = "/registro", method = RequestMethod.POST)
  public String registro(@RequestParam(value = "nombreuser") String nombreuser,
                         @RequestParam(value = "contrasenia") String contrasenia,
                         @RequestParam(value = "celular") String celular,
                         @RequestParam(value = "email") String email,
                         @RequestParam(value = "nombre") String nombre,
                         @RequestParam(value = "apellido") String apellido,
                      HttpServletResponse response) {



    Categoria categoriaVarios = new Categoria();
    categoriaVarios.setNombre("VARIOS");
    List<Categoria> categorias = new ArrayList<>();
    categorias.add(categoriaVarios);



    Usuario usuarioNuevo = new Usuario();
    usuarioNuevo.setNombre(nombre);
    usuarioNuevo.setApellido(apellido);
    usuarioNuevo.setNombreUser(nombreuser);
    usuarioNuevo.setContrasenia(contrasenia);
    usuarioNuevo.setCelular(celular);
    usuarioNuevo.setCategorias(categorias);
    usuarioNuevo.setMail(email);

    categoriaServicio.guardarCategoria(categoriaVarios);
    usuarioServicio.guardarUsuario(usuarioNuevo);
    usuarioServicio.actualizarUsuarios();

    Usuario usuarioPersistido = usuarioServicio.buscarPorUsuario(nombreuser);
    Long idUsuarioNuevo = usuarioPersistido.getId();

      Cookie cookie = new Cookie("userSessionId",idUsuarioNuevo.toString());
      cookie.setSecure(true);
      cookie.setMaxAge(300); // 5 minutos dura la sesion
      cookie.setHttpOnly(true);
      response.addCookie(cookie);


      System.out.println(cookie.getValue() + "usuarioEnSesion");
      return "redirect:/";
    }
  }

