package ar.edu.utn.frba.dds.persistencia.repositorios;

import ar.edu.utn.frba.dds.models.EntidadPropietaria;
import ar.edu.utn.frba.dds.persistencia.EntityManagerHelper;
import ar.edu.utn.frba.dds.persistencia.repositorios.daos.Dao;
import ar.edu.utn.frba.dds.persistencia.repositorios.daos.DaoHibernate;
import java.util.List;

public class RepositorioEntidadPropietarias extends Repositorio<EntidadPropietaria> {

  private static RepositorioEntidadPropietarias instance = null;

  private RepositorioEntidadPropietarias(Dao<EntidadPropietaria> dao) {
    super(dao);
  }

  public static RepositorioEntidadPropietarias getInstance() {
    if (instance == null) {
      instance = new RepositorioEntidadPropietarias(new DaoHibernate<>(EntidadPropietaria.class));
    }
    return instance;
  }

  public Boolean existeEntidadPropietariaConElNombre(String nombre) {
    EntityManagerHelper.getEntityManager().getTransaction().begin();
    List results = EntityManagerHelper.createQuery("from EntidadPropietaria where nombre = :nombre")
        .setParameter("nombre", nombre).getResultList();
    EntityManagerHelper.getEntityManager().getTransaction().commit();

    return !results.isEmpty();
  }
}
