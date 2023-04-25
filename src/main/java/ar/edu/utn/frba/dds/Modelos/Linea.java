package ar.edu.utn.frba.dds.Modelos;

import java.util.List;

public class Linea {
  private String nombre;

  private TipoTransporte tipo;
  private String estacionDeOrigen;
  private String estacionDeDestino;
  private List<Estacion> listaDeEstaciones;

  public void agregarEstacion(Estacion estacion){
    this.listaDeEstaciones.add(estacion);
  }
  public void eliminarEstacion(Estacion estacion){
    this.listaDeEstaciones.remove(estacion);
  }
  public List<Estacion> getListaDeEstaciones(){
    return this.listaDeEstaciones;
  }

}