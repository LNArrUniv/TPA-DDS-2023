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
public class MayorCantidadIncidentes extends MetodosRanking {
  public MayorCantidadIncidentes() {
    this.nombre = "Mayor cantidad de incidentes en la semana";
    //this.rankingCantidadIncidentes = new ArrayList<>();
  }

  @Override
  public List<ItemRanking> generarRanking(List<Entidad> entidades) {
    List<ItemRanking> rankingCantidadIncidentes = new ArrayList<>();
    for (Entidad entidad : entidades) {
      List<Incidente> incidentesEntidad = RepositorioIncidentes.getInstance()
          .incidentesDeEntidad(entidad);
      long cantidadIncidentes = incidentesEntidad.stream().filter(incidente ->
          sonDelPeriodo(incidente)).count();
      double cantidad = (double) cantidadIncidentes;
      ItemRanking item = new ItemRanking(entidad, cantidad, LocalDateTime.now(), this);
      rankingCantidadIncidentes.add(item);
      RepositoriosItemsRankings.getInstance().add(item);
    }
    return rankingCantidadIncidentes;
  }

  private Boolean sonDelPeriodo(Incidente incidente) {
    long tiempo = ChronoUnit.DAYS.between(incidente.getFechaHoraApertura(), LocalDateTime.now());
    return tiempo < 7;
  }
}
