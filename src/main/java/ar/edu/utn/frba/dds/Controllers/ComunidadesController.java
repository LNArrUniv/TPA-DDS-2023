package ar.edu.utn.frba.dds.Controllers;

import ar.edu.utn.frba.dds.Modelos.Comunidades.Comunidad;
import ar.edu.utn.frba.dds.Modelos.Comunidades.Membresia;
import ar.edu.utn.frba.dds.Modelos.Servicio;
import ar.edu.utn.frba.dds.Persistencia.repositorios.RepositorioComunidades;
import ar.edu.utn.frba.dds.Persistencia.repositorios.RepositorioIncidentes;
import ar.edu.utn.frba.dds.Persistencia.repositorios.RepositorioMembresias;
import ar.edu.utn.frba.dds.Persistencia.repositorios.RepositorioServicios;
import ar.edu.utn.frba.dds.Server.Utils.ICrudViewsHandler;
import io.javalin.http.Context;
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
    context.render("comunidades.hbs", model);
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

    context.render("servicios.hbs", model);
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
