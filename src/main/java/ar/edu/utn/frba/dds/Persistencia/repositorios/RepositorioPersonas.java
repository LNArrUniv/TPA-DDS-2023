package ar.edu.utn.frba.dds.Persistencia.repositorios;

import ar.edu.utn.frba.dds.Modelos.Usuarios.Persona;
import ar.edu.utn.frba.dds.Persistencia.EntityManagerHelper;
import ar.edu.utn.frba.dds.Persistencia.repositorios.daos.DAO;
import ar.edu.utn.frba.dds.Persistencia.repositorios.daos.DAOHibernate;
import java.util.List;

public class RepositorioPersonas extends Repositorio<Persona> {

  private static RepositorioPersonas instance = null;

  private RepositorioPersonas(DAO<Persona> dao) {
    super(dao);
  }

  public static RepositorioPersonas getInstance() {
    if (instance == null) {
      instance = new RepositorioPersonas(new DAOHibernate<>(Persona.class));
    }
    return instance;
  }

  public Boolean usuarioYContraseniaCorrectas(String user, String pass) {
    List resultados = EntityManagerHelper.createQuery("from Persona where username = :user and contrasenia = :pass").setParameter("user", user).setParameter("pass", pass).getResultList();

    return !resultados.isEmpty();
  }

  public Long getId(String user, String pass) {
    List<Persona> resultados = EntityManagerHelper.createQuery("from Persona where username = :user and contrasenia = :pass").setParameter("user", user).setParameter("pass", pass).getResultList();
    long id = resultados.get(0).getId();
    return id;
  }

  public List<String> todosLosUsuarios(){
    List<Persona> resultados = EntityManagerHelper.createQuery("from Persona").getResultList();

    return resultados.stream().map(p -> p.getUsername()).toList();
  }
}
