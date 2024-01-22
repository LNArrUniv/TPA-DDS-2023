package ar.edu.utn.frba.dds.models.usuarios;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

@Embeddable
public class Usuario {
  @Getter
  @Setter
  @Column
  private String username;
  @Getter
  @Setter
  @Column
  private String contrasenia;
  @Getter
  @Enumerated(EnumType.STRING)
  @Column(name = "tipo")
  private Rol rol;

  public Usuario(String username, String contrasenia, Rol rol) {
    this.username = username;
    this.contrasenia = contrasenia;
    this.rol = rol;
  }

  public Usuario() {

  }
}