package ar.edu.utn.frba.dds.controllers;

import static java.lang.Thread.sleep;

import ar.edu.utn.frba.dds.models.Servicio;
import ar.edu.utn.frba.dds.models.comunidades.CargoComunidad;
import ar.edu.utn.frba.dds.models.comunidades.Comunidad;
import ar.edu.utn.frba.dds.models.comunidades.Membresia;
import ar.edu.utn.frba.dds.models.comunidades.RolComunidad;
import ar.edu.utn.frba.dds.servicio.fusionarcomunidadesapi.FusionComunidades;
import ar.edu.utn.frba.dds.servicio.fusionarcomunidadesapi.entidades.ComunidadDto;
import ar.edu.utn.frba.dds.servicio.fusionarcomunidadesapi.entidades.PropuestaDeFusionDto;
import ar.edu.utn.frba.dds.servicio.fusionarcomunidadesapi.entidades.ServicioParticularObservadoDto;
import ar.edu.utn.frba.dds.models.usuarios.Persona;
import ar.edu.utn.frba.dds.persistencia.repositorios.RepositorioComunidades;
import ar.edu.utn.frba.dds.persistencia.repositorios.RepositorioIncidentes;
import ar.edu.utn.frba.dds.persistencia.repositorios.RepositorioMembresias;
import ar.edu.utn.frba.dds.persistencia.repositorios.RepositorioPersonas;
import ar.edu.utn.frba.dds.persistencia.repositorios.RepositorioServicios;
import ar.edu.utn.frba.dds.server.utils.IcrudViewsHandler;
import ar.edu.utn.frba.dds.servicio.fusionarcomunidadesapi.IFusionComunidades;
import io.javalin.http.Context;
import io.javalin.http.HttpStatus;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ComunidadesController extends Controller implements IcrudViewsHandler {

  @Override
  public void index(Context context) {
    long id = context.sessionAttribute("id");
    List<Membresia> membresias = RepositorioMembresias.getInstance().getComunidadesDePersona(id);
    ArrayList<Comunidad> comunidades = new ArrayList<>();

    for (Membresia m : membresias) {
      comunidades.add(m.getComunidad());
    }

    Map<String, Object> model = new HashMap<>();
    model.put("comunidades", comunidades);

    context.render("comunidades/comunidades.hbs", model);
  }

  @Override
  public void show(Context context) {
    RepositorioComunidades.getInstance().clean();
    Comunidad comunidad = RepositorioComunidades.getInstance()
        .get(Long.parseLong(context.pathParam("id")));
    Map<String, Object> model = new HashMap<>();
    model.put("comunidad", comunidad);

    ArrayList<Servicio> serviciosSinIncidentes = new ArrayList<>();
    ArrayList<Servicio> serviciosConIncidentes = new ArrayList<>();
    for (Servicio s : comunidad.getServiciosDeInteres()) {
      if (RepositorioIncidentes.getInstance().hayIncidentesActivosEnServicioDeComunidad(s.getId(),
          comunidad.getId())) {
        serviciosConIncidentes.add(RepositorioServicios.getInstance().get(s.getId()));
      } else {
        serviciosSinIncidentes.add(RepositorioServicios.getInstance().get(s.getId()));
      }
    }
    model.put("serviciosSinIncidentes", serviciosSinIncidentes);
    model.put("serviciosConIncidentes", serviciosConIncidentes);

    Persona user = RepositorioPersonas.getInstance().get(context.sessionAttribute("id"));
    Boolean esAdmin = user.esAdmin(comunidad);
    model.put("admin", esAdmin);

    Membresia membresia = RepositorioMembresias.getInstance()
        .membresiaDePersonaEnComunidad(context.sessionAttribute("id"), comunidad.getId());
    model.put("membresia", membresia);

    //EntityManagerHelper.closeEntityManager();
    context.render("servicios/servicios.hbs", model);
  }

  @Override
  public void create(Context context) {
    context.render("comunidades/crear_comunidad.hbs");
  }

  @Override
  public void save(Context context) {
    Comunidad comunidadNueva = new Comunidad(context.formParam("nombreComunidad"));
    long id = context.sessionAttribute("id");
    RepositorioComunidades.getInstance().add(comunidadNueva);
    Persona user = RepositorioPersonas.getInstance().get(id);
    user.darseAltaComunidadCreada(comunidadNueva);
    RepositorioPersonas.getInstance().update(user);

    //EntityManagerHelper.closeEntityManager();
    context.status(HttpStatus.CREATED);
    context.redirect("/comunidades");
  }

  @Override
  public void edit(Context context) {
    RepositorioMembresias.getInstance().clean();
    Map<String, Object> model = new HashMap<>();
    Comunidad comunidad = RepositorioComunidades.getInstance()
        .get(Long.parseLong(context.pathParam("id")));
    List membresias = RepositorioMembresias.getInstance().membresiasDeComunidad(comunidad);


    model.put("comunidad", comunidad);
    model.put("membresias", membresias);
    model.put("cargos", CargoComunidad.values());

    //EntityManagerHelper.closeEntityManager();
    context.render("comunidades/editar_comunidad.hbs", model);
  }

  public void eliminarMiembro(Context context) {
    Membresia membresia = RepositorioMembresias.getInstance()
        .membresiaDePersonaEnComunidad(Long.parseLong(context.formParam("idMiembro")),
            Long.parseLong(context.pathParam("id")));

    membresia.getMiembro().darseBajaComunidad(membresia);
    RepositorioPersonas.getInstance().update(membresia.getMiembro());
    RepositorioMembresias.getInstance().delete(membresia);

    //EntityManagerHelper.closeEntityManager();
    context.status(HttpStatus.OK);
    context.redirect("/comunidades/".concat(context.pathParam("id")).concat("/editar"));
  }

  public void editarCargoMiembro(Context context) {
    Membresia membresia = RepositorioMembresias.getInstance()
        .membresiaDePersonaEnComunidad(Long.parseLong(context.formParam("idMiembro")),
            Long.parseLong(context.pathParam("id")));

    membresia.cambiarCargo(CargoComunidad.valueOf(context.formParam("cargoM")));
    RepositorioMembresias.getInstance().update(membresia);

    //EntityManagerHelper.closeEntityManager();
    context.status(HttpStatus.OK);
    context.redirect("/comunidades/".concat(context.pathParam("id")).concat("/editar"));
  }

  public void editarComunidad(Context context) {
    Comunidad comunidad = RepositorioComunidades.getInstance()
        .get(Long.parseLong(context.pathParam("id")));
    comunidad.setNombreComunidad(context.formParam("nombre"));

    RepositorioComunidades.getInstance().update(comunidad);

    //EntityManagerHelper.closeEntityManager();
    context.redirect("/comunidades/".concat(context.pathParam("id")).concat("/servicios"));
  }

  public void editarRolMiembro(Context context) {
    long idPersona = context.sessionAttribute("id");
    RepositorioMembresias.getInstance().clean();
    Membresia membresia = RepositorioMembresias.getInstance()
        .membresiaDePersonaEnComunidad(idPersona, Long.parseLong(context.pathParam("id")));
    membresia.cambiarRol();

    RepositorioMembresias.getInstance().update(membresia);

    //EntityManagerHelper.closeEntityManager();
    context.redirect("/comunidades/".concat(context.pathParam("id")).concat("/servicios"));
  }

  public void obtenerComunidades(Context context) {
    RepositorioComunidades.getInstance().clean();
    List<Comunidad> comunidades = RepositorioComunidades.getInstance().all();

    List<ComunidadDto> comunidadesDto = comunidades.stream()
        .map(Comunidad::toDto)
        .collect(Collectors.toList());

    context.status(HttpStatus.OK);
    context.json(comunidadesDto);
  }


  public void unirse(Context context) {
    Map<String, Object> model = new HashMap<>();
    List<Comunidad> comunidadesQueNoFormaParte = RepositorioComunidades.getInstance()
        .all().stream().filter(comunidad ->
            !comunidad.personaFormaParteDeLaComunidad(RepositorioPersonas.getInstance()
                .get(context.sessionAttribute("id")))).toList();
    model.put("comunidades", comunidadesQueNoFormaParte);
    model.put("roles", RolComunidad.values());

    //EntityManagerHelper.closeEntityManager();
    context.render("comunidades/unirse_comunidad.hbs", model);
  }

  @Override
  public void update(Context context) {
    Comunidad comunidad = RepositorioComunidades.getInstance()
        .get(Long.parseLong(context.formParam("comunidad")));
    Persona persona = RepositorioPersonas.getInstance()
        .get(context.sessionAttribute("id"));
    persona.darseAltaComunidad(comunidad, RolComunidad.valueOf(context.formParam("rol")));

    RepositorioComunidades.getInstance().update(comunidad);
    RepositorioPersonas.getInstance().update(persona);

    //EntityManagerHelper.closeEntityManager();
    context.status(HttpStatus.OK);
    context.redirect("/comunidades");
  }

  public void agregarServicio(Context context) {
    Comunidad comunidad = RepositorioComunidades.getInstance()
        .get(Long.parseLong(context.pathParam("id")));
    Servicio servicio = RepositorioServicios.getInstance()
        .get(Long.parseLong(context.formParam("idServicio")));
    comunidad.agregarServicioDeInteres(servicio);

    RepositorioComunidades.getInstance().update(comunidad);

    //EntityManagerHelper.closeEntityManager();
    context.status(HttpStatus.OK);
    context.redirect("/comunidades/".concat(context.pathParam("id")).concat("/servicios"));
  }

  public void fusionarComunidades(Context context) {
    try {
      // Convertir el cuerpo del request a un objeto PropuestaDeFusionDTO
      //PropuestaDeFusionDTO propuestaDTO = context.bodyAsClass(PropuestaDeFusionDTO.class);

      Comunidad una = RepositorioComunidades.getInstance()
          .get(Long.parseLong(context.formParam("idComunidad1")));
      Comunidad otra = RepositorioComunidades.getInstance()
          .get(Long.parseLong(context.formParam("idComunidad2")));
      PropuestaDeFusionDto propuestaDeFusionDto = new PropuestaDeFusionDto(
          new ComunidadDto((int) una.getId(), una.getNombreComunidad(),
              una.getServiciosDeInteres(), una.getGradoDeConfianza()),
          new ComunidadDto((int) otra.getId(), otra.getNombreComunidad(),
              otra.getServiciosDeInteres(), otra.getGradoDeConfianza())
      );

      // Crear una instancia de Retrofit
      Retrofit retrofit = new Retrofit.Builder()
          .baseUrl("https://api-fusion-de-comunidades.onrender.com")
          .addConverterFactory(GsonConverterFactory.create())
          .build();

      // Usar la instancia para hacer una llamada POST a /fusionarcomunidades
      IFusionComunidades service = retrofit.create(IFusionComunidades.class);
      retrofit2.Response<ComunidadDto> response =
          service.fusionarComunidades(propuestaDeFusionDto).execute();
      sleep(300);
      if (response.code() == HttpStatus.OK.getCode()) {
        ComunidadDto resultado = new ComunidadDto(response.body());

        Comunidad fusionada = new Comunidad(resultado.getNombre());
        fusionada.setGradoDeConfianza(resultado.getGradoDeConfianza());
        for (ServicioParticularObservadoDto s : resultado.getServiciosParticularesObservados()) {
          fusionada.agregarServicioDeInteres(RepositorioServicios.getInstance()
              .get(s.getServicio().getId()));
        }
        Set<Persona> todos = new HashSet<>();
        List<Persona> deUnaComunidad = RepositorioComunidades.getInstance()
            .get(Long.parseLong(context.formParam("idComunidad1")))
            .getMiembros().stream().map(Membresia::getMiembro).toList();
        List<Persona> deOtraComunidad = RepositorioComunidades.getInstance()
            .get(Long.parseLong(context.formParam("idComunidad2")))
            .getMiembros().stream().map(Membresia::getMiembro).toList();
        todos.addAll(deUnaComunidad);
        todos.addAll(deOtraComunidad);

        for (Persona p : todos) {
          p.darseAltaComunidadFusionada(fusionada,
              RolComunidad.AFECTADO, CargoComunidad.ADMINISTRADOR);
          //RepositorioPersonas.getInstance().update(m.getMiembro());
        }
        List<Membresia> memDeC1 = RepositorioMembresias.getInstance().membresiasDeComunidad(una);
        for (Membresia m : memDeC1) {
          m.getMiembro().darseBajaComunidad(m);
          RepositorioMembresias.getInstance().delete(m);
        }
        List<Membresia> memDeC2 = RepositorioMembresias.getInstance().membresiasDeComunidad(otra);
        for (Membresia m : memDeC2) {
          m.getMiembro().darseBajaComunidad(m);
          RepositorioMembresias.getInstance().delete(m);
        }

        RepositorioComunidades.getInstance().add(fusionada);

        context.redirect("/obtenerPosiblesFusiones");
        RepositorioComunidades.getInstance().delete(una);
        RepositorioComunidades.getInstance().delete(otra);
        //context.status(200).result(resultado);
      } else {
        context.status(response.code()).result("Error al fusionar comunidades");

      }
    } catch (Exception e) {
      e.printStackTrace();
      context.status(500).result("Error interno del servidor");
    }
  }

  public void obtenerPosiblesFusiones(Context context) throws IOException, InterruptedException {
    ArrayList<PropuestaDeFusionDto> listadoAmostrar = FusionComunidades.getInstancia().obtenerPosiblesFusiones();

    if (!listadoAmostrar.isEmpty()) {
      Map<String, Object> model = new HashMap<>();
      model.put("listado", listadoAmostrar);

      context.render("comunidades/comunidades_fusion.hbs", model);
    } else {
      context.render("comunidades/error_fusion.hbs");
      //context.status(response.code()).result("Error al obtener propuestas de fusión");
    }
  }
    @Override
    public void delete (Context context){

    }
  }
