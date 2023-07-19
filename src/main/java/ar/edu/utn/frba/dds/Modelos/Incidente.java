package ar.edu.utn.frba.dds.Modelos;

import ar.edu.utn.frba.dds.Modelos.Ubicacion.Localidad;
import lombok.Getter;
import java.time.LocalDateTime;

public class Incidente {
    @Getter
    private String descripcion;
    private LocalDateTime fechaHoraApertura;
    @Getter
    private Persona informante;
    private Boolean resuelto;
    private LocalDateTime fechaHoraCierre;
    @Getter
    private Servicio servicio;
    @Getter
    private Localidad ubicacion;

    public Incidente(String descripcion, Persona informante, Servicio servicio) {
        this.descripcion = descripcion;
        this.fechaHoraApertura = LocalDateTime.now();
        this.informante = informante;
        this.servicio = servicio;
        this.ubicacion = servicio.getUbicacion();
    }

    public void marcarComoResuelto(){
        this.fechaHoraCierre = LocalDateTime.now();
        this.resuelto = true;
    }

    public LocalDateTime getFechaHoraApertura() {
        return fechaHoraApertura;
    }

    public LocalDateTime getFechaHoraCierre() {
        return fechaHoraCierre;
    }
}
