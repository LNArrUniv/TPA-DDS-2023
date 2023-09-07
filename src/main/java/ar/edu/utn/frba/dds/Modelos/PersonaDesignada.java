package ar.edu.utn.frba.dds.Modelos;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "persona_designada")
public class PersonaDesignada extends Usuario {
  public PersonaDesignada(String nombre, String apellido, String username, String contrasenia) {
    super(nombre, apellido, username, contrasenia);
  }

  public PersonaDesignada() {

  }
}
