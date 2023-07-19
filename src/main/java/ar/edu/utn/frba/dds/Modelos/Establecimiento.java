package ar.edu.utn.frba.dds.Modelos;

import ar.edu.utn.frba.dds.Modelos.Ubicacion.Localidad;
import lombok.Getter;
import java.util.ArrayList;

public class Establecimiento {
  private String nombre;
  private String descripcion;
  @Getter
  private ArrayList<Servicio> servicios;
  private Localidad ubicacion;

  public Establecimiento(String nombre, String descripcion, Localidad ubicacion) {
    this.nombre = nombre;
    this.descripcion = descripcion;
    this.servicios = new ArrayList<Servicio>();
    this.ubicacion = ubicacion;
  }

  public void agregarServicio(Servicio servicio) {
    servicios.add(servicio);
  }
}
