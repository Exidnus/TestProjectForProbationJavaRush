package testproject.data;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import testproject.domain.Person;

import java.io.IOException;
import java.util.Properties;

/**
 * Created by Exidnus on 23.01.2016.
 */
@org.springframework.context.annotation.Configuration
@EnableTransactionManagement
@ComponentScan
public class HibernateRepositoryTestConfig {

    @Bean
    public PlatformTransactionManager platformTransactionManager(SessionFactory sessionFactory) {
        return new HibernateTransactionManager(sessionFactory);
    }

    @Bean(destroyMethod = "shutdown")
    public DataSource datasource() {
        EmbeddedDatabaseBuilder edb = new EmbeddedDatabaseBuilder();
        edb.setType(EmbeddedDatabaseType.H2);
        edb.addScript("schema.sql");
        EmbeddedDatabase embeddedDatabase = edb.build();
        return embeddedDatabase;
    }

    @Bean(destroyMethod = "close")
    public SessionFactory sessionFactory() {
        try {
            LocalSessionFactoryBean lsfb = new LocalSessionFactoryBean();
            lsfb.setDataSource(datasource());
            lsfb.setPackagesToScan("testproject.domain");
            Properties properties = new Properties();
            properties.setProperty("dialect", "org.hibernate.dialect.H2Dialect");
            lsfb.setHibernateProperties(properties);
            lsfb.afterPropertiesSet();
            return lsfb.getObject();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
