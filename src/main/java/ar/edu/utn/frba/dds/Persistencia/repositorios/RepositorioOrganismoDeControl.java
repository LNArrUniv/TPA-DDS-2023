package ar.edu.utn.frba.dds.Persistencia.repositorios;

import ar.edu.utn.frba.dds.Modelos.OrganismoDeControl;
import ar.edu.utn.frba.dds.Persistencia.EntityManagerHelper;
import ar.edu.utn.frba.dds.Persistencia.repositorios.daos.DAO;
import ar.edu.utn.frba.dds.Persistencia.repositorios.daos.DAOHibernate;

import java.util.List;

public class RepositorioOrganismoDeControl extends Repositorio<OrganismoDeControl>{

  private static RepositorioOrganismoDeControl instance = null;

  private RepositorioOrganismoDeControl(DAO<OrganismoDeControl> dao) {
    super(dao);
  }

  public static RepositorioOrganismoDeControl getInstance() {
    if (instance == null) {
      instance = new RepositorioOrganismoDeControl(new DAOHibernate<>(OrganismoDeControl.class));
    }
    return instance;
  }

  public List getOrganismosDeControl(){
    return EntityManagerHelper.createQuery("from OrganismoDeControl").getResultList();
  }

  public Boolean existeOrganismoConElNombre(String nombre){
    List results = EntityManagerHelper.createQuery("from OrganismoDeControl where nombre = :nombre").setParameter("nombre", nombre).getResultList();

    return !results.isEmpty();
  }

  public List getOrganismoConElNombre(String nombre){
    List results = EntityManagerHelper.createQuery("from OrganismoDeControl where nombre = :nombre").setParameter("nombre", nombre).getResultList();

    return results;
  }
}
