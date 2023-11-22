package ar.edu.utn.frba.dds.Controllers;

import ar.edu.utn.frba.dds.Modelos.Notificaciones.ConfiguracionNotificaciones;
import ar.edu.utn.frba.dds.Modelos.Notificaciones.CuandoSuceden;
import ar.edu.utn.frba.dds.Modelos.Notificaciones.MedioDeNotificacionesPreferido;
import ar.edu.utn.frba.dds.Modelos.Notificaciones.MedioNotificacionesEmail;
import ar.edu.utn.frba.dds.Modelos.Notificaciones.MedioNotificacionesWhatsapp;
import ar.edu.utn.frba.dds.Modelos.Notificaciones.SinApuros;
import ar.edu.utn.frba.dds.Modelos.UbicacionDTO.Localidad;
import ar.edu.utn.frba.dds.Modelos.UbicacionDTO.Provincia;
import ar.edu.utn.frba.dds.Modelos.Usuarios.Persona;
import ar.edu.utn.frba.dds.Modelos.Usuarios.PersonaDesignada;
import ar.edu.utn.frba.dds.Modelos.Usuarios.Rol;
import ar.edu.utn.frba.dds.Modelos.Usuarios.Usuario;
import ar.edu.utn.frba.dds.Persistencia.repositorios.RepositorioPersonas;
import ar.edu.utn.frba.dds.Persistencia.repositorios.RepositorioPersonasDesignadas;
import ar.edu.utn.frba.dds.Seguridad.Filtros.ControlPasswordDebil;
import ar.edu.utn.frba.dds.Seguridad.ValidadorPassword;
import ar.edu.utn.frba.dds.Server.Utils.ICrudViewsHandler;
import ar.edu.utn.frba.dds.Servicio.GeoRefAPIService;
import io.javalin.http.Context;
import io.javalin.http.HttpStatus;
import java.io.IOException;
import java.time.LocalTime;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class PersonasDesignadasController extends Controller implements ICrudViewsHandler {
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
  public void edit(Context context) throws IOException {
    Map<String, Object> model = new HashMap<>();
    PersonaDesignada persona = RepositorioPersonasDesignadas.getInstance().get(context.sessionAttribute("id"));

    model.put("user", persona);

    context.render("personas/editar_perfil_designada.hbs", model);
  }

  @Override
  public void update(Context context){
    ValidadorPassword validador = new ValidadorPassword();
    validador.addFiltro(new ControlPasswordDebil());

    Boolean contraseniaValida = validador.validarPassword(context.formParam("password"));

    List<String> usernames = RepositorioPersonas.getInstance().todosLosUsuarios().stream().filter(s -> !Objects.equals(s, context.formParam("username"))).toList();
    Boolean usernameValido = !usernames.contains(context.formParam("username"));

    if (contraseniaValida && usernameValido) {
      PersonaDesignada persona = RepositorioPersonasDesignadas.getInstance().get(context.sessionAttribute("id"));

      persona.setNombre(context.formParam("nombre"));
      persona.setApellido(context.formParam("apellido"));
      persona.getUsuario().setUsername(context.formParam("username"));
      persona.getUsuario().setContrasenia(context.formParam("password"));

      RepositorioPersonasDesignadas.getInstance().update(persona);

      context.status(HttpStatus.OK);
      context.redirect("/rankings");
    } else {
      Map<String, Object> model = new HashMap<>();

      model.put("contraseniaInvalida", !contraseniaValida);
      model.put("usernameInvalido", !usernameValido);
      model.put("user", RepositorioPersonasDesignadas.getInstance().get(context.sessionAttribute("id")));

      context.render("personas/editar_perfil_designada.hbs", model);
    }
  }

  @Override
  public void delete(Context context) {

  }
}