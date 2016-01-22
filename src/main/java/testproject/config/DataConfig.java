package testproject.config;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Created by Exidnus on 08.01.2016.
 */
@org.springframework.context.annotation.Configuration
@EnableTransactionManagement
public class DataConfig {
    @Bean(destroyMethod = "close")
    public SessionFactory sessionFactory() {
        return new Configuration().configure().buildSessionFactory();
    }
}
