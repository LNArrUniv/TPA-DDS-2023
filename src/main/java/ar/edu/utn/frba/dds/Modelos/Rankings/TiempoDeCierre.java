package ar.edu.utn.frba.dds.Modelos.Rankings;

import ar.edu.utn.frba.dds.Modelos.Entidad;
import ar.edu.utn.frba.dds.Modelos.Incidente;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class TiempoDeCierre implements MetodosRanking {

    private ArrayList<ItemRanking> rankingTiempoPromedio;

    @Override
    public void generarRanking(ArrayList<Entidad> entidades){
        for (Entidad entidad: entidades) {
            rankingTiempoPromedio.add(new ItemRanking(entidad,promedioCierre(entidad)));
        }
    }

    public double promedioCierre(Entidad entidad){
        double acumulado=0;
        for (Incidente incidente:entidad.getIncidentes()) {
            acumulado+=tiempoReparacion(incidente);
        }
        return acumulado/entidad.getIncidentes().size();
    }

    private float tiempoReparacion(Incidente incidente){ //TODO puse el tiempo en horas pq creo que era el mejor periodo de tiempo
        long diff = ChronoUnit.HOURS.between(incidente.getFechaHoraCierre(),incidente.getFechaHoraApertura());
        return (float) diff;
    }
}
