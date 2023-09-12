package ar.edu.utn.frba.dds.Modelos;

import ar.edu.utn.frba.dds.Persistencia.EntidadPersistente;
import lombok.Getter;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "membresia")
public class Membresia extends EntidadPersistente {
  @Getter
  @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
  private Comunidad comunidad;
  @Getter
  @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
  private Persona miembro;
  @Enumerated(EnumType.STRING)
  private Rol tipoDeUsuario;
  @Enumerated(EnumType.STRING)
  private CargoComunidad cargoDentroDeComunidad;


  public Membresia(Comunidad comunidad, Rol tipoDeUsuario, Persona miembro, CargoComunidad cargoComunidad) {
    this.comunidad = comunidad;
    this.tipoDeUsuario = tipoDeUsuario;
    this.miembro = miembro;
    this.cargoDentroDeComunidad = cargoComunidad;
  }

  public Membresia() {

  }

  public void cambiarRol(){
    if (tipoDeUsuario.equals(Rol.AFECTADO)){
      this.tipoDeUsuario = Rol.OBSERVADOR;
    } else {
      this.tipoDeUsuario = Rol.AFECTADO;
    }
  }

  public void cambiarCargo(){
    if (cargoDentroDeComunidad.equals(CargoComunidad.MIEMBRO)){
      this.cargoDentroDeComunidad = CargoComunidad.ADMINISTRADOR;
    } else {
      this.cargoDentroDeComunidad = CargoComunidad.MIEMBRO;
    }
  }

}
