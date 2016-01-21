package testproject.web.vaadin;

import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinServlet;

import javax.servlet.annotation.WebServlet;

/**
 * Created by Exidnus on 20.01.2016.
 */
@WebServlet(urlPatterns = {"/peoplevaadin/*", "/VAADIN/*"})
@VaadinServletConfiguration(ui = HelloWorld.class, productionMode = false)
public class MyUIServlet extends VaadinServlet {
}
