package ar.edu.utn.frba.dds;

import ar.edu.utn.frba.dds.Modelos.Comunidades.Comunidad;
import ar.edu.utn.frba.dds.Modelos.Entidad;
import ar.edu.utn.frba.dds.Modelos.EntidadPropietaria;
import ar.edu.utn.frba.dds.Modelos.Establecimiento;
import ar.edu.utn.frba.dds.Modelos.Incidente;
import ar.edu.utn.frba.dds.Modelos.Notificaciones.CuandoSuceden;
import ar.edu.utn.frba.dds.Modelos.Notificaciones.MedioNotificacionesEmail;
import ar.edu.utn.frba.dds.Modelos.Notificaciones.MedioNotificacionesWhatsapp;
import ar.edu.utn.frba.dds.Modelos.Notificaciones.SinApuros;
import ar.edu.utn.frba.dds.Modelos.OrganismoDeControl;
import ar.edu.utn.frba.dds.Modelos.Rankings.MetodosRanking;
import ar.edu.utn.frba.dds.Modelos.Rankings.RankingIncidentes;
import ar.edu.utn.frba.dds.Modelos.Usuarios.Persona;
import ar.edu.utn.frba.dds.Modelos.Usuarios.PersonaDesignada;
import ar.edu.utn.frba.dds.Modelos.Comunidades.RolComunidad;
import ar.edu.utn.frba.dds.Modelos.Servicio;
import ar.edu.utn.frba.dds.Modelos.UbicacionDTO.Localidad;
import ar.edu.utn.frba.dds.Modelos.UbicacionDTO.Provincia;
import ar.edu.utn.frba.dds.Modelos.Usuarios.Rol;
import ar.edu.utn.frba.dds.Modelos.Usuarios.Usuario;
import ar.edu.utn.frba.dds.Persistencia.EntityManagerHelper;
import ar.edu.utn.frba.dds.Persistencia.repositorios.RepositorioPersonas;
import ar.edu.utn.frba.dds.Servicio.GeoRefAPIService;
import java.time.LocalTime;
import java.util.List;

public class MainPrueba {
  public static void main(String[] args) throws Exception {

    // === Usuarios y Personas Designadas ===
    Usuario usuarioPepe = new Usuario("pepejose23", "1234", Rol.PERSONA_DESIGNADA);
    PersonaDesignada pepeEncargadoCNRT = new PersonaDesignada("Pepe", "Jose", usuarioPepe);

    Usuario usuarioManuel = new Usuario("manujose674", "4321", Rol.PERSONA_DESIGNADA);
    PersonaDesignada manuelEncargadoTrenesArg = new PersonaDesignada("Manuel", "Jose", usuarioManuel);

    Usuario usuarioLaura = new Usuario("lauritagonz56", "5678", Rol.PERSONA_DESIGNADA);
    PersonaDesignada lauraMetro = new PersonaDesignada("Laura", "Gonzalez", usuarioLaura);

    Usuario usuarioAnna = new Usuario("annafer564", "8765", Rol.PERSONA_DESIGNADA);
    PersonaDesignada annaAvianca = new PersonaDesignada("Anna", "Fernandez", usuarioAnna);

    Usuario usuarioPedro = new Usuario("pedroluis09", "0987", Rol.PERSONA_DESIGNADA);
    PersonaDesignada pedroAeroControl = new PersonaDesignada("Pedro", "Luis", usuarioPedro);

    Usuario usuarioRosa = new Usuario("rositaflower45", "4567", Rol.PERSONA_DESIGNADA);
    PersonaDesignada rosaBusLines = new PersonaDesignada("Rosa", "Flores", usuarioRosa);

    // === Organismos y Entidades Propietarias ===
    OrganismoDeControl cnrt = new OrganismoDeControl("CNRT", pepeEncargadoCNRT);
    OrganismoDeControl metroControl = new OrganismoDeControl("Metro Control", lauraMetro);
    OrganismoDeControl aeroControl = new OrganismoDeControl("Aero Control", pedroAeroControl);
    OrganismoDeControl busControl = new OrganismoDeControl("Bus Control", rosaBusLines);

    EntidadPropietaria trenesArg = new EntidadPropietaria("Trenes Argentinos", "Descripción...", manuelEncargadoTrenesArg, cnrt);
    EntidadPropietaria metroBA = new EntidadPropietaria("Metro Buenos Aires", "Descripción...", lauraMetro, metroControl);
    EntidadPropietaria avianca = new EntidadPropietaria("Avianca Airlines", "Descripción...", annaAvianca, aeroControl);
    EntidadPropietaria busLines = new EntidadPropietaria("Bus Lines Argentina", "Descripción...", rosaBusLines, busControl);

    // === Entidades y Establecimientos ===
    Provincia bsas = GeoRefAPIService.getInstancia().listadoDeProvincias().provinciaDeId(6).get();
    /*Corrientes*/
    Provincia ciudadY = GeoRefAPIService.getInstancia().listadoDeProvincias().provinciaDeId(18).get();
    /*Tucumán*/
    Provincia ciudadZ = GeoRefAPIService.getInstancia().listadoDeProvincias().provinciaDeId(90).get();
    /*Formosa*/
    Provincia ciudadW = GeoRefAPIService.getInstancia().listadoDeProvincias().provinciaDeId(34).get();

    Localidad lobos = GeoRefAPIService.getInstancia().localidadPorNombreYProv("Lobos", bsas.id).localidades.get(0);
    Localidad localidadW = GeoRefAPIService.getInstancia().localidadPorNombreYProv("BELLA VISTA", ciudadY.id).localidades.get(0);
    Localidad localidadZ = GeoRefAPIService.getInstancia().localidadPorNombreYProv("MACOMITAS", ciudadZ.id).localidades.get(0);
    Localidad localidadY = GeoRefAPIService.getInstancia().localidadPorNombreYProv("7 DE ABRIL", ciudadZ.id).localidades.get(0);
    Localidad localidadX = GeoRefAPIService.getInstancia().localidadPorNombreYProv("LOS CHIRIGUANOS", ciudadW.id).localidades.get(0);

    Entidad lineaSarmiento = new Entidad("Linea Sarmiento", "...", trenesArg, bsas);
    Entidad[] trenes = new Entidad[6];
    Entidad[] metros = new Entidad[6];
    Entidad[] aerolineas = new Entidad[6];
    Entidad[] buses = new Entidad[6];
    for (int i = 0; i < 6; i++) {
      trenes[i] = new Entidad("Tren Linea " + (i+1), "Descripción...", trenesArg, bsas);
      metros[i] = new Entidad("Metro Linea " + (i+1), "Descripción...", metroBA, ciudadY);
      aerolineas[i] = new Entidad("Vuelo " + (i+1), "Descripción...", avianca, ciudadZ);
      buses[i] = new Entidad("Bus Ruta " + (i+1), "Descripción...", busLines, ciudadW);
    }

    Establecimiento estacionLobos = new Establecimiento("Estacion Lobos", "...", lobos, null, lineaSarmiento);
    Establecimiento[] estacionesTren = new Establecimiento[10];
    Establecimiento[] estacionesMetro = new Establecimiento[10];
    Establecimiento[] aeropuertos = new Establecimiento[10];
    Establecimiento[] paradasBus = new Establecimiento[10];
    for (int i = 0; i < 10; i++) {
      estacionesTren[i] = new Establecimiento("Estación Tren " + (i+1), "Descripción...", localidadX, null, trenes[i % 6]);
      estacionesMetro[i] = new Establecimiento("Estación Metro " + (i+1), "Descripción...", localidadY, null, metros[i % 6]);
      aeropuertos[i] = new Establecimiento("Aeropuerto " + (i+1), "Descripción...", localidadZ, null, aerolineas[i % 6]);
      paradasBus[i] = new Establecimiento("Parada Bus " + (i+1), "Descripción...", localidadW, null, buses[i % 6]);
    }

    // === Servicios ===
    Servicio servicioBanios = new Servicio("Baños de la Estación", "...", estacionLobos);
    Servicio[] serviciosTren = new Servicio[10];
    Servicio[] serviciosMetro = new Servicio[10];
    Servicio[] serviciosAero = new Servicio[10];
    Servicio[] serviciosBus = new Servicio[10];
    for (int i = 0; i < 10; i++) {
      serviciosTren[i] = new Servicio("Servicio Tren " + (i+1), "Descripción...", estacionesTren[i]);
      serviciosMetro[i] = new Servicio("Servicio Metro " + (i+1), "Descripción...", estacionesMetro[i]);
      serviciosAero[i] = new Servicio("Servicio Aero " + (i+1), "Descripción...", aeropuertos[i]);
      serviciosBus[i] = new Servicio("Servicio Bus " + (i+1), "Descripción...", paradasBus[i]);
    }

    // === Notificaciones ===
    MedioNotificacionesEmail medioPreferido = new MedioNotificacionesEmail("juanro@gmail.com");
    SinApuros config = new SinApuros(medioPreferido);
    config.agregarHorario(LocalTime.of(20, 30, 0));
    config.agregarHorario(LocalTime.of(7, 45, 0));
    config.agregarHorario(LocalTime.of(14, 0, 0));

    // === Personas ===
    /*
    Persona pepe = new Persona("Pepe", "Jose", usuarioPepe, config);
    pepe.setUbicacion(localidadW);
    Persona manuel = new Persona("Manuel", "Jose", usuarioManuel, config);
    manuel.setUbicacion(localidadZ);
    Persona laura = new Persona("Laura", "Gonzalez", usuarioLaura, config);
    laura.setUbicacion(localidadY);
    Persona anna = new Persona("Anna", "Fernandez", usuarioAnna, config);
    anna.setUbicacion(localidadX);
    Persona pedro = new Persona("Pedro", "Luis", usuarioPedro, config);
    pedro.setUbicacion(lobos);
    Persona rosa = new Persona("Rosa", "Flores", usuarioRosa, config);
    rosa.setUbicacion(localidadW);
    */
    Usuario usuarioJuan = new Usuario("juanro1259", "123", Rol.NORMAL);
    Persona juan = new Persona("Juan", "Rodriguez", usuarioJuan, config);
    juan.setUbicacion(lobos);
    juan.agregarEntidadDeInteres(lineaSarmiento);
    juan.agregarServicioDeInteres(servicioBanios);

    MedioNotificacionesWhatsapp medioPreferidoJose = new MedioNotificacionesWhatsapp("123123123");
    CuandoSuceden configJose = new CuandoSuceden(medioPreferidoJose);
    Usuario usuarioJose = new Usuario("josero1259", "123", Rol.NORMAL);
    Persona jose = new Persona("Jose", "Rodriguez", usuarioJose, configJose);
    jose.setUbicacion(lobos);
    jose.agregarEntidadDeInteres(lineaSarmiento);
    jose.agregarServicioDeInteres(servicioBanios);

    // === Comunidades ===
    Comunidad comunidadLobos = new Comunidad("Comunidad de Lobos");
    Comunidad[] comunidades = new Comunidad[10];
    for (int i = 0; i < 10; i++) {
      comunidades[i] = new Comunidad("Comunidad " + (i+1));
      comunidades[i].agregarServicioDeInteres(serviciosTren[i]);
      comunidades[i].agregarServicioDeInteres(serviciosMetro[i]);
      comunidades[i].agregarServicioDeInteres(serviciosAero[i]);
      comunidades[i].agregarServicioDeInteres(serviciosBus[i]);
    }

    // === Incidentes ===
    Incidente incidente0 = new Incidente("Incidente 1", "El servicio de baños de la Estación Lobos está fuera de servicio por el momento", juan, servicioBanios, comunidadLobos);
    Incidente incidente2 = new Incidente("Incidente 2", "El servicio de baños de la Estación Lobos está fuera de servicio por el momento", juan, servicioBanios, comunidadLobos);
    Incidente incidente3 = new Incidente("Incidente 3", "El servicio de baños de la Estación Lobos está fuera de servicio por el momento", juan, servicioBanios, comunidadLobos);
    Incidente incidente4 = new Incidente("Incidente 4", "El servicio de baños de la Estación Lobos está fuera de servicio por el momento", juan, servicioBanios, comunidadLobos);

    incidente0.marcarComoResuelto();
    incidente3.marcarComoResuelto();

    // === Extras ===
    comunidadLobos.agregarServicioDeInteres(servicioBanios);
    juan.darseAltaComunidad(comunidadLobos, RolComunidad.AFECTADO);

    // === Persistencia ===

      EntityManagerHelper.beginTransaction();

      // Persistir Usuarios y Personas Designadas
      EntityManagerHelper.persist(juan);
      EntityManagerHelper.persist(jose);
      EntityManagerHelper.persist(pepeEncargadoCNRT);
      EntityManagerHelper.persist(manuelEncargadoTrenesArg);
      EntityManagerHelper.persist(lauraMetro);
      EntityManagerHelper.persist(annaAvianca);
      EntityManagerHelper.persist(pedroAeroControl);
      EntityManagerHelper.persist(rosaBusLines);

      // Persistir Organismos y Entidades Propietarias
      EntityManagerHelper.persist(cnrt);
      EntityManagerHelper.persist(metroControl);
      EntityManagerHelper.persist(aeroControl);
      EntityManagerHelper.persist(busControl);
      EntityManagerHelper.persist(trenesArg);
      EntityManagerHelper.persist(metroBA);
      EntityManagerHelper.persist(avianca);
      EntityManagerHelper.persist(busLines);

      // Persistir Entidades y Establecimientos
      for (int i = 0; i < 6; i++) {
        EntityManagerHelper.persist(trenes[i]);
        EntityManagerHelper.persist(metros[i]);
        EntityManagerHelper.persist(aerolineas[i]);
        EntityManagerHelper.persist(buses[i]);
      }

      for (int i = 0; i < 10; i++) {
        EntityManagerHelper.persist(estacionesTren[i]);
        EntityManagerHelper.persist(estacionesMetro[i]);
        EntityManagerHelper.persist(aeropuertos[i]);
        EntityManagerHelper.persist(paradasBus[i]);
      }

      // Persistir Servicios
      for (int i = 0; i < 10; i++) {
        EntityManagerHelper.persist(serviciosTren[i]);
        EntityManagerHelper.persist(serviciosMetro[i]);
        EntityManagerHelper.persist(serviciosAero[i]);
        EntityManagerHelper.persist(serviciosBus[i]);
      }

      // Persistir Comunidades
      for (int i = 0; i < 10; i++) {
        EntityManagerHelper.persist(comunidades[i]);
      }

      // Persistir Incidentes (incluyendo los que ya tenías)
      EntityManagerHelper.persist(incidente0);
      EntityManagerHelper.persist(incidente2);
      EntityManagerHelper.persist(incidente3);
      EntityManagerHelper.persist(incidente4);


      EntityManagerHelper.commit();

      Persona persona = RepositorioPersonas.getInstance().get(1);
      SinApuros configprueba = (SinApuros) persona.getConfiguracionNotificaciones();
      configprueba.getHorariosDeNotificacion().forEach(localTime -> System.out.println(localTime.toString()));
  }
}