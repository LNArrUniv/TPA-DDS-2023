package ar.edu.utn.frba.dds.Modelos.Usuarios;

import ar.edu.utn.frba.dds.Persistencia.EntidadPersistente;
import lombok.Getter;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Embeddable
public class Usuario {
  @Getter
  @Column
  private String username;
  @Column
  private String contrasenia;

  public Usuario(String username, String contrasenia) {
    this.username = username;
    this.contrasenia = contrasenia;
  }

  public Usuario() {

  }
}