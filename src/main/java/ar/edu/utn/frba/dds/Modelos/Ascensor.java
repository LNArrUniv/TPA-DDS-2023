package ar.edu.utn.frba.dds.Modelos;

public class Ascensor extends MedioDeElevacion{
  private int capacidad;

  public Ascensor(String nombre, int tramoCalleHastaAcceso, int tramoAccesoHastaAnden, int capacidad) {
    super(nombre, tramoCalleHastaAcceso, tramoAccesoHastaAnden);
    this.capacidad = capacidad;
  }

  @Override
  public void usar() {

  }
}
