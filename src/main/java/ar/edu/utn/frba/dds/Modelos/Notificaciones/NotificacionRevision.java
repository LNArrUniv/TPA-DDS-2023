package ar.edu.utn.frba.dds.Modelos.Notificaciones;

import ar.edu.utn.frba.dds.Modelos.Incidente;

public class NotificacionRevision extends Notificacion {

  public NotificacionRevision(Incidente incidente) {
    super(incidente);
  }

  @Override
  public String getMensajeDeNotificacion() {
    String msg = "Hay un incidente ACTIVO en el Servicio " + incidente.getServicio().getNombre() + ". Por favor, si tiene tiempo de revisar su estado actual se lo agradeceriamos. Muchas gracias y disculpe las molestias.";

    return msg;
  }

  @Override
  public String getEncabezado() {
    String encabezado = "INCIDENTE ACTIVO CERCA SUYO";

    return encabezado;
  }
}
