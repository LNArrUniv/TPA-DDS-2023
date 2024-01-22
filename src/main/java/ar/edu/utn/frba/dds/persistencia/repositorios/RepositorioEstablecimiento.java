package ar.edu.utn.frba.dds.persistencia.repositorios;

import ar.edu.utn.frba.dds.models.Establecimiento;
import ar.edu.utn.frba.dds.persistencia.repositorios.daos.Dao;
import ar.edu.utn.frba.dds.persistencia.repositorios.daos.DaoHibernate;

public class RepositorioEstablecimiento extends Repositorio<Establecimiento> {
  private static RepositorioEstablecimiento instance = null;

  private RepositorioEstablecimiento(Dao<Establecimiento> dao) {
    super(dao);
  }

  public static RepositorioEstablecimiento getInstance() {
    if (instance == null) {
      instance = new RepositorioEstablecimiento(new DaoHibernate<>(Establecimiento.class));
    }
    return instance;
  }
}
