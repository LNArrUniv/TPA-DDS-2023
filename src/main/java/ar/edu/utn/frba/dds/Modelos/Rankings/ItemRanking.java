package ar.edu.utn.frba.dds.Modelos.Rankings;

import ar.edu.utn.frba.dds.Modelos.Entidad;
import lombok.Getter;
import lombok.Setter;

public class ItemRanking {
  @Getter @Setter
  private Entidad entidad;
  @Getter @Setter
  private Double valorParametro;

  public ItemRanking(Entidad entidad, Double valorParametro) {
    this.entidad = entidad;
    this.valorParametro = valorParametro;
  }
}
