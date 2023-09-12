package ar.edu.utn.frba.dds.Modelos.UbicacionDTO;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Transient;

@Embeddable
public class Provincia {
  @Transient
  public long id;
  @Column
  public String nombre;
}
