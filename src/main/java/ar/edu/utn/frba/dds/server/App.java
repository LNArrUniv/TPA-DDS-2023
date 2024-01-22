package ar.edu.utn.frba.dds.server;

import ar.edu.utn.frba.dds.models.rankings.RankingIncidentes;
import ar.edu.utn.frba.dds.persistencia.repositorios.RepositorioRankings;

public class App {

  public static void main(String[] args) {
    if (RepositorioRankings.getInstance().all().isEmpty()) {
      RankingIncidentes.getInstance().generarRankings();
    }

    Server.init();
  }
}
