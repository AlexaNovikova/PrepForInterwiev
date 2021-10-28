package ru.gb;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public class AbstractDao<T, ID extends Serializable> {
    protected SessionFactory sessionFactory;
    protected Class<T> typeClass;
    protected Class<ID> idClass;

    public AbstractDao(SessionFactory sessionFactory, Class<T> typeClass, Class<ID> idClass) {
        this.sessionFactory = sessionFactory;
        this.typeClass = typeClass;
        this.idClass = idClass;
    }

    public Optional<T> findById(ID id) {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            T result = (T) session.get(typeClass, id);
            session.getTransaction().commit();
            return Optional.ofNullable(result);
        }
    }

    public List<T> findAll() {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            String hql = "from " + typeClass.getSimpleName();
            Query<T> query = session.createQuery(hql);
            List<T> list = query.list();
            session.getTransaction().commit();
            return list;
        }
    }

    public T saveOrUpdate(T obj) {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            session.saveOrUpdate(obj);
            session.getTransaction().commit();
            return obj;
        }
    }

    public void delete(T obj) {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            session.delete(obj);
            session.getTransaction().commit();
        }
    }
}
