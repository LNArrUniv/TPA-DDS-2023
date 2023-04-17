package ar.edu.utn.frba.dds.Modelos;

abstract public class MedioDeElevacion implements Servicio {
  private String nombre;
  private int tramoCalleHastaAcceso;
  private int tramoAccesoHastaAnden;

  public MedioDeElevacion(String nombre, int tramoCalleHastaAcceso, int tramoAccesoHastaAnden) {
    this.nombre = nombre;
    this.tramoCalleHastaAcceso = tramoCalleHastaAcceso;
    this.tramoAccesoHastaAnden = tramoAccesoHastaAnden;
  }
}
