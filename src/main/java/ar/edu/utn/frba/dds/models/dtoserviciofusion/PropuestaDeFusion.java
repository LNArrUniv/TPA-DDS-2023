package ar.edu.utn.frba.dds.models.dtoserviciofusion;

import ar.edu.utn.frba.dds.models.comunidades.Comunidad;
import lombok.Getter;

public class PropuestaDeFusion {
  @Getter
  private Comunidad unaComunidad;
  @Getter
  private Comunidad otraComunidad;

  public PropuestaDeFusion(Comunidad unaComunidad, Comunidad otraComunidad) {
    this.unaComunidad = unaComunidad;
    this.otraComunidad = otraComunidad;
  }
}
