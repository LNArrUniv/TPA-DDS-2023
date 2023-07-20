package ar.edu.utn.frba.dds.Modelos.Rankings;

import ar.edu.utn.frba.dds.Modelos.Entidad;
import ar.edu.utn.frba.dds.Modelos.Notificaciones.SinApuros;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.time.temporal.ChronoUnit;
import java.util.Timer;
import java.util.TimerTask;

public class RankingIncidentes {

  private ArrayList<Entidad> entidades;
  private ArrayList<MetodosRanking> metodosRankings; //[TiempoDeCierre,MayorCantidadIncidentes,GradoImpacto]


  private void filtrarIncidentes(ArrayList<Entidad> entidades) {
    entidades.forEach(entidad -> entidad.getIncidentes());
  }

  private void generarRankings() {
    Timer tempo = new Timer();
    LocalDateTime semanaPasada = LocalDateTime.now().minus(7, ChronoUnit.DAYS);
    long duracionSemanaEnMiliseg = LocalDateTime.now().until(semanaPasada, ChronoUnit.MILLIS);
    //tempo.schedule(new GeneradorRanking(), semana);
    tempo.scheduleAtFixedRate(new GeneradorRanking(), 0, duracionSemanaEnMiliseg); // Para que ejecute el generador 1 vez y despues lo haga cada 7 dias
  }

  private class GeneradorRanking extends TimerTask {

    @Override
    public void run() {
      metodosRankings.forEach(metodo -> metodo.generarRanking(entidades));
    }
  }
}
