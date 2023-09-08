package ar.edu.utn.frba.dds.Modelos;

import ar.edu.utn.frba.dds.Persistencia.EntidadPersistente;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.time.LocalDateTime;
//TODO: REVISAR EL DER
@Entity
@Table
public class Incidente  extends EntidadPersistente {
  @Getter
  @Column
  @Type(type="text")
  private String descripcion;
  @Setter // Para los tests

  private LocalDateTime fechaHoraApertura;
  @Getter
  @Transient
  private Persona informante;
  @Column
  private Boolean resuelto;
  private LocalDateTime fechaHoraCierre;
  @Getter
  @Transient
  private Servicio servicio;
  @Getter
  @Transient
  private Comunidad comunidad;

  public Incidente(String descripcion, Persona informante, Servicio servicio, Comunidad comunidad) {
    this.descripcion = descripcion;
    this.fechaHoraApertura = LocalDateTime.now();
    this.informante = informante;
    this.servicio = servicio;
    this.comunidad = comunidad;
  }

  public Incidente() {

  }

  public void marcarComoResuelto() {
    this.fechaHoraCierre = LocalDateTime.now();
    this.resuelto = true;
  }

  public LocalDateTime getFechaHoraApertura() {
    return fechaHoraApertura;
  }

  public LocalDateTime getFechaHoraCierre() {
    return fechaHoraCierre;
  }
}
