package ar.edu.utn.frba.dds.server.handlers;

import io.javalin.Javalin;
import java.util.Arrays;

public class AppHandlers {
  private Ihandler[] handlers = new Ihandler[]{
      new AccessDeniedHandler(),
  };

  public static void applyHandlers(Javalin app) {
    Arrays.stream(new AppHandlers().handlers).toList().forEach(handler -> handler.setHandle(app));
  }
}
