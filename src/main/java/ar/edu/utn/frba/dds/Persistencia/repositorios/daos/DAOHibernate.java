package ar.edu.utn.frba.dds.Persistencia.repositorios.daos;

import ar.edu.utn.frba.dds.Persistencia.EntityManagerHelper;
import java.util.List;

public class DAOHibernate<T> implements DAO<T> {
    private Class<T> type;

    public DAOHibernate(Class<T> type){
        this.type = type;
    }

    @Override
    public List<T> all() {
        return EntityManagerHelper.getEntityManager().createQuery("FROM " + type.getSimpleName()).getResultList();
    }

    @Override
    public T get(long id) {
        return EntityManagerHelper.getEntityManager().find(type, id);
    }

    @Override
    public void add(Object object) {
        EntityManagerHelper.getEntityManager().getTransaction().begin();
        EntityManagerHelper.getEntityManager().persist(object);
        EntityManagerHelper.getEntityManager().getTransaction().commit();
    }

    @Override
    public void update(Object object) {
        EntityManagerHelper.getEntityManager().getTransaction().begin();
        EntityManagerHelper.getEntityManager().merge(object);
        EntityManagerHelper.getEntityManager().getTransaction().commit();
    }

    @Override
    public void clean() {
        //
    }

    @Override
    public void delete(Object object) {
        EntityManagerHelper.getEntityManager().getTransaction().begin();
        EntityManagerHelper.getEntityManager().remove(object);
        EntityManagerHelper.getEntityManager().getTransaction().commit();
    }
}