package ar.edu.utn.frba.dds.Server.Utils;

import ar.edu.utn.frba.dds.Modelos.DTOServicio1.PropuestaDeFusionDTO;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Servicio1 {
  @GET("/posiblesfusiones")
  Call<String> obtenerPosiblesFusiones();

  @POST("/fusionarcomunidades")
  Call<String> fusionarComunidades(@Body PropuestaDeFusionDTO comunidad);
}
