package ar.edu.utn.frba.dds.persistencia.repositorios;

import ar.edu.utn.frba.dds.models.OrganismoDeControl;
import ar.edu.utn.frba.dds.persistencia.EntityManagerHelper;
import ar.edu.utn.frba.dds.persistencia.repositorios.daos.Dao;
import ar.edu.utn.frba.dds.persistencia.repositorios.daos.DaoHibernate;
import java.util.List;

public class RepositorioOrganismoDeControl extends Repositorio<OrganismoDeControl> {

  private static RepositorioOrganismoDeControl instance = null;

  private RepositorioOrganismoDeControl(Dao<OrganismoDeControl> dao) {
    super(dao);
  }

  public static RepositorioOrganismoDeControl getInstance() {
    if (instance == null) {
      instance = new RepositorioOrganismoDeControl(new DaoHibernate<>(OrganismoDeControl.class));
    }
    return instance;
  }

  public Boolean existeOrganismoConElNombre(String nombre) {
    EntityManagerHelper.getEntityManager().getTransaction().begin();
    List results = EntityManagerHelper.createQuery("from OrganismoDeControl where nombre = :nombre")
        .setParameter("nombre", nombre).getResultList();
    EntityManagerHelper.getEntityManager().getTransaction().commit();

    return !results.isEmpty();
  }

  public List getOrganismoConElNombre(String nombre) {
    EntityManagerHelper.getEntityManager().getTransaction().begin();
    List results = EntityManagerHelper.createQuery("from OrganismoDeControl where nombre = :nombre")
        .setParameter("nombre", nombre).getResultList();
    EntityManagerHelper.getEntityManager().getTransaction().commit();

    return results;
  }
}
