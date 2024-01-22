package ar.edu.utn.frba.dds.persistencia.repositorios;

import ar.edu.utn.frba.dds.models.usuarios.Persona;
import ar.edu.utn.frba.dds.models.usuarios.PersonaDesignada;
import ar.edu.utn.frba.dds.persistencia.EntityManagerHelper;
import ar.edu.utn.frba.dds.persistencia.repositorios.daos.Dao;
import ar.edu.utn.frba.dds.persistencia.repositorios.daos.DaoHibernate;
import java.util.List;

public class RepositorioPersonasDesignadas extends Repositorio<PersonaDesignada> {

  private static RepositorioPersonasDesignadas instance = null;

  private RepositorioPersonasDesignadas(Dao<PersonaDesignada> dao) {
    super(dao);
  }

  public static RepositorioPersonasDesignadas getInstance() {
    if (instance == null) {
      instance = new RepositorioPersonasDesignadas(new DaoHibernate<>(PersonaDesignada.class));
    }
    return instance;
  }

  public Boolean usuarioContraseniaCorrectas(String user, String pass) {
    EntityManagerHelper.getEntityManager().getTransaction().begin();
    List resultados = EntityManagerHelper
        .createQuery("from PersonaDesignada where username = :user and contrasenia = :pass")
        .setParameter("user", user).setParameter("pass", pass).getResultList();
    EntityManagerHelper.getEntityManager().getTransaction().commit();

    return !resultados.isEmpty();
  }

  public Long getId(String user, String pass) {
    EntityManagerHelper.getEntityManager().getTransaction().begin();
    List<PersonaDesignada> resultados = EntityManagerHelper
        .createQuery("from PersonaDesignada where username = :user and contrasenia = :pass")
        .setParameter("user", user).setParameter("pass", pass).getResultList();
    EntityManagerHelper.getEntityManager().getTransaction().commit();

    long id = resultados.get(0).getId();
    return id;
  }

  public Boolean usuarioYaExiste(String user) {
    EntityManagerHelper.getEntityManager().getTransaction().begin();
    List<Persona> resultados = EntityManagerHelper
        .createQuery("from Persona where username = :user")
        .setParameter("user", user).getResultList();
    EntityManagerHelper.getEntityManager().getTransaction().commit();

    return !resultados.isEmpty();
  }
}
