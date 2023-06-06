package ar.edu.utn.frba.dds.modelos;

import java.util.ArrayList;
import java.util.List;

public class Persona {

    private ArrayList<Entidad> entidadesDeInteres;
    private ArrayList<Servicio> serviciosDeInteres;

    private Localizacion localizacion;
    private ArrayList<Servicio> interesProblematica;
    private ArrayList<Comunidad> comunidades;

    public Persona(Localizacion localizacion) {
        this.entidadesDeInteres = new ArrayList<Entidad>();
        this.serviciosDeInteres = new ArrayList<Servicio>();
        this.localizacion = localizacion;
        this.interesProblematica = new ArrayList<Servicio>();
        this.comunidades = new ArrayList<Comunidad>();
    }

    public void agregarInteresProblematica(Servicio servicio) {
        this.interesProblematica.add(servicio);
    }

    public void eliminarInteresProblematica(Servicio servicio) {
        this.interesProblematica.remove(servicio);
    }

    public void darseAltaComunidad(Comunidad comunidad) {
        this.comunidades.add(comunidad);
    }

    public void darseBajaComunidad(Comunidad comunidad) {
        this.comunidades.remove(comunidad);
    }
}
