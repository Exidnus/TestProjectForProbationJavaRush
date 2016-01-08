package testproject.config;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;

/**
 * Created by Exidnus on 08.01.2016.
 */
@org.springframework.context.annotation.Configuration
public class DataConfig {
    @Bean(destroyMethod = "close")
    public SessionFactory sessionFactory() {
        return new Configuration().configure().buildSessionFactory();
    }
}
