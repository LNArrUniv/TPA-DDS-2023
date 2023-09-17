package ar.edu.utn.frba.dds.Modelos.Rankings;

import ar.edu.utn.frba.dds.Modelos.Entidad;
import ar.edu.utn.frba.dds.Modelos.Incidente;
import ar.edu.utn.frba.dds.Servicio.gradoDeImpacto.CalculadorGradoDeImpactoService;
import ar.edu.utn.frba.dds.Servicio.gradoDeImpacto.ListadoDeResultados;
import ar.edu.utn.frba.dds.Servicio.gradoDeImpacto.ListadoDeValores;
import ar.edu.utn.frba.dds.Servicio.gradoDeImpacto.ValoresFormula;
import java.io.IOException;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class GradoImpacto implements MetodosRanking {
    private ArrayList<ItemRanking> rankingGradoDeImpacto = new ArrayList<>();

    final private Integer CNF = 1; //TODO: cambiar  (Preguntar al ayudante!)

    private double tiempoReparacion(Incidente incidente) {
        double diff = ChronoUnit.MINUTES.between(incidente.getFechaHoraCierre(), incidente.getFechaHoraApertura()) / 60.0;
        // Sigue expresado en horas por el /60, pero lo puse en minutos para que no devuelva 0 cuando se arregla en menos de 1 hora
        return Math.abs(diff);
    }

    private void enviarValoresAAPI(List<Entidad> entidades){
        List<ValoresFormula> listaValores = new ArrayList<>();
        for (Entidad entidad:entidades) {
            Integer cantIncidentesNoResueltos = Math.toIntExact(entidad.getIncidentes().stream().filter(i -> !i.getEstaResuelto()).count());
            double tiempoResolucionIncidente = entidad.getIncidentes().stream()
                .filter(Incidente::getEstaResuelto)
                .mapToDouble(this::tiempoReparacion)
                .sum();
            int totalPersonasImpactadas = entidad.getIncidentes().stream().mapToInt(i -> i.getComunidad().totalMiembrosAfectados()).sum();

            listaValores.add(new ValoresFormula(entidad.getId(), (int) tiempoResolucionIncidente, cantIncidentesNoResueltos, CNF, totalPersonasImpactadas));
        }
        ListadoDeValores valores = new ListadoDeValores(listaValores);
        CalculadorGradoDeImpactoService.getInstancia().calcularGradoDeImpacto(valores);
    }

    @Override
    public void generarRanking(ArrayList<Entidad> entidades) throws IOException {
        enviarValoresAAPI(entidades);
        ListadoDeResultados resultados = CalculadorGradoDeImpactoService.getInstancia().obtenerResultados();
        for (Entidad entidad : entidades) {

            rankingGradoDeImpacto.add(new ItemRanking(entidad, resultados.valorDeEntidad(entidad.getId()).get().getResultadoGradoImpacto()));
        }
    }
}
