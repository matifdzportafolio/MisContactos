package com.example.demo;

import com.example.demo.entities.Categoria;
import com.example.demo.entities.Contacto;
import com.example.demo.entities.Usuario;
import com.example.demo.repositories.CategoriaServicio;
import com.example.demo.repositories.ContactoServicio;
import com.example.demo.repositories.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class App implements CommandLineRunner {

  @Autowired
  UsuarioServicio usuarioServicio;

  @Autowired
  CategoriaServicio categoriaServicio;

  @Autowired
  ContactoServicio contactoServicio;




  public static void main(String[] args) {
    SpringApplication.run(App.class, args);
  }

  @Override
  public void run(String... args) throws Exception {
    Usuario usuarioMati = new Usuario();
    usuarioMati.setNombre("Matias");
    usuarioMati.setApellido("Fernandez");
    usuarioMati.setNombreUser("matifdz");
    usuarioMati.setContrasenia("1234");
    usuarioMati.setCelular("1122334455");
    usuarioMati.setMail("mati@gmail.com");

    List<Categoria> categoriasMati = new ArrayList<>();
    Categoria categoriaTrabajo = new Categoria();
    categoriaTrabajo.setNombre("TRABAJO");
    Categoria categoriaFamilia = new Categoria();
    categoriaFamilia.setNombre("FAMILIA");
    categoriasMati.add(categoriaFamilia);
    categoriasMati.add(categoriaTrabajo);
    usuarioMati.setCategorias(categoriasMati);

    List<Contacto> contactosMati = new ArrayList<>();
    Contacto contactoJavier = new Contacto();
    contactoJavier.setNombre("Javier");
    contactoJavier.setApellido("Lopez");
    contactoJavier.setCategoria(categoriaTrabajo);
    contactoJavier.setEmail("javier@gmail.com");
    contactoJavier.setTelefono("1122334455");
    contactoJavier.setFechaNac(LocalDate.now());


    usuarioMati.setContactos(contactosMati);
    usuarioMati.nuevoContacto(contactoJavier);


    categoriaServicio.guardarCategoria(categoriaTrabajo);
    categoriaServicio.guardarCategoria(categoriaFamilia);
    contactoServicio.guardarContacto(contactoJavier);
    usuarioServicio.guardarUsuario(usuarioMati);

  }
}
