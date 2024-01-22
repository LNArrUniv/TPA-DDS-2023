package ar.edu.utn.frba.dds.persistencia.repositorios;

import ar.edu.utn.frba.dds.models.Servicio;
import ar.edu.utn.frba.dds.persistencia.repositorios.daos.Dao;
import ar.edu.utn.frba.dds.persistencia.repositorios.daos.DaoHibernate;

public class RepositorioServicios extends Repositorio<Servicio> {
  private static RepositorioServicios instance = null;

  private RepositorioServicios(Dao<Servicio> dao) {
    super(dao);
  }

  public static RepositorioServicios getInstance() {
    if (instance == null) {
      instance = new RepositorioServicios(new DaoHibernate<>(Servicio.class));
    }
    return instance;
  }
}
