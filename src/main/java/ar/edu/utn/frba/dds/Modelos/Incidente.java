package ar.edu.utn.frba.dds.Modelos;

import java.time.LocalDateTime;

public class Incidente {
    private String descripcion;
    private LocalDateTime fechaHoraApertura;
    private Persona informante;
    private Boolean resuelto;
    private LocalDateTime fechaHoraCierre;

    public Persona getInformante() {
        return informante;
    }

    public String getDescripcion() {
        return descripcion;
    }
}
