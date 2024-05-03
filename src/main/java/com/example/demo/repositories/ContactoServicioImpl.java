package com.example.demo.repositories;

import com.example.demo.entities.Contacto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class ContactoServicioImpl implements ContactoServicio{

  @Autowired
  ContactoRepositorio contactoRepositorio;

  @Override
  public List<Contacto> todosLosContactos() {
    return contactoRepositorio.findAll();
  }

  @Override
  public void guardarContacto(Contacto contacto) {
    contactoRepositorio.save(contacto);
  }

  @Override
  public void actualizarContactos() {
    this.contactoRepositorio.flush();
  }

  @Override
  public Contacto obtenerContactoPorId(Long id) {
    return this.contactoRepositorio.findAll().stream().filter(contacto -> contacto.getId().equals(id)).toList().get(0);
  }
}
