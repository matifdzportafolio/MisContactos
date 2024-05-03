package com.example.demo.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "Contacto")
public class Contacto {

  @Id
  @GeneratedValue
  private Long id;
  @Column(name = "Nombre")
  private String nombre;
  @Column(name = "Apellido")
  private String apellido;
  @Column(name = "Telefono")
  private String telefono;
  @Column(name = "Email")
  private String email;
  @Column(name = "FechaNacimiento")
  private LocalDate fechaNac;
  @ManyToOne(cascade = CascadeType.MERGE)
  private Categoria categoria;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
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

  public String getTelefono() {
    return telefono;
  }

  public void setTelefono(String telefono) {
    this.telefono = telefono;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public LocalDate getFechaNac() {
    return fechaNac;
  }

  public void setFechaNac(LocalDate fechaNac) {
    this.fechaNac = fechaNac;
  }

  public Categoria getCategoria() {
    return categoria;
  }

  public void setCategoria(Categoria categoria) {
    this.categoria = categoria;
  }
}
