package ar.edu.utn.frba.dds;

import ar.edu.utn.frba.dds.Modelos.Comunidad;
import ar.edu.utn.frba.dds.Modelos.Entidad;
import ar.edu.utn.frba.dds.Modelos.EntidadPropietaria;
import ar.edu.utn.frba.dds.Modelos.Establecimiento;
import ar.edu.utn.frba.dds.Modelos.Incidente;
import ar.edu.utn.frba.dds.Modelos.Notificaciones.MedioNotificacionesEmail;
import ar.edu.utn.frba.dds.Modelos.Notificaciones.SinApuros;
import ar.edu.utn.frba.dds.Modelos.OrganismoDeControl;
import ar.edu.utn.frba.dds.Modelos.Persona;
import ar.edu.utn.frba.dds.Modelos.PersonaDesignada;
import ar.edu.utn.frba.dds.Modelos.Rol;
import ar.edu.utn.frba.dds.Modelos.Servicio;
import ar.edu.utn.frba.dds.Modelos.UbicacionDTO.Localidad;
import ar.edu.utn.frba.dds.Modelos.UbicacionDTO.Provincia;
import ar.edu.utn.frba.dds.Persistencia.EntityManagerHelper;
import ar.edu.utn.frba.dds.Persistencia.repositorios.Repositorio;
import ar.edu.utn.frba.dds.Persistencia.repositorios.RepositorioIncidentes;
import ar.edu.utn.frba.dds.Persistencia.repositorios.RepositorioPersonas;
import ar.edu.utn.frba.dds.Servicio.GeoRefAPIService;
import java.time.LocalTime;
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
  }
}
