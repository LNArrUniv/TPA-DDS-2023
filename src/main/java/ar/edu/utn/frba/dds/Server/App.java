package ar.edu.utn.frba.dds.Server;

import ar.edu.utn.frba.dds.Modelos.Rankings.RankingIncidentes;

public class App {

    public static void main(String[] args) {
        RankingIncidentes.getInstance().generarRankings();

        Server.init();
    }
}
