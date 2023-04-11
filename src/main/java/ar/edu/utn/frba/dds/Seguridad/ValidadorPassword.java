package ar.edu.utn.frba.dds.Seguridad;

import ar.edu.utn.frba.dds.Seguridad.Filtros.FiltroInterface;

import java.util.ArrayList;
import java.util.List;

public class ValidadorPassword {

  private List<FiltroInterface> filtros = new ArrayList<>();

  public Boolean ValidadorPassword(String password) {
   return this.filtros.stream().allMatch( filtro -> filtro.validar(password) );
  }

}
