package ar.edu.utn.frba.dds.Modelos;
import java.util.List;

public class Comunidad {
  private String nombreComunidad;
  private Servicio problematicaDeInteres;
  private List<Usuario> miembros;
  private List<Usuario> administradores;

  public void agregarMiembro(Usuario usuario){
    this.miembros.remove(usuario);
  }
  public void eleminarMiembro(Usuario usuario) {
    this.miembros.remove(usuario);
  }
  public void relevarServicio(Servicio servicio, Estacion estacion){
    estacion.modificarServicio(servicio);
  }
}