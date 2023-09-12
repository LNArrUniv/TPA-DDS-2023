package ar.edu.utn.frba.dds.Modelos;

import ar.edu.utn.frba.dds.Persistencia.EntidadPersistente;
import lombok.Getter;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Usuario {
  @Id
  @GeneratedValue(strategy = GenerationType.TABLE)
  private long id;
  @Column
  private String nombre;
  @Column
  private String apellido;
  @Getter
  @Column
  private String username;
  @Column
  private String contrasenia;

  protected Usuario(String nombre, String apellido, String username, String contrasenia) {
    this.nombre = nombre;
    this.apellido = apellido;
    this.username = username;
    this.contrasenia = contrasenia;
  }

  public Usuario() {

  }
}