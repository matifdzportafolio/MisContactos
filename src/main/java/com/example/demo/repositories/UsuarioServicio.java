package com.example.demo.repositories;

import com.example.demo.entities.Usuario;
import java.util.List;

public interface UsuarioServicio {

  List<Usuario> todosLosUsuarios();

  Usuario buscarPorUsuario(String usuario);

  void guardarUsuario(Usuario usuario);

  Usuario buscaroPorId(Long id);

  void actualizarUsuarios();

}
