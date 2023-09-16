package ar.edu.utn.frba.dds.Modelos;

import ar.edu.utn.frba.dds.Modelos.UbicacionDTO.Localidad;
import ar.edu.utn.frba.dds.Persistencia.EntidadPersistente;
import lombok.Getter;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.JdbcType;
import org.hibernate.type.descriptor.jdbc.LongNVarcharJdbcType;
import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "establecimiento")
public class Establecimiento extends EntidadPersistente {
  @Column
  private String nombre;
  @Column
  @JdbcType(LongNVarcharJdbcType.class)
  private String descripcion;
  @Getter
  @Embedded
  @AttributeOverride(name="nombre", column=@Column(name="localidad"))
  private Localidad ubicacion;
  @Column
  private String direccion;
  @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
  @JoinColumn(name = "entidad_id", referencedColumnName = "id")
  private Entidad entidad;

  public Establecimiento(String nombre, String descripcion, Localidad ubicacion, String direccion, Entidad entidad) {
    this.nombre = nombre;
    this.descripcion = descripcion;
    this.ubicacion = ubicacion;
    this.direccion = direccion;
    this.entidad = entidad;
  }

  public Establecimiento() {

  }
  /*
  public void agregarServicio(Servicio servicio) {
    servicios.add(servicio);
    servicio.setEstablecimiento(this);
  }
  */
}
