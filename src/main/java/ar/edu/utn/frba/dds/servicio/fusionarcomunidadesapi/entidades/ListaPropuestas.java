package ar.edu.utn.frba.dds.servicio.fusionarcomunidadesapi.entidades;

import java.util.List;
import lombok.Getter;


public class ListaPropuestas {
  @Getter
  public List<PropuestaDeFusionDto> propuestas;

  public ListaPropuestas(List<PropuestaDeFusionDto> propuestas) {
    this.propuestas = propuestas;
  }
}
