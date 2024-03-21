package ar.edu.utn.frba.dds.servicio.fusionarcomunidadesapi;

import ar.edu.utn.frba.dds.servicio.fusionarcomunidadesapi.entidades.ComunidadDto;
import ar.edu.utn.frba.dds.servicio.fusionarcomunidadesapi.entidades.PropuestaDeFusionDto;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface IFusionComunidades {
  @GET("/posiblesfusiones")
  Call<List<PropuestaDeFusionDto>> obtenerPosiblesFusiones();

  @POST("/fusionarcomunidades")
  Call<ComunidadDto> fusionarComunidades(@Body PropuestaDeFusionDto comunidad);
}
