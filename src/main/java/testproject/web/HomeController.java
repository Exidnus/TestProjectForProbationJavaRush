package testproject.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Exidnus on 06.01.2016.
 */
@Controller
public class HomeController {

    @RequestMapping(value = {"/", "/homepage"}, method = RequestMethod.GET)
    public String home() {
        return "home";
    }
}
