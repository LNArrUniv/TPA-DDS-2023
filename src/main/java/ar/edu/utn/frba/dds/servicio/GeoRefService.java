package ar.edu.utn.frba.dds.servicio;

import ar.edu.utn.frba.dds.servicio.entidadesgeoref.ListadoDeLocalidades;
import ar.edu.utn.frba.dds.servicio.entidadesgeoref.ListadoDeProvincias;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GeoRefService {
  @GET("provincias")
  Call<ListadoDeProvincias> provincias();

  @GET("localidades")
  Call<ListadoDeLocalidades> localidades();

  @GET("localidades")
  Call<ListadoDeLocalidades> localidades(@Query("nombre") String nombre,
                                         @Query("provincia") long id);
}
