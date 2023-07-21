package ar.edu.utn.frba.dds.Modelos;

import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;

public class OrganismoDeControl {
  @Getter
  private String nombre;
  private Usuario encargado;
  @Setter
  private ArrayList<EntidadPropietaria> entidadesControladas = new ArrayList<>();

  public OrganismoDeControl(String nombre, Usuario encargado) {
    this.nombre = nombre;
    this.encargado = encargado;
  }
}
