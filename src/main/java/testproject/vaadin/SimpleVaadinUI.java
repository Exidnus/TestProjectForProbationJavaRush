package testproject.vaadin;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.server.ExternalResource;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import testproject.domain.Person;
import testproject.data.PersonRepository;

import java.util.List;

/**
 * Created by Exidnus on 20.01.2016.
 */
@Title("Hello World Simple Example")
@Theme("valo")
@org.springframework.stereotype.Component("SimpleVaadinUI")
@Scope("prototype")
public class SimpleVaadinUI extends UI {

    //TODO Выполнил интеграцию Vaadin и Spring, мягко говоря, методом научного тыка, нужно разобраться

    Grid personsGrid = new Grid();
    Link goToHomePage = new Link("Вернуться к списку проектов", new ExternalResource("homepage"));
    private PersonRepository personRepository;

    @Autowired
    public void setPersonRepository(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        configureComponents();
        buildLayouts();
    }

    private void configureComponents() {
        personsGrid.setColumns("Имя", "Возраст", "Админ", "Дата создания");
        List<Person> persons = personRepository.getAll();
        for (Person p : persons) {
            personsGrid.addRow(p.getName(), String.valueOf(p.getAge()), p.isAdmin() ? "Да" : "Нет", p.getSimpleDate());
        }
    }

    private void buildLayouts() {
        personsGrid.setSizeFull();

        VerticalLayout main = new VerticalLayout();
        main.addComponent(personsGrid);
        main.addComponent(goToHomePage);

        setContent(main);
    }
}
