package ar.edu.utn.frba.dds.Modelos.Notificaciones;

import ar.edu.utn.frba.dds.Modelos.Incidente;
import ar.edu.utn.frba.dds.Modelos.Servicio;

public class NotificacionIncidenteResuelto extends Notificacion {
  public NotificacionIncidenteResuelto(Incidente incidente) {
    super(incidente);
  }

  @Override
  public String getMensajeDeNotificacion() {
    String msg = "El incidente informado anteriormente por " + incidente.getInformante().getUsername() + " en el Servicio " + incidente.getServicio().getNombre() + " fue marcado como RESUELTO";

    return msg;
  }

  @Override
  public String getEncabezado(){
    String encabezado = "INCIDENTE RESUELTO";

    return encabezado;
  }
}
