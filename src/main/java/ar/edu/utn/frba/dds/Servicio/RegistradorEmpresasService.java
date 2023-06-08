package ar.edu.utn.frba.dds.Servicio;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RegistradorEmpresasService {

    private static String ArchivoCSV;
    public List<String> entidadesPrestadoras = new ArrayList<>();
    public List<String> organismosDeControl = new ArrayList<>();

    public RegistradorEmpresasService(String archivoCSV) {
        ArchivoCSV = archivoCSV;
    }

    public List<String> obtenerEntidadesPrestadoras() throws IOException,
                                                                    CsvValidationException{
        CSVReader reader = new CSVReader(new FileReader(ArchivoCSV));
        reader.readNext(); // Leer la primera línea y descartarla (encabezados)
        String[] campos;
        while ((campos = reader.readNext()) != null) {
            entidadesPrestadoras.add(campos[1]);
        }
        reader.close();
        return entidadesPrestadoras;
    }

    public List<String> obtenerOrganismosControl() throws IOException,
                                                                 CsvValidationException{
        CSVReader reader = new CSVReader(new FileReader(ArchivoCSV));
        reader.readNext(); // Leer la primera línea y descartarla (encabezados)
        String[] campos;
        while ((campos = reader.readNext()) != null) {
            organismosDeControl.add(campos[3]);
        }
        reader.close();
        return organismosDeControl;
    }
}

