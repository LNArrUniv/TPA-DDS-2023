package ar.edu.utn.frba.dds.Persistencia.repositorios;

import ar.edu.utn.frba.dds.Modelos.Usuarios.Persona;
import ar.edu.utn.frba.dds.Modelos.Usuarios.PersonaDesignada;
import ar.edu.utn.frba.dds.Persistencia.EntityManagerHelper;
import ar.edu.utn.frba.dds.Persistencia.repositorios.daos.DAO;
import ar.edu.utn.frba.dds.Persistencia.repositorios.daos.DAOHibernate;
import java.util.List;

public class RepositorioPersonasDesignadas extends Repositorio<PersonaDesignada> {

  private static RepositorioPersonasDesignadas instance = null;

  private RepositorioPersonasDesignadas(DAO<PersonaDesignada> dao) {
    super(dao);
  }

  public static RepositorioPersonasDesignadas getInstance() {
    if (instance == null) {
      instance = new RepositorioPersonasDesignadas(new DAOHibernate<>(PersonaDesignada.class));
    }
    return instance;
  }

  public Boolean usuarioYContraseniaCorrectas(String user, String pass) {
    List resultados = EntityManagerHelper.createQuery("from PersonaDesignada where username = :user and contrasenia = :pass").setParameter("user", user).setParameter("pass", pass).getResultList();
    return !resultados.isEmpty();
  }

  public Long getId(String user, String pass) {
    List<Persona> resultados = EntityManagerHelper.createQuery("from Persona where username = :user and contrasenia = :pass").setParameter("user", user).setParameter("pass", pass).getResultList();
    long id = resultados.get(0).getId();
    return id;
  }
}
