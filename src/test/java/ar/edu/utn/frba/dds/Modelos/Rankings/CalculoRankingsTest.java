package ar.edu.utn.frba.dds.Modelos.Rankings;

import ar.edu.utn.frba.dds.Modelos.Comunidad;
import ar.edu.utn.frba.dds.Modelos.Entidad;
import ar.edu.utn.frba.dds.Modelos.Establecimiento;
import ar.edu.utn.frba.dds.Modelos.Incidente;
import ar.edu.utn.frba.dds.Modelos.Servicio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class CalculoRankingsTest {
  Incidente incidenteEscaleraMec;
  Incidente incidenteAscensor;
  Incidente incidenteBaño;
  Entidad bancoNacion;
  Entidad bancoProvincia;
  ArrayList<Entidad> entidades;
  TiempoDeCierre rankingPromedioTiempoDeCierre;
  MayorCantidadIncidentes rankingMayorCantidadIncidentes;

  @BeforeEach
  public void init() {
    bancoNacion = new Entidad("Banco Nacion", "", null);
    bancoProvincia = new Entidad("Bancio Provincia", "", null);
    Establecimiento sucursalAlmagroNac = new Establecimiento("Sucursal Almagro del Banco Nacion", "", null);
    Establecimiento sucursalAlmagroProv = new Establecimiento("Sucursal Almagro del Banco Provincia", "", null);
    bancoNacion.agregarEstablecimiento(sucursalAlmagroNac);
    bancoProvincia.agregarEstablecimiento(sucursalAlmagroProv);

    Servicio servicioBNacion = new Servicio("Escalera Mecanica", "", null);
    Servicio otroServicioBNacion = new Servicio("Escalera Mecanica", "", null);

    Servicio servicioBProvincia = new Servicio("Baño", "", null);
    sucursalAlmagroNac.agregarServicio(servicioBNacion);
    sucursalAlmagroNac.agregarServicio(otroServicioBNacion);
    sucursalAlmagroProv.agregarServicio(servicioBProvincia);

    Comunidad comunidadAlmagro = new Comunidad("Comunidad de Servicios de Almagro (Bancos)");

    incidenteEscaleraMec = new Incidente("No anda la Escalera Mecanica", null, servicioBNacion, comunidadAlmagro);
    incidenteBaño = new Incidente("El baño esta fuera de servicio", null, servicioBProvincia, comunidadAlmagro);
    bancoNacion.agregarIncidente(incidenteEscaleraMec);
    bancoProvincia.agregarIncidente(incidenteBaño);
    incidenteAscensor = new Incidente("El ascensor no funciona", null, otroServicioBNacion, comunidadAlmagro);
    bancoNacion.agregarIncidente(incidenteAscensor);

    entidades = new ArrayList<>();
    entidades.add(bancoNacion);
    entidades.add(bancoProvincia);

    rankingPromedioTiempoDeCierre = new TiempoDeCierre();
    rankingMayorCantidadIncidentes = new MayorCantidadIncidentes();
  }

  @Test
  public void ElBancoNacionTieneMejorPromedioDeCierreQueElBancoProvincia(){ // O sea tarda menos en cerrarlos
    incidenteEscaleraMec.setFechaHoraApertura(LocalDateTime.now().minus(1, ChronoUnit.HOURS));
    incidenteEscaleraMec.marcarComoResuelto();
    incidenteBaño.setFechaHoraApertura(LocalDateTime.now().minus(3, ChronoUnit.HOURS));
    incidenteBaño.marcarComoResuelto();

    rankingPromedioTiempoDeCierre.generarRanking(entidades);
    ArrayList<ItemRanking> ranking = rankingPromedioTiempoDeCierre.getRankingTiempoPromedio();
    Collections.sort(ranking, Comparator.comparing(ItemRanking::getValorParametro));

    System.out.println(ranking.get(0).getValorParametro());
    System.out.println(ranking.get(1).getValorParametro());
    Assertions.assertEquals(ranking.get(0).getEntidad(), bancoNacion);
    Assertions.assertEquals(ranking.get(1).getEntidad(), bancoProvincia);
  }

  @Test
  public void ElBancoNacionTieneMasIncidentesQueElBancoProvincia(){
    incidenteEscaleraMec.setFechaHoraApertura(LocalDateTime.now().minus(1, ChronoUnit.HOURS));
    incidenteEscaleraMec.marcarComoResuelto();
    incidenteAscensor.setFechaHoraApertura(LocalDateTime.now().minus(9, ChronoUnit.HOURS));
    incidenteAscensor.marcarComoResuelto();
    incidenteBaño.setFechaHoraApertura(LocalDateTime.now().minus(3, ChronoUnit.HOURS));
    incidenteBaño.marcarComoResuelto();

    rankingMayorCantidadIncidentes.generarRanking(entidades);
    ArrayList<ItemRanking> ranking = rankingMayorCantidadIncidentes.getRankingCantidadIncidentes();
    Collections.sort(ranking, Comparator.comparing(ItemRanking::getValorParametro));
    Collections.reverse(ranking); // Mas arriba en el ranking peor, o sea, mas incidentes

    System.out.println(ranking.get(0).getValorParametro());
    System.out.println(ranking.get(1).getValorParametro());
    Assertions.assertEquals(ranking.get(0).getEntidad(), bancoNacion);
    Assertions.assertEquals(ranking.get(1).getEntidad(), bancoProvincia);
  }
}
