package ar.edu.utn.frba.dds.Modelos.Notificaciones;

import ar.edu.utn.frba.dds.Modelos.Incidente;
import ar.edu.utn.frba.dds.Modelos.Usuarios.Persona;
import ar.edu.utn.frba.dds.Persistencia.EntidadPersistente;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "notificaciones")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Notificacion extends EntidadPersistente {
  @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
  protected Incidente incidente;
  @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
  protected Persona usuarioANotificar;

  public Notificacion(Incidente incidente, Persona usuarioANotificar) {
    this.incidente = incidente;
    this.usuarioANotificar = usuarioANotificar;
  }

  public Notificacion() {

  }

  public abstract String getMensajeDeNotificacion();

  public abstract String getEncabezado();
}
