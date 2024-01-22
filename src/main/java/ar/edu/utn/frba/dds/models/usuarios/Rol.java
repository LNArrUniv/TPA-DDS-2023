package ar.edu.utn.frba.dds.models.usuarios;

import io.javalin.security.RouteRole;

public enum Rol implements RouteRole {
  NORMAL,
  PERSONA_DESIGNADA,
  ADMINISTRADOR
}
