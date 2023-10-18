package ar.edu.utn.frba.dds.Controllers;

import ar.edu.utn.frba.dds.Modelos.Comunidades.CargoComunidad;
import ar.edu.utn.frba.dds.Modelos.Comunidades.Comunidad;
import ar.edu.utn.frba.dds.Modelos.Comunidades.Membresia;
import ar.edu.utn.frba.dds.Modelos.Comunidades.RolComunidad;
import ar.edu.utn.frba.dds.Modelos.Servicio;
import ar.edu.utn.frba.dds.Modelos.Usuarios.Persona;
import ar.edu.utn.frba.dds.Modelos.Usuarios.Rol;
import ar.edu.utn.frba.dds.Persistencia.repositorios.RepositorioComunidades;
import ar.edu.utn.frba.dds.Persistencia.repositorios.RepositorioIncidentes;
import ar.edu.utn.frba.dds.Persistencia.repositorios.RepositorioMembresias;
import ar.edu.utn.frba.dds.Persistencia.repositorios.RepositorioPersonas;
import ar.edu.utn.frba.dds.Persistencia.repositorios.RepositorioServicios;
import ar.edu.utn.frba.dds.Server.Utils.ICrudViewsHandler;
import io.javalin.http.Context;
import io.javalin.http.HttpStatus;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ComunidadesController extends Controller implements ICrudViewsHandler {

  @Override
  public void index(Context context) {
    long id = context.sessionAttribute("id");
    List<Membresia> membresias = RepositorioMembresias.getInstance().getComunidadesDePersona(id);
    ArrayList<Comunidad> comunidades = new ArrayList<Comunidad>();

    for (Membresia m:membresias) {
      comunidades.add(m.getComunidad());
    }

    Map<String, Object> model = new HashMap<>();
    model.put("comunidades", comunidades);
    context.render("comunidades/comunidades.hbs", model);
  }

  @Override
  public void show(Context context) {
    Comunidad comunidad = RepositorioComunidades.getInstance().get(Long.parseLong(context.pathParam("id")));
    Map<String, Object> model = new HashMap<>();
    model.put("comunidad", comunidad);

    ArrayList<Servicio> serviciosSinIncidentes = new ArrayList<>();
    ArrayList<Servicio> serviciosConIncidentes = new ArrayList<>();
    for (Servicio s:comunidad.getServiciosDeInteres()) {
      if (RepositorioIncidentes.getInstance().hayIncidentesActivosEnServicioDeComunidad(s.getId(), comunidad.getId())){
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

    context.render("servicios.hbs", model);
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

    context.status(HttpStatus.CREATED);
    context.redirect("/comunidades");
  }

  @Override
  public void edit(Context context) {

  }

  public void unirse(Context context) {
    Map<String, Object> model = new HashMap<>();
    List comunidadesQueNoFormaParte = RepositorioComunidades.getInstance().all().stream().filter(comunidad -> !comunidad.personaFormaParteDeLaComunidad(RepositorioPersonas.getInstance().get(context.sessionAttribute("id")))).toList();
    model.put("comunidades", comunidadesQueNoFormaParte);
    model.put("roles", RolComunidad.values());
    context.render("comunidades/unirse_comunidad.hbs", model);
  }

  @Override
  public void update(Context context) {
    Comunidad comunidad = RepositorioComunidades.getInstance().get(Long.parseLong(context.formParam("comunidad")));
    Persona persona = RepositorioPersonas.getInstance().get(context.sessionAttribute("id"));
    persona.darseAltaComunidad(comunidad, RolComunidad.valueOf(context.formParam("rol")));

    RepositorioComunidades.getInstance().update(comunidad);
    RepositorioPersonas.getInstance().update(persona);
    System.out.println(persona.getNombre());

    context.status(HttpStatus.OK);
    context.redirect("/comunidades");
  }

  public void agregarServicio(Context context) {
    Comunidad comunidad = RepositorioComunidades.getInstance().get(Long.parseLong(context.pathParam("id")));
    Servicio servicio = RepositorioServicios.getInstance().get(Long.parseLong(context.formParam("idServicio")));
    comunidad.agregarServicioDeInteres(servicio);

    RepositorioComunidades.getInstance().update(comunidad);

    context.status(HttpStatus.OK);
    context.redirect("/comunidades/".concat(context.pathParam("id")).concat("/servicios"));
  }

  @Override
  public void delete(Context context) {

  }
}
