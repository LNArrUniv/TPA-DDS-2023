package ar.edu.utn.frba.dds.persistencia.repositorios;

import ar.edu.utn.frba.dds.models.comunidades.Comunidad;
import ar.edu.utn.frba.dds.persistencia.repositorios.daos.Dao;
import ar.edu.utn.frba.dds.persistencia.repositorios.daos.DaoHibernate;

public class RepositorioComunidades extends Repositorio<Comunidad> {

  private static RepositorioComunidades instance = null;

  private RepositorioComunidades(Dao<Comunidad> dao) {
    super(dao);
  }

  public static RepositorioComunidades getInstance() {
    if (instance == null) {
      instance = new RepositorioComunidades(new DaoHibernate<>(Comunidad.class));
    }
    return instance;
  }
}