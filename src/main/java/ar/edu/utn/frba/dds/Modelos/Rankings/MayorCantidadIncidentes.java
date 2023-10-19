package ar.edu.utn.frba.dds.Modelos.Rankings;

import ar.edu.utn.frba.dds.Modelos.Entidad;
import ar.edu.utn.frba.dds.Modelos.Incidente;
import ar.edu.utn.frba.dds.Persistencia.repositorios.RepositorioIncidentes;
import ar.edu.utn.frba.dds.Persistencia.repositorios.RepositoriosItemsRankings;
import lombok.Getter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Entity
public class MayorCantidadIncidentes extends MetodosRanking {
  @Getter
  @Column
  private String nombre = "Mayor cantidad de incidentes en la semana";
  //public LocalDateTime horarioActual;
  /*
  @Getter
  @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, mappedBy = "ranking")
  private List<ItemRanking> rankingCantidadIncidentes;
   */

  public MayorCantidadIncidentes() {
    //this.rankingCantidadIncidentes = new ArrayList<>();
  }

  @Override
  public void generarRanking(List<Entidad> entidades) {
    for (Entidad entidad : entidades) {
      List<Incidente> incidentesEntidad = RepositorioIncidentes.getInstance().incidentesDeEntidad(entidad);
      long cantidadIncidentes = incidentesEntidad.stream().filter(incidente -> sonDelPeriodo(incidente)).count();
      double cantidad = (double) cantidadIncidentes;
      ItemRanking item = new ItemRanking(entidad, cantidad, LocalDateTime.now(), this);
      //rankingCantidadIncidentes.add(item);
      RepositoriosItemsRankings.getInstance().add(item);
    }
  }

  private Boolean sonDelPeriodo(Incidente incidente) {
    long tiempo = ChronoUnit.DAYS.between(incidente.getFechaHoraApertura(), LocalDateTime.now());
    return tiempo < 7;
  }


}
