package ar.edu.utn.frba.dds.SendersTest;

import ar.edu.utn.frba.dds.Servicio.SenderService;
import org.junit.jupiter.api.Test;

public class SenderServiceTest {

  @Test
    public void testWpMail() throws Exception{

      SenderService senderService = SenderService.getInstance();

      senderService.email("oviedofranc@gmail.com", "Test encabezado", "Test Mensaje");
      senderService.whatsApp("Test Mensaje", "1126068543");
    }

}
