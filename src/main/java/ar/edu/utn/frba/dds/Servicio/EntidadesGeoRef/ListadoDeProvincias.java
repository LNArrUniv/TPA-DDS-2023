package ar.edu.utn.frba.dds.Servicio.EntidadesGeoRef;

import ar.edu.utn.frba.dds.Modelos.UbicacionDTO.Provincia;

import java.util.List;

public class ListadoDeProvincias extends PageInterface {
  public Parametro parametros;
  public List<Provincia> provincias;
  private class Parametro {
          public List<String> campos;
      }
}
