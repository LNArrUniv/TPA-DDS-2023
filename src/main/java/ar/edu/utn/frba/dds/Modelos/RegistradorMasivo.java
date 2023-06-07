package ar.edu.utn.frba.dds.Modelos;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RegistradorMasivo {
    public static List<String> obtenerEntidadesPrestadoras(String archivoCSV) throws IOException, CsvValidationException {

        List<String> entidadesPrestadoras = new ArrayList<>();
        CSVReader reader = new CSVReader(new FileReader(archivoCSV));
        reader.readNext(); // Leer la primera línea y descartarla (encabezados)

        String[] campos;
        while ((campos = reader.readNext()) != null) {
            entidadesPrestadoras.add(campos[1]);
        }

        reader.close();
        return entidadesPrestadoras;
    }

    public static List<String> obtenerOrganismosControl(String archivoCSV) throws IOException, CsvValidationException {
        List<String> organismosControl = new ArrayList<>();

        CSVReader reader = new CSVReader(new FileReader(archivoCSV));
        reader.readNext(); // Leer la primera línea y descartarla (encabezados)

        String[] campos;
        while ((campos = reader.readNext()) != null) {
            organismosControl.add(campos[3]);
        }

        reader.close();

        return organismosControl;
    }
}

/*
 public static void main(String[] args) {
        String archivoCSV = "src/main/Archivos/ServiciosPrestadoresYOrganismosDeControl.csv";
        try {
            List<String> entidades = obtenerEntidadesPrestadoras(archivoCSV);
            List<String> organismos = obtenerOrganismosControl(archivoCSV);

            // Hacer algo con las listas obtenidas
            System.out.println("Entidades Prestadoras: " + entidades);
            System.out.println("Organismos de Control: " + organismos);
        } catch (IOException | CsvValidationException e) {
            // Manejo de la excepción
            e.printStackTrace();
        }
    }
}
 */
