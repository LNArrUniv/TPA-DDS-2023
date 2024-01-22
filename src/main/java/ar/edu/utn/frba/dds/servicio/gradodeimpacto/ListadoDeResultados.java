package ar.edu.utn.frba.dds.servicio.gradodeimpacto;

import ar.edu.utn.frba.dds.servicio.entidadesgeoref.PageInterface;
import java.util.List;
import lombok.Getter;

public class ListadoDeResultados extends PageInterface {
  @Getter
  public List<EntidadValor> entidadValor;

  public EntidadValor valorDeEntidad(long id) {
    return this.entidadValor.stream()
        .filter(l -> l.getEntidadId() == id)
        .findFirst().get();
  }

  public ListadoDeResultados(List<EntidadValor> entidadValor) {
    this.entidadValor = entidadValor;
  }
}
