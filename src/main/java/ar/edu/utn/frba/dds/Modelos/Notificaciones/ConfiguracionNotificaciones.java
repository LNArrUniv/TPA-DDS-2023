package ar.edu.utn.frba.dds.Modelos.Notificaciones;

import ar.edu.utn.frba.dds.Persistencia.EntidadPersistente;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity
@Table(name = "configuracion_notificaciones")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipoNotificaciones", discriminatorType = DiscriminatorType.STRING)
public abstract class ConfiguracionNotificaciones extends EntidadPersistente {
  public void notificarMiembro(Notificacion notificacion) throws Exception {

  }
}
