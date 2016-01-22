package testproject.vaadin;

import org.springframework.web.WebApplicationInitializer;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration.Dynamic;
/**
 * Created by Exidnus on 22.01.2016.
 */
public class VaadinServlerInitializer implements WebApplicationInitializer {
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        Dynamic myServlet = servletContext.addServlet("Vaadin", ru.xpoft.vaadin.SpringVaadinServlet.class);
        myServlet.addMapping("/peoplevaadin/*", "/VAADIN/*");
        myServlet.setInitParameter("beanName", "SimpleVaadinUI");
    }
}
