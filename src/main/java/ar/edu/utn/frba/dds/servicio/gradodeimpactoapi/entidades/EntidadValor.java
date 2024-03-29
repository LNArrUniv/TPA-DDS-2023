package ar.edu.utn.frba.dds.servicio.gradodeimpactoapi.entidades;

import lombok.Getter;

@Getter
public class EntidadValor {
  public long entidadId;
  public double resultadoGradoImpacto;

  public EntidadValor(long entidadId, double resultadoGradoImpacto) {
    this.entidadId = entidadId;
    this.resultadoGradoImpacto = resultadoGradoImpacto;
  }
}
