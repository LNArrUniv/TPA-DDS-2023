package ar.edu.utn.frba.dds.Modelos.Rankings;

import ar.edu.utn.frba.dds.Modelos.Entidad;
import ar.edu.utn.frba.dds.Persistencia.EntidadPersistente;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.time.LocalDate;

@Table(name = "item_ranking")
@Entity
public class ItemRanking extends EntidadPersistente {
  @Getter @Setter
  @ManyToOne(cascade = {CascadeType.MERGE})
  private Entidad entidad;
  @Getter @Setter
  @Column
  private Double valorParametro;
  @Getter @Setter
  @Column
  private LocalDate fecha;
  @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
  private MetodosRanking ranking;

  public ItemRanking(Entidad entidad, Double valorParametro, LocalDate fecha, MetodosRanking ranking) {
    this.entidad = entidad;
    this.valorParametro = valorParametro;
    this.fecha = fecha;
    this.ranking = ranking;
  }

  public ItemRanking() {

  }
}
