package ar.edu.utn.frba.dds;

import ar.edu.utn.frba.dds.Modelos.Comunidades.Comunidad;
import ar.edu.utn.frba.dds.Modelos.Rankings.RankingIncidentes;
import ar.edu.utn.frba.dds.Modelos.Servicio;
import ar.edu.utn.frba.dds.Persistencia.repositorios.RepositorioComunidades;
import ar.edu.utn.frba.dds.Persistencia.repositorios.RepositorioIncidentes;
import ar.edu.utn.frba.dds.Persistencia.repositorios.RepositorioServicios;
import org.checkerframework.checker.units.qual.C;

public class MainDeploy {
  public static void main(String[] args) throws Exception {
    CargadorDatos cd = new CargadorDatos();
    cd.cargarDatos();

    //RankingIncidentes.getInstance().generarRankings();

  }
}
