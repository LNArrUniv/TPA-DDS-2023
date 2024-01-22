package ar.edu.utn.frba.dds.servicio.gradodeimpacto;

import java.util.List;
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
