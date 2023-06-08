package ar.edu.utn.frba.dds.Servicio;

import ar.edu.utn.frba.dds.Servicio.EntidadesGeoRef.ListadoDeProvincias;
import retrofit2.Call;
import retrofit2.http.GET;

public interface GeoRefService {
  @GET("provincias")
  Call<ListadoDeProvincias> provincias();
}
