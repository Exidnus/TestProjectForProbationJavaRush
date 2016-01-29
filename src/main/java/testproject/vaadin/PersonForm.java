package testproject.vaadin;

import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.ui.*;
import testproject.domain.Person;

/**
 * Created by Exidnus on 23.01.2016.
 */
public class PersonForm extends FormLayout {

    Button cancel = new Button("Отменить", this::cancel);
    TextField nameField = new TextField("Имя");
    CheckBox admin = new CheckBox("Админ");
    TextField ageField = new TextField("Возраст");
    BeanFieldGroup<Person> beanFieldGroup;
    //DateField birthDate = new DateField("Дата рождения");
    Person person;
    Button save = new Button("Сохранить", this::save);

    public PersonForm() {
        configureComponents();
        buildLayout();
    }

    private void configureComponents() {

    }

    private void buildLayout() {
        setSizeUndefined();
        setMargin(true);

        HorizontalLayout actions = new HorizontalLayout(save, cancel);
        actions.setSpacing(true);

        addComponents(actions, nameField, ageField, admin);
    }

    void edit(Person person) {
        this.person = person;

        nameField.setValue(person.getName());
        ageField.setValue(String.valueOf(person.getAge()));
        admin.setValue(person.isAdmin());
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

    @Override
    public SimpleVaadinUI getUI() {
        return (SimpleVaadinUI) super.getUI();
    }
}
