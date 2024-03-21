package ar.edu.utn.frba.dds.seguridad;

import ar.edu.utn.frba.dds.seguridad.filtros.FiltroInterface;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ValidadorPassword {

  public ValidadorPassword() {
  }

  private List<FiltroInterface> filtros = new ArrayList<>();

  public Boolean validarPassword(String password) {
    return this.filtros.stream().allMatch(filtro -> {
      try {
        return filtro.validar(password);
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    });
  }

  public void addFiltro(FiltroInterface filtro) {
    filtros.add(filtro);
  }
}
