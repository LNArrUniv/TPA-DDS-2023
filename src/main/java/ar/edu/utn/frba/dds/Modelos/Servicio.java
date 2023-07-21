package ar.edu.utn.frba.dds.Modelos;

import ar.edu.utn.frba.dds.Modelos.UbicacionDTO.Localidad;
import lombok.Getter;

public class Servicio {
  @Getter
  private String nombre;
  private String descripcion;
  @Getter
  private Localidad ubicacion;

  public Servicio(String nombre, String descripcion, Localidad ubicacion) {
    this.nombre = nombre;
    this.descripcion = descripcion;
    this.ubicacion = ubicacion;
  }
}
