package ar.edu.utn.frba.dds.persistencia.repositorios;

import ar.edu.utn.frba.dds.models.notificaciones.Notificacion;
import ar.edu.utn.frba.dds.persistencia.EntityManagerHelper;
import ar.edu.utn.frba.dds.persistencia.repositorios.daos.Dao;
import ar.edu.utn.frba.dds.persistencia.repositorios.daos.DaoHibernate;
import java.util.List;

public class RepositorioNotificaciones extends Repositorio<Notificacion> {

  private static RepositorioNotificaciones instance = null;

  private RepositorioNotificaciones(Dao<Notificacion> dao) {
    super(dao);
  }

  public static RepositorioNotificaciones getInstance() {
    if (instance == null) {
      instance = new RepositorioNotificaciones(new DaoHibernate<>(Notificacion.class));
    }
    return instance;
  }

  public List getNotificacionesDeUsuario(long id) {
    EntityManagerHelper.getEntityManager().getTransaction().begin();
    List resultados = EntityManagerHelper
        .createQuery("from Notificacion where usuarioANotificar_id = :usuario")
        .setParameter("usuario", id).getResultList();
    EntityManagerHelper.getEntityManager().getTransaction().commit();

    return resultados;
  }

}
