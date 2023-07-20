package ar.edu.utn.frba.dds.Modelos.Rankings;

import ar.edu.utn.frba.dds.Modelos.Entidad;
import ar.edu.utn.frba.dds.Modelos.Incidente;
import lombok.Getter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class TiempoDeCierre implements MetodosRanking {
  @Getter
  private ArrayList<ItemRanking> rankingTiempoPromedio = new ArrayList<>();

  @Override
  public void generarRanking(ArrayList<Entidad> entidades) {
    for (Entidad entidad : entidades) {
      rankingTiempoPromedio.add(new ItemRanking(entidad, promedioCierre(entidad)));
    }
  }

  public double promedioCierre(Entidad entidad) {
    double acumulado = 0;
    for (Incidente incidente : entidad.getIncidentes()) {
      acumulado += tiempoReparacion(incidente);
    }
    return acumulado / entidad.getIncidentes().size();
  }

  private double tiempoReparacion(Incidente incidente) { //TODO puse el tiempo en horas pq creo que era el mejor periodo de tiempo
    double diff = ChronoUnit.MINUTES.between(incidente.getFechaHoraCierre(), incidente.getFechaHoraApertura()) / 60.0;
    return Math.abs(diff);
  }
}
