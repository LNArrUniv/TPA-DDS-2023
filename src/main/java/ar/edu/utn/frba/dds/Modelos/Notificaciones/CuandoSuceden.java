package ar.edu.utn.frba.dds.Modelos.Notificaciones;

import ar.edu.utn.frba.dds.Persistencia.converters.MedioDeContactoPreferidoAttributeConverter;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "Cuando suceden")
public class CuandoSuceden extends ConfiguracionNotificaciones{
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
