package ar.edu.utn.frba.dds;

import ar.edu.utn.frba.dds.Servicio.gradoDeImpacto.CalculadorGradoDeImpactoService;
import ar.edu.utn.frba.dds.Servicio.gradoDeImpacto.ListadoDeResultados;
import ar.edu.utn.frba.dds.Servicio.gradoDeImpacto.ListadoDeValores;
import ar.edu.utn.frba.dds.Servicio.gradoDeImpacto.ValoresFormula;
import java.util.ArrayList;
import java.util.List;

public class MainPrueba {

  public static void main(String[] args) throws Exception {
    /*
    // ********** DATOS PARA PROBAR LA DB **********

    PersonaDesignada pepeEncargadoCNRT = new PersonaDesignada("Pepe", "Jose", "pepejose23", "1234");
    PersonaDesignada manuelEncargadoTrenesArg = new PersonaDesignada("Manuel", "Jose", "manujose674", "4321");
    OrganismoDeControl cnrt = new OrganismoDeControl("Comisión Nacional de Regulación del Transporte", pepeEncargadoCNRT);
    EntidadPropietaria trenesArg = new EntidadPropietaria("Trenes Argentinos",
        "Gestionamos y operamos la red ferroviaria del país y hacemos las obras necesarias para que el traslado de pasajeros y el transporte de carga por tren sea cada vez más seguro y eficiente.",
        manuelEncargadoTrenesArg, cnrt);

    Provincia bsas = GeoRefAPIService.getInstancia().listadoDeProvincias().provinciaDeId(6).get();
    Entidad lineaSarmiento = new Entidad("Linea Sarmiento",
        "Una de las 7 líneas suburbanas de los Ferrocarriles metropolitanos de Buenos Aires, presta servicios de pasajeros entre las estaciones Once, en el barrio porteño de Balvanera, y las estaciones de Moreno, Lobos y Mercedes en el oeste del Gran Buenos Aires.",
        trenesArg, bsas);

    Localidad lobos = GeoRefAPIService.getInstancia().localidadPorNombreYProv("Lobos", bsas.id).localidades.get(0);
    Establecimiento estacionLobos = new Establecimiento("Estacion Lobos", "Estacion de la localidad Lobos, terminal del ramal Merlo-Lobos de la Linea Sarmiento", lobos, null, lineaSarmiento);

    Servicio servicioBanios = new Servicio("Baños de la Estación", "Servicio de baños de la estación Lobos de la línea Sarmiento", estacionLobos);


    MedioNotificacionesEmail medioPreferido = new MedioNotificacionesEmail("juanro@gmail.com");
    SinApuros config = new SinApuros(medioPreferido);
    config.agregarHorario(LocalTime.of(20, 30, 0));
    config.agregarHorario(LocalTime.of(7, 45, 0));
    config.agregarHorario(LocalTime.of(14, 0, 0));

    Persona juan = new Persona("Juan", "Rodriguez", "juanro1259", "PXm^cgC#5Ehm3", config);
    juan.setUbicacion(lobos);
    juan.agregarEntidadDeInteres(lineaSarmiento);
    juan.agregarServicioDeInteres(servicioBanios);

    Comunidad comunidadLobos = new Comunidad("Comunidad de Lobos");
    comunidadLobos.agregarServicioDeInteres(servicioBanios);
    juan.darseAltaComunidad(comunidadLobos, Rol.AFECTADO);

    Incidente incidente = new Incidente("El servicio de baños de la Estación Lobos está fuera de servicio por el momento", juan, servicioBanios, comunidadLobos);


    EntityManagerHelper.beginTransaction();
    EntityManagerHelper.persist(juan);
    EntityManagerHelper.persist(incidente);
    EntityManagerHelper.commit();
    */
    /*
    // ********** PRUEBA DE LOS REPOS **********

    Provincia bsas = GeoRefAPIService.getInstancia().listadoDeProvincias().provinciaDeId(6).get();
    Localidad lobos = GeoRefAPIService.getInstancia().localidadPorNombreYProv("Lobos", bsas.id).localidades.get(0);

    List incidentes = RepositorioIncidentes.getInstance().incidentesEnUbicacion(lobos);
    //List incidentes = RepositorioIncidentes.getInstance().getActivos();
    System.out.println(incidentes.size());
    */

    ValoresFormula valoresFormula1 = new ValoresFormula(1, 1, 1, 1, 1);
    ValoresFormula valoresFormula2 = new ValoresFormula(2, 1, 2, 1, 1);
    ValoresFormula valoresFormula3 = new ValoresFormula(3, 1, 3, 1, 1);
    List<ValoresFormula> valores = new ArrayList();
    valores.add(valoresFormula1);
    valores.add(valoresFormula2);
    valores.add(valoresFormula3);
    ListadoDeValores listado = new ListadoDeValores(valores);
    CalculadorGradoDeImpactoService.getInstancia().calcularGradoDeImpacto(listado);

    ListadoDeResultados resultados = CalculadorGradoDeImpactoService.getInstancia().obtenerResultados();
    System.out.println(resultados.valorDeEntidad(1).get().entidad_id + " " + resultados.valorDeEntidad(1).get().resultadoGradoImpacto);
    System.out.println(resultados.valorDeEntidad(2).get().entidad_id + " " + resultados.valorDeEntidad(2).get().resultadoGradoImpacto);
    System.out.println(resultados.valorDeEntidad(3).get().entidad_id + " " + resultados.valorDeEntidad(3).get().resultadoGradoImpacto);

  }
}
