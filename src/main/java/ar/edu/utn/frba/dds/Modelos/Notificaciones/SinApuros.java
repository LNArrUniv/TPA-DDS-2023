package ar.edu.utn.frba.dds.Modelos.Notificaciones;

import ar.edu.utn.frba.dds.Persistencia.converters.MedioDeContactoPreferidoAttributeConverter;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

import java.util.Timer;
import java.util.TimerTask;
import java.util.TreeSet;

@Entity
@DiscriminatorValue(value = "Sin apuros")
public class SinApuros extends ConfiguracionNotificaciones{
  @Convert(converter = MedioDeContactoPreferidoAttributeConverter.class)
  @Column(name = "contacto")
  private MedioDeNotificacionesPreferido medio;
  @Transient
  private TreeSet<LocalTime> horariosDeNotificacion;
  @Transient
  private Boolean running;
  @Transient
  private ArrayList<Notificacion> notificacionesPendientes;

  public SinApuros(MedioDeNotificacionesPreferido medio) {
    this.medio = medio;
    this.running = false;
    this.horariosDeNotificacion = new TreeSet<>();
    this.notificacionesPendientes = new ArrayList<>();
  }

  public SinApuros() {

  }

  @Override
  public void notificarMiembro(Notificacion notificacion) {
    notificacionesPendientes.add(notificacion);
    if(!running){
      running = true;

      LocalTime horaNotificacion = horariosDeNotificacion.ceiling(LocalTime.now());
      long tiempoHastaNotificacion = LocalTime.now().until(horaNotificacion, ChronoUnit.MILLIS);

      Timer timer = new Timer();
      timer.schedule(new Task(), tiempoHastaNotificacion);
    }
  }

  private void notificarPendientes(){
    notificacionesPendientes.forEach(notificacion -> {
      try {
        medio.notificar(notificacion);
      } catch (Exception e) {
        throw new RuntimeException(e);
      }
    });
  }

  public void agregarHorario(LocalTime horario){
    horariosDeNotificacion.add(horario);
  }

  public void eliminarHorario(LocalTime horario){
    horariosDeNotificacion.remove(horario);
  }

  private class Task extends TimerTask {
    @Override
    public void run(){
      notificarPendientes();
      running = false;
    }
  }
}
