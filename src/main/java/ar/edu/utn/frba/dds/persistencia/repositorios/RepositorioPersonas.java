package ar.edu.utn.frba.dds.persistencia.repositorios;

import ar.edu.utn.frba.dds.models.usuarios.Persona;
import ar.edu.utn.frba.dds.persistencia.EntityManagerHelper;
import ar.edu.utn.frba.dds.persistencia.repositorios.daos.Dao;
import ar.edu.utn.frba.dds.persistencia.repositorios.daos.DaoHibernate;
import java.util.List;

public class RepositorioPersonas extends Repositorio<Persona> {

  private static RepositorioPersonas instance = null;

  private RepositorioPersonas(Dao<Persona> dao) {
    super(dao);
  }

  public static RepositorioPersonas getInstance() {
    if (instance == null) {
      instance = new RepositorioPersonas(new DaoHibernate<>(Persona.class));
    }
    return instance;
  }

  public Boolean usuarioContraseniaCorrectas(String user, String pass) {
    EntityManagerHelper.getEntityManager().getTransaction().begin();
    List resultados = EntityManagerHelper
        .createQuery("from Persona where username = :user and contrasenia = :pass")
        .setParameter("user", user).setParameter("pass", pass).getResultList();
    EntityManagerHelper.getEntityManager().getTransaction().commit();

    return !resultados.isEmpty();
  }

  public Long getId(String user, String pass) {
    EntityManagerHelper.getEntityManager().getTransaction().begin();
    List<Persona> resultados = EntityManagerHelper
        .createQuery("from Persona where username = :user and contrasenia = :pass")
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
