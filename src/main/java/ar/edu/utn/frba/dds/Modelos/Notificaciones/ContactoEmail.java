package ar.edu.utn.frba.dds.Modelos.Notificaciones;

public class ContactoEmail implements MedioDeContacto{
  private String email;

  public ContactoEmail(String mail) {
    email = mail;
  }

  @Override
  public void notificar(Notificacion notificacion) {
    //TODO: NotificadorEmail.getInstance().notificar(email, notificacion.getMensajeDeNotificacion());
    System.out.println(notificacion.getMensajeDeNotificacion());
  }
}
