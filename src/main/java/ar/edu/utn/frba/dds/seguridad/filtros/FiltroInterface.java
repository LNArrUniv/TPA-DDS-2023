package ar.edu.utn.frba.dds.seguridad.filtros;

import java.io.IOException;

public interface FiltroInterface {

  Boolean validar(String password) throws IOException;
}
