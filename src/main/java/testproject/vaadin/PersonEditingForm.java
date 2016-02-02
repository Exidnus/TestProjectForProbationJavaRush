package testproject.vaadin;

import com.vaadin.ui.*;
import testproject.domain.Person;

/**
 * Created by Exidnus on 23.01.2016.
 */
public class PersonEditingForm extends FormLayout {

    private Button save = new Button("Сохранить", this::save);
    private Button cancel = new Button("Отменить", this::cancel);
    private Button delete = new Button("Удалить", this::delete);
    private TextField nameField = new TextField("Имя");
    private CheckBox admin = new CheckBox("Админ");
    private TextField ageField = new TextField("Возраст");

    private Person person;

    public PersonEditingForm() {
        configureComponents();
        buildLayout();
    }

    private void configureComponents() {

    }

    private void buildLayout() {
        setSizeUndefined();
        setMargin(true);

        HorizontalLayout actions = new HorizontalLayout(save, cancel, delete);
        actions.setSpacing(true);

        addComponents(actions, nameField, ageField, admin);
    }

    void edit(Person person) {
        this.person = person;

        nameField.setValue(person.getName());
        ageField.setValue(String.valueOf(person.getAge()));
        admin.setValue(person.isAdmin());

        getUI().getPersonAddingForm().setVisible(false);
        setVisible(true);
    }

    private void save(Button.ClickEvent event) {
        person.setName(nameField.getValue());
        person.setAge(Integer.parseInt(ageField.getValue()));
        person.setAdmin(admin.getValue());

        getUI().getManager().update(person);
        getUI().refreshGrid();
    }

    private void cancel(Button.ClickEvent event) {
        Notification.show("Отмена");
        setVisible(false);
    }

    private void delete(Button.ClickEvent event) {
        getUI().getManager().deletePersonById(person.getId());
        getUI().refreshGrid();
    }

    @Override
    public SimpleVaadinUI getUI() {
        return (SimpleVaadinUI) super.getUI();
    }
}
