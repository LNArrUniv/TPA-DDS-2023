package ar.edu.utn.frba.dds.Modelos.Rankings;

import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import ar.edu.utn.frba.dds.Modelos.Comunidad;
import ar.edu.utn.frba.dds.Modelos.Entidad;
import ar.edu.utn.frba.dds.Modelos.Establecimiento;
import ar.edu.utn.frba.dds.Modelos.Incidente;
import ar.edu.utn.frba.dds.Modelos.Persona;
import ar.edu.utn.frba.dds.Modelos.Rol;
import ar.edu.utn.frba.dds.Modelos.Servicio;
import ar.edu.utn.frba.dds.Servicio.GeoRefAPIService;
import ar.edu.utn.frba.dds.Servicio.gradoDeImpacto.CalculadorGradoDeImpactoService;
import ar.edu.utn.frba.dds.Servicio.gradoDeImpacto.EntidadValor;
import ar.edu.utn.frba.dds.Servicio.gradoDeImpacto.ListadoDeResultados;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class CalculoRankingsTest {
  Incidente incidenteEscaleraMec;
  Incidente incidenteAscensor;
  Incidente incidenteBanio;
  Entidad bancoNacion;
  Entidad bancoProvincia;
  ArrayList<Entidad> entidades;
  TiempoDeCierre rankingPromedioTiempoDeCierre;
  MayorCantidadIncidentes rankingMayorCantidadIncidentes;
  GradoImpacto rankingGradoDeImpacto;

  @BeforeEach
  public void init() throws IOException {
    bancoNacion = new Entidad("Banco Nacion", "", null, null);
    bancoNacion.setId(0);
    bancoProvincia = new Entidad("Bancio Provincia", "", null, null);
    bancoProvincia.setId(1);
    Establecimiento sucursalAlmagroNac = new Establecimiento("Sucursal Almagro del Banco Nacion", "", null, "Cochabamba 1234", bancoNacion);
    Establecimiento sucursalAlmagroProv = new Establecimiento("Sucursal Almagro del Banco Provincia", "", null, "Av. Rivadavia 2943", bancoProvincia);

    Servicio servicioBNacion = new Servicio("Escalera Mecanica", "", sucursalAlmagroNac);
    Servicio otroServicioBNacion = new Servicio("Ascensor", "", sucursalAlmagroNac);

    Servicio servicioBProvincia = new Servicio("Baño", "", sucursalAlmagroProv);


    Comunidad comunidadAlmagro = new Comunidad("Comunidad de Servicios de Almagro (Bancos)");

    incidenteEscaleraMec = new Incidente("No anda la Escalera Mecanica", null, servicioBNacion, comunidadAlmagro);
    incidenteBanio = new Incidente("El baño esta fuera de servicio", null, servicioBProvincia, comunidadAlmagro);
    bancoNacion.agregarIncidente(incidenteEscaleraMec);
    bancoProvincia.agregarIncidente(incidenteBanio);
    incidenteAscensor = new Incidente("El ascensor no funciona", null, otroServicioBNacion, comunidadAlmagro);
    bancoNacion.agregarIncidente(incidenteAscensor);

    entidades = new ArrayList<>();
    entidades.add(bancoNacion);
    entidades.add(bancoProvincia);

    rankingPromedioTiempoDeCierre = new TiempoDeCierre();
    rankingMayorCantidadIncidentes = new MayorCantidadIncidentes();
    rankingGradoDeImpacto = new GradoImpacto();

    Persona miembro1 = new Persona("", "", "", "", null);
    Persona miembro2 = new Persona("", "", "", "", null);
    Persona miembro3 = new Persona("", "", "", "", null);

    miembro1.darseAltaComunidad(comunidadAlmagro, Rol.AFECTADO);
    miembro2.darseAltaComunidad(comunidadAlmagro, Rol.OBSERVADOR);
    miembro3.darseAltaComunidad(comunidadAlmagro, Rol.AFECTADO);
  }

  @Test
  public void ElBancoNacionTieneMejorPromedioDeCierreQueElBancoProvincia() {
    incidenteEscaleraMec.setFechaHoraApertura(LocalDateTime.now().minusHours(1));
    incidenteEscaleraMec.marcarComoResuelto();
    incidenteAscensor.setFechaHoraApertura(LocalDateTime.now().minusHours(1));
    incidenteAscensor.marcarComoResuelto();
    incidenteBanio.setFechaHoraApertura(LocalDateTime.now().minusHours(3));
    incidenteBanio.marcarComoResuelto();

    rankingPromedioTiempoDeCierre.generarRanking(entidades);
    ArrayList<ItemRanking> ranking = rankingPromedioTiempoDeCierre.getRankingTiempoPromedio();
    ranking.sort(Comparator.comparing(ItemRanking::getValorParametro));

    System.out.println("Ranking Mejor promedio de cierre");
    System.out.println(ranking.get(0).getEntidad().getNombre() + " - " + ranking.get(0).getValorParametro());
    System.out.println(ranking.get(1).getEntidad().getNombre() + " - " + ranking.get(1).getValorParametro());
    Assertions.assertEquals(ranking.get(0).getEntidad(), bancoNacion);
    Assertions.assertEquals(ranking.get(1).getEntidad(), bancoProvincia);
  }

  @Test
  public void ElBancoNacionTieneMasIncidentesQueElBancoProvincia(){
    incidenteEscaleraMec.setFechaHoraApertura(LocalDateTime.now().minusHours(1));
    incidenteEscaleraMec.marcarComoResuelto();
    incidenteAscensor.setFechaHoraApertura(LocalDateTime.now().minusHours(9));
    incidenteAscensor.marcarComoResuelto();
    incidenteBanio.setFechaHoraApertura(LocalDateTime.now().minusHours(3));
    incidenteBanio.marcarComoResuelto();

    rankingMayorCantidadIncidentes.generarRanking(entidades);
    ArrayList<ItemRanking> ranking = rankingMayorCantidadIncidentes.getRankingCantidadIncidentes();
    ranking.sort(Comparator.comparing(ItemRanking::getValorParametro));
    Collections.reverse(ranking); // Mas arriba en el ranking peor, o sea, mas incidentes

    System.out.println("Ranking Mayor cantidad de incidentes");
    System.out.println(ranking.get(0).getEntidad().getNombre() + " - " + ranking.get(0).getValorParametro());
    System.out.println(ranking.get(1).getEntidad().getNombre() + " - " + ranking.get(1).getValorParametro());
    Assertions.assertEquals(ranking.get(0).getEntidad(), bancoNacion);
    Assertions.assertEquals(ranking.get(1).getEntidad(), bancoProvincia);
  }

  @Test
  public void ElBancoNacionTieneUnMayorGradoDeImpactoQueElBancoProvincia() throws IOException {
    incidenteEscaleraMec.setFechaHoraApertura(LocalDateTime.now().minusHours(1));
    incidenteEscaleraMec.marcarComoResuelto();
    incidenteAscensor.setFechaHoraApertura(LocalDateTime.now().minusHours(4));
    incidenteBanio.setFechaHoraApertura(LocalDateTime.now().minusHours(1));
    incidenteBanio.marcarComoResuelto();
    //(tiempoResolucionIncidente + cantIncidentesNoResueltos * cnf) * totalPersonasImpactadas;
    //Banco nacion: (1 + 1 * 1) * 4 (2 miembros afectados por c/indente) = 8.0
    //Banco prov.: (1 + 0 * 1) * 2 = 2.0
    rankingGradoDeImpacto.generarRanking(entidades);
    ArrayList<ItemRanking> ranking = rankingGradoDeImpacto.getRankingGradoDeImpacto();

    System.out.println("Ranking grado de impacto");
    System.out.println(ranking.get(0).getEntidad().getNombre() + " - " + ranking.get(0).getValorParametro());
    System.out.println(ranking.get(1).getEntidad().getNombre() + " - " + ranking.get(1).getValorParametro());

    Assertions.assertEquals(ranking.get(0).getEntidad(), bancoNacion);
    Assertions.assertEquals(ranking.get(1).getEntidad(), bancoProvincia);
  }
}
