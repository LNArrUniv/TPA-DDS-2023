package ar.edu.utn.frba.dds.Modelos.Notificaciones;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

import java.util.Timer;
import java.util.TimerTask;
import java.util.TreeSet;


public class SinApuros implements ConfiguracionNotificaciones{
  private MedioDeContacto medioDeContacto;
  private TreeSet<LocalTime> horariosDeNotificacion;
  private Boolean running = false;
  private ArrayList<Notificacion> notificacionesPendientes;

  public SinApuros(MedioDeContacto medioDeContacto) {
    this.medioDeContacto = medioDeContacto;
    this.running = false;
    this.horariosDeNotificacion = new TreeSet<>();
    this.notificacionesPendientes = new ArrayList<>();
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
    notificacionesPendientes.forEach(notificacion -> medioDeContacto.notificar(notificacion));
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
