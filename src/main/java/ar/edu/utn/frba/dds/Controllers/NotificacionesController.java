package ar.edu.utn.frba.dds.Controllers;

import ar.edu.utn.frba.dds.Server.Utils.ICrudViewsHandler;
import io.javalin.http.Context;

import java.util.HashMap;
import java.util.Map;

public class NotificacionesController extends Controller implements ICrudViewsHandler {

  @Override
  public void index(Context context) {
    Map<String, Object> model = new HashMap<>();
    context.render("notificaciones.hbs",model);
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
}
