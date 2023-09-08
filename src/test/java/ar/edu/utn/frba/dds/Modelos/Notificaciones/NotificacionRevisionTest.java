package ar.edu.utn.frba.dds.Modelos.Notificaciones;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import ar.edu.utn.frba.dds.Modelos.Establecimiento;
import ar.edu.utn.frba.dds.Modelos.Incidente;
import ar.edu.utn.frba.dds.Modelos.Persona;
import ar.edu.utn.frba.dds.Modelos.RepositorioIncidentes;
import ar.edu.utn.frba.dds.Modelos.Servicio;
import ar.edu.utn.frba.dds.Modelos.UbicacionDTO.Localidad;
import ar.edu.utn.frba.dds.Servicio.EntidadesGeoRef.ListadoDeLocalidades;
import ar.edu.utn.frba.dds.Servicio.GeoRefAPIService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

public class NotificacionRevisionTest {
  Persona carlos;
  MedioNotificacionesEmail emailDeContacto;
  GeoRefAPIService geoRefAPIService;

  @BeforeEach
  public void init() throws Exception {
    geoRefAPIService = mock(GeoRefAPIService.class);

    Localidad liniers = new Localidad();
    liniers.id = 02063010001;
    liniers.nombre = "LINIERS";
    ListadoDeLocalidades listado = new ListadoDeLocalidades();
    listado.localidades = List.of(liniers);
    doReturn(listado).when(geoRefAPIService).listadoDeLocalidades();

    Establecimiento sucursalBancoRIO = new Establecimiento("Banco RIO", "", geoRefAPIService.listadoDeLocalidades().localidadDeId(02063010001).get(), "Av. Rivadavia 123", null);
    Servicio servicioRandom = new Servicio("Ascensor de la sucursal Liniers del Banco RIO", "null", sucursalBancoRIO);
    Incidente incidenteCercano = new Incidente("Ascensor fuera de servicio", null, servicioRandom, null);
    ArrayList<Incidente> activos = new ArrayList<>();
    activos.add(incidenteCercano);
    RepositorioIncidentes.getInstance().setActivos(activos);

    emailDeContacto = mock(String.valueOf(MedioNotificacionesEmail.class));

    carlos = new Persona("Carlos", "Rodriguez", "CarlosR", null, new CuandoSuceden(emailDeContacto));

    doAnswer(invocationOnMock -> {
      Object[] args = invocationOnMock.getArguments();
      Notificacion notificacion = (Notificacion) args[0];
      System.out.println(notificacion.getMensajeDeNotificacion());
      return null;
    }).when(emailDeContacto).notificar(any(Notificacion.class));
  }

  @Test
  public void AlTenerLaMismaUbicacionQueUnIncidenteSeLePreguntaACarlosSiPuedeRevisarlo() throws Exception {
    carlos.setUbicacion(geoRefAPIService.listadoDeLocalidades().localidadDeId(02063010001).get());

    verify(emailDeContacto, times(1)).notificar(any(Notificacion.class));
  }
}