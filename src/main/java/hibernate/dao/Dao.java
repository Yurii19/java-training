package hibernate.dao;

import hibernate.models.Client;
import hibernate.utils.SessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.function.BiConsumer;
import java.util.function.BiFunction;

public class Dao<T> {
    private final Class<T> type;

    public Dao(Class<T> type) {
        this.type = type;
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
      return   performOperationAndGetResult(value, (session, value1) -> (Long) session.save(value));
       // return value;
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
            operation.accept(session, value);
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
