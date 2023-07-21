package ar.edu.utn.frba.dds.Modelos.Notificaciones;

import ar.edu.utn.frba.dds.Servicio.SenderService;
import com.google.api.services.gmail.Gmail;

public class ContactoWhatsapp implements MedioDeContacto{
  private String telefono;

  @Override
  public void notificar(Notificacion notificacion) {
    SenderService.getInstance().whatsApp(notificacion.getMensajeDeNotificacion(), telefono);
    //System.out.println(notificacion.getMensajeDeNotificacion());
  }
}
