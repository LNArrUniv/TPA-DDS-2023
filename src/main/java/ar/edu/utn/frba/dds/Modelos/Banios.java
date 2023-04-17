package ar.edu.utn.frba.dds.Modelos;

public class Banios implements Servicio{
  private String nombre;
  private int capacidad;

  public Banios(String nombre, int capacidad) {
    this.nombre = nombre;
    this.capacidad = capacidad;
  }
}
