package ar.edu.utn.frba.dds.Modelos;

public class ContactoEmail implements MedioDeContacto{
  private String email;

  @Override
  public void notificar(Notificacion notificacion) {
    //TODO: NotificadorEmail.getInstance().notificar(email, notificacion.getMensajeDeNotificacion());
  }
}
