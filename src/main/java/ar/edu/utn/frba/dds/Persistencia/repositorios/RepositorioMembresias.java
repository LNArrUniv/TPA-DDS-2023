package ar.edu.utn.frba.dds.Persistencia.repositorios;

import ar.edu.utn.frba.dds.Modelos.Comunidades.Comunidad;
import ar.edu.utn.frba.dds.Modelos.Comunidades.Membresia;
import ar.edu.utn.frba.dds.Persistencia.EntityManagerHelper;
import ar.edu.utn.frba.dds.Persistencia.repositorios.daos.DAO;
import ar.edu.utn.frba.dds.Persistencia.repositorios.daos.DAOHibernate;
import java.util.List;

public class RepositorioMembresias extends Repositorio<Membresia> {

  private static RepositorioMembresias instance = null;

  private RepositorioMembresias(DAO<Membresia> dao) {
    super(dao);
  }

  public static RepositorioMembresias getInstance() {
    if (instance == null) {
      instance = new RepositorioMembresias(new DAOHibernate<>(Membresia.class));
    }
    return instance;
  }

  public List getComunidadesDePersona(long idPersona) {
    List resultados = EntityManagerHelper.createQuery("from Membresia where miembro_id = :miembro").setParameter("miembro", idPersona).getResultList();

    return resultados;
  }

  public Membresia membresiaDePersonaEnComunidad(long idPersona, long idComunidad) {
    List resultados = EntityManagerHelper.createQuery("from Membresia where miembro_id = :miembro and comunidad_id = :comunidad").setParameter("miembro", idPersona).setParameter("comunidad", idComunidad).getResultList();

    return (Membresia) resultados.get(0);
  }

  public List membresiasDeComunidad(Comunidad comunidad){
    List resultados = EntityManagerHelper.createQuery("from Membresia where comunidad_id = :comunidad").setParameter("comunidad", comunidad.getId()).getResultList();

    return resultados;
  }
}