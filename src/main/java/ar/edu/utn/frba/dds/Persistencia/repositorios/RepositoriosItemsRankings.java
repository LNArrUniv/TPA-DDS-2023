package ar.edu.utn.frba.dds.Persistencia.repositorios;

import ar.edu.utn.frba.dds.Modelos.Rankings.ItemRanking;
import ar.edu.utn.frba.dds.Modelos.Servicio;
import ar.edu.utn.frba.dds.Persistencia.repositorios.daos.DAO;
import ar.edu.utn.frba.dds.Persistencia.repositorios.daos.DAOHibernate;

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
}
