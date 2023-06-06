package ar.edu.utn.frba.dds.modelos;

import java.util.ArrayList;
import java.util.List;

public class Linea {
  private String nombre;

  private TipoTransporte tipo;
  private String estacionDeOrigen;
  private String estacionDeDestino;
  private ArrayList<Estacion> listaDeEstaciones;

  public Linea(String nombre, TipoTransporte tipo, String estacionDeOrigen, String estacionDeDestino) {
    this.nombre = nombre;
    this.tipo = tipo;
    this.estacionDeOrigen = estacionDeOrigen;
    this.estacionDeDestino = estacionDeDestino;
    this.listaDeEstaciones = new ArrayList<Estacion>();
  }

  public void agregarEstacion(Estacion estacion) {
    this.listaDeEstaciones.add(estacion);
  }

  public void eliminarEstacion(Estacion estacion) {
    this.listaDeEstaciones.remove(estacion);
  }

  public ArrayList<Estacion> getListaDeEstaciones() {
    return this.listaDeEstaciones;
  }
  private void agregarEstaciones (ArrayList<Estacion> estaciones){
    estaciones.forEach(estacion->listaDeEstaciones.add(estacion));
  }
}