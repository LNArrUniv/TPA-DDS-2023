package ar.edu.utn.frba.dds.Modelos.Rankings;

import ar.edu.utn.frba.dds.Modelos.Entidad;

import java.io.IOException;
import java.util.ArrayList;

public interface MetodosRanking {

  public void generarRanking(ArrayList<Entidad> entidades) throws IOException;
}
