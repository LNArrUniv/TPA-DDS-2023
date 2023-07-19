package ar.edu.utn.frba.dds.Modelos;

import lombok.Getter;
import lombok.Setter;

public class OrganismoDeControl {
  @Getter
  private String nombre;
  private Usuario encargado;
  @Setter
  private EntidadPropietaria entidadControlada;

  public OrganismoDeControl(String nombre, Usuario encargado) {
    this.nombre = nombre;
    this.encargado = encargado;
  }
}
