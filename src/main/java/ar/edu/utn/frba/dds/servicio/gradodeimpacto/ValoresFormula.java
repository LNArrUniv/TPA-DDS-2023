package ar.edu.utn.frba.dds.servicio.gradodeimpacto;

public class ValoresFormula {
  long entidadId;
  int tiempoResolucionIncidente;
  int cantIncidentesNoResueltos;
  int cnf;
  int totalPersonasImpactadas;

  public ValoresFormula(long entidadId, int tiempoResolucionIncidente,
                        int cantIncidentesNoResueltos, int cnf, int totalPersonasImpactadas) {
    this.entidadId = entidadId;
    this.tiempoResolucionIncidente = tiempoResolucionIncidente;
    this.cantIncidentesNoResueltos = cantIncidentesNoResueltos;
    this.cnf = cnf;
    this.totalPersonasImpactadas = totalPersonasImpactadas;
  }
}
