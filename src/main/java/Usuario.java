import java.util.List;

public class Usuario {
    private String nombre;
    private String apellido;
    private String email;
    private String contraseña;
    private List<Servicio> interesProblematica;
    private List<Comunidad> comunidades;

    public void agregarInteresProblematica(Servicio servicio) {interesProblematica.add(servicio);}
    public void eliminarInteresProblematica(Servicio servicio) {interesProblematica.add(servicio);}
    public void darseBajaComunidad(Comunidad comunidad) {comunidades.remove(comunidad);}

}
