package testproject.vaadin;

import com.vaadin.ui.*;
import testproject.domain.Person;

import java.util.Date;

/**
 * Created by exidnus on 02.02.16.
 */
public class PersonAddingForm extends FormLayout {
    Button save = new Button("Сохранить", this::save);
    Button cancel = new Button("Отмена", this::cancel);
    TextField nameTextField = new TextField("Имя");
    CheckBox isAdminCheckBox = new CheckBox("Админ");
    DateField birthDay = new DateField("День рождения");

    public PersonAddingForm() {
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

        addComponents(nameTextField, isAdminCheckBox, birthDay, actions);
    }

    public void create() {
        getUI().getPersonEditingForm().setVisible(false);
        setVisible(true);
    }

    private void save(Button.ClickEvent event) {
        String name = nameTextField.getValue();
        boolean isAdmin = isAdminCheckBox.getValue();
        Date nowDate = new Date();
        Date birthDayDate = birthDay.getValue();
        int age = (int) ((nowDate.getTime() - birthDayDate.getTime()) / 365 / 24 / 60 / 60 / 1000);

        getUI().getManager().addPerson(new Person(name, age, isAdmin));
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
