package com.example.demo.repositories;

import com.example.demo.entities.Categoria;
import java.util.List;

public interface CategoriaServicio {

  List<Categoria> todasLasCategorias();
  void guardarCategoria(Categoria categoria);

  void actualizarCategorias();

}
