package ar.edu.utn.frba.dds.servicio.fusionarcomunidadesapi.entidades;

public class ServicioParticularObservadoDto {
  private int id;
  private ServicioDto servicio;
  private EstablecimientoDto establecimiento;

  public ServicioParticularObservadoDto() {
  }

  public ServicioParticularObservadoDto(ServicioDto servicio, EstablecimientoDto establecimiento) {
    this.id = servicio.getId();
    this.servicio = servicio;
    this.establecimiento = establecimiento;
  }

  public ServicioDto getServicio() {
    return servicio;
  }

  public EstablecimientoDto getEstablecimiento() {
    return establecimiento;
  }
}
