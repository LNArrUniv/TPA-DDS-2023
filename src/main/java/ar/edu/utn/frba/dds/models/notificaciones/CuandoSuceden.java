package ar.edu.utn.frba.dds.models.notificaciones;

import ar.edu.utn.frba.dds.persistencia.converters.MedioDeContactoPreferidoAttributeConverter;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import lombok.Getter;

@Entity
@DiscriminatorValue(value = "Cuando suceden")
public class CuandoSuceden extends ConfiguracionNotificaciones {
  @Getter
  @Convert(converter = MedioDeContactoPreferidoAttributeConverter.class)
  @Column(name = "contacto")
  private MedioDeNotificacionesPreferido medio;

  public CuandoSuceden(MedioDeNotificacionesPreferido medio) {
    this.medio = medio;
  }

  public CuandoSuceden() {

  }

  @Override
  public void notificarMiembro(Notificacion notificacion) throws Exception {
    medio.notificar(notificacion);
  }
}
