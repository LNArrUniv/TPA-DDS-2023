package ar.edu.utn.frba.dds.servicio.gradodeimpactoapi.entidades;

import java.util.List;

public class ListadoDeValores {
  List<ValoresFormula> valoresPorEntidad;

  public ListadoDeValores(List<ValoresFormula> valoresPorEntidad) {
    this.valoresPorEntidad = valoresPorEntidad;
  }
}
