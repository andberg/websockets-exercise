package se.berglund.datastorage;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

/* This is a tool-box class that helps 
 * with creating a sessionFactory, 
 * to lessen code-replication.
 * */

public class HibernateSessionFactory {
	
	private static final SessionFactory sessionFactory;

    static {
            try {
                Configuration cfg = new Configuration().configure("hibernate.cfg.xml");         
                StandardServiceRegistryBuilder sb = new StandardServiceRegistryBuilder();
                sb.applySettings(cfg.getProperties());
                StandardServiceRegistry standardServiceRegistry = sb.build();                   
                sessionFactory = cfg.buildSessionFactory(standardServiceRegistry);      
                
            } catch (Throwable th) {
                    System.err.println("Enitial SessionFactory creation failed" + th);
                    throw new ExceptionInInitializerError(th);
            }
    }
    public static SessionFactory getSessionFactory() {
            return sessionFactory;
    }

}
