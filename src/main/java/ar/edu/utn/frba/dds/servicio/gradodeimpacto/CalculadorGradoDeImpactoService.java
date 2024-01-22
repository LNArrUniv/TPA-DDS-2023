package ar.edu.utn.frba.dds.servicio.gradodeimpacto;

import java.io.IOException;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CalculadorGradoDeImpactoService {
  private static CalculadorGradoDeImpactoService instancia = null;
  private static final String URLAPI = "https://prueba-deploy-0nxf.onrender.com";
  private Retrofit retrofit;

  private CalculadorGradoDeImpactoService() {
    this.retrofit = new Retrofit.Builder()
        .baseUrl(URLAPI)
        .addConverterFactory(GsonConverterFactory.create())
        .build();
  }

  public static CalculadorGradoDeImpactoService getInstancia() {
    if (instancia == null) {
      instancia = new CalculadorGradoDeImpactoService();
    }
    return instancia;
  }

  public void calcularGradoDeImpacto(ListadoDeValores listadoDeValores) {
    CalculadorGradoDeImpactoApiService calculadorGradoDeImpacto =
        this.retrofit.create(CalculadorGradoDeImpactoApiService.class);
    Call<ListadoDeValores> requestCalcular =
        calculadorGradoDeImpacto.calcularGradoDeImpacto(listadoDeValores);
    requestCalcular.enqueue(new Callback<>() {
      @Override
      public void onResponse(Call<ListadoDeValores> call, Response<ListadoDeValores> response) {
      }

      @Override
      public void onFailure(Call<ListadoDeValores> call, Throwable t) {
      }
    });
  }

  public ListadoDeResultados obtenerResultados() throws IOException {
    CalculadorGradoDeImpactoApiService calculadorGradoDeImpacto =
        this.retrofit.create(CalculadorGradoDeImpactoApiService.class);
    Call<List<EntidadValor>> requestResultados = calculadorGradoDeImpacto.obtenerResultados();
    Response<List<EntidadValor>> responseResultados = requestResultados.execute();
    ListadoDeResultados listado = new ListadoDeResultados(responseResultados.body());
    return listado;
  }
}
