package ar.edu.utn.frba.dds.server.utils;

import ar.edu.utn.frba.dds.models.dtoserviciofusion.ComunidadDto;
import ar.edu.utn.frba.dds.models.dtoserviciofusion.PropuestaDeFusionDto;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Servicio1 {
  @GET("/posiblesfusiones")
  Call<List<PropuestaDeFusionDto>> obtenerPosiblesFusiones();

  @POST("/fusionarcomunidades")
  Call<ComunidadDto> fusionarComunidades(@Body PropuestaDeFusionDto comunidad);
}
