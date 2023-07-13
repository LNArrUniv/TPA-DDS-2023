package ar.edu.utn.frba.dds.Modelos;

import ar.edu.utn.frba.dds.Modelos.Notificaciones.ConfiguracionNotificaciones;
import ar.edu.utn.frba.dds.Modelos.Notificaciones.Notificacion;
import ar.edu.utn.frba.dds.Modelos.Notificaciones.NotificacionNuevoIncidente;
import ar.edu.utn.frba.dds.Modelos.Notificaciones.NotificacionRevision;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class Persona extends Usuario {

  private ArrayList<Entidad> entidadesDeInteres;
  private ArrayList<ServicioPorPersona> serviciosDeInteres;
  private Localizacion localizacion;
  private ArrayList<Comunidad> comunidades;
  private ConfiguracionNotificaciones configuracionNotificaciones;

  public Persona(String nombre, String apellido, String username, String contrasenia, ConfiguracionNotificaciones configuracionNotificaciones) {
    super(nombre, apellido, username, contrasenia);
    this.entidadesDeInteres = new ArrayList<Entidad>();
    this.serviciosDeInteres = new ArrayList<ServicioPorPersona>();
    this.comunidades = new ArrayList<Comunidad>();
    this.configuracionNotificaciones = configuracionNotificaciones;
  }

  public void setLocalizacion(Localizacion localizacion) {
    this.localizacion = localizacion;
    ArrayList<Incidente> incidentesConMismaLocalizacion = (ArrayList<Incidente>) RepositorioIncidentes.getInstance().getActivos().stream().filter(incidente -> incidente.getLocalizacion().equals(localizacion));
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

  public void notificar(Notificacion notificacion) {
    configuracionNotificaciones.notificarMiembro(notificacion);
  }
}
