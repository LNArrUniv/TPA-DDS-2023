package ar.edu.utn.frba.dds.modelos;

import java.util.ArrayList;

public class Entidad {
    private String nombre;
    private String descripcion;
    private ArrayList<Establecimiento> establecimientos;
    private Localizacion localizacion;

    public Entidad(String nombre, String descripcion, Localizacion localizacion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.establecimientos = new ArrayList<Establecimiento>();
        this.localizacion = localizacion;
    }

    private void usarServicio(Establecimiento establecimiento, Servicio servicio){}
    private void agregarEstablecimiento(Establecimiento establecimiento){
        establecimientos.add(establecimiento);
    }

}
