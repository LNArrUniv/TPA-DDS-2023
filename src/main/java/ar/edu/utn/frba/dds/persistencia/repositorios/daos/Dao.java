package ar.edu.utn.frba.dds.persistencia.repositorios.daos;

import java.util.List;

public interface Dao<T> {
  /**
   * Data Access Object
   */
  List<T> all();

  T get(long id);

  void add(Object objeto);

  void delete(Object objeto);

  void update(Object objeto);

  void clean();
}
