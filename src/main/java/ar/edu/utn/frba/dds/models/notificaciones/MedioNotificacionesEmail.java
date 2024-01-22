package ar.edu.utn.frba.dds.models.notificaciones;

public class MedioNotificacionesEmail extends MedioDeNotificacionesPreferido {
  public MedioNotificacionesEmail(String email) {
    super(email, null);
  }

  @Override
  public void notificar(Notificacion notificacion) throws Exception {
    //SenderService.getInstance().
    // email(super.getEmail(), notificacion.getEncabezado() ,
    // notificacion.getMensajeDeNotificacion());
    //System.out.println(notificacion.getMensajeDeNotificacion());
  }
}
