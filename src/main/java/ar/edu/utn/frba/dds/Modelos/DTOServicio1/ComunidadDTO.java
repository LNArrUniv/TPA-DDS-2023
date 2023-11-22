package ar.edu.utn.frba.dds.Modelos.DTOServicio1;

import ar.edu.utn.frba.dds.Modelos.Servicio;

import java.util.List;
import java.util.stream.Collectors;

public class ComunidadDTO {
  private int id;
  private List<ServicioParticularObservadoDTO> serviciosParticularesObservados;
  private Double gradoDeConfianza;

  public ComunidadDTO() {}

  public ComunidadDTO(int id, List<Servicio> serviciosDeInteres, Double gradoDeConfianza) {
    this.id = id;
    this.serviciosParticularesObservados = serviciosDeInteres.stream()
        .map(servicio ->
            new ServicioParticularObservadoDTO(
                new ServicioDTO((int) servicio.getId(), servicio.getNombre()),
                new EstablecimientoDTO((int) servicio.getEstablecimiento().getId(), servicio.getEstablecimiento().getNombre()))
        ).collect(Collectors.toList());
    this.gradoDeConfianza = gradoDeConfianza;
  }


  public int getId() {
    return id;
  }

  public List<ServicioParticularObservadoDTO> getServiciosParticularesObservados() {
    return serviciosParticularesObservados;
  }

  public Double getGradoDeConfianza() {
    return gradoDeConfianza;
  }
}
