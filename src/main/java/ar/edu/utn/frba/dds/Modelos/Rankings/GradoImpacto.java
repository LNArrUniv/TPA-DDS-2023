package ar.edu.utn.frba.dds.Modelos.Rankings;

import ar.edu.utn.frba.dds.Modelos.Entidad;
import ar.edu.utn.frba.dds.Modelos.Incidente;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class GradoImpacto implements MetodosRanking { //TODO no esta bien definido en el TP pero hay que establecer alguna relacion entre incidente-entidad-comunidad

    private Double calcularImpacto(Incidente incidente){
        int totalMiembros = incidente.getComunidad().totalMiembros();
        // Ver en un futuro como se calcula el impacto dado la cantidad de miembros
        double impacto=0;
        return impacto;
    }

    @Override
    public void generarRanking(ArrayList<Entidad> entidades) {
        for (Entidad entidad:entidades) {
            entidad.getIncidentes().forEach(incidente -> calcularImpacto(incidente));//.forEach(incidente -> calcularImpacto(incidente));
        }
    }
}
