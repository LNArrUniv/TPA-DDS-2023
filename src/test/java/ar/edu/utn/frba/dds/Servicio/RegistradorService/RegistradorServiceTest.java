package ar.edu.utn.frba.dds.Servicio.RegistradorService;

import ar.edu.utn.frba.dds.Modelos.EntidadPropietaria;
import ar.edu.utn.frba.dds.Modelos.OrganismoDeControl;
import ar.edu.utn.frba.dds.Servicio.RegistradorEmpresasService;
import com.opencsv.exceptions.CsvValidationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class RegistradorServiceTest {
  private static RegistradorEmpresasService registrador;
  @BeforeAll
  public static void init() throws CsvValidationException, IOException {
    String archivoCSV = "src/main/Archivos/ServiciosPrestadoresYOrganismosDeControl.csv";
    registrador = new RegistradorEmpresasService(archivoCSV);

    registrador.obtenerEntidadesPrestadoras();
    registrador.obtenerOrganismosControl();
  }
  @Test
  public void seCarganCorrectamenteLasEntidadesPrestadoras() {
    System.out.println("\n----------------Entidades Prestadoras----------------");
    for (EntidadPropietaria entidad : registrador.getEntidadesPrestadoras()){
      System.out.println(entidad.getNombre().concat(": ").concat(entidad.getDescripcion()));
    }

    Assertions.assertEquals(8, registrador.getEntidadesPrestadoras().size());
  }

  @Test
  public void seCarganCorrectamenteLosOrganismosDeControl() {
    System.out.println("----------------Organismos de Control----------------");
    for (OrganismoDeControl organismo : registrador.getOrganismosDeControl()){
      System.out.println(organismo.getNombre());
    }

    Assertions.assertEquals(8, registrador.getOrganismosDeControl().size());
  }
}
