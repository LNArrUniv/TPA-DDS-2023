package ar.edu.utn.frba.dds.Modelos.Rankings;

import ar.edu.utn.frba.dds.Modelos.Entidad;
import ar.edu.utn.frba.dds.Modelos.Notificaciones.SinApuros;
import ar.edu.utn.frba.dds.Persistencia.repositorios.RepositorioEntidades;
import lombok.Getter;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class RankingIncidentes {
  private static RankingIncidentes instance = null;

  @Getter
  private List<MetodosRanking> metodosRankings = List.of(new TiempoDeCierre(), new MayorCantidadIncidentes(), new GradoImpacto()); //[new TiempoDeCierre(),MayorCantidadIncidentes,GradoImpacto]
  @Getter
  private Boolean running = false;

  private RankingIncidentes(){
  }
  public static RankingIncidentes getInstance(){
    if (instance == null){
      instance = new RankingIncidentes();
    }
    return instance;
  }

  public void generarRankings() {

    Timer tempo = new Timer();
    LocalDateTime semanaPasada = LocalDateTime.now().minus(7, ChronoUnit.DAYS);
    long duracionSemanaEnMiliseg = semanaPasada.until(LocalDateTime.now(), ChronoUnit.MILLIS);

    running = true;
    // Para que ejecute el generador 1 vez y despues lo haga cada 7 dias
    tempo.scheduleAtFixedRate(new GeneradorRanking(), 0, duracionSemanaEnMiliseg);
  }

  private class GeneradorRanking extends TimerTask {
    @Override
    public void run() {
      metodosRankings.forEach(metodo -> {
        List entidades = RepositorioEntidades.getInstance().all();
        try {
          metodo.generarRanking(entidades);
        } catch (IOException e) {
          throw new RuntimeException(e);
        } catch (InterruptedException e) {
          throw new RuntimeException(e);
        }
      });
    }
  }

}
