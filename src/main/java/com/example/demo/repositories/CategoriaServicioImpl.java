package com.example.demo.repositories;

import com.example.demo.entities.Categoria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class CategoriaServicioImpl implements CategoriaServicio{

  @Autowired
  CategoriaRepositorio categoriaRepositorio;

  @Override
  public List<Categoria> todasLasCategorias() {
    return categoriaRepositorio.findAll();
  }

  @Override
  public void guardarCategoria(Categoria categoria) {
    categoriaRepositorio.save(categoria);
  }

  @Override
  public void actualizarCategorias() {
    categoriaRepositorio.flush();
  }


}
