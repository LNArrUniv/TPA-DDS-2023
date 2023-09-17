package ar.edu.utn.frba.dds.Servicio.gradoDeImpacto;

import ar.edu.utn.frba.dds.Servicio.EntidadesGeoRef.PageInterface;
import java.util.List;
import java.util.Optional;

public class ListadoDeResultados extends PageInterface {
  public List<EntidadValor> entidadValor;

  public Optional<EntidadValor> valorDeEntidad(long id){
    return this.entidadValor.stream()
        .filter(l -> l.entidad_id == id)
        .findFirst();
  }

  public ListadoDeResultados(List<EntidadValor> entidadValor) {
    this.entidadValor = entidadValor;
  }
}
