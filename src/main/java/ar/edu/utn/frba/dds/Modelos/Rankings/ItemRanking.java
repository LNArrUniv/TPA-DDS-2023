package ar.edu.utn.frba.dds.Modelos.Rankings;

import ar.edu.utn.frba.dds.Modelos.Entidad;
import lombok.Getter;

public class ItemRanking {
  @Getter
  private Entidad entidad;
  @Getter
  private Double valorParametro;

  public ItemRanking(Entidad entidad, Double valorParametro) {
    this.entidad = entidad;
    this.valorParametro = valorParametro;
  }

  public void setEntidad(Entidad entidad) {
    this.entidad = entidad;
  }

  public void setValorParametro(Double valorParametro) {
    this.valorParametro = valorParametro;
  }
}
