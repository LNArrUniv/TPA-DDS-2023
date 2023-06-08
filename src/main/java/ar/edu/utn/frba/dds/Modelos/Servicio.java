package ar.edu.utn.frba.dds.Modelos;

public class Servicio {
  private String nombre;
  private String descripcion;
  private boolean estado;

  private Localizacion localizacion;

  public Servicio(String nombre, String descripcion, boolean estado, Localizacion localizacion) {
    this.nombre = nombre;
    this.descripcion = descripcion;
    this.estado = estado;
    this.localizacion = localizacion;
  }

  private void usarServicio() {
  }

  ;
}
