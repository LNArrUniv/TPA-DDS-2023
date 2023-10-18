package ar.edu.utn.frba.dds;

import ar.edu.utn.frba.dds.Modelos.Comunidades.Comunidad;
import ar.edu.utn.frba.dds.Modelos.Entidad;
import ar.edu.utn.frba.dds.Modelos.EntidadPropietaria;
import ar.edu.utn.frba.dds.Modelos.Establecimiento;
import ar.edu.utn.frba.dds.Modelos.Incidente;
import ar.edu.utn.frba.dds.Modelos.Notificaciones.MedioNotificacionesEmail;
import ar.edu.utn.frba.dds.Modelos.Notificaciones.SinApuros;
import ar.edu.utn.frba.dds.Modelos.OrganismoDeControl;
import ar.edu.utn.frba.dds.Modelos.Usuarios.Persona;
import ar.edu.utn.frba.dds.Modelos.Usuarios.PersonaDesignada;
import ar.edu.utn.frba.dds.Modelos.Comunidades.RolComunidad;
import ar.edu.utn.frba.dds.Modelos.Servicio;
import ar.edu.utn.frba.dds.Modelos.UbicacionDTO.Localidad;
import ar.edu.utn.frba.dds.Modelos.UbicacionDTO.Provincia;
import ar.edu.utn.frba.dds.Modelos.Usuarios.Usuario;
import ar.edu.utn.frba.dds.Persistencia.EntityManagerHelper;
import ar.edu.utn.frba.dds.Persistencia.repositorios.RepositorioComunidades;
import ar.edu.utn.frba.dds.Persistencia.repositorios.RepositorioIncidentes;
import ar.edu.utn.frba.dds.Persistencia.repositorios.RepositorioPersonas;
import ar.edu.utn.frba.dds.Persistencia.repositorios.RepositorioServicios;
import ar.edu.utn.frba.dds.Servicio.GeoRefAPIService;
import org.hibernate.bytecode.enhance.internal.bytebuddy.EnhancerImpl;
import java.time.LocalTime;

public class MainPrueba {

  public static void main(String[] args) throws Exception {

    // ********** DATOS PARA PROBAR LA DB **********

    Usuario usuarioPepe = new Usuario("pepejose23", "1234");
    PersonaDesignada pepeEncargadoCNRT = new PersonaDesignada("Pepe", "Jose", usuarioPepe);
    Usuario usuarioManuel = new Usuario("manujose674", "4321");
    PersonaDesignada manuelEncargadoTrenesArg = new PersonaDesignada("Manuel", "Jose", usuarioManuel);
    OrganismoDeControl cnrt = new OrganismoDeControl("Comisión Nacional de Regulación del Transporte", pepeEncargadoCNRT);
    EntidadPropietaria trenesArg = new EntidadPropietaria("Trenes Argentinos",
        "Gestionamos y operamos la red ferroviaria del país y hacemos las obras necesarias para que el traslado de pasajeros y el transporte de carga por tren sea cada vez más seguro y eficiente.",
        manuelEncargadoTrenesArg, cnrt);

    Provincia bsas = GeoRefAPIService.getInstancia().listadoDeProvincias().provinciaDeId(6).get();
    Entidad lineaMitre = new Entidad("Linea Mitre", "ghuwghoughnaoiganga", trenesArg, bsas);
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

    Usuario usuarioJuan = new Usuario("juanro1259", "123");
    Persona juan = new Persona("Juan", "Rodriguez", usuarioJuan, config);
    juan.setUbicacion(lobos);
    juan.agregarEntidadDeInteres(lineaSarmiento);
    juan.agregarServicioDeInteres(servicioBanios);

    Usuario usuarioJose = new Usuario("josero1259", "123");
    Persona jose = new Persona("Jose", "Rodriguez", usuarioJose, config);
    jose.setUbicacion(lobos);
    jose.agregarEntidadDeInteres(lineaSarmiento);
    jose.agregarServicioDeInteres(servicioBanios);

    Comunidad comunidadLobos = new Comunidad("Comunidad de Lobos");
    comunidadLobos.agregarServicioDeInteres(servicioBanios);
    juan.darseAltaComunidad(comunidadLobos, RolComunidad.AFECTADO);

    Incidente incidente = new Incidente("Incidente 1", "El servicio de baños de la Estación Lobos está fuera de servicio por el momento", juan, servicioBanios, comunidadLobos);
    Incidente incidente2 = new Incidente("Incidente 2", "El servicio de baños de la Estación Lobos está fuera de servicio por el momento", juan, servicioBanios, comunidadLobos);
    Incidente incidente3 = new Incidente("Incidente 3", "El servicio de baños de la Estación Lobos está fuera de servicio por el momento", juan, servicioBanios, comunidadLobos);
    Incidente incidente4 = new Incidente("Incidente 4", "El servicio de baños de la Estación Lobos está fuera de servicio por el momento", juan, servicioBanios, comunidadLobos);
    incidente.marcarComoResuelto();
    incidente2.marcarComoResuelto();
    incidente3.marcarComoResuelto();

    EntityManagerHelper.beginTransaction();
    EntityManagerHelper.persist(juan);
    EntityManagerHelper.persist(jose);
    EntityManagerHelper.persist(incidente);
    EntityManagerHelper.persist(incidente2);
    EntityManagerHelper.persist(incidente3);
    EntityManagerHelper.persist(incidente4);
    EntityManagerHelper.persist(lineaMitre);
    EntityManagerHelper.commit();

    /*
    // ********** PRUEBA DE LOS REPOS **********

    Provincia bsas = GeoRefAPIService.getInstancia().listadoDeProvincias().provinciaDeId(6).get();
    Localidad lobos = GeoRefAPIService.getInstancia().localidadPorNombreYProv("Lobos", bsas.id).localidades.get(0);

    List incidentes = RepositorioIncidentes.getInstance().incidentesEnUbicacion(lobos);
    //List incidentes = RepositorioIncidentes.getInstance().getActivos();
    System.out.println(incidentes.size());

    Persona persona = RepositorioPersonas.getInstance().get(1);
    System.out.println(persona.getMembresiasAComunidades().size());
    Comunidad comunidadMiembro = RepositorioComunidades.getInstance().get(1);
    Comunidad comunidadAdmin = RepositorioComunidades.getInstance().get(2);
    System.out.println(persona.esAdmin(comunidadMiembro));
    System.out.println(persona.esAdmin(comunidadAdmin));
    */
    //RepositorioComunidades.getInstance().get(2);
    //RepositorioServicios.getInstance().get(1);

  }
}
