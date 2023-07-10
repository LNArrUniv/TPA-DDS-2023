package ar.edu.utn.frba.dds.Modelos;

import java.util.ArrayList;

public class Persona extends Usuario {

  private ArrayList<Entidad> entidadesDeInteres;
  private ArrayList<Servicio> serviciosDeInteres;
  private Localizacion localizacion;
  private ArrayList<Comunidad> comunidades;
  private MedioDeContacto contacto;

  public Persona(Localizacion localizacion) {
    this.entidadesDeInteres = new ArrayList<Entidad>();
    this.serviciosDeInteres = new ArrayList<Servicio>();
    this.localizacion = localizacion;
    this.comunidades = new ArrayList<Comunidad>();
  }

  public void darseAltaComunidad(Comunidad comunidad) {
    this.comunidades.add(comunidad);
  }

  public void darseBajaComunidad(Comunidad comunidad) {
    this.comunidades.remove(comunidad);
  }

  public void notificar(Notificacion notificacion) {
    //TODO
  }
}
