package ar.edu.utn.frba.dds.Controllers;

import ar.edu.utn.frba.dds.Modelos.Entidad;
import ar.edu.utn.frba.dds.Modelos.Notificaciones.ConfiguracionNotificaciones;
import ar.edu.utn.frba.dds.Modelos.Notificaciones.CuandoSuceden;
import ar.edu.utn.frba.dds.Modelos.Notificaciones.MedioDeNotificacionesPreferido;
import ar.edu.utn.frba.dds.Modelos.Notificaciones.MedioNotificacionesEmail;
import ar.edu.utn.frba.dds.Modelos.Notificaciones.MedioNotificacionesWhatsapp;
import ar.edu.utn.frba.dds.Modelos.Notificaciones.SinApuros;
import ar.edu.utn.frba.dds.Modelos.Servicio;
import ar.edu.utn.frba.dds.Modelos.Usuarios.Persona;
import ar.edu.utn.frba.dds.Modelos.Usuarios.Usuario;
import ar.edu.utn.frba.dds.Persistencia.repositorios.RepositorioEntidades;
import ar.edu.utn.frba.dds.Persistencia.repositorios.RepositorioPersonas;
import ar.edu.utn.frba.dds.Persistencia.repositorios.RepositorioServicios;
import ar.edu.utn.frba.dds.Seguridad.Filtros.ControlPasswordDebil;
import ar.edu.utn.frba.dds.Seguridad.ValidadorPassword;
import ar.edu.utn.frba.dds.Server.Utils.ICrudViewsHandler;
import io.javalin.http.Context;
import io.javalin.http.HttpStatus;
import java.time.LocalTime;
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
    context.render("registro.hbs");
  }

  @Override
  public void save(Context context) {

    ValidadorPassword validador = new ValidadorPassword();
    validador.addFiltro(new ControlPasswordDebil());

    Boolean contraseniaValida = validador.validarPassword(context.formParam("password"));

    List<String> usernames = RepositorioPersonas.getInstance().todosLosUsuarios();
    Boolean usernameValido = !usernames.contains(context.formParam("username"));

    if (contraseniaValida && usernameValido) {
      MedioDeNotificacionesPreferido medio = null;
      if (context.formParam("selectMedio").equals("1")) {
        medio = new MedioNotificacionesWhatsapp(context.formParam("celular"));
      } else if (context.formParam("selectMedio").equals("2")){
        medio = new MedioNotificacionesEmail(context.formParam("email"));
      }

      ConfiguracionNotificaciones config;
      if (context.formParam("selectConfig").equals("2")) {
        config = new SinApuros(medio);
        for (int i = 1; i <= 4; i++) {
          if (!context.formParam("horario".concat(String.valueOf(i))).isBlank()) {
            ((SinApuros) config).agregarHorario(LocalTime.parse(context.formParam("horario".concat(String.valueOf(i)))));
          }
        }
      } else {
        config = new CuandoSuceden(medio);
      }

      Usuario usuario = new Usuario(context.formParam("username"), context.formParam("password"));

      Persona nuevo_user = new Persona(context.formParam("nombre"), context.formParam("apellido"), usuario, config);
      RepositorioPersonas.getInstance().add(nuevo_user);

      context.status(HttpStatus.CREATED);
      context.redirect("/login");
    } else {
      Map<String, Object> model = new HashMap<>();

      model.put("contraseniaInvalida", !contraseniaValida);
      model.put("usernameInvalido", !usernameValido);

      context.render("personas/registro.hbs", model);
    }
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
    RepositorioPersonas.getInstance().clean();
    Persona user = RepositorioPersonas.getInstance().get(context.sessionAttribute("id"));

    model.put("serviciosInteres", user.getServiciosDeInteres());
    model.put("entidadesInteres", user.getEntidadesDeInteres());

    context.render("personas/intereses/interes.hbs", model);
  }

  public void interesSeleccionarServicio(Context context) {
    Map<String, Object> model = new HashMap<>();

    List servicios = RepositorioServicios.getInstance().all();
    Persona user = RepositorioPersonas.getInstance().get(context.sessionAttribute("id"));
    List serviciosQueLaPersonaNoTiene = servicios.stream().filter( s -> !user.getServiciosDeInteres().contains(s)).toList();

    model.put("servicios", serviciosQueLaPersonaNoTiene);

    context.render("personas/intereses/agregar_svc_interes.hbs", model);
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

    context.render("personas/intereses/agregar_entidad_interes.hbs", model);
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
