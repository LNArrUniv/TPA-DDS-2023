package ar.edu.utn.frba.dds.models.rankings;

import ar.edu.utn.frba.dds.models.Entidad;
import ar.edu.utn.frba.dds.models.Incidente;
import ar.edu.utn.frba.dds.persistencia.repositorios.RepositorioIncidentes;
import ar.edu.utn.frba.dds.persistencia.repositorios.RepositoriosItemsRankings;
import ar.edu.utn.frba.dds.servicio.gradodeimpactoapi.CalculadorGradoDeImpactoService;
import ar.edu.utn.frba.dds.servicio.gradodeimpactoapi.entidades.ListadoDeResultados;
import ar.edu.utn.frba.dds.servicio.gradodeimpactoapi.entidades.ListadoDeValores;
import ar.edu.utn.frba.dds.servicio.gradodeimpactoapi.entidades.ValoresFormula;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;

@Entity
public class GradoImpacto extends MetodosRanking {
  // El CNF es algo que sabemos nosotros y le enviamos a la api
  private final Integer cnf = 1;

  public GradoImpacto() {
    this.nombre = "Mayor grado de impacto de las problematicas";
    //this.rankingGradoDeImpacto = new ArrayList<>();
  }

  private double tiempoReparacion(Incidente incidente) {
    double diff = ChronoUnit.MINUTES.between(incidente.getFechaHoraCierre(),
        incidente.getFechaHoraApertura()) / 60.0;
    // Sigue expresado en horas por el /60,
    // pero lo puse en minutos para que no devuelva 0 cuando se arregla en menos de 1 hora
    return Math.abs(diff);
  }

  private void enviarValoresAapi(List<Entidad> entidades) {
    List<ValoresFormula> listaValores = new ArrayList<>();
    for (Entidad entidad : entidades) {
      List<Incidente> incidentesEntidad = RepositorioIncidentes.getInstance()
          .incidentesDeEntidad(entidad);
      int cantIncidentesNoResueltos = incidentesEntidad.stream().filter(incidente ->
          !incidente.getEstaResuelto()).toList().size();
      double tiempoResolucionIncidente = incidentesEntidad.stream()
          .filter(Incidente::getEstaResuelto)
          .mapToDouble(this::tiempoReparacion)
          .sum();
      int totalPersonasImpactadas = incidentesEntidad.stream().mapToInt(i ->
          i.getComunidad().totalMiembrosAfectados()).sum();

      listaValores.add(new ValoresFormula(entidad.getId(), (int) tiempoResolucionIncidente,
          cantIncidentesNoResueltos, cnf, totalPersonasImpactadas));
    }
    ListadoDeValores valores = new ListadoDeValores(listaValores);
    CalculadorGradoDeImpactoService.getInstancia().calcularGradoDeImpacto(valores);
  }

  @Override
  public List<ItemRanking> generarRanking(List<Entidad> entidades)
      throws IOException, InterruptedException {
    List<ItemRanking> rankingGradoDeImpacto = new ArrayList<>();
    enviarValoresAapi(entidades);
    Thread.sleep(500);
    ListadoDeResultados resultados = CalculadorGradoDeImpactoService.getInstancia()
        .obtenerResultados();
    for (Entidad entidad : entidades) {
      ItemRanking item = new ItemRanking(entidad, resultados
          .valorDeEntidad(entidad.getId()).getResultadoGradoImpacto(), LocalDateTime.now(), this);
      rankingGradoDeImpacto.add(item);
      RepositoriosItemsRankings.getInstance().add(item);
    }
    return rankingGradoDeImpacto;
  }
}
