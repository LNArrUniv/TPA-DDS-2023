package ar.edu.utn.frba.dds.persistencia.repositorios;

import ar.edu.utn.frba.dds.models.rankings.ItemRanking;
import ar.edu.utn.frba.dds.persistencia.EntityManagerHelper;
import ar.edu.utn.frba.dds.persistencia.repositorios.daos.Dao;
import ar.edu.utn.frba.dds.persistencia.repositorios.daos.DaoHibernate;
import java.time.LocalDateTime;
import java.util.List;

public class RepositoriosItemsRankings extends Repositorio<ItemRanking> {
  private static RepositoriosItemsRankings instance = null;

  private RepositoriosItemsRankings(Dao<ItemRanking> dao) {
    super(dao);
  }

  public static RepositoriosItemsRankings getInstance() {
    if (instance == null) {
      instance = new RepositoriosItemsRankings(new DaoHibernate<>(ItemRanking.class));
    }
    return instance;
  }

  public List itemsEstaSemana(long idRanking) {
    EntityManagerHelper.getEntityManager().getTransaction().begin();
    List resultados = EntityManagerHelper
        .createQuery("from ItemRanking where "
            + "fecha <= :hoy and fecha >= :semanaPasada and ranking_id = :id")
        .setParameter("hoy", LocalDateTime.now())
        .setParameter("semanaPasada", LocalDateTime.now().minusDays(7))
        .setParameter("id", idRanking).getResultList();
    EntityManagerHelper.getEntityManager().getTransaction().commit();

    return resultados;
  }
  /*
  public Boolean pasaronDiezMinutosDesdeLosUltimos(){
    EntityManagerHelper.getEntityManager().getTransaction().begin();
    List<ItemRanking> todos = EntityManagerHelper.createQuery("from ItemRanking").getResultList();
    EntityManagerHelper.getEntityManager().getTransaction().commit();

    List<ItemRanking> recientes = todos.stream()
        .filter(i -> ChronoUnit.MINUTES.between(i.getFecha(), LocalDateTime.now()) <= 10)
        .toList();

    return recientes.isEmpty();
  }

  public List<ItemRanking> masRecientes(long idRanking){
    // 10min
    EntityManagerHelper.getEntityManager().getTransaction().begin();
    List<ItemRanking> todos = EntityManagerHelper
    .createQuery("from ItemRanking where ranking_id = :id")
    .setParameter("id", idRanking).getResultList();
    EntityManagerHelper.getEntityManager().getTransaction().commit();

    List<ItemRanking> recientes = todos.stream()
        .filter(i -> ChronoUnit.MINUTES.between(i.getFecha(), LocalDateTime.now()) <= 10)
        .toList();

    return recientes;
  }
  */
}
