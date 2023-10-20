package ar.edu.utn.frba.dds.Modelos.Rankings;

import ar.edu.utn.frba.dds.Modelos.Entidad;
import ar.edu.utn.frba.dds.Modelos.Incidente;
import ar.edu.utn.frba.dds.Persistencia.repositorios.RepositorioEntidades;
import ar.edu.utn.frba.dds.Persistencia.repositorios.RepositorioIncidentes;
import ar.edu.utn.frba.dds.Persistencia.repositorios.RepositoriosItemsRankings;
import lombok.Getter;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Entity
public class TiempoDeCierre extends MetodosRanking {
  @Getter
  @Column
  private String nombre = "Mayor promedio de tiempo de cierre de incidentes";
  /*
  @Getter
  @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, mappedBy = "ranking")
  private List<ItemRanking> rankingTiempoPromedio;
   */

  public TiempoDeCierre() {
    //this.rankingTiempoPromedio = new ArrayList<>();
  }

  @Override
  public void generarRanking(List<Entidad> entidades) {
    for (Entidad entidad : entidades) {
      ItemRanking item = new ItemRanking(entidad, promedioCierre(entidad), LocalDateTime.now(), this);
      //rankingTiempoPromedio.add(item);
      RepositoriosItemsRankings.getInstance().add(item);
    }
  }

  public double promedioCierre(Entidad entidad) {
    List<Incidente> incidentesDeEntidad = RepositorioIncidentes.getInstance().incidentesDeEntidad(entidad);
    List<Incidente> incidentesResueltosDeEntidad = incidentesDeEntidad.stream().filter(incidente -> incidente.getEstaResuelto()).toList();
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
    double diff = ChronoUnit.MINUTES.between(incidente.getFechaHoraApertura(), incidente.getFechaHoraCierre()) / 60.0;
    // Sigue expresado en horas por el /60, pero lo puse en minutos para que no devuelva 0 cuando se arregla en menos de 1 hora
    return Math.abs(diff);
  }
}
