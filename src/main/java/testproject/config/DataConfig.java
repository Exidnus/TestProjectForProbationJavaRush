package testproject.config;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by Exidnus on 08.01.2016.
 */
@org.springframework.context.annotation.Configuration
@EnableTransactionManagement
public class DataConfig {

    @Bean
    public DataSource dataSource() {
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setDatabaseName("test");
        dataSource.setUser("root");
        dataSource.setPassword("root");
        dataSource.setEncoding("UTF-8");
        return dataSource;
    }

    /*@Bean(destroyMethod = "close") //и еще один вариант
    public DataSource dataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/test");
        dataSource.setUsername("root");
        dataSource.setPassword("root");
        return dataSource;
    }*/


    /*@Bean //и еще один
    public DataSource dataSource() {
        DriverManagerDataSource driverManager = new DriverManagerDataSource();
        driverManager.setDriverClassName("com.mysql.jdbc.Driver");
        driverManager.setUrl("jdbc:mysql://localhost:3306/test");
        driverManager.setUsername("root");
        driverManager.setPassword("root");
        return driverManager;
    }*/

    @Bean(destroyMethod = "close")
    public SessionFactory sessionFactory(DataSource dataSource) {
        try {
            LocalSessionFactoryBean lsfb = new LocalSessionFactoryBean();
            lsfb.setDataSource(dataSource);
            lsfb.setPackagesToScan("testproject.domain");
            Properties properties = new Properties();
            properties.setProperty("dialect", "org.hibernate.dialect.MySQL5Dialect");
            lsfb.setHibernateProperties(properties);
            lsfb.afterPropertiesSet();
            return lsfb.getObject();
        } catch (IOException e) {
            return null;
        }
    }

    @Bean
    public PlatformTransactionManager platformTransactionManager(SessionFactory sessionFactory) {
        return new HibernateTransactionManager(sessionFactory);
    }
}
