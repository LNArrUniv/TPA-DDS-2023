package ar.edu.utn.frba.dds.models.notificaciones;

import ar.edu.utn.frba.dds.models.Incidente;
import ar.edu.utn.frba.dds.models.usuarios.Persona;
import ar.edu.utn.frba.dds.persistencia.EntidadPersistente;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Getter;

@Entity
@Table(name = "notificaciones")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Notificacion extends EntidadPersistente {
  @Getter
  @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
  protected Incidente incidente;
  @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
  protected Persona usuarioAnotificar;
  @Getter
  @Column
  protected LocalDateTime fecha;

  public Notificacion(Incidente incidente, Persona usuarioAnotificar) {
    this.incidente = incidente;
    this.usuarioAnotificar = usuarioAnotificar;
    this.fecha = LocalDateTime.now();
  }

  public Notificacion() {

  }

  public abstract String getMensajeDeNotificacion();

  public abstract String getEncabezado();

  public String getFechaString() {
    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm, dd-MM-yyy");

    return fecha.format(dateTimeFormatter);
  }
}
