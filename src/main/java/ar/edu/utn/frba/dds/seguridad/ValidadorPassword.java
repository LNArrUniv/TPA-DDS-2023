package ar.edu.utn.frba.dds.seguridad;

import ar.edu.utn.frba.dds.seguridad.filtros.FiltroInterface;
import java.util.ArrayList;
import java.util.List;

public class ValidadorPassword {

  private List<FiltroInterface> filtros = new ArrayList<>();

  public Boolean validarPassword(String password) {
    return this.filtros.stream().allMatch(filtro -> filtro.validar(password));
  }

  public void addFiltro(FiltroInterface filtro) {
    filtros.add(filtro);
  }
}
