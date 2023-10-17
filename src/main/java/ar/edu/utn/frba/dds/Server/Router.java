package ar.edu.utn.frba.dds.Server;

import static io.javalin.apibuilder.ApiBuilder.*;

import ar.edu.utn.frba.dds.Controllers.ComunidadesController;
import ar.edu.utn.frba.dds.Controllers.FactoryController;
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
            get("comunidades/{id}/servicios/{idServicio}", ((ServiciosController) FactoryController.controller("Servicios"))::index);
        });
    }
}
