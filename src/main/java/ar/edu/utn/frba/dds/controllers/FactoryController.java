package ar.edu.utn.frba.dds.controllers;

public class FactoryController {

  public static Object controller(String nombre) {
    Object controller = null;
    switch (nombre) {
      case "Login":
        controller = new LoginController();
        break;
      case "Comunidades":
        controller = new ComunidadesController();
        break;
      case "Servicios":
        controller = new ServiciosController();
        break;
      case "Incidentes":
        controller = new IncidentesController();
        break;
      case "Personas":
        controller = new PersonasController();
        break;
      case "Csv":
        controller = new CsvController();
        break;
      case "Rankings":
        controller = new RankingsController();
        break;
      case "Notificaciones":
        controller = new NotificacionesController();
        break;
      case "PersonasDesignadas":
        controller = new PersonasDesignadasController();
        break;
      default:
        break;
    }
    return controller;
  }
}
