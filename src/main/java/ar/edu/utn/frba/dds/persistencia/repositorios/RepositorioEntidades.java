package ar.edu.utn.frba.dds.persistencia.repositorios;

import ar.edu.utn.frba.dds.models.Entidad;
import ar.edu.utn.frba.dds.persistencia.repositorios.daos.Dao;
import ar.edu.utn.frba.dds.persistencia.repositorios.daos.DaoHibernate;

public class RepositorioEntidades extends Repositorio<Entidad> {
  private static RepositorioEntidades instance = null;

  private RepositorioEntidades(Dao<Entidad> dao) {
    super(dao);
  }

  public static RepositorioEntidades getInstance() {
    if (instance == null) {
      instance = new RepositorioEntidades(new DaoHibernate<>(Entidad.class));
    }
    return instance;
  }
}

