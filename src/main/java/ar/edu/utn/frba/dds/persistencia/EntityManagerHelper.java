package ar.edu.utn.frba.dds.persistencia;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class EntityManagerHelper {

  private static EntityManagerFactory emf;

  private static ThreadLocal<EntityManager> threadLocal;

  static {
    try {
      Map<String, String> env = System.getenv();
      Map<String, Object> configOverrides = new HashMap<String, Object>();

      String[] keys = new String[]{
          "javax.persistence.jdbc.driver",
          "javax.persistence.jdbc.password",
          "javax.persistence.jdbc.url",
          "javax.persistence.jdbc.user",
          "hibernate.hbm2ddl.auto",
          "hibernate.connection.pool_size",
          "hibernate.show_sql"};

      for (String key : keys) {
        if (env.containsKey(key)) {
          String value = env.get(key);
          configOverrides.put(key, value);
        }

      }

      emf = Persistence.createEntityManagerFactory("simple-persistence-unit", configOverrides);
      threadLocal = new ThreadLocal<>();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private static EntityManagerFactory emf() {
    if (emf == null || !emf.isOpen()) {
      Map<String, String> env = System.getenv();
      Map<String, Object> configOverrides = new HashMap<String, Object>();

      String[] keys = new String[]{
          "javax.persistence.jdbc.driver",
          "javax.persistence.jdbc.password",
          "javax.persistence.jdbc.url",
          "javax.persistence.jdbc.user",
          "hibernate.hbm2ddl.auto",
          "hibernate.connection.pool_size",
          "hibernate.show_sql"};

      for (String key : keys) {
        if (env.containsKey(key)) {
          String value = env.get(key);
          configOverrides.put(key, value);
        }

      }

      emf = Persistence.createEntityManagerFactory("simple-persistence-unit", configOverrides);
    }
    return emf;
  }

  private static ThreadLocal<EntityManager> threadLocal() {
    if (threadLocal == null) {
      threadLocal = new ThreadLocal<>();
    }
    return threadLocal;
  }

  public static EntityManager entityManager() {
    return getEntityManager();
  }

  public static EntityManager getEntityManager() {
    EntityManager manager = threadLocal().get();
    if (manager == null || !manager.isOpen()) {
      manager = emf().createEntityManager();
      threadLocal().set(manager);
    }
    return manager;
  }

  public static void closeEntityManager() {
    EntityManager em = threadLocal.get();
    if (em != null) {
      threadLocal.set(null);
      em.close();
    }
  }

  public static void closeEntityManagerFactory() {
    emf.close();
  }

  public static void beginTransaction() {
    EntityManager em = EntityManagerHelper.getEntityManager();
    EntityTransaction tx = em.getTransaction();
    try {
      if (!tx.isActive()) {
        tx.begin();
      }
    } catch (Exception e) {
      tx.rollback();
    }
  }

  public static void commit() {
    EntityManager em = EntityManagerHelper.getEntityManager();
    EntityTransaction tx = em.getTransaction();
    if (tx.isActive()) {
      tx.commit();
    }

  }

  public static void rollback() {
    EntityManager em = EntityManagerHelper.getEntityManager();
    EntityTransaction tx = em.getTransaction();
    if (tx.isActive()) {
      tx.rollback();
    }
  }

  public static Query createQuery(String query) {
    return getEntityManager().createQuery(query);
  }

  public static void persist(Object o) {
    entityManager().persist(o);
  }

  public static void withTransaction(Runnable action) {
    withTransaction(() -> {
      action.run();
      return null;
    });
  }

  public static <A> A withTransaction(Supplier<A> action) {
    beginTransaction();
    try {
      A result = action.get();
      commit();
      return result;
    } catch (Throwable e) {
      rollback();
      throw e;
    }
  }
}
