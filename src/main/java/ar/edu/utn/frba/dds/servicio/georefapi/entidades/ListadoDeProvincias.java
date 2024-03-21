package ar.edu.utn.frba.dds.servicio.georefapi.entidades;

import ar.edu.utn.frba.dds.models.ubicacion.Provincia;
import java.util.List;
import java.util.Optional;

public class ListadoDeProvincias extends PageInterface {
  public Parametro parametros;
  public List<Provincia> provincias;

  private class Parametro {
    public List<String> campos;
  }

  public Optional<Provincia> provinciaDeId(int id) {
    return this.provincias.stream()
        .filter(p -> p.id == id)
        .findFirst();
  }
}
