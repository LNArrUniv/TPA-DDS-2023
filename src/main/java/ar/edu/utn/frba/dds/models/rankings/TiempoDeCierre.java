package ar.edu.utn.frba.dds.models.rankings;

import ar.edu.utn.frba.dds.models.Entidad;
import ar.edu.utn.frba.dds.models.Incidente;
import ar.edu.utn.frba.dds.persistencia.repositorios.RepositorioIncidentes;
import ar.edu.utn.frba.dds.persistencia.repositorios.RepositoriosItemsRankings;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;

@Entity
public class TiempoDeCierre extends MetodosRanking {
  public TiempoDeCierre() {
    this.nombre = "Mayor promedio de tiempo de cierre de incidentes";
    //this.rankingTiempoPromedio = new ArrayList<>();
  }

  @Override
  public List<ItemRanking> generarRanking(List<Entidad> entidades) {
    List<ItemRanking> rankingTiempoPromedio = new ArrayList<>();
    for (Entidad entidad : entidades) {
      ItemRanking item = new ItemRanking(entidad, promedioCierre(entidad),
          LocalDateTime.now(), this);
      rankingTiempoPromedio.add(item);
      RepositoriosItemsRankings.getInstance().add(item);
    }
    return rankingTiempoPromedio;
  }

  public double promedioCierre(Entidad entidad) {
    List<Incidente> incidentesDeEntidad = RepositorioIncidentes.getInstance()
        .incidentesDeEntidad(entidad);
    List<Incidente> incidentesResueltosDeEntidad = incidentesDeEntidad.stream().filter(incidente ->
        incidente.getEstaResuelto()).toList();
    if (incidentesResueltosDeEntidad.isEmpty()) {
      return 0;
    } else {
      double acumulado = 0;
      for (Incidente incidente : incidentesResueltosDeEntidad) {
        acumulado += tiempoReparacion(incidente);
      }
      return acumulado / incidentesResueltosDeEntidad.size();
    }
  }

  private double tiempoReparacion(Incidente incidente) {
    double diff = ChronoUnit.MINUTES.between(incidente.getFechaHoraApertura(),
        incidente.getFechaHoraCierre()) / 60.0;
    // Sigue expresado en horas por el /60,
    // pero lo puse en minutos para que no devuelva 0 cuando se arregla en menos de 1 hora
    return Math.abs(diff);
  }
}
