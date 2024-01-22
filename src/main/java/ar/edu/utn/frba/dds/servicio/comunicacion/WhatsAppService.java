package ar.edu.utn.frba.dds.servicio.comunicacion;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

public class WhatsAppService {

  private static final String ACCOUNT_SID = "AC79d295d4366cd283ccb1de2cb5b67492";
  private static final String AUTH_TOKEN = "011160be6191bba53f4b16dcdc771365";

  public void sendWhatsapp(String mensaje, String numero) {
    Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
    Message message = Message
        .creator(
            new PhoneNumber("whatsapp:+549" + numero),
            new PhoneNumber("whatsapp:+14155238886"),
            mensaje
        )
        .create();

    System.out.println(message.getSid());
  }
}
