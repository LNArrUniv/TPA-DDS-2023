import java.util.List;
import ar.edu.utn.frba.dds.Modelos.Servicio;
public class Comunidad {
    private String nombreComunidad;
    private Servicio problematicaDeInteres;
    private List<Usuario> miembros;
    private List<Usuario> administradores;

    public void agregarMiembro(Usuario usuario){miembros.remove(usuario);}
    public void eleminarMiembro(Usuario usuario) {miembros.remove(usuario);}
    public void relevarServicio(Servicio servicio, Estacion estacion){ estacion.modificarServicio(servicio);}
}
