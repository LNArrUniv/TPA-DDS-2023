package ar.edu.utn.frba.dds.server.handlers;

import io.javalin.Javalin;

public interface Ihandler {
  void setHandle(Javalin app);
}
