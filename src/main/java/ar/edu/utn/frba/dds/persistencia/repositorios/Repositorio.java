package ar.edu.utn.frba.dds.persistencia.repositorios;

import ar.edu.utn.frba.dds.persistencia.repositorios.daos.Dao;
import java.util.List;

public class Repositorio<T> {
  protected Dao<T> dao;

  public Repositorio(Dao<T> dao) {
    this.dao = dao;
  }

  public void setDao(Dao<T> dao) {
    this.dao = dao;
  }

  public List<T> all() {
    return this.dao.all();
  }

  public T get(long id) {
    return this.dao.get(id);
  }

  public void add(Object object) {
    this.dao.add(object);
  }

  public void update(Object object) {
    this.dao.update(object);
  }

  public void delete(Object object) {
    this.dao.delete(object);
  }

  public void clean() {
    this.dao.clean();
  }
}
