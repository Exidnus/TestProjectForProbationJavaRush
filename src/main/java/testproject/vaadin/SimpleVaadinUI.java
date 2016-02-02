package testproject.vaadin;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.MethodProperty;
import com.vaadin.server.ExternalResource;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import testproject.data.PersonManager;
import testproject.domain.Person;

import java.util.List;
import java.util.Locale;

/**
 * Created by Exidnus on 20.01.2016.
 */
@Title("Список людей CrUD Vaadin")
@Theme("valo")
@org.springframework.stereotype.Component("SimpleVaadinUI")
@Scope("prototype")
public class SimpleVaadinUI extends UI {

    //TODO Выполнил интеграцию Vaadin и Spring, мягко говоря, методом научного тыка, нужно разобраться

    private Grid personsGrid = new Grid();
    private Link goToHomePage = new Link("Вернуться к списку проектов", new ExternalResource("homepage"));
    private TextField filter = new TextField();
    private Button newPerson = new Button("Добавить человека");

    private PersonEditingForm personEditingForm = new PersonEditingForm();
    private PersonAddingForm personAddingForm = new PersonAddingForm();

    private BeanItemContainer<Person> beanItemContainer;

    private PersonManager manager;

    @Autowired
    public void setPersonManager(PersonManager manager) {
        this.manager = manager;
    }

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        configureComponents();
        buildLayouts();
    }

    private void configureComponents() {
        filter.setInputPrompt("Фильтр по имени");
        newPerson.addClickListener(clickEvent -> personAddingForm.create());

        personEditingForm.setVisible(false);
        personAddingForm.setVisible(false);

        beanItemContainer = new BeanItemContainer<>(Person.class, manager.getAll());
        personsGrid.setContainerDataSource(beanItemContainer);
        personsGrid.setColumnOrder("name", "age");

        List<Grid.Column> columns = personsGrid.getColumns();
        columns.get(0).setHeaderCaption("Имя");
        columns.get(1).setHeaderCaption("Возраст");
        columns.get(2).setHeaderCaption("Админ");
        personsGrid.removeColumn(columns.get(3).getPropertyId());
        personsGrid.removeColumn(columns.get(4).getPropertyId());
        columns.get(5).setHeaderCaption("Дата создания записи");

        personsGrid.setSelectionMode(Grid.SelectionMode.SINGLE);
        personsGrid.addSelectionListener(selectionEvent -> personEditingForm.edit((Person) personsGrid
                .getSelectedRow()));

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

        HorizontalLayout main = new HorizontalLayout(center, personEditingForm, personAddingForm);
        main.setSizeFull();
        main.setExpandRatio(center, 1);

        VerticalLayout reallyMain = new VerticalLayout(main, goToHomePage);
        reallyMain.setSizeFull();
        reallyMain.setExpandRatio(main, 1);
        reallyMain.setComponentAlignment(goToHomePage, Alignment.BOTTOM_CENTER);

        setContent(reallyMain);
    }

    void refreshGrid() {

        beanItemContainer = new BeanItemContainer<>(Person.class, manager.getAll());
        try {
            personsGrid.setContainerDataSource(beanItemContainer);
        } catch (Exception ignored) {
            //Здесь выбрасывается исключение (NullPointerException - причина)
            //На форуме ваадин об этой ошибки писали, в качестве решения
            //предлагалось удалять все итемы из бинИтемКонтейнера (мне это не помогло)
            //TODO Что-то сделать с этим нужно
        }
        personEditingForm.setVisible(false);
        personAddingForm.setVisible(false);
    }

    PersonManager getManager() {
        return manager;
    }

    PersonAddingForm getPersonAddingForm() {
        return personAddingForm;
    }

    PersonEditingForm getPersonEditingForm() {
        return personEditingForm;
    }
}
