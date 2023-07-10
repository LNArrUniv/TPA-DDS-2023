package ar.edu.utn.frba.dds.Modelos;

public class NotificacionIncidenteResuelto extends Notificacion {
  public NotificacionIncidenteResuelto(Servicio servicio, Incidente incidente) {
    super();
  }

  @Override
  public String getMensajeDeNotificacion() {
    String msg = "El incidente informado anteriormente por " + incidente.getInformante().getUsername() + " en el Servicio " + servicio.getNombre() + " fue marcado como RESUELTO";

    return msg;
  }
}
