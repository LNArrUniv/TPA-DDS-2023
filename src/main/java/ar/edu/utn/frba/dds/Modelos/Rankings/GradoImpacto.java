package ar.edu.utn.frba.dds.Modelos.Rankings;

import ar.edu.utn.frba.dds.Modelos.Entidad;
import ar.edu.utn.frba.dds.Modelos.Incidente;
import ar.edu.utn.frba.dds.Modelos.Persona;
import java.util.List;
import java.util.stream.ArrayList;
import java.util.stream.Collectors;

public class GradoImpacto implements MetodosRanking {

    final private Integer CNF = 1; //TODO: cambiar  (Preguntar al ayudante!)

    public List<Persona> personasAfectadas(Incidente incidente){
        return incidente.stream().map( i -> i.getComunidad().getMiembros() );
    }

    public List<Persona> calculoImpactoIncidente(Incidente incidente){
        return personasAfectadas(incidente).stream().distinct().collect(Collectors.toList());;
    }

    public Integer cantidadPersonasImpactadasPorIncidente(Incidente incidente){
        return calculoImpactoIncidente(incidente).size();
    }

    public Integer calculoImpacto(List<Incidente> incidentes){
        Integer cantIncidentesNoResueltos = incidentes.stream().filter( i -> !i.resuelto ).count();
        Integer tiempoResolucionIncidente = incidentes.stream()
           .filter(i -> i.isResuelto())
           .mapToInt(i -> (int) (i.getFechaHoraCierre() - i.getFechaHoraApertura()))
           .sum();
        
        Integer totalPersonasImpactadas = incidentes.mapToInt( i -> cantidadPersonasImpactadasPorIncidente(i) ).sum();

        return (tiempoResolucionIncidente + cantIncidentesNoResueltos * CNF) * totalPersonasImpactadas ;
    }

    @Override
    public List<ItemRanking> generarRanking(List<Entidad> entidades) {
        List<ItemRanking> ranking = new ArrayList<>();
        for (Entidad entidad : entidades) {
            double impactoTotal = entidad.getIncidentes().stream()
                .mapToDouble(incidente -> calculoImpacto(incidente))
                .sum();

            ranking.add(new ItemRanking(entidad, impactoTotal));
        }
        return ranking;
    }

}
