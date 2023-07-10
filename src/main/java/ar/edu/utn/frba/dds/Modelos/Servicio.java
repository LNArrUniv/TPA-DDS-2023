package ar.edu.utn.frba.dds.Modelos;

import java.util.ArrayList;

public class Servicio {
  private String nombre;
  private String descripcion;
  private Localizacion localizacion;
  private ArrayList<Incidente> incidentesActivos;
  private ArrayList<Incidente> historialIncidentes;

  public Servicio(String nombre, String descripcion, Localizacion localizacion) {
    this.nombre = nombre;
    this.descripcion = descripcion;
    this.localizacion = localizacion;
  }

  public String getNombre() {
    return nombre;
  }

  public void agregarNuevoIncidente(Incidente incidente){
    incidentesActivos.add(incidente);
  }

  public void marcarComoResuelto(Incidente incidente){
    incidentesActivos.remove(incidente);
    historialIncidentes.add(incidente);
  }
}
