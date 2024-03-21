package ar.edu.utn.frba.dds.controllers;

import ar.edu.utn.frba.dds.models.usuarios.PersonaDesignada;
import ar.edu.utn.frba.dds.persistencia.repositorios.RepositorioPersonasDesignadas;
import ar.edu.utn.frba.dds.seguridad.ValidadorPassword;
import ar.edu.utn.frba.dds.seguridad.filtros.ControlPasswordDebil;
import ar.edu.utn.frba.dds.server.utils.IcrudViewsHandler;
import io.javalin.http.Context;
import io.javalin.http.HttpStatus;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class PersonasDesignadasController extends Controller implements IcrudViewsHandler {
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
    PersonaDesignada persona = RepositorioPersonasDesignadas.getInstance()
        .get(context.sessionAttribute("id"));

    model.put("user", persona);

    context.render("personas/editar_perfil_designada.hbs", model);
  }

  @Override
  public void update(Context context) {
    ValidadorPassword validador = new ValidadorPassword();
    validador.addFiltro(new ControlPasswordDebil());

    PersonaDesignada persona = RepositorioPersonasDesignadas.getInstance()
        .get(context.sessionAttribute("id"));

    Boolean contraseniaValida = validador.validarPassword(context.formParam("password"));

    Boolean usernameValido = !RepositorioPersonasDesignadas.getInstance()
        .usuarioYaExiste(context.formParam("username"))
        || context.formParam("username").equals(persona.getUsuario().getUsername());

    if (contraseniaValida && usernameValido) {

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
      model.put("user", RepositorioPersonasDesignadas.getInstance()
          .get(context.sessionAttribute("id")));

      context.render("personas/editar_perfil_designada.hbs", model);
    }
  }

  @Override
  public void delete(Context context) {

  }
}