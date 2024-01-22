package ar.edu.utn.frba.dds.servicio;

import ar.edu.utn.frba.dds.servicio.comunicacion.MailService;
import ar.edu.utn.frba.dds.servicio.comunicacion.WhatsAppService;

public class SenderService {
  private static SenderService instance = null;

  private static MailService mailServiceInstance;

  private SenderService() {
    this.instance = new SenderService();
  }

  static {
    try {
      mailServiceInstance = new MailService();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  private static WhatsAppService whatsAppServiceInstance = new WhatsAppService();

  public String email(String mail, String encabezado, String mensaje) throws Exception {
    mailServiceInstance.sendMail(mail, encabezado, mensaje);
    return "Mensaje enviado por email";
  }

  public String whatsApp(String mensaje, String numero) {
    whatsAppServiceInstance.sendWhatsapp(mensaje, numero);
    return "Mensaje enviado por WhatsApp";
  }

  public static SenderService getInstance() {
    if (instance == null) {
      instance = new SenderService();
    }
    return instance;
  }
}
