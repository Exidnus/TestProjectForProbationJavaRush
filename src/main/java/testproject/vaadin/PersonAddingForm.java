package testproject.vaadin;

import com.vaadin.ui.*;
import testproject.domain.Person;

import java.util.Date;

/**
 * Created by exidnus on 02.02.16.
 */
class PersonAddingForm extends FormLayout {
    private Button save = new Button("Сохранить", this::save);
    private Button cancel = new Button("Отмена", this::cancel);
    private TextField nameTextField = new TextField("Имя");
    private CheckBox isAdminCheckBox = new CheckBox("Админ");
    private DateField birthDay = new DateField("День рождения");

    PersonAddingForm() {
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

    void create() {
        getUI().getPersonEditingForm().setVisible(false);
        setVisible(true);
    }

    private void save(Button.ClickEvent event) {
        try {
            String name = nameTextField.getValue();
            boolean isAdmin = isAdminCheckBox.getValue();
            Date nowDate = new Date();
            Date birthDayDate = birthDay.getValue();
            int age = (int) ((nowDate.getTime() - birthDayDate.getTime()) / 365 / 24 / 60 / 60 / 1000);

            if (name.length() < 2 || age > 100 || age < 1) {
                Notification.show("Неверный ввод!", "Имя должно быть не короче 2 букв, возраст должен" +
                        "быть\r\nот 1 года до 100 лет включительно", Notification.Type.ERROR_MESSAGE);
            } else {
                getUI().getManager().addPerson(new Person(name, age, isAdmin));
                nameTextField.setValue("");
                isAdminCheckBox.setValue(false);
                birthDay.setValue(null);
                getUI().refreshGrid();
            }
        } catch (NullPointerException e) {
            Notification.show("Неверный ввод!", "Нужно указать имя и " +
                    "дату рождения!", Notification.Type.ERROR_MESSAGE);
        }
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
