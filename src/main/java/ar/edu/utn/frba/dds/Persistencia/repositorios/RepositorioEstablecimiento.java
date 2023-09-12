package ar.edu.utn.frba.dds.Persistencia.repositorios;

import ar.edu.utn.frba.dds.Modelos.Entidad;
import ar.edu.utn.frba.dds.Persistencia.repositorios.daos.DAO;
import ar.edu.utn.frba.dds.Persistencia.repositorios.daos.DAOHibernate;

public class RepositorioEstablecimiento extends Repositorio<Entidad> {
  private static RepositorioEstablecimiento instance = null;

  private RepositorioEstablecimiento(DAO<Entidad> dao) {
    super(dao);
  }

  public static RepositorioEstablecimiento getInstance() {
    if (instance == null) {
      instance = new RepositorioEstablecimiento(new DAOHibernate<>(Entidad.class));
    }
    return instance;
  }
}
