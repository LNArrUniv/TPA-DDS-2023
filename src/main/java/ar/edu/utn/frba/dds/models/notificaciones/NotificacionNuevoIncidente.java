package ar.edu.utn.frba.dds.models.notificaciones;

import ar.edu.utn.frba.dds.models.Incidente;
import ar.edu.utn.frba.dds.models.usuarios.Persona;
import javax.persistence.Entity;

@Entity

public class NotificacionNuevoIncidente extends Notificacion {

  public NotificacionNuevoIncidente(Incidente incidente, Persona usuarioAnotificar) {
    super(incidente, usuarioAnotificar);
  }

  public NotificacionNuevoIncidente() {

  }

  @Override
  public String getMensajeDeNotificacion() {
    String msg = "Hola " + usuarioAnotificar.getNombre() + ", le queremos informar que "
        + "hay un nuevo incidente informado por " + incidente.getInformante().getUsername()
        + " en el Servicio " + incidente.getServicio().getNombre() + " de "
        + incidente.getServicio().getEstablecimiento().getNombre() + " - "
        + incidente.getServicio().getEstablecimiento().getEntidad().getNombre()
        + ": " + incidente.getDescripcion();
    return msg;
  }

  @Override
  public String getEncabezado() {
    String encabezado = "NUEVO INCIDENTE";

    return encabezado;
  }
}
