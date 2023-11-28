package ar.edu.utn.frba.dds.Persistencia.repositorios;

import ar.edu.utn.frba.dds.Modelos.Rankings.MetodosRanking;
import ar.edu.utn.frba.dds.Persistencia.EntityManagerHelper;
import ar.edu.utn.frba.dds.Persistencia.repositorios.daos.DAO;
import ar.edu.utn.frba.dds.Persistencia.repositorios.daos.DAOHibernate;
import java.time.LocalDate;
import java.util.List;

public class RepositorioRankings extends Repositorio<MetodosRanking>{
  private static RepositorioRankings instance = null;

  private RepositorioRankings(DAO<MetodosRanking> dao) {
    super(dao);
  }

  public static RepositorioRankings getInstance() {
    if (instance == null) {
      instance = new RepositorioRankings(new DAOHibernate<>(MetodosRanking.class));
    }
    return instance;
  }
/*
  public MetodosRanking rankingPorNombre(String nombre){
      EntityManagerHelper.getEntityManager().getTransaction().begin();
      List<MetodosRanking> resultados = EntityManagerHelper.createQuery("from MetodosRanking where nombre = :nombre").setParameter("nombre", nombre).getResultList();
      EntityManagerHelper.getEntityManager().getTransaction().commit();

      return resultados.get(0);
  }
 */
}
