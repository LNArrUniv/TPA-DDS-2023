package ar.edu.utn.frba.dds.RegistradorService;

import ar.edu.utn.frba.dds.Servicio.RegistradorEmpresasService;
import com.opencsv.exceptions.CsvValidationException;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

public class RegistradorServiceTest {
  @Test
  public static void main(String[] args) throws CsvValidationException, IOException {
    String archivoCSV = "src/main/Archivos/ServiciosPrestadoresYOrganismosDeControl.csv";
    RegistradorEmpresasService registrador = new RegistradorEmpresasService(archivoCSV);
    List<String> entidades = registrador.obtenerEntidadesPrestadoras();
    List<String> organismos = registrador.obtenerOrganismosControl();

    System.out.println("----------------Entidades Prestadoras----------------");
    for (String entidad : entidades){
      System.out.println(entidad);
    }

    System.out.println("----------------Organismos de Control----------------");
    for (String organismo : organismos){
      System.out.println(organismo);
    }

  }

}
