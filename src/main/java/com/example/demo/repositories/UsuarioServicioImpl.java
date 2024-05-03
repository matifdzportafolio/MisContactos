package com.example.demo.repositories;

import com.example.demo.entities.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UsuarioServicioImpl implements UsuarioServicio{

  @Autowired
  UsuarioRepositorio usuarioRepositorio;

  @Override
  public List<Usuario> todosLosUsuarios() {
    return usuarioRepositorio.findAll();
  }

  @Override
  public Usuario buscarPorUsuario(String usuario) {
   List<Usuario> usuarios =  usuarioRepositorio.findAll().stream().filter(usuario1 -> usuario1.getNombreUser().equals(usuario)).toList();
   if(usuarios.isEmpty()){
     return null;
   }
   else {
     return usuarios.get(0);
   }
  }

  @Override
  public void guardarUsuario(Usuario usuario) {
    this.usuarioRepositorio.save(usuario);
  }

  @Override
  public Usuario buscaroPorId(Long id) {
    return this.usuarioRepositorio.findAll().stream().filter(usuario -> usuario.getId().equals(id)).toList().get(0);
  }

  @Override
  public void actualizarUsuarios() {
    usuarioRepositorio.flush();
  }
}
