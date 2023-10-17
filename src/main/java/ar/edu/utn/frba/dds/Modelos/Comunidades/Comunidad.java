package ar.edu.utn.frba.dds.Modelos.Comunidades;

import ar.edu.utn.frba.dds.Modelos.Incidente;
import ar.edu.utn.frba.dds.Modelos.Notificaciones.Notificacion;
import ar.edu.utn.frba.dds.Modelos.Notificaciones.NotificacionIncidenteResuelto;
import ar.edu.utn.frba.dds.Modelos.Notificaciones.NotificacionNuevoIncidente;
import ar.edu.utn.frba.dds.Modelos.Servicio;
import ar.edu.utn.frba.dds.Modelos.Usuarios.Persona;
import ar.edu.utn.frba.dds.Persistencia.EntidadPersistente;
import lombok.Getter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "comunidad")
public class Comunidad extends EntidadPersistente {
  @Column
  @Getter
  private String nombreComunidad;
  @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
  @JoinColumn (name = "servicio_por_comunidad")
  @Getter
  private List<Servicio> serviciosDeInteres;
  @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, mappedBy = "comunidad")
  private List<Membresia> miembros;

  public Comunidad(String nombreComunidad) {
    this.nombreComunidad = nombreComunidad;
    this.serviciosDeInteres = new ArrayList<>();
    this.miembros = new ArrayList<>();
  }

  public Comunidad() {

  }

  public void agregarMiembro(Membresia nuevoMiembro) {
    this.miembros.add(nuevoMiembro);
  }

  public void eleminarMiembro(Membresia miembro) {
    this.miembros.remove(miembro);
  }
  /*

  public void relevarServicio(Servicio servicio, Establecimiento establecimiento) {
    serviciosDeInteres.add(servicio);
    establecimiento.agregarServicio(servicio);
  }
  */

  public void agregarServicioDeInteres(Servicio servicio){
    serviciosDeInteres.add(servicio);
  }

  public int totalMiembros(){
    return miembros.size();
  }

  public int totalMiembrosAfectados(){
    return (int) miembros.stream().filter(membresia -> membresia.getTipoDeUsuario().equals(RolComunidad.AFECTADO)).count();
  }

  public void informarNuevoIncidente(Incidente incidente){
    Notificacion notificacion = new NotificacionNuevoIncidente(incidente);
    notificarMiembros(notificacion);
  }

  public void informarIncidenteResuelto(Incidente incidente){
    incidente.marcarComoResuelto();
    Notificacion notificacion = new NotificacionIncidenteResuelto(incidente);
    notificarMiembros(notificacion);
  }

  private void notificarMiembros(Notificacion notificacion){
    miembros.forEach(membresia -> {
      try {
        membresia.getMiembro().notificar(notificacion);
      } catch (Exception e) {
        throw new RuntimeException(e);
      }
    });
  }

  public Boolean personaFormaParteDeLaComunidad(Persona persona) {
    return !this.miembros.stream().filter(membresia -> membresia.getMiembro().equals(persona)).toList().isEmpty();
  }
}