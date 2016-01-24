package testproject.vaadin;

import com.vaadin.ui.*;
import testproject.domain.Person;

/**
 * Created by Exidnus on 23.01.2016.
 */
public class PersonForm extends FormLayout {

    Button cancel = new Button("Отменить", this::cancel);
    TextField nameField = new TextField("Имя");
    OptionGroup admin = new OptionGroup("Админ");
    Button save = new Button("Сохранить", this::save);
    DateField birthDate = new DateField("Дата рождения");

    Person person;

    public PersonForm() {
        configureComponents();
        buildLayout();
    }

    private void configureComponents() {
        admin.addItems("Да", "Нет");
    }

    private void buildLayout() {
        setSizeUndefined();
        setMargin(true);

        HorizontalLayout actions = new HorizontalLayout(save, cancel);
        actions.setSpacing(true);

        addComponents(actions, nameField, admin, birthDate);
    }

    void edit(Person person) {
        this.person = person;
        setVisible(true);
    }

    private void save(Button.ClickEvent event) {
        String name = nameField.getValue();
        boolean isAdmin = admin.getValue().equals("Да");

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
