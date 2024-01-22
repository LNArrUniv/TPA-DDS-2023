package ar.edu.utn.frba.dds.models.notificaciones;

import ar.edu.utn.frba.dds.persistencia.EntidadPersistente;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
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

  public String getDiscriminatorValue() {
    DiscriminatorValue val = this.getClass().getAnnotation(DiscriminatorValue.class);

    return val == null ? null : val.value();
  }
}
