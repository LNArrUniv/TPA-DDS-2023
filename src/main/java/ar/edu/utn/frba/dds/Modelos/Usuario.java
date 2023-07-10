package ar.edu.utn.frba.dds.Modelos;

public abstract class Usuario {
  private String nombre;
  private String apellido;
  private String username;
  private String contrasenia;

  protected Usuario(String nombre, String apellido, String username, String contrasenia) {
    this.nombre = nombre;
    this.apellido = apellido;
    this.username = username;
    this.contrasenia = contrasenia;
  }

  public String getUsername() {
    return username;
  }
}