package ar.edu.utn.frba.dds.Modelos;

import ar.edu.utn.frba.dds.Modelos.UbicacionDTO.Localidad;
import ar.edu.utn.frba.dds.Persistencia.EntidadPersistente;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;
import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;
@Entity
@Table(name = "incidente")
public class Incidente  extends EntidadPersistente {
  @Getter
  @Column
  @Type(type="text")
  private String descripcion;
  @Setter // Para los tests
  @Column
  private LocalDateTime fechaHoraApertura;
  @Getter
  @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
  @JoinColumn(name = "informante_id", referencedColumnName = "id")
  private Persona informante;
  @Getter
  @Column
  private Boolean estaResuelto;
  @Column
  private LocalDateTime fechaHoraCierre;
  @Getter
  @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
  @JoinColumn(name = "servicio_id", referencedColumnName = "id")
  private Servicio servicio;
  @Getter
  @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
  @JoinColumn(name = "comunidad_id", referencedColumnName = "id")
  private Comunidad comunidad;
  @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
  private Entidad entidad;
  @Embedded
  @AttributeOverride(name="nombre", column=@Column(name="localidad"))
  private Localidad localidad;

  public Incidente(String descripcion, Persona informante, Servicio servicio, Comunidad comunidad) {
    this.descripcion = descripcion;
    this.fechaHoraApertura = LocalDateTime.now();
    this.informante = informante;
    this.servicio = servicio;
    this.comunidad = comunidad;
    this.estaResuelto = false;
    this.localidad = servicio.getEstablecimiento().getUbicacion();
    this.entidad = servicio.getEstablecimiento().getEntidad();
  }

  public Incidente() {

  }

  public void marcarComoResuelto() {
    this.fechaHoraCierre = LocalDateTime.now();
    this.estaResuelto = true;
  }

  public LocalDateTime getFechaHoraApertura() {
    return fechaHoraApertura;
  }

  public LocalDateTime getFechaHoraCierre() {
    return fechaHoraCierre;
  }
}
