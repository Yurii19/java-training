package hibernate.utils;

import hibernate.models.Atm;
import hibernate.models.Client;
import hibernate.models.Operation;
import org.hibernate.SessionFactory;
import org.hibernate.HibernateException;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class SessionFactoryUtil {
    private static final Logger LOG = LoggerFactory.getLogger(SessionFactoryUtil.class);
    private static SessionFactory sessionFactory;

    private SessionFactoryUtil() {
    }

    public static SessionFactory getSessionFactory() {
        if (sessionFactory != null) {
            return sessionFactory;
        }
        Configuration configuration = new org.hibernate.cfg.Configuration().configure();
        configuration.addAnnotatedClass(Atm.class);
        configuration.addAnnotatedClass(Client.class);
        configuration.addAnnotatedClass(Operation.class);
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties())
                .build();
        try {
            sessionFactory = configuration.buildSessionFactory(registry);
            return sessionFactory;
        } catch (HibernateException e) {
            LOG.error("Could not create Session Factory",e);
        }
        return null;
    }
}
