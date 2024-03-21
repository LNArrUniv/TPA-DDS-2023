package ar.edu.utn.frba.dds.models.ubicacion;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Transient;
import lombok.Getter;

@Embeddable
public class Provincia {
  @Getter
  @Transient
  public long id;
  @Getter
  @Column
  public String nombre;
}
