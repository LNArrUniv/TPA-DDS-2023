package ar.edu.utn.frba.dds.Modelos.Notificaciones;

import ar.edu.utn.frba.dds.Modelos.Incidente;

public abstract class Notificacion {
  protected Incidente incidente;

  public Notificacion(Incidente incidente) {
    this.incidente = incidente;
  }

  public abstract String getMensajeDeNotificacion();

  public abstract String getEncabezado();
}
