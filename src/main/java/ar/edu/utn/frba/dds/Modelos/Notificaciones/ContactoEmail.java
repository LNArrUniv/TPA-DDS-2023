package ar.edu.utn.frba.dds.Modelos.Notificaciones;

import ar.edu.utn.frba.dds.Servicio.SenderService;
import com.google.api.services.gmail.Gmail;

public class ContactoEmail implements MedioDeContacto{
  private String email;

  public ContactoEmail(String mail) {
    email = mail;
  }

  @Override
  public void notificar(Notificacion notificacion) throws Exception {
    SenderService.getInstance().email(email, notificacion.getEncabezado() , notificacion.getMensajeDeNotificacion());
    //System.out.println(notificacion.getMensajeDeNotificacion());
  }
}
