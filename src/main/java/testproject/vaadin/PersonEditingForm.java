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
    private TextField nameTextField = new TextField("Имя");
    private CheckBox isAdminCheckBox = new CheckBox("Админ");
    private TextField ageTextField = new TextField("Возраст");

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

        addComponents(actions, nameTextField, ageTextField, isAdminCheckBox);
    }

    void edit(Person person) {
        this.person = person;

        nameTextField.setValue(person.getName());
        ageTextField.setValue(String.valueOf(person.getAge()));
        isAdminCheckBox.setValue(person.isAdmin());

        getUI().getPersonAddingForm().setVisible(false);
        setVisible(true);
    }

    private void save(Button.ClickEvent event) {
        String name = nameTextField.getValue();
        boolean isAdmin = isAdminCheckBox.getValue();
        int age = Integer.parseInt(ageTextField.getValue());

        if (name.length() < 2 || age < 1 || age > 100) {
            Notification.show("Неверный ввод!", "Имя должно быть не короче 2 букв, возраст должен" +
                    "быть\r\nот 1 года до 100 лет включительно", Notification.Type.ERROR_MESSAGE);
        } else {
            person.setName(name);
            person.setAge(age);
            person.setAdmin(isAdmin);

            getUI().getManager().update(person);
            getUI().refreshGrid();
        }
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
