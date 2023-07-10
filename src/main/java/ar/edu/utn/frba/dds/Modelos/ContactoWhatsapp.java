package ar.edu.utn.frba.dds.Modelos;

public class ContactoWhatsapp implements MedioDeContacto{
  private String telefono;

  @Override
  public void notificar(Notificacion notificacion) {
    //TODO: NotificadorWhatsapp.getInstance().notificar(telefono, notificacion.getMensajeDeNotificacion());
  }
}
