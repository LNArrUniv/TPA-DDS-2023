package ar.edu.utn.frba.dds.models.ubicacion;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Transient;
import lombok.Getter;

@Embeddable
public class Localidad {
  @Getter
  @Transient
  public long id;
  @Column
  @Getter
  public String nombre;

}
