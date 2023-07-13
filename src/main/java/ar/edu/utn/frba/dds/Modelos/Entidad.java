package ar.edu.utn.frba.dds.Modelos;

import java.util.ArrayList;

public class Entidad {
  private String nombre;
  private String descripcion;
  private ArrayList<Establecimiento> establecimientos;
  private Localizacion localizacion;

  private ArrayList<Incidente> incidentes;

  public Entidad(String nombre, String descripcion, Localizacion localizacion) {
    this.nombre = nombre;
    this.descripcion = descripcion;
    this.establecimientos = new ArrayList<Establecimiento>();
    this.localizacion = localizacion;
    this.incidentes=new ArrayList<>();
  }

  public void agregarEstablecimiento(Establecimiento establecimiento) {
    establecimientos.add(establecimiento);
  }

  public ArrayList<Incidente> getIncidentes() {
    return incidentes;
  }
}
