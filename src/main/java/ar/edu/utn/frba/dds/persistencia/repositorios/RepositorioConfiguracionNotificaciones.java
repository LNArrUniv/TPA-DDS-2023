package ar.edu.utn.frba.dds.persistencia.repositorios;

import ar.edu.utn.frba.dds.models.notificaciones.ConfiguracionNotificaciones;
import ar.edu.utn.frba.dds.persistencia.repositorios.daos.Dao;
import ar.edu.utn.frba.dds.persistencia.repositorios.daos.DaoHibernate;

public class RepositorioConfiguracionNotificaciones
    extends Repositorio<ConfiguracionNotificaciones> {

  private static RepositorioConfiguracionNotificaciones instance = null;

  private RepositorioConfiguracionNotificaciones(Dao<ConfiguracionNotificaciones> dao) {
    super(dao);
  }

  public static RepositorioConfiguracionNotificaciones getInstance() {
    if (instance == null) {
      instance = new RepositorioConfiguracionNotificaciones(
          new DaoHibernate<>(ConfiguracionNotificaciones.class));
    }
    return instance;
  }

}
