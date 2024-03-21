package ar.edu.utn.frba.dds.servicio.fusionarcomunidadesapi.entidades;

public class PropuestaDeFusionDto {
  private ComunidadDto unaComunidad;
  private ComunidadDto otraComunidad;

  public PropuestaDeFusionDto() {}

  public PropuestaDeFusionDto(ComunidadDto unaComunidad, ComunidadDto otraComunidad) {
    this.unaComunidad = unaComunidad;
    this.otraComunidad = otraComunidad;
  }

  // Getters y setters

  public ComunidadDto getUnaComunidad() {
    return unaComunidad;
  }

  public void setUnaComunidad(ComunidadDto unaComunidad) {
    this.unaComunidad = unaComunidad;
  }

  public ComunidadDto getOtraComunidad() {
    return otraComunidad;
  }

  public void setOtraComunidad(ComunidadDto otraComunidad) {
    this.otraComunidad = otraComunidad;
  }
}