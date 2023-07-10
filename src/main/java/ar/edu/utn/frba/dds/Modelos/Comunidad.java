package ar.edu.utn.frba.dds.Modelos;

import ar.edu.utn.frba.dds.Modelos.Notificaciones.Notificacion;
import ar.edu.utn.frba.dds.Modelos.Notificaciones.NotificacionIncidenteResuelto;
import ar.edu.utn.frba.dds.Modelos.Notificaciones.NotificacionNuevoIncidente;
import java.util.ArrayList;

public class Comunidad {
  private String nombreComunidad;
  private ArrayList<Servicio> serviciosDeInteres;
  private ArrayList<Persona> miembros;
  private ArrayList<Persona> administradores;

  public Comunidad(String nombreComunidad) {
    this.nombreComunidad = nombreComunidad;
    this.serviciosDeInteres = new ArrayList<>();
    this.miembros = new ArrayList<>();
    this.administradores = new ArrayList<>();
  }

  public void agregarMiembro(Persona nuevoMiembro) {
    this.miembros.add(nuevoMiembro);
  }

  public void eleminarMiembro(Persona miembro) {
    this.miembros.remove(miembro);
  }

  public void relevarServicio(Servicio servicio, Establecimiento establecimiento) {
    serviciosDeInteres.add(servicio);
    establecimiento.agregarServicio(servicio);
  }

  public void agregarServicioDeInteres(Servicio servicio){
    serviciosDeInteres.add(servicio);
  }

  public void informarNuevoIncidente(Servicio servicio, Incidente incidente){
    servicio.agregarNuevoIncidente(incidente);
    Notificacion notificacion = new NotificacionNuevoIncidente(servicio, incidente);
    miembros.forEach(miembro -> miembro.notificar(notificacion));
    administradores.forEach(admin -> admin.notificar(notificacion));
  }

  public void informarIncidenteResuelto(Servicio servicio, Incidente incidente){
    servicio.marcarComoResuelto(incidente);
    Notificacion notificacion = new NotificacionIncidenteResuelto(servicio, incidente);
    miembros.forEach(miembro -> miembro.notificar(notificacion));
    administradores.forEach(admin -> admin.notificar(notificacion));
  }
}