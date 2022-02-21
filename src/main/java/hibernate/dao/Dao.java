package hibernate.dao;

import hibernate.utils.SessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;


public class Dao<T> {
    private final Class<T> type;

    public Dao(Class<T> type) {
        this.type = type;
    }

    public T getByName(String name) {
        SessionFactory sessionFactory = SessionFactoryUtil.getSessionFactory();
        if (sessionFactory == null) {
            return null;
        }
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Query<T> query = session.createQuery("from " + type.getSimpleName() + " a where a.name =: name", type);
            query.setParameter("name", name);
            return query.uniqueResult();
        }
    }

    public T getByAtm(int atmId) {
        SessionFactory sessionFactory = SessionFactoryUtil.getSessionFactory();
        if (sessionFactory == null) {
            return null;
        }
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Query<T> query = session.createQuery("FROM " + type.getSimpleName() + " s WHERE s.atm.id = " + atmId);
            return (T) query.uniqueResult();
        }
    }

    public List<T> getAll() {
        SessionFactory sessionFactory = SessionFactoryUtil.getSessionFactory();
        if (sessionFactory == null) {
            return null;
        }
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            return session.createQuery("SELECT a FROM " + type.getSimpleName() + " a", type).getResultList();
        }
    }

    public T getById(long id) {
        SessionFactory sessionFactory = SessionFactoryUtil.getSessionFactory();
        if (sessionFactory == null) {
            return null;
        }
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            return session.get(type, id);
        }
    }

    public Long create(T value) {
        return performOperationAndGetResult(value, (session, value1) -> (Long) session.save(value));
    }

    public void update(T value) {
        performOperation(value, (session, value1) -> session.update(value));
    }

    public void delete(T value) {
        performOperation(value, (session, value1) -> session.delete(value));
    }

    private void performOperation(T value, BiConsumer<Session, T> operation) {
        SessionFactory sessionFactory = SessionFactoryUtil.getSessionFactory();
        if (sessionFactory == null) {
            return;
        }
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            operation.accept(session, value);
            transaction.commit();
        }
    }

    private <U> U performOperationAndGetResult(T value, BiFunction<Session, T, U> operation) {
        SessionFactory sessionFactory = SessionFactoryUtil.getSessionFactory();
        if (sessionFactory == null) {
            return null;
        }
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            return operation.apply(session, value);
        }
    }
}
