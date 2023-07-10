package ar.edu.utn.frba.dds.Modelos;

import ar.edu.utn.frba.dds.Modelos.Notificaciones.ConfiguracionNotificaciones;
import ar.edu.utn.frba.dds.Modelos.Notificaciones.Notificacion;
import java.util.ArrayList;

public class Persona extends Usuario {

  private ArrayList<Entidad> entidadesDeInteres;
  private ArrayList<Servicio> serviciosDeInteres;
  private Localizacion localizacion;
  private ArrayList<Comunidad> comunidades;
  private ConfiguracionNotificaciones configuracionNotificaciones;

  public Persona(String nombre, String apellido, String username, String contrasenia, ConfiguracionNotificaciones configuracionNotificaciones) {
    super(nombre, apellido, username, contrasenia);
    this.entidadesDeInteres = new ArrayList<Entidad>();
    this.serviciosDeInteres = new ArrayList<Servicio>();
    this.comunidades = new ArrayList<Comunidad>();
    this.configuracionNotificaciones = configuracionNotificaciones;
  }

  public void setLocalizacion(Localizacion localizacion) {
    this.localizacion = localizacion;
  }

  public void darseAltaComunidad(Comunidad comunidad) {
    this.comunidades.add(comunidad);
    comunidad.agregarMiembro(this);
  }

  public void darseBajaComunidad(Comunidad comunidad) {
    this.comunidades.remove(comunidad);
  }

  public void notificar(Notificacion notificacion) {
    configuracionNotificaciones.notificarMiembro(notificacion);
  }
}
