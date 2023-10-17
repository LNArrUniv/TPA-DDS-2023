package ar.edu.utn.frba.dds.Modelos.Usuarios;

import ar.edu.utn.frba.dds.Modelos.Comunidades.CargoComunidad;
import ar.edu.utn.frba.dds.Modelos.Comunidades.Comunidad;
import ar.edu.utn.frba.dds.Modelos.Entidad;
import ar.edu.utn.frba.dds.Modelos.Incidente;
import ar.edu.utn.frba.dds.Modelos.Comunidades.Membresia;
import ar.edu.utn.frba.dds.Modelos.Notificaciones.ConfiguracionNotificaciones;
import ar.edu.utn.frba.dds.Modelos.Notificaciones.Notificacion;
import ar.edu.utn.frba.dds.Modelos.Notificaciones.NotificacionRevision;
import ar.edu.utn.frba.dds.Modelos.Comunidades.RolComunidad;
import ar.edu.utn.frba.dds.Modelos.Servicio;
import ar.edu.utn.frba.dds.Modelos.UbicacionDTO.Localidad;
import ar.edu.utn.frba.dds.Persistencia.EntidadPersistente;
import ar.edu.utn.frba.dds.Persistencia.repositorios.RepositorioIncidentes;
import lombok.Getter;
import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "persona")
public class Persona extends EntidadPersistente {
  @Getter
  @Column
  private String nombre;
  @Getter
  @Column
  private String apellido;
  @Embedded
  private Usuario usuario;
  @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
  @JoinTable(name = "entidadesInteres_por_persona")
  private List<Entidad> entidadesDeInteres;
  @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
  @JoinTable(name = "serviciosInteres_por_persona")
  private List<Servicio> serviciosDeInteres;
  @Embedded
  @AttributeOverride(name="nombre", column=@Column(name="localidad"))
  private Localidad ubicacion;
  @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, mappedBy = "miembro")
  private List<Membresia> membresiasAComunidades;

  @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
  private ConfiguracionNotificaciones configuracionNotificaciones;

  public Persona(String nombre, String apellido, Usuario usuario, ConfiguracionNotificaciones configuracionNotificaciones) {
    this.nombre = nombre;
    this.apellido = apellido;
    this.usuario = usuario;
    this.entidadesDeInteres = new ArrayList<>();
    this.serviciosDeInteres = new ArrayList<>();
    this.membresiasAComunidades = new ArrayList<>();
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

  public String getUsername(){
    return this.usuario.getUsername();
  }

  public void darseAltaComunidad(Comunidad comunidad, RolComunidad rolComunidad) {
    Membresia membresia = new Membresia(comunidad, rolComunidad, this, CargoComunidad.MIEMBRO);

    membresiasAComunidades.add(membresia);
    comunidad.agregarMiembro(membresia);
  }

  public void darseBajaComunidad(Membresia membresia) {
    this.membresiasAComunidades.remove(membresia);
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