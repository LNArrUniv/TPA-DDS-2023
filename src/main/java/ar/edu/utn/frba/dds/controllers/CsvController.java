package ar.edu.utn.frba.dds.controllers;

import ar.edu.utn.frba.dds.models.EntidadPropietaria;
import ar.edu.utn.frba.dds.models.OrganismoDeControl;
import ar.edu.utn.frba.dds.persistencia.repositorios.RepositorioEntidadPropietarias;
import ar.edu.utn.frba.dds.persistencia.repositorios.RepositorioOrganismoDeControl;
import ar.edu.utn.frba.dds.server.utils.IcrudViewsHandler;
import ar.edu.utn.frba.dds.servicio.RegistradorEmpresasService;
import com.opencsv.exceptions.CsvValidationException;
import io.javalin.http.Context;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.io.FileUtils;

public class CsvController extends Controller implements IcrudViewsHandler {
  @Override
  public void index(Context context) {
    List<EntidadPropietaria> entidades = RepositorioEntidadPropietarias.getInstance().all();
    List<OrganismoDeControl> organismoDeControls = RepositorioOrganismoDeControl.getInstance()
        .all();

    Map<String, Object> model = new HashMap<>();
    model.put("entidades", entidades);
    model.put("organismos", organismoDeControls);
    context.render("csv.hbs", model);
  }

  @Override
  public void show(Context context) {

  }

  @Override
  public void create(Context context) {

  }

  @Override
  public void save(Context context) throws IOException, CsvValidationException {
    // Creo el archivo
    FileUtils.copyInputStreamToFile(context.uploadedFile("archivoCSV").content(),
        new File("src/main/Archivos/archivoCSV.csv"));
    // Instancio el registrador
    RegistradorEmpresasService registradorEmpresasService =
        new RegistradorEmpresasService("src/main/Archivos/archivoCSV.csv");
    // Registro los organismos que todavia no existen
    registradorEmpresasService.obtenerOrganismosControl();
    for (OrganismoDeControl oc : registradorEmpresasService.getOrganismosDeControl()) {
      if (!RepositorioOrganismoDeControl.getInstance().existeOrganismoConElNombre(oc.getNombre())) {
        RepositorioOrganismoDeControl.getInstance().add(oc);
      }
    }
    // Registro las entidades propietarias que todavia no existen
    registradorEmpresasService.obtenerEntidadesPrestadoras();
    for (EntidadPropietaria ep : registradorEmpresasService.getEntidadesPrestadoras()) {
      if (!RepositorioEntidadPropietarias.getInstance()
          .existeEntidadPropietariaConElNombre(ep.getNombre())) {
        RepositorioEntidadPropietarias.getInstance().add(ep);
      }
    }

    List<EntidadPropietaria> entidades = RepositorioEntidadPropietarias.getInstance().all();
    List<OrganismoDeControl> organismoDeControls = RepositorioOrganismoDeControl.getInstance()
        .all();
    Boolean subido = true;

    Map<String, Object> model = new HashMap<>();
    model.put("entidades", entidades);
    model.put("organismos", organismoDeControls);
    model.put("subido", subido);
    context.render("csv.hbs", model);

    context.render("csv.hbs", model);
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

