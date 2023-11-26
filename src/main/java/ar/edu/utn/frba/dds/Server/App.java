package ar.edu.utn.frba.dds.Server;

import ar.edu.utn.frba.dds.CargadorDatos;
import ar.edu.utn.frba.dds.Modelos.Rankings.RankingIncidentes;
import ar.edu.utn.frba.dds.Persistencia.repositorios.RepositorioEntidadPropietarias;
import ar.edu.utn.frba.dds.Persistencia.repositorios.RepositorioEstablecimiento;
import ar.edu.utn.frba.dds.Persistencia.repositorios.RepositorioRankings;
import ar.edu.utn.frba.dds.Persistencia.repositorios.RepositoriosItemsRankings;

public class App {

    public static void main(String[] args) {
/*
        if (RepositorioEntidadPropietarias.getInstance().all().isEmpty()) {
            CargadorDatos cd = new CargadorDatos();
            try {
                cd.cargarDatos();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        if(RepositorioRankings.getInstance().all().isEmpty()){
            RankingIncidentes.getInstance().generarRankings();
        }
*/
      Server.init();
    }
}
