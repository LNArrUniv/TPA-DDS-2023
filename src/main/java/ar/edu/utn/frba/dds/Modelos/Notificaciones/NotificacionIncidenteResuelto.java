package ar.edu.utn.frba.dds.Modelos.Notificaciones;

import ar.edu.utn.frba.dds.Modelos.Incidente;
import ar.edu.utn.frba.dds.Modelos.Usuarios.Persona;
import javax.persistence.Entity;

@Entity
public class NotificacionIncidenteResuelto extends Notificacion {
  public NotificacionIncidenteResuelto(Incidente incidente,  Persona usuarioANotificar) {
    super(incidente, usuarioANotificar);
  }

  public NotificacionIncidenteResuelto() {

  }

  @Override
  public String getMensajeDeNotificacion() {
    //String msg = "El incidente informado anteriormente por " + incidente.getInformante().getUsername() + " en el Servicio " + incidente.getServicio().getNombre() + " fue marcado como RESUELTO";
    String msg = "Hola " + usuarioANotificar.getNombre() + ", le queremos informar que el incidente reportado anteriormente por " +
        incidente.getInformante().getUsername() + " en el Servicio " + incidente.getServicio().getNombre() + " de " + incidente.getServicio().getEstablecimiento().getNombre() + " - " + incidente.getServicio().getEstablecimiento().getEntidad().getNombre() + " fue marcado como resuelto";

    return msg;
  }

  @Override
  public String getEncabezado(){
    String encabezado = "INCIDENTE RESUELTO";

    return encabezado;
  }
}
