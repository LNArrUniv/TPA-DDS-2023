package ar.edu.utn.frba.dds.Modelos.Notificaciones;

import ar.edu.utn.frba.dds.Modelos.Incidente;

public class NotificacionNuevoIncidente extends Notificacion {

  public NotificacionNuevoIncidente(Incidente incidente) {
    super(incidente);
  }

  @Override
  public String getMensajeDeNotificacion() {
    String msg = "Nuevo incidente informado por " + incidente.getInformante().getUsername() + " en el Servicio " + incidente.getServicio().getNombre() + ": " + incidente.getDescripcion();

    return msg;
  }

  @Override
  public String getEncabezado(){
    String encabezado = "NUEVO INCIDENTE";

    return encabezado;
  }
}
