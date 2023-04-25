package ar.edu.utn.frba.dds.Modelos;

import java.util.List;

public class Estacion {
  private String nombre;
  private String direccion;
  private List<Linea> lineasPertenecientes;
  private List<Servicio> servicios;

  public void eliminarServicio(Servicio servicio){
    this.servicios.remove(servicio);
  }
  public void agregarServicio(Servicio servicio){
    this.servicios.add(servicio);
  }
  public void modificarServicio(Servicio servicio){ //TODO completar
  }

}