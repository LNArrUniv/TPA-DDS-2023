package ar.edu.utn.frba.dds.models.dtoserviciofusion;

import java.util.List;

public class ComunidadObservadaDto {
  private List<ComunidadDto> comunidades;

  public ComunidadObservadaDto(List<ComunidadDto> comunidadesObservadas) {
    this.comunidades = comunidadesObservadas;
  }

  public List<ComunidadDto> getComunidades() {
    return comunidades;
  }

  public void setComunidades(List<ComunidadDto> comunidades) {
    this.comunidades = comunidades;
  }
}
