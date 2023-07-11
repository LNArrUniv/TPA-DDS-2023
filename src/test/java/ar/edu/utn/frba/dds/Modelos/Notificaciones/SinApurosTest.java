package ar.edu.utn.frba.dds.Modelos.Notificaciones;

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

public class SinApurosTest {
  Persona pepe;
  Persona jose;
  Servicio unServicio;
  Comunidad comunidad;

  @BeforeEach
  public void init(){
    MedioDeContacto medioDeContactoPepe = new ContactoEmail("pepe@gmail.com");
    SinApuros configPepe = new SinApuros(medioDeContactoPepe);
    configPepe.agregarHorario(LocalTime.now().plus(30, ChronoUnit.SECONDS));
    configPepe.agregarHorario(LocalTime.of(20,23,0));
    configPepe.agregarHorario(LocalTime.of(23,0,0));
    configPepe.agregarHorario(LocalTime.of(19,0,0));

    MedioDeContacto medioDeContactoJose = new ContactoEmail("jose@gmail.com");
    ConfiguracionNotificaciones configJose = new CuandoSuceden(medioDeContactoJose);

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
    Incidente incidente = new Incidente("La escalera mecanica del segundo piso no esta en funcionamiento", pepe);
    CountDownLatch lock = new CountDownLatch(1);

    comunidad.informarNuevoIncidente(unServicio, incidente);

    lock.await(40, TimeUnit.SECONDS); // para que el test no finalice y le de tiempo a testear el "SinApuros"
  }

  @Test
  public void seEnvianLasNotificacionesAlCerrarUnIncidente() throws InterruptedException {
    Incidente incidente = new Incidente("La escalera mecanica del segundo piso no esta en funcionamiento", pepe);
    CountDownLatch lock = new CountDownLatch(1);

    comunidad.informarIncidenteResuelto(unServicio, incidente);

    lock.await(40, TimeUnit.SECONDS); // para que el test no finalice y le de tiempo a testear el "SinApuros"
  }
}
