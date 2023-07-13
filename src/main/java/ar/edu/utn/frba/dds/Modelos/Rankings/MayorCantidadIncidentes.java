package ar.edu.utn.frba.dds.Modelos.Rankings;

import ar.edu.utn.frba.dds.Modelos.Entidad;
import ar.edu.utn.frba.dds.Modelos.Incidente;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class MayorCantidadIncidentes implements MetodosRanking{

    //public LocalDateTime horarioActual;
    private ArrayList<ItemRanking> rankingCantidadIncidentes;

    @Override
    public void generarRanking(ArrayList<Entidad> entidades) {
        for (Entidad entidad:entidades) {
            long cantidadIncidentes = entidad.getIncidentes().stream().filter(incidente -> sonDelPeriodo(incidente)).count();
            double cantidad = cantidadIncidentes;
            rankingCantidadIncidentes.add(new ItemRanking(entidad,cantidad));
        }
    }

    private Boolean sonDelPeriodo(Incidente incidente){
            long tiempo = ChronoUnit.DAYS.between(LocalDateTime.now(),incidente.getFechaHoraCierre());
            return tiempo<7;
    }




}
