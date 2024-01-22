package ar.edu.utn.frba.dds.servicio;

import ar.edu.utn.frba.dds.models.EntidadPropietaria;
import ar.edu.utn.frba.dds.models.OrganismoDeControl;
import ar.edu.utn.frba.dds.persistencia.repositorios.RepositorioOrganismoDeControl;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RegistradorEmpresasService {

  private static String ArchivoCsv;
  private List<EntidadPropietaria> entidadesPrestadoras = new ArrayList<>();
  private List<OrganismoDeControl> organismosDeControl = new ArrayList<>();

  public RegistradorEmpresasService(String archivoCsv) {
    ArchivoCsv = archivoCsv;
  }

  public void obtenerEntidadesPrestadoras() throws IOException, CsvValidationException {
    CSVReader reader = new CSVReader(new FileReader(ArchivoCsv));
    reader.readNext(); // Leer la primera línea y descartarla (encabezados)
    String[] campos;
    while ((campos = reader.readNext()) != null) {
      OrganismoDeControl organismo = (OrganismoDeControl) RepositorioOrganismoDeControl
          .getInstance().getOrganismoConElNombre(campos[2]).get(0);
      entidadesPrestadoras.add(new EntidadPropietaria(campos[0], campos[1], null, organismo));
    }
    reader.close();
  }

  public void obtenerOrganismosControl() throws IOException, CsvValidationException {
    CSVReader reader = new CSVReader(new FileReader(ArchivoCsv));
    reader.readNext();
    // Leer la primera línea y descartarla (encabezados)
    String[] campos;
    while ((campos = reader.readNext()) != null) {

      organismosDeControl.add(new OrganismoDeControl(campos[2], null));
    }
    reader.close();
  }

  public List<EntidadPropietaria> getEntidadesPrestadoras() {
    return entidadesPrestadoras;
  }

  public List<OrganismoDeControl> getOrganismosDeControl() {
    return organismosDeControl;
  }
}

