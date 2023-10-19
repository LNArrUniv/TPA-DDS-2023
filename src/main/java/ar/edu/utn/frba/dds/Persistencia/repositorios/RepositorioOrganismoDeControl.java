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
}
