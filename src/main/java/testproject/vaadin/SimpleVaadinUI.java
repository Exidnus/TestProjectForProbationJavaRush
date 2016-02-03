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

    private Button previous = new Button("Следующая страница");
    private Button next = new Button("Следующая страница");

    private int currentPosition = 0;
    private int length = 10;

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
        filter.setInputPrompt("Поиск по имени");
        filter.addTextChangeListener(textChangeEvent -> {
            if (textChangeEvent.getText().length() == 0) {
                currentPosition = 0;
                refreshGrid();
            }
            else refreshGrid(textChangeEvent.getText());
        });

        newPerson.addClickListener(clickEvent -> personAddingForm.create());

        personEditingForm.setVisible(false);
        personAddingForm.setVisible(false);

        previous.addClickListener(clickEvent -> {
            if (currentPosition > 0) currentPosition -= length;
            refreshGrid();
        });
        next.addClickListener(clickEvent -> {
            if (currentPosition + length < manager.getCount()) currentPosition += length;
            refreshGrid();
        });

        beanItemContainer = new BeanItemContainer<>(Person.class);
        personsGrid.setContainerDataSource(beanItemContainer);
        personsGrid.setColumnOrder("name", "age");

        List<Grid.Column> columns = personsGrid.getColumns();
        columns.get(0).setHeaderCaption("Имя");
        columns.get(1).setHeaderCaption("Возраст");
        personsGrid.removeColumn(columns.get(2).getPropertyId());
        personsGrid.removeColumn(columns.get(3).getPropertyId());
        personsGrid.removeColumn(columns.get(4).getPropertyId());
        columns.get(5).setHeaderCaption("Дата создания записи");
        columns.get(6).setHeaderCaption("Админ");

        personsGrid.setSelectionMode(Grid.SelectionMode.SINGLE);
        personsGrid.addSelectionListener(selectionEvent -> personEditingForm.edit((Person) personsGrid
                .getSelectedRow()));

        refreshGrid();
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

        HorizontalLayout changePage = new HorizontalLayout(previous, next);

        VerticalLayout reallyMain = new VerticalLayout(main, changePage, goToHomePage);
        reallyMain.setSizeFull();
        reallyMain.setExpandRatio(main, 1);
        reallyMain.setComponentAlignment(goToHomePage, Alignment.BOTTOM_CENTER);
        reallyMain.setComponentAlignment(changePage, Alignment.MIDDLE_CENTER);

        setContent(reallyMain);
    }

    void refreshGrid() {
        filter.setValue("");

        beanItemContainer = new BeanItemContainer<>(Person.class,
                manager.getPageWithoutOrder(currentPosition, length));
        try {
            personsGrid.setContainerDataSource(beanItemContainer);
        } catch (Exception ignored) {
            //Здесь выбрасывается исключение (NullPointerException - причина)
            //На форуме ваадин об этой ошибки писали, в качестве решения
            //предлагалось удалять все итемы из бинИтемКонтейнера (мне это не помогло)
            //TODO Что-то сделать с этим, пожалуй, нужно
        }
        personEditingForm.setVisible(false);
        personAddingForm.setVisible(false);
    }

    void refreshGrid(String name) {
        beanItemContainer = new BeanItemContainer<>(Person.class, manager.findByName(name));
        try {
            personsGrid.setContainerDataSource(beanItemContainer);
        } catch (Exception ignored) {}
        personAddingForm.setVisible(false);
        personEditingForm.setVisible(false);
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
