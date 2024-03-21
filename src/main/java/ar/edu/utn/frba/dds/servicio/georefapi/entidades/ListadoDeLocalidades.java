package ar.edu.utn.frba.dds.servicio.georefapi.entidades;

import ar.edu.utn.frba.dds.models.ubicacion.Localidad;
import java.util.List;
import java.util.Optional;

public class ListadoDeLocalidades extends PageInterface {
  public List<Localidad> localidades;
  public Parametro parametros;

  private class Parametro {
    public List<String> campos;
  }

  public Optional<Localidad> localidadDeId(long id) {
    return this.localidades.stream()
        .filter(l -> l.id == id)
        .findFirst();
  }
}
