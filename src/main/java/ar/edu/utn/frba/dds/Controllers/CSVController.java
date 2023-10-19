package ar.edu.utn.frba.dds.Controllers;

import ar.edu.utn.frba.dds.Modelos.Comunidades.Comunidad;
import ar.edu.utn.frba.dds.Modelos.Comunidades.Membresia;
import ar.edu.utn.frba.dds.Modelos.Entidad;
import ar.edu.utn.frba.dds.Persistencia.repositorios.RepositorioEntidades;
import ar.edu.utn.frba.dds.Persistencia.repositorios.RepositorioMembresias;
import ar.edu.utn.frba.dds.Server.Utils.ICrudViewsHandler;
import io.javalin.http.Context;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CSVController extends Controller implements ICrudViewsHandler {
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
  /*
  @Override
  public void index(Context context){
    // TODO: Revisar porque hice copy paste
    long id = context.sessionAttribute("id");
    List<Entidad> entidad = RepositorioEntidades.getInstance().getEntidad(id);
    ArrayList<Comunidad> comunidades = new ArrayList<Comunidad>();

    for (Entidad e:entidad) {
      entidad.add(e.getEntidad());
    }

    Map<String, Object> model = new HashMap<>();
    model.put("entidades", entidades);
    context.render("csv.hbs", model);
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
  public void edit(Context context){

  }

  @Override
  public void update(Context context) {

  }

  @Override
  public void delete(Context context) {

  }
  */
}
