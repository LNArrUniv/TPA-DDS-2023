package ar.edu.utn.frba.dds.Persistencia.repositorios;

import ar.edu.utn.frba.dds.Modelos.Entidad;
import ar.edu.utn.frba.dds.Modelos.Incidente;
import ar.edu.utn.frba.dds.Modelos.UbicacionDTO.Localidad;
import ar.edu.utn.frba.dds.Persistencia.EntityManagerHelper;
import ar.edu.utn.frba.dds.Persistencia.repositorios.daos.DAO;
import ar.edu.utn.frba.dds.Persistencia.repositorios.daos.DAOHibernate;
import java.util.List;

public class RepositorioIncidentes extends Repositorio<Incidente> {
  private static RepositorioIncidentes instance = null;

  private RepositorioIncidentes(DAO<Incidente> dao) {
    super(dao);
  }

  public static RepositorioIncidentes getInstance() {
    if (instance == null) {
      instance = new RepositorioIncidentes(new DAOHibernate<>(Incidente.class));
    }
    return instance;
  }

  public List getActivos() {
    return EntityManagerHelper.createQuery("from Incidente where estaResuelto = :false").setParameter("false", false).getResultList();
  }

  public List getResueltos() {
    return EntityManagerHelper.createQuery("from Incidente where estaResuelto = :true").setParameter("true", true).getResultList();
  }

  public List incidentesEnUbicacion(Localidad ubicacion){
    return EntityManagerHelper.createQuery("from Incidente where localidad = :localidad and estaResuelto = false").setParameter("localidad", ubicacion).getResultList();
  }

  public Boolean hayIncidentesActivosEnServicioDeComunidad(Long servicioId, Long comunidadId){
    List resultados = EntityManagerHelper.createQuery("from Incidente where servicio_id = :servicio and comunidad_id = :comunidad and estaResuelto = 0").setParameter("servicio", servicioId).setParameter("comunidad", comunidadId).getResultList();
    return !resultados.isEmpty();
  }

  public List incidentesDeServicioYComunidad(Long servicioId, Long comunidadId){
    List resultados = EntityManagerHelper.createQuery("from Incidente where servicio_id = :servicio and comunidad_id = :comunidad").setParameter("servicio", servicioId).setParameter("comunidad", comunidadId).getResultList();
    return resultados;
  }

  public List incidentesDeEntidad(Entidad entidad){
    List resultados = EntityManagerHelper.createQuery("from Incidente where entidad_id = :entidad").setParameter("entidad", entidad.getId()).getResultList();

    return resultados;
  }
}
