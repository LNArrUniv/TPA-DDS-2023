package ar.edu.utn.frba.dds.Controllers;

import ar.edu.utn.frba.dds.Modelos.Comunidades.Comunidad;
import ar.edu.utn.frba.dds.Modelos.Comunidades.Membresia;
import ar.edu.utn.frba.dds.Modelos.Incidente;
import ar.edu.utn.frba.dds.Modelos.Servicio;
import ar.edu.utn.frba.dds.Persistencia.repositorios.RepositorioIncidentes;
import ar.edu.utn.frba.dds.Persistencia.repositorios.RepositorioMembresias;
import ar.edu.utn.frba.dds.Persistencia.repositorios.RepositorioServicios;
import ar.edu.utn.frba.dds.Server.Utils.ICrudViewsHandler;
import io.javalin.http.Context;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ServiciosController extends Controller implements ICrudViewsHandler {

  @Override
  public void index(Context context) {
    List servicios = RepositorioServicios.getInstance().all();
    Map<String, Object> model = new HashMap<>();
    model.put("servicios", servicios);
    model.put("comunidad", context.pathParam("id"));

    context.render("agregar_servicio.hbs", model);
  }

  @Override
  public void show(Context context) {
    List<Incidente> incidentes = RepositorioIncidentes.getInstance().incidentesDeServicioYComunidad(Long.parseLong(context.pathParam("idServicio")), Long.parseLong(context.pathParam("id")));
    List<Incidente> incidentesResueltos = incidentes.stream().filter(incidente -> incidente.getEstaResuelto() == true).collect(Collectors.toList());
    List<Incidente> incidentesAbiertos = incidentes.stream().filter(incidente -> incidente.getEstaResuelto() == false).collect(Collectors.toList());
    Servicio servicio = RepositorioServicios.getInstance().get(Long.parseLong(context.pathParam("idServicio")));

    Map<String, Object> model = new HashMap<>();
    model.put("servicio", servicio);
    model.put("comunidad", context.pathParam("id"));
    model.put("incidentesAbiertos", incidentesAbiertos);
    model.put("incidentesResueltos", incidentesResueltos);

    context.render("incidentes.hbs", model);
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
