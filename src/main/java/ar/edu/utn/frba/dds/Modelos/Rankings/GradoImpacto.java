package ar.edu.utn.frba.dds.Modelos.Rankings;

import ar.edu.utn.frba.dds.Modelos.Entidad;
import ar.edu.utn.frba.dds.Modelos.Incidente;
import ar.edu.utn.frba.dds.Modelos.Membresia;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GradoImpacto implements MetodosRanking {
    @Override
    public void generarRanking(ArrayList<Entidad> entidades) {

    }
    final private Integer CNF = 1; //TODO: cambiar  (Preguntar al ayudante!)

    public List<Membresia> personasAfectadas(Incidente incidente){
        return incidente.getComunidad().getMiembros();
    }

    public List<Membresia> calculoImpactoIncidente(Incidente incidente){
        return personasAfectadas(incidente).stream().distinct().collect(Collectors.toList());
    }

    public Integer cantidadPersonasImpactadasPorIncidente(Incidente incidente){
        return calculoImpactoIncidente(incidente).size();
    }

    public Long calculoImpacto(List<Incidente> incidentes){
        Long cantIncidentesNoResueltos = incidentes.stream().filter(i -> !i.getResuelto()).count();
        Integer tiempoResolucionIncidente = incidentes.stream()
           .filter(i -> i.getResuelto()==true)
           .mapToInt(i -> (int) (i.getFechaHoraCierre().getSecond() - i.getFechaHoraApertura().getSecond()))
           .sum();

        Integer totalPersonasImpactadas = incidentes.stream().mapToInt(i -> cantidadPersonasImpactadasPorIncidente(i)).sum();

        tiempoResolucionIncidente /=3600;

        return (tiempoResolucionIncidente + cantIncidentesNoResueltos * CNF) * totalPersonasImpactadas ;
    }

    public List<ItemRanking> generarRanking(List<Entidad> entidades) {
        List<ItemRanking> ranking = new ArrayList<>();
        for (Entidad entidad : entidades) {
            double impactoTotal = entidad.getIncidentes().stream()
                .mapToDouble(incidente -> calculoImpacto((List<Incidente>) incidente))
                .sum();

            ranking.add(new ItemRanking(entidad, impactoTotal));
        }
        return ranking;
    }
}
