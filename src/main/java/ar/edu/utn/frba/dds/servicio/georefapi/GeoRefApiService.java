package ar.edu.utn.frba.dds.servicio.georefapi;

import ar.edu.utn.frba.dds.servicio.georefapi.entidades.ListadoDeLocalidades;
import ar.edu.utn.frba.dds.servicio.georefapi.entidades.ListadoDeProvincias;
import java.io.IOException;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GeoRefApiService {
  private static GeoRefApiService instancia = null;
  private static final String URLAPI = "https://apis.datos.gob.ar/georef/api/";
  private Retrofit retrofit;

  private GeoRefApiService() {
    this.retrofit = new Retrofit.Builder()
        .baseUrl(URLAPI)
        .addConverterFactory(GsonConverterFactory.create())
        .build();
  }

  public static GeoRefApiService getInstancia() {
    if (instancia == null) {
      instancia = new GeoRefApiService();
    }
    return instancia;
  }

  public ListadoDeProvincias listadoDeProvincias() throws IOException {
    GeoRefService geoRefService = this.retrofit.create(GeoRefService.class);
    Call<ListadoDeProvincias> requestProvinciasArgentinas = geoRefService.provincias();
    Response<ListadoDeProvincias> responseProvinciasArgentinas =
        requestProvinciasArgentinas.execute();
    return responseProvinciasArgentinas.body();
  }

  public ListadoDeLocalidades listadoDeLocalidades() throws IOException {
    GeoRefService geoRefService = this.retrofit.create(GeoRefService.class);
    Call<ListadoDeLocalidades> requestLocalidadesArgentinas = geoRefService.localidades();
    Response<ListadoDeLocalidades> responseLocalidadesArgentinas =
        requestLocalidadesArgentinas.execute();
    return responseLocalidadesArgentinas.body();
  }

  public ListadoDeLocalidades localidadPorNombreYprov(String nombre, long idProvincia)
      throws IOException {
    GeoRefService geoRefService = this.retrofit.create(GeoRefService.class);
    Call<ListadoDeLocalidades> requestLocalidadesArgentinas =
        geoRefService.localidades(nombre, idProvincia);
    Response<ListadoDeLocalidades> responseLocalidadesArgentinas =
        requestLocalidadesArgentinas.execute();
    return responseLocalidadesArgentinas.body();
  }
}