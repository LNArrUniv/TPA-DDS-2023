package ar.edu.utn.frba.dds.Modelos;

import ar.edu.utn.frba.dds.Modelos.Notificaciones.ConfiguracionNotificaciones;
import ar.edu.utn.frba.dds.Modelos.Notificaciones.Notificacion;
import ar.edu.utn.frba.dds.Modelos.Notificaciones.NotificacionRevision;
import ar.edu.utn.frba.dds.Modelos.UbicacionDTO.Localidad;
import org.checkerframework.framework.qual.TargetLocations;
import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.ArrayList;

@Entity
@Table(name = "persona")
public class Persona extends Usuario {
  @Transient
  private ArrayList<Entidad> entidadesDeInteres;
  @Transient
  private ArrayList<ServicioPorPersona> serviciosDeInteres;
  @Embedded
  @AttributeOverride(name="nombre", column=@Column(name="localidad"))
  private Localidad ubicacion;
  @Transient
  private ArrayList<Comunidad> comunidades;
  @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
  private ConfiguracionNotificaciones configuracionNotificaciones;

  public Persona(String nombre, String apellido, String username, String contrasenia, ConfiguracionNotificaciones configuracionNotificaciones) {
    super(nombre, apellido, username, contrasenia);
    this.entidadesDeInteres = new ArrayList<>();
    this.serviciosDeInteres = new ArrayList<>();
    this.comunidades = new ArrayList<>();
    this.configuracionNotificaciones = configuracionNotificaciones;
  }

  public Persona() {

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
