package ar.edu.utn.frba.dds.Modelos;

import java.time.LocalDateTime;

public class Incidente {
    private String descripcion;
    private LocalDateTime fechaHoraApertura;
    private Persona informante;
    private Boolean resuelto;
    private LocalDateTime fechaHoraCierre;

    public Incidente(String descripcion, Persona informante) {
        this.descripcion = descripcion;
        this.fechaHoraApertura = LocalDateTime.now();
        this.informante = informante;
    }

    public Persona getInformante() {
        return informante;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public LocalDateTime getFechaHoraApertura() {
        return fechaHoraApertura;
    }

    public LocalDateTime getFechaHoraCierre() {
        return fechaHoraCierre;
    }
}
