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
