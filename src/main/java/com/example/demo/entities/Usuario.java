package com.example.demo.entities;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;

@Entity
@Table(name = "Usuario")
public class Usuario {

  @Id
  @GeneratedValue
  private Long id;

  @Column(name = "nombre")
  private String nombre;

  @Column(name = "apellido")
  private String apellido;

  @Column(name = "nombreUser")
  private String nombreUser;
  @Column(name = "contrasenia")
  private String contrasenia;
  @Column(name = "mail")
  private String mail;
  @Column(name = "celular")
  private String celular;
  @OneToMany(cascade = CascadeType.MERGE)
  private List<Contacto> contactos;
  @OneToMany(cascade = CascadeType.MERGE)
  private List<Categoria> categorias;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getNombreUser() {
    return nombreUser;
  }

  public void setNombreUser(String nombreUser) {
    this.nombreUser = nombreUser;
  }

  public String getContrasenia() {
    return contrasenia;
  }

  public void setContrasenia(String contrasenia) {
    this.contrasenia = contrasenia;
  }

  public String getMail() {
    return mail;
  }

  public void setMail(String mail) {
    this.mail = mail;
  }

  public String getCelular() {
    return celular;
  }

  public void setCelular(String celular) {
    this.celular = celular;
  }

  public List<Contacto> getContactos() {
    return contactos;
  }

  public void setContactos(List<Contacto> contactos) {
    this.contactos = contactos;
  }

  public List<Categoria> getCategorias() {
    return categorias;
  }

  public void setCategorias(List<Categoria> categorias) {
    this.categorias = categorias;
  }

  public void nuevoContacto(Contacto contacto){
    this.contactos.add(contacto);
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public String getApellido() {
    return apellido;
  }

  public void setApellido(String apellido) {
    this.apellido = apellido;
  }

  public void agregarCategoria(Categoria categoria){
    categorias.add(categoria);
  }

}


