package ar.edu.utn.frba.dds.persistencia;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
public class EntidadPersistente {
  @Setter
  @Getter
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

}
