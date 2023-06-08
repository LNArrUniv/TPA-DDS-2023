package ar.edu.utn.frba.dds.GeoRefAPI;


import ar.edu.utn.frba.dds.Modelos.EntidadDTO.Provincia;
import ar.edu.utn.frba.dds.Servicio.EntidadesGeoRef.ListadoDeProvincias;
import ar.edu.utn.frba.dds.Servicio.GeoRefAPIService;
import org.junit.jupiter.api.Test;

import java.io.IOException;


public class GeoRefAPITest {
  @Test
  public static void main(String[] args) throws IOException {
    GeoRefAPIService geoRefService = GeoRefAPIService.getInstancia();
    System.out.println("Obteniendo las Provincias...");
    ListadoDeProvincias listadoDeProvincias = geoRefService.listadoDeProvincias();

    for (Provincia provincia : listadoDeProvincias.provincias){
      System.out.println(provincia.nombre);
    }
}

}

