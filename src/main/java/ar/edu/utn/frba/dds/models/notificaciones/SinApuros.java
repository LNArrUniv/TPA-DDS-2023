package ar.edu.utn.frba.dds.models.notificaciones;

import ar.edu.utn.frba.dds.persistencia.converters.HorariosDeNotificacionAttributeConverter;
import ar.edu.utn.frba.dds.persistencia.converters.MedioDeContactoPreferidoAttributeConverter;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.TreeSet;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;
import lombok.Getter;

@Entity
@DiscriminatorValue(value = "Sin apuros")
public class SinApuros extends ConfiguracionNotificaciones {
  @Getter
  @Convert(converter = MedioDeContactoPreferidoAttributeConverter.class)
  @Column(name = "contacto")
  private MedioDeNotificacionesPreferido medio;
  @Getter
  @Convert(converter = HorariosDeNotificacionAttributeConverter.class)
  @Column(name = "horariosDeNotificacion")
  private TreeSet<LocalTime> horariosDeNotificacion = new TreeSet<>();
  @Transient
  private Boolean running = false;
  @Transient
  private ArrayList<Notificacion> notificacionesPendientes = new ArrayList<>();

  public SinApuros(MedioDeNotificacionesPreferido medio) {
    this.medio = medio;
  }

  public SinApuros() {

  }

  @Override
  public void notificarMiembro(Notificacion notificacion) {
    notificacionesPendientes.add(notificacion);
    if (!running) {
      running = true;

      LocalTime horaNotificacion = horariosDeNotificacion.ceiling(LocalTime.now());
      long tiempoHastaNotificacion = 0L;
      if (horaNotificacion != null) {
        tiempoHastaNotificacion = LocalTime.now().until(horaNotificacion, ChronoUnit.MILLIS);
      }

      Timer timer = new Timer();
      timer.schedule(new Task(), tiempoHastaNotificacion);
    }
  }

  private void notificarPendientes() {
    notificacionesPendientes.forEach(notificacion -> {
      try {
        medio.notificar(notificacion);
      } catch (Exception e) {
        throw new RuntimeException(e);
      }
    });
  }

  public void agregarHorario(LocalTime horario) {
    horariosDeNotificacion.add(horario);
  }

  public void eliminarHorario(LocalTime horario) {
    horariosDeNotificacion.remove(horario);
  }

  private class Task extends TimerTask {
    @Override
    public void run() {
      notificarPendientes();
      running = false;
    }
  }
}
