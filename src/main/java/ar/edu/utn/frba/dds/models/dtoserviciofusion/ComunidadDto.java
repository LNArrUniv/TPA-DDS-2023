package ar.edu.utn.frba.dds.models.dtoserviciofusion;

import ar.edu.utn.frba.dds.models.Servicio;
import java.util.List;
import java.util.stream.Collectors;

public class ComunidadDto {
  private int id;
  private String nombre;
  private List<ServicioParticularObservadoDto> serviciosParticularesObservados;
  private Double gradoDeConfianza;

  public ComunidadDto() {
  }

  public ComunidadDto(int id, String nombre,
                      List<Servicio> serviciosDeInteres, Double gradoDeConfianza) {
    this.id = id;
    this.nombre = nombre;
    this.serviciosParticularesObservados = serviciosDeInteres.stream()
        .map(servicio ->
            new ServicioParticularObservadoDto(
                new ServicioDto((int) servicio.getId(), servicio.getNombre()),
                new EstablecimientoDto((int) servicio.getEstablecimiento().getId(),
                    servicio.getEstablecimiento().getNombre()))
        ).collect(Collectors.toList());
    this.gradoDeConfianza = gradoDeConfianza;
  }

  public ComunidadDto(ComunidadDto body) {
    this.id = body.id;
    this.nombre = body.nombre;
    this.serviciosParticularesObservados = body.serviciosParticularesObservados;
    this.gradoDeConfianza = body.gradoDeConfianza;
  }

  public int getId() {
    return id;
  }

  public String getNombre() {
    return nombre;
  }

  public List<ServicioParticularObservadoDto> getServiciosParticularesObservados() {
    return serviciosParticularesObservados;
  }

  public Double getGradoDeConfianza() {
    return gradoDeConfianza;
  }
}
