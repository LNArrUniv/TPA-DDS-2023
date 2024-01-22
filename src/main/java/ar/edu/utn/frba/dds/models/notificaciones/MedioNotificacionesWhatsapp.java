package ar.edu.utn.frba.dds.models.notificaciones;

public class MedioNotificacionesWhatsapp extends MedioDeNotificacionesPreferido {
  public MedioNotificacionesWhatsapp(String telefono) {
    super(null, telefono);
  }

  @Override
  public void notificar(Notificacion notificacion) {
    //SenderService.getInstance()
    // .whatsApp(notificacion.getMensajeDeNotificacion(), super.getTelefono());
    //System.out.println(notificacion.getMensajeDeNotificacion());
  }
}
