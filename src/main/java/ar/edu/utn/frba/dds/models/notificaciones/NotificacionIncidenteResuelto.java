package ar.edu.utn.frba.dds.models.notificaciones;

import ar.edu.utn.frba.dds.models.Incidente;
import ar.edu.utn.frba.dds.models.usuarios.Persona;
import javax.persistence.Entity;

@Entity
public class NotificacionIncidenteResuelto extends Notificacion {
  public NotificacionIncidenteResuelto(Incidente incidente, Persona usuarioAnotificar) {
    super(incidente, usuarioAnotificar);
  }

  public NotificacionIncidenteResuelto() {

  }

  @Override
  public String getMensajeDeNotificacion() {
    String msg = "Hola " + usuarioAnotificar.getNombre() + ", le queremos informar que "
        + "el incidente reportado anteriormente por " + incidente.getInformante().getUsername()
        + " en el Servicio " + incidente.getServicio().getNombre() + " de "
        + incidente.getServicio().getEstablecimiento().getNombre() + " - "
        + incidente.getServicio().getEstablecimiento().getEntidad().getNombre()
        + " fue marcado como resuelto";

    return msg;
  }

  @Override
  public String getEncabezado() {
    String encabezado = "INCIDENTE RESUELTO";

    return encabezado;
  }
}
