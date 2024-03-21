package ar.edu.utn.frba.dds.servicio.georefapi.entidades;

import ar.edu.utn.frba.dds.models.ubicacion.Municipalidad;
import java.util.List;

public class ListadoDeMunicipios extends PageInterface {
  public List<Municipalidad> municipalidades;
  public Parametro parametros;

  private class Parametro {
    public List<String> campos;
    public int max;
    public List<String> provincia;
  }
}
