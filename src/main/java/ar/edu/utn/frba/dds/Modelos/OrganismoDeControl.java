package ar.edu.utn.frba.dds.Modelos;

public class OrganismoDeControl {
  private String nombre;
  private Usuario encargado;

  private EntidadPropietaria entidadControlada;


  public OrganismoDeControl(String nombre, Usuario encargado) {
    this.nombre = nombre;
    this.encargado = encargado;
  }

  private void setEntidadControlada(EntidadPropietaria entidad) {
    entidadControlada = entidad;
  }

  public String getNombre() {
    return nombre;
  }
}
