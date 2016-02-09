package testproject.config;

import com.mysql.jdbc.AbandonedConnectionCleanupThread;
import org.springframework.web.WebApplicationInitializer;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletException;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;

/**
 * Created by Exidnus on 09.02.2016.
 */
public class DestroyerAbandonedConnectionCleanupThread implements WebApplicationInitializer,
                                                                ServletContextListener {
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        servletContext.addListener(this.getClass());
    }

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {

    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        Enumeration<Driver> drivers = DriverManager.getDrivers();
        Driver d = null;
        while (drivers.hasMoreElements()) {
            try {
                d = drivers.nextElement();
                DriverManager.deregisterDriver(d);
            } catch (SQLException ignored) {}
        }
        try {
            AbandonedConnectionCleanupThread.shutdown();
        } catch (InterruptedException ignored) {}
    }
}
