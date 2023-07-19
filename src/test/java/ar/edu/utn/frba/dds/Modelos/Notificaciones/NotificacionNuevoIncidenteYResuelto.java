package ar.edu.utn.frba.dds.Modelos.Notificaciones;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;

import ar.edu.utn.frba.dds.Modelos.Comunidad;
import ar.edu.utn.frba.dds.Modelos.Incidente;
import ar.edu.utn.frba.dds.Modelos.Persona;
import ar.edu.utn.frba.dds.Modelos.Servicio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class NotificacionNuevoIncidenteYResuelto {
  Persona pepe;
  Persona jose;
  Servicio unServicio;
  Comunidad comunidad;

  @BeforeEach
  public void init() throws Exception {
    ContactoEmail medioDeContactoPepe = mock(ContactoEmail.class); //new ContactoEmail("pepe@gmail.com");
    SinApuros configPepe = new SinApuros(medioDeContactoPepe);
    configPepe.agregarHorario(LocalTime.now().plus(10, ChronoUnit.SECONDS));
    configPepe.agregarHorario(LocalTime.of(20,30,0));
    configPepe.agregarHorario(LocalTime.of(23,0,0));
    configPepe.agregarHorario(LocalTime.of(19,0,0));

    doAnswer(invocationOnMock -> {
      Object[] args = invocationOnMock.getArguments();
      Notificacion notificacion = (Notificacion) args[0];
      System.out.println(notificacion.getMensajeDeNotificacion());
      return null;
    }).when(medioDeContactoPepe).notificar(any(Notificacion.class));


    ContactoEmail medioDeContactoJose = mock(ContactoEmail.class); //new ContactoEmail("jose@gmail.com");
    CuandoSuceden configJose = new CuandoSuceden(medioDeContactoJose);

    doAnswer(invocationOnMock -> {
      Object[] args = invocationOnMock.getArguments();
      Notificacion notificacion = (Notificacion) args[0];
      System.out.println(notificacion.getMensajeDeNotificacion());
      return null;
    }).when(medioDeContactoJose).notificar(any(Notificacion.class));

    pepe = new Persona("pepe", "gonzalez", "pepegonz", "askfakof", configPepe);
    jose = new Persona("jose", "gonzalez", "josegonz", "askfakof", configJose);

    unServicio = new Servicio("Escalera mecanica 2do piso", "", null);
    comunidad = new Comunidad("Comunidad1");
    pepe.darseAltaComunidad(comunidad);
    jose.darseAltaComunidad(comunidad);
    comunidad.agregarServicioDeInteres(unServicio);

  }

  @Test
  public void seEnvianLasNotificacionesAlAbrirUnIncidente() throws InterruptedException {
    Incidente incidente = new Incidente("La escalera mecanica del segundo piso no esta en funcionamiento", pepe, unServicio);
    CountDownLatch lock = new CountDownLatch(1);

    comunidad.informarNuevoIncidente(incidente);

    lock.await(12, TimeUnit.SECONDS); // para que el test no finalice y le de tiempo a testear el "SinApuros"
  }

  @Test
  public void seEnvianLasNotificacionesAlCerrarUnIncidente() throws InterruptedException {
    Incidente incidente = new Incidente("La escalera mecanica del segundo piso no esta en funcionamiento", pepe, unServicio);
    CountDownLatch lock = new CountDownLatch(1);

    comunidad.informarIncidenteResuelto(incidente);

    lock.await(12, TimeUnit.SECONDS); // para que el test no finalice y le de tiempo a testear el "SinApuros"
  }
}
