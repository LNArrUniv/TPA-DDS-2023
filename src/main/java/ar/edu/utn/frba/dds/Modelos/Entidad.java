package ar.edu.utn.frba.dds.Modelos;

import ar.edu.utn.frba.dds.Modelos.Ubicacion.Provincia;
import lombok.Getter;
import java.util.ArrayList;

public class Entidad {
  private String nombre;
  private String descripcion;
  @Getter
  private ArrayList<Establecimiento> establecimientos;
  private Provincia ubicacion;

  public Entidad(String nombre, String descripcion, Provincia ubicacion) {
    this.nombre = nombre;
    this.descripcion = descripcion;
    this.establecimientos = new ArrayList<Establecimiento>();
    this.ubicacion = ubicacion;
  }

  public void agregarEstablecimiento(Establecimiento establecimiento) {
    establecimientos.add(establecimiento);
  }
}
