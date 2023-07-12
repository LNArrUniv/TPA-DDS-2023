package ar.edu.utn.frba.dds.Modelos;


public class ServicioPorPersona {

    private Servicio servicioElegido;

    private String tipoDeUsuario;

    public ServicioPorPersona(Servicio servicioElegido, String tipoDeInteres) {
        this.servicioElegido = servicioElegido;
        this.tipoDeUsuario = tipoDeUsuario;
    }

    public String getNombre() {
        return servicioElegido.getNombre();
    }

    public void agregarNuevoIncidente(Incidente incidente){
        servicioElegido.agregarNuevoIncidente(incidente);
    }

    public void marcarComoResuelto(Incidente incidente){ servicioElegido.marcarComoResuelto(incidente); }
}