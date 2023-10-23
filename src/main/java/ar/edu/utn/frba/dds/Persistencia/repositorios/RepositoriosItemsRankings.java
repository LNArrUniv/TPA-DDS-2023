package ar.edu.utn.frba.dds.Persistencia.repositorios;

import ar.edu.utn.frba.dds.Modelos.Rankings.ItemRanking;
import ar.edu.utn.frba.dds.Modelos.Servicio;
import ar.edu.utn.frba.dds.Persistencia.EntityManagerHelper;
import ar.edu.utn.frba.dds.Persistencia.repositorios.daos.DAO;
import ar.edu.utn.frba.dds.Persistencia.repositorios.daos.DAOHibernate;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class RepositoriosItemsRankings extends Repositorio<ItemRanking> {
  private static RepositoriosItemsRankings instance = null;

  private RepositoriosItemsRankings(DAO<ItemRanking> dao) {
    super(dao);
  }

  public static RepositoriosItemsRankings getInstance() {
    if (instance == null) {
      instance = new RepositoriosItemsRankings(new DAOHibernate<>(ItemRanking.class));
    }
    return instance;
  }

  public List itemsEstaSemana(long idRanking){
    List resultados = EntityManagerHelper.createQuery("from ItemRanking where fecha <= :hoy and fecha >= :semanaPasada and ranking_id = :id").setParameter("hoy", LocalDate.now()).setParameter("semanaPasada", LocalDate.now().minusDays(7)).setParameter("id", idRanking).getResultList();
    return resultados;
  }

}
