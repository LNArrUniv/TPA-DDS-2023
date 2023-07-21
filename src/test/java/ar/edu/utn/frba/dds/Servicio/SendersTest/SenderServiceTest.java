package ar.edu.utn.frba.dds.Servicio.SendersTest;

import ar.edu.utn.frba.dds.Servicio.SenderService;
import org.junit.jupiter.api.Test;

public class SenderServiceTest {

  @Test
    public void testWpMail() throws Exception{

      String incidentTittle = "Incidente grave en su zona";
      String incidentMensaje = "" +
          "Hemos sidos notificados de un incidente en el sistema de la escalera mecanica del segundo piso. " +
          "Por favor, dirijase al lugar para verificar el estado de la misma.";

      SenderService senderService = new SenderService();

      senderService.email("mailPruebas@gmail.com", incidentTittle, incidentMensaje);
      senderService.whatsApp(incidentTittle + " - " + incidentMensaje, "5412345679");
    }

}
