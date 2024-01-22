package ar.edu.utn.frba.dds.persistencia.repositorios;

import ar.edu.utn.frba.dds.models.rankings.MetodosRanking;
import ar.edu.utn.frba.dds.persistencia.repositorios.daos.Dao;
import ar.edu.utn.frba.dds.persistencia.repositorios.daos.DaoHibernate;

public class RepositorioRankings extends Repositorio<MetodosRanking> {
  private static RepositorioRankings instance = null;

  private RepositorioRankings(Dao<MetodosRanking> dao) {
    super(dao);
  }

  public static RepositorioRankings getInstance() {
    if (instance == null) {
      instance = new RepositorioRankings(new DaoHibernate<>(MetodosRanking.class));
    }
    return instance;
  }
  /*
  public MetodosRanking rankingPorNombre(String nombre){
      EntityManagerHelper.getEntityManager().getTransaction().begin();
      List<MetodosRanking> resultados = EntityManagerHelper
      .createQuery("from MetodosRanking where nombre = :nombre")
      .setParameter("nombre", nombre).getResultList();
      EntityManagerHelper.getEntityManager().getTransaction().commit();

      return resultados.get(0);
  }
  */
}
