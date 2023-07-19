package ar.edu.utn.frba.dds.Modelos.Notificaciones;

public class CuandoSuceden implements ConfiguracionNotificaciones{
  private MedioDeContacto medioDeContacto;

  public CuandoSuceden(MedioDeContacto medioDeContacto) {
    this.medioDeContacto = medioDeContacto;
  }

  @Override
  public void notificarMiembro(Notificacion notificacion) throws Exception {
    medioDeContacto.notificar(notificacion);
  }
}
