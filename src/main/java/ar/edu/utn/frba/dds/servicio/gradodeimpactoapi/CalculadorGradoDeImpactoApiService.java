package ar.edu.utn.frba.dds.servicio.gradodeimpactoapi;

import java.util.List;
import ar.edu.utn.frba.dds.servicio.gradodeimpactoapi.entidades.EntidadValor;
import ar.edu.utn.frba.dds.servicio.gradodeimpactoapi.entidades.ListadoDeValores;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface CalculadorGradoDeImpactoApiService {
  @GET("/api/calcularImpacto")
  Call<List<EntidadValor>> obtenerResultados();

  @POST("/api/calcularImpacto")
  Call<ListadoDeValores> calcularGradoDeImpacto(@Body ListadoDeValores listadoDeValores);

}
