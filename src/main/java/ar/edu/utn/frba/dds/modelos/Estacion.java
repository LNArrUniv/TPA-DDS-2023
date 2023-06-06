package ar.edu.utn.frba.dds.modelos;

import java.util.ArrayList;
import java.util.List;

public class Estacion {
  private String nombre;
  private String direccion;
  private List<Linea> lineasPertenecientes;
  private List<Servicio> servicios;

  public Estacion(String nombre, String direccion) {
    this.nombre = nombre;
    this.direccion = direccion;
    this.lineasPertenecientes = new ArrayList<Linea>();
    this.servicios = new ArrayList<Servicio>();
  }

  public void eliminarServicio(Servicio servicio) {
    this.servicios.remove(servicio);
  }

  public void agregarServicio(Servicio servicio) {
    this.servicios.add(servicio);
  }

  private void agregarLinea(Linea linea){
    lineasPertenecientes.add(linea);
  }

  public void modificarServicio(Servicio servicio) {
    //TODO completar
  }
}