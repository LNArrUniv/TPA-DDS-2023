package ar.edu.utn.frba.dds.models;

import ar.edu.utn.frba.dds.models.ubicacion.Localidad;
import ar.edu.utn.frba.dds.persistencia.EntidadPersistente;
import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Getter;
import org.hibernate.annotations.Type;

@Entity
@Table(name = "establecimiento")
public class Establecimiento extends EntidadPersistente {
  @Column
  @Getter
  private String nombre;
  @Column
  @Type(type = "text")
  private String descripcion;
  @Getter
  @Embedded
  @AttributeOverride(name = "nombre", column = @Column(name = "localidad"))
  private Localidad ubicacion;
  @Column
  private String direccion;
  @JsonIgnore
  @Getter
  @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
  @JoinColumn(name = "entidad_id", referencedColumnName = "id")
  private Entidad entidad;

  public Establecimiento(String nombre, String descripcion,
                         Localidad ubicacion, String direccion, Entidad entidad) {
    this.nombre = nombre;
    this.descripcion = descripcion;
    this.ubicacion = ubicacion;
    this.direccion = direccion;
    this.entidad = entidad;
  }

  public Establecimiento() {

  }
}
