package ar.edu.utn.frba.dds.Modelos;

public class NotificacionNuevoIncidente extends Notificacion {

  public NotificacionNuevoIncidente(Servicio servicio, Incidente incidente) {
    super();
  }

  @Override
  public String getMensajeDeNotificacion() {
    String msg = "Nuevo incidente informado por " + incidente.getInformante().getUsername() + " en el Servicio " + servicio.getNombre() + ": " + incidente.getDescripcion();

    return msg;
  }
}
