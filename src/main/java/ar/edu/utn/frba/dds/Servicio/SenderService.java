package ar.edu.utn.frba.dds.Servicio;

import ar.edu.utn.frba.dds.Servicio.Comunicacion.MailService;
import ar.edu.utn.frba.dds.Servicio.Comunicacion.WhatsAppService;

public class SenderService {

  private static MailService mailServiceInstance;

  static {
    try {
      mailServiceInstance = new MailService();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
  private static WhatsAppService whatsAppServiceInstance =  new WhatsAppService();

  public String email(String mail, String encabezado, String mensaje) throws Exception {
    mailServiceInstance.sendMail(mail, encabezado, mensaje);
    return "Mensaje enviado por email";
  }

  public String whatsApp(String mensaje, String numero){
    whatsAppServiceInstance.SendWhatsapp(mensaje, numero);
    return "Mensaje enviado por WhatsApp";
  }


}
