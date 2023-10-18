package ar.edu.utn.frba.dds.Controllers;

import ar.edu.utn.frba.dds.Modelos.Entidad;
import ar.edu.utn.frba.dds.Modelos.Servicio;
import ar.edu.utn.frba.dds.Modelos.Usuarios.Persona;
import ar.edu.utn.frba.dds.Modelos.Usuarios.Usuario;
import ar.edu.utn.frba.dds.Persistencia.repositorios.RepositorioEntidades;
import ar.edu.utn.frba.dds.Persistencia.repositorios.RepositorioPersonas;
import ar.edu.utn.frba.dds.Persistencia.repositorios.RepositorioServicios;
import ar.edu.utn.frba.dds.Server.Utils.ICrudViewsHandler;
import io.javalin.http.Context;
import io.javalin.http.HttpStatus;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PersonasController extends Controller implements ICrudViewsHandler {
  @Override
  public void index(Context context) {

  }

  @Override
  public void show(Context context) {

  }

  @Override
  public void create(Context context) {

  }

  @Override
  public void save(Context context) {

  }

  @Override
  public void edit(Context context) {

  }

  @Override
  public void update(Context context) {

  }

  @Override
  public void delete(Context context) {

  }

  public void interes(Context context) {
    Map<String, Object> model = new HashMap<>();

    Persona user = RepositorioPersonas.getInstance().get(context.sessionAttribute("id"));

    model.put("serviciosInteres", user.getServiciosDeInteres());
    model.put("entidadesInteres", user.getEntidadesDeInteres());

    context.render("interes.hbs", model);
  }

  public void interesSeleccionarServicio(Context context) {
    Map<String, Object> model = new HashMap<>();

    List servicios = RepositorioServicios.getInstance().all();
    Persona user = RepositorioPersonas.getInstance().get(context.sessionAttribute("id"));
    List serviciosQueLaPersonaNoTiene = servicios.stream().filter( s -> !user.getServiciosDeInteres().contains(s)).toList();

    model.put("servicios", serviciosQueLaPersonaNoTiene);

    context.render("agregar_svc_interes.hbs", model);
  }

  public void interesAgregarServicio(Context context) {
    Servicio servicio = RepositorioServicios.getInstance().get(Long.parseLong(context.formParam("idServicio")));
    Persona user = RepositorioPersonas.getInstance().get(context.sessionAttribute("id"));

    user.agregarServicioDeInteres(servicio);
    RepositorioPersonas.getInstance().update(user);

    context.status(HttpStatus.OK);
    context.redirect("/interes");
  }

  public void interesSeleccionarEntidad(Context context) {
    Map<String, Object> model = new HashMap<>();

    List entidades = RepositorioEntidades.getInstance().all();
    Persona user = RepositorioPersonas.getInstance().get(context.sessionAttribute("id"));
    List entidadesQueLaPersonaNoTiene = entidades.stream().filter( e -> !user.getEntidadesDeInteres().contains(e)).toList();

    model.put("entidades", entidadesQueLaPersonaNoTiene);

    context.render("agregar_entidad_interes.hbs", model);
  }

  public void interesAgregarEntidad(Context context) {
    Entidad entidad = RepositorioEntidades.getInstance().get(Long.parseLong(context.formParam("idEntidad")));
    Persona user = RepositorioPersonas.getInstance().get(context.sessionAttribute("id"));

    user.agregarEntidadDeInteres(entidad);
    RepositorioPersonas.getInstance().update(user);

    context.status(HttpStatus.OK);
    context.redirect("/interes");
  }
}
