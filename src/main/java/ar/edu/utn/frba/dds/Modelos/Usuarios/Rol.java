package ar.edu.utn.frba.dds.Modelos.Usuarios;

import io.javalin.security.RouteRole;

public enum Rol implements RouteRole {
  MIEMBRO,
  PERSONA_DESIGNADA,
  ADMINISTRADOR
}
