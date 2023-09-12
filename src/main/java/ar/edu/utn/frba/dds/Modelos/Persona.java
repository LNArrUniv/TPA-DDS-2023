package ar.edu.utn.frba.dds.Modelos;

import ar.edu.utn.frba.dds.Modelos.Notificaciones.ConfiguracionNotificaciones;
import ar.edu.utn.frba.dds.Modelos.Notificaciones.Notificacion;
import ar.edu.utn.frba.dds.Modelos.Notificaciones.NotificacionRevision;
import ar.edu.utn.frba.dds.Modelos.UbicacionDTO.Localidad;
import ar.edu.utn.frba.dds.Persistencia.repositorios.RepositorioIncidentes;
import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "persona")
public class Persona extends Usuario {
  @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
  @JoinTable(name = "entidades_por_persona")
  private List<Entidad> entidadesDeInteres;
  @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
  @JoinTable(name = "servicios_por_persona")
  private List<Servicio> serviciosDeInteres;
  @Embedded
  @AttributeOverride(name="nombre", column=@Column(name="localidad"))
  private Localidad ubicacion;
  @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, mappedBy = "miembro")
  private List<Membresia> comunidades;

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
    List<Incidente> incidentesConMismaLocalizacion = RepositorioIncidentes.getInstance().incidentesEnUbicacion(nuevaUbicacion);
    for (Incidente incidente: incidentesConMismaLocalizacion) {
      Notificacion notificacion = new NotificacionRevision(incidente);
      notificar(notificacion);
    }
  }

  public void darseAltaComunidad(Comunidad comunidad, Rol rolComunidad) {
    Membresia membresia = new Membresia(comunidad, rolComunidad, this, CargoComunidad.MIEMBRO);

    comunidades.add(membresia);
    comunidad.agregarMiembro(membresia);
  }

  public void darseBajaComunidad(Membresia membresia) {
    this.comunidades.remove(membresia);
    membresia.getComunidad().eleminarMiembro(membresia);

    // DELETE membresia?
  }


  public void notificar(Notificacion notificacion) throws Exception {
    configuracionNotificaciones.notificarMiembro(notificacion);
  }

  public void agregarEntidadDeInteres(Entidad entidad){
    entidadesDeInteres.add(entidad);
  }

  public void borrarEntidadDeInteres(Entidad entidad){
    entidadesDeInteres.remove(entidad);
  }

  public void agregarServicioDeInteres(Servicio servicio){
    serviciosDeInteres.add(servicio);
  }

  public void borrarServicioDeInteres(Servicio servicio){
    serviciosDeInteres.remove(servicio);
  }
}
