package ar.edu.utn.frba.dds.Modelos.Notificaciones;

import ar.edu.utn.frba.dds.Modelos.Incidente;
import ar.edu.utn.frba.dds.Modelos.Servicio;

public class NotificacionNuevoIncidente extends Notificacion {

  public NotificacionNuevoIncidente(Servicio servicio, Incidente incidente) {
    super(servicio, incidente);
  }

  @Override
  public String getMensajeDeNotificacion() {
    String msg = "Nuevo incidente informado por " + incidente.getInformante().getUsername() + " en el Servicio " + servicio.getNombre() + ": " + incidente.getDescripcion();

    return msg;
  }
}
