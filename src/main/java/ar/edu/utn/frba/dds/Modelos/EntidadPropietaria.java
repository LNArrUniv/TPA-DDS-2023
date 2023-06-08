package ar.edu.utn.frba.dds.Modelos;

import java.util.ArrayList;

public class EntidadPropietaria {
  private String nombre;
  private String descripcion;
  private Usuario encargado;

  private ArrayList<Entidad> entidades;

  public EntidadPropietaria(String nombre, String descripcion, Usuario encargado) {
    this.nombre = nombre;
    this.descripcion = descripcion;
    this.encargado = encargado;
  }

  public String getNombre() {
    return nombre;
  }

  public String getDescripcion() {
    return descripcion;
  }

  private void agregarEntidad(Entidad entidad) {
    entidades.add(entidad);
  }
}
