package ar.edu.utn.frba.dds.Controllers;

import ar.edu.utn.frba.dds.Persistencia.repositorios.RepositorioPersonas;
import ar.edu.utn.frba.dds.Persistencia.repositorios.RepositorioPersonasDesignadas;
import io.javalin.http.Context;
import java.util.HashMap;
import java.util.Map;

public class LoginController extends Controller {

  public void get(Context context) {
    context.render("login.hbs");
  }

  public void post(Context context){
    Map<String, Object> model = new HashMap<>();
    model.put("failed", true);
    if (RepositorioPersonas.getInstance().usuarioYContraseniaCorrectas(context.formParam("username"), context.formParam("password"))) {
      context.sessionAttribute("id", RepositorioPersonas.getInstance().getId(context.formParam("username"), context.formParam("password")));
      context.redirect("/comunidades");
    } else if (RepositorioPersonasDesignadas.getInstance().usuarioYContraseniaCorrectas(context.formParam("username"), context.formParam("password"))) {
      context.sessionAttribute("id", RepositorioPersonasDesignadas.getInstance().getId(context.formParam("username"), context.formParam("password")));
      context.redirect("/designada");
    }
    else {
      context.render("login.hbs", model);
    }
  }
}
