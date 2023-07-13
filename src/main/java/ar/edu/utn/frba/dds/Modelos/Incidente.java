package ar.edu.utn.frba.dds.Modelos;

import java.time.LocalDateTime;

public class Incidente {
    private String descripcion;
    private LocalDateTime fechaHoraApertura;
    private Persona informante;
    private Boolean resuelto;
    private LocalDateTime fechaHoraCierre;
    private Servicio servicio;
    private Localizacion localizacion;

    public Incidente(String descripcion, Persona informante, Servicio servicio) {
        this.descripcion = descripcion;
        this.fechaHoraApertura = LocalDateTime.now();
        this.informante = informante;
        this.servicio = servicio;
        this.localizacion = servicio.getLocalizacion();
    }

    public Persona getInformante() {
        return informante;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public Localizacion getLocalizacion() {
        return localizacion;
    }

    public Servicio getServicio() {
        return servicio;
    }

    public void marcarComoResuelto(){
        this.fechaHoraCierre = LocalDateTime.now();
        this.resuelto = true;
    }
}
