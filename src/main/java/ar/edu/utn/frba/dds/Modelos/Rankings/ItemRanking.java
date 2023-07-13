package ar.edu.utn.frba.dds.Modelos.Rankings;

import ar.edu.utn.frba.dds.Modelos.Entidad;

public class ItemRanking {

    private Entidad entidad;
    private Double valorParametro;

    public ItemRanking(Entidad entidad, Double valorParametro) {
        this.entidad = entidad;
        this.valorParametro = valorParametro;
    }

    public void setEntidad(Entidad entidad) {
        this.entidad = entidad;
    }

    public void setValorParametro(Double valorParametro) {
        this.valorParametro = valorParametro;
    }
}
