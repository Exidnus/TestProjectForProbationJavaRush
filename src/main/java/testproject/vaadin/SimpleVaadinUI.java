package testproject.vaadin;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.ExternalResource;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import testproject.data.PersonManager;
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

    private PersonManager manager;

    @Autowired
    public void setPersonRepository(PersonManager manager) {
        this.manager = manager;
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

        /*personsGrid.setColumns("Имя", "Возраст", "Админ", "Дата создания");
        List<Person> persons = manager.getAll();
        for (Person p : persons) {
            personsGrid.addRow(p.getName(), String.valueOf(p.getAge()), p.isAdmin() ? "Да" : "Нет", p.getSimpleDate());
        }*/

        personsGrid.setContainerDataSource(new BeanItemContainer<>(Person.class, manager.getAll()));
        personsGrid.setColumnOrder("name", "age");
        //List<Grid.Column> columns = personsGrid.getColumns();
        //personsGrid.removeColumn(columns.get(3));
        //personsGrid.removeColumn(columns.get(4).toString());
        personsGrid.setSelectionMode(Grid.SelectionMode.SINGLE);
        personsGrid.addSelectionListener(selectionEvent -> personForm.edit((Person) personsGrid.getSelectedRow()));
    }

    private void buildLayouts() {
        HorizontalLayout actions = new HorizontalLayout(filter, newPerson);
        actions.setWidth("100%");
        filter.setWidth("100%");
        actions.setExpandRatio(filter, 1);

        VerticalLayout center = new VerticalLayout(actions, personsGrid);
        center.setSizeFull();
        personsGrid.setSizeFull();
        center.setExpandRatio(personsGrid, 1);

        HorizontalLayout main = new HorizontalLayout(center, personForm);
        main.setSizeFull();
        main.setExpandRatio(center, 1);

        VerticalLayout reallyMain = new VerticalLayout(main, goToHomePage);
        reallyMain.setSizeFull();
        reallyMain.setExpandRatio(main, 1);
        reallyMain.setComponentAlignment(goToHomePage, Alignment.BOTTOM_CENTER);

        setContent(reallyMain);
    }


}
