package ar.edu.utn.frba.dds.models.notificaciones;

import ar.edu.utn.frba.dds.models.Incidente;
import ar.edu.utn.frba.dds.models.usuarios.Persona;
import javax.persistence.Entity;

@Entity
public class NotificacionRevision extends Notificacion {

  public NotificacionRevision(Incidente incidente,  Persona usuarioAnotificar) {
    super(incidente, usuarioAnotificar);
  }

  public NotificacionRevision() {

  }

  @Override
  public String getMensajeDeNotificacion() {
    String msg = "Hola " + usuarioAnotificar.getNombre() + ", hay un incidente activo"
        + " en el Servicio " + incidente.getServicio().getNombre() + " de "
        + incidente.getServicio().getEstablecimiento().getNombre() + " - "
        + incidente.getServicio().getEstablecimiento().getEntidad().getNombre()
        + ". Por favor, si tiene tiempo de revisar y revisar su estado actual "
        + "se lo agradeceriamos. Muchas gracias y disculpe las molestias.";

    return msg;
  }

  @Override
  public String getEncabezado() {
    String encabezado = "INCIDENTE ACTIVO CERCA SUYO";

    return encabezado;
  }
}
