package ar.edu.utn.frba.dds.Modelos;

import java.util.ArrayList;

public class EntidadPropietaria {
  private String nombre;
  private Usuario encargado;

  private ArrayList<Entidad> entidades;

  public EntidadPropietaria(String nombre, Usuario encargado) {
    this.nombre = nombre;
    this.encargado = encargado;
  }

  private void agregarEntidad(Entidad entidad) {
    entidades.add(entidad);
  }
}
