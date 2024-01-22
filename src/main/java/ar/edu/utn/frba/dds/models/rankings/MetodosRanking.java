package ar.edu.utn.frba.dds.models.rankings;

import ar.edu.utn.frba.dds.models.Entidad;
import ar.edu.utn.frba.dds.persistencia.EntidadPersistente;
import java.io.IOException;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import lombok.Getter;

@Entity
@Table(name = "rankings")
//@DiscriminatorColumn(name = "nombre", discriminatorType = DiscriminatorType.STRING)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class MetodosRanking extends EntidadPersistente {
  @Getter
  @Column
  protected String nombre;

  public List<ItemRanking> generarRanking(List<Entidad> entidades)
      throws IOException, InterruptedException {

    return null;
  }
}
