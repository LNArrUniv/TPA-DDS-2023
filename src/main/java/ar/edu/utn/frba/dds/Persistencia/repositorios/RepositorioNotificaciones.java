package ar.edu.utn.frba.dds.Persistencia.repositorios;

import ar.edu.utn.frba.dds.Modelos.Notificaciones.Notificacion;
import ar.edu.utn.frba.dds.Persistencia.repositorios.daos.DAO;
import ar.edu.utn.frba.dds.Persistencia.repositorios.daos.DAOHibernate;

public class RepositorioNotificaciones extends Repositorio<Notificacion>{

  private static RepositorioNotificaciones instance = null;

  private RepositorioNotificaciones(DAO<Notificacion> dao) {
    super(dao);
  }

  public static RepositorioNotificaciones getInstance() {
    if (instance == null) {
      instance = new RepositorioNotificaciones(new DAOHibernate<>(Notificacion.class));
    }
    return instance;
  }
}
