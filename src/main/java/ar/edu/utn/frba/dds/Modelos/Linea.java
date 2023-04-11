package ar.edu.utn.frba.dds.Modelos;
import java.util.List;

public class Linea {
  private String nombre;
  private String estacionDeOrigen;
  private String estacionDeDestino;
  private List<String> listaDeEstaciones;

  public void setListaDeEstaciones(String valor) {
    listaDeEstaciones.add(valor);
  }
  public List<String> getListaDeEstaciones(){
    return this.listaDeEstaciones;
  }

}