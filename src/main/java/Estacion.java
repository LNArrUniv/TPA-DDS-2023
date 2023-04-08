import java.util.List;

public class Estacion {
    private String nombre;
    private String direccion;
    private List<Linea> lineasPertenecientes;
    private List<Servicio> servicios;

    public void eliminarServicio(Servicio servicio){ servicios.remove(servicio);}
    public void agregarServicio(Servicio servicio){ servicios.add(servicio);}
    public void modificarServicio(Servicio servicio){ }

}
