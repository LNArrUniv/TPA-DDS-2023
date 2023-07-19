package ar.edu.utn.frba.dds.Modelos;

import lombok.Getter;
import java.util.ArrayList;

public class EntidadPropietaria {
  @Getter
  private String nombre;
  @Getter
  private String descripcion;
  private Usuario encargado;
  private ArrayList<Entidad> entidades;

  public EntidadPropietaria(String nombre, String descripcion, Usuario encargado) {
    this.nombre = nombre;
    this.descripcion = descripcion;
    this.encargado = encargado;
  }

  private void agregarEntidad(Entidad entidad) {
    entidades.add(entidad);
  }
}
