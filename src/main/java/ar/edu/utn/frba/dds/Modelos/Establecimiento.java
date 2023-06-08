package ar.edu.utn.frba.dds.Modelos;

import java.util.ArrayList;
import java.util.List;

public class Establecimiento {
  private String nombre;
  private String descripcion;
  private ArrayList<Servicio> servicios;
  private ArrayList<Servicio> serviciosConProblema;
  private Localizacion localizacion;

  public Establecimiento(String nombre, String descripcion, Localizacion localizacion) {
    this.nombre = nombre;
    this.descripcion = descripcion;
    this.servicios = new ArrayList<Servicio>();
    this.serviciosConProblema = new ArrayList<Servicio>();
    this.localizacion = localizacion;
  }

  private void usarServicio(Servicio servicio) {
  }

  private void agregarServicio(Servicio servicio) {
    servicios.add(servicio);
  }

  private void agregarServicioConProblema(Servicio servicioConProblema) {
    serviciosConProblema.add(servicioConProblema);
  }
}
