package ar.edu.utn.frba.dds.Persistencia.repositorios;

import ar.edu.utn.frba.dds.Modelos.EntidadPropietaria;
import ar.edu.utn.frba.dds.Persistencia.repositorios.daos.DAO;
import ar.edu.utn.frba.dds.Persistencia.repositorios.daos.DAOHibernate;

public class RepositorioEntidadPropietarias extends Repositorio<EntidadPropietaria>{

  private static RepositorioEntidadPropietarias instance = null;

  private RepositorioEntidadPropietarias(DAO<EntidadPropietaria> dao) {
    super(dao);
  }

  public static RepositorioEntidadPropietarias getInstance() {
    if (instance == null) {
      instance = new RepositorioEntidadPropietarias(new DAOHibernate<>(EntidadPropietaria.class));
    }
    return instance;
  }
}
