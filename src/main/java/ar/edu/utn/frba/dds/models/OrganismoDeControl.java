package ar.edu.utn.frba.dds.models;

import ar.edu.utn.frba.dds.models.usuarios.PersonaDesignada;
import ar.edu.utn.frba.dds.persistencia.EntidadPersistente;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Getter;

@Entity
@Table(name = "organismo_de_control")
public class OrganismoDeControl extends EntidadPersistente {
  @Getter
  @Column
  private String nombre;
  @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
  private PersonaDesignada encargado;

  public OrganismoDeControl(String nombre, PersonaDesignada encargado) {
    this.nombre = nombre;
    this.encargado = encargado;
  }

  public OrganismoDeControl() {

  }
}
