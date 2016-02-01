package testproject.config;

import com.mysql.jdbc.AbandonedConnectionCleanupThread;
import org.springframework.stereotype.Component;
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
 * Created by Exidnus on 31.01.2016.
 */
/*
Класс нужен, чтобы избежать потенциальных утечек памяти. Без него в логах Томката появлялись сообщения вида
WARNING [http-apr-8080-exec-24] org.apache.catalina.loader.WebappClassLoaderBase.clearReferencesThreads
The web application [TestProject] appears to have started a thread named [Abandoned connection cleanup thread]
but has failed to stop it. This is very likely to create a memory leak. Stack trace of thread:
 java.lang.Object.wait(Native Method)
 java.lang.ref.ReferenceQueue.remove(ReferenceQueue.java:143)
 com.mysql.jdbc.AbandonedConnectionCleanupThread.run(AbandonedConnectionCleanupThread.java:43)
Действительно, если много раз деплоить-андеплоить приложение без данного класса, утечки памяти наблюдались.
 */
@Component
public class UnregisteringDriverListener implements ServletContextListener, WebApplicationInitializer {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {

    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        try {
            AbandonedConnectionCleanupThread.shutdown();
        } catch (Throwable ignored) {}

        Enumeration<Driver> drivers = DriverManager.getDrivers();
        while (drivers.hasMoreElements()) {
            deregisterDrivers(drivers.nextElement());
        }
    }

    private void deregisterDrivers(Driver driver) {
        try {
            DriverManager.deregisterDriver(driver);
        } catch (SQLException ignored) {}
    }

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        servletContext.addListener(this.getClass());
    }
}
