package ar.edu.utn.frba.dds.Modelos.Notificaciones;

import ar.edu.utn.frba.dds.Servicio.SenderService;

public class ContactoWhatsapp implements MedioDeContacto{
  private String telefono;

  @Override
  public void notificar(Notificacion notificacion) {
    SenderService notificador = SenderService.getInstance();
    notificador.whatsApp(notificacion.getMensajeDeNotificacion(), telefono);
    //System.out.println(notificacion.getMensajeDeNotificacion());
  }
}
