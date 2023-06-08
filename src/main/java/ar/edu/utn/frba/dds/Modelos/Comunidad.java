package ar.edu.utn.frba.dds.Modelos;

import java.util.List;

public class Comunidad {
  private String nombreComunidad;
  private Servicio problematicaDeInteres;
  private ArrayList<Usuario> miembros;
  private ArrayList<Usuario> administradores;

  public Comunidad(String nombreComunidad, Servicio problematicaDeInteres) {
    this.nombreComunidad = nombreComunidad;
    this.problematicaDeInteres = problematicaDeInteres;
    this.miembros = new ArrayList<>();
    this.administradores = new ArrayList<>();
  }

  public void agregarMiembro(Usuario usuario) {
    this.miembros.add(usuario);
  }

  public void eleminarMiembro(Usuario usuario) {
    this.miembros.remove(usuario);
  }

  public void relevarServicio(Servicio servicio, Establecimiento establecimiento) {
    // TODO: establecimiento.modificarServicio(servicio);
  }
}