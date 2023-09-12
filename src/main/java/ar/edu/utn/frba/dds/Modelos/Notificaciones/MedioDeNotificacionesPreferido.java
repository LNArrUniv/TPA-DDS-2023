package ar.edu.utn.frba.dds.Modelos.Notificaciones;

import lombok.Getter;

public abstract class MedioDeNotificacionesPreferido {
  @Getter
  private String email;
  @Getter
  private String telefono;

  public MedioDeNotificacionesPreferido(String email, String telefono) {
    this.email = email;
    this.telefono = telefono;
  }

  void notificar(Notificacion notificacion) throws Exception {

  }
}
