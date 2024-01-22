package ar.edu.utn.frba.dds.server.middlewares;

import ar.edu.utn.frba.dds.models.usuarios.Rol;
import ar.edu.utn.frba.dds.server.exceptions.AccessDeniedException;
import io.javalin.config.JavalinConfig;
import io.javalin.http.Context;

public class AuthMiddleware {
  public static void apply(JavalinConfig config) {
    config.accessManager(((handler, context, routeRoles) -> {
      Rol userRole = getUserRoleType(context);

      if (routeRoles.size() == 0 || routeRoles.contains(userRole)) {
        handler.handle(context);
      } else {
        throw new AccessDeniedException();
      }
    }));
  }

  private static Rol getUserRoleType(Context context) {
    return context.sessionAttribute("tipo_rol") != null
        ? Rol.valueOf(context.sessionAttribute("tipo_rol")) : null;
  }
}
