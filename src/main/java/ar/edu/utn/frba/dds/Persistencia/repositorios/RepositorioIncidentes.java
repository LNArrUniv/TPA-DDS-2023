package ar.edu.utn.frba.dds.Persistencia.repositorios;

import ar.edu.utn.frba.dds.Modelos.Incidente;
import ar.edu.utn.frba.dds.Modelos.UbicacionDTO.Localidad;
import ar.edu.utn.frba.dds.Persistencia.EntityManagerHelper;
import ar.edu.utn.frba.dds.Persistencia.repositorios.daos.DAO;
import ar.edu.utn.frba.dds.Persistencia.repositorios.daos.DAOHibernate;
import java.util.List;

public class RepositorioIncidentes extends Repositorio<Incidente> {
  private static RepositorioIncidentes instance = null;

  private RepositorioIncidentes(DAO<Incidente> dao) {
    super(dao);
  }

  public static RepositorioIncidentes getInstance() {
    if (instance == null) {
      instance = new RepositorioIncidentes(new DAOHibernate<>(Incidente.class));
    }
    return instance;
  }

  public List getActivos() {
    return EntityManagerHelper.createQuery("from Incidente where estaResuelto = :false").setParameter("false", false).getResultList();
  }

  public List getResueltos() {
    return EntityManagerHelper.createQuery("from Incidente where estaResuelto = :true").setParameter("true", true).getResultList();
  }

  public List incidentesEnUbicacion(Localidad ubicacion){
    return EntityManagerHelper.createQuery("from Incidente where localidad = :localidad and estaResuelto = false").setParameter("localidad", ubicacion).getResultList();
  }

}
