package ar.edu.utn.frba.dds;

import ar.edu.utn.frba.dds.Modelos.Comunidades.Comunidad;
import ar.edu.utn.frba.dds.Modelos.Servicio;
import ar.edu.utn.frba.dds.Persistencia.repositorios.RepositorioComunidades;
import ar.edu.utn.frba.dds.Persistencia.repositorios.RepositorioIncidentes;
import ar.edu.utn.frba.dds.Persistencia.repositorios.RepositorioServicios;

public class MainDeploy {
  public static void main(String[] args) {
    Servicio servicio = RepositorioServicios.getInstance().all().get(0);
    Comunidad comunidad = RepositorioComunidades.getInstance().all().get(0);

    Boolean prueba = RepositorioIncidentes.getInstance().hayIncidentesActivosEnServicioDeComunidad(servicio.getId(), comunidad.getId());
    System.out.println(prueba);

  }
}
