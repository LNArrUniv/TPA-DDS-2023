package ar.edu.utn.frba.dds.Modelos.Notificaciones;

import ar.edu.utn.frba.dds.Modelos.Incidente;
import ar.edu.utn.frba.dds.Modelos.Servicio;

public abstract class Notificacion {
  protected Servicio servicio;
  protected Incidente incidente;

  public Notificacion(Servicio servicio, Incidente incidente) {
    this.servicio = servicio;
    this.incidente = incidente;
  }

  public abstract String getMensajeDeNotificacion();
}
