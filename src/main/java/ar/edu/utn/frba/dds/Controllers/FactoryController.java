package ar.edu.utn.frba.dds.Controllers;

public class FactoryController {

    public static Object controller(String nombre) {
        Object controller = null;
        switch (nombre) {
            case "Login": controller = new LoginController(); break;
            case "Comunidades": controller = new ComunidadesController(); break;
            case "Servicios": controller = new ServiciosController(); break;
        }
        return controller;
    }
}
