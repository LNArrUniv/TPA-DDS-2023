package ar.edu.utn.frba.dds.Modelos;

import ar.edu.utn.frba.dds.Modelos.UbicacionDTO.Localidad;
import lombok.Setter;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class RepositorioIncidentes {
  private static RepositorioIncidentes instance = null;
  @Setter
  private ArrayList<Incidente> activos = new ArrayList<>();
  private ArrayList<Incidente> resueltos = new ArrayList<>();

  private RepositorioIncidentes() {
  }

  public static RepositorioIncidentes getInstance(){
    if(instance == null){
      instance = new RepositorioIncidentes();
    }
    return instance;
  }

  public ArrayList<Incidente> getActivos() {
    return activos;
  }

  public ArrayList<Incidente> getResueltos() {
    return resueltos;
  }

  public ArrayList<Incidente> incidentesEnUbicacion(Localidad ubicacion){
    return (ArrayList<Incidente>) getActivos().stream().filter(incidente -> incidente.getServicio().getEstablecimiento().getUbicacion().getId() == ubicacion.getId()).collect(Collectors.toList());
  }
}
