package ar.edu.utn.frba.dds.Modelos.Notificaciones;

import ar.edu.utn.frba.dds.Servicio.SenderService;
import lombok.Getter;
import java.util.regex.Pattern;

public class MedioNotificacionesEmail extends MedioDeNotificacionesPreferido{
  public MedioNotificacionesEmail(String email) {
    super(email, null);
  }

  @Override
  public void notificar(Notificacion notificacion) throws Exception {
    SenderService.getInstance().email(super.getEmail(), notificacion.getEncabezado() , notificacion.getMensajeDeNotificacion());
    //System.out.println(notificacion.getMensajeDeNotificacion());
  }
}
