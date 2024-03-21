package ar.edu.utn.frba.dds.models.comunidades;

import ar.edu.utn.frba.dds.models.Incidente;
import ar.edu.utn.frba.dds.models.Servicio;
import ar.edu.utn.frba.dds.servicio.fusionarcomunidadesapi.entidades.ComunidadDto;
import ar.edu.utn.frba.dds.models.notificaciones.Notificacion;
import ar.edu.utn.frba.dds.models.notificaciones.NotificacionIncidenteResuelto;
import ar.edu.utn.frba.dds.models.notificaciones.NotificacionNuevoIncidente;
import ar.edu.utn.frba.dds.models.usuarios.Persona;
import ar.edu.utn.frba.dds.persistencia.EntidadPersistente;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name = "comunidad")
public class Comunidad extends EntidadPersistente {
  @Column
  @Getter
  @Setter
  private String nombreComunidad;
  @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
  @Fetch(value = FetchMode.SUBSELECT)
  @JoinColumn(name = "servicio_por_comunidad")
  @Getter
  private List<Servicio> serviciosDeInteres;
  @JsonIgnore
  @Getter
  @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE},
      mappedBy = "comunidad")
  private List<Membresia> miembros;
  @Column
  @Getter
  @Setter
  private Double gradoDeConfianza;

  public Comunidad(String nombreComunidad) {
    this.nombreComunidad = nombreComunidad;
    this.serviciosDeInteres = new ArrayList<>();
    this.miembros = new ArrayList<>();
    // Ya no es random, al ser random no matcheaba ninguna comunidad
    int randomInt = 7;
    this.gradoDeConfianza = Double.valueOf(randomInt);

  }

  public Comunidad() {

  }

  public void agregarMiembro(Membresia nuevoMiembro) {
    this.miembros.add(nuevoMiembro);
  }

  public void eliminarMiembro(Membresia miembro) {
    this.miembros.remove(miembro);
  }

  public void agregarServicioDeInteres(Servicio servicio) {
    serviciosDeInteres.add(servicio);
  }

  public int totalMiembros() {
    return miembros.size();
  }

  public int totalMiembrosAfectados() {
    return (int) miembros.stream().filter(membresia -> membresia.getTipoDeUsuario()
        .equals(RolComunidad.AFECTADO)).count();
  }

  public void informarNuevoIncidente(Incidente incidente) {
    miembros.forEach(membresia -> {
      try {
        Persona miembro = membresia.getMiembro();
        Notificacion notificacion = new NotificacionNuevoIncidente(incidente, miembro);
        miembro.notificar(notificacion);

      } catch (Exception e) {
        throw new RuntimeException(e);
      }
    });
  }

  public void informarIncidenteResuelto(Incidente incidente) {
    incidente.marcarComoResuelto();

    miembros.forEach(membresia -> {
      try {
        Persona miembro = membresia.getMiembro();
        Notificacion notificacion = new NotificacionIncidenteResuelto(incidente, miembro);
        miembro.notificar(notificacion);

      } catch (Exception e) {
        throw new RuntimeException(e);
      }
    });
  }

  public ComunidadDto toDto() {
    return new ComunidadDto(
        (int) this.getId(),
        this.getNombreComunidad(),
        this.serviciosDeInteres,
        this.getGradoDeConfianza()
    );
  }

  public Boolean personaFormaParteDeLaComunidad(Persona persona) {
    return !this.miembros.stream().filter(membresia -> membresia.getMiembro()
        .equals(persona)).toList().isEmpty();
  }
}