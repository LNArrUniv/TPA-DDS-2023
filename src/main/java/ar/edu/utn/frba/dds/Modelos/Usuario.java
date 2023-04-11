package ar.edu.utn.frba.dds.Modelos;
import java.util.List;

public class Usuario {
  private String nombre;
  private String apellido;
  private String email;
  private String contraseña;
  private List<Servicio> interesProblematica;
  private List<Comunidad> comunidades;

  public void agregarInteresProblematica(Servicio servicio) {
    this.interesProblematica.add(servicio);
  }
  public void eliminarInteresProblematica(Servicio servicio) {
    this.interesProblematica.remove(servicio);
  }
  public void agregarComunidad(Comunidad comunidad) {
    this.comunidades.add(comunidad);
  }
  public void darseBajaComunidad(Comunidad comunidad) {
    this.comunidades.remove(comunidad);
  }

}