package ar.edu.utn.frba.dds.Modelos;

import ar.edu.utn.frba.dds.Modelos.Notificaciones.ConfiguracionNotificaciones;
import ar.edu.utn.frba.dds.Modelos.Notificaciones.Notificacion;
import ar.edu.utn.frba.dds.Modelos.Notificaciones.NotificacionRevision;
import ar.edu.utn.frba.dds.Modelos.UbicacionDTO.Localidad;
import java.util.ArrayList;

public class Persona extends Usuario {

  private ArrayList<Entidad> entidadesDeInteres;
  private ArrayList<ServicioPorPersona> serviciosDeInteres;
  private Localidad ubicacion;
  private ArrayList<Comunidad> comunidades;
  private ConfiguracionNotificaciones configuracionNotificaciones;

  public Persona(String nombre, String apellido, String username, String contrasenia, ConfiguracionNotificaciones configuracionNotificaciones) {
    super(nombre, apellido, username, contrasenia);
    this.entidadesDeInteres = new ArrayList<>();
    this.serviciosDeInteres = new ArrayList<>();
    this.comunidades = new ArrayList<>();
    this.configuracionNotificaciones = configuracionNotificaciones;
  }

  public void setUbicacion(Localidad nuevaUbicacion) throws Exception {
    this.ubicacion = nuevaUbicacion;
    ArrayList<Incidente> incidentesConMismaLocalizacion = RepositorioIncidentes.getInstance().incidentesEnUbicacion(nuevaUbicacion);
    for (Incidente incidente: incidentesConMismaLocalizacion) {
      Notificacion notificacion = new NotificacionRevision(incidente);
      notificar(notificacion);
    }
  }

  public void darseAltaComunidad(Comunidad comunidad) {
    this.comunidades.add(comunidad);
    comunidad.agregarMiembro(this);
  }

  public void darseBajaComunidad(Comunidad comunidad) {
    this.comunidades.remove(comunidad);
  }

  public void notificar(Notificacion notificacion) throws Exception {
    configuracionNotificaciones.notificarMiembro(notificacion);
  }
}
