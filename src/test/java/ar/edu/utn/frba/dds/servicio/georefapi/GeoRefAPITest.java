package ar.edu.utn.frba.dds.servicio.georefapi;


import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import ar.edu.utn.frba.dds.models.ubicaciondto.Provincia;
import ar.edu.utn.frba.dds.servicio.entidadesgeoref.ListadoDeProvincias;
import ar.edu.utn.frba.dds.servicio.GeoRefApiService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;
import java.util.Optional;


public class GeoRefAPITest {

  static GeoRefApiService geoRefAPIService = null;

  @BeforeAll
  public static void init() throws IOException {
    geoRefAPIService = mock(GeoRefApiService.class);

    Provincia buenossAiressProv = new Provincia();
    buenossAiressProv.id = 6;
    buenossAiressProv.nombre = "Buenos Aires";
    Provincia cordoba = new Provincia();
    cordoba.id = 14;
    cordoba.nombre = "Córdoba";
    Provincia misiones = new Provincia();
    misiones.id = 54;
    misiones.nombre = "Misiones";

    ListadoDeProvincias listado = new ListadoDeProvincias();
    listado.provincias = List.of(buenossAiressProv, cordoba, misiones);

    when(geoRefAPIService.listadoDeProvincias()).thenReturn(listado);
  }
  @Test
  public void APIDevuelveUnListadoDeProvincias() throws IOException {
    ListadoDeProvincias listado = geoRefAPIService.listadoDeProvincias();

    Assertions.assertEquals(3, listado.provincias.size());
  }

  @Test
  public void APIDevuelveBuenosAiresAlPedirselaPorId() throws IOException {
    ListadoDeProvincias listado = geoRefAPIService.listadoDeProvincias();
    Optional<Provincia> provincia = listado.provinciaDeId(06);
    String nombreProv = provincia.map(p -> p.nombre).get();

    Assertions.assertEquals("Buenos Aires", nombreProv);
  }
}

