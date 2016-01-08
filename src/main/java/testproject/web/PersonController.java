package testproject.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import testproject.data.PersonRepository;

/**
 * Created by Exidnus on 06.01.2016.
 */
@Controller
public class PersonController {
    private PersonRepository personRepository;

    @Autowired
    public PersonController(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @RequestMapping(value = "/people", method = RequestMethod.GET)
    public String persons(Model model) {
        model.addAttribute("personsList", personRepository.getAll());
        return "persons";
    }
}
