package ar.edu.utn.frba.dds.servicio.fusionarcomunidadesapi;

import static java.lang.Thread.sleep;

import ar.edu.utn.frba.dds.servicio.fusionarcomunidadesapi.entidades.ListaPropuestas;
import ar.edu.utn.frba.dds.servicio.fusionarcomunidadesapi.entidades.PropuestaDeFusionDto;
import ar.edu.utn.frba.dds.servicio.georefapi.GeoRefApiService;
import ar.edu.utn.frba.dds.servicio.georefapi.GeoRefService;
import ar.edu.utn.frba.dds.servicio.georefapi.entidades.ListadoDeLocalidades;
import ar.edu.utn.frba.dds.servicio.georefapi.entidades.ListadoDeProvincias;
import io.javalin.http.HttpStatus;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FusionComunidades {
  private static FusionComunidades instancia = null;
  private static final String URLAPI = "https://api-fusion-de-comunidades.onrender.com";
  private Retrofit retrofit;

  private FusionComunidades() {
    this.retrofit = new Retrofit.Builder()
        .baseUrl(URLAPI)
        .addConverterFactory(GsonConverterFactory.create())
        .build();
  }

  public static FusionComunidades getInstancia() {
    if (instancia == null) {
      instancia = new FusionComunidades();
    }
    return instancia;
  }

  public ArrayList<PropuestaDeFusionDto> obtenerPosiblesFusiones() throws IOException, InterruptedException {
    IFusionComunidades service = retrofit.create(IFusionComunidades.class);

    retrofit2.Response<List<PropuestaDeFusionDto>> response =
        service.obtenerPosiblesFusiones().execute();

    sleep(300);
    ArrayList<PropuestaDeFusionDto> listadoAmostrar = new ArrayList<>();

    if (response.code() == HttpStatus.OK.getCode()) {
      ListaPropuestas listado = new ListaPropuestas(response.body());

      listadoAmostrar.addAll(listado.getPropuestas());
    }

    return listadoAmostrar;
  }
}
