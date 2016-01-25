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
@Title("Список людей CrUD Vaadin")
@Theme("valo")
@org.springframework.stereotype.Component("SimpleVaadinUI")
@Scope("prototype")
public class SimpleVaadinUI extends UI {

    //TODO Выполнил интеграцию Vaadin и Spring, мягко говоря, методом научного тыка, нужно разобраться

    Grid personsGrid = new Grid();
    Link goToHomePage = new Link("Вернуться к списку проектов", new ExternalResource("homepage"));
    TextField filter = new TextField();
    Button newPerson = new Button("Добавить человека");
    PersonForm personForm = new PersonForm();

    private PersonRepository personRepository;

    public PersonRepository getPersonRepository() {
        return personRepository;
    }

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
        filter.setInputPrompt("Фильтр по имени");
        newPerson.addClickListener(clickEvent -> personForm.edit(new Person()));
        //personsGrid.addSelectionListener(clickEvent -> personForm.edit((Person) personsGrid.getSelectedRow()));

        personForm.setVisible(false);

        personsGrid.setColumns("Имя", "Возраст", "Админ", "Дата создания");
        List<Person> persons = personRepository.getAll();
        for (Person p : persons) {
            personsGrid.addRow(p.getName(), String.valueOf(p.getAge()), p.isAdmin() ? "Да" : "Нет", p.getSimpleDate());
        }
    }

    private void buildLayouts() {
        HorizontalLayout actions = new HorizontalLayout(filter, newPerson);
        actions.setWidth("100%");
        filter.setWidth("100%");
        actions.setExpandRatio(filter, 1);

        VerticalLayout center = new VerticalLayout(actions, personsGrid, goToHomePage);
        center.setSizeFull();
        personsGrid.setSizeFull();
        center.setExpandRatio(personsGrid, 1);
        center.setComponentAlignment(goToHomePage, Alignment.BOTTOM_CENTER);

        HorizontalLayout main = new HorizontalLayout(center, personForm);
        main.setSizeFull();
        main.setExpandRatio(center, 1);

        setContent(main);
    }


}
