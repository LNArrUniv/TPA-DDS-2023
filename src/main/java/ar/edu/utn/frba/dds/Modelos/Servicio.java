package ar.edu.utn.frba.dds.Modelos;

import java.util.ArrayList;

public class Servicio {
  private String nombre;
  private String descripcion;
  private Localizacion localizacion;

  public Servicio(String nombre, String descripcion, Localizacion localizacion) {
    this.nombre = nombre;
    this.descripcion = descripcion;
    this.localizacion = localizacion;
  }

  public String getNombre() {
    return nombre;
  }

  public Localizacion getLocalizacion() {
    return localizacion;
  }
}
