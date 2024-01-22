package ar.edu.utn.frba.dds;

import ar.edu.utn.frba.dds.models.Entidad;
import ar.edu.utn.frba.dds.models.EntidadPropietaria;
import ar.edu.utn.frba.dds.models.Establecimiento;
import ar.edu.utn.frba.dds.models.Incidente;
import ar.edu.utn.frba.dds.models.OrganismoDeControl;
import ar.edu.utn.frba.dds.models.Servicio;
import ar.edu.utn.frba.dds.models.comunidades.Comunidad;
import ar.edu.utn.frba.dds.models.notificaciones.CuandoSuceden;
import ar.edu.utn.frba.dds.models.notificaciones.MedioNotificacionesEmail;
import ar.edu.utn.frba.dds.models.notificaciones.MedioNotificacionesWhatsapp;
import ar.edu.utn.frba.dds.models.notificaciones.SinApuros;
import ar.edu.utn.frba.dds.models.ubicaciondto.Localidad;
import ar.edu.utn.frba.dds.models.ubicaciondto.Provincia;
import ar.edu.utn.frba.dds.models.usuarios.Persona;
import ar.edu.utn.frba.dds.models.usuarios.PersonaDesignada;
import ar.edu.utn.frba.dds.models.usuarios.Rol;
import ar.edu.utn.frba.dds.models.usuarios.Usuario;
import ar.edu.utn.frba.dds.persistencia.EntityManagerHelper;
import ar.edu.utn.frba.dds.servicio.GeoRefApiService;
import java.time.LocalTime;

public class CargadorDatos {
  public CargadorDatos() {
  }

  public void cargarDatos() throws Exception {

    // USUARIOS Y PERSONSAS DESIGNADAS
    Usuario usuarioLarreta = new Usuario("hr.larreta", "1234", Rol.PERSONA_DESIGNADA);
    PersonaDesignada larretaSubte
        = new PersonaDesignada("Horacio", "Rodriguez Larreta", usuarioLarreta);


    // ORGANISMOS DE CONTROL
    OrganismoDeControl gobCiudad
        = new OrganismoDeControl("Gobierno de la ciudad autonoma de Buenos Aires", larretaSubte);


    //ENTIDAD PROPIETARIAS
    EntidadPropietaria sbase;
    sbase = new EntidadPropietaria("Subterráneos de Buenos Aires Sociedad del Estado",
        "Subterráneos de Buenos Aires Sociedad del Estado (SBASE) es la empresa de la Ciudad "
            + "Autónoma de Buenos Aires que tiene a su cargo la administración de la red de "
            + "subtes, su desarrollo, expansión y el control de la operación del servicio. "
            + "Además, es la autoridad de aplicación de la Ley 4472.", larretaSubte, gobCiudad);


    //PROVINCIAS Y LOCALIDADES
    Provincia bsas;
    Provincia caba;
    bsas = GeoRefApiService.getInstancia().listadoDeProvincias().provinciaDeId(6).get();
    caba = GeoRefApiService.getInstancia().listadoDeProvincias().provinciaDeId(2).get();

    Localidad flores;
    flores = GeoRefApiService
            .getInstancia().localidadPorNombreYprov("Flores", caba.id).localidades.get(0);
    Localidad caballito;
    caballito = GeoRefApiService
            .getInstancia().localidadPorNombreYprov("Caballito", caba.id).localidades.get(0);
    Localidad monserrat;
    monserrat = GeoRefApiService
        .getInstancia().localidadPorNombreYprov("Monserrat", caba.id).localidades.get(0);
    Localidad sanTelmo;
    sanTelmo = GeoRefApiService
        .getInstancia().localidadPorNombreYprov("San Telmo", caba.id).localidades.get(0);
    Localidad sanNicolas;
    sanNicolas = GeoRefApiService
        .getInstancia().localidadPorNombreYprov("San Nicolas", caba.id).localidades.get(0);
    Localidad almagro;
    almagro = GeoRefApiService
        .getInstancia().localidadPorNombreYprov("Almagro", caba.id).localidades.get(0);


    //ENTIDADES
    Entidad lineaA;
    lineaA =
        new Entidad("Linea A de subte", "La Línea A es una de las seis líneas del Subte de Buenos "
            + "Aires, abierta al público el 1° de diciembre de 1913, convirtiéndose así en la "
            + "primera de toda América Latina, el hemisferio sur y todos los países de habla "
            + "hispana. Se extiende a lo largo de 9,7 kilómetros entre Plaza de Mayo en el barrio"
            + " porteño de Monserrat y San Pedrito en el barrio porteño de Flores. Circula por "
            + "debajo de la totalidad de la Avenida de Mayo y parte de la Avenida Rivadavia, "
            + "siendo utilizada por alrededor de 250.000 personas al día.", sbase, bsas);
    Entidad lineaB;
    lineaB =
        new Entidad("Linea B de subte", "La línea B del subte de Buenos Aires,"
            + " abierta al público el 17 de octubre de 1930, se extiende a lo largo de 11,8 km "
            + "entre Leandro N. Alem y Juan Manuel de Rosas. Esta fue la última estación inaugurada"
            + " de la línea, en julio de 2013, junto a la Echeverría.", sbase, bsas);


    //ESTABLECIMIENTOS
    //linea A
    Establecimiento estacionSanPedrito;
    estacionSanPedrito = new Establecimiento("San Pedrito",
        "San Pedrito es la estación terminal oeste de la línea A del Subte de Buenos Aires.",
        flores, null, lineaA);
    Establecimiento estacionSanJoseDeFlores;
    estacionSanJoseDeFlores = new Establecimiento("San Jose de flores",
        "San José de Flores es una estación de la línea A del Subte de Buenos Aires. "
            + "Debido a su cercanía con la estación Flores de la línea Sarmiento, "
            + "es un importante nodo de combinación.", flores, null, lineaA);
    Establecimiento estacionCarabobo;
    estacionCarabobo = new Establecimiento("Carabobo",
        "Carabobo es una estación de la línea A del Subte de Buenos Aires.", flores, null, lineaA);
    Establecimiento estacionPuan;
    estacionPuan = new Establecimiento("Puán",
        "Puán es la estación terminal oeste de la línea A del Subte de Buenos Aires.",
        flores, null, lineaA);
    Establecimiento estacionPrimeraJunta;
    estacionPrimeraJunta = new Establecimiento("Primera Junta",
        "Primera Junta es la estación terminal oeste de la línea A del Subte de Buenos Aires.",
        caballito, null, lineaA);
    Establecimiento estacionAcoyte;
    estacionAcoyte = new Establecimiento("Acoyte",
        "Acoyte es la estación terminal oeste de la línea A del Subte de Buenos Aires.",
        caballito, null, lineaA);
    Establecimiento estacionRioDeJaneiro;
    estacionRioDeJaneiro = new Establecimiento("Rio de Janeiro",
        "Rio de Janeiro es la estación terminal oeste de la línea A del Subte de Buenos Aires.",
        flores, null, lineaA);
    Establecimiento estacionCastroBarros;
    estacionCastroBarros = new Establecimiento("Castro Barros",
        "Castro Barros es la estación terminal oeste de la línea A del Subte de Buenos Aires.",
        flores, null, lineaA);
    Establecimiento estacionLoria;
    estacionLoria = new Establecimiento("Loria",
        "Loria es la estación terminal oeste de la línea A del Subte de Buenos Aires.",
        flores, null, lineaA);
    Establecimiento estacionPlazaMiserere;
    estacionPlazaMiserere = new Establecimiento("Plaza Miserere",
        "Plaza Miserere es la estación terminal oeste de la línea A del Subte de Buenos Aires.",
        flores, null, lineaA);
    Establecimiento estacionAlberti;
    estacionAlberti = new Establecimiento("Alberti",
        "Alberti es la estación terminal oeste de la línea A del Subte de Buenos Aires.",
        flores, null, lineaA);
    Establecimiento estacionPasco;
    estacionPasco = new Establecimiento("Pasco",
        "Pasco es la estación terminal oeste de la línea A del Subte de Buenos Aires.",
        flores, null, lineaA);
    Establecimiento estacionCongreso;
    estacionCongreso = new Establecimiento("Congreso",
        "Congreso es la estación terminal oeste de la línea A del Subte de Buenos Aires.",
        flores, null, lineaA);
    Establecimiento estacionSaenzPena;
    estacionSaenzPena = new Establecimiento("Sáenz Peña",
        "Sáenz Peña es la estación terminal oeste de la línea A del Subte de Buenos Aires.",
        flores, null, lineaA);
    Establecimiento estacionLima;
    estacionLima = new Establecimiento("Lima",
        "Lima es la estación terminal oeste de la línea A del Subte de Buenos Aires.",
        monserrat, null, lineaA);
    Establecimiento estacionPiedras;
    estacionPiedras = new Establecimiento("Piedras",
        "Piedras es la estación terminal oeste de la línea A del Subte de Buenos Aires.",
        sanTelmo, null, lineaA);
    Establecimiento estacionPeru;
    estacionPeru = new Establecimiento("Perú",
        "Perú es la estación terminal oeste de la línea A del Subte de Buenos Aires.",
        sanTelmo, null, lineaA);
    Establecimiento estacionPlazaDeMayo;
    estacionPlazaDeMayo = new Establecimiento("Plaza de Mayo",
        "Plaza de Mayo es la estación terminal oeste de la línea A del Subte de Buenos Aires.",
        monserrat, null, lineaA);


    //linea B
    // Crear establecimientos para la línea B
    Establecimiento estacionLeandroNalem;
    estacionLeandroNalem = new Establecimiento("Leandro N. Alem",
        "Leandro N. Alem es una estación de la línea B del Subte de Buenos Aires.",
        sanNicolas, null, lineaB);
    Establecimiento estacionFlorida;
    estacionFlorida = new Establecimiento("Florida",
        "Florida es una estación de la línea B del Subte de Buenos Aires.",
        sanNicolas, null, lineaB);
    Establecimiento estacionCarlosPellegrini;
    estacionCarlosPellegrini = new Establecimiento("Carlos Pellegrini",
        "Carlos Pellegrini es una estación de la línea B del Subte de Buenos Aires.",
        sanNicolas, null, lineaB);
    Establecimiento estacionUruguay;
    estacionUruguay = new Establecimiento("Uruguay",
        "Uruguay es una estación de la línea B del Subte de Buenos Aires.",
        almagro, null, lineaB);
    Establecimiento estacionCallao;
    estacionCallao = new Establecimiento("Callao",
        "Callao es una estación de la línea B del Subte de Buenos Aires.",
        almagro, null, lineaB);
    Establecimiento estacionPasteur;
    estacionPasteur = new Establecimiento("Pasteur",
        "Pasteur es una estación de la línea B del Subte de Buenos Aires.",
        almagro, null, lineaB);
    Establecimiento estacionPueyrredon;
    estacionPueyrredon = new Establecimiento("Pueyrredón",
        "Pueyrredón es una estación de la línea B del Subte de Buenos Aires.",
        almagro, null, lineaB);
    Establecimiento estacionCarlosGardel;
    estacionCarlosGardel = new Establecimiento("Carlos Gardel",
        "Carlos Gardel es una estación de la línea B del Subte de Buenos Aires.",
        almagro, null, lineaB);
    Establecimiento estacionMedrano;
    estacionMedrano = new Establecimiento("Medrano",
        "Medrano es una estación de la línea B del Subte de Buenos Aires.",
        flores, null, lineaB);
    Establecimiento estacionAngelGallardo;
    estacionAngelGallardo = new Establecimiento("Ángel Gallardo",
        "Ángel Gallardo es una estación de la línea B del Subte de Buenos Aires.",
        flores, null, lineaB);
    Establecimiento estacionMalabia;
    estacionMalabia = new Establecimiento("Malabia",
        "Malabia es una estación de la línea B del Subte de Buenos Aires.",
        almagro, null, lineaB);
    Establecimiento estacionDorregoFcgsm;
    estacionDorregoFcgsm = new Establecimiento("DorregoFCGSM",
        "DorregoFCGSM es una estación de la línea B del Subte de Buenos Aires.",
        flores, null, lineaB);
    Establecimiento estacionFedericoLacroze;
    estacionFedericoLacroze = new Establecimiento("Federico Lacroze",
        "Federico Lacroze es una estación de la línea B del Subte de Buenos Aires.",
        almagro, null, lineaB);
    Establecimiento estacionTronadorVillaOrtuzar;
    estacionTronadorVillaOrtuzar = new Establecimiento("Tronador - Villa Ortúzar",
        "Tronador - Villa Ortúzar es una estación de la línea B del Subte de Buenos Aires.",
        flores, null, lineaB);
    Establecimiento estacionDeLosIncasParqueChas;
    estacionDeLosIncasParqueChas = new Establecimiento("De los Incas - Parque Chas",
        "De los Incas - Parque Chas es una estación de la línea B del Subte de Buenos Aires.",
        flores, null, lineaB);
    Establecimiento estacionEcheverria;
    estacionEcheverria = new Establecimiento("Echeverría",
        "Echeverría es una estación de la línea B del Subte de Buenos Aires.",
        almagro, null, lineaB);
    Establecimiento estacionJuanManuelDeRosasFcgbm;
    estacionJuanManuelDeRosasFcgbm = new Establecimiento("Juan Manuel de RosasFCGBM",
        "Juan Manuel de RosasFCGBM es una estación de la línea B del Subte de Buenos Aires.",
        almagro, null, lineaB);


    //Servicios
    // Crear servicios de baños para cada estación de la línea A
    Servicio servicioBaniosCarabobo;
    servicioBaniosCarabobo = new Servicio("Baños de la Estacion",
        "Baños disponibles en la estacion Carabobo", estacionCarabobo);
    Servicio servicioBaniosSanPedrito;
    servicioBaniosSanPedrito = new Servicio("Baños de la Estacion",
        "Baños disponibles en la estacion San Pedrito", estacionSanPedrito);
    Servicio servicioBaniosSanJoseDeFlores;
    servicioBaniosSanJoseDeFlores = new Servicio("Baños de la Estacion",
        "Baños disponibles en la estacion San Jose de Flores", estacionSanJoseDeFlores);
    Servicio servicioBaniosPuan;
    servicioBaniosPuan = new Servicio("Baños de la Estación",
        "Baños disponibles en la estación Puán.", estacionPuan);
    Servicio servicioBaniosPrimeraJunta;
    servicioBaniosPrimeraJunta = new Servicio("Baños de la Estación",
        "Baños disponibles en la estación Primera Junta.", estacionPrimeraJunta);
    Servicio servicioBaniosAcoyte;
    servicioBaniosAcoyte = new Servicio("Baños de la Estación",
        "Baños disponibles en la estación Acoyte.", estacionAcoyte);
    Servicio servicioBaniosRioDeJaneiro;
    servicioBaniosRioDeJaneiro = new Servicio("Baños de la Estación",
        "Baños disponibles en la estación Rio de Janeiro.", estacionRioDeJaneiro);
    Servicio servicioBaniosCastroBarros;
    servicioBaniosCastroBarros = new Servicio("Baños de la Estación",
        "Baños disponibles en la estación Castro Barros.", estacionCastroBarros);
    Servicio servicioBaniosLoria;
    servicioBaniosLoria = new Servicio("Baños de la Estación",
        "Baños disponibles en la estación Loria.", estacionLoria);
    Servicio servicioBaniosPlazaMiserere;
    servicioBaniosPlazaMiserere = new Servicio("Baños de la Estación",
        "Baños disponibles en la estación Plaza Miserere.", estacionPlazaMiserere);
    Servicio servicioBaniosAlberti;
    servicioBaniosAlberti = new Servicio("Baños de la Estación",
        "Baños disponibles en la estación Alberti.", estacionAlberti);
    Servicio servicioBaniosPasco;
    servicioBaniosPasco = new Servicio("Baños de la Estación",
        "Baños disponibles en la estación Pasco.", estacionPasco);
    Servicio servicioBaniosCongreso;
    servicioBaniosCongreso = new Servicio("Baños de la Estación",
        "Baños disponibles en la estación Congreso.", estacionCongreso);
    Servicio servicioBaniosSaenzPena;
    servicioBaniosSaenzPena = new Servicio("Baños de la Estación",
        "Baños disponibles en la estación Sáenz Peña.", estacionSaenzPena);
    Servicio servicioBaniosLima;
    servicioBaniosLima = new Servicio("Baños de la Estación",
        "Baños disponibles en la estación Lima.", estacionLima);
    Servicio servicioBaniosPiedras;
    servicioBaniosPiedras = new Servicio("Baños de la Estación",
        "Baños disponibles en la estación Piedras.", estacionPiedras);
    Servicio servicioBaniosPeru;
    servicioBaniosPeru = new Servicio("Baños de la Estación",
        "Baños disponibles en la estación Perú.", estacionPeru);
    Servicio servicioBaniosPlazaDeMayo;
    servicioBaniosPlazaDeMayo = new Servicio("Baños de la Estación",
        "Baños disponibles en la estación Plaza de Mayo.", estacionPlazaDeMayo);


    Servicio servicioEscaleraPuan;
    servicioEscaleraPuan = new Servicio("Escalera Mecánica",
        "Escalera mecánica disponible en Puán.", estacionPuan);
    Servicio servicioEscaleraPrimeraJunta;
    servicioEscaleraPrimeraJunta = new Servicio("Escalera Mecánica",
        "Escalera mecánica disponible en Primera Junta.", estacionPrimeraJunta);
    Servicio servicioEscaleraAcoyte;
    servicioEscaleraAcoyte = new Servicio("Escalera Mecánica",
        "Escalera mecánica disponible en Acoyte.", estacionAcoyte);
    Servicio servicioEscaleraCarabobo;
    servicioEscaleraCarabobo = new Servicio("Escalera Mecánica",
        "Escalera mecánica disponible en Carabobo.", estacionCarabobo);
    Servicio servicioEscaleraRioDeJaneiro;
    servicioEscaleraRioDeJaneiro = new Servicio("Escalera Mecánica",
        "Escalera mecánica disponible en Río de Janeiro.", estacionRioDeJaneiro);
    Servicio servicioEscaleraCastroBarros;
    servicioEscaleraCastroBarros = new Servicio("Escalera Mecánica",
        "Escalera mecánica disponible en Castro Barros.", estacionCastroBarros);
    Servicio servicioEscaleraLoria;
    servicioEscaleraLoria = new Servicio("Escalera Mecánica",
        "Escalera mecánica disponible en Loria.", estacionLoria);
    Servicio servicioEscaleraPlazaMiserere;
    servicioEscaleraPlazaMiserere = new Servicio("Escalera Mecánica",
        "Escalera mecánica disponible en Plaza Miserere.", estacionPlazaMiserere);
    Servicio servicioEscaleraAlberti;
    servicioEscaleraAlberti = new Servicio("Escalera Mecánica",
        "Escalera mecánica disponible en Alberti.", estacionAlberti);
    Servicio servicioEscaleraPasco;
    servicioEscaleraPasco = new Servicio("Escalera Mecánica",
        "Escalera mecánica disponible en Pasco.", estacionPasco);
    Servicio servicioEscaleraCongreso;
    servicioEscaleraCongreso = new Servicio("Escalera Mecánica",
        "Escalera mecánica disponible en Congreso.", estacionCongreso);
    Servicio servicioEscaleraSaenzPena;
    servicioEscaleraSaenzPena = new Servicio("Escalera Mecánica",
        "Escalera mecánica disponible en Sáenz Peña.", estacionSaenzPena);
    Servicio servicioEscaleraLima;
    servicioEscaleraLima = new Servicio("Escalera Mecánica",
        "Escalera mecánica disponible en Lima.", estacionLima);
    Servicio servicioEscaleraPiedras;
    servicioEscaleraPiedras = new Servicio("Escalera Mecánica",
        "Escalera mecánica disponible en Piedras.", estacionPiedras);
    Servicio servicioEscaleraPeru;
    servicioEscaleraPeru = new Servicio("Escalera Mecánica",
        "Escalera mecánica disponible en Perú.", estacionPeru);
    Servicio servicioEscaleraPlazaDeMayo;
    servicioEscaleraPlazaDeMayo = new Servicio("Escalera Mecánica",
        "Escalera mecánica disponible en Plaza de Mayo.", estacionPlazaDeMayo);


    // Crear servicios de baños para cada estación de la línea B
    Servicio servicioBaniosLeandroNalem;
    servicioBaniosLeandroNalem = new Servicio("Baños de la Estación",
        "Baños disponibles en la estación Leandro N. Alem.", estacionLeandroNalem);
    Servicio servicioBaniosFlorida;
    servicioBaniosFlorida = new Servicio("Baños de la Estación",
        "Baños disponibles en la estación Florida.", estacionFlorida);
    Servicio servicioBaniosCarlosPellegrini;
    servicioBaniosCarlosPellegrini = new Servicio("Baños de la Estación",
        "Baños disponibles en la estación Carlos Pellegrini.", estacionCarlosPellegrini);
    Servicio servicioBaniosUruguay;
    servicioBaniosUruguay = new Servicio("Baños de la Estación",
        "Baños disponibles en la estación Uruguay.", estacionUruguay);
    Servicio servicioBaniosCallao;
    servicioBaniosCallao = new Servicio("Baños de la Estación",
        "Baños disponibles en la estación Callao.", estacionCallao);
    Servicio servicioBaniosPasteur;
    servicioBaniosPasteur = new Servicio("Baños de la Estación",
        "Baños disponibles en la estación Pasteur.", estacionPasteur);
    Servicio servicioBaniosPueyrredon;
    servicioBaniosPueyrredon = new Servicio("Baños de la Estación",
        "Baños disponibles en la estación Pueyrredón.", estacionPueyrredon);
    Servicio servicioBaniosCarlosGardel;
    servicioBaniosCarlosGardel = new Servicio("Baños de la Estación",
        "Baños disponibles en la estación Carlos Gardel.", estacionCarlosGardel);
    Servicio servicioBaniosMedrano;
    servicioBaniosMedrano = new Servicio("Baños de la Estación",
        "Baños disponibles en la estación Medrano.", estacionMedrano);
    Servicio servicioBaniosAngelGallardo;
    servicioBaniosAngelGallardo = new Servicio("Baños de la Estación",
        "Baños disponibles en la estación Ángel Gallardo.", estacionAngelGallardo);
    Servicio servicioBaniosMalabia;
    servicioBaniosMalabia = new Servicio("Baños de la Estación",
        "Baños disponibles en la estación Malabia.", estacionMalabia);
    Servicio servicioBaniosDorregoFcgsm;
    servicioBaniosDorregoFcgsm = new Servicio("Baños de la Estación",
        "Baños disponibles en la estación DorregoFCGSM.", estacionDorregoFcgsm);
    Servicio servicioBaniosFedericoLacroze;
    servicioBaniosFedericoLacroze = new Servicio("Baños de la Estación",
        "Baños disponibles en la estación Federico Lacroze.", estacionFedericoLacroze);
    Servicio servicioBaniosTronadorVillaOrtuzar;
    servicioBaniosTronadorVillaOrtuzar = new Servicio("Baños de la Estación",
        "Baños disponibles en la estación Tronador - Villa Ortúzar.", estacionTronadorVillaOrtuzar);
    Servicio servicioBaniosDeLosIncasParqueChas;
    servicioBaniosDeLosIncasParqueChas = new Servicio("Baños de la Estación",
        "Baños disponibles en la estación De los Incas - Parque Chas.",
        estacionDeLosIncasParqueChas);
    Servicio servicioBaniosEcheverria;
    servicioBaniosEcheverria = new Servicio("Baños de la Estación",
        "Baños disponibles en la estación Echeverría.", estacionEcheverria);
    Servicio servicioBaniosJuanManuelDeRosasFcgbm;
    servicioBaniosJuanManuelDeRosasFcgbm = new Servicio("Baños de la Estación",
        "Baños disponibles en la estación Juan Manuel de RosasFCGBM.",
        estacionJuanManuelDeRosasFcgbm);


    Servicio servicioEscaleraLeandroNalem;
    servicioEscaleraLeandroNalem = new Servicio("Escalera Mecánica",
        "Escalera mecánica disponible en Leandro N. Alem.", estacionLeandroNalem);
    Servicio servicioEscaleraFlorida;
    servicioEscaleraFlorida = new Servicio("Escalera Mecánica",
        "Escalera mecánica disponible en Florida.", estacionFlorida);
    Servicio servicioEscaleraCarlosPellegrini;
    servicioEscaleraCarlosPellegrini = new Servicio("Escalera Mecánica",
        "Escalera mecánica disponible en Carlos Pellegrini.", estacionCarlosPellegrini);
    Servicio servicioEscaleraUruguay;
    servicioEscaleraUruguay = new Servicio("Escalera Mecánica",
        "Escalera mecánica disponible en Uruguay.", estacionUruguay);
    Servicio servicioEscaleraCallao;
    servicioEscaleraCallao = new Servicio("Escalera Mecánica",
        "Escalera mecánica disponible en Callao.", estacionCallao);
    Servicio servicioEscaleraPueyrredon;
    servicioEscaleraPueyrredon = new Servicio("Escalera Mecánica",
        "Escalera mecánica disponible en Pueyrredón.", estacionPueyrredon);
    Servicio servicioEscaleraCarlosGardel;
    servicioEscaleraCarlosGardel = new Servicio("Escalera Mecánica",
        "Escalera mecánica disponible en Carlos Gardel.", estacionCarlosGardel);
    Servicio servicioEscaleraMedrano;
    servicioEscaleraMedrano = new Servicio("Escalera Mecánica",
        "Escalera mecánica disponible en Medrano.", estacionMedrano);
    Servicio servicioEscaleraAngelGallardo;
    servicioEscaleraAngelGallardo = new Servicio("Escalera Mecánica",
        "Escalera mecánica disponible en Ángel Gallardo.", estacionAngelGallardo);
    Servicio servicioEscaleraFedericoLacroze;
    servicioEscaleraFedericoLacroze = new Servicio("Escalera Mecánica",
        "Escalera mecánica disponible en Federico Lacroze.", estacionFedericoLacroze);
    Servicio servicioEscaleraTronadorVillaOrtuzar;
    servicioEscaleraTronadorVillaOrtuzar = new Servicio("Escalera Mecánica",
        "Escalera mecánica disponible en Tronador - Villa Ortúzar.", estacionTronadorVillaOrtuzar);
    Servicio servicioEscaleraDeLosIncasParqueChas;
    servicioEscaleraDeLosIncasParqueChas = new Servicio("Escalera Mecánica",
        "Escalera mecánica disponible en De los Incas - Parque Chas", estacionDeLosIncasParqueChas);
    Servicio servicioEscaleraEcheverria;
    servicioEscaleraEcheverria = new Servicio("Escalera Mecánica",
        "Escalera mecánica disponible en Echeverría.", estacionEcheverria);


    //Comunidades
    Comunidad comunidadCaballito = new Comunidad("Comunidad de Caballito");
    comunidadCaballito.agregarServicioDeInteres(servicioBaniosAcoyte);
    comunidadCaballito.agregarServicioDeInteres(servicioBaniosPrimeraJunta);
    Comunidad comunidadAlmagro = new Comunidad("Comunidad de Almagro");
    comunidadAlmagro.agregarServicioDeInteres(servicioEscaleraUruguay);
    comunidadAlmagro.agregarServicioDeInteres(servicioEscaleraCallao);

    MedioNotificacionesEmail medioPreferido = new MedioNotificacionesEmail("juanro@gmail.com");
    SinApuros config = new SinApuros(medioPreferido);
    config.agregarHorario(LocalTime.of(20, 30, 0));
    config.agregarHorario(LocalTime.of(7, 45, 0));
    config.agregarHorario(LocalTime.of(14, 0, 0));

    // === Personas ===
    Usuario usuarioJuan = new Usuario("juanro1259", "123", Rol.NORMAL);
    Persona juan = new Persona("Juan", "Rodriguez", usuarioJuan, config);
    juan.setUbicacion(flores);
    juan.agregarEntidadDeInteres(lineaA);
    juan.agregarServicioDeInteres(servicioBaniosCarabobo);
    juan.darseAltaComunidadCreada(comunidadCaballito);

    MedioNotificacionesWhatsapp medioPreferidoJose = new MedioNotificacionesWhatsapp("123123123");
    CuandoSuceden configJose = new CuandoSuceden(medioPreferidoJose);
    Usuario usuarioJose = new Usuario("josero1259", "123", Rol.NORMAL);
    Persona jose = new Persona("Jose", "Rodriguez", usuarioJose, configJose);
    jose.setUbicacion(almagro);
    jose.agregarEntidadDeInteres(lineaB);
    jose.agregarServicioDeInteres(servicioEscaleraLeandroNalem);
    jose.darseAltaComunidadCreada(comunidadAlmagro);

    Incidente incidente1;
    Incidente incidente2;
    Incidente incidente3;
    incidente1 = new Incidente("Incidente 1",
        "Los baños están fuera de servicio", juan, servicioBaniosPrimeraJunta, comunidadCaballito);
    incidente2 = new Incidente("Incidente 2",
        "Los baños no están disponibles por mantenimiento",
        juan, servicioBaniosPrimeraJunta, comunidadCaballito);
    incidente3 = new Incidente("Incidente 1",
        "Las escaleras mecanicas no están funcionando",
        jose, servicioEscaleraCallao, comunidadAlmagro);

    comunidadCaballito.informarIncidenteResuelto(incidente1);
    comunidadAlmagro.informarIncidenteResuelto(incidente3);


    // ------- PERSISTENCIA -------

    EntityManagerHelper.beginTransaction();

    EntityManagerHelper.persist(larretaSubte);
    EntityManagerHelper.persist(gobCiudad);


    EntityManagerHelper.persist(lineaA);
    EntityManagerHelper.persist(sbase);

    //Linea A
    EntityManagerHelper.persist(estacionCarabobo);
    EntityManagerHelper.persist(estacionSanPedrito);
    EntityManagerHelper.persist(estacionSanJoseDeFlores);
    EntityManagerHelper.persist(estacionPuan);
    EntityManagerHelper.persist(estacionPrimeraJunta);
    EntityManagerHelper.persist(estacionAcoyte);
    EntityManagerHelper.persist(estacionRioDeJaneiro);
    EntityManagerHelper.persist(estacionCastroBarros);
    EntityManagerHelper.persist(estacionLoria);
    EntityManagerHelper.persist(estacionPlazaMiserere);
    EntityManagerHelper.persist(estacionAlberti);
    EntityManagerHelper.persist(estacionPasco);
    EntityManagerHelper.persist(estacionCongreso);
    EntityManagerHelper.persist(estacionSaenzPena);
    EntityManagerHelper.persist(estacionLima);
    EntityManagerHelper.persist(estacionPiedras);
    EntityManagerHelper.persist(estacionPeru);
    EntityManagerHelper.persist(estacionPlazaDeMayo);

    //linea b
    // Persistir cada estación de la línea B individualmente
    EntityManagerHelper.persist(estacionLeandroNalem);
    EntityManagerHelper.persist(estacionFlorida);
    EntityManagerHelper.persist(estacionCarlosPellegrini);
    EntityManagerHelper.persist(estacionUruguay);
    EntityManagerHelper.persist(estacionCallao);
    EntityManagerHelper.persist(estacionPasteur);
    EntityManagerHelper.persist(estacionPueyrredon);
    EntityManagerHelper.persist(estacionCarlosGardel);
    EntityManagerHelper.persist(estacionMedrano);
    EntityManagerHelper.persist(estacionAngelGallardo);
    EntityManagerHelper.persist(estacionMalabia);
    EntityManagerHelper.persist(estacionDorregoFcgsm);
    EntityManagerHelper.persist(estacionFedericoLacroze);
    EntityManagerHelper.persist(estacionTronadorVillaOrtuzar);
    EntityManagerHelper.persist(estacionDeLosIncasParqueChas);
    EntityManagerHelper.persist(estacionEcheverria);
    EntityManagerHelper.persist(estacionJuanManuelDeRosasFcgbm);

    // Persistir servicios de baños de la línea A
    EntityManagerHelper.persist(servicioBaniosCarabobo);
    EntityManagerHelper.persist(servicioBaniosSanPedrito);
    EntityManagerHelper.persist(servicioBaniosSanJoseDeFlores);
    EntityManagerHelper.persist(servicioBaniosPuan);
    EntityManagerHelper.persist(servicioBaniosPrimeraJunta);
    EntityManagerHelper.persist(servicioBaniosAcoyte);
    EntityManagerHelper.persist(servicioBaniosRioDeJaneiro);
    EntityManagerHelper.persist(servicioBaniosCastroBarros);
    EntityManagerHelper.persist(servicioBaniosLoria);
    EntityManagerHelper.persist(servicioBaniosPlazaMiserere);
    EntityManagerHelper.persist(servicioBaniosAlberti);
    EntityManagerHelper.persist(servicioBaniosPasco);
    EntityManagerHelper.persist(servicioBaniosCongreso);
    EntityManagerHelper.persist(servicioBaniosSaenzPena);
    EntityManagerHelper.persist(servicioBaniosLima);
    EntityManagerHelper.persist(servicioBaniosPiedras);
    EntityManagerHelper.persist(servicioBaniosPeru);
    EntityManagerHelper.persist(servicioBaniosPlazaDeMayo);

    // Persistir servicios de escalera mecánica para la Línea A
    EntityManagerHelper.persist(servicioEscaleraPuan);
    EntityManagerHelper.persist(servicioEscaleraPrimeraJunta);
    EntityManagerHelper.persist(servicioEscaleraAcoyte);
    EntityManagerHelper.persist(servicioEscaleraCarabobo);
    EntityManagerHelper.persist(servicioEscaleraRioDeJaneiro);
    EntityManagerHelper.persist(servicioEscaleraCastroBarros);
    EntityManagerHelper.persist(servicioEscaleraLoria);
    EntityManagerHelper.persist(servicioEscaleraPlazaMiserere);
    EntityManagerHelper.persist(servicioEscaleraAlberti);
    EntityManagerHelper.persist(servicioEscaleraPasco);
    EntityManagerHelper.persist(servicioEscaleraCongreso);
    EntityManagerHelper.persist(servicioEscaleraSaenzPena);
    EntityManagerHelper.persist(servicioEscaleraLima);
    EntityManagerHelper.persist(servicioEscaleraPiedras);
    EntityManagerHelper.persist(servicioEscaleraPeru);
    EntityManagerHelper.persist(servicioEscaleraPlazaDeMayo);

    // Persistir servicios de baños de la línea B
    EntityManagerHelper.persist(servicioBaniosLeandroNalem);
    EntityManagerHelper.persist(servicioBaniosFlorida);
    EntityManagerHelper.persist(servicioBaniosCarlosPellegrini);
    EntityManagerHelper.persist(servicioBaniosUruguay);
    EntityManagerHelper.persist(servicioBaniosCallao);
    EntityManagerHelper.persist(servicioBaniosPasteur);
    EntityManagerHelper.persist(servicioBaniosPueyrredon);
    EntityManagerHelper.persist(servicioBaniosCarlosGardel);
    EntityManagerHelper.persist(servicioBaniosMedrano);
    EntityManagerHelper.persist(servicioBaniosAngelGallardo);
    EntityManagerHelper.persist(servicioBaniosMalabia);
    EntityManagerHelper.persist(servicioBaniosDorregoFcgsm);
    EntityManagerHelper.persist(servicioBaniosFedericoLacroze);
    EntityManagerHelper.persist(servicioBaniosTronadorVillaOrtuzar);
    EntityManagerHelper.persist(servicioBaniosDeLosIncasParqueChas);
    EntityManagerHelper.persist(servicioBaniosEcheverria);
    EntityManagerHelper.persist(servicioBaniosJuanManuelDeRosasFcgbm);

    // Persistir servicios de escalera mecánica para la Línea B
    EntityManagerHelper.persist(servicioEscaleraLeandroNalem);
    EntityManagerHelper.persist(servicioEscaleraFlorida);
    EntityManagerHelper.persist(servicioEscaleraCarlosPellegrini);
    EntityManagerHelper.persist(servicioEscaleraUruguay);
    EntityManagerHelper.persist(servicioEscaleraCallao);
    EntityManagerHelper.persist(servicioEscaleraPueyrredon);
    EntityManagerHelper.persist(servicioEscaleraCarlosGardel);
    EntityManagerHelper.persist(servicioEscaleraMedrano);
    EntityManagerHelper.persist(servicioEscaleraAngelGallardo);
    EntityManagerHelper.persist(servicioEscaleraFedericoLacroze);
    EntityManagerHelper.persist(servicioEscaleraTronadorVillaOrtuzar);
    EntityManagerHelper.persist(servicioEscaleraDeLosIncasParqueChas);
    EntityManagerHelper.persist(servicioEscaleraEcheverria);

    // Comunidad
    EntityManagerHelper.persist(comunidadCaballito);

    // Persona
    EntityManagerHelper.persist(juan);
    EntityManagerHelper.persist(jose);

    EntityManagerHelper.persist(incidente1);
    EntityManagerHelper.persist(incidente2);
    EntityManagerHelper.persist(incidente3);

    EntityManagerHelper.commit();
  }
}
