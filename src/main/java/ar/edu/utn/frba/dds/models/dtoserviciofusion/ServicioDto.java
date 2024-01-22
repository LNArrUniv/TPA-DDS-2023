package ar.edu.utn.frba.dds.models.dtoserviciofusion;

public class ServicioDto {
  private int id;
  private String nombre;

  public ServicioDto() {
  }

  public ServicioDto(int id, String nombre) {
    this.id = id;
    this.nombre = nombre;
  }

  public int getId() {
    return id;
  }

  public String getNombre() {
    return nombre;
  }
}
