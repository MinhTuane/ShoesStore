package shoesStore.controller.web;

import java.util.logging.Logger;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateController {
	
private static final SessionFactory sessionFactory;
	
	static {
        try {
            Logger logger = Logger.getLogger("Mylogger");
            logger.info("Trying to create a test connection with database");
            Configuration configuration = new Configuration().configure();
            configuration.configure();
            StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration
                    .getProperties());
            sessionFactory = configuration.buildSessionFactory(builder.build());
        } catch (Throwable ex) {
            System.out.println("SessionFactory creation failed with error" + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

	//Tạo mới sessionFactory
	
	//Lấy sessionFactory
	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	//Ngắt kết nối với sessionFactory
	public static void closeSessionFactory() {
		getSessionFactory().close();
	}
}
