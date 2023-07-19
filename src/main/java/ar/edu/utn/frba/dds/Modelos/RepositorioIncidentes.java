package ar.edu.utn.frba.dds.Modelos;

import java.util.ArrayList;

public class RepositorioIncidentes {
  private static RepositorioIncidentes instance;
  private ArrayList<Incidente> activos;
  private ArrayList<Incidente> resueltos;

  private RepositorioIncidentes() {
  }

  public static RepositorioIncidentes getInstance(){
    if(instance.equals(null)){
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
}
