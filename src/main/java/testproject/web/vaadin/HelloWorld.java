package testproject.web.vaadin;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.*;

/**
 * Created by Exidnus on 20.01.2016.
 */
@Title("Hello World Simple Example")
@Theme("valo")
public class HelloWorld extends UI {
    @Override
    protected void init(VaadinRequest vaadinRequest) {
        VerticalLayout content = new VerticalLayout();
        content.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
        setContent(content);

        Label label = new Label("Hello World");
        content.addComponent(label);

        Button button = new Button("Push on me!");
        button.addClickListener(clickEvent -> Notification.show("You pushed on me!!"));

        content.addComponent(button);

        content.setComponentAlignment(label, Alignment.MIDDLE_CENTER);
    }
}
