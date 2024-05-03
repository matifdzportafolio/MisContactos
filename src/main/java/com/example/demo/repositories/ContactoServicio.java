package com.example.demo.repositories;

import com.example.demo.entities.Contacto;
import org.springframework.stereotype.Service;
import java.util.List;

public interface ContactoServicio {

  List<Contacto> todosLosContactos();

  void guardarContacto(Contacto contacto);

  void actualizarContactos();

  Contacto obtenerContactoPorId(Long id);

}
