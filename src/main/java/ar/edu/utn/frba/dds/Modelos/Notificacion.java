package ar.edu.utn.frba.dds.Modelos;

public abstract class Notificacion {
  protected Servicio servicio;
  protected Incidente incidente;
  public abstract String getMensajeDeNotificacion();
}
