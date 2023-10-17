package ar.edu.utn.frba.dds.Server;

import static io.javalin.apibuilder.ApiBuilder.*;

import ar.edu.utn.frba.dds.Controllers.ComunidadesController;
import ar.edu.utn.frba.dds.Controllers.FactoryController;
import ar.edu.utn.frba.dds.Controllers.IncidentesController;
import ar.edu.utn.frba.dds.Controllers.LoginController;
import ar.edu.utn.frba.dds.Controllers.ServiciosController;

public class Router {

    public static void init() {
        Server.app().get("/persona", context -> {
            context.result("hola");
        });

        Server.app().get("/designada", context -> {
            context.result("chau");
        });

        Server.app().get("/", context -> {
            context.redirect("/login");
        });


        Server.app().routes(() -> {
            get("login", ((LoginController) FactoryController.controller("Login"))::get);
            post("login", ((LoginController) FactoryController.controller("Login"))::post);

            get("comunidades", ((ComunidadesController) FactoryController.controller("Comunidades"))::index);
            get("comunidades/crear", ((ComunidadesController) FactoryController.controller("Comunidades"))::create);
            post("comunidades/crear", ((ComunidadesController) FactoryController.controller("Comunidades"))::save);
            get("comunidades/unirse", ((ComunidadesController) FactoryController.controller("Comunidades"))::unirse);
            post("comunidades/unirse", ((ComunidadesController) FactoryController.controller("Comunidades"))::update);
            get("comunidades/{id}/servicios", ((ComunidadesController) FactoryController.controller("Comunidades"))::show);
            get("comunidades/{id}/servicios/agregar", ((ServiciosController) FactoryController.controller("Servicios"))::index);
            get("comunidades/{id}/servicios/agregar/{idServicio}", ((ComunidadesController) FactoryController.controller("Comunidades"))::agregarServicio);
            get("comunidades/{id}/servicios/{idServicio}", ((ServiciosController) FactoryController.controller("Servicios"))::show);
            post("comunidades/{id}/servicios/{idServicio}/crear_incidente", ((IncidentesController) FactoryController.controller("Incidentes"))::create);
            get("/comunidades/{id}/servicios/{idServicio}/resolver/{idIncidente}", ((IncidentesController) FactoryController.controller("Incidentes"))::edit);
        });
    }
}
