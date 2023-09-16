package ar.edu.utn.frba.dds.Modelos;

import ar.edu.utn.frba.dds.Modelos.UbicacionDTO.Provincia;
import ar.edu.utn.frba.dds.Persistencia.EntidadPersistente;
import lombok.Getter;
import lombok.Setter;
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
import javax.persistence.Transient;
import java.util.ArrayList;

@Entity
@Table(name = "entidad")
public class Entidad extends EntidadPersistente {
  @Getter
  @Column
  private String nombre;
  @Column
  @JdbcType(LongNVarcharJdbcType.class)
  private String descripcion;
  @Setter
  @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
  @JoinColumn(name = "entidadPropietaria_id", referencedColumnName = "id")
  private EntidadPropietaria entidadPropietaria;
  @Embedded
  @AttributeOverride(name="nombre", column=@Column(name="provincia"))
  private Provincia ubicacion;
  @Getter
  @Transient
  private ArrayList<Incidente> incidentes;

  public Entidad(String nombre, String descripcion, EntidadPropietaria entidadPropietaria, Provincia ubicacion) {
    this.nombre = nombre;
    this.descripcion = descripcion;
    this.entidadPropietaria = entidadPropietaria;
    this.ubicacion = ubicacion;
    this.incidentes=new ArrayList<>();
  }

  public Entidad() {

  }

  public void agregarIncidente(Incidente incidente){
    incidentes.add(incidente);
  }
}
