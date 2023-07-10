package ar.edu.utn.frba.dds.Modelos;

import java.util.ArrayList;

public class Entidad {
  private String nombre;
  private String descripcion;
  private ArrayList<Establecimiento> establecimientos;
  private Localizacion localizacion;

  public Entidad(String nombre, String descripcion, Localizacion localizacion) {
    this.nombre = nombre;
    this.descripcion = descripcion;
    this.establecimientos = new ArrayList<Establecimiento>();
    this.localizacion = localizacion;
  }

  public void agregarEstablecimiento(Establecimiento establecimiento) {
    establecimientos.add(establecimiento);
  }
}
