package ar.edu.utn.frba.dds.Persistencia.repositorios;

import ar.edu.utn.frba.dds.Modelos.Persona;
import ar.edu.utn.frba.dds.Persistencia.repositorios.daos.DAO;
import ar.edu.utn.frba.dds.Persistencia.repositorios.daos.DAOHibernate;

public class RepositorioPersonas extends Repositorio<Persona> {

  private static RepositorioPersonas instance = null;

  private RepositorioPersonas(DAO<Persona> dao) {
    super(dao);
  }

  public static RepositorioPersonas getInstance() {
    if (instance == null) {
      instance = new RepositorioPersonas(new DAOHibernate<>(Persona.class));
    }
    return instance;
  }
}
